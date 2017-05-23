import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class OdbiorZamowien extends JPanel implements ListSelectionListener, KeyListener{
	String serverName = "localhost";
    String mydatabase = "magazyn";
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
    String username = "root";
    String password = "";
	private Polaczenie polaczenie;
	private JList<String> list;
	private JTable tabela;
	private String[] tab,tabMagazyn;
	private JSplitPane splitPane,splitPane1;
	private JComboBox<String> jcbNumerMagazynu;
	private JScrollPane scrollPane,scrollPane1;
	private JLabel jlbNrZam,jlbNumerPZ,jlbDataWystawienia,jlbNumerMagazynu,jlbDostawca,jlbWartoscNetto,jlbUwagi;
	private JTextField jtfNrZam,search,jtfNumerPZ,jtfDataWystawienia,jtfNumerMagazynu,jtfWartoscNetto,jtfUwagi,jtfDostawca;
	private JButton jbZatwierdz;
	public OdbiorZamowien()
	{
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
		
		String[] columnNames = 
			{"Lp",
			"Kod towaru",
            "Nazwa towaru",
            "j.m",
            "Ilosc",
            "Cena", 
            "Wartosc Netto"};
		
		String[][] data = new String[0][0];
		
		tabela = new JTable(data, columnNames);
		tabela.setDefaultEditor(Object.class, null);
		tabela.setPreferredScrollableViewportSize(new Dimension(400,200));
		tabela.getTableHeader().setReorderingAllowed(false);
		
		scrollPane1 = new JScrollPane(tabela);
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 0, 10);
		jlbNumerPZ = new JLabel("Numer PZ:");
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
			String sql = "SELECT IdZamowienie, NumerZamowienia, TerminRealizacji, DataRealizacji, DataWystawienia, sposobdostawy.SposobDostawy, KosztDostawy,WartoscTowarow, KosztZamowienia, dostawca.NazwaSkrocona FROM zamowienie INNER JOIN sposobdostawy ON sposobdostawy.IdSposobDostawy=zamowienie.IdSposobDostawy INNER JOIN dostawca ON dostawca.IdDostawca=zamowienie.IdDostawcy WHERE NumerZamowienia='"+sel+"'";
			
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
//				jtfDataWys.setText(tabPom[4]);
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
					towary[j][2]=result.getString(3);
					towary[j][3]=result.getString(4);
					towary[j][4]=result.getString(5);
					wynik+=Double.parseDouble(result.getString(5));
					j++;
				}
				jtfWartoscNetto.setText(Double.toString(wynik));
	
				String[] columnNames = 
					{"Lp",
		            "Nazwa Towaru",
		            "Cena",
		            "Ilosc",
		            "Wartosc Netto"};
				DefaultTableModel tableModel = new DefaultTableModel(0,0);
				tableModel.setColumnIdentifiers(columnNames);
				tabela.setModel(tableModel);
				for (int i = 0; i < towary.length; i++) {
					String[] data = new String[towary[0].length];
					for(int z = 0;z<5;z++){
						data[z]= towary[i][z];
					}
					tableModel.addRow(data);
				}

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
	public void dodajPZ() throws SQLException
	{
		Connection connection = DriverManager.getConnection(url, username, password);
		String query = "INSERT INTO zamowienie "
				+ "(NumerPZ,Data,Towar,Cena,Ilosc,Wartosc,IdSposobDostawy,KosztDostawy,WartoscTowarow)"
			    + " values (?, ?, ?, ?, ?, ?,?,?,?)";
		PreparedStatement preparedStmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) { }
	@Override
	public void keyReleased(KeyEvent arg0) { szukaj(search.getText()); }
	@Override
	public void keyTyped(KeyEvent arg0) { }
}

