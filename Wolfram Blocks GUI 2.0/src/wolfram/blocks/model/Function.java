package wolfram.blocks.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

// TODO add block attributes
public class Function {
	public static Connection fnConn = null;
	private static Map<Integer, InputAttribute> attlist;
	
	private String name;
	private String label;
	private int numInputs;
	boolean hasoutputs;
	boolean collapsible;
	boolean attributes;
	ArrayList<InputNode> in;
	
	public Function(String type, Connection con, Map<Integer, InputAttribute> inAttr) {
		Statement stmt;
		String getType = "SELECT * FROM FUNCTIONS WHERE NAME = '" + type + "'";
		name = type;
		try {
			if (fnConn == null) {
				fnConn = con;
				attlist = inAttr;
			}
			stmt = fnConn.createStatement();
			ResultSet function = stmt.executeQuery(getType);
			int count = 0;
			while (function.next()) {
				if (count > 0) {
					throw new SQLException();
				}
				label = function.getString("LABEL"); //Maybe use?
				numInputs = function.getInt("NUMINPUTS");
				hasoutputs = function.getBoolean("HASOUTPUT");
				collapsible = function.getBoolean("COLLAPSIBLE");
				attributes = function.getBoolean("ATTRIBUTES");
				if (numInputs != 0) {
					in = getInputs(name);
				}
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<InputNode> getInputs(String name) {
		ArrayList<InputNode> results = new ArrayList<>();
		try {
			Statement instmt = fnConn.createStatement();
			ResultSet inputs = instmt.executeQuery("SELECT * FROM INPUTS WHERE FN_NAME = '" + name + "'");
			while (inputs.next()) {
				String label = inputs.getString("LABEL");
				int id = inputs.getInt("IN_ID");
				Statement attrstmt = fnConn.createStatement();
				ResultSet attr = attrstmt.executeQuery("SELECT * FROM INPUT_DETAILS WHERE IN_ID = " 
				+ id + " AND FN_NAME = '" + name + "'");
				ArrayList<InputAttribute> attrs = new ArrayList<>();
				while (attr.next()) {
					int at = attr.getInt("ATNAME");
					attrs.add(attlist.get(at));
				}
				results.add(new InputNode(label, attrs, id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

	public String getName() {
		return name;
	}

	public String getLabel() {
		return label;
	}

	public int getNumInputs() {
		return numInputs;
	}

	public boolean isHasoutputs() {
		return hasoutputs;
	}

	public boolean isCollapsible() {
		return collapsible;
	}

	public boolean isAttributes() {
		return attributes;
	}

	public ArrayList<InputNode> getIn() {
		return in;
	}

}
