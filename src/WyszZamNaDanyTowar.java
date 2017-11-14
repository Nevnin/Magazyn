import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
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

public class WyszZamNaDanyTowar extends JPanel implements ListSelectionListener, KeyListener{
	private Polaczenie polaczenie;
	private JList<String> list;
	private JTable tabela;
	private String[] tab;
	private JSplitPane splitPane,splitPane1;
	private JScrollPane scrollPane,scrollPane1;
	private JLabel jlbNrZam,jlbTermin,jlbDataReal,jlbDataWys,jlbSposDos,jlbKosztDos,jlbWartoscTow,jlbKosztZam,jlbDostawca;
	private JTextField search,jtfNrZam,jtfTermin,jtfDataReal,jtfDataWys,jtfSposDos,jtfKosztDos,jtfWartoscTow,jtfKosztZam,jtfDostawca;
	private JDialog dialog;
	SzczegolyZamowienia szzam;
	DecimalFormat df;
	public WyszZamNaDanyTowar()
	{
		try {
			polaczenie = new Polaczenie();
			String sql = "SELECT * FROM towar";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tab = new String [rozmiar];
			while(rs.next())
			{
				tab[i] = rs.getString("NazwaTowaru");
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
		
		df = new DecimalFormat("###,###.00");
		DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(' ');
		df.setDecimalFormatSymbols(symbols);

		
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
			{"Nr Zamowienia",
            "Termin Realizacji",
            "Data Realizacji",
            "Data Wystawienia",
            "Wartosc Zamowienia"};
		
		String[][] data = new String[0][0];
		
		tabela = new JTable(data, columnNames);
		tabela.setDefaultEditor(Object.class, null);
		tabela.getTableHeader().setReorderingAllowed(false);
		
		scrollPane1 = new JScrollPane(tabela);
		final JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		p.setPreferredSize(new Dimension(600, 200));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 0, 10);
		
	
     
        splitPane.setRightComponent(scrollPane1);
 
     
        add(splitPane);
       
        
        ustawNasluchZdarzen();
        
        
        tabela.addMouseListener
        (
          new MouseAdapter()
          {
            public void mouseClicked(MouseEvent e)
            {
              if (e.getClickCount() == 2)
              {		
            	  int selected=tabela.getSelectedRow();
            	  
            	  String nrZam=tabela.getValueAt(selected, 0).toString();
            	  
            	  dialog = new JDialog();
            	  szzam= new SzczegolyZamowienia(nrZam);
//            	  his.list.setSelectedValue();
            	  dialog.setContentPane(szzam);
            	  dialog.setVisible(true);
            	 // dialog.setMinimumSize(new Dimension(900, 600));
            	  dialog.pack();
            	  dialog.setLocationRelativeTo(p);
            	  
            	
              }
              if (e.getClickCount() == 1)
              {
              }
            }
          }
        );
	}
     
	private void ustawNasluchZdarzen(){
		list.addListSelectionListener(this);
		search.addKeyListener(this);
	}
	public void valueChanged(ListSelectionEvent arg0) {
		
		if(arg0.getValueIsAdjusting())
		{
			String[] idT;
			String[][] towary;
			String sel = list.getSelectedValue().toString();
			String sql = "SELECT IdTowar FROM towar WHERE NazwaTowaru='"+sel+"'";
			
			try {
				ResultSet rs = polaczenie.sqlSelect(sql);
				idT = new String[1];
				
				rs.next();
				for(int i = 0;i<idT.length;i++)
				{
					idT[i]=rs.getString("IdTowar");
				}
		
				int id = Integer.parseInt(idT[0]);
	
				String query1 = "SELECT DISTINCT zamowienie.NumerZamowienia, zamowienie.TerminRealizacji, zamowienie.DataRealizacji, zamowienie.DataWystawienia, zamowienie.CalkowitaWartoscZamowienia FROM `zamowienietowar` INNER JOIN zamowienie ON zamowienie.IdZamowienie = zamowienietowar.IdZamowienie WHERE IdTowar = '"+id+"'";
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
					double cena = Double.parseDouble(result.getString(5));
					towary[j][4] = df.format(cena);
					
					j++;
				}
			
	
				String[] columnNames = 
					{"Nr Zamowienia",
		            "Termin Realizacji",
		            "Data Realizacji",
		            "Data Wystawienia",
		            "Wartosc Zamowienia"};
				DefaultTableModel tableModel = new DefaultTableModel(0,0);
				tableModel.setColumnIdentifiers(columnNames);
				tabela.setModel(tableModel);
				DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
				tableRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
				tabela.getColumnModel().getColumn(4).setCellRenderer(tableRenderer);
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

	public void keyPressed(KeyEvent arg0) { }

	public void keyReleased(KeyEvent arg0) { szukaj(search.getText()); }
	public void keyTyped(KeyEvent arg0) { }
}

