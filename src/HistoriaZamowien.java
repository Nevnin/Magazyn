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
	public JList<String> list;
	private JTable tabela;
	private String[] tab;
	private JSplitPane splitPane,splitPane1;
	private JScrollPane scrollPane,scrollPane1;
	private JLabel jlbNrZam,jlbTermin,jlbDataReal,jlbDataWys,jlbSposDos,jlbKosztDos,jlbWartoscTow,jlbKosztZam,jlbDostawca;
	private JTextField search,jtfNrZam,jtfTermin,jtfDataReal,jtfDataWys,jtfSposDos,jtfKosztDos,jtfWartoscTow,jtfKosztZam,jtfDostawca;
	SzczegolyZamowienia sz = new SzczegolyZamowienia();
	public HistoriaZamowien()
	{
		try {
			polaczenie = new Polaczenie();
			String sql = "SELECT * FROM zamowienie ORDER BY NumerZamowienia ASC";
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
		//list.setPreferredSize(new Dimension(150, 150));
		list.setAlignmentX(CENTER_ALIGNMENT);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//list.setPreferredSize(new Dimension(200, 200));
		
		scrollPane.setViewportView(list);
		panel.add(search);
		search.setMaximumSize(new Dimension(200, 20));
		panel.add(scrollPane);
		splitPane.setLeftComponent(panel);
		//scrollPane1.setViewportView(list1);
		
		
        
        splitPane.setRightComponent(sz);
 
     
        add(splitPane);
        
        
        ustawNasluchZdarzen();
	}
	private void ustawNasluchZdarzen(){
		list.addListSelectionListener(this);
		search.addKeyListener(this);
	}
	public void valueChanged(ListSelectionEvent arg0) {
		
		if(arg0.getValueIsAdjusting())
		{	
			String sel = list.getSelectedValue().toString();
			remove(sz);
			repaint();
			sz = new SzczegolyZamowienia(sel);
		    splitPane.setRightComponent(sz);
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

