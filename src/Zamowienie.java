import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import org.eclipse.wb.swing.FocusTraversalOnArray;
//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;

import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.LineBorder;

public class Zamowienie extends JPanel implements ActionListener {
	 String serverName = "localhost";
	    String mydatabase = "magazyn";
	    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
	    String username = "root";
	    String password = "";
	Polaczenie poloczenie;
	public JList dostawcy;
	private JList towar;
	String[] tablica;
	String[] tablica1;
	String[] tabtowar;
	String[] tabdostawca;
	String[] tabzamowienie;
	JButton zamow ;
	JSplitPane splitPane;
	PanelDoZam p;
	PanelDoZam p2;
	boolean btowar=false;
	boolean bdostawcy = false;
	String nazwaZamowienia;
	String teraz;
	public Zamowienie(){
		setPreferredSize(new Dimension(400,300));
		GridLayout g= new GridLayout(2,1);
		setLayout(g);
		SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd"); 
		 Date data = new Date(); 
		 teraz = dt.format(data);
		 try {
			nazwaZamowienia = tworzenieNazwyZam();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
		}splitPane = new JSplitPane();
		 splitPane.setBounds(85, 5, 207, 202);
		// splitPane.setPreferredSize(new Dimension(400, 400));
		// splitPane.setMinimumSize(new Dimension(400, 200));
		add(splitPane,g);
		
		 dostawcy = new JList(); 
		 dostawcy.setPreferredSize(new Dimension(200,200));
		 dostawcy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 
		 splitPane.setRightComponent(dostawcy);
		towar = new JList(tablica);
		 towar.setSize(new Dimension(200,200));
		towar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		towar.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()==true)
				{
					btowar=true;
					String query = "SELECT dostawca.NazwaSkrocona FROM `towar` INNER JOIN dostawcatowar ON dostawcatowar.IdTowar=towar.IdTowar INNER JOIN dostawca ON dostawca.IdDostawca= dostawcatowar.IdDostawca WHERE towar.NazwaTowaru ='"+towar.getSelectedValue()+"'GROUP BY dostawca.NazwaSkrocona";
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
						String zapytanietowar = "Select * from towar WHERE NazwaTowaru='"+towar.getSelectedValue()+"'";
						ResultSet ztowar =poloczenie.sqlSelect(zapytanietowar);
						tabtowar = new String[10];
						while(ztowar.next()){
						for(int j=0; j<tabtowar.length; j++){
							tabtowar[j] = ztowar.getString(j+1);
						}
						}
						dostawcy = new JList(tablica1);
						dostawcy.setPreferredSize(new Dimension(200,200));
						dostawcy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						dostawcy.addListSelectionListener(new ListSelectionListener(){
						
							@Override
							public void valueChanged(ListSelectionEvent e) {
								if(e.getValueIsAdjusting()==true)
								{
									bdostawcy=true;
									String zapytaniedostawca = "Select * from dostawca WHERE NazwaSkrocona='"+dostawcy.getSelectedValue()+"'";
									try {
										ResultSet zdostawca =poloczenie.sqlSelect(zapytaniedostawca);
										tabdostawca = new String[12];
										while(zdostawca.next()){
										for(int j=0; j<tabdostawca.length; j++){
											tabdostawca[j] = zdostawca.getString(j+1);
										}
										}
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									try {
										if(sprawdzenieCzyJestZam(nazwaZamowienia,Integer.parseInt(tabdostawca[0]))==true){
										remove(p);
										p2= new PanelDoZam(1);
										validate();
										repaint();
										}
										else
										{
											remove(p2);
											p= new PanelDoZam(1);
											validate();
											repaint();	
										}
									} catch (NumberFormatException | SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									
								}
								
							}});
						
						splitPane.setRightComponent(dostawcy);
						validate();
						repaint();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				}});
		splitPane.setLeftComponent(towar);
		p = new PanelDoZam(0);
		add(p,g);
		ustawNasluchZdarzen();
		
	}
	private void ustawNasluchZdarzen() 
	{
		p.zamow.addActionListener(this);
		p2.zamow.addActionListener(this);
	}
	private String walidacjaDat(String TerminRealizacji) throws ParseException
	{
		String error="";
		
		if(TerminRealizacji.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")){
			String walidacjaDaty = sprawdzenieDaty(TerminRealizacji);
			if(walidacjaDaty.length()>0)
				{
					error+=walidacjaDaty;	
				}
		}else
			{
				error+="Niepoprawny format daty przy Termin Realizacji \n";}
		
		return error;
	}
	private String walidacjaList() throws ParseException
	{
		String error="";
		if(btowar!=true)
		{
			error+="Towar nie zosta³ wybrany z listy\n";
		}
		if(bdostawcy!=true)
		{
			error+="Dostawca nie zosta³ wybrany z listy\n";
		}
			return error;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object z= e.getSource();
		if(z=="Zamow")
		{
			String TerminRealizacji = p.tfTerminRealizacji.getText().toString();
			
			
			 
			try {
				 String walidacja = walidacjaDat(TerminRealizacji);
				 walidacja+=walidacjaList();
				 if(walidacja.length()>0)
				 {
			    	JOptionPane.showMessageDialog(null, walidacja,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
				 }else
				 {
					 
				 }
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(z=="Dodaj do zamowienia")
		{
			String walidacja;
			try {
				walidacja = walidacjaList();
				if(walidacja.length()>0)
				 {
			    	JOptionPane.showMessageDialog(null, walidacja,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
				 }else
				 {
					 
				 }
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	} 
	public String sprawdzenieDaty(String data) throws ParseException
	{
		
		String error="";
		String[] podzial= null;
		podzial = data.split("-");
		int miesiac = Integer.parseInt(podzial[1]);
		int rok = Integer.parseInt(podzial[0]);
		int dzien = Integer.parseInt(podzial[2]);
		System.out.println(rok+" "+miesiac+" "+dzien);
		if(miesiac>12){
			error+="Zosta³ podany niepoprawny miesi¹c , nie powinien byæ wiêkszy ni¿ 12.\n"; }
		else{
		 if(miesiac==1 ||miesiac==3 || miesiac==5 || miesiac==7 || miesiac==8 || miesiac==10 || miesiac==12)
		 {
			 if(dzien>31)
			 {
				 error+="Zosta³ podany niepoprawny dzien, nie powinien byæ wiêkszy ni¿ 31.\n";
			 }
		 }
		 else if(miesiac==4 || miesiac==6 || miesiac==9 || miesiac==11)
		 {
			 if(dzien>30)
			 {
				 error+="Zosta³ podany niepoprawny dzien, nie powinien byæ wiêkszy ni¿ 30.\n";
			 }
		 }
		 else
		 {
			 if(((rok%4== 0) && (rok%100!= 0)) || (rok%400 == 0))
			 {
				 if(dzien>29)
				 {
					 error+="Zosta³ podany niepoprawny dzien, nie powinien byæ wiêkszy ni¿ 29.\n";
				 }
			 }
			 else
			 {
				 error+="Zosta³ podany niepoprawny dzien, nie powinien byæ wiêkszy ni¿ 28.\n";
			 }
		 }
		 }
		
		return error;
	} 
	public int sprawdzenieIlosciZam() 
	{
		int k=0;
		String count = "SELECT count(*) from zamowienie WHERE DataWystawienia='"+teraz+"'";
		System.out.println(count);
		try {
			 ResultSet rs = poloczenie.sqlSelect(count);
			while(rs.next())
			{
				 k =rs.getInt(1);
			}
			return k;
		} catch (SQLException e) {
			return 0;
		}
		
	}
	public boolean sprawdzenieCzyJestZam(String nazwaZam,int IdDostawcy) throws SQLException
	{
		String zapytanie="SELECT * from zamowienie WHERE NumerZamowienia='"+nazwaZam+"' AND IdDostawcy='"+IdDostawcy+"'";
		ResultSet rs = poloczenie.sqlSelect(zapytanie);
		if(rs.getRow()==0)
		{
			return false;
		}
		return true;
	}

	public void Zamowienie(Date TerminRealizacji,float KosztZamowienia , int IdDostawcy , String NumerZamowienia, String SposobDostawy , float KosztDostawy) throws SQLException
	{
		Connection connection = DriverManager.getConnection(url, username, password);
		String query = "INSERT INTO zamowienie "
				+ "(TerminReallizacji,DataRealizacji,KosztZamowienia,IdDostawcy,DataWystawienia,NumerZamowienia,SposobDostawy,KosztDostawy,WartoscTowarow)"
			    + " values (?, ?, ?, ?, ?, ?,?,?,?)";
		PreparedStatement preparedStmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		preparedStmt.setDate (1,(java.sql.Date) TerminRealizacji);
		preparedStmt.setString (2,"NULL");
		preparedStmt.setFloat (3,KosztZamowienia);
		preparedStmt.setInt (4,IdDostawcy);
		preparedStmt.setString (5, teraz);
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
	public String tworzenieNazwyZam() throws SQLException
	{
		String nazwa="";
		String[] tablica = teraz.split("-");
		nazwa+=tablica[0]+"/"+tablica[1]+"/"+tablica[2]+"/"+sprawdzenieIlosciZam()+1;
		return nazwa;
	}
}



