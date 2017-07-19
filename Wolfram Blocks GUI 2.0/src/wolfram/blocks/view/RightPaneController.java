package wolfram.blocks.view;

import wolfram.blocks.MainApp;
import wolfram.blocks.model.Expr;
import wolfram.blocks.model.Function;
import wolfram.blocks.model.InputAttribute;
import wolfram.blocks.model.MultipleHeadException;
import wolfram.blocks.model.WolframModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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
	 private Map<Integer, InputAttribute> inputattr = new HashMap<>();
	 private Map<String, Function> functions = new HashMap<>();
	 private Connection fnConn;
	 
	 public RightPaneController() {
		 try {
			this.fnConn = DriverManager.
						getConnection("jdbc:h2:~/function;INIT=create schema if not exists function\\;" 
								+ "SET SCHEMA function;", "sa", "");
			Statement stmt = fnConn.createStatement();
			ResultSet inAttr = stmt.executeQuery("SELECT * FROM INPUT_ATTRIBUTES");
			while (inAttr.next()) {
				int key = inAttr.getInt("ID");
				InputAttribute value = new InputAttribute(inAttr.getString("NAME"));
				inputattr.put(key, value);
			}
			Statement stmt2 = fnConn.createStatement();
			ResultSet fns = stmt.executeQuery("SELECT * FROM FUNCTIONS");
			while (fns.next()) {
				String key = fns.getString("NAME");
				Function value =  new Function(key, fnConn, inputattr);
				functions.put(key, value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
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
		newBlock.setBlockData(new Expr(functions.get("String")));
		newBlock.initializeBlock();
		mainApp.addBlock((DraggableFactory.makeDraggable((Node)(newBlock))));
	}
	
	@FXML
	private void handleNewStyle() {
		Block newBlock = mainApp.createBlock();
		newBlock.setBlockData(new Expr(functions.get("Style")));
		newBlock.initializeBlock();
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
