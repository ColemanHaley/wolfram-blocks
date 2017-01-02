package wolfram.blocks.model;

import java.util.ArrayList;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import wolfram.blocks.view.Block;

public class WolframModel {
	private KernelLink ml = null; //put this in mainapp and kill them at the end of running
	public WolframModel(KernelLink ml){
		

	   this.ml = ml;

	    try {
	        // Get rid of the initial InputNamePacket the kernel will send
	        // when it is launched.
	        ml.discardAnswer();

	    } catch (MathLinkException e) {
	        System.out.println("MathLinkException occurred: " + e.getMessage());
	    }
	}
	
	public void buildModel(AnchorPane blockArea) throws MultipleHeadException{		//get rid of when try-catch		
				//((AnchorPane) ((AnchorPane) rootLayout.getCenter()).getChildren().get(0))
		ObservableList<Node> blocks = blockArea.getChildren();
		Block head = null;
		for( Node n : blocks ){
			Node a = ((Group) n).getChildren().get(0);
			
    		if(a instanceof Block){ //Safety check! 
    			System.out.println("U there bruh\n");
    			if(((Block) a).getOutArgs().size() == 0 && head == null){
    				head = (Block) a; 
    			}
    			else if (((Block) a).getOutArgs().size() == 0 && head != null){
    				throw new MultipleHeadException("More than one head block"); //Add try-catch
    			}
    		}
    	}
		if (head == null){
			System.out.println("ahhhhhhhhhhh");
			return;
		}
		//TODO handle no exprs made
		com.wolfram.jlink.Expr result = toMExpr(head);
		System.out.println(result.toString());
		
	}
	private com.wolfram.jlink.Expr toMExpr(Block head){
		try{
			//TODO collapse into another private function for less code duplication (with Expr type method)
			ml.evaluate( String.format("ToExpression[%s, InputForm, Inactive]", head.getName()) );
			ml.waitForAnswer();
			com.wolfram.jlink.Expr headExpr = ml.getExpr();
			ArrayList<com.wolfram.jlink.Expr> exprs = new ArrayList<com.wolfram.jlink.Expr>();
			for(InputNode in : head.getInputs()){
				for(OutputNode out : in.getArgs()){
					System.out.println("Y tho\n");
					exprs.add(toMExpr(out.getParent()));
					
				}
			}
			
			doMathematicaStuff(exprs, headExpr); //Now there should be an applied function in the stream f[a,b,c...]
			return ml.getExpr();
		

		} catch (MathLinkException e) {
	        System.out.println("MathLinkException occurred: " + e.getMessage());
	        return new com.wolfram.jlink.Expr("Error");
		}
	}

	private com.wolfram.jlink.Expr toMExpr(Expr head){
		try{
			ml.evaluate( String.format("ToExpression[%s, InputForm, Inactive]", head.getType()) );
			ml.waitForAnswer();
			com.wolfram.jlink.Expr headExpr = ml.getExpr();
			//System.out.println(headExpr.toString());
			ArrayList<com.wolfram.jlink.Expr> exprs = new ArrayList<com.wolfram.jlink.Expr>();
			for(InputNode in : head.getInputs()){
				for(OutputNode out : in.getArgs()){
					exprs.add(toMExpr(out.getParent()));
					System.out.println("Y tho\n");
				}
			}
			
			doMathematicaStuff(exprs, headExpr); //Now there should be an applied function in the stream f[a,b,c...]
			return ml.getExpr();
	
		} catch (MathLinkException e) {
	        System.out.println("MathLinkException occurred: " + e.getMessage());
	        return new com.wolfram.jlink.Expr("Error");
		}
	}
	
	private void doMathematicaStuff(ArrayList<com.wolfram.jlink.Expr> exprs, com.wolfram.jlink.Expr headExpr) {
		
		try {
			//Get a mathematica list of all the appropriate exprs
			ml.putFunction("EvaluatePacket", 1);
			ml.putFunction("List", exprs.size());
			for(com.wolfram.jlink.Expr ex : exprs){
				ml.put(ex);
			}
			ml.endPacket();
			ml.waitForAnswer();
			com.wolfram.jlink.Expr list = ml.getExpr();
			
			//Apply headExpr to all the elements in the list
			ml.putFunction("EvaluatePacket", 1);
			ml.putFunction("Apply", 2);
			ml.put(headExpr);
			ml.put(list);
			ml.endPacket();
			ml.waitForAnswer();
		} catch (MathLinkException e) {
			System.out.println("MathLinkException occurred: " + e.getMessage());
		}

	}
}
	

