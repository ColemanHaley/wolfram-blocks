package wolfram.blocks.view;

import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import wolfram.blocks.model.InputNode;
import wolfram.blocks.model.OutputNode;

public abstract class InputNodeDecorator extends Node implements InputNodeView {
	
	protected InputNodeView inToBeDecorated;
	
	public InputNodeDecorator(InputNodeView i){
		this.inToBeDecorated = i;
	}
	
	//Delegate implemented methods
	@Override
	public void setXCenter(double x) {
		inToBeDecorated.setXCenter(x);
	}

	@Override
	public void setYCenter(double y) {
		inToBeDecorated.setYCenter(y);
	}

	@Override
	public DoubleProperty getXCenter() {
		return inToBeDecorated.getXCenter();
	}

	@Override
	public DoubleProperty getYCenter() {
		return inToBeDecorated.getYCenter();
	}

	@Override
	public Block getBlock() {
		return inToBeDecorated.getBlock();
	}

	@Override
	public void addConnectedTo(OutputNode originNode) {
		inToBeDecorated.addConnectedTo(originNode);
	}

	@Override
	public InputNode getData() {
		return inToBeDecorated.getData();
	}
	
	@Override
	public ObservableList<Node> getChildren(){
		return inToBeDecorated.getChildren();
	}
	
	@Override
	public void disconnect(OutputNode origin){
		inToBeDecorated.disconnect(origin);
	}

}
