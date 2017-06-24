package wolfram.blocks.model;

import java.sql.*;
import org.h2.Driver;

public class FunctionDB {
	public void startDatabase(){
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try {
			Connection conn = DriverManager.
					getConnection("jdbc:h2:~/function;INIT=create schema if not exists function\\;" 
			+ "SET SCHEMA function;", "sa", "");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
}

