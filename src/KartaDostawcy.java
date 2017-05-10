import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class KartaDostawcy extends JPanel implements ActionListener{
    String serverName = "localhost";
    String mydatabase = "magazyn";
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
    String username = "root";
    String password = "";
    
    private JButton jbtPrzycisk;
    private JLabel jlbTytul, jlbNazwaSkrocona, jlbNazwaPelna, jlbNip, jlbTelefon1, jlbTelefon2, jlbTelefon3, jlbNazwaDzialu, jlbNrKonta, jlbAdres, jlbKodPocztowy, jlbPoczta;
    private JTextField jtfNazwaSkrocona, jtfNip, jtfTelefon1, jtfTelefon2, jtfTelefon3, jtfNazwaDzialu, jtfNrKonta, jtfAdres, jtfKodPocztowy, jtfPoczta;
    private JTextArea jtfNazwaPelna;
	Polaczenie polaczenie;
    public KartaDostawcy(){     
    	setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        gbc.insets = new Insets(0, 10, 1, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        jlbTytul = new JLabel("Dodawanie Karty Dostawcy");
        jlbTytul.setFont(new Font("Calibri", Font.BOLD, 30));
        jlbNazwaSkrocona = new JLabel("Nazwa Skrócona*");
        jtfNazwaSkrocona = new JTextField("",35);
        jtfNazwaSkrocona.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 1, 0, 0)));        
        jlbNazwaSkrocona.setToolTipText(jlbNazwaSkrocona.getText()+" : pole wymagane, maksymalna d³ugoœæ to 35 znaków");
        jtfNazwaSkrocona.setToolTipText(jlbNazwaSkrocona.getText()+" : maksymalna d³ugoœæ to 35 znaków");
        jlbNazwaPelna = new JLabel("Nazwa Pe³na*");
        jtfNazwaPelna = new JTextArea();
        jtfNazwaPelna.setPreferredSize(new Dimension(400, 40));
        jtfNazwaPelna.setLineWrap(true);
        jtfNazwaPelna.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 1, 0, 0)));
        jlbNazwaPelna.setToolTipText(jlbNazwaPelna.getText()+" : pole wymagane, maksymalna d³ugoœæ to 100 znaków");
        jtfNazwaPelna.setToolTipText(jlbNazwaPelna.getText()+" : maksymalna d³ugoœæ to 100 znaków");
        jlbNip = new JLabel("NIP*");
        jtfNip = new JTextField("",36);
        jlbNip.setToolTipText(jlbNip.getText()+" : pole wymagane, maksymalna d³ugoœæ to 10 znaków");
        jtfNip.setToolTipText(jlbNip.getText()+" : maksymalna d³ugoœæ to 10 znaków");
        jlbTelefon1 = new JLabel("Telefon 1");
        jtfTelefon1 = new JTextField("");
        jtfTelefon1.setMinimumSize(new Dimension(100, 20));
        jtfTelefon1.setPreferredSize(new Dimension(400, 20));
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
		jlbNazwaDzialu = new JLabel("Nazwa Dzia³u*");
		jtfNazwaDzialu = new JTextField("");
		jlbNazwaDzialu.setToolTipText(jlbNazwaDzialu.getText()+" : pole wymagane, maksymalna d³ugoœæ to 50 znaków");
		jtfNazwaDzialu.setToolTipText(jlbNazwaDzialu.getText()+" : maksymalna d³ugoœæ to 50 znaków");
        jlbNrKonta = new JLabel("Nr Konta*");
		jtfNrKonta = new JTextField("");
		jlbNrKonta.setToolTipText(jlbNrKonta.getText()+" : pole wymagane, maksymalna d³ugoœæ to 30 znaków");
		jtfNrKonta.setToolTipText(jlbNrKonta.getText()+" : maksymalna d³ugoœæ to 30 znaków");
		jlbAdres = new JLabel("Adres*");
		jtfAdres = new JTextField("");
		jlbAdres.setToolTipText(jlbAdres.getText()+" : pole wymagane, maksymalna d³ugoœæ to 50 znaków");
		jtfAdres.setToolTipText(jlbAdres.getText()+" : maksymalna d³ugoœæ to 50 znaków");
		jlbKodPocztowy = new JLabel("KodPoczowy*");
		jtfKodPocztowy = new JTextField("");
		jlbKodPocztowy.setToolTipText(jlbKodPocztowy.getText()+" : pole wymagane, maksymalna d³ugoœæ to 6 znaków");
		jtfKodPocztowy.setToolTipText(jlbKodPocztowy.getText()+" : maksymalna d³ugoœæ to 6 znaków");
		jlbPoczta = new JLabel("Poczta*");
		jtfPoczta = new JTextField("");
		jlbPoczta.setToolTipText(jlbPoczta.getText()+" : pole wymagane, maksymalna d³ugoœæ to 30 znaków");
		jtfPoczta.setToolTipText(jlbPoczta.getText()+" : maksymalna d³ugoœæ to 30 znaków");
        jbtPrzycisk = new JButton("Zatwierdz");
