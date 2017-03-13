

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class KartaDostawcy {
    String serverName = "localhost";
    String mydatabase = "magazyn";
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
    String username = "user";
    String password = "123456";
    public KartaDostawcy(){}
    public boolean insert(String jtfNazwaSkrocona,String jtfNazwaPelna,String jtfNip,String jtfTelefon1, 
    						String jtfTelefon2,String jtfTelefon3,String jtfNazwaDzialu,String jtfNrKonta, 
    						String jtfAdres,String jtfKodPocztowy,String jtfPoczta){
    	try{
    		Connection connection = DriverManager.getConnection(url, username, password);
			Statement myStmt = connection.createStatement();
			
			String query = "INSERT INTO dostawca "
					+ "(NazwaSkrocona, NazwaPelna, NIP, Telefon1, Telefon2, Telefon3, NazwaDzialu, NrKonta, Adres, KodPocztowy, Poczta)"
			        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString (1, jtfNazwaSkrocona);
			preparedStmt.setString (2, jtfNazwaPelna);
			preparedStmt.setString (3, jtfNip);
			preparedStmt.setString (4, jtfTelefon1);
			preparedStmt.setString (5, jtfTelefon2);
			preparedStmt.setString (6, jtfTelefon3);
			preparedStmt.setString (7, jtfNazwaDzialu);
			preparedStmt.setString (8, jtfNrKonta);
			preparedStmt.setString (9, jtfAdres);
			preparedStmt.setString (10, jtfKodPocztowy);
			preparedStmt.setString (11, jtfPoczta);
			
			  // execute the preparedstatement
			preparedStmt.execute();
			  
			connection.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
    }
}
