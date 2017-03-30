import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDesktopPane;
import javax.swing.JList;

public class Zamowienie extends JPanel implements ListSelectionListener {
	Polaczenie poloczenie;
	public JList list_1,list_2;
	private JList list_1_1;
	String[] tablica;
	String[] tablica1;
	JSplitPane splitPane;
	
	public Zamowienie() {
		//setPreferredSize(new Dimension(400,400));
		try {
			poloczenie = new Polaczenie();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String query1 = "SELECT * FROM `towar` WHERE  StanMagazynowyDysponowany < MinStanMagazynowy";
			ResultSet rs = poloczenie.sqlSelect(query1);
			rs.last();
			int rozmiar = rs.getRow();
			rs.beforeFirst();
			int i =0;
			tablica= new String[rozmiar];
			while(rs.next())
			{
				tablica[i]=rs.getString(2);
				i++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 splitPane = new JSplitPane();
		// splitPane.setPreferredSize(new Dimension(400, 400));
		// splitPane.setMinimumSize(new Dimension(400, 200));
		add(splitPane);
		
		 list_1 = new JList();
		 list_2 = new JList(); 
		 list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 splitPane.setRightComponent(list_2);
		 list_1.setAlignmentX(CENTER_ALIGNMENT);
		list_1 = new JList(tablica);
	//	list_1.setPreferredSize(new Dimension(200, 10));
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.addListSelectionListener(this);
		splitPane.setLeftComponent(list_1);
		
		
		
		
		
		
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		if(e.getValueIsAdjusting()==true)
		{
			String query = "SELECT dostawca.NazwaSkrocona FROM `towar` INNER JOIN dostawcatowar ON dostawcatowar.IdTowar=towar.IdTowar INNER JOIN dostawca ON dostawca.IdDostawca= dostawcatowar.IdDostawca WHERE towar.NazwaTowaru ='"+list_1.getSelectedValue()+"'GROUP BY dostawca.NazwaSkrocona";
			
			try {
				ResultSet rs = poloczenie.sqlSelect(query);
				rs.last();
				int rozmiar = rs.getRow();
				rs.beforeFirst();
				tablica1 = new String[rozmiar];
				int i =0;
				while(rs.next())
				{
					
					tablica1[i]=rs.getString(1);
					i++;
					
				}
				list_2 = new JList(tablica1);
				list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list_2.addListSelectionListener(this);
				splitPane.setRightComponent(list_2);
				 list_2.setAlignmentX(CENTER_ALIGNMENT);
				validate();
				
				repaint();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	

}
