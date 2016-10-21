package wolfram.blocks.model;

public class BlockFactory {
	
	public static Expr createBlockData(String type) {
		Function fn = null;
		Expr expr = null;
		switch (type) {
			case "String":
				fn = Function.STRING;
				expr = new Expr("String", fn);
				break;
			case "Style":
				fn = Function.STYLE;
				expr = new Expr("Style", fn);
			case "List":
				fn = Function.MLIST;
				expr = new Expr("List", fn);
		}
		if (fn != null && expr != null) {
			
			if (fn.hasOutput()) {
				expr.hasOutput();
			}
			if (fn.isCollapsible()) {
				expr.isCollapsible();
			}
			if (fn.inputCount()>0) {
				expr.hasInputs();
				for(InputNode i : fn.getInputs()) {
					expr.addInput(i);
				}
			}
		
			return expr;
			
		} else {
			throw new NullPointerException();
		}
		
	}
	
	
}
