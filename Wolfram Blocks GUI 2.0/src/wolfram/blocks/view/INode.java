package wolfram.blocks.view;

import javafx.beans.property.DoubleProperty;
import wolfram.blocks.model.InputNode;
import wolfram.blocks.model.OutputNode;

public interface INode {

	void setXCenter(double x);

	void setYCenter(double y);

	DoubleProperty getXCenter();

	DoubleProperty getYCenter();

	Block getBlock();

	void addConnectedTo(OutputNode originNode);

	//Here we are with the getters and setters again :(
	InputNode getData();

}