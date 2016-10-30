package wolfram.blocks.util;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import wolfram.blocks.view.Connector;

public class HandleConnectors {

	public static void setUnboundEndpoint(AnchorPane area, AnchorPane connectorLayer){
		area.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                new EventHandler<MouseEvent>() {
                    public void handle(final MouseEvent mouseEvent) { 
                    	for( Node n : connectorLayer.getChildren()){
                    		if(n instanceof Connector){
                    			if(!(((Connector) n).isLocked())){
                    				((Connector) n).setEndPointX(mouseEvent.getX());
                    				((Connector) n).setEndPointY(mouseEvent.getY());
                    				mouseEvent.consume();
                    			}
                    		}
                    	}
                    };
                });
	}
}
