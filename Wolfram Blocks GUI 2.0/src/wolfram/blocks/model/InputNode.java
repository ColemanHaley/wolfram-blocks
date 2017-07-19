package wolfram.blocks.model;

import java.util.ArrayList;

public class InputNode implements INode {
	private ArrayList<OutputNode> args;
	
	private String label;
	private ArrayList<InputAttribute> attributes;
	private int id;
	
	public InputNode () {
		this("",new ArrayList<OutputNode>(),new ArrayList<InputAttribute>(), 1);
	}
	
	public InputNode (int id) {
		this("",new ArrayList<OutputNode>(),new ArrayList<InputAttribute>(), id);
	}
	public InputNode(String label, ArrayList<InputAttribute> attributes, int id) {
		this(label, new ArrayList<OutputNode>(), attributes, id);
	}
	
	//args functions
	public InputNode(String label, ArrayList<OutputNode> initArgs, ArrayList<InputAttribute> attributes, int id) {
		this.args = initArgs;
		this.label = label;
		this.attributes = attributes;
		this.id = id;
	}
	
	public int getID(){return id;}
	
	public ArrayList<InputAttribute> getAttributes() {return attributes;}
	
	@Override 
	public ArrayList<OutputNode> getArgs () {
		return args;
	}

	@Override
	public void appendArgs (OutputNode newArg) {
		args.add(newArg);
	}

	@Override
	public void appendArgs (ArrayList<OutputNode> newArgs) {
		args.addAll(newArgs);
	}

	public void deleteArg(OutputNode toDelete){args.remove(toDelete);}
	
	@Override
	public String getLabel(){
		return label;
	}
	
	
}