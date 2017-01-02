package wolfram.blocks.model;

import java.util.ArrayList;

public class InputNode {
	private ArrayList<OutputNode> args;
	
	private String label;
	private ArrayList<InputAttribute> attributes;
	
	public InputNode () {
		this("",new ArrayList<OutputNode>(),new ArrayList<InputAttribute>());
	}
	
	
	
	//args functions
	public InputNode(String label, ArrayList<OutputNode> initArgs, ArrayList<InputAttribute> attributes) {
		this.args = initArgs;
		this.label = label;
		this.attributes = attributes;
	}
	
	public ArrayList<InputAttribute> getAttributes() {return attributes;}
	
	public ArrayList<OutputNode> getArgs () {
		return args;
	}
	public void appendArgs (OutputNode newArg) {
		args.add(newArg);
	}
	public void appendArgs (ArrayList<OutputNode> newArgs) {
		args.addAll(newArgs);
	}
	public ArrayList<OutputNode> returnArgs () {
		return args;
	}
	public String getLabel(){
		return label;
	}
	
	
}