//        jbtPrzycisk.setMaximumSize(new Dimension(100, 25));
//        jbtPrzycisk.setPreferredSize(new Dimension(100, 25));
        
        gbc.gridx = 1; gbc.gridy = 0;
        add(jlbTytul,gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(jlbNazwaSkrocona,gbc);
        gbc.gridx++;
        add(jtfNazwaSkrocona,gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(jlbNazwaPelna,gbc);
        gbc.gridx++;
        add(jtfNazwaPelna,gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(jlbNip,gbc);
        gbc.gridx++;
        add(jtfNip,gbc);

		gbc.gridx = 0; gbc.gridy++;
		add(jlbTelefon1,gbc);
		gbc.gridx++;
		add(jtfTelefon1,gbc);
		gbc.gridx = 0; gbc.gridy++;
		add(jlbTelefon2,gbc);
		gbc.gridx++;
		add(jtfTelefon2,gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(jlbTelefon3,gbc);
        gbc.gridx++;
        add(jtfTelefon3,gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(jlbNazwaDzialu,gbc);
        gbc.gridx++;
        add(jtfNazwaDzialu,gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(jlbNrKonta,gbc);
        gbc.gridx++;
        add(jtfNrKonta,gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(jlbAdres,gbc);
        gbc.gridx++;
        add(jtfAdres,gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(jlbKodPocztowy,gbc);
        gbc.gridx++;
        add(jtfKodPocztowy,gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(jlbPoczta,gbc);
        gbc.gridx++;
        add(jtfPoczta,gbc);
        gbc.gridx = 0; gbc.gridy++;
//       
        gbc.gridy++;
        add(jbtPrzycisk,gbc);
        
        ustawNasluchZdarzen();
        documentListener();
        focusListener();
        keyListener();
    }
	private void ustawNasluchZdarzen(){
		jbtPrzycisk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
        Object z= e.getSource();
    	if(z==jbtPrzycisk){
    		kartaWalidacja();
    	}
	}
	private boolean insertDostawca(){
    	String nazwaSkrocona = jtfNazwaSkrocona.getText().toString();
    	String nazwaPelna = jtfNazwaPelna.getText().toString();
    	String nip = jtfNip.getText().toString();
    	String telefon1 = jtfTelefon1.getText().toString();
    	String telefon2 = jtfTelefon2.getText().toString();
    	String telefon3 = jtfTelefon3.getText().toString();
    	String nazwaDzialu = jtfNazwaDzialu.getText().toString();
    	String nrKonta = jtfNrKonta.getText().toString();
    	String adres = jtfAdres.getText().toString();
    	String kodPocztowy = jtfKodPocztowy.getText().toString();
    	String poczta  = jtfPoczta.getText().toString();
		try{
			Connection connection = DriverManager.getConnection(url, username, password);
			connection.createStatement();
			String query = "INSERT INTO dostawca "
				+ "(NazwaSkrocona, NazwaPelna, NIP, Telefon1, Telefon2, Telefon3, NazwaDzialu, NrKonta, Adres, KodPocztowy, Poczta)"
			    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString (1, nazwaSkrocona);
			preparedStmt.setString (2, nazwaPelna);
			preparedStmt.setString (3, nip);
			preparedStmt.setString (4, telefon1);
			preparedStmt.setString (5, telefon2);
			preparedStmt.setString (6, telefon3);
			preparedStmt.setString (7, nazwaDzialu);
			preparedStmt.setString (8, nrKonta);
			preparedStmt.setString (9, adres);
			preparedStmt.setString (10, kodPocztowy);
			preparedStmt.setString (11, poczta);
			
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
    	//insertTowaryDostawcy();
    	String walidacja = walidacjaDanychDostawcy();
    	if(walidacja.length()>0){
    		JOptionPane.showMessageDialog(null, walidacja,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
    	}else {
	    	boolean spr = insertDostawca();
	    	if(spr == true){
	    		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone powodzeniem","", JOptionPane.INFORMATION_MESSAGE);
	    		wyczyscDaneKontaktowe();
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone niepowodzeniem","Uwaga!", JOptionPane.ERROR_MESSAGE);
	    	}
    	}
	}
    private String walidacjaDanychDostawcy(){
    	String error="";
    	String nazwaSkrocona = jtfNazwaSkrocona.getText().toString();
    	String nazwaPelna = jtfNazwaPelna.getText().toString();
    	String nip = jtfNip.getText().toString();
    	String telefon1 = jtfTelefon1.getText().toString();
    	String telefon2 = jtfTelefon2.getText().toString();
    	String telefon3 = jtfTelefon3.getText().toString();
    	String nazwaDzialu = jtfNazwaDzialu.getText().toString();
    	String nrKonta = jtfNrKonta.getText().toString();
    	String adres = jtfAdres.getText().toString();
    	String kodPocztowy = jtfKodPocztowy.getText().toString();
    	String poczta  = jtfPoczta.getText().toString();
    	if(nazwaSkrocona.length()>100){ 
    		error+="Nazwa Skrócona zosta³a podana nieprawid³owa(100max znaków)\n";
    		jtfNazwaSkrocona.setBackground(Color.RED);
    	}
    	if(nazwaSkrocona.matches("^\\s*$")){
    		error+="Nazwa Skrócona zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
    		jtfNazwaSkrocona.setBackground(Color.RED);
    	}    	
    	if(nazwaPelna.length()>100){
    		error+="Nazwa Pe³na zosta³a podana nieprawid³owa(100max znaków)\n";
    		jtfNazwaPelna.setBackground(Color.RED);
    	}
    	if(nazwaPelna.matches("^\\s*$")){
    		error+="Nazwa Pe³na zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
    		jtfNazwaPelna.setBackground(Color.RED);
    	} 
    	if(nip.length()!=10){
    		error+="D³ugoœæ Nip'u musi byc 10(cyfr)\n";
    		jtfNip.setBackground(Color.RED);
    	} 
    	else if(!nip.matches("[0-9]{10}")){
    		error+="Nip mo¿e zawieraæ tylko cyfry(10)\n";
    		jtfNip.setBackground(Color.RED);
    	}
    	if(!telefon1.isEmpty() && !telefon1.matches("[0-9]{9,20}")){
    		error+="Numer Telefon1 mo¿e sk³adaæ siê tylko z cyfr(o d³ugoœci od 9 do 20)\n";
    		jtfTelefon1.setBackground(Color.RED);
    	}
    	if(!telefon2.isEmpty() && !telefon2.matches("[0-9]{9,20}")){
    		error+="Numer Telefon2 mo¿e sk³adaæ siê tylko z cyfr(o d³ugoœci od 9 do 20)\n";
    		jtfTelefon2.setBackground(Color.RED);
    	}
    	if(!telefon3.isEmpty() && !telefon3.matches("[0-9]{9,20}")){
    		error+="Numer Telefon3 mo¿e sk³adaæ siê tylko z cyfr(o d³ugoœci od 9 do 20)\n";
    		jtfTelefon3.setBackground(Color.RED);
    	}
    	if(nazwaDzialu.length()>50){
    		error+="Nazwa Dzia³u zosta³a podana zbyt d³uga(50max)\n";
    		jtfNazwaDzialu.setBackground(Color.RED);
    	}
    	if(nazwaDzialu.matches("^\\s*$")){
    		error+="Nazwa Dzialu zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
    		jtfNazwaDzialu.setBackground(Color.RED);
    	} 
    	if(nrKonta.length()>30){
    		error+="Nr Konta zosta³ podany zbyt d³ugi(30max)\n";
    		jtfNrKonta.setBackground(Color.RED);
    	} 
    	if(nrKonta.matches("^\\s*$")){
    		error+="Nr Konta zosta³ podany nieprawid³owy(nie mo¿e pozostaæ pusty)\n";
    		jtfNrKonta.setBackground(Color.RED);
    	}
    	if(adres.length()>50){
    		error+="Adres zosta³ podany zbyt d³ugi(50max)\n";
    		jtfAdres.setBackground(Color.RED);
    	}
    	if(adres.matches("^\\s*$")){
    		error+="Adres zosta³ podany nieprawid³owy(nie mo¿e pozostaæ pusty)\n";
    		jtfAdres.setBackground(Color.RED);
    	}
    	if(kodPocztowy.length()>6){
    		error+="Kod Pocztowy zosta³ podany zbyt d³ugi(6max)\n";
    		jtfKodPocztowy.setBackground(Color.RED);
    	}
    	else if(!kodPocztowy.matches("[0-9]{2}\\-[0-9]{3}")){
    		error+="Kod Pocztowy zosta³ podany nieprawid³owy(00-000)\n";
    		jtfKodPocztowy.setBackground(Color.RED);
    	}
    	if(poczta.length()>30){
    		error+="Poczta zosta³a podana zbyt d³uga(30max)\n";
    		jtfPoczta.setBackground(Color.RED);
    	}
    	if(poczta.matches("^\\s*$")){
    		error+="Poczta zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
    		jtfPoczta.setBackground(Color.RED);
    	}
    	return error;
    }
    private void wyczyscDaneKontaktowe(){
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
    private void documentListener(){
        jtfNazwaSkrocona.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) { check(); }
			@Override
			public void insertUpdate(DocumentEvent e) { check(); }
			@Override
			public void changedUpdate(DocumentEvent e) { check(); }

		    public void check() {
		    	if (jtfNazwaSkrocona.getText().length()>100){
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
		    	if (jtfNazwaPelna.getText().length()>100){
		    		JOptionPane.showMessageDialog(null, "Nie mo¿na wprowadzic wiêcej ni¿ 100 znaków!", "B³¹d!", JOptionPane.ERROR_MESSAGE);
		    	}
		    }
		});
    }
    private void focusListener(){
        jtfNazwaSkrocona.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		    	String nazwaSkrocona = jtfNazwaSkrocona.getText().toString();
		    	if(nazwaSkrocona.length()>100){ 
		    		jtfNazwaSkrocona.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Nazwa Skrócona zosta³a podana nieprawid³owa(100max znaków)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
//		    	if(nazwaSkrocona.matches("^\\s*$")){
//		    		jtfNazwaSkrocona.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Nazwa Skrócona zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		    	}  
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfNazwaSkrocona.selectAll();
				jtfNazwaSkrocona.setBackground(Color.WHITE);
			}
		});
        jtfNazwaPelna.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) { 
		    	String nazwaPelna = jtfNazwaPelna.getText().toString();
		    	if(nazwaPelna.length()>100){
		    		jtfNazwaPelna.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Nazwa Pe³na zosta³a podana nieprawid³owa(100max znaków)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
//		    	if(nazwaPelna.matches("^\\s*$")){
//		    		jtfNazwaPelna.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Nazwa Pe³na zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		    	} 
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfNazwaPelna.selectAll();
				jtfNazwaPelna.setBackground(Color.WHITE);
			}
		});
        jtfNip.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		    	String nip = jtfNip.getText().toString();
		    	if(nip.length()!=10){
		    		jtfNip.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "D³ugoœæ Nip'u musi byc 10(cyfr)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	} 
		    	else if(!nip.matches("[0-9]{10}")){
		    		jtfNip.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Nip mo¿e zawieraæ tylko cyfry(10)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfNip.selectAll();
				jtfNip.setBackground(Color.WHITE);
			}
		});
        jtfTelefon1.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		    	String telefon1 = jtfTelefon1.getText().toString();
		    	if(!telefon1.isEmpty() && !telefon1.matches("[0-9]{9,20}")){
		    		jtfTelefon1.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Numer Telefon1 mo¿e sk³adaæ siê tylko z cyfr(o d³ugoœci od 9 do 20)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfTelefon1.selectAll();
				jtfTelefon1.setBackground(Color.WHITE);
			}
		});
		jtfTelefon2.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		    	String telefon2 = jtfTelefon2.getText().toString();
		    	if(!telefon2.isEmpty() && !telefon2.matches("[0-9]{9,20}")){
		    		jtfTelefon2.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Numer Telefon2 mo¿e sk³adaæ siê tylko z cyfr(o d³ugoœci od 9 do 20)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfTelefon2.selectAll();
				jtfTelefon2.setBackground(Color.WHITE);
			}
		});
		jtfTelefon3.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		    	String telefon3 = jtfTelefon3.getText().toString();
		    	if(!telefon3.isEmpty() && !telefon3.matches("[0-9]{9,20}")){
		    		jtfTelefon3.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Numer Telefon3 mo¿e sk³adaæ siê tylko z cyfr(o d³ugoœci od 9 do 20)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfTelefon3.selectAll();
				jtfTelefon3.setBackground(Color.WHITE);
			}
		});
		jtfNazwaDzialu.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		    	String nazwaDzialu = jtfNazwaDzialu.getText().toString();
		    	if(nazwaDzialu.length()>50){
		    		jtfNazwaDzialu.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Nazwa Dzia³u zosta³a podana zbyt d³uga(50max)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
//		    	if(nazwaDzialu.matches("^\\s*$")){
//		    		jtfNazwaDzialu.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Nazwa Dzialu zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		    	} 
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfNazwaDzialu.selectAll();
				jtfNazwaDzialu.setBackground(Color.WHITE);
			}
		});
		jtfNrKonta.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		    	String nrKonta = jtfNrKonta.getText().toString();
		    	if(nrKonta.length()>30){
		    		jtfNrKonta.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Nr Konta zosta³ podany zbyt d³ugi(30max)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	} 
