package wolfram.blocks.model;

import java.util.ArrayList;

public class OutputNode implements ONode {
	public ArrayList<InputNode> args;
	public Expr parent;
	public OutputNode (Expr parent) {
		this(new ArrayList<InputNode>(), parent);
	}
	public OutputNode (ArrayList<InputNode> initArgs, Expr parent) {
		args = initArgs;
		this.parent = parent;
	}
	
	public ArrayList<InputNode> getArgs () {
		return args;
	}

	
	public void appendArgs (InputNode newArg) {
		args.add(newArg);
	}
	
	
	public void appendArgs (ArrayList<InputNode> newArgs) {
		args.addAll(newArgs);
	}

	
	public Expr getParent() {return parent;}

	
}
