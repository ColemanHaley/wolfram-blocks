package wolfram.blocks.view;

import wolfram.blocks.MainApp;
import wolfram.blocks.model.BlockFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;


public class RightPaneController {

	 private MainApp mainApp;
	 private final BooleanProperty connectorModeActiveProperty =
	            new SimpleBooleanProperty(this, "connectorModeActive", false);
	 
	 @FXML 
	 CheckBox chkConnect;
	 
	 public void setMainApp(MainApp mainApp) {
		 this.mainApp = mainApp;
	 }
	 
	@FXML
	private void handleNewString() {
		mainApp.createBlock();
		mainApp.getBlockController().setBlockData(BlockFactory.createBlockData("Style"));
		mainApp.getBlockController().initializeBlock();
		mainApp.addBlock((DraggableFactory.makeDraggable((Node)(mainApp.getBlock()), this)));
	}
	
	@FXML
	private void handleNewStyle() {
		mainApp.createBlock();
		mainApp.addBlock((DraggableFactory.makeDraggable((Node)(mainApp.getBlock()), this)));
	}
	
	@FXML
	private void handleCheckConnector() {
		if(chkConnect.isSelected()) {
			connectorModeActiveProperty.set(true) ;
		}
		else {
			connectorModeActiveProperty.set(false);
		}
	}
	
	public BooleanProperty inConnectMode() {
		return connectorModeActiveProperty;
	}
}
