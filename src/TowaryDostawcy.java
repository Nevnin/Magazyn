
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
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
import javax.swing.JTabbedPane;

public class TowaryDostawcy extends JPanel implements ListSelectionListener, KeyListener{
	private Polaczenie polaczenie;
	private JList<String> list,listaTowary;
	private JTable tabela, tabela2;
	private String[] tab, tabTow, podswietlenieTowarow;
	private String[] columnNames = {"NazwaTowaruWgDostawcy",
            "KodTowaruWgDostawcy",
            "Cena",
            "DataOd",
            "DataDo"};
	String[][] towary;
	private String[] columnNames2 = 
		{"NazwaDostawcy",
        "KodTowaru",
        "KodTowaruWgDostawcy"};
	private JSplitPane splitPane,splitPane1,splitPane2,splitPane3;
	private JScrollPane scrollPane,scrollPane1,scrollPane2,scrollPane3;
	private JTabbedPane tabbedPane;
	private JLabel jlbNrZam, jlbNazwa;
	private JTextField search, search1,search2,search3;
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
		
		try {
			polaczenie = new Polaczenie();
			String sql1 = "SELECT NazwaTowaru, KodTowaru FROM towar";
			ResultSet rs1 = polaczenie.sqlSelect(sql1);
			rs1.last();
			int rozmiar = rs1.getRow();
			rs1.beforeFirst();
			int i = 0;
			tabTow = new String [rozmiar];
			while(rs1.next())
			{
				tabTow[i] = rs1.getString("NazwaTowaru");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		tabbedPane = new JTabbedPane();
		
		splitPane = new JSplitPane();
		splitPane1 = new JSplitPane();
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(200,600));
		
		splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		scrollPane = new JScrollPane();
		
		 
		
		search = new JTextField();
		search2 = new JTextField();
		
		search2.setMinimumSize(new Dimension(300, 20));
		
		panel.add(scrollPane);
		search2.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) 
			{
				szukajTowaru(search2.getText()); 
				
			}
			@Override
			public void keyTyped(KeyEvent e) { }
		});
		
		jlbNrZam = new JLabel("Towary");
		list = new JList<String>(tab);
		list.setMinimumSize(new Dimension(150,150));
		list.setPreferredSize(new Dimension(150, 150));
		list.setAlignmentX(CENTER_ALIGNMENT);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener(){
	
			@Override
	public void valueChanged(ListSelectionEvent e) {
		
		if(e.getValueIsAdjusting())
		{
			
			String[] tabPom;
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
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}});
		
		scrollPane.setViewportView(list);
		panel.add(search);
		search.setMaximumSize(new Dimension(200, 20));
		panel.add(scrollPane);
		search.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) { szukaj(search.getText());}
			@Override
			public void keyTyped(KeyEvent e) { }
			
		});
		
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
		
		tabela.setAutoCreateRowSorter(true);
		

        splitPane1.setTopComponent(search2);
        splitPane1.setBottomComponent(scrollPane1);
        splitPane.setRightComponent(splitPane1);
        
        tabbedPane.addTab("Po dostawcach", null, splitPane, "po dostawcach");

        //     
        //
        //
        //
        //
        // 
        //
        //
        //
        //
        //
        splitPane2 = new JSplitPane();
		splitPane3 = new JSplitPane();
		scrollPane2 = new JScrollPane();
		
		splitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
		panel2.setPreferredSize(new Dimension(200,600));
		
		
		
		search1 = new JTextField();
		search3 = new JTextField();
		
		search3.setMinimumSize(new Dimension(300, 20));
		
		panel.add(scrollPane);
		search3.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) 
			{
				szukajTowaru2(search3.getText()); 
				
			}
			@Override
			public void keyTyped(KeyEvent e) { }
		});
		
		jlbNazwa = new JLabel("Dostawcy");
		listaTowary = new JList<String>(tabTow);
		listaTowary.setMinimumSize(new Dimension(150,150));
		listaTowary.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaTowary.setPreferredSize(new Dimension(150, 150));
		listaTowary.setAlignmentX(CENTER_ALIGNMENT);
		listaTowary.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
			
			if(e.getValueIsAdjusting()==true)
			{
				
				String[] tabPom;
				String sel = listaTowary.getSelectedValue().toString();
				String sql = "SELECT IdTowar FROM towar WHERE NazwaTowaru='"+sel+"'";
				
				
				try {
					ResultSet rs = polaczenie.sqlSelect(sql);
					tabPom = new String[1];
					
					rs.next();
					for(int i = 0;i<tabPom.length;i++)
					{
						tabPom[i]=rs.getString(i+1);
					}
					int id = Integer.parseInt(tabPom[0]);
		
					String query1 = "SELECT DISTINCT dostawca.NazwaSkrocona, KodTowaru, dostawcatowar.KodTowaruWgDostawcy FROM `dostawcatowar` INNER JOIN towar ON towar.IdTowar=dostawcatowar.IdTowar INNER JOIN dostawca ON dostawca.IdDostawca=dostawcatowar.IdDostawca WHERE towar.IdTowar='"+id+"'";
					ResultSet result = polaczenie.sqlSelect(query1);
					result.last();
					int rozmiar = result.getRow();
					result.beforeFirst();
					towary = new String[rozmiar][20]; 
		int j=0;
					while(result.next())
					{
						towary[j][0]=result.getString(1);
						towary[j][1]=result.getString(2);
						towary[j][2]=result.getString(3);
						
						j++;
					}
				
		
					String[] columnNames2 = 
						{"NazwaDostawcy",
			            "KodTowaru",
			            "KodTowaruWgDostawcy"};
					DefaultTableModel tableModel = new DefaultTableModel(0,0);
					tableModel.setColumnIdentifiers(columnNames2);
					tabela2.setModel(tableModel);
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
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}});
		
		scrollPane2.setViewportView(listaTowary);
		
		search1.setMaximumSize(new Dimension(200, 20));
		
		splitPane2.setLeftComponent(panel2);
		
		scrollPane2.setViewportView(listaTowary);
		panel2.add(search1);
		search1.setMaximumSize(new Dimension(200, 20));
		panel2.add(scrollPane2);
		search1.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) { }
			@Override
			public void keyReleased(KeyEvent e) { szukaj2(search1.getText());}
			@Override
			public void keyTyped(KeyEvent e) { }
			
		});
		
		//scrollPane1.setViewportView(list1);
		
		String[] columnNames2 = 
			{"NazwaDostawcy",
            "KodTowaru",
            "KodTowaruWgDostawcy"};
		
		String[][] data2 = new String[0][0];
		
		tabela2 = new JTable(data, columnNames2);
		tabela2.setDefaultEditor(Object.class, null);
		tabela2.getTableHeader().setReorderingAllowed(false);
		
		tabela2.setAutoCreateRowSorter(true);
		
		scrollPane3 = new JScrollPane(tabela2);
		
		splitPane3.setTopComponent(search3);
        splitPane3.setBottomComponent(scrollPane3);
        splitPane2.setRightComponent(splitPane3);
		
		
		     
        tabbedPane.addTab("Po towarach", null, splitPane2, "po towarach");
        
       
        
        add(tabbedPane);
        
       
        ustawNasluchZdarzen();
	}
	private void ustawNasluchZdarzen(){
		list.addListSelectionListener(this);
		listaTowary.addListSelectionListener(this);
		search.addKeyListener(this);
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
	
	public void szukaj2(String text){
		try {
			polaczenie = new Polaczenie();
			String sql = "SELECT NazwaTowaru FROM towar WHERE NazwaTowaru LIKE '%"+text+"%'";
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
			//list.clearSelection();
			listaTowary.setListData(tab);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
public void szukajTowaru(String text){
		
		try {
			String sel = list.getSelectedValue().toString();
			polaczenie = new Polaczenie();
			String sql = "SELECT NazwaSkrocona, NazwaTowaruWgDostawcy FROM dostawcatowar INNER JOIN dostawca ON dostawcatowar.IdDostawca = dostawca.IdDostawca WHERE NazwaTowaruWgDostawcy LIKE '%"+text+"%' AND NazwaSkrocona = '"+sel+"'";
			ResultSet rs = polaczenie.sqlSelect(sql);
			
			System.out.println(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			String[] data = null;
			DefaultTableModel tableModel1 = new DefaultTableModel(0,0); 
			tableModel1.setColumnIdentifiers(columnNames);
			tabela.setModel(tableModel1);
			podswietlenieTowarow = new String[rozmiar];
			while(rs.next()){
				podswietlenieTowarow[i] = rs.getString("NazwaTowaruWgDostawcy");
				i++;
			}
		for (int k=0;k<towary.length;k++)	
		{
			for(int l=0;l<podswietlenieTowarow.length;l++)
			{
			if(podswietlenieTowarow[l].equals(towary[k][0]))
			{	
				System.out.println(podswietlenieTowarow[l]+"Dziala");
				
				
					data = new String[towary[0].length];
					for(int z = 0;z<5;z++){
						data[z]= towary[k][z];
					}
					tableModel1.addRow(data);
					break;
				
			}
			
			}
		}
		tabela.setModel(tableModel1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

public void szukajTowaru2(String text){
	
	try {
		String sel = listaTowary.getSelectedValue().toString();
		polaczenie = new Polaczenie();
		String sql = "SELECT NazwaSkrocona, NazwaTowaru FROM dostawcatowar INNER JOIN dostawca ON dostawcatowar.IdDostawca = dostawca.IdDostawca INNER JOIN towar ON towar.IdTowar = dostawcatowar.IdTowar WHERE NazwaSkrocona LIKE '%"+text+"%' AND NazwaTowaru = '"+sel+"'";
		ResultSet rs = polaczenie.sqlSelect(sql);
		
		System.out.println(sql);
		rs.last();
		int rozmiar = rs.getRow();
		rs.beforeFirst();
		int i = 0;
		String[] data = null;
		DefaultTableModel tableModel1 = new DefaultTableModel(0,0); 
		tableModel1.setColumnIdentifiers(columnNames2);
		tabela.setModel(tableModel1);
		podswietlenieTowarow = new String[rozmiar];
		while(rs.next()){
			podswietlenieTowarow[i] = rs.getString("NazwaTowaruWgDostawcy");
			i++;
		}
	for (int k=0;k<towary.length;k++)	
	{
		for(int l=0;l<podswietlenieTowarow.length;l++)
		{
		if(podswietlenieTowarow[l].equals(towary[k][0]))
		{	
			System.out.println(podswietlenieTowarow[l]+"Dziala");
			
			
				data = new String[towary[0].length];
				for(int z = 0;z<5;z++){
					data[z]= towary[k][z];
				}
				tableModel1.addRow(data);
				break;
			
		}
		
		}
	}
	tabela.setModel(tableModel1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
//	public void szukajTowaru(String text){
//
//	}
	
	
	
}


