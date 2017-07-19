package wolfram.blocks.model;
import java.util.*;

public class Expr {
	private long id;
	
	private boolean collapsible = false;
	private boolean hasOutput = false;
	private boolean hasInputs = false;
	//private boolean attributesSet = false;
	
	private boolean collapsed;
	private ArrayList<InputNode> inputs;
	private OutputNode output = null;
	//private boolean groupMember;
	//private boolean isGroupHead;
	private String blockLabel;
	private String type;
	
	//private HashMap<String, Object> attributes;
	
	
	public Expr(Function fn) {
		if (fn == null) {
			System.out.println("thats how java works");
		}
		this.type = fn.getName();
		this.blockLabel = fn.getLabel();
		this.collapsible = fn.isCollapsible();
		this.hasOutput = fn.isHasoutputs();
		this.output = (this.hasOutput) ? new OutputNode(this) : null;
		this.hasInputs = (fn.getNumInputs() > 0) ? true : false;
		this.inputs = fn.getIn();
	}
	
	
	//public void setAttributes(HashMap<String, Object> attr) {
	//	
	//}
	
	public String getBlockLabel() {return blockLabel;}
	public void setBlockLabel(String str) {blockLabel = str;}
	
	public String getType() {return type;}
	
	public long id() {return id;}
//TODO: Real error handling
	public void changeCollapseState() {
		if (collapsible = true) {
			collapsed = !collapsed;
		}
		else {
			System.out.println("Warning: This Expr is not collapsible. Called: changeCollapseState.");
		}
	}
	
	public void isCollapsible() {
		if (collapsible != true) {
			collapsed = false;
			collapsible = true;
		}
		else {
			System.out.println("Warning: This Expr has already been declared as collapsible. Called: isCollapsible().");
		}
	}
/*	
	public void hasOutput() {
		if (hasOutput != true) {
			hasOutput = true;
			output = new OutputNode(this);
		}
		else {
			System.out.println("Warning: This Expr has already been declared as having an output. Called: hasOutput().");
		}
	}
	
	public void hasInputs() {
		if (hasInputs != true) {
			hasInputs = true;
			inputs = new ArrayList<InputNode>();
		}
		else {
			System.out.println("Warning: This Expr has already been declared as having input(s). Called: hasInputs().");
		}
	}
*/	
	public void addInput(InputNode input) {
		if (hasInputs = true) {
			inputs.add(input);
		}
		else {
			System.out.println("Warning: This Expr has not been declared as having input(s). Called: addInput().");
		}
	}
	
	public boolean isCollapsed() {
		if(collapsible != false) {
			return collapsed;
		} else {
			System.out.println("Note: this expr is not collapsible. Called: isCollapsed().");
			return false;
		}
	}
	
	public void connect (Expr endExpr, int endNodeIndex) {
		InputNode endNode = endExpr.getInputs().get(endNodeIndex);
		endNode.appendArgs(this.getOutput());
		output.appendArgs(endNode);
	}
	
	public boolean hasOutputBool() {return hasOutput;}
	public boolean hasInputsBool() {return hasInputs;}
	
	public OutputNode getOutput() {return output;}
	public ArrayList<InputNode> getInputs() {return inputs;}
}
