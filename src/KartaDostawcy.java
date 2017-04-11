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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
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
    
    private JButton jbtPrzycisk, jbtNowyTowar;
    private JLabel jlbTytul, jlbNazwaSkrocona, jlbNazwaPelna, jlbNip, jlbTelefon1, jlbTelefon2, jlbTelefon3, jlbNazwaDzialu, jlbNrKonta, jlbAdres, jlbKodPocztowy, jlbPoczta, jlbTowary, jlbCena, jlbDataOd, jlbDataDo, jlbKodWgDos, jlbNazwaWgDos;
    private JTextField jtfNip, jtfTelefon1, jtfTelefon2, jtfTelefon3, jtfNazwaDzialu, jtfNrKonta, jtfAdres, jtfKodPocztowy, jtfPoczta, jtfTowary, jtfCena, jtfDataOd, jtfDataDo, jtfKodWgDos, jtfNazwaWgDos;
    private JTextArea jtfNazwaSkrocona, jtfNazwaPelna;
    private JTabbedPane tabbedPane;
    private JSplitPane splitPane;
    private JComboBox<String> jcbTowary;
	private String[] tab;
	Polaczenie polaczenie;
    public KartaDostawcy(){     
    	setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        c.insets = new Insets(0, 10, 1, 10);  
        
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        JPanel panelGlDane = new JPanel();
        panelGlDane.setLayout(new GridBagLayout());
        GridBagConstraints cPanelGlDane = new GridBagConstraints();
        cPanelGlDane.insets = new Insets(0, 10, 1, 10);  
        cPanelGlDane.fill = GridBagConstraints.HORIZONTAL;
        jlbTytul = new JLabel("Dodawanie Karty Dostawcy");
        jlbTytul.setFont(new Font("Calibri", Font.BOLD, 30));
        jlbNazwaSkrocona = new JLabel("Nazwa Skrócona");
        jtfNazwaSkrocona = new JTextArea();
        jtfNazwaSkrocona.setPreferredSize(new Dimension(400, 40));
        jtfNazwaSkrocona.setLineWrap(true);
        jtfNazwaSkrocona.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 1, 0, 0)));        
        jlbNazwaSkrocona.setToolTipText(jlbNazwaSkrocona.getText()+" : maksymalna d³ugoœæ to 100 znaków");
        jtfNazwaSkrocona.setToolTipText(jlbNazwaSkrocona.getText()+" : maksymalna d³ugoœæ to 100 znaków");
        jlbNazwaPelna = new JLabel("Nazwa Pe³na");
        jtfNazwaPelna = new JTextArea();
        jtfNazwaPelna.setPreferredSize(new Dimension(400, 40));
        jtfNazwaPelna.setLineWrap(true);
        jtfNazwaPelna.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 1, 0, 0)));
        jlbNazwaPelna.setToolTipText(jlbNazwaPelna.getText()+" : maksymalna d³ugoœæ to 100 znaków");
        jtfNazwaPelna.setToolTipText(jlbNazwaPelna.getText()+" : maksymalna d³ugoœæ to 100 znaków");
        jlbNip = new JLabel("NIP");
        jtfNip = new JTextField("",36);
        jlbNip.setToolTipText(jlbNip.getText()+" : maksymalna d³ugoœæ to 10 znaków");
        jtfNip.setToolTipText(jlbNip.getText()+" : maksymalna d³ugoœæ to 10 znaków");
        
        JPanel panelDaneOs = new JPanel();
        panelDaneOs.setLayout(new GridBagLayout());
        GridBagConstraints cPanelDaneOs = new GridBagConstraints();
        cPanelDaneOs.insets = new Insets(0, 10, 1, 10);  
        cPanelDaneOs.fill = GridBagConstraints.HORIZONTAL;
        tabbedPane = new JTabbedPane();
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
        jbtPrzycisk.setMaximumSize(new Dimension(100, 25));
        jbtPrzycisk.setPreferredSize(new Dimension(100, 25));
                
        JPanel panelTowary = new JPanel();
        panelTowary.setLayout(new GridBagLayout());
        GridBagConstraints cPanelTowary = new GridBagConstraints();
        cPanelTowary.insets = new Insets(0, 10, 1, 10);
        cPanelTowary.fill = GridBagConstraints.HORIZONTAL;  
        try {
			polaczenie = new Polaczenie();
			String sql = "SELECT * FROM towar";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tab = new String[rozmiar];
			while(rs.next()){
				tab[i] = rs.getString("NazwaTowaru");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        jbtNowyTowar = new JButton("Nowy Towar");
        jlbTowary = new JLabel("Nazwa towaru");
		jcbTowary = new JComboBox<String>(tab);
		jtfTowary = new JTextField();
		jtfTowary.setVisible(false);
        jlbCena = new JLabel("Cena");
        jtfCena = new JTextField("");
        jtfCena.setMinimumSize(new Dimension(400, 20));
        jtfCena.setPreferredSize(new Dimension(400, 20));
        jlbDataOd = new JLabel("Dana od");
        jtfDataOd = new JTextField("");
        jlbDataDo = new JLabel("Dana do");
        jtfDataDo = new JTextField("");
        jlbKodWgDos = new JLabel("Kod towaru wed³ug dostacy");
        jtfKodWgDos = new JTextField("");
        jlbNazwaWgDos = new JLabel("Nazwa towaru wed³ug dostacy");
        jtfNazwaWgDos = new JTextField("");        
        
        cPanelGlDane.gridx = 1; cPanelGlDane.gridy = 0;
        panelGlDane.add(jlbTytul,cPanelGlDane);
        cPanelGlDane.gridx = 0; cPanelGlDane.gridy++;
        panelGlDane.add(jlbNazwaSkrocona,cPanelGlDane);
        cPanelGlDane.gridx++;
        panelGlDane.add(jtfNazwaSkrocona,cPanelGlDane);
        cPanelGlDane.gridx = 0; cPanelGlDane.gridy++;
        panelGlDane.add(jlbNazwaPelna,cPanelGlDane);
        cPanelGlDane.gridx++;
        panelGlDane.add(jtfNazwaPelna,cPanelGlDane);
        cPanelGlDane.gridx = 0; cPanelGlDane.gridy++;
        panelGlDane.add(jlbNip,cPanelGlDane);
        cPanelGlDane.gridx++;
        panelGlDane.add(jtfNip,cPanelGlDane);
        splitPane.setTopComponent(panelGlDane);

		cPanelDaneOs.gridx = 0; cPanelDaneOs.gridy = 0;
		panelDaneOs.add(jlbTelefon1,cPanelDaneOs);
		cPanelDaneOs.gridx++;
		panelDaneOs.add(jtfTelefon1,cPanelDaneOs);
		cPanelDaneOs.gridx = 0; cPanelDaneOs.gridy++;
		panelDaneOs.add(jlbTelefon2,cPanelDaneOs);
		cPanelDaneOs.gridx++;
		panelDaneOs.add(jtfTelefon2,cPanelDaneOs);
        cPanelDaneOs.gridx = 0; cPanelDaneOs.gridy++;
        panelDaneOs.add(jlbTelefon3,cPanelDaneOs);
        cPanelDaneOs.gridx++;
        panelDaneOs.add(jtfTelefon3,cPanelDaneOs);
        cPanelDaneOs.gridx = 0; cPanelDaneOs.gridy++;
        panelDaneOs.add(jlbNazwaDzialu,cPanelDaneOs);
        cPanelDaneOs.gridx++;
        panelDaneOs.add(jtfNazwaDzialu,cPanelDaneOs);
        cPanelDaneOs.gridx = 0; cPanelDaneOs.gridy++;
        panelDaneOs.add(jlbNrKonta,cPanelDaneOs);
        cPanelDaneOs.gridx++;
        panelDaneOs.add(jtfNrKonta,cPanelDaneOs);
        cPanelDaneOs.gridx = 0; cPanelDaneOs.gridy++;
        panelDaneOs.add(jlbAdres,cPanelDaneOs);
        cPanelDaneOs.gridx++;
        panelDaneOs.add(jtfAdres,cPanelDaneOs);
        cPanelDaneOs.gridx = 0; cPanelDaneOs.gridy++;
        panelDaneOs.add(jlbKodPocztowy,cPanelDaneOs);
        cPanelDaneOs.gridx++;
        panelDaneOs.add(jtfKodPocztowy,cPanelDaneOs);
        cPanelDaneOs.gridx = 0; cPanelDaneOs.gridy++;
        panelDaneOs.add(jlbPoczta,cPanelDaneOs);
        cPanelDaneOs.gridx++;
        panelDaneOs.add(jtfPoczta,cPanelDaneOs);
        cPanelDaneOs.gridx = 0; cPanelDaneOs.gridy++;
        tabbedPane.addTab("Dane Kontaktowe", null, panelDaneOs, "Formularz do danych kontaktowych");
        
        cPanelTowary.gridx = 0; cPanelTowary.gridy = 0;
        panelTowary.add(jlbTowary, cPanelTowary);
        cPanelTowary.gridx++;
        panelTowary.add(jcbTowary, cPanelTowary);
        panelTowary.add(jtfTowary, cPanelTowary);
        cPanelTowary.gridx++;
        panelTowary.add(jbtNowyTowar, cPanelTowary);
        cPanelTowary.gridx = 0; cPanelTowary.gridy++;
        panelTowary.add(jlbCena, cPanelTowary);
        cPanelTowary.gridx++;
        panelTowary.add(jtfCena, cPanelTowary);
        cPanelTowary.gridx = 0; cPanelTowary.gridy++;
        panelTowary.add(jlbDataOd, cPanelTowary);
        cPanelTowary.gridx++;
        panelTowary.add(jtfDataOd, cPanelTowary);
        cPanelTowary.gridx = 0; cPanelTowary.gridy++;
        panelTowary.add(jlbDataDo, cPanelTowary);
        cPanelTowary.gridx++;
        panelTowary.add(jtfDataDo, cPanelTowary);
        cPanelTowary.gridx = 0; cPanelTowary.gridy++;
        panelTowary.add(jlbKodWgDos, cPanelTowary);
        cPanelTowary.gridx++;
        panelTowary.add(jtfKodWgDos, cPanelTowary);
        cPanelTowary.gridx = 0; cPanelTowary.gridy++;
        panelTowary.add(jlbNazwaWgDos, cPanelTowary);
        cPanelTowary.gridx++;
        panelTowary.add(jtfNazwaWgDos, cPanelTowary);
        tabbedPane.addTab("Towary Dostawcy", null, panelTowary, "Formularz do towarów dostawcy");
        splitPane.setBottomComponent(tabbedPane);
        
        c.gridx = 0; c.gridy = 0;
        add(splitPane,c);
        c.gridx = 0; c.gridy++;
        add(jbtPrzycisk,c);
        
        ustawNasluchZdarzen();
        documentListener();
        focusListener();
    }
	private void ustawNasluchZdarzen(){
		jbtPrzycisk.addActionListener(this);
		jbtNowyTowar.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
        Object z= e.getSource();
    	if(z==jbtPrzycisk){
    		kartaWalidacja();
    	}else if(z==jbtNowyTowar){
    		if(jbtNowyTowar.getText()=="Nowy Towar"){
    			jbtNowyTowar.setText("Towar z listy");
    			jcbTowary.setVisible(false);
    			jtfTowary.setVisible(true);
    		}
    		else if(jbtNowyTowar.getText()=="Towar z listy"){
    			jbtNowyTowar.setText("Nowy Towar");
    			jcbTowary.setVisible(true);
    			jtfTowary.setVisible(false);
    		}
    	}
	}
    private boolean insert(String NazwaSkrocona, String NazwaPelna, String Nip, String Telefon1, String Telefon2, String Telefon3, String NazwaDzialu, String NrKonta, String Adres, String KodPocztowy, String Poczta){
		try{
			Connection connection = DriverManager.getConnection(url, username, password);
			connection.createStatement();
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
    		JOptionPane.showMessageDialog(null, walidacja,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
    	}else {
	    	boolean spr = insert(NazwaSkrocona, NazwaPelna, Nip, Telefon1, Telefon2, Telefon3, NazwaDzialu, NrKonta, Adres, KodPocztowy, Poczta);
	    	//JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	    	if(spr == true){
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
