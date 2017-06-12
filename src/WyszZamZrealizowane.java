import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

<<<<<<< HEAD
public class WyszZamZrealizowane extends JPanel implements ListSelectionListener, KeyListener, ActionListener{
=======
public class WyszZamZrealizowane extends JPanel implements ActionListener, ListSelectionListener, KeyListener{
>>>>>>> branch 'master' of https://github.com/Nevnin/Magazyn.git
	private Polaczenie polaczenie;
	private JList<String> list;
	private JTable tabela;
	private String[] tab;
	private JSplitPane splitPane,splitPane1;
	private JScrollPane scrollPane,scrollPane1;
	private JLabel jlbOkres,jlbOd,jlbDo;
	private JTextField jtfOd,jtfDo;
	private JButton szukaj;
	public WyszZamZrealizowane()
	{
		
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		panel.setPreferredSize(new Dimension(200, 200));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 2, 10);
		
		
		scrollPane = new JScrollPane();
		
		jlbOkres = new JLabel("Podaj ramy czasowe");
		jlbOkres.setFont(new Font("Calibri", Font.BOLD, 20));
		szukaj = new JButton("Szukaj zamówieñ");
		
		jlbOd = new JLabel("Od:");
		jtfOd = new JTextField();
		jlbDo = new JLabel("Do:");
		jtfDo = new JTextField();
		scrollPane.setViewportView(list);
		jtfOd.setPreferredSize(new Dimension(70, 20));
		jtfDo.setPreferredSize(new Dimension(70, 20));
		panel.add(scrollPane);
		splitPane.setTopComponent(panel);
		//scrollPane1.setViewportView(list1);
		
		
		c.gridx = 2; c.gridy = 0;
		panel.add(jlbOkres,c);
		c.gridx = 0; c.gridy = 4;
        panel.add(jlbOd,c);
        c.gridx += 2;
        panel.add(jtfOd,c);
        c.gridx = 0; c.gridy = 6 ;
        panel.add(jlbDo,c);
        c.gridx += 2;
        panel.add(jtfDo,c);
        c.gridx = 2; c.gridy = 8;
        panel.add(szukaj,c);
		
		
		
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
		
		
	
     
        splitPane.setBottomComponent(scrollPane1);
 
     
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
            	  
              }
              if (e.getClickCount() == 1)
              {
              }
            }
          }
        );
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();
		String pocz = jtfOd.getText();
		String konc = jtfDo.getText();
		System.out.print("pocz"+pocz);
		
		String[][] zamowienia;
		if(z==szukaj)
		{
			Polaczenie polaczenie= new Polaczenie();
			try {
				polaczenie = new Polaczenie();
				
				String sql = "SELECT NumerZamowienia, TerminRealizacji, DataRealizacji, DataWystawienia, CalkowitaWartoscZamowienia from zamowienie where DataRealizacji BETWEEN '"+pocz+"' AND '"+konc+"'";
				ResultSet rs=polaczenie.sqlSelect(sql);
				int rozmiar = rs.getRow();
				rs.beforeFirst();
				zamowienia = new String [rozmiar][5];
				int j = 0;
				while(rs.next()){
					zamowienia[j][0] = rs.getString(1);
					zamowienia[j][1] = rs.getString(2);
					zamowienia[j][2] = rs.getString(3);
					zamowienia[j][3] = rs.getString(4);
					zamowienia[j][4] = rs.getString(5);
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
				for (int i = 0; i < zamowienia.length; i++) {
					String[] data = new String[zamowienia[0].length];
					for(int p = 0;p<5;p++){
						data[p]= zamowienia[i][p];
					}
					tableModel.addRow(data);
				}
				
			} catch (SQLException d) {
				// TODO Auto-generated catch block
				d.printStackTrace();
			}
		}
		
		
	}
	
     
	private void ustawNasluchZdarzen(){
		
		
		szukaj.addActionListener(this);	
	
	}
	@Override
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
					towary[j][4]=result.getString(5);
					
					j++;
				}
			
	
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
	public void keyReleased(KeyEvent arg0) { }
	@Override
	public void keyTyped(KeyEvent arg0) { }
}

