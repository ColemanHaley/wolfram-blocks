package wolfram.blocks.view;


import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import wolfram.blocks.model.InputAttribute;

public class InputNode extends AnchorPane {
	
	private final DoubleProperty xCenter = new SimpleDoubleProperty(this, "inputXCenter");
	private final DoubleProperty yCenter = new SimpleDoubleProperty(this, "outputYCenter");
	private RightPaneController rPC;
	private Circle inputNode;
	private boolean inputFieldCreated = false;
	
	public InputNode(RightPaneController rPC, ArrayList<InputAttribute> attributes) {
		super();
		this.setPrefSize(25.0, 24.0);
		this.setMaxWidth(124.0);
		inputNode = new Circle(12.0);
		inputNode.setLayoutX(12.0);
		inputNode.setLayoutY(13.0);
		inputNode.setFill(Color.DODGERBLUE);
		this.getChildren().add(inputNode);
		initialize();
		makeInputAttributes(attributes);
		this.rPC = rPC;
	}
	private void initialize(){
		inputNode.addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, new EventHandler<MouseEvent>() {
			public void handle(final MouseEvent mouseEvent) {
				
				if(rPC.inConnectMode()){
					rPC.sendConnectSignal(InputNode.this);
				}
			}
		});
	}
	private void makeInputAttributes(ArrayList<InputAttribute> attributes) {
		for(InputAttribute attr : attributes) {
			switch (attr)
			{
				case INPUTFIELD:
					//createInputField();
					break;
				case LONGINPUTFIELD:
					if(inputFieldCreated = false) {
						//createInputField();
					}
					//TODO Less sketch way to get input field
					//((TextField)this.getChildren().get(1)).setPrefWidth(100.0);
				default:
					break;
				
			}
		}
	}
	
	public void setXCenter(double x) {xCenter.setValue(x);}
	public void setYCenter(double y) {yCenter.setValue(y);}
	
	public DoubleProperty getXCenter() {return xCenter;}
	public DoubleProperty getYCenter() {return yCenter;}
	
	
	private void createInputField() {
		this.getChildren().addAll(new TextField());
		inputFieldCreated = true;
	}
}
