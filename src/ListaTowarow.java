import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class ListaTowarow extends JPanel implements KeyListener{
	JScrollPane scrollPane;
	JPanel p;
	JLabel jlbTytul;
	JTextField search;
	JTable table;
	
	Polaczenie polaczenie;
	
	public ListaTowarow() {
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 1, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        
        String[][] data = getAllTowaray();
        String[] columnNames = getColumnNames();
        for (int i = 0; i < columnNames.length; i++) {
			System.out.println(columnNames[i]);
		}
        table = new JTable(data,columnNames) {
        	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        		Component c = super.prepareRenderer(renderer, row, column);
        		int r = table.convertRowIndexToModel(row);
        		int c1 = table.convertColumnIndexToModel(column);
        		String min = (String) getModel().getValueAt(row, 1);
        		String rzecz = (String) getModel().getValueAt(row, 2);
        		if(Integer.parseInt(min)>Integer.parseInt(rzecz)) {
        			c.setBackground(Color.red);
        		}
        		else {
        			c.setBackground(Color.white);
        		}
				return c;
        	}
        };

        scrollPane = new JScrollPane(table);
        table.getFillsViewportHeight();
//	    scrollPane.setPreferredSize(new Dimension(400, 200));
//	    scrollPane.setMinimumSize(new Dimension(400, 200));
        
        jlbTytul = new JLabel("Lista towarów:");
		search = new JTextField("Wyszukaj towar po nazwie");

		gbc.gridx = 0; gbc.gridy = 0;
        p.add(jlbTytul,gbc);
        gbc.gridy++;
        p.add(search,gbc);
        gbc.gridy++;
        p.add(scrollPane,gbc);
        add(p);
        

		ustawNasluchZdarzen();
		focusListener();
	}
	private void ustawNasluchZdarzen(){
		search.addKeyListener(this);
	}
	private String[][] getAllTowaray() {
		String[][] tab = null;
		try {
			polaczenie = new Polaczenie();
			polaczenie.Connect();
			String sql = "SELECT * FROM towar";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tab = new String[rozmiar][4];
			while(rs.next()){
				tab[i][0] = rs.getString("NazwaTowaru");
				tab[i][1] = rs.getString("MinStanMagazynowy");
				tab[i][2] = rs.getString("StanMagazynowyRzeczywisty");
				tab[i][3] = rs.getString("MaxStanMagazynowy");
				System.out.println(tab[i][0]+"/"+tab[i][1]+"/"+tab[i][2]+"/"+tab[i][3]);
				i++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tab;
	}
	private String[] getColumnNames() {
		String[] tab = {"Nazwa towaru",
				"Stan minimalny","Stan rzeczywisty","Stan maksymalny"};
		return tab;
	}
	private void szukaj(String text){
		String[][] tab = null;
		try {
			polaczenie = new Polaczenie();
			polaczenie.Connect();
			String sql = "SELECT * FROM towar WHERE NazwaTowaru LIKE '%"+text+"%'";
			ResultSet rs = polaczenie.sqlSelect(sql);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			tab = new String[rozmiar][4];
			while(rs.next()){
				tab[i][0] = rs.getString("NazwaTowaru");
				tab[i][1] = rs.getString("MinStanMagazynowy");
				tab[i][2] = rs.getString("StanMagazynowyRzeczywisty");
				tab[i][3] = rs.getString("MaxStanMagazynowy");
				i++;
			}
			//list.clearSelection();
			DefaultTableModel tableModel = new DefaultTableModel(0,0);
			tableModel.setColumnIdentifiers(getColumnNames());
			table.setModel(tableModel);

			for (i = 0; i < tab.length; i++) {
				String[] data = new String[tab[0].length];
				for(int j = 0;j<tab[0].length;j++){
					data[j]= tab[i][j];
				}
				tableModel.addRow(data);
			}
			//t
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	private void focusListener() {
		search.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(search.toString() == "") {
					search.setText("Wyszukaj towar po nazwie");
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				search.setText("");
			}
		});
	}
}
