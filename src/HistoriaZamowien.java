import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
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

public class HistoriaZamowien extends JPanel implements ListSelectionListener, KeyListener{
	private Polaczenie polaczenie;
	private JList<String> list;
	private JTable tabela;
	private String[] tab;
	private JSplitPane splitPane,splitPane1;
	private JScrollPane scrollPane,scrollPane1;
	private JLabel jlbNrZam,jlbTermin,jlbDataReal,jlbDataWys,jlbSposDos,jlbKosztDos,jlbWartoscTow,jlbKosztZam,jlbDostawca;
	private JTextField search,jtfNrZam,jtfTermin,jtfDataReal,jtfDataWys,jtfSposDos,jtfKosztDos,jtfWartoscTow,jtfKosztZam,jtfDostawca;
	public HistoriaZamowien()
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
		panel.setPreferredSize(new Dimension(200,600));
		splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		scrollPane = new JScrollPane();
		
		search = new JTextField();
		list = new JList<String>(tab);
		list.setMinimumSize(new Dimension(150,150));
		list.setPreferredSize(new Dimension(150, 150));
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
            "Nazwa Towaru",
            "Cena",
            "Ilosc",
            "Wartosc Netto"};
		
		String[][] data = new String[0][0];
		
		tabela = new JTable(data, columnNames);
		tabela.setDefaultEditor(Object.class, null);
		tabela.getTableHeader().setReorderingAllowed(false);
		
		scrollPane1 = new JScrollPane(tabela);
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		p.setPreferredSize(new Dimension(600, 200));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 0, 10);
		
		jlbNrZam = new JLabel("Numer Zamowienia:");
		jtfNrZam = new JTextField("Numer Zamowienia");
		jtfNrZam.setEditable(false);
		jlbTermin = new JLabel("Termin Realizacji:");
		jtfTermin = new JTextField();
		jtfTermin.setEditable(false);
		jlbDataReal = new JLabel("Data Realizacji:");
		jtfDataReal = new JTextField();
		jtfDataReal.setEditable(false);
		jlbDataWys = new JLabel("Data Wystawienia:");
		jtfDataWys = new JTextField();
		jtfDataWys.setEditable(false);
		jlbSposDos = new JLabel("Sposob Dostawy:");
		jtfSposDos = new JTextField();
		jtfSposDos.setEditable(false);
		jlbKosztDos = new JLabel("Koszt Dostawy:");
		jtfKosztDos = new JTextField();
		jtfKosztDos.setEditable(false);
		jlbWartoscTow = new JLabel("Wartosc Towarów:");
		jtfWartoscTow = new JTextField();
		jtfWartoscTow.setEditable(false);
		jlbKosztZam = new JLabel("Koszt Zamowienia:");
		jtfKosztZam = new JTextField();
		jtfKosztZam.setEditable(false);
		jlbDostawca = new JLabel("Dostawca:");
		jtfDostawca = new JTextField();
		jtfDostawca.setEditable(false);
//		jlbLp = new JLabel("Lp");
//		jlbNazwaTowaru = new JLabel("Nazwa Towaru");
//		jlbCena = new JLabel("Cena");
//		jlbIlosc = new JLabel("Ilosc");
//		jlbWartosc = new JLabel("Wartosc");
		//area = new JTextArea();
		
		c.gridx = 0; c.gridy = 0;
        p.add(jlbNrZam,c);
        c.gridx += 3;
        p.add(jtfNrZam,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTermin,c);
        c.gridx += 3;
        p.add(jtfTermin,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbDataReal,c);
        c.gridx += 3;
        p.add(jtfDataReal,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbDataWys,c);
        c.gridx += 3;
        p.add(jtfDataWys,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbSposDos,c);
        c.gridx += 3;
        p.add(jtfSposDos,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbKosztDos,c);
        c.gridx += 3;
        p.add(jtfKosztDos,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbWartoscTow,c);
        c.gridx += 3;
        p.add(jtfWartoscTow,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbKosztZam,c);
        c.gridx += 3;
        p.add(jtfKosztZam,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbDostawca,c);
        c.gridx += 3;
        p.add(jtfDostawca,c);
