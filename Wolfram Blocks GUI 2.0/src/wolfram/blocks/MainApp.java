package wolfram.blocks;

import java.io.IOException;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

import wolfram.blocks.view.Block;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import wolfram.blocks.model.BlockFactory;
import wolfram.blocks.util.HandleConnectors;
import wolfram.blocks.view.Connector;
import wolfram.blocks.view.InputNodeView;
import wolfram.blocks.view.OutputNodeView;
import wolfram.blocks.view.RightPaneController;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

//TODO: Add arrowheads
public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private double numBlocks = 0; //Total number of blocks currently existing
	private AnchorPane testBlock;
	private RightPaneController rightPaneController;
	private KernelLink ml = null;
	
    public static void main(String[] args) 
    {
        launch(args);
    }
 
    @Override
    public void start(Stage mainStage) 
    {
    	this.primaryStage = mainStage;
        primaryStage.setTitle("Wolfram Blocks"); //TODO: Come up with better title
        
        try {
			ml = MathLinkFactory.createKernelLink("-linkmode launch -linkname \"C:\\\\Program Files\\\\Wolfram Research\\\\Mathematica\\\\10.4\\\\mathkernel.exe\"");
		} catch (MathLinkException e) {
			System.out.println("MathLinkException occurred: " + e.getMessage());
		}
        initRootLayout(); //Make the window
        showRightPane(); //Make the menu
        showBlocksandConnectors();
    }
    
    public void initRootLayout() {
		try {
			
			FXMLLoader loader = new FXMLLoader(); 
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	private AnchorPane showBlockArea() {
		AnchorPane blockArea =  null;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/BlockAnchorPane.fxml"));
			blockArea = (AnchorPane) loader.load();
			return (AnchorPane) blockArea;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return blockArea;
	}
    
	private AnchorPane showConnectorLayer() {
		AnchorPane conLayer = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/BlockAnchorPane.fxml"));
			conLayer = (AnchorPane) loader.load();
			conLayer.setMouseTransparent(true); //This is sketchy hacky
			//Background color = new Background(new BackgroundFill(Color.DARKMAGENTA, CornerRadii.EMPTY, Insets.EMPTY));
			//conLayer.setBackground(color);
			return conLayer;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conLayer;
	}
	
	public void showBlocksandConnectors(){
		AnchorPane blockConnectors = new AnchorPane();
		AnchorPane blockArea = showBlockArea();
		AnchorPane conLayer = showConnectorLayer();
		
		blockConnectors.getChildren().addAll(blockArea, conLayer);
		HandleConnectors.handleUnboundConnectors(blockArea, conLayer, rightPaneController); //sets any unbound end point to the current mouse location
		conLayer.toFront();	
		conLayer.prefWidthProperty().bind(blockConnectors.widthProperty());
		conLayer.prefHeightProperty().bind(blockConnectors.heightProperty());
		blockArea.prefWidthProperty().bind(blockConnectors.widthProperty());
		blockArea.prefHeightProperty().bind(blockConnectors.heightProperty());
		rootLayout.setCenter(blockConnectors);
		
	}
	
	public Block createBlock() {
			Block block = new Block();
			AnchorPane blockArea = (AnchorPane) ((AnchorPane)rootLayout.getCenter()).getChildren().get(0);
			
			blockArea.getChildren().addAll(block);
			//blockArea.getChildren().addAll(new Block());
			
			block.relocate(100+numBlocks*2, 50+numBlocks*2); //TODO: Replace this with logical placement
			AnchorPane.setTopAnchor(block, numBlocks*100);
			numBlocks++;
			testBlock = block;
			block.setMainApp(this);
			return block;
	}
	
	public void showRightPane() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RightPane.fxml"));
			AnchorPane rightPane = (AnchorPane) loader.load();
			
			rootLayout.setRight(rightPane);
			rightPaneController = loader.getController();
			rightPaneController.setMainApp(this);
			rightPaneController.setMathLink(ml);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public double getNumBlocks() {
		return numBlocks;
	}
	
	public BorderPane getRoot() {
		return rootLayout;
	}
	

	
	public RightPaneController getRPController(){
		return rightPaneController;
	}
	
	public void addBlock(Node block) {
		//TODO: add safety
		AnchorPane blockArea = ((AnchorPane) ((AnchorPane) rootLayout.getCenter()).getChildren().get(0));
		blockArea.getChildren().addAll(block);
	}
	
	public void addConnector(Node connector){
		//TODO add safety
		AnchorPane conLayer = ((AnchorPane) ((AnchorPane) rootLayout.getCenter()).getChildren().get(1));
		conLayer.getChildren().addAll(connector);
	}
	@Override
	public void stop(){
		ml.close();
		System.out.println("Works.");
	}
	
	public AnchorPane getBlockArea(){return ((AnchorPane) ((AnchorPane) rootLayout.getCenter()).getChildren().get(0));}
}
