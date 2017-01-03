package wolfram.blocks.view;

public final class ConnectCommunicator {

	private static SimpleInputNodeView currentEndnode = null;
	private static boolean inConnectMode = false;
	
	public static void sendConnectSignal(SimpleInputNodeView endNode){
		currentEndnode = endNode;
	}
	 
	public static SimpleInputNodeView getConnectSignal(){
		return currentEndnode;
	}
	
	public static boolean inConnectMode(){return inConnectMode;}
	public static void setConnectMode(boolean b){inConnectMode = b;}
	 
}
