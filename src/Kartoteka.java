import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
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
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Kartoteka extends JPanel {
	private Polaczenie polaczenie;
	private JList<String> list;
	private JTable tabela;
	private JSplitPane splitPane;
	private JScrollPane scrollPane,scrollPane1;
	private JLabel jlbRok,jlbMiesiac,jlbBilans;
	private JTextField jtfRok,jtfMiesiac;
	private JButton jbOK;
	String[] columnNames = 
		{"Nazwa Operacji",
        "Typ",
        "Ilosc PZ",
        "Cena PZ",
        "Ilosc F","Cena F","Zapas ilosc","Zapas cena"};
	public Kartoteka()
	{
		
		
		//splitPane = new JSplitPane();
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 0, 10);
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		scrollPane = new JScrollPane();
		jlbBilans = new JLabel("Bilans");
		jlbBilans.setFont(new Font("Calibri", Font.BOLD, 20));
		jlbRok = new JLabel("Rok:");
		
		jlbMiesiac = new JLabel("Miesiac:");
		jtfRok = new JTextField();
		
		jtfMiesiac = new JTextField();
		scrollPane.setViewportView(list);
		jtfRok.setPreferredSize(new Dimension(70, 20));
		jtfMiesiac.setPreferredSize(new Dimension(70, 20));
		
		jbOK= new JButton("OK");
		panel.add(scrollPane);
		splitPane.setTopComponent(panel);
		//scrollPane1.setViewportView(list1);
		
		
		c.gridx = 0; c.gridy = 1;
		panel.add(jlbRok,c);
		c.gridx = 1; 
        panel.add(jtfRok,c);
        c.gridx += 1;
        panel.add(jlbMiesiac,c);
        c.gridx += 1; 
        panel.add(jtfMiesiac,c);
        c.gridx += 1;
        panel.add(jbOK,c);
       
                
		
		
		
		
		String[][] data = new String[0][0];
		
		tabela = new JTable(data, columnNames);
		tabela.setDefaultEditor(Object.class, null);
		tabela.getTableHeader().setReorderingAllowed(false);
		
		scrollPane1 = new JScrollPane(tabela);
        splitPane.setBottomComponent(scrollPane1);
        add(splitPane);
        ustawNasluchZdarzen();
	}
     
	private void ustawNasluchZdarzen(){
		
	
	}
	private void ustawRozmiarTablicu()
	{
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	int[] tabKolSzer = {50,155,100,100,150};
    	for(int i=0; i<columnNames.length; i++){
    		tabela.getColumnModel().getColumn(i).setPreferredWidth(tabKolSzer[i]);
			DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
    		if(i==2 || i==4){
    			tableRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
    			tabela.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
    		}
    		else if(i==0 || i ==1 || i==3){
    			tableRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    			tabela.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
    		}
    	}
	}
	
	
}

