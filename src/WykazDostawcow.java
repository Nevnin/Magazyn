import java.awt.Color;
import java.awt.Dialog.ModalityType;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class WykazDostawcow extends JPanel implements ListSelectionListener, KeyListener, ActionListener{
	private Polaczenie polaczenie;
	private JList<String> list;
	private String[] tab, tabTowary;
	private JSplitPane splitPane, spDaneButt, spListDane;
	private JScrollPane scrollPane,scPaneTabTow;
	private JLabel jlbNazSkroc, jlbNazPeln,jlbNIP, jlbTel1, jlbTel2,jlbTel3,jlbNazDzial,jlbNrKonta,jlbMiejsc, jlbAdres,jlbKodPocz,jlbPoczta, jlbKodWgDos, jlbNazwaWgDos, jlbDataDo, jlbDataOd, jlbCena, jlbTowary, jlbTytulTowary;
	private JTextField search,jtfNazwaSkrocona,jtfNIP,jtfTelefon1,jtfTelefon2,jtfTelefon3,jtfNazwaDzialu,jtfNrKonta, jtfMiejsc, jtfAdres,jtfKodPocztowy,jtfPoczta, jtfKodWgDos, jtfNazwaWgDos, jtfDataDo, jtfDataOd, jtfCena;
	private JTextArea jtaNazwaPelna;
	private JButton jbtDodajDos, jbtDodajTow, jbtNowyTowar, jbtZakonczTowar, jbtAnuluj;
	private JComboBox<String> jcbTowary;
	private JPanel pTowary;
	private boolean edycjaListy;
	private DefaultTableModel tableModel;
	private JTable tablicaTowarow;
	private JDialog dialog;
    String serverName = "localhost";
    String mydatabase = "magazyn";
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
    String username = "root";
    String password = "";
    
	public WykazDostawcow() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		p.setPreferredSize(new Dimension(600, 300));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 0, 10);
		
		JPanel pButtons = new JPanel();
		
        pTowary = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelTowary = new GridBagConstraints();
        gbcPanelTowary.insets = new Insets(0, 10, 1, 10);
        gbcPanelTowary.fill = GridBagConstraints.HORIZONTAL;  
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);							//panel + spMigRig
		spListDane = new JSplitPane();							//spDaneButt + pTowary
		spDaneButt = new JSplitPane(JSplitPane.VERTICAL_SPLIT);	//p + pButtons
		
		String[] tabNazwyKol = getNazwyKol();
		tabTowary = getTowary();
		tab = listaDostawcow();

	    JPanel panelTowaryDolny = new JPanel();
	    panelTowaryDolny.setLayout(new BoxLayout(panelTowaryDolny, BoxLayout.Y_AXIS));
	    tableModel = new DefaultTableModel(0,0);
		tableModel.setColumnIdentifiers(tabNazwyKol);
		tablicaTowarow = new JTable();
		tablicaTowarow.getTableHeader().setReorderingAllowed(false);
		tablicaTowarow.setModel(tableModel);
		tablicaTowarow.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int[] tabKolSzer = {155,175,175,155,105};
		System.out.println(tabNazwyKol.length);
		for(int i=0; i<tabNazwyKol.length; i++){
			tablicaTowarow.getColumnModel().getColumn(i).setPreferredWidth(tabKolSzer[i]);
				DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
			if(i==4){
				tableRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
				tablicaTowarow.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
			}
//			else if(i==2 || i ==3){
//				tableRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//				tablicaTowarow.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
//			}
			else{
				tableRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				tablicaTowarow.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
			}
		}
	    scrollPane = new JScrollPane(tablicaTowarow);
	    scrollPane.setPreferredSize(new Dimension(400, 200));
	    scrollPane.setMinimumSize(new Dimension(400, 200));
		
		scrollPane = new JScrollPane();
		scPaneTabTow = new JScrollPane(tablicaTowarow);
		search = new JTextField();
		list = new JList<String>(tab);
		list.setMinimumSize(new Dimension(150,150));
		list.setPreferredSize(new Dimension(150, 150));
		list.setAlignmentX(CENTER_ALIGNMENT);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		search.setMaximumSize(new Dimension(200, 20));
		edycjaListy = true;
		
		Border border = BorderFactory.createLineBorder(Color.lightGray);
		jlbNazSkroc = new JLabel("Nazwa skrocona:");
		jtfNazwaSkrocona = new JTextField();
		jtfNazwaSkrocona.setEditable(false);
		jlbNazPeln = new JLabel("Nazwa pelna:");
		jtaNazwaPelna = new JTextArea();
		jtaNazwaPelna.setEditable(false);
		jtaNazwaPelna.setPreferredSize(new Dimension(400, 40));
		jtaNazwaPelna.setLineWrap(true);
		jtaNazwaPelna.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 2, 0, 0)));
		jtaNazwaPelna.setBackground(null);
		jlbNIP = new JLabel("NIP:");
		jtfNIP = new JTextField();
		jtfNIP.setEditable(false);
		jlbTel1 = new JLabel("Telefon 1:");
		jtfTelefon1 = new JTextField();
		jtfTelefon1.setEditable(false);
		jlbTel2 = new JLabel("Telefon 2:");
		jtfTelefon2 = new JTextField();
		jtfTelefon2.setEditable(false);
		jlbTel3 = new JLabel("Telefon 3:");
		jtfTelefon3 = new JTextField();
		jtfTelefon3.setEditable(false);
		jlbNazDzial = new JLabel("Nazwa dzialu:");
		jtfNazwaDzialu = new JTextField();
		jtfNazwaDzialu.setEditable(false);
		jlbNrKonta = new JLabel("Numer konta:");
		jtfNrKonta = new JTextField();
		jtfNrKonta.setEditable(false);
		jlbMiejsc = new JLabel("Miejscowoœæ:");
		jtfMiejsc = new JTextField();
		jtfMiejsc.setEditable(false);
		jlbAdres = new JLabel("Adres:");
		jtfAdres = new JTextField();
		jtfAdres.setEditable(false);
		jlbKodPocz = new JLabel("Kod pocztowy:");
		jtfKodPocztowy = new JTextField();
		jtfKodPocztowy.setEditable(false);
		jlbPoczta = new JLabel("Poczta:");
		jtfPoczta = new JTextField();
		jtfPoczta.setEditable(false);
        jbtDodajDos= new JButton("Dodaj nowego dostawce");
        jbtDodajDos.setName("addDos");
