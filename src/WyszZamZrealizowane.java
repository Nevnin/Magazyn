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

public class WyszZamZrealizowane extends JPanel implements ActionListener, KeyListener{
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
		
		
		String[][] zamowienia;
		if(z==szukaj)
		{
			
			try {
				polaczenie = new Polaczenie();
				
				String sql = "SELECT NumerZamowienia, TerminRealizacji, DataRealizacji, DataWystawienia, CalkowitaWartoscZamowienia from zamowienie where DataRealizacji BETWEEN '"+pocz+"' AND '"+konc+"'";
				
				ResultSet rs=polaczenie.sqlSelect(sql);
				rs.last();
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
	public void keyPressed(KeyEvent arg0) { }
	@Override
	public void keyReleased(KeyEvent arg0) { }
	@Override
	public void keyTyped(KeyEvent arg0) { }
}