//		    	if(nrKonta.matches("^\\s*$")){
//		    		jtfNrKonta.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Nr Konta zosta³ podany nieprawid³owy(nie mo¿e pozostaæ pusty)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		    	}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfNrKonta.selectAll();
				jtfNrKonta.setBackground(Color.WHITE);
			}
		});
		jtfAdres.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		    	String adres = jtfAdres.getText().toString();
		    	if(adres.length()>50){
		    		jtfAdres.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Adres zosta³ podany zbyt d³ugi(50max)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
//		    	if(adres.matches("^\\s*$")){
//		    		jtfAdres.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Adres zosta³ podany nieprawid³owy(nie mo¿e pozostaæ pusty)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		    	}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfAdres.selectAll();
				jtfAdres.setBackground(Color.WHITE);
			}
		});
		jtfKodPocztowy.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		    	String kodPocztowy = jtfKodPocztowy.getText().toString();
		    	if(kodPocztowy.length()>6){
		    		jtfKodPocztowy.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Kod Pocztowy zosta³ podany zbyt d³ugi(6max)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
		    	else if(!kodPocztowy.matches("[0-9]{2}\\-[0-9]{3}")){
		    		jtfKodPocztowy.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Kod Pocztowy zosta³ podany nieprawid³owy(00-000)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfKodPocztowy.selectAll();
				jtfKodPocztowy.setBackground(Color.WHITE);
			}
		});
		jtfPoczta.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String poczta = jtfPoczta.getText().toString();
		    	if(poczta.length()>30){
		    		jtfPoczta.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Poczta zosta³a podana zbyt d³uga(30max)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
//		    	if(poczta.matches("^\\s*$")){
//		    		jtfPoczta.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Poczta zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		    	}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfPoczta.selectAll();
				jtfPoczta.setBackground(Color.WHITE);
			}
		});
	}
    private void keyListener(){
    	jtfNazwaPelna.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB)  {
					jtfNazwaPelna.transferFocus();
				}
			}
		});
    }
}
