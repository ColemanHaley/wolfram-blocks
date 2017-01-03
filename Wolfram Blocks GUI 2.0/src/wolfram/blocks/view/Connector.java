package wolfram.blocks.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class Connector extends Line {
	
	DoubleProperty endPointX = new SimpleDoubleProperty(this, "mousePosX");
	DoubleProperty endPointY = new SimpleDoubleProperty(this, "mousePosY");
	boolean locked = false;
	OutputNodeView origin;
	SimpleInputNodeView endNode;
	
	public Connector(OutputNodeView origin) {
		super();
		initialize(origin);
	}
	
	private void initialize(OutputNodeView origin) {
		this.origin = origin;
		this.setStrokeWidth(1.5);
		endXProperty().bind(endPointX);
		endYProperty().bind(endPointY);
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
		startXProperty().bind(origin.getXCenter());
		startYProperty().bind(origin.getYCenter());
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
	
	public OutputNodeView getOrigin(){return origin;}
	
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
			endXProperty().bind(endPointX);
			endYProperty().bind(endPointY);
		}
	}
	
	public void detatch(SimpleInputNodeView endNode){
		locked = false;
		origin.disconnect(endNode.getData());
		endNode.disconnect(origin.getData());
	}
	
	
}
