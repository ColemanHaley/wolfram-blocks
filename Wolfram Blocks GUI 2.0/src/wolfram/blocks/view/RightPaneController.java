package wolfram.blocks.view;

import wolfram.blocks.MainApp;
import wolfram.blocks.model.BlockFactory;
import wolfram.blocks.model.MultipleHeadException;
import wolfram.blocks.model.WolframModel;

import com.wolfram.jlink.KernelLink;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

//TODO: Make this class more coherent
public class RightPaneController {

	 private MainApp mainApp;
	 private SimpleInputNodeView currentEndnode = null;
	 private final BooleanProperty connectorModeActiveProperty =
	            new SimpleBooleanProperty(this, "connectorModeActive", false);
	 
	 private KernelLink ml = null; //TODO is this field necessary?
	 private WolframModel model = null;
	 
	 @FXML 
	 CheckBox chkConnect;
	 
	 public void setMainApp(MainApp mainApp) {
		 this.mainApp = mainApp;
	 }
	 //these guys are a big deal and super sketch
	 public void sendConnectSignal(SimpleInputNodeView endNode){
		 currentEndnode = endNode;
	 }
	 
	 public SimpleInputNodeView getConnectSignal(){
		 return currentEndnode;
	 }

	@FXML
	private void handleNewString() {
		Block newBlock = mainApp.createBlock();
		newBlock.setBlockData(BlockFactory.createBlockData("Style"));
		newBlock.initializeBlock();
		mainApp.addBlock((DraggableFactory.makeDraggable((Node)(newBlock))));
	}
	
	@FXML
	private void handleNewStyle() {
		Block newBlock = mainApp.createBlock();
		mainApp.addBlock((DraggableFactory.makeDraggable((Node)(newBlock))));
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
	
	@FXML
	private void handleRun(){
		try {
			model.buildModel(mainApp.getBlockArea());
		} catch (MultipleHeadException e) {
			// TODO Replace with real error
			System.out.println("Error: too many head exprs");
		}
	}
	
	public void setConnectMode(boolean val){
		connectorModeActiveProperty.set(val);
	}
	
	public boolean inConnectMode() {
		return connectorModeActiveProperty.getValue();
	}
	
	public void setMathLink(KernelLink ml){this.ml = ml; model = new WolframModel(ml);}
}
