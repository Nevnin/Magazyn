

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KartaDostawcy {
    String serverName = "localhost";
    String mydatabase = "test";
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
    String username = "user";
    String password = "123456";
	public KartaDostawcy(String imie, String nazwisko){
	    try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement myStmt = connection.createStatement();
			String sql;
			sql = "SELECT * FROM test";
//			ResultSet myRs = myStmt.executeQuery(sql);
//			while(myRs.next()){
//				System.out.println(myRs.getString("id")+","+myRs.getString("test"));
//			}
//			myStmt.executeQuery("INSERT INTO test (id, test) "
//			          +"VALUES (1,'imie')");
			sql = "INSERT INTO test (id, test) "
			          +"VALUES (1,'"+imie+"')";
			myStmt.executeUpdate(sql);
//			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
