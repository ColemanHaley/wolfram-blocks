package wolfram.blocks.model;

import java.util.ArrayList;

public class OutputNode {
	public ArrayList<InputNode> args;
	public long id;
	public OutputNode () {
		this(new ArrayList<InputNode>());
	}
	public OutputNode (ArrayList<InputNode> initArgs) {
		args = initArgs;
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
	public static boolean regionFunction () {
		//TODO Add real code;
		//TODO See if all are same shape and size;
		return false;
	}
	public void setID (long newID) {
		id = newID;
	}
}
