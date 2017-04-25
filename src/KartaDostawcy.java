import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class KartaDostawcy extends JPanel implements ActionListener{
    String serverName = "localhost";
    String mydatabase = "magazyn";
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
    String username = "root";
    String password = "";
    
    private JButton jbtPrzycisk, jbtNowyTowar;
    private JLabel jlbTytul, jlbTytulTowary, jlbNazwaSkrocona, jlbNazwaPelna, jlbNip, jlbTelefon1, jlbTelefon2, jlbTelefon3, jlbNazwaDzialu, jlbNrKonta, jlbAdres, jlbKodPocztowy, jlbPoczta, jlbTowary, jlbCena, jlbDataOd, jlbDataDo, jlbKodWgDos, jlbNazwaWgDos;
    private JTextField jtfNip, jtfTelefon1, jtfTelefon2, jtfTelefon3, jtfNazwaDzialu, jtfNrKonta, jtfAdres, jtfKodPocztowy, jtfPoczta, jtfCena, jtfDataOd, jtfDataDo, jtfKodWgDos, jtfNazwaWgDos;
    private JTextArea jtfNazwaSkrocona, jtfNazwaPelna;
    private JTabbedPane tabbedPane;
    private JSplitPane splitPane;
    private JComboBox<String> jcbTowary;
	private String[] tab;
	private JTable tablicaTowarow;
	private JScrollPane scrollPane;
	private DefaultTableModel tablemodel;
	Polaczenie polaczenie;
    public KartaDostawcy(){     
    	setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        gbc.insets = new Insets(0, 10, 1, 10);  
        
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        tabbedPane = new JTabbedPane();
        
        JPanel panelDaneOs = new JPanel();
        panelDaneOs.setLayout(new GridBagLayout());
        panelDaneOs.setMinimumSize(new Dimension(400, 400));
        GridBagConstraints gbcPanelDaneOs = new GridBagConstraints();
        gbcPanelDaneOs.insets = new Insets(0, 10, 1, 10);  
        gbcPanelDaneOs.fill = GridBagConstraints.HORIZONTAL;

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
        GridBagConstraints gbcPanelTowary = new GridBagConstraints();
        gbcPanelTowary.insets = new Insets(0, 10, 1, 10);
        gbcPanelTowary.fill = GridBagConstraints.HORIZONTAL;  
        String[] tabNazwyKol = null;
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
        try{
			polaczenie = new Polaczenie();
			String sql = "SELECT * FROM dostawcatowar";
			ResultSet rs = polaczenie.sqlSelect(sql);
        	int rozmiarKol = rs.getMetaData().getColumnCount();
			tabNazwyKol = new String[rozmiarKol-2];
			tabNazwyKol[0] = "Nazwa Towaru";
			for(int i=1; i<rozmiarKol-2; i++){
				tabNazwyKol[i] = rs.getMetaData().getColumnName(i+3);
			}
        }catch (SQLException e) {
			e.printStackTrace();
        }
        jlbTytulTowary = new JLabel("Dodawanie towaru do Dostawcy");
        jlbTytulTowary.setFont(new Font("Calibri", Font.BOLD, 30));
        jbtNowyTowar = new JButton("Nowy Towar");
        jlbTowary = new JLabel("Nazwa towaru");
		jcbTowary = new JComboBox<String>(tab);
        jlbCena = new JLabel("Cena");
        jtfCena = new JTextField("");
        jtfCena.setMinimumSize(new Dimension(400, 20));
        jtfCena.setPreferredSize(new Dimension(400, 20));
        jlbDataOd = new JLabel("Data od");
        jtfDataOd = new JTextField("");
        jlbDataDo = new JLabel("Data do");
        jtfDataDo = new JTextField("");
        jlbKodWgDos = new JLabel("Kod towaru wed³ug dostacy");
        jtfKodWgDos = new JTextField("");
        jlbNazwaWgDos = new JLabel("Nazwa towaru wed³ug dostacy");
        jtfNazwaWgDos = new JTextField("");
        
        JPanel panelTowaryDolny = new JPanel();
        //panelTowaryDolny.setLayout(new GridLayout(0,1));
        tablemodel = new DefaultTableModel(0,0);
    	tablemodel.setColumnIdentifiers(tabNazwyKol);
    	tablicaTowarow = new JTable();
    	tablicaTowarow.getTableHeader().setReorderingAllowed(false);
    	tablicaTowarow.setModel(tablemodel);
        scrollPane = new JScrollPane(tablicaTowarow);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setMinimumSize(new Dimension(400, 200));
        
        gbcPanelDaneOs.gridx = 1; gbcPanelDaneOs.gridy = 0;
        panelDaneOs.add(jlbTytul,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx = 0; gbcPanelDaneOs.gridy++;
        panelDaneOs.add(jlbNazwaSkrocona,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx++;
        panelDaneOs.add(jtfNazwaSkrocona,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx = 0; gbcPanelDaneOs.gridy++;
        panelDaneOs.add(jlbNazwaPelna,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx++;
        panelDaneOs.add(jtfNazwaPelna,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx = 0; gbcPanelDaneOs.gridy++;
        panelDaneOs.add(jlbNip,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx++;
        panelDaneOs.add(jtfNip,gbcPanelDaneOs);

		gbcPanelDaneOs.gridx = 0; gbcPanelDaneOs.gridy++;
		panelDaneOs.add(jlbTelefon1,gbcPanelDaneOs);
		gbcPanelDaneOs.gridx++;
		panelDaneOs.add(jtfTelefon1,gbcPanelDaneOs);
		gbcPanelDaneOs.gridx = 0; gbcPanelDaneOs.gridy++;
		panelDaneOs.add(jlbTelefon2,gbcPanelDaneOs);
		gbcPanelDaneOs.gridx++;
		panelDaneOs.add(jtfTelefon2,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx = 0; gbcPanelDaneOs.gridy++;
        panelDaneOs.add(jlbTelefon3,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx++;
        panelDaneOs.add(jtfTelefon3,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx = 0; gbcPanelDaneOs.gridy++;
        panelDaneOs.add(jlbNazwaDzialu,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx++;
        panelDaneOs.add(jtfNazwaDzialu,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx = 0; gbcPanelDaneOs.gridy++;
        panelDaneOs.add(jlbNrKonta,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx++;
        panelDaneOs.add(jtfNrKonta,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx = 0; gbcPanelDaneOs.gridy++;
        panelDaneOs.add(jlbAdres,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx++;
        panelDaneOs.add(jtfAdres,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx = 0; gbcPanelDaneOs.gridy++;
        panelDaneOs.add(jlbKodPocztowy,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx++;
        panelDaneOs.add(jtfKodPocztowy,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx = 0; gbcPanelDaneOs.gridy++;
        panelDaneOs.add(jlbPoczta,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx++;
        panelDaneOs.add(jtfPoczta,gbcPanelDaneOs);
        gbcPanelDaneOs.gridx = 0; gbcPanelDaneOs.gridy++;
        tabbedPane.addTab("Dane Kontaktowe", null, panelDaneOs, "Formularz do danych kontaktowych");

        gbcPanelTowary.gridx = 1; gbcPanelTowary.gridy = 0;
        panelTowary.add(jlbTytulTowary, gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        panelTowary.add(jlbTowary, gbcPanelTowary);
        gbcPanelTowary.gridx++;
        panelTowary.add(jcbTowary, gbcPanelTowary);
        gbcPanelTowary.gridx++;
        panelTowary.add(jbtNowyTowar, gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        panelTowary.add(jlbCena, gbcPanelTowary);
        gbcPanelTowary.gridx++;
        panelTowary.add(jtfCena, gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        panelTowary.add(jlbDataOd, gbcPanelTowary);
        gbcPanelTowary.gridx++;
        panelTowary.add(jtfDataOd, gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        panelTowary.add(jlbDataDo, gbcPanelTowary);
        gbcPanelTowary.gridx++;
        panelTowary.add(jtfDataDo, gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        panelTowary.add(jlbKodWgDos, gbcPanelTowary);
        gbcPanelTowary.gridx++;
        panelTowary.add(jtfKodWgDos, gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        panelTowary.add(jlbNazwaWgDos, gbcPanelTowary);
        gbcPanelTowary.gridx++;
        panelTowary.add(jtfNazwaWgDos, gbcPanelTowary);
        tabbedPane.addTab("Towary Dostawcy", null, panelTowary, "Formularz do towarów dostawcy");
        
        JButton buttonTest = new JButton("Testowy");
        panelTowaryDolny.add(buttonTest);
		panelTowaryDolny.add(scrollPane);  

        splitPane.setTopComponent(tabbedPane);
        splitPane.setBottomComponent(panelTowaryDolny);
        
        gbc.gridx = 0; gbc.gridy = 0;
        add(splitPane,gbc);
        gbc.gridx = 0; gbc.gridy++;
        add(jbtPrzycisk,gbc);
        
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
    		dodajNowyTowar();
    	}
	}
    private void dodajNowyTowar() {
    	String[] tabPom = {jcbTowary.getSelectedItem().toString(),jtfCena.getText(),jtfDataOd.getText(),jtfDataDo.getText(),jtfKodWgDos.getText(),jtfNazwaWgDos.getText()};
    	tablemodel.addRow(tabPom);
    	tablicaTowarow.setModel(tablemodel);
    	wyczyscDaneTowaru();
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
	private boolean insertTowaryDostawcy(){
		try {
			polaczenie = new Polaczenie();
			String sql;
			String idDostawca;
			sql = "SELECT idDostawca FROM Dostawca WHERE NazwaSkrocona="+jtfNazwaSkrocona.getText().toString()+" AND NazwaPelna="+jtfNazwaPelna.getText().toString()+"";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.next();
			idDostawca = rs.getString("idDostawca");
			System.out.println(idDostawca);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
		return true;
	}
    private void kartaWalidacja() {
    	String walidacja = walidacja();
    	if(walidacja.length()>0){
    		JOptionPane.showMessageDialog(null, walidacja,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
    	}else {
	    	boolean spr = insertDostawca();
	    	if(spr == true){
//	    		boolean sprTowar = insertTowaryDostawcy();
//	    		String txt = "";
//	    		if(!sprTowar)
//	    			txt = "\nDodawanie dostawcy zakoñczone niepowodzeniem";
	    		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone powodzeniem","", JOptionPane.INFORMATION_MESSAGE);
	    		wyczyscDaneKontaktowe();
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone niepowodzeniem","Uwaga!", JOptionPane.ERROR_MESSAGE);
	    	}
    	}
	}
    private String walidacja(){
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
    	String cena = jtfCena.getText().toString();
    	String dataOd = jtfDataOd.getText().toString();
    	String dataDo = jtfDataDo.getText().toString();
    	String kodWgDos = jtfKodWgDos.getText().toString();
    	String nazwaWgDos = jtfNazwaWgDos.getText().toString();
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
    	if(cena.matches("^\\s*$")){
    		error+="Cena zosta³a podana nieprawid³owa(nie mo¿e pozostac pusta)\n";
			jtfCena.setBackground(Color.RED);
    	}
    	try{
        	Double.parseDouble(cena);
    	}catch (Exception e) {
			error+="Cena zosta³a podana nieprawid³owa(tylko liczby ,np. 20.99)\n";
			jtfCena.setBackground(Color.RED);
		}
    	if(dataOd.length()>11){
    		error+="Data zosta³a podana nieprawid³owa(max 10 znaków YYYY-MM-DD)";
    		jtfDataOd.setBackground(Color.RED);
    	}
    	try{
//    		String input = "Thu Jun 18 20:56:02 EDT 2009";
//            SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
//            Date date = parser.parse(input);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            String formattedDate = formatter.format(date);
            formatter.parse(dataOd);
    	}catch (Exception e) {
    		error+="Data zosta³a podana nieprawid³owa(np. 2017-04-25)";
    		jtfDataOd.setBackground(Color.RED);
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
    private void wyczyscDaneTowaru(){
    	jtfCena.setText("");
    	jtfDataOd.setText("");
    	jtfDataDo.setText("");
    	jtfKodWgDos.setText("");
    	jtfNazwaWgDos.setText("");
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
		    	if(nazwaSkrocona.matches("^\\s*$")){
		    		jtfNazwaSkrocona.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Nazwa Skrócona zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}  
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
		    	if(nazwaPelna.matches("^\\s*$")){
		    		jtfNazwaPelna.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Nazwa Pe³na zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	} 
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
		    	if(nazwaDzialu.matches("^\\s*$")){
		    		jtfNazwaDzialu.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Nazwa Dzialu zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	} 
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
		    	if(nrKonta.matches("^\\s*$")){
		    		jtfNrKonta.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Nr Konta zosta³ podany nieprawid³owy(nie mo¿e pozostaæ pusty)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
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
		    	if(adres.matches("^\\s*$")){
		    		jtfAdres.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Adres zosta³ podany nieprawid³owy(nie mo¿e pozostaæ pusty)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
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
		    	if(poczta.matches("^\\s*$")){
		    		jtfPoczta.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Poczta zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfPoczta.selectAll();
				jtfPoczta.setBackground(Color.WHITE);
			}
		});
		jtfCena.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		    	try{
		        	Double.parseDouble(jtfCena.getText().toString());
		    	}catch (Exception ex) {
		    		jtfCena.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Cena zosta³a podana nieprawid³owa(tylko liczby ,np. 20.99)","Uwaga!", JOptionPane.ERROR_MESSAGE);
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfCena.selectAll();
				jtfCena.setBackground(Color.WHITE);
			}
		});
		jtfDataOd.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String dataOd = jtfDataOd.getText().toString();
		    	try{
		    		if(dataOd.matches("^\\s*$")){
		    			throw new Exception("Exception thrown");
		    		}
		    		else if(dataOd.length()>11){
		    			throw new Exception("Exception thrown");
		    		}
		            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		            formatter.parse(dataOd);
		    	}catch (Exception ex) {
//		    		if(dataOd.length()>11){
		        		jtfDataOd.setBackground(Color.RED);
			    		JOptionPane.showMessageDialog(null, "Data zosta³a podana nieprawid³owa(max 10 znaków w formacie 'YYYY-MM-DD')","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		        	}
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfDataOd.selectAll();
				jtfDataOd.setBackground(Color.WHITE);
			}
		});
	}
}
/*
 * 
    	if(dataOd.length()>11){
    		error+="Data zosta³a podana nieprawid³owa(max 10 znaków YYYY-MM-DD)";
    		jtfDataOd.setBackground(Color.RED);
    	}
    	*/
