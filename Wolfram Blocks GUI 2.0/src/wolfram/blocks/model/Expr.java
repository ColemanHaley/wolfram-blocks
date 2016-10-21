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
	private OutputNode output;
	private Function parent;
	
	//private boolean groupMember;
	//private boolean isGroupHead;
	private String blockLabel;
	private String type;
	
	//private HashMap<String, Object> attributes;
	
	public Expr(String type, Function parent) {
		//this.groupMember = false;
		//this.isGroupHead = false;
		this.blockLabel = type;
		this.type = type;
		this.parent = parent;
	}
	
	
	//public void setAttributes(HashMap<String, Object> attr) {
	//	
	//}
	
	public String getBlockLabel() {return blockLabel;}
	public Function getParent() {return parent;}
	public void setBlockLabel(String str) {blockLabel = str;}
	
	public String getType() {return type;}
	
	public long id() {return id;}

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
	
	public void hasOutput() {
		if (hasOutput != true) {
			hasOutput = true;
			output = new OutputNode();
			output.setID(this.id);
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
	
	public void addInput(InputNode input) {
		if (hasInputs = true) {
			input.setID(this.id);
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
