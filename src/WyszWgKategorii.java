
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

public class WyszWgKategorii extends JPanel implements ListSelectionListener, KeyListener{
	private Polaczenie polaczenie;
	private JList<String>list;
	private String[] tab;
	private JSplitPane splitPane, splitPane1;
	private JScrollPane scrollPane, scrollPane1;
	private JTextField search;
	private JTable tabela;
	
	
	public WyszWgKategorii(){
		try{
			polaczenie = new Polaczenie();
			String sql = "SELECT * FROM kategoria";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tab = new String [rozmiar];
			while(rs.next()){
				tab[i] = rs.getString("Nazwa");
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
		list.setAlignmentX(CENTER_ALIGNMENT);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(list);
		panel.add(search);
		search.setMaximumSize(new Dimension(200, 20));
		panel.add(scrollPane);
		splitPane.setLeftComponent(panel);
		
		String[] columnNames = 
			{"Nazwa Towaru",
			"Data realizacji",
            "Termin realizacji",
            "Nazwa dostawcy",
            "Sposob dostawy"};
		
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
		
        splitPane.setRightComponent(scrollPane1);
 
        add(splitPane);
       
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
		//szukaj(search.getText());
		
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
			String sql = "SELECT IdKategoria FROM kategoria WHERE IdKategoria='"+sel+"'";
			System.out.println(sel);
			try {
				ResultSet rs = polaczenie.sqlSelect(sql);
				idT = new String[1];
				
				rs.next();
				for(int i = 0;i<idT.length;i++)
				{
					idT[i]=rs.getString("IdKategoria");
				}
		
				int id = Integer.parseInt(idT[0]);
	
				String query1 = "SELECT towar.NazwaTowaru, zamowienie.DataRealizacji, zamowienie.TerminRealizacji, dostawca.NazwaSkrocona, sposobdostawy.SposobDostawy "
						+ "FROM `zamowienie` "
						+ "INNER JOIN zamowienietowar ON zamowienie.IdZamowienie = zamowienietowar.IdZamowienie "
						+ "INNER JOIN towar ON towar.IdTowar = zamowienietowar.IdZamowienieTowar "
						+ "INNER JOIN dostawca ON dostawca.IdDostawca = zamowienie.IdDostawcy "
						+ "INNER JOIN sposobdostawy ON sposobdostawy.IdSposobDostawy = zamowienie.IdSposobDostawy "
						+ "INNER JOIN kategoria ON kategoria.IdKategoria = towar.IdKategoria" 
						+ "WHERE kategoria.IdKategoria  = '"+id+"'";
				System.out.println(query1);
				ResultSet result = polaczenie.sqlSelect(query1);
				result.last();
				int rozmiar = result.getRow();
				result.beforeFirst();
				zamowienie = new String[rozmiar][5]; 
	int j=0;
				while(result.next())
				{
					zamowienie[j][0]=result.getString(1);
					zamowienie[j][1]=result.getString(2);
					zamowienie[j][2]=result.getString(3);
					zamowienie[j][3]=result.getString(4);
					zamowienie[j][4]=result.getString(5);
					
					j++;
				}
			
	
				String[] columnNames = 
					{"Nazwa Towaru",
							"Data realizacji",
				            "Termin realizacji",
				            "Nazwa dostawcy",
				            "Sposob dostawy"};
				DefaultTableModel tableModel = new DefaultTableModel(0,0);
				tableModel.setColumnIdentifiers(columnNames);
				tabela.setModel(tableModel);
				for (int i = 0; i < zamowienie.length; i++) {
					String[] data = new String[zamowienie[0].length];
					for(int z = 0;z<5;z++){
						data[z]= zamowienie[i][z];
					}
					tableModel.addRow(data);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	/*
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
	}*/

}