import java.awt.Component;
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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class StanMagazynowy extends JPanel  implements ListSelectionListener, KeyListener, ListCellRenderer<Object> {
	private JList<String> list;
	private String[] tab;
	private Polaczenie polaczenie;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JProgressBar jpbStanReal, jpbStanDys;
	private JLabel jlbAkt, jlbNazwa, jlbKod, jlbStanMax, jlbStanMin, jlbStanReal, jlbStanDys;
	private JTextField search, jtfNazwa, jtfKod, jtfStanMax, jtfStanMin, jtfStanMax2, jtfStanMin2, jtfStanReal, jtfStanDys;
	public StanMagazynowy(){
		try {
			polaczenie = new Polaczenie();
			String sql = "SELECT * FROM towar";
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		splitPane = new JSplitPane();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		scrollPane = new JScrollPane();
		search = new JTextField();
		list = new JList<String>(tab);
		
		//list.setMaximumSize(new Dimension(100, 100));
		list.setAlignmentX(CENTER_ALIGNMENT);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		panel.add(search);
		panel.add(scrollPane);
		splitPane.setLeftComponent(panel);
		
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.insets = new Insets(0, 10, 0, 10);  
	    
	    jlbNazwa = new JLabel("Nazwa:");
	    jtfNazwa = new JTextField();
	    jtfNazwa.setEditable(false);
	    jlbKod = new JLabel("Kod towaru:");
	    jtfKod = new JTextField();
	    jtfKod.setEditable(false);
	    jlbAkt = new JLabel("Akt");
		jlbStanMin = new JLabel("Min");
		jtfStanMin = new JTextField("0");
		jtfStanMin.setEditable(false);
		jtfStanMin2 = new JTextField("0");
		jtfStanMin2.setEditable(false);
		jlbStanMax = new JLabel("Max");
		jtfStanMax = new JTextField("0");
		jtfStanMax.setEditable(false);
		jtfStanMax2 = new JTextField("0");
		jtfStanMax2.setEditable(false);
		jlbStanReal = new JLabel("Stan rzeczywisty");
		jpbStanReal = new JProgressBar();
		jpbStanReal.setStringPainted(true);
		jtfStanReal = new JTextField("0");
		jtfStanReal.setEditable(false);
		jlbStanDys = new JLabel("Stan dysponowany");
		jpbStanDys = new JProgressBar();
		jpbStanDys.setStringPainted(true);
		jtfStanDys = new JTextField("0");
		jtfStanDys.setEditable(false);
		
		c.gridx = 0; c.gridy = 0;
        p.add(jlbNazwa,c);
        c.gridx += 2;
        p.add(jtfNazwa,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbKod,c);
        c.gridx += 2;
        p.add(jtfKod,c);
        c.gridx = 1; c.gridy++;
        p.add(jlbStanMin,c);
        c.gridx++;
        p.add(jtfStanReal,c);
        c.gridx ++;
        p.add(jlbAkt,c);
        c.gridx++;
        p.add(jlbStanMax,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbStanReal,c);
        c.gridx++;
        p.add(jtfStanMin,c);
        c.gridx++;
        p.add(jpbStanReal,c);
        c.gridx++;
        p.add(jtfStanReal,c);
        c.gridx++;
        p.add(jtfStanMax,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbStanDys,c);
        c.gridx++;
        p.add(jtfStanMin2,c);
        c.gridx++;
        p.add(jpbStanDys,c);
        c.gridx++;
        p.add(jtfStanDys,c);
        c.gridx++;
        p.add(jtfStanMax2,c);
		splitPane.setRightComponent(p);
		
		add(splitPane);
		
		ustawNasluchZdarzen();
	}
	private void ustawNasluchZdarzen(){
		list.addListSelectionListener(this);
		search.addKeyListener(this);
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getValueIsAdjusting()) {
			String[] tabPom;
			String sel = list.getSelectedValue().toString();
			String sql = "SELECT NazwaTowaru, MinStanMagazynowy, MaxStanMagazynowy, StanMagazynowyRzeczywisty, StanMagazynowyDysponowany, KodTowaru FROM towar WHERE NazwaTowaru='"+sel+"'";
			try {
				ResultSet rs = polaczenie.sqlSelect(sql);
				tabPom = new String[6];
				rs.next();
				for(int i=0; i<tabPom.length; i++){
					tabPom[i] = rs.getString(i+1);
					//System.out.println(rs.getMetaData());
				}
				jtfNazwa.setText(tabPom[0]);
				jtfKod.setText(tabPom[5]);
				int min = Integer.parseInt(tabPom[1]);
				int max = Integer.parseInt(tabPom[2]);
				int real = Integer.parseInt(tabPom[3]);
				int dys = Integer.parseInt(tabPom[4]);
				jtfStanMin.setText(tabPom[1]);
				jtfStanMin2.setText(tabPom[1]);
				jtfStanReal.setText(tabPom[3]);
				jtfStanDys.setText(tabPom[4]);
				jpbStanReal.setMaximum(max);
				jpbStanReal.setValue(real);
				jpbStanDys.setMaximum(max);
				jpbStanDys.setValue(dys);
				jtfStanMax.setText(tabPom[2]);
				jtfStanMax2.setText(tabPom[2]);
				if(real<=min){
					jpbStanReal.setForeground(java.awt.Color.RED);
					jtfStanReal.setForeground(java.awt.Color.RED);
				}
				else{ 
					jpbStanReal.setForeground(new java.awt.Color(163, 184, 204));
					jtfStanReal.setForeground(java.awt.Color.BLACK);
				}
				if(dys<=min){
					jpbStanDys.setForeground(java.awt.Color.RED);
					jtfStanDys.setForeground(java.awt.Color.RED);
				}
				else{ 
					jpbStanDys.setForeground(new java.awt.Color(163, 184, 204));
					jtfStanDys.setForeground(java.awt.Color.BLACK);
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
          }		
	}
	public void szukaj(String text){
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
			list.setListData(tab);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {	 }
	@Override
	public void keyReleased(KeyEvent arg0) { szukaj(search.getText()); }
	@Override
	public void keyTyped(KeyEvent arg0) { }
	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		try {
			polaczenie = new Polaczenie();
			String sql = "SELECT * FROM towar";
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
