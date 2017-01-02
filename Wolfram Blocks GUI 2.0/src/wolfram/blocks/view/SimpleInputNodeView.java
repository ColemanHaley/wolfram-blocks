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

public class SimpleInputNodeView extends AnchorPane implements INode {
	
	private final DoubleProperty xCenter = new SimpleDoubleProperty(this, "inputXCenter");
	private final DoubleProperty yCenter = new SimpleDoubleProperty(this, "outputYCenter");
	private RightPaneController rPC;
	private InputNode data;
	private Circle inputNode;
	private Block parent;
	private boolean inputFieldCreated = false;
	
	public SimpleInputNodeView(RightPaneController rPC, InputNode thisInput, Block block) {
		super();
		this.setPrefSize(25.0, 24.0);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		this.setMaxWidth(124.0);
		this.parent = block;
		this.data = thisInput;
		ArrayList<InputAttribute> attributes = thisInput.getAttributes();
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
					rPC.sendConnectSignal(SimpleInputNodeView.this);
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
	
	/* (non-Javadoc)
	 * @see wolfram.blocks.view.InputNode#setXCenter(double)
	 */
	@Override
	public void setXCenter(double x) {xCenter.setValue(x);}
	/* (non-Javadoc)
	 * @see wolfram.blocks.view.InputNode#setYCenter(double)
	 */
	@Override
	public void setYCenter(double y) {yCenter.setValue(y);}
	
	/* (non-Javadoc)
	 * @see wolfram.blocks.view.InputNode#getXCenter()
	 */
	@Override
	public DoubleProperty getXCenter() {return xCenter;}
	/* (non-Javadoc)
	 * @see wolfram.blocks.view.InputNode#getYCenter()
	 */
	@Override
	public DoubleProperty getYCenter() {return yCenter;}
	
	/* (non-Javadoc)
	 * @see wolfram.blocks.view.InputNode#getBlock()
	 */
	@Override
	public Block getBlock() {return parent;}
	private void createInputField() {
		this.getChildren().addAll(new TextField());
		inputFieldCreated = true;
	}
	/* (non-Javadoc)
	 * @see wolfram.blocks.view.InputNode#addConnectedTo(wolfram.blocks.model.OutputNode)
	 */
	@Override
	public void addConnectedTo(OutputNode originNode){
		data.appendArgs(originNode);
	}
	 //Here we are with the getters and setters again :(
	 /* (non-Javadoc)
	 * @see wolfram.blocks.view.InputNode#getData()
	 */
	@Override
	public InputNode getData(){
		 return this.data;
	 }
}
