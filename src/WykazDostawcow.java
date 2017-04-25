
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WykazDostawcow extends JPanel implements ListSelectionListener, KeyListener{
	private Polaczenie polaczenie;
	private JList list;
	private String[] tab;
	private JSplitPane splitPane;
	private JScrollPane scrollPane,scrollPane1;
	private JLabel jlbNazSkroc, jlbNazPeln,jlbNIP, jlbTel1, jlbTel2,jlbTel3,jlbNazDzial,jlbNrKonta,jlbAdres,jlbKodPocz,jlbPoczta;
	private JTextField search,jtfNazSkroc,jtfNIP,jtfTel1,jtfTel2,jtfTel3,jtfNazDzial,jtfNrKonta,jtfAdres,jtfKodPocz,jtfPoczta;
	private JTextArea area, jtaNazPeln;
	private JButton jbtDodajDos, jbtDodajTow;

	public WykazDostawcow() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		splitPane = new JSplitPane();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		scrollPane = new JScrollPane();
		scrollPane1 = new JScrollPane();
		search = new JTextField();
		list = new JList(tab);
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
		Border border = BorderFactory.createLineBorder(Color.lightGray);
		
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		p.setPreferredSize(new Dimension(600, 300));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 0, 10);
		
		jlbNazSkroc = new JLabel("Nazwa Skrocona:");
		jtfNazSkroc = new JTextField();
		jtfNazSkroc.setEditable(false);
		jlbNazPeln = new JLabel("Nazwa Pelna:");
		jtaNazPeln = new JTextArea();
		jtaNazPeln.setEditable(false);
		jtaNazPeln.setPreferredSize(new Dimension(400, 40));
		jtaNazPeln.setLineWrap(true);
		jtaNazPeln.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 2, 0, 0)));
		jtaNazPeln.setBackground(null);
		jlbNIP = new JLabel("NIP:");
		jtfNIP = new JTextField();
		jtfNIP.setEditable(false);
		jlbTel1 = new JLabel("Telefon 1:");
		jtfTel1 = new JTextField();
		jtfTel1.setEditable(false);
		jlbTel2 = new JLabel("Telefon 2:");
		jtfTel2 = new JTextField();
		jtfTel2.setEditable(false);
		jlbTel3 = new JLabel("Telefon 3:");
		jtfTel3 = new JTextField();
		jtfTel3.setEditable(false);
		jlbNazDzial = new JLabel("Nazwa Dzialu:");
		jtfNazDzial = new JTextField();
		jtfNazDzial.setEditable(false);
		jlbNrKonta = new JLabel("Numer Konta:");
		jtfNrKonta = new JTextField();
		jtfNrKonta.setEditable(false);
		jlbAdres = new JLabel("Adres:");
		jtfAdres = new JTextField();
		jtfAdres.setEditable(false);
		jlbKodPocz = new JLabel("Kod Pocztowy:");
		jtfKodPocz = new JTextField();
		jtfKodPocz.setEditable(false);
		jlbPoczta = new JLabel("Poczta:");
		jtfPoczta = new JTextField();
		jtfPoczta.setEditable(false);
		
		c.gridx = 0; c.gridy = 0;
        p.add(jlbNazSkroc,c);
        c.gridx++;
        p.add(jtfNazSkroc,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNazPeln,c);
        c.gridx++;
        p.add(jtaNazPeln,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNIP,c);
        c.gridx++;
        p.add(jtfNIP,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTel1,c);
        c.gridx++;
        p.add(jtfTel1,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTel2,c);
        c.gridx++;
        p.add(jtfTel2,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTel3,c);
        c.gridx++;
        p.add(jtfTel3,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNazDzial,c);
        c.gridx++;
        p.add(jtfNazDzial,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbNrKonta,c);
        c.gridx++;
        p.add(jtfNrKonta,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbAdres,c);
        c.gridx++;
        p.add(jtfAdres,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbKodPocz,c);
        c.gridx++;
        p.add(jtfKodPocz,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbPoczta,c);
        c.gridx++;
        p.add(jtfPoczta, c);
        
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
		if(arg0.getValueIsAdjusting()) {
			String[] tabPom;
			String sel = list.getSelectedValue().toString();
			String sql = "SELECT NazwaSkrocona, NazwaPelna, NIP, Telefon1, Telefon2, Telefon3, NazwaDzialu, NrKonta, Adres, KodPocztowy, Poczta  FROM dostawca WHERE NazwaSkrocona='"+sel+"'";
			//int id = 0;
			//String sql = "SELECT NumerZamowienia, TerminRealizacji, DataRealizacji, DataWystawienia, SposobDostawy, KosztDostawy,WartoscTowarow, KosztZamowienia FROM zamowienie WHERE NumerZamowienia='"+sel+"'";
			
			try {
				ResultSet rs = polaczenie.sqlSelect(sql);
				tabPom = new String[11];
				
				rs.next();
				for(int i = 0;i<tabPom.length;i++)
				{
					tabPom[i]=rs.getString(i+1);
				}
				jtfNazSkroc.setText(tabPom[0]);
				jtaNazPeln.setText(tabPom[1]);
				jtfNIP.setText(tabPom[2]);
				jtfTel1.setText(tabPom[3]);
				jtfTel2.setText(tabPom[4]);
				jtfTel3.setText(tabPom[5]);
				jtfNazDzial.setText(tabPom[6]);
				jtfNrKonta.setText(tabPom[7]);
				jtfAdres.setText(tabPom[8]);
				jtfKodPocz.setText(tabPom[9]);
				jtfPoczta.setText(tabPom[10]);
	
			} catch (Exception e) {
				// TODO: handle exception
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
	@Override
	public void keyPressed(KeyEvent arg0) { }
	@Override
	public void keyReleased(KeyEvent arg0) { szukaj(search.getText()); }
	@Override
	public void keyTyped(KeyEvent arg0) { }
}