//        jbtDodajDos.setEnabled(false);
        jbtAnuluj = new JButton("Anuluj dodawanie");
        jbtAnuluj.setVisible(false);
        jbtDodajTow= new JButton("Dodaj towary do dostawcy");
        jbtDodajTow.setEnabled(false);
        jbtDodajTow.setName("addTowary");
        
        jlbTytulTowary = new JLabel("Dodawanie towaru Dostawcy");
        jlbTytulTowary.setFont(new Font("Calibri", Font.BOLD, 30));
        jbtNowyTowar = new JButton("Nowy towar");
        jbtZakonczTowar = new JButton("Zakoncz");
//        jbtNowyTowar.setSize(new Dimension(10, 10));
        jlbTowary = new JLabel("Nazwa towaru");
		jcbTowary = new JComboBox<String>(tabTowary);
        jlbCena = new JLabel("Cena");
        jtfCena = new JTextField("");
        jtfCena.setMinimumSize(new Dimension(400, 20));
        jtfCena.setPreferredSize(new Dimension(400, 20));
        jlbDataOd = new JLabel("Data od");
        jtfDataOd = new JTextField("");
        jlbDataDo = new JLabel("Data do");
        jtfDataDo = new JTextField("");
        jlbKodWgDos = new JLabel("Kod towaru wed³ug dostawcy");
        jtfKodWgDos = new JTextField("");
        jlbNazwaWgDos = new JLabel("Nazwa towaru wed³ug dostawcy");
        jtfNazwaWgDos = new JTextField("");

		panel.add(search);
		panel.add(scrollPane);
		
		c.gridx = 0; c.gridy = 0;
        p.add(jlbNazSkroc,c);
        c.gridx++;
        p.add(jtfNazwaSkrocona,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNazPeln,c);
        c.gridx++;
        p.add(jtaNazwaPelna,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNIP,c);
        c.gridx++;
        p.add(jtfNIP,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTel1,c);
        c.gridx++;
        p.add(jtfTelefon1,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTel2,c);
        c.gridx++;
        p.add(jtfTelefon2,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTel3,c);
        c.gridx++;
        p.add(jtfTelefon3,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNazDzial,c);
        c.gridx++;
        p.add(jtfNazwaDzialu,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNrKonta,c);
        c.gridx++;
        p.add(jtfNrKonta,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbMiejsc,c);
        c.gridx++;
        p.add(jtfMiejsc,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbAdres,c);
        c.gridx++;
        p.add(jtfAdres,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbKodPocz,c);
        c.gridx++;
        p.add(jtfKodPocztowy,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbPoczta,c);
        c.gridx++;
        p.add(jtfPoczta, c);

        pButtons.add(jbtAnuluj);
        pButtons.add(jbtDodajDos);
        pButtons.add(jbtDodajTow);
        
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy = 0;
        gbcPanelTowary.fill = GridBagConstraints.CENTER;
        gbcPanelTowary.gridwidth = 2;
        pTowary.add(jlbTytulTowary,gbcPanelTowary);
        gbcPanelTowary.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelTowary.gridwidth = 1;
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        pTowary.add(jlbTowary,gbcPanelTowary);
        gbcPanelTowary.gridx++;
        pTowary.add(jcbTowary,gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        pTowary.add(jlbCena,gbcPanelTowary);
        gbcPanelTowary.gridx++;
        pTowary.add(jtfCena,gbcPanelTowary);
//        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
//        pTowary.add(jlbDataOd,gbcPanelTowary);
//        gbcPanelTowary.gridx++;
//        pTowary.add(jtfDataOd,gbcPanelTowary);
//        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
//        pTowary.add(jlbDataDo,gbcPanelTowary);
//        gbcPanelTowary.gridx++;
//        pTowary.add(jtfDataDo,gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        pTowary.add(jlbKodWgDos,gbcPanelTowary);
        gbcPanelTowary.gridx++;
        pTowary.add(jtfKodWgDos,gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        pTowary.add(jlbNazwaWgDos,gbcPanelTowary);
        gbcPanelTowary.gridx++;
        pTowary.add(jtfNazwaWgDos,gbcPanelTowary);
//        gbcPanelTowary = new GridBagConstraints(); 
//        gbcPanelTowary.fill = GridBagConstraints.NONE;
//        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
//        pTowary.add(jbtNowyTowar,gbcPanelTowary);
//        gbcPanelTowary.anchor = GridBagConstraints.EAST;
//        gbcPanelTowary.gridx++;
//        pTowary.add(jbtZakonczTowar,gbcPanelTowary);

        spDaneButt.setTopComponent(p);
        spDaneButt.setBottomComponent(pButtons);
        
        spListDane.setLeftComponent(panel);
        spListDane.setRightComponent(spDaneButt);
        
        
		splitPane.setRightComponent(scPaneTabTow);
        splitPane.setLeftComponent(spListDane);
        add(splitPane);
        
        ustawNasluchZdarzen();
        documentListener();
        focusListener();
        keyListener();
        focusable(false);
	}
	private void ustawNasluchZdarzen(){
		list.addListSelectionListener(this);
		search.addKeyListener(this);
		jbtDodajDos.addActionListener(this);
		jbtDodajTow.addActionListener(this);
		jbtNowyTowar.addActionListener(this);
		jbtZakonczTowar.addActionListener(this);
		jbtAnuluj.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object e = arg0.getSource();
		if(e==jbtDodajTow){
			dialogTowary();
		}
		else if(e==jbtDodajDos){
			if(jbtDodajDos.getName() == "addDos"){
				jbtDodajDos.setText("Zakoncz dodawanie dostawcy");
				jbtDodajDos.setName("endDos");
				jbtDodajTow.setEnabled(false);
				jbtAnuluj.setVisible(true);
				jtaNazwaPelna.setBackground(Color.WHITE);
				edycjaDanychDos(true);
				edycjaListy = false;
				tableModel.setRowCount(0);
				wyczyscDaneKontaktowe();
				focusable(true);
			} else if(jbtDodajDos.getName() == "endDos"){
				String walidacja = walidacjaDanychDostawcy();
		    	if(walidacja.length()>0){
		    		JOptionPane.showMessageDialog(null, walidacja,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
		    	}else {
			    	boolean spr = insertDostawca();
			    	if(spr == true){
			    		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone powodzeniem","", JOptionPane.INFORMATION_MESSAGE);
						jbtDodajDos.setText("Dodaj nowego dostawce");
						jbtDodajDos.setName("addDos");
						jbtDodajTow.setEnabled(true);
						jbtAnuluj.setVisible(false);
						jtaNazwaPelna.setBackground(null);
						edycjaDanychDos(false);
						edycjaListy = true;
						kolorDanychDos();
						DefaultListModel<String> listModel = new DefaultListModel<String>();
				        listModel.removeAllElements();
				        String[] tabPom = listaDostawcow();
				        for (int i = 0; i < tabPom.length; i++) {
							listModel.add(i, tabPom[i]);
						}list.setModel(listModel);
						focusable(false);
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone niepowodzeniem","Uwaga!", JOptionPane.ERROR_MESSAGE);
			    	}
		    	}
			}
		}
		else if(e==jbtAnuluj){
			jbtDodajDos.setText("Dodaj nowego dostawce");
			jbtDodajDos.setName("addDos");
	        jbtDodajTow.setEnabled(false);
			jbtAnuluj.setVisible(false);
			jtaNazwaPelna.setBackground(null);
			edycjaDanychDos(false);
			edycjaListy = true;
			wyczyscDaneKontaktowe();
			kolorDanychDos();
			focusable(false);
		}
		else if(e==jbtNowyTowar){
			String walidacjaTowaru = walidacjaTowaru();
			if(walidacjaTowaru.length()>0){
	    		JOptionPane.showMessageDialog(null, walidacjaTowaru,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				insertTowaryDostawcy();
//				dodajNowyTowar();
				wyczyscDaneTowaru();
			}
		}
		else if(e==jbtZakonczTowar){
			dialog.dispose();
		}
	}
	/**
	 * Wykonuje zapytanie(SELECT) do bazy danych na tabelê 'dostawcy'
	 * <b>listaDostawcow()</b> zwraca posortowane nazwy skrócone dostawcow 
	 * @return String[] - tablica
	 */
	private String[] listaDostawcow() {
		String[] tabPom;
		try {
			polaczenie = new Polaczenie();
			String sql = "SELECT * FROM dostawca ORDER BY NazwaSkrocona";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tabPom = new String [rozmiar];
			while(rs.next()) {
				tabPom[i] = rs.getString("NazwaSkrocona");
//				System.out.println(rs.getString("NazwaSkrocona"));
				i++;
			}
		} catch (SQLException e) {
			return null;
		}
		return tabPom;
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(edycjaListy){
			if(arg0.getValueIsAdjusting()) {
				String[] tabPom = null;
				String sel = list.getSelectedValue().toString();
				String sql = "SELECT NazwaSkrocona, NazwaPelna, NIP, Telefon1, Telefon2, Telefon3, NazwaDzialu, NrKonta, Adres, KodPocztowy, Poczta, idDostawca  FROM dostawca WHERE NazwaSkrocona='"+sel+"'";
				//int id = 0;
				//String sql = "SELECT NumerZamowienia, TerminRealizacji, DataRealizacji, DataWystawienia, SposobDostawy, KosztDostawy,WartoscTowarow, CalkowitaWartoscZamowienia FROM zamowienie WHERE NumerZamowienia='"+sel+"'";
				
				try {
					ResultSet rs = polaczenie.sqlSelect(sql);
					tabPom = new String[12];
					
					rs.next();
					for(int i = 0;i<tabPom.length;i++)
					{
						tabPom[i]=rs.getString(i+1);
					}
					jtfNazwaSkrocona.setText(tabPom[0]);
					jtaNazwaPelna.setText(tabPom[1]);
					jtfNIP.setText(tabPom[2]);
					jtfTelefon1.setText(tabPom[3]);
					jtfTelefon2.setText(tabPom[4]);
					jtfTelefon3.setText(tabPom[5]);
					jtfNazwaDzialu.setText(tabPom[6]);
					jtfNrKonta.setText(tabPom[7]);
					jtfAdres.setText(tabPom[8]);
					jtfKodPocztowy.setText(tabPom[9]);
					jtfPoczta.setText(tabPom[10]);
					jbtDodajTow.setEnabled(true);
		
				} catch (Exception e) {
					// TODO: handle exception
				}
				sql = "SELECT NazwaTowaru,KodTowaru,Cena,KodTowaruWgDostawcy,NazwaTowaruWgDostawcy FROM dostawcatowar "
						+ "INNER JOIN towar ON towar.IdTowar=dostawcatowar.IdTowar "
						+ "WHERE IdDostawca = '"+tabPom[11]+"' ORDER BY Cena DESC"
								+ "";
//				System.out.println(sql);
				try {
					ResultSet rs = polaczenie.sqlSelect(sql);
					rs.last();
					int rozmiar = rs.getRow();
					//tabPom = new String[rs.getRow()];
					rs.beforeFirst();
					rs.next();
//					tableModel = new DefaultTableModel(0,0);
//					tableModel.setColumnIdentifiers(getNazwyKol());
					tableModel.setRowCount(0);
					tablicaTowarow.setModel(tableModel);					
					//jtfTelefon3.setText("123");
					
					for(int i=0; i<rozmiar; i++){
						double cenaD = Double.parseDouble(rs.getString("Cena"));
						DecimalFormat df = new DecimalFormat("###,###.00z³");
						System.out.println(rs.getString("Cena"));
						String[] tabPom2 = {rs.getString("NazwaTowaru"),rs.getString("KodTowaru"),rs.getString("KodTowaruWgDostawcy"),rs.getString("NazwaTowaruWgDostawcy"),""+df.format(cenaD)};
						tableModel.addRow(tabPom2);
						rs.next();
					}
				} catch (Exception e) {
					// TODO: handle exception
//					System.out.println("error");
				}
			}
		}
	}
	/**
	 * Okno wyszukiwania nad list¹.
	 * Po wprowadzeniu tekstu, wykonywanie jest zapytanie do bazy. Lista wyœwietla nazwy skrócone dostawców posuj¹ce do wzorca.
	 * @param text - wzorzec(string), jesli zostaniu pusty, lista powinna wyswietlic wszystkich dostawcow
	 */
	public void szukaj(String text){
		try {
			polaczenie = new Polaczenie();
			String sql = "SELECT NazwaSkrocona FROM dostawca WHERE NazwaSkrocona LIKE '%"+text+"%'";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tab = new String[rozmiar];
			while(rs.next()){
				tab[i] = rs.getString("NazwaSkrocona");
				i++;
			}
			//list.clearSelection();
			list.setListData(tab);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String[] getTowary(){
		String[] tabPom = null;
		try {
			polaczenie = new Polaczenie();
			String sql = "SELECT * FROM towar";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tabPom = new String[rozmiar];
			while(rs.next()){
				tabPom[i] = rs.getString("NazwaTowaru");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tabPom;
	}
	public String[] getNazwyKol(){
		String[] tabPom = null;
//	    try{
//	    	polaczenie = new Polaczenie();
//			String sql = "SELECT * FROM dostawcatowar";
//			ResultSet rs = polaczenie.sqlSelect(sql);
//			int rozmiarKol = rs.getMetaData().getColumnCount();
			tabPom = new String[5];
			tabPom[0] = "Nazwa towaru";
			tabPom[1] = "Kod towaru";
//			tabPom[2] = "Data Od";
//			tabPom[3] = "Data Do";
			tabPom[2] = "Kod towaru wed³ug dostawcy";
			tabPom[3] = "Nazwa towaru wed³ug dostawcy";
			tabPom[4] = "Cena";
	//		
	//		for(int i=1; i<rozmiarKol-2; i++){
	//			tabNazwyKol[i] = rs.getMetaData().getColumnName(i+3);
	//		}
//	      }catch (SQLException e) {
//				e.printStackTrace();
//	    }
		return tabPom;
	}
	@Override
	public void keyPressed(KeyEvent arg0) { }
	@Override
	public void keyReleased(KeyEvent arg0) { szukaj(search.getText()); }
	@Override
	public void keyTyped(KeyEvent arg0) { }

    private void wyczyscDaneKontaktowe(){
		jtfNazwaSkrocona.setText("");
		jtaNazwaPelna.setText("");
		jtfNIP.setText("");
		jtfTelefon1.setText("");
		jtfTelefon2.setText("");
		jtfTelefon3.setText("");
		jtfNazwaDzialu.setText("");
		jtfNrKonta.setText("");
		jtfMiejsc.setText("");
		jtfAdres.setText("");
		jtfKodPocztowy.setText("");
		jtfPoczta.setText("");
    }
    private void edycjaDanychDos(boolean flag){
		jtfNazwaSkrocona.setEditable(flag);
		jtaNazwaPelna.setEditable(flag);
		jtfNIP.setEditable(flag);
		jtfTelefon1.setEditable(flag);
		jtfTelefon2.setEditable(flag);
		jtfTelefon3.setEditable(flag);
		jtfNazwaDzialu.setEditable(flag);
		jtfNrKonta.setEditable(flag);
		jtfMiejsc.setEditable(flag);
		jtfAdres.setEditable(flag);
		jtfKodPocztowy.setEditable(flag);
		jtfPoczta.setEditable(flag);
    }
	private void kolorDanychDos(){
		jtfNazwaSkrocona.setBackground(null);
		jtaNazwaPelna.setBackground(null);
		jtfNIP.setBackground(null);
		jtfTelefon1.setBackground(null);
		jtfTelefon2.setBackground(null);
		jtfTelefon3.setBackground(null);
		jtfNazwaDzialu.setBackground(null);
		jtfNrKonta.setBackground(null);
		jtfMiejsc.setBackground(null);
		jtfAdres.setBackground(null);
		jtfKodPocztowy.setBackground(null);
		jtfPoczta.setBackground(null);
	}
	private void focusable(boolean flag){
    	jtfNazwaSkrocona.setFocusable(flag);
		jtaNazwaPelna.setFocusable(flag);
		jtfNIP.setFocusable(flag);
		jtfTelefon1.setFocusable(flag);
		jtfTelefon2.setFocusable(flag);
		jtfTelefon3.setFocusable(flag);
		jtfNazwaDzialu.setFocusable(flag);
		jtfNrKonta.setFocusable(flag);
		jtfMiejsc.setFocusable(flag);
		jtfAdres.setFocusable(flag);
		jtfKodPocztowy.setFocusable(flag);
		jtfPoczta.setFocusable(flag);
	}
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//        Object z= arg0.getSource();
//		if(z==jbtNowyTowar){
//				String walidacjaTowaru = walidacjaTowaru();
//				if(walidacjaTowaru.length()>0)
//		    		JOptionPane.showMessageDialog(null, walidacjaTowaru,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
//			else 
//				dodajNowyTowar();
//		}
//		
//	}
//
//	private void ustawNasluchZdarzen(){
//		jbtNowyTowar.addActionListener(this);
//	}
//    private void kartaWalidacja() {
//    	//insertTowaryDostawcy();
//    	String walidacja = walidacjaDanychDostawcy();
//    	if(walidacja.length()>0){
//    		JOptionPane.showMessageDialog(null, walidacja,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
//    	}else {
//	    	boolean spr = insertDostawca();
//	    	if(spr == true){
//	    		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone powodzeniem","", JOptionPane.INFORMATION_MESSAGE);
//	    		wyczyscDaneKontaktowe();
//	    	}
//	    	else {
//	    		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone niepowodzeniem","Uwaga!", JOptionPane.ERROR_MESSAGE);
//	    	}
//    	}
//	}
    private boolean insertDostawca(){
		String nazwaSkrocona = jtfNazwaSkrocona.getText().toString();
		String nazwaPelna = jtaNazwaPelna.getText().toString();
		String nip = jtfNIP.getText().toString();
		String telefon1 = jtfTelefon1.getText().toString();
		String telefon2 = jtfTelefon2.getText().toString();
		String telefon3 = jtfTelefon3.getText().toString();
		String nazwaDzialu = jtfNazwaDzialu.getText().toString();
		String nrKonta = jtfNrKonta.getText().toString();
		String miejsc = jtfMiejsc.getText().toString();
		String adres = jtfAdres.getText().toString();
		String kodPocztowy = jtfKodPocztowy.getText().toString();
		String poczta  = jtfPoczta.getText().toString();
		try{
			Connection connection = DriverManager.getConnection(url, username, password);
			connection.createStatement();
			String query = "INSERT INTO dostawca "
				+ "(NazwaSkrocona, NazwaPelna, NIP, Telefon1, Telefon2, Telefon3, NazwaDzialu, NrKonta, Miejscowosc, Adres, KodPocztowy, Poczta)"
			    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
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
			preparedStmt.setString (9, miejsc);
			preparedStmt.setString (10, adres);
			preparedStmt.setString (11, kodPocztowy);
			preparedStmt.setString (12, poczta);
			
			// execute the preparedstatement
			preparedStmt.execute();
			connection.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
    private String walidacjaDanychDostawcy(){
    	String error="";
    	String nazwaSkrocona = jtfNazwaSkrocona.getText().toString();
    	String nazwaPelna = jtaNazwaPelna.getText().toString();
    	String nip = jtfNIP.getText().toString();
    	String telefon1 = jtfTelefon1.getText().toString();
    	String telefon2 = jtfTelefon2.getText().toString();
    	String telefon3 = jtfTelefon3.getText().toString();
    	String nazwaDzialu = jtfNazwaDzialu.getText().toString();
    	String nrKonta = jtfNrKonta.getText().toString();
    	String miejsc = jtfMiejsc.getText().toString();
    	String adres = jtfAdres.getText().toString();
    	String kodPocztowy = jtfKodPocztowy.getText().toString();
    	String poczta  = jtfPoczta.getText().toString();
    	String[][] tabPom = null;
    	boolean czyDosJest = false;
    	try {
			Polaczenie polacz = new Polaczenie();
	    	String sql = "SELECT * FROM dostawca WHERE NazwaSkrocona='"+nazwaSkrocona+"' AND NazwaPelna='"+nazwaPelna+"' AND NazwaDzialu='"+nazwaDzialu+"'";
//	    	System.out.println(sql);
			ResultSet rs = polacz.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			if(rozmiar>0){
				czyDosJest = true;
				rs.first();
				tabPom = new String[rozmiar][13];
				for(int i=0; i<rozmiar; i++){
					for(int j=0; j<13; j++){
						tabPom[i][j]=rs.getString(j+1);
						System.out.print(tabPom[i][j]+"|");
					}
					System.out.println();
				}
				System.out.println(rozmiar);
			}
		} catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, "B³¹d po³¹czenia z baz¹","Uwaga!", JOptionPane.ERROR_MESSAGE);
		}
    	if(czyDosJest){
    		error+="Podany dostawca istnieje juz w bazie danych\n";
    	}
    	if(nazwaSkrocona.length()>35){ 
    		error+="Nazwa Skrócona zosta³a podana nieprawid³owa(35max znaków)\n";
    		jtfNazwaSkrocona.setBackground(Color.RED);
    	}
    	if(nazwaSkrocona.matches("^\\s*$")){
    		error+="Nazwa Skrócona zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
    		jtfNazwaSkrocona.setBackground(Color.RED);
    	}    	
    	if(nazwaPelna.length()>100){
    		error+="Nazwa Pe³na zosta³a podana nieprawid³owa(100max znaków)\n";
    		jtaNazwaPelna.setBackground(Color.RED);
    	}
    	if(nazwaPelna.matches("^\\s*$")){
    		error+="Nazwa Pe³na zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
    		jtaNazwaPelna.setBackground(Color.RED);
    	} 
    	if(nip.length()!=10){
    		error+="D³ugoœæ Nip'u musi byc 10(cyfr)\n";
    		jtfNIP.setBackground(Color.RED);
    	} 
    	else if(!nip.matches("[0-9]{10}")){
    		error+="Nip mo¿e zawieraæ tylko cyfry(10)\n";
    		jtfNIP.setBackground(Color.RED);
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
    	if(miejsc.length()>50){
    		error+="Miejscowosc zosta³a podana zbyt d³uga(50max)\n";
    		jtfAdres.setBackground(Color.RED);
    	}
    	if(miejsc.matches("^\\s*$")){
    		error+="Miejscowosc zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
    		jtfAdres.setBackground(Color.RED);
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
	private boolean insertTowaryDostawcy(){
		try {
			polaczenie = new Polaczenie();
			String sql;
			String idDostawca;
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		Date date = new Date();
			sql = "SELECT idDostawca FROM Dostawca WHERE Nip="+jtfNIP.getText().toString()+"";
			//sql = "SELECT * FROM Dostawca WHERE Nip=5645824795";
			ResultSet rs = polaczenie.sqlSelect(sql);
//			ResultSetMetaData rsmd = rs.getMetaData();
			rs.next();
			idDostawca = rs.getString("idDostawca");
			
			sql = "SELECT IdTowar,KodTowaru FROM towar WHERE NazwaTowaru='"+jcbTowary.getSelectedItem()+"'";
			System.out.println(sql);
			rs = polaczenie.sqlSelect(sql);
			rs.next();
			String idTowaru = rs.getString("IdTowar");
			String kodTowaru = rs.getString("KodTowaru");
			
			sql = "SELECT * FROM dostawcatowar WHERE idTowar='"+idTowaru+"' AND idDostawca='"+idDostawca+"'";
			System.out.println(sql);
			rs = polaczenie.sqlSelect(sql);
			if(!rs.last()){
				Connection connection = DriverManager.getConnection(url, username, password);
				connection.createStatement();
				String query = "INSERT INTO dostawcatowar "
					+ "(IdDostawca, IdTowar, Cena, DataOd, DataDo, KodTowaruWgDostawcy, NazwaTowaruWgDostawcy)"
				    + " values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = connection.prepareStatement(query);
				preparedStmt.setString (1, idDostawca);
				preparedStmt.setString (2, idTowaru);
				preparedStmt.setString (3, jtfCena.getText().toString());
				preparedStmt.setString (4, dateFormat.format(date));
				preparedStmt.setString (5, null);
				preparedStmt.setString (6, jtfKodWgDos.getText().toString());
				preparedStmt.setString (7, jtfNazwaWgDos.getText().toString());
				System.out.println("err");
				// execute the preparedstatement
				preparedStmt.execute();
				connection.close();
				double cenaD = Double.parseDouble(jtfCena.getText().toString());
				DecimalFormat df = new DecimalFormat("###,###.00z³");
				System.out.println(df.format(cenaD));
		    	String[] tabPom = {jcbTowary.getSelectedItem().toString(),kodTowaru,jtfKodWgDos.getText(),jtfNazwaWgDos.getText(),""+df.format(cenaD)};
		    	tableModel.addRow(tabPom);
		    	tablicaTowarow.setModel(tableModel);
			}
			else {
				errorAlert("W bazie znajduje juz sie ten towar");
				return false;
			}
			
//			System.out.println(idDostawca+","+idTowaru+" "+jcbTowary.getSelectedItem());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}		
		return true;
	}
//    private void dodajNowyTowar() {
////    	String walidacjaTowaru = walidacjaTowaru();
////    	if(walidacjaTowaru.length()>0){
////    		JOptionPane.showMessageDialog(null, walidacjaTowaru,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
////    	}else{
//    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    		Date date = new Date();
//	    	String[] tabPom = {jcbTowary.getSelectedItem().toString(),jtfCena.getText(),dateFormat.format(date),jtfDataDo.getText(),jtfKodWgDos.getText(),jtfNazwaWgDos.getText()};
//	    	tableModel.addRow(tabPom);
//	    	tablicaTowarow.setModel(tableModel);
//	    	wyczyscDaneTowaru();
////    	}
//	}
    /**
     * Tworzy formularz dodawania towarow dostawcy
     */
    private void dialogTowary(){
//		JFrame frame = new JFrame();
//		frame.add(pTowary);
//		frame.setPreferredSize(new Dimension(400, 300));
//		frame.setSize(700, 300);
//		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
		JOptionPane optionPane = new JOptionPane(pTowary,
			    JOptionPane.PLAIN_MESSAGE,
			    JOptionPane.DEFAULT_OPTION, 
			    null, new Object[]{jbtNowyTowar,jbtZakonczTowar}, null);
		dialog = new JDialog ();
		dialog.setContentPane(optionPane);
		dialog.setModal (true);
//		dialog.setAlwaysOnTop (true);
		dialog.setModalityType (ModalityType.APPLICATION_MODAL);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
    }
    public void errorAlert(String txt){
    	JOptionPane.showMessageDialog(this,  txt, "Uwaga!", JOptionPane.ERROR_MESSAGE);
    }
    private String walidacjaTowaru(){
    	String error = "";
    	String cena = jtfCena.getText().toString();
//    	String dataOd = jtfDataOd.getText().toString();
//    	String dataDo = jtfDataDo.getText().toString();
    	String kodWgDos = jtfKodWgDos.getText().toString();
    	String nazwaWgDos = jtfNazwaWgDos.getText().toString();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    	Date dtDataOd = null;
//    	Date dtDataDo = null;
//    	boolean flagaDate = true;
    	if(cena.matches("^\\s*$")){
    		error+="Cena zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
			jtfCena.setBackground(Color.RED);
    	}
    	else {
	    	try{
	        	Double.parseDouble(cena);
	    	}catch (Exception e) {
				error+="Cena zosta³a podana nieprawid³owa(tylko liczby ,np. 20.99)\n";
				jtfCena.setBackground(Color.RED);
			}
    	}
//    	if(dataOd.length()>11){
//    		error+="DataOd zosta³a podana nieprawid³owa(max 10 znaków, YYYY-MM-DD)\n";
//    		jtfDataOd.setBackground(Color.RED);
//    	}
//    	if(dataOd.isEmpty() || dataOd.matches("^\\s*$")){
//    		error+="DataOd zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
//    		jtfDataOd.setBackground(Color.RED);
//    		flagaDate = false;
//    	}
//    	else{
//            try {
//				dtDataOd = formatter.parse(dataOd);
//			} catch (Exception e) {
//	    		error+="DataOd zosta³a podana nieprawid³owa(YYYY-MM-DD)\n";
//	    		jtfDataOd.setBackground(Color.RED);
//	    		flagaDate = false;
//			}
//    	}    	
//    	if(dataDo.length()>11){
//    		error+="DataDo zosta³a podana nieprawid³owa(max 10 znaków, YYYY-MM-DD)\n";
//    		jtfDataDo.setBackground(Color.RED);
//    	}else if(dataDo.isEmpty()){
//    		flagaDate = false;
//    	}
//    	else if(!dataDo.isEmpty()){
//	    	try{
//	            dtDataDo = formatter.parse(dataDo);
//	    	}catch (Exception e) {
//	    		error+="DataDo zosta³a podana nieprawid³owa(YYYY-MM-DD)\n";
//	    		jtfDataDo.setBackground(Color.RED);
//	    		flagaDate = false;
//			}
//    	}
//    	if(flagaDate){
//    		if(dtDataOd.after(dtDataDo)){
//	    		error+="DataDo musi byc pózniejsza ni¿ DataOd\n";
//	    		jtfDataDo.setBackground(Color.RED);
//	    		jtfDataOd.setBackground(Color.RED);
//    		}
//    	}
    	if(kodWgDos.isEmpty() || kodWgDos.matches("^\\s*$")){
    		error+="Kod weg³ug Dostawcy zosta³a podany nieprawid³owy(nie mo¿e pozostaæ pusty)\n";
    		jtfKodWgDos.setBackground(Color.RED);
    	}
    	else if(kodWgDos.length()>50){
    		error+="Kod weg³ug Dostawcy zosta³a podany nieprawid³owy(d³ugoœæ nie mo¿e przekraczaæ 50 znaków)\n";
    		jtfKodWgDos.setBackground(Color.RED);
    	}
    	if(nazwaWgDos.matches("^\\s*$")){
    		error+="Nazwa weg³ug Dostawcy zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
    		jtfNazwaWgDos.setBackground(Color.RED);
    	}
    	else if(nazwaWgDos.length()>50){
    		error+="Nazwa weg³ug Dostawcy zosta³a podana nieprawid³owa(d³ugoœæ nie mo¿e przekraczaæ 50 znaków)\n";
    		jtfNazwaWgDos.setBackground(Color.RED);
    	}
    	return error;
    }
    private void wyczyscDaneTowaru(){
    	jtfCena.setText("");
    	jtfDataOd.setText("");
    	jtfDataDo.setText("");
    	jtfKodWgDos.setText("");
    	jtfNazwaWgDos.setText("");
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
        jtfNIP.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		    	String nip = jtfNIP.getText().toString();
		    	if(nip.length()!=10){
		    		jtfNIP.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "D³ugoœæ Nip'u musi byc 10(cyfr)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	} 
		    	else if(!nip.matches("[0-9]{10}")){
		    		jtfNIP.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Nip mo¿e zawieraæ tylko cyfry(10)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfNIP.selectAll();
				jtfNIP.setBackground(Color.WHITE);
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
		jtfMiejsc.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
		    	String adres = jtfMiejsc.getText().toString();
		    	if(adres.length()>50){
		    		jtfMiejsc.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Miescowosc zosta³a podana zbyt d³uga(50max)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
		    	jtfPoczta.setText(jtfMiejsc.getText());
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
//		    		if(dataOd.matches("^\\s*$")){
//		    			throw new Exception("Exception thrown");
//		    		}
		    		if(dataOd.length()>11){
		    			throw new Exception("Exception thrown");
		    		}
//		            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		            formatter.parse(dataOd);
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
		jtfKodWgDos.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String kodWgDos = jtfKodWgDos.getText().toString();
//		    	if(kodWgDos.isEmpty() || kodWgDos.matches("^\\s*$")){
//		    		jtfKodWgDos.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Kod weg³ug Dostawcy zosta³a podany nieprawid³owy(nie mo¿e pozostaæ pusty)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		    	}
		    	if(kodWgDos.length()>50){
		    		jtfKodWgDos.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Kod weg³ug Dostawcy zosta³a podany nieprawid³owy(d³ugoœæ nie mo¿e przekraczaæ 50 znaków)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfKodWgDos.selectAll();
				jtfKodWgDos.setBackground(Color.WHITE);
			}
		});
		jtfNazwaWgDos.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String nazwaWgDos = jtfNazwaWgDos.getText().toString();
//		    	if(nazwaWgDos.isEmpty() || nazwaWgDos.matches("^\\s*$")){
//		    		jtfKodWgDos.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Nazwa weg³ug Dostawcy zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		    	}
		    	if(nazwaWgDos.length()>50){
		    		jtfKodWgDos.setBackground(Color.RED);
		    		JOptionPane.showMessageDialog(null, "Nazwa weg³ug Dostawcy zosta³a podana nieprawid³owa(d³ugoœæ nie mo¿e przekraczaæ 50 znaków)","Uwaga!", JOptionPane.ERROR_MESSAGE);
		    	}
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtfNazwaWgDos.selectAll();
				jtfNazwaWgDos.setBackground(Color.WHITE);
			}
		});
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
        jtaNazwaPelna.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) { check(); }
			@Override
			public void insertUpdate(DocumentEvent e) { check(); }
			@Override
			public void changedUpdate(DocumentEvent e) { check(); }

		    public void check() {
		    	if (jtaNazwaPelna.getText().length()>100){
		    		JOptionPane.showMessageDialog(null, "Nie mo¿na wprowadzic wiêcej ni¿ 100 znaków!", "B³¹d!", JOptionPane.ERROR_MESSAGE);
		    	}
		    }
		});
    }
    private void keyListener(){
    	jtaNazwaPelna.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB)  {
					jtaNazwaPelna.transferFocus();
				}
			}
		});
    }

}