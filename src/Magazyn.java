import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		
		zaklkartdostawcy.jbtPrzycisk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String label = e.getActionCommand();
        Object z= e.getSource();
        if(z==menu.szukanietowarow) {
            removeP();
            add(p1);
            validate();
            dopasujSieDoZawartosci();
            repaint();
        }
        if(z==menu.zaklkartdostawcy) {
        	removeP();
        	add(zaklkartdostawcy);
        	validate();
            dopasujSieDoZawartosci();
            repaint();
        }
        if(z==zaklkartdostawcy.jbtPrzycisk){
        	KartaDostawcy karta = new KartaDostawcy();
        	String jtfNazwaSkrocona = zaklkartdostawcy.jtfNazwaSkrocona.getText().toString();
        	String jtfNazwaPelna = zaklkartdostawcy.jtfNazwaPelna.getText().toString();
        	String jtfNip = zaklkartdostawcy.jtfNip.getText().toString();
        	String jtfTelefon1 = zaklkartdostawcy.jtfTelefon1.getText().toString();
        	String jtfTelefon2 = zaklkartdostawcy.jtfTelefon2.getText().toString();
        	String jtfTelefon3 = zaklkartdostawcy.jtfTelefon3.getText().toString();
        	String jtfNazwaDzialu = zaklkartdostawcy.jtfNazwaDzialu.getText().toString();
        	String jtfNrKonta = zaklkartdostawcy.jtfNrKonta.getText().toString();
        	String jtfAdres = zaklkartdostawcy.jtfAdres.getText().toString();
        	String jtfKodPocztowy = zaklkartdostawcy.jtfKodPocztowy.getText().toString();
        	String jtfPoczta  = zaklkartdostawcy.jtfPoczta.getText().toString();
        	boolean spr = karta.insert(jtfNazwaSkrocona, jtfNazwaPelna, jtfNip, jtfTelefon1, jtfTelefon2, jtfTelefon3, jtfNazwaDzialu, jtfNrKonta, jtfAdres, jtfKodPocztowy, jtfPoczta);
        	//JFrame frame = new JFrame("JOptionPane showMessageDialog example");
        	if(spr == true){
        		int dialogButton = JOptionPane.INFORMATION_MESSAGE;
        		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone powodzeniem","", JOptionPane.INFORMATION_MESSAGE);
        		zaklkartdostawcy.jtfNazwaPelna.setText("");
        		zaklkartdostawcy.jtfNazwaPelna.setText("");
        		zaklkartdostawcy.jtfNip.setText("");
        		zaklkartdostawcy.jtfTelefon1.setText("");
        		zaklkartdostawcy.jtfTelefon2.setText("");
        		zaklkartdostawcy.jtfTelefon3.setText("");
        		zaklkartdostawcy.jtfNazwaDzialu.setText("");
        		zaklkartdostawcy.jtfNrKonta.setText("");
        		zaklkartdostawcy.jtfAdres.setText("");
        		zaklkartdostawcy.jtfKodPocztowy.setText("");
        		zaklkartdostawcy.jtfPoczta.setText("");
        	}
        	else {
        		int dialogButton = JOptionPane.ERROR_MESSAGE;
        		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone niepowodzeniem","Uwaga!", JOptionPane.ERROR_MESSAGE);
        	}
        }
	}
	private void dopasujSieDoZawartosci() {
		// TODO Auto-generated method stub
		 pack();   
	        setLocationRelativeTo(null); 
	}
	public void removeP(){
		remove(panel);
		remove(p1);
		remove(zaklkartdostawcy);
	}
}
