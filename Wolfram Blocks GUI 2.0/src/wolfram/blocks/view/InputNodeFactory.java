package wolfram.blocks.view;

import wolfram.blocks.model.InputAttribute;
import wolfram.blocks.model.InputNode;
import wolfram.blocks.view.InputDecorators.LongInputFieldDecorator;

public class InputNodeFactory {
	
	public void decorateInputNode(InputNodeView inputView){
		
		
		for(InputAttribute i : inputView.getData().getAttributes()){
			/*switch(i){
				case LONGINPUTFIELD:
					inputView = new LongInputFieldDecorator(inputView);
				default:
					break;
			}*/
			
		}
	}
	
}
