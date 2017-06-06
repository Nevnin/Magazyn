import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
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

public class WyszWgWartosci extends JPanel implements ListSelectionListener, KeyListener{
	private Polaczenie polaczenie;
	private JList<String>list;
	private String[] tab;
	private JSplitPane splitPane, splitPane1;
	private JScrollPane scrollPane, scrollPane1;
	private JTextField search, jtfDostawcaNazwaSkrocona, jtfSposobDostawy, jtfCenaCalkowita, jtfNumerZamowienia, jtfTerminRealizacji;
	private JTable tabela;
	private JLabel jlbDostawcaNazwaSkrocona, jlbSposobDostawy, jlbCenaCalkowita, jlbNumerZamowienia, jlbTerminRealizacji;
	private DefaultTableModel tableModel;
	
	public WyszWgWartosci(){
		try{
			polaczenie = new Polaczenie();
			String sql = "SELECT *, CONCAT(CalkowitaWartoscZamowienia, ' zl   ', NumerZamowienia) AS Koszt_Numer FROM `zamowienie` GROUP BY CalkowitaWartoscZamowienia ORDER BY CalkowitaWartoscZamowienia DESC";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tab = new String [rozmiar];
			while(rs.next()){
				tab[i] = rs.getString("Koszt_Numer");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		splitPane = new JSplitPane();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension (200,600));
		splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		scrollPane = new JScrollPane();
		search = new JTextField();
		
		list = new JList<String>(tab);
		list.setMinimumSize(new Dimension(150,150));
		list.setPreferredSize(new Dimension(150,150));
		list.setAlignmentX(RIGHT_ALIGNMENT);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListCellRenderer doPrawej = (DefaultListCellRenderer)list.getCellRenderer();
		doPrawej.setHorizontalAlignment(SwingConstants.RIGHT);
		
		scrollPane.setViewportView(list);
		panel.add(search);
		search.setMaximumSize(new Dimension(200, 20));
		panel.add(scrollPane);
		splitPane.setLeftComponent(panel);
		
		String[] columnNames = 
			{"Nazwa Towaru",
					"Kod towaru",
		            "Ilosc",
		            "Cena"};

		tableModel = new DefaultTableModel(0,0);
		tableModel.setColumnIdentifiers(columnNames);
		tabela = new JTable();
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setModel(tableModel);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setEnabled(false);
		tabela.setAutoCreateRowSorter(true);
		int[] tabKolSzer = {155,175,175,125};
		System.out.println(columnNames.length+" , ");
		for(int i=0; i<columnNames.length; i++){
			tabela.getColumnModel().getColumn(i).setPreferredWidth(tabKolSzer[i]);
			DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
			if(i==3|| i==2){
				tableRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
				tabela.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
			}
//			else if(i==2 || i ==3){
//				tableRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//				tablicaTowarow.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
//			}
			else{
				tableRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				tabela.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
			}
		}

		scrollPane1 = new JScrollPane(tabela);
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		p.setPreferredSize(new Dimension(600, 200));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 0, 10);
		
		jlbNumerZamowienia = new JLabel("Numer zamowienia");
		jtfNumerZamowienia = new JTextField("Numer zamowienia");
		jtfNumerZamowienia.setEditable(false);
		jlbDostawcaNazwaSkrocona = new JLabel("Dostawca:");
		jtfDostawcaNazwaSkrocona = new JTextField("");
		jtfDostawcaNazwaSkrocona.setEditable(false);
		jlbTerminRealizacji = new JLabel("Termin Realizacji:");
		jtfTerminRealizacji = new JTextField();
		jtfTerminRealizacji.setEditable(false);
		jlbCenaCalkowita = new JLabel("Cena:");
		jtfCenaCalkowita = new JTextField();
		jtfCenaCalkowita.setEditable(false);
		jlbSposobDostawy = new JLabel("Sposob dostawy:");
		jtfSposobDostawy = new JTextField();
		jtfSposobDostawy.setEditable(false);
		
		c.gridx = 0; c.gridy = 0;
        p.add(jlbNumerZamowienia,c);
        c.gridx += 2;
        p.add(jtfNumerZamowienia,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbDostawcaNazwaSkrocona,c);
        c.gridx += 2;
        p.add(jtfDostawcaNazwaSkrocona,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTerminRealizacji,c);
        c.gridx += 2;
        p.add(jtfTerminRealizacji,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbCenaCalkowita,c);
        c.gridx += 2;
        p.add(jtfCenaCalkowita,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbSposobDostawy,c);
        c.gridx += 2;
        p.add(jtfSposobDostawy,c);
        c.gridx = 0; c.gridy++;
		
        splitPane1.setTopComponent(p);
        splitPane.setBottomComponent(scrollPane1);
        splitPane1.setRightComponent(scrollPane1);
        
        add(splitPane);
        add(splitPane1);
       
        ustawNasluchZdarzen();
	}
	private void ustawNasluchZdarzen(){
		list.addListSelectionListener(this);
		search.addKeyListener(this);
	}
		

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		szukaj(search.getText());
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(arg0.getValueIsAdjusting())
		{
			String[] idT;
			String[][] zamowienie;
			String sel = list.getSelectedValue().toString();
			String tabS[] = sel.split(" ");
			String sql = "SELECT IdZamowienie, NumerZamowienia, dostawca.NazwaSkrocona, CalkowitaWartoscZamowienia, TerminRealizacji, sposobdostawy.SposobDostawy FROM zamowienie INNER JOIN sposobdostawy ON sposobdostawy.IdSposobDostawy=zamowienie.IdSposobDostawy INNER JOIN dostawca ON dostawca.IdDostawca=zamowienie.IdDostawcy WHERE CalkowitaWartoscZamowienia='"+tabS[0]+"'";
			try {
				ResultSet rs = polaczenie.sqlSelect(sql);
				idT = new String[6];
				
				rs.next();
				for(int i = 0;i<idT.length;i++)
				{
					idT[i]=rs.getString(i+1);
				}
				jtfNumerZamowienia.setText(idT[1]);
				jtfDostawcaNazwaSkrocona.setText(idT[2]);
				jtfCenaCalkowita.setText(idT[3]);
				jtfTerminRealizacji.setText(idT[4]);
				jtfSposobDostawy.setText(idT[5]);
				int id = Integer.parseInt(idT[0]);
	
				String query1 = "SELECT towar.NazwaTowaru, towar.KodTowaru, CONCAT(zamowienietowar.ilosc,' ',jednostkimiary.NazwaSkrocona), CONCAT(zamowienietowar.cena,' zl')  "
						+ "FROM `zamowienie` "
						+ "INNER JOIN zamowienietowar ON zamowienie.IdZamowienie = zamowienietowar.IdZamowienie "
						+ "INNER JOIN towar ON towar.IdTowar = zamowienietowar.IdTowar "
						+ "INNER JOIN dostawca ON dostawca.IdDostawca = zamowienie.IdDostawcy "
						+ "INNER JOIN sposobdostawy ON sposobdostawy.IdSposobDostawy = zamowienie.IdSposobDostawy "
						+ "INNER JOIN jednostkimiary ON jednostkimiary.IdJednostkaMiary = towar.IdJednostkaMiary "
						+ "WHERE zamowienie.IdZamowienie  = "+id+"";
				ResultSet result = polaczenie.sqlSelect(query1);
				result.last();
				int rozmiar = result.getRow();
				result.beforeFirst();
				zamowienie = new String[rozmiar][4]; 
	int j=0;
				while(result.next())
				{
					zamowienie[j][0]=result.getString(1);
					zamowienie[j][1]=result.getString(2);
					zamowienie[j][2]=result.getString(3);
					zamowienie[j][3]=result.getString(4);
					j++;
				}
			
				tableModel.setRowCount(0);
				tabela.setModel(tableModel);
				
				for (int i = 0; i < zamowienie.length; i++) {
					String[] data = new String[zamowienie[0].length];
					for(int z = 0;z<zamowienie[0].length;z++){
						data[z]= zamowienie[i][z];
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
			String sql = "SELECT CONCAT(CalkowitaWartoscZamowienia, 'zl   ', NumerZamowienia) AS Koszt_Numer FROM `zamowienie` WHERE CalkowitaWartoscZamowienia LIKE '%"+text+"%' GROUP BY CalkowitaWartoscZamowienia ORDER BY CalkowitaWartoscZamowienia DESC ";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tab = new String[rozmiar];
			while(rs.next()){
				tab[i] = rs.getString("Koszt_Numer");
				i++;
			}
			//list.clearSelection();
			list.setListData(tab);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
