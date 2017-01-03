package wolfram.blocks.view;

import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import wolfram.blocks.model.InputNode;
import wolfram.blocks.model.OutputNode;

public interface InputNodeView  {

	void setXCenter(double x);

	void setYCenter(double y);

	DoubleProperty getXCenter();

	DoubleProperty getYCenter();

	Block getBlock();
	
	ObservableList<Node> getChildren();
	void addConnectedTo(OutputNode originNode);
	public void disconnect(OutputNode origin);

	//Here we are with the getters and setters again :(
	InputNode getData();

}