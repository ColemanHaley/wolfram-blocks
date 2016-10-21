package wolfram.blocks;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import wolfram.blocks.model.BlockFactory;
import wolfram.blocks.view.BlockController;
import wolfram.blocks.view.RightPaneController;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private double numBlocks = 0;
	private AnchorPane testBlock;
	private BlockController newController;
	
    public static void main(String[] args) 
    {
        launch(args);
    }
 
    @Override
    public void start(Stage mainStage) 
    {
    	this.primaryStage = mainStage;
        primaryStage.setTitle("Wolfram Blocks");
        
        initRootLayout();
        showRightPane();
        showBlockArea();
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
    
	public void showBlockArea() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/BlockAnchorPane.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			rootLayout.setCenter(personOverview);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	public void createBlock() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Block.fxml"));
			AnchorPane block = (AnchorPane) loader.load();
			AnchorPane blockArea = ((AnchorPane)rootLayout.getCenter());
			
			
			blockArea.getChildren().addAll(block);
			block.relocate(100+numBlocks*2, 50+numBlocks*2);
			AnchorPane.setTopAnchor(block, numBlocks*100);
			numBlocks++;
			testBlock = block;
			newController = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showRightPane() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RightPane.fxml"));
			AnchorPane rightPane = (AnchorPane) loader.load();
			
			rootLayout.setRight(rightPane);
			RightPaneController controller = loader.getController();
			controller.setMainApp(this);
			
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
	
	public AnchorPane getBlock() {
		return testBlock;
	}
	
	public BlockController getBlockController(){
		return newController;
	}
	
	public void addBlock(Node block) {
		((AnchorPane)rootLayout.getCenter()).getChildren().addAll(block);
	}
}
