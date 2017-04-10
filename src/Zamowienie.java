import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import javax.swing.border.LineBorder;

public class Zamowienie extends JPanel implements ActionListener {
	 String serverName = "localhost";
	    String mydatabase = "magazyn";
	    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
	    String username = "root";
	    String password = "";
	Polaczenie poloczenie;
	public JList list_1,list_2;
	private JList list_1_2;
	private JList list_1_1;
	String[] tablica;
	String[] tablica1;
	JButton zamow ;
	JSplitPane splitPane;
	PanelDoZam p;
	public Zamowienie() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(400,300));
		GridLayout g= new GridLayout(2,1);
		setLayout(g);
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
//		SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd"); 
//		 Date data = new Date(); 
//		System.out.println(dt.format(data));
		splitPane = new JSplitPane();
		 splitPane.setBounds(85, 5, 207, 202);
		// splitPane.setPreferredSize(new Dimension(400, 400));
		// splitPane.setMinimumSize(new Dimension(400, 200));
		add(splitPane,g);
		
		 list_2 = new JList(); 
		 list_2.setPreferredSize(new Dimension(200,200));
		 list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 
		 splitPane.setRightComponent(list_2);
		list_1_2 = new JList(tablica);
		 list_1_2.setSize(new Dimension(200,200));
		 
	//	list_1.setPreferredSize(new Dimension(200, 10));
		list_1_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1_2.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()==true)
				{
					String query = "SELECT dostawca.NazwaSkrocona FROM `towar` INNER JOIN dostawcatowar ON dostawcatowar.IdTowar=towar.IdTowar INNER JOIN dostawca ON dostawca.IdDostawca= dostawcatowar.IdDostawca WHERE towar.NazwaTowaru ='"+list_1_2.getSelectedValue()+"'GROUP BY dostawca.NazwaSkrocona";
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
						list_2.setPreferredSize(new Dimension(200,200));
						list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						list_2.addListSelectionListener(new ListSelectionListener(){

							@Override
							public void valueChanged(ListSelectionEvent e) {
								if(e.getValueIsAdjusting()==true)
								{
									p = new PanelDoZam(0);
									add(p,g);
									ustawNasluchZdarzen();
									validate();
									repaint();
									
								}
								
							}});
						
						splitPane.setRightComponent(list_2);
						 list_2.setAlignmentX(CENTER_ALIGNMENT);
						validate();
						repaint();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				}});
		splitPane.setLeftComponent(list_1_2);
	}
	private void ustawNasluchZdarzen() 
	{
		p.zamow.addActionListener(this);
	}
	public void Zamowienie(Date TerminRealizacji, Date DataRealizacji,float KosztZamowienia , int IdDostawcy , String NumerZamowienia, String SposobDostawy , float KosztDostawy) throws SQLException
	{
		Connection connection = DriverManager.getConnection(url, username, password);
		String query = "INSERT INTO zamowienie "
				+ "(TerminReallizacji,DataRealizacji,KosztZamowienia,IdDostawcy,DataWystawienia,NumerZamowienia,SposobDostawy,KosztDostawy,WartoscTowarow)"
			    + " values (?, ?, ?, ?, ?, ?,?,?,?)";
		PreparedStatement preparedStmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		preparedStmt.setDate (1,(java.sql.Date) TerminRealizacji);
		preparedStmt.setDate (2,(java.sql.Date) DataRealizacji);
		preparedStmt.setFloat (3,KosztZamowienia);
		preparedStmt.setInt (4,IdDostawcy);
		Date dzis = new Date();
		preparedStmt.setDate (5,(java.sql.Date) dzis);
		preparedStmt.setString (6,NumerZamowienia);
		preparedStmt.setString (7,SposobDostawy);
		preparedStmt.setFloat (8,KosztDostawy);
		float WartoscDostawy = KosztDostawy+KosztZamowienia;
		preparedStmt.setFloat (9,WartoscDostawy);
		preparedStmt.execute();
		connection.close();
	}
	public void dodanieTowaruDoZamowienia(int idZam, int idTow,int ilosc, double cena) throws SQLException
	{
		Connection connection = DriverManager.getConnection(url, username, password);
		String query = "INSERT INTO zamowienietowar "
				+ "(LP,IdTowar,Cena,Ilosc,WartoscNetto,IdZamowienie)"
			    + " values (?, ?, ?, ?, ?, ?)";
		int LP = LP(idZam)+1;
		PreparedStatement preparedStmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		preparedStmt.setInt (1,LP);
		preparedStmt.setInt (2, idTow);
		preparedStmt.setDouble (3, cena);
		preparedStmt.setInt (4, ilosc);
		double wartosc= cena * ilosc;
		preparedStmt.setDouble (5,wartosc);
		preparedStmt.setInt (6,idZam);
		preparedStmt.execute();
		String query2= "UPDATE zamowienie set KosztZamowienia =KosztZamowienia + ? WHERE IdZamowienie=?";
		PreparedStatement preparedStmt2 = connection.prepareStatement(query2);
		
		preparedStmt2.setDouble(1,wartosc);
		preparedStmt2.setInt(2,idZam);
		preparedStmt.execute();
		connection.close();
	}
	public int LP(int idZam)
	{
		String query = "Select LP from zamowienietowar INNER JOIN zamowienie WHERE IdZamowienie="+idZam; 
		ResultSet rs;
		int LP=0;
		try {
			rs = poloczenie.sqlSelect(query);
			
			while(rs.next())
			{
				if(LP<rs.getInt(1))
				{
					LP=rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return LP;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object z= e.getSource();
		if(z==p.zamow)
		{
			
		}
		
	} 
}



