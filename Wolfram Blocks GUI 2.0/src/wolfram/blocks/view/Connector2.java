package wolfram.blocks.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class Connector2 extends Group {
	
	DoubleProperty endPointX = new SimpleDoubleProperty(this, "mousePosX");
	DoubleProperty endPointY = new SimpleDoubleProperty(this, "mousePosY");
	boolean locked = false;
	OutputNodeView origin;
	Line line;
	SimpleInputNodeView endNode;
	
	public Connector2(OutputNodeView origin) {
		super();
		initialize(origin);
	}
	
	private void initialize(OutputNodeView origin) {
		this.origin = origin;
		this.line.setStrokeWidth(1.5);
		line.endXProperty().bind(endPointX);
		line.endYProperty().bind(endPointY);
//		this.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
//			public void handle(final MouseEvent mouseEvent) {
//				System.out.println("test");
//				if(locked==false){
//					System.out.println(mouseEvent.getX());
//					endPointX.set(mouseEvent.getX());
//					
//					endPointY.set(mouseEvent.getY());
//					
//				}
//			}
//		});
		line.startXProperty().bind(origin.getXCenter());
		line.startYProperty().bind(origin.getYCenter());
		this.getChildren().add(line);
	}
	
	public void setEndPointX(double x){
		endPointX.set(x);
	}
	
	public void setEndPointY(double y){
		endPointY.set(y);
	}
	
	public boolean isLocked(){
		return locked;
	}
	
	public void setEndNode(SimpleInputNodeView endNode){
		if(!this.locked){
			//TODO prevent backhooking (check if same block-parent)
			this.endNode = endNode;
			Block endNodeBlock = endNode.getBlock();
			this.locked = true;
			origin.addConnectedTo(endNode.getData());
			endNode.addConnectedTo(origin.getData());
			endPointX = endNode.getXCenter();
			endPointX.add(endNodeBlock.getLayoutX() + endNodeBlock.getTranslateX());
			endPointY = endNode.getYCenter();
			endPointY.add(endNodeBlock.getLayoutY() + endNodeBlock.getTranslateY());
			line.endXProperty().bind(endPointX);
			line.endYProperty().bind(endPointY);
		}
	}
	
}
