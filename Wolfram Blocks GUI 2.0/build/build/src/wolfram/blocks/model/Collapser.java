package wolfram.blocks.model;

public class Collapser {
	private long id;

	public Collapser () {
		//TODO Add graphics representation;
	}
	
	public static boolean regionMemberQ () {
		return false;
	}
	
	//ID Functions
	public void setID (long newID) {
		id = newID;
	}
	
	public long getID () {
		return id;
	}
}
