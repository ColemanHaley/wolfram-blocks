package wolfram.blocks.view.InputDecorators;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import wolfram.blocks.view.InputNodeDecorator;
import wolfram.blocks.view.InputNodeView;

public class LongInputFieldDecorator extends InputNodeDecorator {

	public LongInputFieldDecorator(InputNodeView i) {
		super(i);
		addInputField(i);
		
	}

	private void addInputField(InputNodeView i){
		TextField t = new TextField();
		t.setPrefWidth(100.0); //Make variable width
		i.getChildren().add(t);
		AnchorPane.setLeftAnchor(t, 24.0);
	}
	
}
