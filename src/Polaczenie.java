import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Polaczenie{

	private final static String DBURL="jdbc:mysql://localhost:3306/magazyn";
	private final static String DBUSER = "root";
	private final static String DBPASS = "";
	private final static String DBDRIVER = "com.mysql.jdbc.Driver";
	
	private Connection connection;
	private Statement statement;
	
	public Polaczenie() throws SQLException{
		connection = (Connection) DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		statement = (Statement) connection.createStatement();
	}
	
	public void sqlSelect(String query) throws SQLException{
		ResultSet rs = statement.executeQuery(query);
	}
	
}
