package wolfram.blocks.view;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import wolfram.blocks.util.ShapeCenterCalc;

public class CenterCalc {
	
	public static double outputCenterX(Node output) {
		double x = 0;
		Node block = output.getParent();
	
		x += block.getLayoutX();
		x += block.getTranslateX();
		
		x += AnchorPane.getLeftAnchor(output);
		return ShapeCenterCalc.outputShapeCenterX(x);
	}
	public static double outputCenterY(Node output) {
		double y = 0;
		Node block = output.getParent();
		
		y += AnchorPane.getTopAnchor(output);
		y += block.getLayoutY();
		y += block.getTranslateY();
		return ShapeCenterCalc.outputShapeCenterY(y);
	}
	
	public static double inputCenterX(Node input) {
		double x = 0;
		Node allInputs = input.getParent();
		Block block = ((SimpleInputNodeView) input).getBlock();
		
		//x += allInputs.getLayoutX();
		x += block.getTranslateX();
		x += block.getLayoutX();
		block.getInputs().size();
		((SimpleInputNodeView) input).getData().getID();
		//Add layoutX of circle inside input
		//x += 12;
		return ShapeCenterCalc.inputShapeCenterX(x);

		
	}
	
	public static double inputCenterY(Node input, double layout) {
		double y = 0;
		Node allInputs = input.getParent();
		Block block = ((SimpleInputNodeView) input).getBlock();
		
		//y += allInputs.getLayoutY();
		y += block.getTranslateY();
		y += block.getLayoutY();
		y += layout;
		//Add layoutX of circle inside input
		y -= 12;
		return ShapeCenterCalc.inputShapeCenterY(y);

		
	}
	
	
}

