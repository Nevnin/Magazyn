
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

public class TowaryDostawcy extends JPanel implements ListSelectionListener, KeyListener{
	private Polaczenie polaczenie;
	private JList<String> list,listaTowary;
	private JTable tabela;
	private String[] tab;
	private JSplitPane splitPane,splitPane1;
	private JScrollPane scrollPane,scrollPane1;
	private JLabel jlbNrZam,jlbTermin,jlbDataReal,jlbDataWys,jlbSposDos,jlbKosztDos,jlbWartoscTow,jlbKosztZam,jlbDostawca;
	private JTextField search,search1,jtfNrZam,jtfTermin,jtfDataReal,jtfDataWys,jtfSposDos,jtfKosztDos,jtfWartoscTow,jtfKosztZam,jtfDostawca;
	public TowaryDostawcy()
	{
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
			e.printStackTrace();
		}
		
		splitPane = new JSplitPane();
		splitPane1 = new JSplitPane();
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(200,600));
		
		splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		scrollPane = new JScrollPane();
		
		search = new JTextField();
		search1 = new JTextField();
		jlbNrZam = new JLabel("Towary");
		list = new JList<String>(tab);
		list.setMinimumSize(new Dimension(150,150));
		list.setPreferredSize(new Dimension(150, 150));
		list.setAlignmentX(CENTER_ALIGNMENT);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(list);
		panel.add(search);
		search.setMaximumSize(new Dimension(200, 20));
		search1.setMinimumSize(new Dimension(300, 20));
		panel.add(scrollPane);
		
		splitPane.setLeftComponent(panel);
		//scrollPane1.setViewportView(list1);
		
		String[] columnNames = 
			{"NazwaTowaruWgDostawcy",
            "KodTowaruWgDostawcy",
            "Cena",
            "DataOd",
            "DataDo"};
		
		String[][] data = new String[0][0];
		
		tabela = new JTable(data, columnNames);
		tabela.setDefaultEditor(Object.class, null);
		tabela.getTableHeader().setReorderingAllowed(false);
		
		scrollPane1 = new JScrollPane(tabela);
//		JPanel p = new JPanel();
//		p.setLayout(new GridBagLayout());
//		GridBagConstraints c = new GridBagConstraints();
//		p.setPreferredSize(new Dimension(600, 200));
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.insets = new Insets(0, 10, 0, 10);
		
		
		

        splitPane1.setTopComponent(jlbNrZam);
        splitPane1.setBottomComponent(scrollPane1);
        splitPane.setRightComponent(splitPane1);
 
     
        add(splitPane);
       // add(splitPane1);
        
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
			String sql = "SELECT IdDostawca FROM dostawca WHERE NazwaSkrocona='"+sel+"'";
			
			
			try {
				ResultSet rs = polaczenie.sqlSelect(sql);
				tabPom = new String[1];
				
				rs.next();
				for(int i = 0;i<tabPom.length;i++)
				{
					tabPom[i]=rs.getString(i+1);
				}
				int id = Integer.parseInt(tabPom[0]);
	
				String query1 = "SELECT NazwaTowaruWgDostawcy,KodTowaruWgDostawcy,Cena,DataOd,DataDo FROM dostawcatowar WHERE dostawcatowar.IdDostawca = '"+id+"'";
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
			
	
				String[] columnNames = 
					{"NazwaTowaruWgDostawcy",
		            "KodTowaruWgDostawcy",
		            "Cena",
		            "DataOd",
		            "DataDo"};
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
	
//	public void szukajTowaru(String text){
//
//	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) { }
	@Override
	public void keyReleased(KeyEvent arg0) { szukaj(search.getText()); }
	@Override
	public void keyTyped(KeyEvent arg0) { }
}


