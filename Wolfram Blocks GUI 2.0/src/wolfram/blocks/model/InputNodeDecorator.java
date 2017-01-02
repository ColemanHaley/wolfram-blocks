package wolfram.blocks.model;

import java.util.ArrayList;

public abstract class InputNodeDecorator implements INode {

	protected final INode iNodeToBeDecorated; //InputNode being decorated
	
	public InputNodeDecorator(INode i){
		this.iNodeToBeDecorated = i;
	}
	
	//Delegate the behavior that's the same
	@Override
	public void appendArgs(ArrayList<OutputNode> newArgs) {
		iNodeToBeDecorated.appendArgs(newArgs);
	}

	@Override
	public ArrayList<OutputNode> getArgs() {
		return iNodeToBeDecorated.getArgs();
	}

	@Override
	public void appendArgs(OutputNode newArg) {
		iNodeToBeDecorated.appendArgs(newArg);
	}

	@Override
	public String getLabel() {
		return iNodeToBeDecorated.getLabel();
	}

}
