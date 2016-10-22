package wolfram.blocks.view;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import wolfram.blocks.util.ShapeCenterCalc;

public class CenterCalc {
	
	public static double outputCenterX(Node output) {
		double x = 0;
		Node block = output.getParent().getParent();
	
		x += block.getLayoutX();
		x += block.getTranslateX();
		
		x += AnchorPane.getLeftAnchor(output);
		return ShapeCenterCalc.outputShapeCenterX(x);
	}
	public static double outputCenterY(Node output) {
		double y = 0;
		Node block = output.getParent().getParent();
		
		y += AnchorPane.getTopAnchor(output);
		y += block.getLayoutY();
		y += block.getTranslateY();
		return ShapeCenterCalc.outputShapeCenterY(y);
	}
	
	public static double inputCenterX(Node input) {
		double x = 0;
		Node allInputs = input.getParent();
		Node block = allInputs.getParent().getParent();
		
		x += allInputs.getLayoutX();
		x += block.getTranslateX();
		x += block.getLayoutX();
		//Add layoutX of circle inside input
		x += 12;
		return ShapeCenterCalc.inputShapeCenterX(x);
		
	}
	
	public static double inputCenterY(Node input) {
		double y = 0;
		Node allInputs = input.getParent();
		Node block = allInputs.getParent().getParent();
		
		y += allInputs.getLayoutY();
		y += block.getTranslateY();
		y += block.getLayoutX();
		//Add layoutX of circle inside input
		y += 12;
		return ShapeCenterCalc.inputShapeCenterY(y);
		
	}
	
	
}

