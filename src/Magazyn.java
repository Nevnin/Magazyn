import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Magazyn extends JFrame implements ActionListener {
	Panel panel;
	Panel p1;
	Panel zaklkartdostawcy = new Panel(400,400,4);
	Menu menu;
	private final static String DBURL="jdbc:mysql://127.0.0.1:3306/magazyn";
	private final static String DBUSER = "root";
	private final static String DBPASS = "";
	private final static String DBDRIVER = "com.mysql.jdbc.Driver";
	
	private Connection connection;
	private Statement statement;
	String query="Select * from uzytkownik";
	public Magazyn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			Class.forName(DBDRIVER);
			connection = (Connection) DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				System.out.println(rs.getString(2));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Problem ze sterownikiem ");
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("SQLSTATE:" + e.getSQLState());
			System.out.println("VendorError:"+e.getErrorCode());
		}
		menu = new Menu();
		setJMenuBar(menu);
		panel = new Panel(400,400,0);
		p1 = new Panel(400,400,1);
		add(panel);
		ustawNasluchZdarzen();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private void ustawNasluchZdarzen() {
		menu.szukanietowarow.addActionListener(this);
		menu.plik.addActionListener(this);
		menu.zaklkartdostawcy.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String label = e.getActionCommand();
        Object z= e.getSource();
        if(z==menu.szukanietowarow) {
            remove(panel);
            add(p1);
            validate();
            dopasujSieDoZawartosci();
            repaint();
        }
	}
	private void dopasujSieDoZawartosci() {
		// TODO Auto-generated method stub
		 pack();   
	        setLocationRelativeTo(null); 
	}
}
