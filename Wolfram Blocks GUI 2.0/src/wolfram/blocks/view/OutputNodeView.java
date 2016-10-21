package wolfram.blocks.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import wolfram.blocks.MainApp;

public class OutputNodeView extends Group {
	
	private MainApp mainApp;
	private boolean mouseClicked;
//	private OutputNodeView prayer;
	
	private final DoubleProperty xCenter = new SimpleDoubleProperty(this, "outputXCenter");
	private final DoubleProperty yCenter = new SimpleDoubleProperty(this, "outputYCenter");
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public OutputNodeView() {
		super(new Polygon(
				0.0,  25.0,
				0.0, -25.0,
				0.0,  40.0));
		((Polygon)this.getChildren().get(0)).setFill(Color.DODGERBLUE);
//		makefakethis();
	}
	
	public boolean isMouseClicked() {
		return mouseClicked;
	}
	
	public void initialize() {
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(final MouseEvent mouseEvent) {
				mouseClicked = true;
				//mainApp.addBlock(new Connector(prayer));
			}
		});
	}
	
//	public void makefakethis() {
//		this.prayer = this;
//	}
//	
	public DoubleProperty getXCenter() {return xCenter;}
	public DoubleProperty getYCenter() {return yCenter;}
	
	public void setXCenter(double x) {xCenter.setValue(x);}
	public void setYCenter(double y) {yCenter.setValue(y);}
	
	public String toString() {
		return this.getChildren().get(0).toString();
	}

}
