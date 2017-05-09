
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WykazDostawcow extends JPanel implements ListSelectionListener, KeyListener, ActionListener{
	private Polaczenie polaczenie;
	private JList list;
	private String[] tab, tabTowary;
	private JSplitPane splitPane, spDaneButt, spMidRig;
	private JScrollPane scrollPane,scrollPane1;
	private JLabel jlbNazSkroc, jlbNazPeln,jlbNIP, jlbTel1, jlbTel2,jlbTel3,jlbNazDzial,jlbNrKonta,jlbAdres,jlbKodPocz,jlbPoczta, jlbKodWgDos, jlbNazwaWgDos, jlbDataDo, jlbDataOd, jlbCena, jlbTowary, jlbTytulTowary;
	private JTextField search,jtfNazSkroc,jtfNIP,jtfTel1,jtfTel2,jtfTel3,jtfNazDzial,jtfNrKonta,jtfAdres,jtfKodPocz,jtfPoczta, jtfKodWgDos, jtfNazwaWgDos, jtfDataDo, jtfDataOd, jtfCena;
	private JTextArea area, jtaNazPeln;
	private JButton jbtDodajDos, jbtDodajTow, jbtNowyTowar;
	private JComboBox<String> jcbTowary;
	private JPanel pTowary;
	private boolean edycjaListy;

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
        pTowary.setVisible(false);
		
		splitPane = new JSplitPane();							//panel + spMigRig
		spMidRig = new JSplitPane();							//spDaneButt + pTowary
		spDaneButt = new JSplitPane(JSplitPane.VERTICAL_SPLIT);	//p + pButtons
		
		String[] tabNazwyKol = null;
		tabTowary = getTowary();
//	    try{
//	    	polaczenie = new Polaczenie();
//			String sql = "SELECT * FROM dostawcatowar";
//			ResultSet rs = polaczenie.sqlSelect(sql);
//			int rozmiarKol = rs.getMetaData().getColumnCount();
//			tabNazwyKol = new String[rozmiarKol-2];
//			tabNazwyKol[0] = "Nazwa Towaru";
//			tabNazwyKol[1] = "Cena";
//			tabNazwyKol[2] = "Data Od";
//			tabNazwyKol[3] = "Data Do";
//			tabNazwyKol[4] = "Kod towaru wed³ug dostawcy";
//			tabNazwyKol[5] = "Nazwa towaru wed³ug dostawcy";
////			
////			for(int i=1; i<rozmiarKol-2; i++){
////				tabNazwyKol[i] = rs.getMetaData().getColumnName(i+3);
////			}
//	      }catch (SQLException e) {
//				e.printStackTrace();
//	    }
		try {
			polaczenie = new Polaczenie();
			String sql = "SELECT * FROM dostawca";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tab = new String [rozmiar];
			while(rs.next())
			{
				tab[i] = rs.getString("NazwaSkrocona");
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		scrollPane = new JScrollPane();
		scrollPane1 = new JScrollPane();
		search = new JTextField();
		list = new JList(tab);
		list.setMinimumSize(new Dimension(150,150));
		list.setPreferredSize(new Dimension(150, 150));
		list.setAlignmentX(CENTER_ALIGNMENT);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		search.setMaximumSize(new Dimension(200, 20));
		edycjaListy = true;
		
		Border border = BorderFactory.createLineBorder(Color.lightGray);
		jlbNazSkroc = new JLabel("Nazwa Skrocona:");
		jtfNazSkroc = new JTextField();
		jtfNazSkroc.setEditable(false);
		jlbNazPeln = new JLabel("Nazwa Pelna:");
		jtaNazPeln = new JTextArea();
		jtaNazPeln.setEditable(false);
		jtaNazPeln.setPreferredSize(new Dimension(400, 40));
		jtaNazPeln.setLineWrap(true);
		jtaNazPeln.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 2, 0, 0)));
		jtaNazPeln.setBackground(null);
		jlbNIP = new JLabel("NIP:");
		jtfNIP = new JTextField();
		jtfNIP.setEditable(false);
		jlbTel1 = new JLabel("Telefon 1:");
		jtfTel1 = new JTextField();
		jtfTel1.setEditable(false);
		jlbTel2 = new JLabel("Telefon 2:");
		jtfTel2 = new JTextField();
		jtfTel2.setEditable(false);
		jlbTel3 = new JLabel("Telefon 3:");
		jtfTel3 = new JTextField();
		jtfTel3.setEditable(false);
		jlbNazDzial = new JLabel("Nazwa Dzialu:");
		jtfNazDzial = new JTextField();
		jtfNazDzial.setEditable(false);
		jlbNrKonta = new JLabel("Numer Konta:");
		jtfNrKonta = new JTextField();
		jtfNrKonta.setEditable(false);
		jlbAdres = new JLabel("Adres:");
		jtfAdres = new JTextField();
		jtfAdres.setEditable(false);
		jlbKodPocz = new JLabel("Kod Pocztowy:");
		jtfKodPocz = new JTextField();
		jtfKodPocz.setEditable(false);
		jlbPoczta = new JLabel("Poczta:");
		jtfPoczta = new JTextField();
		jtfPoczta.setEditable(false);

        jbtDodajDos= new JButton("Dodaj nowego dostawce");
        jbtDodajDos.setName("addDos");
