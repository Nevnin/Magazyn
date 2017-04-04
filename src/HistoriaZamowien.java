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

public class HistoriaZamowien extends JPanel implements ListSelectionListener{
Polaczenie polaczenie;
public JList list1,list2;
String[] tablica,tablica1,tablica3;
JSplitPane splitPane;
public HistoriaZamowien()
{
	try
	{
		polaczenie = new Polaczenie();
	} 
	catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try
	{
		String query1 ="SELECT * FROM `zamowienie`";
		ResultSet rs = polaczenie.sqlSelect(query1);
		rs.last();
		int rozmiar = rs.getRow();
		rs.beforeFirst();
		int i = 0;
		tablica = new String[rozmiar];
		while (rs.next())
		{
			tablica[i] = rs.getString(7);
			i++;
		}
	}
	catch (SQLException e)
	{
		e.printStackTrace();
	}
	

	splitPane = new JSplitPane();
	add(splitPane);
	
	list1 = new JList();
	list2 = new JList();
	list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	splitPane.setRightComponent(list2);
	list1.setAlignmentX(CENTER_ALIGNMENT);
	list1 = new JList(tablica);
	list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list1.addListSelectionListener(this);
	splitPane.setLeftComponent(list1);
}

@Override
public void valueChanged(ListSelectionEvent e) {
	
	if(e.getValueIsAdjusting()==true)
	{
		String query1 ="SELECT * FROM `zamowienie`";		
			try
			{
				
				ResultSet rs = polaczenie.sqlSelect(query1);
				rs.last();
				int rozmiar = rs.getRow();
				rs.beforeFirst();
				int i = 0;
				tablica = new String[rozmiar];
				while (rs.next())
				{
					tablica[i] = rs.getString(7);
					i++;
				}
			
				list2 = new JList(tablica1);
				list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list2.addListSelectionListener(this);
				splitPane.setRightComponent(list2);
				 list2.setAlignmentX(CENTER_ALIGNMENT);
			validate();
			
			repaint();
		} catch (SQLException e1) 
			{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		}
	
}

}

