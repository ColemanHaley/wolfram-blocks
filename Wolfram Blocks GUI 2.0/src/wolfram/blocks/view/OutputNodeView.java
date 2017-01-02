package wolfram.blocks.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import wolfram.blocks.MainApp;
import wolfram.blocks.model.InputNode;
import wolfram.blocks.model.OutputNode;

public class OutputNodeView extends Group {
	
	private MainApp mainApp;
	private boolean mouseClicked;
	private OutputNodeView prayer;
	private OutputNode data;
	private Block parent;
	
	private final DoubleProperty xCenter = new SimpleDoubleProperty(this, "outputXCenter");
	private final DoubleProperty yCenter = new SimpleDoubleProperty(this, "outputYCenter");
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public OutputNodeView(OutputNode thisOut, Block parent) {
		super();
		this.data = thisOut;
		this.parent = parent;
		Polygon outputNode = new Polygon();
        outputNode.getPoints().addAll(new Double[]{
        		  0.0,  -25.0,
				 40.0,    0.0,
				  0.0,   25.0});
		outputNode.setLayoutX(12.0);
		outputNode.setLayoutY(13.0);
		outputNode.setFill(Color.DODGERBLUE);
		initialize();
		this.getChildren().add(outputNode);
		makefakethis();
	}
	
	public boolean isMouseClicked() {
		return mouseClicked;
	}
	
	private void initialize() {
		this.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			public void handle(final MouseEvent mouseEvent) {
					mouseClicked = true;
					Connector newConnector = new Connector(prayer); //can't i just say "this"???
					mainApp.addConnector(newConnector);
					newConnector.setEndPointX(mouseEvent.getSceneX() + 10);
					newConnector.setEndPointY(mouseEvent.getSceneY()- 40);
					mainApp.getRPController().setConnectMode(true);	
			}		
		});
	}
	
	public void addConnectedTo(InputNode endNode){
		data.appendArgs(endNode);
	}
	
	public void makefakethis() {
		this.prayer = this;
	}
	
	public DoubleProperty getXCenter() {return xCenter;}
	public DoubleProperty getYCenter() {return yCenter;}
	
	public void setXCenter(double x) {xCenter.setValue(x);}
	public void setYCenter(double y) {yCenter.setValue(y);}
	
	public String toString() {
		return this.getChildren().get(0).toString();
	}
	
	public OutputNode getData() {
		return this.data;
	}

}
