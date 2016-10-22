package wolfram.blocks.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class Connector extends Line {
	
	DoubleProperty endPointX = new SimpleDoubleProperty(this, "mousePosX");
	DoubleProperty endPointY = new SimpleDoubleProperty(this, "mousePosY");
	boolean locked = false;
	
	
	public Connector(OutputNodeView origin) {
		super();
		initialize(origin);
	}
	
	private void initialize(OutputNodeView origin) {
		this.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			public void handle(final MouseEvent mouseEvent) {
				if(locked==false){
					endPointX.set(mouseEvent.getX());
					endXProperty().bind(endPointX);
					endPointY.set(mouseEvent.getY());
					endYProperty().bind(endPointY);
				}
			}
		});
		System.out.println(origin.getXCenter());
		startXProperty().bind(origin.getXCenter());
		startYProperty().bind(origin.getYCenter());
	}
	
	private void destroy(){
		
	}
}