//        jbtDodajDos.setEnabled(false);
        jbtDodajTow= new JButton("Dodaj towary do dostawcy");
        jbtDodajTow.setEnabled(false);
        jbtDodajTow.setName("addTowary");
        
        jlbTytulTowary = new JLabel("Dodawanie towaru do Dostawcy");
        jlbTytulTowary.setFont(new Font("Calibri", Font.BOLD, 30));
        jbtNowyTowar = new JButton("Nowy Towar");
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
        jlbKodWgDos = new JLabel("Kod towaru wed³ug dostacy");
        jtfKodWgDos = new JTextField("");
        jlbNazwaWgDos = new JLabel("Nazwa towaru wed³ug dostacy");
        jtfNazwaWgDos = new JTextField("");

		panel.add(search);
		panel.add(scrollPane);
		
		c.gridx = 0; c.gridy = 0;
        p.add(jlbNazSkroc,c);
        c.gridx++;
        p.add(jtfNazSkroc,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNazPeln,c);
        c.gridx++;
        p.add(jtaNazPeln,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNIP,c);
        c.gridx++;
        p.add(jtfNIP,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTel1,c);
        c.gridx++;
        p.add(jtfTel1,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTel2,c);
        c.gridx++;
        p.add(jtfTel2,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTel3,c);
        c.gridx++;
        p.add(jtfTel3,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNazDzial,c);
        c.gridx++;
        p.add(jtfNazDzial,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNrKonta,c);
        c.gridx++;
        p.add(jtfNrKonta,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbAdres,c);
        c.gridx++;
        p.add(jtfAdres,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbKodPocz,c);
        c.gridx++;
        p.add(jtfKodPocz,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbPoczta,c);
        c.gridx++;
        p.add(jtfPoczta, c);

        pButtons.add(jbtDodajDos);
        pButtons.add(jbtDodajTow);
        
        gbcPanelTowary.gridx = 1; gbcPanelTowary.gridy = 0;
        pTowary.add(jlbTytulTowary,gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        pTowary.add(jlbTowary,gbcPanelTowary);
        gbcPanelTowary.gridx++;
        pTowary.add(jcbTowary,gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        pTowary.add(jlbCena,gbcPanelTowary);
        gbcPanelTowary.gridx++;
        pTowary.add(jtfCena,gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        pTowary.add(jlbDataOd,gbcPanelTowary);
        gbcPanelTowary.gridx++;
        pTowary.add(jtfDataOd,gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        pTowary.add(jlbDataDo,gbcPanelTowary);
        gbcPanelTowary.gridx++;
        pTowary.add(jtfDataDo,gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        pTowary.add(jlbKodWgDos,gbcPanelTowary);
        gbcPanelTowary.gridx++;
        pTowary.add(jtfKodWgDos,gbcPanelTowary);
        gbcPanelTowary.gridx = 0; gbcPanelTowary.gridy++;
        pTowary.add(jlbNazwaWgDos,gbcPanelTowary);
        gbcPanelTowary.gridx++;
        pTowary.add(jtfNazwaWgDos,gbcPanelTowary);
        
//        JPanel panelTowaryDolny = new JPanel();
//      panelTowaryDolny.setLayout(new BoxLayout(panelTowaryDolny, BoxLayout.Y_AXIS));
//      tablemodel = new DefaultTableModel(0,0);
//  	tablemodel.setColumnIdentifiers(tabNazwyKol);
//  	tablicaTowarow = new JTable();
//  	tablicaTowarow.getTableHeader().setReorderingAllowed(false);
//  	tablicaTowarow.setModel(tablemodel);
//  	tablicaTowarow.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//  	int[] tabKolSzer = {200,100,100,100,200,200};
//  	for(int i=0; i<tabNazwyKol.length; i++){
//  		tablicaTowarow.getColumnModel().getColumn(i).setPreferredWidth(tabKolSzer[i]);
//			DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
//  		if(i==1){
//  			tableRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
//  			tablicaTowarow.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
//  		}
//  		else if(i==2 || i ==3){
//  			tableRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//  			tablicaTowarow.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
//  		}
//  	}
//      scrollPane = new JScrollPane(tablicaTowarow);
//      scrollPane.setPreferredSize(new Dimension(400, 200));
//      scrollPane.setMinimumSize(new Dimension(400, 200));

        spDaneButt.setTopComponent(p);
        spDaneButt.setBottomComponent(pButtons);
        
        spMidRig.setLeftComponent(spDaneButt);
        spMidRig.setRightComponent(pTowary);
        
		splitPane.setLeftComponent(panel);
        splitPane.setRightComponent(spMidRig);
        add(splitPane);
        
        ustawNasluchZdarzen();
	}
	private void ustawNasluchZdarzen(){
		list.addListSelectionListener(this);
		search.addKeyListener(this);
		jbtDodajDos.addActionListener(this);
		jbtDodajTow.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object e = arg0.getSource();
		if(e==jbtDodajTow){
			if(jbtDodajTow.getName() == "addTowary"){
				pTowary.setVisible(true);
				spMidRig.setDividerLocation(CENTER_ALIGNMENT);
				jbtDodajTow.setText("Zakoncz dodawanie towarow");
		        jbtDodajTow.setName("endTowary");
			}
			else if(jbtDodajTow.getName() == "endTowary"){
				pTowary.setVisible(false);
				spMidRig.setDividerLocation(RIGHT_ALIGNMENT);
				jbtDodajTow.setText("Dodaj towary do dostawcy");
		        jbtDodajTow.setName("addTowary");
			}
		}
		else if(e==jbtDodajDos){
			if(jbtDodajDos.getName() == "addDos"){
				jbtDodajDos.setText("Zakoncz dodawanie dostawcy");
				jbtDodajDos.setName("endDos");
				jbtDodajTow.setEnabled(false);
				jtaNazPeln.setBackground(Color.WHITE);
				edycjaDanychDos(true);
				edycjaListy = false;
				wyczyscDaneKontaktowe();
			} else if(jbtDodajDos.getName() == "endDos"){
				jbtDodajDos.setText("Dodaj nowego dostawce");
				jbtDodajDos.setName("addDos");
				jbtDodajTow.setEnabled(true);
				jtaNazPeln.setBackground(null);
				edycjaDanychDos(false);
				edycjaListy = true;
			}
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(edycjaListy){
			if(arg0.getValueIsAdjusting()) {
				String[] tabPom;
				String sel = list.getSelectedValue().toString();
				String sql = "SELECT NazwaSkrocona, NazwaPelna, NIP, Telefon1, Telefon2, Telefon3, NazwaDzialu, NrKonta, Adres, KodPocztowy, Poczta  FROM dostawca WHERE NazwaSkrocona='"+sel+"'";
				//int id = 0;
				//String sql = "SELECT NumerZamowienia, TerminRealizacji, DataRealizacji, DataWystawienia, SposobDostawy, KosztDostawy,WartoscTowarow, KosztZamowienia FROM zamowienie WHERE NumerZamowienia='"+sel+"'";
				
				try {
					ResultSet rs = polaczenie.sqlSelect(sql);
					tabPom = new String[11];
					
					rs.next();
					for(int i = 0;i<tabPom.length;i++)
					{
						tabPom[i]=rs.getString(i+1);
					}
					jtfNazSkroc.setText(tabPom[0]);
					jtaNazPeln.setText(tabPom[1]);
					jtfNIP.setText(tabPom[2]);
					jtfTel1.setText(tabPom[3]);
					jtfTel2.setText(tabPom[4]);
					jtfTel3.setText(tabPom[5]);
					jtfNazDzial.setText(tabPom[6]);
					jtfNrKonta.setText(tabPom[7]);
					jtfAdres.setText(tabPom[8]);
					jtfKodPocz.setText(tabPom[9]);
					jtfPoczta.setText(tabPom[10]);
					jbtDodajTow.setEnabled(true);
		
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
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
	@Override
	public void keyPressed(KeyEvent arg0) { }
	@Override
	public void keyReleased(KeyEvent arg0) { szukaj(search.getText()); }
	@Override
	public void keyTyped(KeyEvent arg0) { }

    private void wyczyscDaneKontaktowe(){
		jtfNazSkroc.setText("");
		jtaNazPeln.setText("");
		jtfNIP.setText("");
		jtfTel1.setText("");
		jtfTel2.setText("");
		jtfTel3.setText("");
		jtfNazDzial.setText("");
		jtfNrKonta.setText("");
		jtfAdres.setText("");
		jtfKodPocz.setText("");
		jtfPoczta.setText("");
    }
    private void edycjaDanychDos(boolean flag){
		jtfNazSkroc.setEditable(flag);
		jtaNazPeln.setEditable(flag);
		jtfNIP.setEditable(flag);
		jtfTel1.setEditable(flag);
		jtfTel2.setEditable(flag);
		jtfTel3.setEditable(flag);
		jtfNazDzial.setEditable(flag);
		jtfNrKonta.setEditable(flag);
		jtfAdres.setEditable(flag);
		jtfKodPocz.setEditable(flag);
		jtfPoczta.setEditable(flag);
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
//	private boolean insertTowaryDostawcy(){
//		try {
//			polaczenie = new Polaczenie();
//			String sql;
//			String idDostawca;
//			sql = "SELECT idDostawca FROM Dostawca WHERE Nip="+jtfNip.getText().toString()+"";
//			sql = "SELECT * FROM Dostawca WHERE Nip=5645824795";
//			ResultSet rs = polaczenie.sqlSelect(sql);
//			ResultSetMetaData rsmd = rs.getMetaData();
//			rs.next();
//			
//			idDostawca = rs.getString("idDostawca");
//			System.out.println(idDostawca);
//			int liczbaWierszy = tablemodel.getRowCount();
//			int liczbaKolumn = tablemodel.getColumnCount();
//			String[][] tabPom = new String[liczbaWierszy][liczbaKolumn];
//			//String
//			for(int i=0; i<liczbaWierszy; i++){
//				for(int j=0; j<liczbaKolumn; j++){
//					tabPom[i][j] = (String) tablemodel.getValueAt(i, j);
//					//System.out.print(tabPom[i][j]+"|");
//					String name = rsmd.getColumnName(j);
//				}
//				//System.out.println();
//			}
//			sql="";
//			for(int i=0; i<liczbaWierszy; i++){
//				sql+="Insert";
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}		
//		return true;
//	}
//
//    private void dodajNowyTowar() {
//    	String walidacjaTowaru = walidacjaTowaru();
//    	if(walidacjaTowaru.length()>0){
//    		JOptionPane.showMessageDialog(null, walidacjaTowaru,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
//    	}else{
//	    	String[] tabPom = {jcbTowary.getSelectedItem().toString(),jtfCena.getText(),jtfDataOd.getText(),jtfDataDo.getText(),jtfKodWgDos.getText(),jtfNazwaWgDos.getText()};
//	    	tablemodel.addRow(tabPom);
//	    	tablicaTowarow.setModel(tablemodel);
//	    	wyczyscDaneTowaru();
//    	}
//	}
//    private String walidacjaTowaru(){
//    	String error = "";
//    	String cena = jtfCena.getText().toString();
//    	String dataOd = jtfDataOd.getText().toString();
//    	String dataDo = jtfDataDo.getText().toString();
//    	String kodWgDos = jtfKodWgDos.getText().toString();
//    	String nazwaWgDos = jtfNazwaWgDos.getText().toString();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    	Date dtDataOd = null;
//    	Date dtDataDo = null;
//    	boolean flagaDate = true;
//    	if(cena.matches("^\\s*$")){
//    		error+="Cena zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
//			jtfCena.setBackground(Color.RED);
//    	}
//    	try{
//        	Double.parseDouble(cena);
//    	}catch (Exception e) {
//			error+="Cena zosta³a podana nieprawid³owa(tylko liczby ,np. 20.99)\n";
//			jtfCena.setBackground(Color.RED);
//		}
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
//			} catch (ParseException e) {
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
//    	if(kodWgDos.isEmpty() || kodWgDos.matches("^\\s*$")){
//    		error+="Kod weg³ug Dostawcy zosta³a podany nieprawid³owy(nie mo¿e pozostaæ pusty)\n";
//    		jtfKodWgDos.setBackground(Color.RED);
//    	}
//    	else if(kodWgDos.length()>50){
//    		error+="Kod weg³ug Dostawcy zosta³a podany nieprawid³owy(d³ugoœæ nie mo¿e przekraczaæ 50 znaków)\n";
//    		jtfKodWgDos.setBackground(Color.RED);
//    	}
//    	if(nazwaWgDos.matches("^\\s*$")){
//    		error+="Nazwa weg³ug Dostawcy zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)\n";
//    		jtfNazwaWgDos.setBackground(Color.RED);
//    	}
//    	else if(nazwaWgDos.length()>50){
//    		error+="Nazwa weg³ug Dostawcy zosta³a podana nieprawid³owa(d³ugoœæ nie mo¿e przekraczaæ 50 znaków)\n";
//    		jtfNazwaWgDos.setBackground(Color.RED);
//    	}
//    	return error;
//    }
//    private void wyczyscDaneTowaru(){
//    	jtfCena.setText("");
//    	jtfDataOd.setText("");
//    	jtfDataDo.setText("");
//    	jtfKodWgDos.setText("");
//    	jtfNazwaWgDos.setText("");
//    }
//    private void focusListener(){
//		jtfCena.addFocusListener(new FocusListener() {
//			@Override
//			public void focusLost(FocusEvent e) {
//		    	try{
//		        	Double.parseDouble(jtfCena.getText().toString());
//		    	}catch (Exception ex) {
//		    		jtfCena.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Cena zosta³a podana nieprawid³owa(tylko liczby ,np. 20.99)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//				}
//			}
//			@Override
//			public void focusGained(FocusEvent e) {
//				jtfCena.selectAll();
//				jtfCena.setBackground(Color.WHITE);
//			}
//		});
//		jtfDataOd.addFocusListener(new FocusListener() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				String dataOd = jtfDataOd.getText().toString();
//		    	try{
//		    		if(dataOd.matches("^\\s*$")){
//		    			throw new Exception("Exception thrown");
//		    		}
//		    		else if(dataOd.length()>11){
//		    			throw new Exception("Exception thrown");
//		    		}
//		            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		            formatter.parse(dataOd);
//		    	}catch (Exception ex) {
//	//		    		if(dataOd.length()>11){
//		        		jtfDataOd.setBackground(Color.RED);
//			    		JOptionPane.showMessageDialog(null, "Data zosta³a podana nieprawid³owa(max 10 znaków w formacie 'YYYY-MM-DD')","Uwaga!", JOptionPane.ERROR_MESSAGE);
//	//		        	}
//				}
//			}
//			@Override
//			public void focusGained(FocusEvent e) {
//				jtfDataOd.selectAll();
//				jtfDataOd.setBackground(Color.WHITE);
//			}
//		});
//		jtfKodWgDos.addFocusListener(new FocusListener() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				String kodWgDos = jtfKodWgDos.getText().toString();
//		    	if(kodWgDos.isEmpty() || kodWgDos.matches("^\\s*$")){
//		    		jtfKodWgDos.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Kod weg³ug Dostawcy zosta³a podany nieprawid³owy(nie mo¿e pozostaæ pusty)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		    	}
//		    	else if(kodWgDos.length()>50){
//		    		jtfKodWgDos.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Kod weg³ug Dostawcy zosta³a podany nieprawid³owy(d³ugoœæ nie mo¿e przekraczaæ 50 znaków)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		    	}
//			}
//			@Override
//			public void focusGained(FocusEvent e) {
//				jtfKodWgDos.selectAll();
//				jtfKodWgDos.setBackground(Color.WHITE);
//			}
//		});
//		jtfNazwaWgDos.addFocusListener(new FocusListener() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				String nazwaWgDos = jtfNazwaWgDos.getText().toString();
//		    	if(nazwaWgDos.isEmpty() || nazwaWgDos.matches("^\\s*$")){
//		    		jtfKodWgDos.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Nazwa weg³ug Dostawcy zosta³a podana nieprawid³owa(nie mo¿e pozostaæ pusta)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		    	}
//		    	else if(nazwaWgDos.length()>50){
//		    		jtfKodWgDos.setBackground(Color.RED);
//		    		JOptionPane.showMessageDialog(null, "Nazwa weg³ug Dostawcy zosta³a podana nieprawid³owa(d³ugoœæ nie mo¿e przekraczaæ 50 znaków)","Uwaga!", JOptionPane.ERROR_MESSAGE);
//		    	}
//			}
//			@Override
//			public void focusGained(FocusEvent e) {
//				jtfNazwaWgDos.selectAll();
//				jtfNazwaWgDos.setBackground(Color.WHITE);
//			}
//		});
//    }
}