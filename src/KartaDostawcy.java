import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;

public class KartaDostawcy extends JPanel implements ActionListener{
    String serverName = "localhost";
    String mydatabase = "magazyn";
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
    String username = "root";
    String password = "";
    
    JButton jbtPrzycisk;
    JLabel jlbNazwaSkrocona, jlbNazwaPelna, jlbNip, jlbTelefon1, jlbTelefon2, jlbTelefon3, jlbNazwaDzialu, jlbNrKonta, jlbAdres, jlbKodPocztowy, jlbPoczta;
    JTextField jtfNip, jtfTelefon1, jtfTelefon2, jtfTelefon3, jtfNazwaDzialu, jtfNrKonta, jtfAdres, jtfKodPocztowy, jtfPoczta;
    JTextArea jtfNazwaSkrocona, jtfNazwaPelna;
    public KartaDostawcy(){
    	setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 10, 0, 10);        
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        
        
        jlbNazwaSkrocona = new JLabel("Nazwa Skrócona");
        jtfNazwaSkrocona = new JTextArea();
        jtfNazwaSkrocona.setPreferredSize(new Dimension(400, 40));
        jtfNazwaSkrocona.setLineWrap(true);
        jtfNazwaSkrocona.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 2, 0, 0)));        
        jlbNazwaSkrocona.setToolTipText(jlbNazwaSkrocona.getText()+" : maksymalna d³ugoœæ to 100 znaków");
        jtfNazwaSkrocona.setToolTipText(jlbNazwaSkrocona.getText()+" : maksymalna d³ugoœæ to 100 znaków");
        jlbNazwaPelna = new JLabel("Nazwa Pe³na");
        jtfNazwaPelna = new JTextArea();
        jtfNazwaPelna.setPreferredSize(new Dimension(400, 40));
        jtfNazwaPelna.setLineWrap(true);
        jtfNazwaPelna.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 2, 0, 0)));
        jlbNazwaPelna.setToolTipText(jlbNazwaPelna.getText()+" : maksymalna d³ugoœæ to 100 znaków");
        jtfNazwaPelna.setToolTipText(jlbNazwaPelna.getText()+" : maksymalna d³ugoœæ to 100 znaków");
        jlbNip = new JLabel("NIP");
        jtfNip = new JTextField("");
        jlbNip.setToolTipText(jlbNip.getText()+" : maksymalna d³ugoœæ to 10 znaków");
        jtfNip.setToolTipText(jlbNip.getText()+" : maksymalna d³ugoœæ to 10 znaków");
        jlbTelefon1 = new JLabel("Telefon 1");
        jtfTelefon1 = new JTextField("");
        jlbTelefon1.setToolTipText(jlbTelefon1.getText()+" : maksymalna d³ugoœæ to 20 znaków");
        jtfTelefon1.setToolTipText(jlbTelefon1.getText()+" : maksymalna d³ugoœæ to 20 znaków");
		jlbTelefon2 = new JLabel("Telefon 2");
		jtfTelefon2 = new JTextField("");
		jlbTelefon2.setToolTipText(jlbTelefon2.getText()+" : maksymalna d³ugoœæ to 20 znaków");
		jtfTelefon2.setToolTipText(jlbTelefon2.getText()+" : maksymalna d³ugoœæ to 20 znaków");
		jlbTelefon3 = new JLabel("Telefon 3");
		jtfTelefon3 = new JTextField("");
		jlbTelefon3.setToolTipText(jlbTelefon3.getText()+" : maksymalna d³ugoœæ to 20 znaków");
		jtfTelefon3.setToolTipText(jlbTelefon3.getText()+" : maksymalna d³ugoœæ to 20 znaków");
		jlbNazwaDzialu = new JLabel("Nazwa Dzia³u");
		jtfNazwaDzialu = new JTextField("");
		jlbNazwaDzialu.setToolTipText(jlbNazwaDzialu.getText()+" : maksymalna d³ugoœæ to 50 znaków");
		jtfNazwaDzialu.setToolTipText(jlbNazwaDzialu.getText()+" : maksymalna d³ugoœæ to 50 znaków");
        jlbNrKonta = new JLabel("Nr Konta");
		jtfNrKonta = new JTextField("");
		jlbNrKonta.setToolTipText(jlbNrKonta.getText()+" : maksymalna d³ugoœæ to 30 znaków");
		jtfNrKonta.setToolTipText(jlbNrKonta.getText()+" : maksymalna d³ugoœæ to 30 znaków");
		jlbAdres = new JLabel("Adres");
		jtfAdres = new JTextField("");
		jlbAdres.setToolTipText(jlbAdres.getText()+" : maksymalna d³ugoœæ to 50 znaków");
		jtfAdres.setToolTipText(jlbAdres.getText()+" : maksymalna d³ugoœæ to 50 znaków");
		jlbKodPocztowy = new JLabel("KodPoczowy");
		jtfKodPocztowy = new JTextField("");
		jlbKodPocztowy.setToolTipText(jlbKodPocztowy.getText()+" : maksymalna d³ugoœæ to 6 znaków");
		jtfKodPocztowy.setToolTipText(jlbKodPocztowy.getText()+" : maksymalna d³ugoœæ to 6 znaków");
		jlbPoczta = new JLabel("Poczta");
		jtfPoczta = new JTextField("");
		jlbPoczta.setToolTipText(jlbPoczta.getText()+" : maksymalna d³ugoœæ to 30 znaków");
		jtfPoczta.setToolTipText(jlbPoczta.getText()+" : maksymalna d³ugoœæ to 30 znaków");
        jbtPrzycisk = new JButton("Zatwierdz");
        
        c.gridx = 0; c.gridy = 0;
        add(jlbNazwaSkrocona,c);
        c.gridx = 1; c.gridy = 0;
        add(jtfNazwaSkrocona,c);
        c.gridx = 0; c.gridy = 1;
        add(jlbNazwaPelna,c);
        c.gridx = 1; c.gridy = 1;
        add(jtfNazwaPelna,c);
        c.gridx = 0; c.gridy = 2;
        add(jlbNip,c);
        c.gridx = 1; c.gridy = 2;
        add(jtfNip,c);
        c.gridx = 0; c.gridy = 3;
        add(jlbTelefon1,c);
        c.gridx = 1; c.gridy = 3;
        add(jtfTelefon1,c);
        c.gridx = 0; c.gridy = 4;
        add(jlbTelefon2,c);
        c.gridx = 1; c.gridy = 4;
        add(jtfTelefon2,c);
        c.gridx = 0; c.gridy = 5;
        add(jlbTelefon3,c);
        c.gridx = 1; c.gridy = 5;
        add(jtfTelefon3,c);
        c.gridx = 0; c.gridy = 6;
        add(jlbNazwaDzialu,c);
        c.gridx = 1; c.gridy = 6;
        add(jtfNazwaDzialu,c);
        c.gridx = 0; c.gridy = 7;
        add(jlbNrKonta,c);
        c.gridx = 1; c.gridy = 7;
        add(jtfNrKonta,c);
        c.gridx = 0; c.gridy = 8;
        add(jlbAdres,c);
        c.gridx = 1; c.gridy = 8;
        add(jtfAdres,c);
        c.gridx = 0; c.gridy = 9;
        add(jlbKodPocztowy,c);
        c.gridx = 1; c.gridy = 9;
        add(jtfKodPocztowy,c);
        c.gridx = 0; c.gridy = 10;
        add(jlbPoczta,c);
        c.gridx = 1; c.gridy = 10;
        add(jtfPoczta,c);
        c.gridx = 0; c.gridy = 11;
        add(jbtPrzycisk,c);
        
        ustawNasluchZdarzen();
        documentListener();
        focusListener();
    }
	private void ustawNasluchZdarzen(){
    	//jtfNazwaSkrocona.addActionListener(this);
    	//jtfNazwaPelna.addActionListener(this);
		jtfNip.addActionListener(this);
		jtfTelefon1.addActionListener(this);
		jtfTelefon2.addActionListener(this);
		jtfTelefon3.addActionListener(this);
		jtfNazwaDzialu.addActionListener(this);
		jtfNrKonta.addActionListener(this);
		jtfAdres.addActionListener(this);
		jtfKodPocztowy.addActionListener(this);
		jtfPoczta.addActionListener(this);
		jbtPrzycisk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        Object z= e.getSource();
    	if(z==jbtPrzycisk){
    		kartaWalidacja();
    	}
        else if(z==jtfNazwaSkrocona || z==jtfNazwaPelna || z==jtfNip || z==jtfTelefon1 || z==jtfTelefon2 || z==jtfTelefon3 || z==jtfNazwaDzialu || z==jtfNrKonta || z==jtfAdres || z==jtfKodPocztowy || z==jtfPoczta){
        	jtfNazwaSkrocona.setBackground(Color.WHITE);
    		jtfNazwaPelna.setBackground(Color.WHITE);
    		jtfNip.setBackground(Color.WHITE);
    		jtfTelefon1.setBackground(Color.WHITE);
    		jtfTelefon2.setBackground(Color.WHITE);
    		jtfTelefon3.setBackground(Color.WHITE);
    		jtfNazwaDzialu.setBackground(Color.WHITE);
    		jtfNrKonta.setBackground(Color.WHITE);
    		jtfAdres.setBackground(Color.WHITE);
    		jtfKodPocztowy.setBackground(Color.WHITE);
    		jtfPoczta.setBackground(Color.WHITE);
    	}
	}
    private boolean insert(String NazwaSkrocona, String NazwaPelna, String Nip, String Telefon1, String Telefon2, String Telefon3, String NazwaDzialu, String NrKonta, String Adres, String KodPocztowy, String Poczta){
		try{
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement myStmt = connection.createStatement();
			String query = "INSERT INTO dostawca "
				+ "(NazwaSkrocona, NazwaPelna, NIP, Telefon1, Telefon2, Telefon3, NazwaDzialu, NrKonta, Adres, KodPocztowy, Poczta)"
			    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString (1, NazwaSkrocona);
			preparedStmt.setString (2, NazwaPelna);
			preparedStmt.setString (3, Nip);
			preparedStmt.setString (4, Telefon1);
			preparedStmt.setString (5, Telefon2);
			preparedStmt.setString (6, Telefon3);
			preparedStmt.setString (7, NazwaDzialu);
			preparedStmt.setString (8, NrKonta);
			preparedStmt.setString (9, Adres);
			preparedStmt.setString (10, KodPocztowy);
			preparedStmt.setString (11, Poczta);
			
			// execute the preparedstatement
			preparedStmt.execute();
			connection.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
    private void kartaWalidacja() {
		// TODO Auto-generated method stub
    	String NazwaSkrocona = jtfNazwaSkrocona.getText().toString();
    	String NazwaPelna = jtfNazwaPelna.getText().toString();
    	String Nip = jtfNip.getText().toString();
    	String Telefon1 = jtfTelefon1.getText().toString();
    	String Telefon2 = jtfTelefon2.getText().toString();
    	String Telefon3 = jtfTelefon3.getText().toString();
    	String NazwaDzialu = jtfNazwaDzialu.getText().toString();
    	String NrKonta = jtfNrKonta.getText().toString();
    	String Adres = jtfAdres.getText().toString();
    	String KodPocztowy = jtfKodPocztowy.getText().toString();
    	String Poczta  = jtfPoczta.getText().toString();
    	String walidacja = walidacja(NazwaSkrocona, NazwaPelna, Nip, Telefon1, Telefon2, Telefon3, NazwaDzialu, NrKonta, Adres, KodPocztowy, Poczta);
    	if(walidacja.length()>0){
    		int dialogButton = JOptionPane.INFORMATION_MESSAGE;
    		JOptionPane.showMessageDialog(null, walidacja,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
    	}else {
	    	boolean spr = insert(NazwaSkrocona, NazwaPelna, Nip, Telefon1, Telefon2, Telefon3, NazwaDzialu, NrKonta, Adres, KodPocztowy, Poczta);
	    	//JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	    	if(spr == true){
	    		int dialogButton = JOptionPane.INFORMATION_MESSAGE;
	    		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone powodzeniem","", JOptionPane.INFORMATION_MESSAGE);
	    		jtfNazwaSkrocona.setText("");
	    		jtfNazwaPelna.setText("");
	    		jtfNip.setText("");
	    		jtfTelefon1.setText("");
	    		jtfTelefon2.setText("");
	    		jtfTelefon3.setText("");
	    		jtfNazwaDzialu.setText("");
	    		jtfNrKonta.setText("");
	    		jtfAdres.setText("");
	    		jtfKodPocztowy.setText("");
	    		jtfPoczta.setText("");
	    	}
	    	else {
	    		int dialogButton = JOptionPane.ERROR_MESSAGE;
	    		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone niepowodzeniem","Uwaga!", JOptionPane.ERROR_MESSAGE);
	    	}
    	}
	}
    private String walidacja(String NazwaSkrocona, String NazwaPelna, String Nip, String Telefon1, String Telefon2, String Telefon3, String NazwaDzialu, String NrKonta, String Adres, String KodPocztowy, String Poczta){
    	String error="";
    	if(NazwaSkrocona.length()>100){ 
    		error+="Nazwa Skrócona zosta³a podana nieprawid³owa(100max znaków)\n";
    		jtfNazwaSkrocona.setBackground(Color.RED);
    	}
    	if(NazwaSkrocona.matches("^\\s*$")){
    		error+="Nazwa Skrócona zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
    		jtfNazwaSkrocona.setBackground(Color.RED);
    	}    	
    	if(NazwaPelna.length()>100){
    		error+="Nazwa Pe³na zosta³a podana nieprawid³owa(100max znaków)\n";
    		jtfNazwaPelna.setBackground(Color.RED);
    	}
    	if(NazwaPelna.matches("^\\s*$")){
    		error+="Nazwa Pe³na zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
    		jtfNazwaPelna.setBackground(Color.RED);
    	} 
    	if(Nip.length()!=10){
    		error+="D³ugoœæ Nip'u musi byc 10(cyfr)\n";
    		jtfNip.setBackground(Color.RED);
    	} 
    	else if(!Nip.matches("[0-9]{10}")){
    		error+="Nip mo¿e zawieraæ tylko cyfry(10)\n";
    		jtfNip.setBackground(Color.RED);
    	}
    	if(!Telefon1.isEmpty() && !Telefon1.matches("[0-9]{9,20}")){
    		error+="Numer Telefon1 mo¿e sk³adaæ siê tylko z cyfr(o d³ugoœci od 9 do 20)\n";
    		jtfTelefon1.setBackground(Color.RED);
    	}
    	if(!Telefon2.isEmpty() && !Telefon2.matches("[0-9]{9,20}")){
    		error+="Numer Telefon2 mo¿e sk³adaæ siê tylko z cyfr(o d³ugoœci od 9 do 20)\n";
    		jtfTelefon2.setBackground(Color.RED);
    	}
    	if(!Telefon3.isEmpty() && !Telefon3.matches("[0-9]{9,20}")){
    		error+="Numer Telefon3 mo¿e sk³adaæ siê tylko z cyfr(o d³ugoœci od 9 do 20)\n";
    		jtfTelefon3.setBackground(Color.RED);
    	}
    	if(NazwaDzialu.length()>50){
    		error+="Nazwa Dzia³u zosta³a podana zbyt d³uga(50max)\n";
    		jtfNazwaDzialu.setBackground(Color.RED);
    	}
    	if(NazwaDzialu.matches("^\\s*$")){
    		error+="Nazwa Dzialu zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
    		jtfNazwaDzialu.setBackground(Color.RED);
    	} 
    	if(NrKonta.length()>30){
    		error+="Nr Konta zosta³ podany zbyt d³ugi(30max)\n";
    		jtfNrKonta.setBackground(Color.RED);
    	} 
    	if(NrKonta.matches("^\\s*$")){
    		error+="Nr Konta zosta³ podany nieprawid³owy(nie mo¿e pozostaæ pusty)\n";
    		jtfNrKonta.setBackground(Color.RED);
    	}
    	if(Adres.length()>50){
    		error+="Adres zosta³ podany zbyt d³ugi(50max)\n";
    		jtfAdres.setBackground(Color.RED);
    	}
    	if(Adres.matches("^\\s*$")){
    		error+="Adres zosta³ podany nieprawid³owy(nie mo¿e pozostaæ pusty)\n";
    		jtfAdres.setBackground(Color.RED);
    	}
    	if(KodPocztowy.length()>6){
    		error+="Kod Pocztowy zosta³ podany zbyt d³ugi(6max)\n";
    		jtfKodPocztowy.setBackground(Color.RED);
    	}
    	else if(!KodPocztowy.matches("[0-9]{2}\\-[0-9]{3}")){
    		error+="Kod Pocztowy zosta³ podany nieprawid³owy(00-000)\n";
    		jtfKodPocztowy.setBackground(Color.RED);
    	}
    	if(Poczta.length()>30){
    		error+="Poczta zosta³a podana zbyt d³uga(30max)\n";
    		jtfPoczta.setBackground(Color.RED);
    	}
    	if(Poczta.matches("^\\s*$")){
    		error+="Poczta zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
    		jtfPoczta.setBackground(Color.RED);
    	}
    	return error;
    }
    private void documentListener(){
        jtfNazwaSkrocona.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) { check(); }
			@Override
			public void insertUpdate(DocumentEvent e) { check(); }
			@Override
			public void changedUpdate(DocumentEvent e) { check(); }

		    public void check() {
		    	if (jtfNazwaSkrocona.getText().length()>10){//make sure no more than 4 lines
		    		JOptionPane.showMessageDialog(null, "Nie mo¿na wprowadzic wiêcej ni¿ 100 znaków!", "B³¹d!", JOptionPane.ERROR_MESSAGE);
		    	}
		    }
		});
        jtfNazwaPelna.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) { check(); }
			@Override
			public void insertUpdate(DocumentEvent e) { check(); }
			@Override
			public void changedUpdate(DocumentEvent e) { check(); }

		    public void check() {
		    	if (jtfNazwaPelna.getText().length()>100){//make sure no more than 4 lines
		    		JOptionPane.showMessageDialog(null, "Nie mo¿na wprowadzic wiêcej ni¿ 100 znaków!", "B³¹d!", JOptionPane.ERROR_MESSAGE);
		    	}
		    }
		});
    }
    private void focusListener(){
        jtfNazwaSkrocona.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				jtfNazwaSkrocona.selectAll();
				jtfNazwaSkrocona.setBackground(Color.WHITE);
			}
		});
        jtfNazwaPelna.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				jtfNazwaPelna.selectAll();
				jtfNazwaPelna.setBackground(Color.WHITE);
			}
		});
        jtfNip.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				jtfNip.selectAll();
				jtfNip.setBackground(Color.WHITE);
			}
		});
        jtfTelefon1.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				jtfTelefon1.selectAll();
				jtfTelefon1.setBackground(Color.WHITE);
			}
		});
		jtfTelefon2.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				jtfTelefon2.selectAll();
				jtfTelefon2.setBackground(Color.WHITE);
			}
		});
		jtfTelefon3.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				jtfTelefon3.selectAll();
				jtfTelefon3.setBackground(Color.WHITE);
			}
		});
		jtfNazwaDzialu.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				jtfNazwaDzialu.selectAll();
				jtfNazwaDzialu.setBackground(Color.WHITE);
			}
		});
		jtfNrKonta.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				jtfNrKonta.selectAll();
				jtfNrKonta.setBackground(Color.WHITE);
			}
		});
		jtfAdres.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				jtfAdres.selectAll();
				jtfAdres.setBackground(Color.WHITE);
			}
		});
		jtfKodPocztowy.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				jtfKodPocztowy.selectAll();
				jtfKodPocztowy.setBackground(Color.WHITE);
			}
		});
		jtfPoczta.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				jtfPoczta.selectAll();
				jtfPoczta.setBackground(Color.WHITE);
			}
		});
	}
}