//        c.gridx = 0; c.gridy = 10;
//        p.add(jlbLp,c);
//        c.gridx = 1; c.gridy = 10;
//        p.add(jlbNazwaTowaru,c);
//        c.gridx = 2; c.gridy = 10;
//        p.add(jlbCena,c);
//        c.gridx = 3; c.gridy = 10;
//        p.add(jlbIlosc,c);
//        c.gridx = 4; c.gridy = 10;
//        p.add(jlbWartosc,c);
        //c.gridx = 0; c.gridy = 12;
   
       // scrollPane.add(tabela);

//      
        splitPane1.setTopComponent(p);
        splitPane1.setBottomComponent(scrollPane1);
        splitPane.setRightComponent(splitPane1);
        //splitPane1.
     
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
			String sql = "SELECT IdZamowienie, NumerZamowienia, TerminRealizacji, DataRealizacji, DataWystawienia, sposobdostawy.SposobDostawy, KosztDostawy,WartoscTowarow, KosztZamowienia, dostawca.NazwaSkrocona FROM zamowienie INNER JOIN sposobdostawy ON sposobdostawy.IdSposobDostawy=zamowienie.SposobDostawy INNER JOIN dostawca ON dostawca.IdDostawca=zamowienie.IdDostawcy WHERE NumerZamowienia='"+sel+"'";
			
			//int id = 0;
			//String sql = "SELECT NumerZamowienia, TerminRealizacji, DataRealizacji, DataWystawienia, SposobDostawy, KosztDostawy,WartoscTowarow, KosztZamowienia FROM zamowienie WHERE NumerZamowienia='"+sel+"'";
			
			try {
				ResultSet rs = polaczenie.sqlSelect(sql);
				tabPom = new String[10];
				
				rs.next();
				for(int i = 0;i<tabPom.length;i++)
				{
					tabPom[i]=rs.getString(i+1);
				}
				jtfNrZam.setText(tabPom[1]);
				jtfTermin.setText(tabPom[2]);
				jtfDataReal.setText(tabPom[3]);
				jtfDataWys.setText(tabPom[4]);
				jtfSposDos.setText(tabPom[5]);
				jtfKosztDos.setText(tabPom[6]);
				jtfWartoscTow.setText(tabPom[7]);
				jtfKosztZam.setText(tabPom[8]);
				jtfDostawca.setText(tabPom[9]);
				int id = Integer.parseInt(tabPom[0]);
	
				String query1 = "SELECT Lp,towar.NazwaTowaru,Cena,Ilosc,WartoscNetto FROM zamowienietowar INNER JOIN towar ON towar.IdTowar = zamowienietowar.IdTowar WHERE zamowienietowar.IdZamowienie = '"+id+"'";
				ResultSet result = polaczenie.sqlSelect(query1);
				result.last();
				int rozmiar = result.getRow();
				result.beforeFirst();
				towary = new String[rozmiar][5]; 
	int j=0;
				while(result.next())
				{
					towary[j][0]=result.getString(1);
					towary[j][1]=result.getString(2);
					towary[j][2]=result.getString(3);
					towary[j][3]=result.getString(4);
					towary[j][4]=result.getString(5);
					
					j++;
				}
				//data=towary;
				//repaint();
	
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
				//tableModel.fireTableDataChanged();
				//String s = Arrays.deepToString(towary);
	//			for (int i = 0;i<rozmiar;i++)
	//			{
	//				t[i] = Arrays.deepToString(towary[i]);
	//			}
				//area.setText(s);
				//area=setText();
				//System.out.println(s);
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
	@Override
	public void keyPressed(KeyEvent arg0) { }
	@Override
	public void keyReleased(KeyEvent arg0) { szukaj(search.getText()); }
	@Override
	public void keyTyped(KeyEvent arg0) { }
}

