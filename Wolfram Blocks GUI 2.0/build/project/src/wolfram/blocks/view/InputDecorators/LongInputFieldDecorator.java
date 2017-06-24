package wolfram.blocks.view.InputDecorators;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

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

	@Override
	protected boolean impl_computeContains(double arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BaseBounds impl_computeGeomBounds(BaseBounds arg0, BaseTransform arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected NGNode impl_createPeer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object impl_processMXNode(MXNodeAlgorithm arg0, MXNodeAlgorithmContext arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
