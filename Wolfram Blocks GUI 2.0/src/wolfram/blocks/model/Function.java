package wolfram.blocks.model;

import java.util.ArrayList;
import java.util.Collections;

public enum Function {
	STRING ("String", 0, true, true, true),
	//SELECT ("Select", 3, true, true, true),
	STYLE ("Style", 1, true, true, true),
	//SELECTFIRST,
	//APPENDTO,
	MLIST ("List", 1, true, true, false);
	
	private final String type;
	private final ArrayList<InputNode> inputs = new ArrayList<InputNode>();
	private final ArrayList<BlockAttribute> attributes = new ArrayList<BlockAttribute>();
	private final int inputCount;
	private final boolean hasOutput;
	private final boolean isCollapsible;
	
	Function (String label, int numIn, boolean outputs, boolean collapses, boolean hasAttributes) {
		this.type = label;
		this.inputCount = numIn;
		this.hasOutput = outputs;
		this.isCollapsible = collapses;
		if (hasAttributes == true) {
			makeAttributes();
		}
		makeInputs();
	}
	
	public String type() {return type;}
	public int inputCount() {return inputCount;}
	public boolean hasOutput() {return hasOutput;}
	public boolean isCollapsible() {return isCollapsible;}
	
	private void makeInputs() {
		if(inputCount > 0) {
			//TODO must resolve attr clearing for more than one input!
			ArrayList<InputAttribute> attr = new ArrayList<InputAttribute>();
			switch (type) {
				case "List":
					Collections.addAll(attr, 
							InputAttribute.INPUTFIELD,
							InputAttribute.MATHEMATICAINPUTFIELD,
							InputAttribute.NAMELESS, 
							InputAttribute.LONGINPUTFIELD, 
							InputAttribute.CANADDINPUTS);
					InputNode listInput = new InputNode("", new ArrayList<OutputNode>(), attr, 1);
					inputs.add(listInput);
					break;
				case "Style":
					Collections.addAll(attr,
							InputAttribute.INPUTFIELD,
							InputAttribute.MATHEMATICAINPUTFIELD,
							InputAttribute.LONGINPUTFIELD);
					
					InputNode styleExprInput = new InputNode("", new ArrayList<OutputNode>(), attr, 1);
					InputNode stringInput2 = new InputNode("", new ArrayList<OutputNode>(), attr, 2);
					//System.out.println(styleExprInput.getAttributes().size());
					inputs.add(styleExprInput);
					inputs.add(stringInput2);
					//System.out.println(styleExprInput.getAttributes().size());
					break;
				case "String":
					Collections.addAll(attr,
							InputAttribute.INPUTFIELD,
							InputAttribute.STRINGINPUTFIELD,
							InputAttribute.LONGINPUTFIELD);
					InputNode stringInput = new InputNode("", new ArrayList<OutputNode>(), attr, 1);
					inputs.add(stringInput);
					break;
			}	
		}
	}
	
	private void makeAttributes() {
		switch (type) {
			case "Style": 
				this.attributes.add(BlockAttribute.HASOPTIONS);
			case "String":
				this.attributes.add(BlockAttribute.TIGHTFIT);
		}
	}

	
	public ArrayList<InputNode> getInputs() {return inputs;}
}

