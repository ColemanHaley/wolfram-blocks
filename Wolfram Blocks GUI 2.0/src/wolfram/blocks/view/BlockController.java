package wolfram.blocks.view;

import java.io.IOException;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import wolfram.blocks.MainApp;
import wolfram.blocks.model.Expr;
import wolfram.blocks.model.InputAttribute;


public class BlockController {
	
	@FXML
	private AnchorPane thisBlock;
	
	@FXML
	private VBox inputs;
	
	private Expr blockData;
	
	
	private ArrayList <Line> outputArrows;
	
	private ArrayList<ArrayList<Line>> inputArrowList;
	
	private OutputNodeView output;
	
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	
	public void setBlockData(Expr data) {
		blockData = data;
	}
	
	public void initializeBlock() {
		if(blockData.hasInputsBool()){
			inputPopulate();
		}
		if(blockData.hasOutputBool()){
			createOutput();
		}
	}
	
	private void inputPopulate() {
			double[] layout = new double[blockData.getInputs().size()];
			for(int i = 0; i<blockData.getInputs().size(); i++) {
				InputNode input = new InputNode();
				inputs.getChildren().addAll(input);
				layout[i] = ((i+1)*200)/(layout.length+1);
				
			}
			if(layout.length > 1) {
				
				double spacing = ((layout[1]-layout[0])-24.0);
				if(spacing > 0) {
					inputs.setSpacing((layout[1]-layout[0])-24.0);
				}
				//TODO Add resize for many inputs
			}
			AnchorPane.setTopAnchor(inputs, layout[0]-12.0);
			AnchorPane.setBottomAnchor(inputs, layout[0]-12.0);
		
		for(Node input : inputs.getChildren() ) {
			((InputNode)input).setXCenter(CenterCalc.inputCenterX(input));
			((InputNode)input).setYCenter(CenterCalc.inputCenterY(input));	
		}
		
	} 	
	
	private void refreshInOutCenter() {
		for(Node input : inputs.getChildren() ) {
			((InputNode)input).setXCenter(CenterCalc.inputCenterX(input));
			((InputNode)input).setYCenter(CenterCalc.inputCenterY(input));	
		}	
		if(output != null) {
			output.setXCenter(CenterCalc.outputCenterX(output));
			output.setYCenter(CenterCalc.outputCenterY(output));	
		}
	}
	
	private void createOutput() {
			output = new OutputNodeView();
			thisBlock.getChildren().addAll(output);
			//System.out.println(thisBlock.getChildren());
			AnchorPane.setLeftAnchor(output, 160.0);
			AnchorPane.setTopAnchor(output, (200/2)-25.0);
			output.setMainApp(mainApp);
	}
	
	public void createInput() {
	}


	
	@FXML
	private void handleMouse() {
		refreshInOutCenter();
	}

	
}
