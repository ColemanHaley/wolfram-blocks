package wolfram.blocks.model;

import java.util.ArrayList;

public interface INode {

	void appendArgs(ArrayList<OutputNode> newArgs);
	
	ArrayList<OutputNode> getArgs();
	
	void appendArgs (OutputNode newArg);

	String getLabel();
	
}
