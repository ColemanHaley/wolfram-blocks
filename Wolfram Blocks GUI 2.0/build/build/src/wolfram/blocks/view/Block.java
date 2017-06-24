package wolfram.blocks.view;

import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import wolfram.blocks.MainApp;
import wolfram.blocks.model.Expr;
import wolfram.blocks.model.InputAttribute;
import wolfram.blocks.model.InputNode;
import wolfram.blocks.model.OutputNode;

public class Block extends AnchorPane {
	
	private VBox inputs = new VBox();
	
	private Expr blockData;
	
	private Rectangle blockBody = new Rectangle();
	
	private ArrayList <Line> outputArrows; //Can we change these into something like std::tuple??
	
	private ArrayList<ArrayList<Line>> inputArrowList;
	
	private OutputNodeView output;
	
	private Text blockLabel;
	
	private MainApp mainApp;
	
	private double[] layout;

	public Block(){
		super();
		((AnchorPane)this).setPrefHeight(200);
		((AnchorPane)this).setPrefWidth(160);
		blockBody.setHeight(200);
		blockBody.setWidth(150);
		//blockBody.setArcHeight(0.0);
		//blockBody.setArcWidth(0.0);
		blockBody.setFill(Color.web("#8997A3"));
		inputs.setLayoutX(2.0);
		inputs.setLayoutY(3.0);
		inputs.setMaxHeight(200.0);
		inputs.setMinHeight(24.0);
		inputs.setMinWidth(24.0);
		
		this.getChildren().addAll(blockBody, inputs);
		
		AnchorPane.setTopAnchor(blockBody, 0.0);
		AnchorPane.setBottomAnchor(blockBody, 0.0);
		AnchorPane.setLeftAnchor(blockBody, 12.0);
		this.addEventFilter(MouseEvent.MOUSE_DRAGGED, 
                event -> refreshInOutCenter() //WHATTTT LAMBDA EXPRESSIONS FTW
        );
	}
	
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
		refreshInOutCenter();
		setLabel();
	}
	
	private void inputPopulate() {
			
		InputNodeFactory inFactory = new InputNodeFactory();
			layout = new double[blockData.getInputs().size()];
			for(int i = 0; i<blockData.getInputs().size(); i++) {
				InputNodeView input = new SimpleInputNodeView(blockData.getInputs().get(i), this);
				inputs.getChildren().add((Node) input);
				inFactory.decorateInputNode(input);
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
		
		for(int i = 0; i < inputs.getChildren().size(); i++ ) {
			SimpleInputNodeView input = (SimpleInputNodeView) inputs.getChildren().get(i);
			input.setXCenter(CenterCalc.inputCenterX(input));
			input.setYCenter(CenterCalc.inputCenterY(input, layout[i]));	
		}
		
	} 	
	
	private void refreshInOutCenter() {
		for(int i = 0; i < inputs.getChildren().size(); i++ ) {
			SimpleInputNodeView input = (SimpleInputNodeView) inputs.getChildren().get(i);
			input.setXCenter(CenterCalc.inputCenterX(input));
			input.setYCenter(CenterCalc.inputCenterY(input, layout[i]));	
		}
		if(output != null) {
			output.setXCenter(CenterCalc.outputCenterX(output));
			output.setYCenter(CenterCalc.outputCenterY(output));	
		}
	}
	
	private void createOutput() {
			output = new OutputNodeView(blockData.getOutput(), this);
			this.getChildren().addAll(output);
			AnchorPane.setLeftAnchor(output, 160.0);
			AnchorPane.setTopAnchor(output, (200/2)-25.0);
			output.setMainApp(mainApp);
	}
	
	private void setLabel(){
		blockLabel = new Text(blockData.getBlockLabel());
		blockLabel.setFill(Color.WHITE);
		blockLabel.setFont(new Font(18));
		blockLabel.setId("label");
		this.getChildren().add(blockLabel);
		AnchorPane.setTopAnchor(blockLabel, 5.0);
		AnchorPane.setLeftAnchor(blockLabel, 22.0);
	}
	
	//TODO: burn w/ fire
	public Expr getData(){return blockData;}

	public ArrayList<InputNode> getOutArgs() {OutputNode out = blockData.getOutput(); return out != null ? out.getArgs() : new ArrayList<InputNode>();}
	public ArrayList<InputNode> getInputs() {return blockData.getInputs();}
	public void createInput() {
	}
	public String getName(){
		return blockData.getType();
	}
	
}

