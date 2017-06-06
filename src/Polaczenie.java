import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Polaczenie{

	private final static String DBURL="jdbc:mysql://localhost:3306/magazyn";
	private final static String DBUSER = "root";
	private final static String DBPASS = "";
	private final static String DBDRIVER = "com.mysql.jdbc.Driver";
	
	private Connection connection;
	
	public Polaczenie(){
		try {
			//Register JDBC driver
			Class.forName(DBDRIVER);
			//Open a connection
			connection = (Connection) DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ResultSet sqlSelect(String query){
		Statement stmt = null;  
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();  
			System.out.println(query);
			rs = stmt.executeQuery(query);
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return rs;
	} 
	public void closeConn(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
