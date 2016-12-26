package wolfram.blocks.model;

public interface OutputtingObject {
	public void connect(Expr endpoint);
	public OutputNode output();
}
