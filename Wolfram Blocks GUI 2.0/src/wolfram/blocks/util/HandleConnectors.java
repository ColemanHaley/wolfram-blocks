package wolfram.blocks.util;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import wolfram.blocks.view.ConnectCommunicator;
import wolfram.blocks.view.Connector;
import wolfram.blocks.view.SimpleInputNodeView;
import wolfram.blocks.view.RightPaneController;

public class HandleConnectors {

	private static void setUnboundEndpoint(AnchorPane area, AnchorPane connectorLayer){
		area.addEventHandler(MouseEvent.DRAG_DETECTED, 
                new EventHandler<MouseEvent>() {
                    public void handle(final MouseEvent mouseEvent) { 
                    	area.startFullDrag();
                    	for( Node n : connectorLayer.getChildren()){
                    		if(n instanceof Connector && ConnectCommunicator.inConnectMode() && !(((Connector) n).isLocked())){
                    			((Connector) n).setEndPointX(mouseEvent.getX());
	                    		((Connector) n).setEndPointY(mouseEvent.getY());
                    		}
                    	}
                    };
                });
		area.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                new EventHandler<MouseEvent>() {
                    public void handle(final MouseEvent mouseEvent) { 
                    	for( Node n : connectorLayer.getChildren()){
                    		if(n instanceof Connector){
                    			if(ConnectCommunicator.inConnectMode()){
	                    			if(!(((Connector) n).isLocked())){
	                    				((Connector) n).setEndPointX(mouseEvent.getX());
	                    				((Connector) n).setEndPointY(mouseEvent.getY());
	                    			}
                    			}
                    		}
                    	}
                    };
                });
	}
	
	private static void exitConnectorMode(AnchorPane area, AnchorPane connectorLayer){
		area.addEventHandler(MouseEvent.MOUSE_RELEASED, 
                new EventHandler<MouseEvent>() {
                    public void handle(final MouseEvent mouseEvent) { 
                    	
                    	ConnectCommunicator.setConnectMode(false);
                    	SimpleInputNodeView endNode = ConnectCommunicator.getConnectSignal();
                    	if(endNode == null){
                    		ArrayList<Node> toRemove = new ArrayList<Node>();
                    		for( Node n : connectorLayer.getChildren()){
                    			if(n instanceof Connector){
                    				if(!(((Connector) n).isLocked())){
		                    				toRemove.add(n);
		                    			}
		                    			
	                    		}	
	                    	}
	                    	for(Node n : toRemove){
	                    		connectorLayer.getChildren().remove(n);
	                    	}
                    	} else {

                    		for( Node n : connectorLayer.getChildren()){
                    			if(n instanceof Connector){
                    				if(!(((Connector) n).isLocked())){
                    					((Connector) n).setEndNode(endNode);
		                    		}		
	                    		}	
	                    	}
                    	}
                    	ConnectCommunicator.sendConnectSignal(null);
                    };
                });
	}	
	
	public static void handleUnboundConnectors(AnchorPane area, AnchorPane connectorLayer){
		setUnboundEndpoint(area, connectorLayer);
		exitConnectorMode(area, connectorLayer);
	}
}
