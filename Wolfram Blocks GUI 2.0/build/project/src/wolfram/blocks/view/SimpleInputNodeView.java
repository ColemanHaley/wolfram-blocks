package wolfram.blocks.view;


import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import wolfram.blocks.model.InputAttribute;
import wolfram.blocks.model.InputNode;
import wolfram.blocks.model.OutputNode;

public class SimpleInputNodeView extends AnchorPane implements InputNodeView {
	
	private final DoubleProperty xCenter = new SimpleDoubleProperty(this, "inputXCenter");
	private final DoubleProperty yCenter = new SimpleDoubleProperty(this, "outputYCenter");
	private InputNode data;
	private Circle inputNode;
	private Block parent;
	private boolean connected = false;
	
	public SimpleInputNodeView(InputNode thisInput, Block block) {
		super();
		
		this.parent = block;
		this.data = thisInput;
		ArrayList<InputAttribute> attributes = thisInput.getAttributes();
		
		draw();
		initialize();
	}
	
	private void initialize(){
		inputNode.addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, 
			event->{if(ConnectCommunicator.inConnectMode() &&connected==false){ 
						ConnectCommunicator.sendConnectSignal(this);connected=true;}}
		);
		inputNode.addEventHandler(MouseEvent.MOUSE_PRESSED,
				event->{ConnectCommunicator.setConnectMode(true);
						ConnectCommunicator.sendDisconnectSignal(this);
				}
		);
	}
	
	@Override
	public void setXCenter(double x) {xCenter.setValue(x);}
	
	@Override
	public void setYCenter(double y) {yCenter.setValue(y);}
	
	@Override
	public DoubleProperty getXCenter() {return xCenter;}

	@Override
	public DoubleProperty getYCenter() {return yCenter;}
	
	@Override
	public Block getBlock() {return parent;}

	@Override
	public void addConnectedTo(OutputNode originNode){data.appendArgs(originNode);}
	
	 //Here we are with the getters and setters again :(
	@Override
	public InputNode getData(){return this.data;}
	@Override
	public void disconnect(OutputNode origin){data.deleteArg(origin);connected=false;}
	
	private void draw(){
		this.setPrefSize(25.0, 24.0);
		this.setMaxWidth(124.0);
		inputNode = new Circle(12.0);
		inputNode.setLayoutX(12.0);
		inputNode.setLayoutY(13.0);
		inputNode.setFill(Color.web("#FA980D"));
		this.getChildren().add(inputNode);
	}

	public boolean isEndNode(){return connected;}
}
