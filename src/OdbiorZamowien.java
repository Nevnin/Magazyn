import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class OdbiorZamowien extends JPanel implements ListSelectionListener, KeyListener, ActionListener{
	String serverName = "localhost";
    String mydatabase = "magazyn";
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
    String username = "root";
    String password = "";
	private Polaczenie polaczenie;
	private JList<String> list;
	private JTable tabela;
	private String[] tab,tabMagazyn,tabNazwyKol = 
		{"Lp",
	            "Nazwa Towaru",
	            "Cena",
	            "Ilosc",
	            "Wartosc Netto"};;
	private JSplitPane splitPane,splitPane1;
	private JComboBox<String> jcbNumerMagazynu;
	private JScrollPane scrollPane,scrollPane1;
	private JLabel jlbNrZam,jlbNumerPZ,jlbDataWystawienia,jlbNumerMagazynu,jlbDostawca,jlbWartoscNetto,jlbUwagi;
	private JTextField jtfNrZam,search;
	JTextField jtfDataWystawienia;
	private JTextField jtfNumerMagazynu;
	private JTextField jtfWartoscNetto;
	private JTextField jtfUwagi;
	private JTextField jtfDostawca;
	public JButton jbZatwierdz;
	private String teraz,nazwaPZ;
	 DecimalFormat df;
	public OdbiorZamowien()
	{
		
		df=new java.text.DecimalFormat("###,###.00"); 
		DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(' ');
		df.setDecimalFormatSymbols(symbols);
		SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd"); 
		 Date data = new Date(); 
		 teraz = dt.format(data);
		 nazwaPZ=tworzenieNazwyPZ();
		try {
			polaczenie = new Polaczenie();
			String sql = "SELECT * FROM zamowienie";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tab = new String [rozmiar];
			while(rs.next())
			{
				tab[i] = rs.getString("NumerZamowienia");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		splitPane = new JSplitPane();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(150,400));
		splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		scrollPane = new JScrollPane();
		
		search = new JTextField();
		list = new JList<String>(tab);
		list.setMinimumSize(new Dimension(150,150));
		list.setAlignmentX(CENTER_ALIGNMENT);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		panel.add(search);
		search.setMaximumSize(new Dimension(200, 20));
		panel.add(scrollPane);
		splitPane.setLeftComponent(panel);
		//scrollPane1.setViewportView(list1);
		

		
		String[][] data1 = new String[0][0];
		
		tabela = new JTable(data1, tabNazwyKol);
		tabela.setDefaultEditor(Object.class, null);
		tabela.setPreferredScrollableViewportSize(new Dimension(400,200));
		tabela.getTableHeader().setReorderingAllowed(false);
		ustawRozmiarTablicu();
		scrollPane1 = new JScrollPane(tabela);
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 0, 10);
		jlbNumerPZ = new JLabel("PZ nr:"+nazwaPZ);
		jlbNumerPZ.setFont(new Font("Calibri", Font.BOLD, 30));
		jlbNrZam = new JLabel("Numer zamowienia");
		jtfNrZam = new JTextField();
		jtfNrZam.setEditable(false);
		jlbDataWystawienia = new JLabel("Data wystawienia:");
		jtfDataWystawienia = new JTextField();
		jtfDataWystawienia.setPreferredSize(new Dimension(400,20));
		String sqlMagazyn = "SELECT * from magazyn";
		
		
		try {
			polaczenie = new Polaczenie();
			ResultSet rs = polaczenie.sqlSelect(sqlMagazyn);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i=0;
			tabMagazyn = new String[rozmiar];
			while(rs.next())
			{
				tabMagazyn[i] = rs.getString("NazwaMagazyn");
				i++;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jlbNumerMagazynu = new JLabel("Magazyn");
		jcbNumerMagazynu = new JComboBox<String>(tabMagazyn);
		jtfNumerMagazynu = new JTextField();
		jtfNumerMagazynu.setVisible(false);
		jlbDostawca = new JLabel("Dostawca:");
		jtfDostawca = new JTextField();
		jtfDostawca.setEditable(false);
		jlbWartoscNetto = new JLabel("Ca³kowita wartosc netto:");
		jtfWartoscNetto = new JTextField();
		jlbUwagi = new JLabel("Uwagi");
		jtfUwagi = new JTextField();
		jbZatwierdz= new JButton("Zatwierdz");
		
		c.gridx = 2; c.gridy = 0;
        p.add(jlbNumerPZ,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNrZam,c);
        c.gridx += 2;
        p.add(jtfNrZam,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbDataWystawienia,c);
        c.gridx += 2;
        p.add(jtfDataWystawienia,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNumerMagazynu,c);
        c.gridx += 2;
        p.add(jcbNumerMagazynu,c);
        p.add(jtfNumerMagazynu,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbDostawca,c);
        c.gridx += 2;
        p.add(jtfDostawca,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbWartoscNetto,c);
        c.gridx += 2;
        p.add(jtfWartoscNetto,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbUwagi,c);
        c.gridx += 2;
        p.add(jtfUwagi,c);
        c.gridx = 0; c.gridy++;
        c.gridx += 2;
        p.add(jbZatwierdz,c);

        splitPane1.setTopComponent(p);
        splitPane1.setBottomComponent(scrollPane1);
        splitPane.setRightComponent(splitPane1);
 
     
        add(splitPane);
        add(splitPane1);
        
        ustawNasluchZdarzen();
	}
	private void ustawNasluchZdarzen(){
		jbZatwierdz.addActionListener(this);
		list.addListSelectionListener(this);
		search.addKeyListener(this);
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		if(arg0.getValueIsAdjusting())
		{
			String[] tabPom;
			String[][] towary;
			String sel = list.getSelectedValue().toString();
			String sql = "SELECT IdZamowienie, NumerZamowienia, TerminRealizacji, DataRealizacji, DataWystawienia, sposobdostawy.SposobDostawy, KosztDostawy,WartoscTowarow, CalkowitaWartoscZamowienia, dostawca.NazwaSkrocona FROM zamowienie INNER JOIN sposobdostawy ON sposobdostawy.IdSposobDostawy=zamowienie.IdSposobDostawy INNER JOIN dostawca ON dostawca.IdDostawca=zamowienie.IdDostawcy WHERE NumerZamowienia='"+sel+"'";
			
			try {
				ResultSet rs = polaczenie.sqlSelect(sql);
				tabPom = new String[10];
				
				rs.next();
				for(int i = 0;i<tabPom.length;i++)
				{
					tabPom[i]=rs.getString(i+1);
				}
				jtfNrZam.setText(tabPom[1]);
//				jtfTermin.setText(tabPom[2]);
//				jtfDataReal.setText(tabPom[3]);
				if(tabPom[3]!=null){
					jtfDataWystawienia.setText(tabPom[3]);
				}else
				{
					jtfDataWystawienia.setText(teraz);
				}
//				jtfSposDos.setText(tabPom[5]);
//				jtfKosztDos.setText(tabPom[6]);
//				jtfWartoscTow.setText(tabPom[7]);
//				jtfKosztZam.setText(tabPom[8]);
				jtfDostawca.setText(tabPom[9]);
				int id = Integer.parseInt(tabPom[0]);
	
				String query1 = "SELECT Lp,towar.NazwaTowaru,Cena,Ilosc,WartoscNetto FROM zamowienietowar INNER JOIN towar ON towar.IdTowar = zamowienietowar.IdTowar WHERE zamowienietowar.IdZamowienie = '"+id+"'";
				ResultSet result = polaczenie.sqlSelect(query1);
				result.last();
				int rozmiar = result.getRow();
				result.beforeFirst();
				towary = new String[rozmiar][5]; 
	int j=0;
	double wynik =0;
				while(result.next())
				{
					towary[j][0]=result.getString(1);
					towary[j][1]=result.getString(2);
					towary[j][2]=df.format(Double.parseDouble(result.getString(3)));
					towary[j][3]=result.getString(4);
					towary[j][4]=df.format(Double.parseDouble(result.getString(5)));
					wynik+=Double.parseDouble(result.getString(5));
					j++;
				}
				jtfWartoscNetto.setText(df.format(wynik));
	
				
				DefaultTableModel tableModel = new DefaultTableModel(0,0);
				tableModel.setColumnIdentifiers(tabNazwyKol);
				tabela.setModel(tableModel);
				for (int i = 0; i < towary.length; i++) {
					String[] data = new String[towary[0].length];
					for(int z = 0;z<5;z++){
						
						data[z]= towary[i][z];
					}
					tableModel.addRow(data);
				}
				ustawRozmiarTablicu();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void szukaj(String text){
		try {
			polaczenie = new Polaczenie();
			String sql = "SELECT NumerZamowienia FROM zamowienie WHERE NumerZamowienia LIKE '%"+text+"%'";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tab = new String[rozmiar];
			while(rs.next()){
				tab[i] = rs.getString("NumerZamowienia");
				i++;
			}
			//list.clearSelection();
			list.setListData(tab);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void dodajPZ() throws ParseException, SQLException 
	{
		
		Connection connection;
			connection = DriverManager.getConnection(url, username, password);
			String query = "INSERT INTO pz "
					+ "(NumerPZ,DataWystawienia, Magazyn, Zamowienie, PodsumowanieNetto, Uwagi)"
					+ " VALUES (?,?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString (1,nazwaPZ);
				preparedStmt.setString(2,(jtfDataWystawienia.getText()));
			String zapMagazyn = "Select * from magazyn WHERE NazwaMagazyn='"+jcbNumerMagazynu.getSelectedItem()+"'";
			Polaczenie poloczenie = new Polaczenie();
			ResultSet rsMagazyn = poloczenie.sqlSelect(zapMagazyn);
			rsMagazyn.next();
			int idMagazyn = rsMagazyn.getInt("IdMagazyn");
			preparedStmt.setInt(3,idMagazyn);
			String zapZamowienie = "Select * from zamowienie WHERE NumerZamowienia='"+jtfNrZam.getText()+"'";
			ResultSet rsZamowienie = poloczenie.sqlSelect(zapZamowienie);
			rsZamowienie.next();
			int idZamowienie = rsZamowienie.getInt("IdZamowienie");
			preparedStmt.setInt (4,idZamowienie);
			Number warNetto;
				warNetto = df.parse(jtfWartoscNetto.getText());
				float wartNetto=warNetto.floatValue();
				preparedStmt.setFloat (5,wartNetto);
			preparedStmt.setString (6,jtfUwagi.getText());
			preparedStmt.execute();
			
			String query2= "UPDATE zamowienie set DataRealizacji = ? WHERE IdZamowienie=?";
			PreparedStatement preparedStmt2 = connection.prepareStatement(query2);
			preparedStmt2.setString(1,jtfDataWystawienia.getText());
			preparedStmt2.setInt(2,idZamowienie);
			preparedStmt2.execute();
			connection.close();
		
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) { }
	@Override
	public void keyReleased(KeyEvent arg0) { szukaj(search.getText()); }
	@Override
	public void keyTyped(KeyEvent arg0) { }
	public String tworzenieNazwyPZ()
	{
		String nazwa="";
		int k=0;
		String[] tablica = teraz.split("-");
		String data = tablica[0]+"-"+tablica[1];
		
		PreparedStatement ps;
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			String count = "SELECT count(*) from pz WHERE DataWystawienia LIKE '"+data+"%'";
			ps = connection.prepareStatement(count);
			ResultSet rsc= ps.executeQuery();
			while(rsc.next())
			{
				k=rsc.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		k=k+1;
		nazwa+=tablica[0]+"/"+tablica[1]+"/"+tablica[2]+"/"+k;
		return nazwa;
		
	}
	public String sprawdzenieDaty(String data) throws ParseException
	{
		
		String error="";
		String[] podzial= null;
		podzial = data.split("-");
		int miesiac = Integer.parseInt(podzial[1]);
		int rok = Integer.parseInt(podzial[0]);
		int dzien = Integer.parseInt(podzial[2]);
		if(miesiac>12){
			error+="Zosta³ podany niepoprawny miesi¹c , nie powinien byæ wiêkszy ni¿ 12.\n"; }
		else{
		 if(miesiac==1 ||miesiac==3 || miesiac==5 || miesiac==7 || miesiac==8 || miesiac==10 || miesiac==12)
		 {
			 if(dzien>31)
			 {
				 error+="Zosta³ podany niepoprawny dzien, nie powinien byæ wiêkszy ni¿ 31.\n";
			 }
		 }
		 else if(miesiac==4 || miesiac==6 || miesiac==9 || miesiac==11)
		 {
			 if(dzien>30)
			 {
				 error+="Zosta³ podany niepoprawny dzien, nie powinien byæ wiêkszy ni¿ 30.\n";
			 }
		 }
		 else
		 {
			 if(((rok%4== 0) && (rok%100!= 0)) || (rok%400 == 0))
			 {
				 if(dzien>29)
				 {
					 error+="Zosta³ podany niepoprawny dzien, nie powinien byæ wiêkszy ni¿ 29.\n";
				 }
			 }
			 else
			 {
				 error+="Zosta³ podany niepoprawny dzien, nie powinien byæ wiêkszy ni¿ 28.\n";
			 }
		 }
		 }
		
		return error;
	}
	String walidacjaDat(String TerminRealizacji) throws ParseException
	{
		String error="";
		
		if(TerminRealizacji.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")){
			String walidacjaDaty = sprawdzenieDaty(TerminRealizacji);
			if(walidacjaDaty.length()>0)
				{
					error+=walidacjaDaty;	
				}
		}else
			{
				error+="Niepoprawny format daty przy DataWystawienia \n";}
		
		return error;
	}
	private void ustawRozmiarTablicu()
	{
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	int[] tabKolSzer = {50,180,100,100,150};
    	for(int i=0; i<tabNazwyKol.length; i++){
    		tabela.getColumnModel().getColumn(i).setPreferredWidth(tabKolSzer[i]);
			DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
    		if(i==2 || i==4){
    			tableRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
    			tabela.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
    		}
    		else if(i==0 || i ==1 || i==3){
    			tableRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    			tabela.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
    		}
    	}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
}
}

