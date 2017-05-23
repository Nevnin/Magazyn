import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zamowieniev2 extends JPanel implements ActionListener, TableModelListener {
	String serverName = "localhost";
    String mydatabase = "magazyn";
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
    String username = "root";
    String password = "";
    DecimalFormat df;
    List<String> lista = new ArrayList<String>();
		JTabbedPane tabbedPane;
		JLabel jlbTytulZamowienie,jlbTerminRealizacji,jlbDataRealizacji,jlbKosztZamowienia,jlbDostawca,jlbDataWystawienia,jlbNumerZamowienia,jlbSposobDostawy,jlbKosztDostawy,jlbWartoscTowarow;
		JTextField jtfTerminRealizacji,jtfDostawca,jtfSposobDostawy,jtfDataRealizacji,jtfKosztZamowienia,jtfDataWystawienia,jtfNumerZamowienia,jtfKosztDostawy,jtfWartoscTowarow;
		JComboBox<String> jcbDostawca,jcbSposobDostawy,jcbTowar;
		JLabel jlTytulZamTow,jlLP,jlTowar,jlCena,jlIlosc,jlWartoscNetto,jlZamowienie;
		JTextField jtfLP,jtfTowar,jtfCena,jtfIlosc,jtfWartoscNetto,jtfZamowienie;
		Polaczenie poloczenie;
		String[] tabDostawca,tabSposobDostawy,tabTowar,tabNazwyKol;
		JPanel panelDaneZam,panelZamTow;
		String teraz,nazwaZam;
		float kosztZam=0.00f,cena=0.00f,wartoscNetto=0.00f,wartoscTowarow=0.00f,kosztDostawy=0.00f;
		int ilosc,lp;
		JSplitPane splitPane;
		DefaultTableModel tablemodel;
		JTable tablicaTowarow;
		JScrollPane scrollPane;
		JButton jbdodajTowar,jbZamow; 
		public Zamowieniev2()
		{
			df=new java.text.DecimalFormat(); 
			df.setMaximumFractionDigits(2); 
			df.setMinimumFractionDigits(2); 
			setLayout(new GridBagLayout());
			GridBagConstraints c= new GridBagConstraints();
			c.insets= new Insets(0,10,1,10);
			splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			tabbedPane = new JTabbedPane();
			panelDaneZam = new JPanel();
			panelDaneZam.setLayout(new GridBagLayout());
			GridBagConstraints cPanelDaneZam= new GridBagConstraints();
			cPanelDaneZam.insets= new Insets(0,10,1,10);
			cPanelDaneZam.fill = GridBagConstraints.HORIZONTAL;
			jlbTerminRealizacji = new JLabel("Termin realizacji");
			jtfTerminRealizacji = new JTextField("");
			jtfTerminRealizacji.setMaximumSize(new Dimension(100,20));
			jtfTerminRealizacji.setPreferredSize(new Dimension(400,20));
			jlbTerminRealizacji.setToolTipText(jlbTerminRealizacji.getText()+" :format daty yyyy-mm-dd(2016-10-06)");
			jtfTerminRealizacji.setToolTipText(jlbTerminRealizacji.getText()+" :format daty yyyy-mm-dd(2016-10-06)");
			
			SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd"); 
			 Date data = new Date(); 
			 teraz = dt.format(data);
			try {
				nazwaZam= tworzenieNazwyZam();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jlbTytulZamowienie = new JLabel("Zamówienie nr: "+nazwaZam);
			jlbTytulZamowienie.setFont(new Font("Calibri", Font.BOLD, 30));
			
			jlbDataRealizacji = new JLabel("Data realizacji");
			jtfDataRealizacji = new JTextField("");
			jtfDataRealizacji.setMaximumSize(new Dimension(100,20));
			jtfDataRealizacji.setPreferredSize(new Dimension(400,20));
			jlbDataRealizacji.setToolTipText(jlbDataRealizacji.getText()+" :format daty yyyy-mm-dd(2016-10-06)");
			jtfDataRealizacji.setToolTipText(jlbDataRealizacji.getText()+" :format daty yyyy-mm-dd(2016-10-06)");
			
			jlbKosztZamowienia = new JLabel("Koszt zamowienia");
			jtfKosztZamowienia = new JTextField("");
			jtfKosztZamowienia.setMaximumSize(new Dimension(100,20));
			jtfKosztZamowienia.setPreferredSize(new Dimension(400,20));
			jlbKosztZamowienia.setToolTipText(jlbKosztZamowienia.getText()+" :prosze wpisywac liczby");
			jtfKosztZamowienia.setToolTipText(jlbKosztZamowienia.getText()+" :prosze wpisywac liczby");
			jtfKosztZamowienia.setEditable(false);
			kosztZam=wartoscTowarow+kosztDostawy;
			jtfKosztZamowienia.setText(df.format(kosztZam));
			String sqlDostawca = "SELECT * from dostawca";
			String sqlSposobDostawy = "SELECT * from sposobdostawy";
			
			try {
				poloczenie = new Polaczenie();
				ResultSet rs = poloczenie.sqlSelect(sqlDostawca);
				rs.last();
				int rozmiar = rs.getRow();
				rs.beforeFirst();
				int i=0;
				tabDostawca = new String[rozmiar];
				while(rs.next())
				{
					tabDostawca[i] = rs.getString("NazwaSkrocona");
					i++;
				}
				ResultSet rsSD = poloczenie.sqlSelect(sqlSposobDostawy);
				rsSD.last();
				int rozmiarSD = rsSD.getRow();
				rsSD.beforeFirst();
				int j=0;
				tabSposobDostawy = new String[rozmiarSD];
				while(rsSD.next())
				{
					tabSposobDostawy[j] = rsSD.getString("SposobDostawy");
					j++;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			jlbDostawca = new JLabel("Dostawca");
			jcbDostawca = new JComboBox<String>(tabDostawca);
			jtfDostawca = new JTextField();
			jtfDostawca.setVisible(false);
			
			jlbDataWystawienia = new JLabel("Data wystawienia");
			jtfDataWystawienia = new JTextField("");
			jtfDataWystawienia.setMaximumSize(new Dimension(100,20));
			jtfDataWystawienia.setPreferredSize(new Dimension(400,20));
			jlbDataWystawienia.setToolTipText(jlbDataWystawienia.getText()+" :format daty yyyy-mm-dd(2016-10-06)");
			jtfDataWystawienia.setToolTipText(jlbDataWystawienia.getText()+" :format daty yyyy-mm-dd(2016-10-06)");
			jtfDataWystawienia.setEditable(false);
			
			jtfDataWystawienia.setText(teraz);
			
			jlbSposobDostawy = new JLabel("Sposob dostawy");
			jcbSposobDostawy = new JComboBox<String>(tabSposobDostawy);
			jtfSposobDostawy = new JTextField();
			jtfSposobDostawy.setVisible(false);
			
			jlbKosztDostawy = new JLabel("Koszt dostawy");
			jtfKosztDostawy = new JTextField("");
			jtfKosztDostawy.setMaximumSize(new Dimension(100,20));
			jtfKosztDostawy.setPreferredSize(new Dimension(400,20));
			jlbKosztDostawy.setToolTipText(jlbKosztDostawy.getText()+" :prosze wpisywac liczby");
			jtfKosztDostawy.setToolTipText(jlbKosztDostawy.getText()+" :prosze wpisywac liczby");
			
			jlbWartoscTowarow= new JLabel("Wartosc towarow");
			jtfWartoscTowarow = new JTextField("");
			jtfWartoscTowarow.setMaximumSize(new Dimension(100,20));
			jtfWartoscTowarow.setPreferredSize(new Dimension(400,20));
			jlbWartoscTowarow.setToolTipText(jlbWartoscTowarow.getText()+" :prosze wpisywac liczby");
			jtfWartoscTowarow.setToolTipText(jlbWartoscTowarow.getText()+" :prosze wpisywac liczby");
			jtfWartoscTowarow.setEditable(false);
			jtfWartoscTowarow.setText(df.format(wartoscTowarow));
			
			cPanelDaneZam.gridx = 1; cPanelDaneZam.gridy=0;
			panelDaneZam.add(jlbTytulZamowienie,cPanelDaneZam);
			cPanelDaneZam.gridx = 0; cPanelDaneZam.gridy++;
			panelDaneZam.add(jlbTerminRealizacji, cPanelDaneZam);
			cPanelDaneZam.gridx++; 
			panelDaneZam.add(jtfTerminRealizacji, cPanelDaneZam);
			cPanelDaneZam.gridx = 0; cPanelDaneZam.gridy++;
			panelDaneZam.add(jlbKosztZamowienia, cPanelDaneZam);
			cPanelDaneZam.gridx++;
			panelDaneZam.add(jtfKosztZamowienia, cPanelDaneZam);
			cPanelDaneZam.gridx = 0; cPanelDaneZam.gridy++;
			panelDaneZam.add(jlbDostawca, cPanelDaneZam);
			cPanelDaneZam.gridx++;
			panelDaneZam.add(jcbDostawca, cPanelDaneZam);
			panelDaneZam.add(jtfDostawca, cPanelDaneZam);
			cPanelDaneZam.gridx = 0; cPanelDaneZam.gridy++;
			panelDaneZam.add(jlbDataWystawienia, cPanelDaneZam);
			cPanelDaneZam.gridx++;
			panelDaneZam.add(jtfDataWystawienia, cPanelDaneZam);
			cPanelDaneZam.gridx = 0; cPanelDaneZam.gridy++;
			panelDaneZam.add(jlbSposobDostawy, cPanelDaneZam);
			cPanelDaneZam.gridx++;
			panelDaneZam.add(jcbSposobDostawy, cPanelDaneZam);
			panelDaneZam.add(jtfSposobDostawy, cPanelDaneZam);
			cPanelDaneZam.gridx = 0; cPanelDaneZam.gridy++;
			panelDaneZam.add(jlbKosztDostawy, cPanelDaneZam);
			cPanelDaneZam.gridx++;
			panelDaneZam.add(jtfKosztDostawy, cPanelDaneZam);
			cPanelDaneZam.gridx = 0; cPanelDaneZam.gridy++;
			panelDaneZam.add(jlbWartoscTowarow, cPanelDaneZam);
			cPanelDaneZam.gridx++;
			panelDaneZam.add(jtfWartoscTowarow, cPanelDaneZam);
			
			tabbedPane.addTab("Dane Zamowienia",null,panelDaneZam,"Formularz do zamowienia");
			
			
			
			panelZamTow = new JPanel();
			panelZamTow.setLayout(new GridBagLayout());
			GridBagConstraints cPanelZamTow= new GridBagConstraints();
			cPanelZamTow.insets= new Insets(0,10,1,10);
			cPanelZamTow.fill = GridBagConstraints.HORIZONTAL;
			
			jlTytulZamTow = new JLabel("Zamówienie nr: "+nazwaZam);
			jlTytulZamTow.setFont(new Font("Calibri", Font.BOLD, 30));
			
			jbdodajTowar =  new JButton("Dodaj towar");
			
			jlLP = new JLabel("Liczba porzadkowa");
			jtfLP = new JTextField("");
			jtfLP.setMaximumSize(new Dimension(100,20));
			jtfLP.setPreferredSize(new Dimension(400,20));
			jtfLP.setEditable(false);
			lp=1;
			jtfLP.setText(Integer.toString(lp));
			
			String sqlTowar="SELECT * from towar INNER JOIN dostawcatowar ON dostawcatowar.IdTowar=towar.IdTowar INNER JOIN dostawca ON dostawcatowar.IdDostawca=dostawca.IdDostawca WHERE NazwaSkrocona='"+jcbDostawca.getSelectedItem()+"' GROUP BY NazwaTowaru";
			
			try {
				ResultSet rsT = poloczenie.sqlSelect(sqlTowar);
				rsT.last();
				int rozmiarT = rsT.getRow();
				rsT.beforeFirst();
				int k=0;
				tabTowar = new String[rozmiarT];
				while(rsT.next())
				{
					tabTowar[k] = rsT.getString("NazwaTowaru");
					k++;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
			jlTowar = new JLabel("Towar");
			jcbTowar = new JComboBox<String>(tabTowar);
			jtfTowar = new JTextField();
			jtfTowar.setVisible(false);
			
String zapytanie ="SELECT * FROM `dostawcatowar` INNER JOIN towar on towar.IdTowar=dostawcatowar.IdTowar  INNER JOIN dostawca on dostawca.IdDostawca=dostawcatowar.IdDostawca WHERE towar.NazwaTowaru = '"+jcbTowar.getSelectedItem()+"' AND dostawca.NazwaSkrocona='"+jcbDostawca.getSelectedItem()+"'  ORDER BY DataDo DESC";
			
			try {
				ResultSet rs=poloczenie.sqlSelect(zapytanie);
				rs.first();
				cena = rs.getFloat("Cena");
				ilosc = rs.getInt("MaxStanMagazynowy")-rs.getInt("StanMagazynowyDysponowany");
				if(ilosc<0)
				{
					ilosc=0;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			jlCena = new JLabel("Cena");
			jtfCena = new JTextField("");
			jtfCena.setMaximumSize(new Dimension(100,20));
			jtfCena.setPreferredSize(new Dimension(400,20));
			jlCena.setToolTipText(jlCena.getText()+" :prosze podac liczbe");
			jtfCena.setToolTipText(jlCena.getText()+" :prosze podac liczbe");
			jtfCena.setEditable(false);
			jtfCena.setText(df.format(cena));
			
			jlIlosc = new JLabel("Ilosc");
			jtfIlosc = new JTextField("");
			jtfIlosc.setMaximumSize(new Dimension(100,20));
			jtfIlosc.setPreferredSize(new Dimension(400,20));
			jlIlosc.setToolTipText(jlIlosc.getText()+" :prosze podac liczbe");
			jtfIlosc.setToolTipText(jlIlosc.getText()+" :prosze podac liczbe");
			jtfIlosc.setText(Integer.toString(ilosc));
			
			jlWartoscNetto = new JLabel("Wartosc netto");
			jtfWartoscNetto = new JTextField("");
			jtfWartoscNetto.setMaximumSize(new Dimension(100,20));
			jtfWartoscNetto.setPreferredSize(new Dimension(400,20));
			jtfWartoscNetto.setEditable(false);
			wartoscNetto = ilosc * cena;
			jtfWartoscNetto.setText(df.format(wartoscNetto));
			
			jlZamowienie = new JLabel("Zamowienie");
			jtfZamowienie = new JTextField("");
			jtfZamowienie.setMaximumSize(new Dimension(100,20));
			jtfZamowienie.setPreferredSize(new Dimension(400,20));
			jlZamowienie.setToolTipText(jlZamowienie.getText()+" :prosze podac liczbe");
			jtfZamowienie.setToolTipText(jlZamowienie.getText()+" :prosze podac liczbe");
			jtfZamowienie.setEditable(false);
			jtfZamowienie.setText(nazwaZam);
			
			
			
			cPanelZamTow.gridx = 1; cPanelZamTow.gridy=0;
			panelZamTow.add(jlTytulZamTow,cPanelZamTow);
			cPanelZamTow.gridx = 0; cPanelZamTow.gridy++;
			panelZamTow.add(jlLP, cPanelZamTow);
			cPanelZamTow.gridx++; 
			panelZamTow.add(jtfLP, cPanelZamTow);
			cPanelZamTow.gridx = 0; cPanelZamTow.gridy++;
			panelZamTow.add(jlTowar, cPanelZamTow);
			cPanelZamTow.gridx++; 
			panelZamTow.add(jcbTowar, cPanelZamTow);
			panelZamTow.add(jtfTowar, cPanelZamTow);
			cPanelZamTow.gridx = 0; cPanelZamTow.gridy++;
			panelZamTow.add(jlCena, cPanelZamTow);
			cPanelZamTow.gridx++; 
			panelZamTow.add(jtfCena, cPanelZamTow);
			cPanelZamTow.gridx = 0; cPanelZamTow.gridy++;
			panelZamTow.add(jlIlosc, cPanelZamTow);
			cPanelZamTow.gridx++; 
			panelZamTow.add(jtfIlosc, cPanelZamTow);
			cPanelZamTow.gridx = 0; cPanelZamTow.gridy++;
			panelZamTow.add(jlWartoscNetto, cPanelZamTow);
			cPanelZamTow.gridx++; 
			panelZamTow.add(jtfWartoscNetto, cPanelZamTow);
			cPanelZamTow.gridx = 0; cPanelZamTow.gridy++;
			panelZamTow.add(jbdodajTowar, cPanelZamTow);
			
			tabbedPane.addTab("Dodanie Towaru",null,panelZamTow,"Formularz do zamowienieTowar");
			
			 
			 try{
					poloczenie = new Polaczenie();
					String sql = "SELECT * FROM zamowienietowar";
					ResultSet rs = poloczenie.sqlSelect(sql);
		        	int rozmiarKol = rs.getMetaData().getColumnCount();
					tabNazwyKol = new String[rozmiarKol-2];
					
					for(int i=0; i<rozmiarKol-2; i++){
						if(i!=1)
						{
							tabNazwyKol[i] = rs.getMetaData().getColumnName(i+2);
						}
						else
						{
							tabNazwyKol[1] = "Nazwa towaru";
						}
					}
		        }catch (SQLException e) {
					e.printStackTrace();
		        }
		        
			 
			 JPanel panelTablicaTowarow = new JPanel();
		        panelTablicaTowarow.setLayout(new GridLayout(0,1));
		         tablemodel = new DefaultTableModel(0,0);
		    	tablemodel.setColumnIdentifiers(tabNazwyKol);
		    	tablicaTowarow = new JTable();
		    	tablicaTowarow.setPreferredScrollableViewportSize(new Dimension(400,200));
		    	tablicaTowarow.getTableHeader().setReorderingAllowed(false);
		    	tablicaTowarow.setModel(tablemodel);
		    	ustawRozmiarTablicu();
		        scrollPane = new JScrollPane(tablicaTowarow);
		        panelTablicaTowarow.add(scrollPane);
		        splitPane.setBottomComponent(tabbedPane);
		        splitPane.setTopComponent(panelTablicaTowarow);
		        jbZamow= new JButton("Zamow");
		        jbZamow.setMaximumSize(new Dimension(100, 25));
		        jbZamow.setPreferredSize(new Dimension(100, 25));
		        if(sprawdzenieCzyJestDodanyTowar() || ilosc==0)
		        {
		        	jbdodajTowar.setEnabled(false);
		        }
		        c.gridx=0; c.gridy=0;
		        add(splitPane,c);
				c.gridy++;
				add(jbZamow,c);
				
				ustawNasluchZdarzen();
				tablemodel.addTableModelListener(this);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Object z = e.getSource();
			if(z==jtfKosztDostawy)
			{
				try {
					if(jtfKosztDostawy.getText().isEmpty())
					{
						kosztDostawy = 0.00f;
					}else{
					kosztDostawy =df.parse(jtfKosztDostawy.getText()).floatValue();
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				kosztZam = wartoscTowarow + kosztDostawy;
				jtfKosztZamowienia.setText(df.format(kosztZam));
				jtfKosztDostawy.setText(df.format(kosztDostawy));
			}
			if(z==jtfIlosc)
			{
				ilosc=Integer.parseInt(jtfIlosc.getText());
				if(ilosc<0)
				{
					ilosc =0 ;
				}
				if(ilosc == 0 || sprawdzenieCzyJestDodanyTowar() )
				{
					jbdodajTowar.setEnabled(false);
				}
				else{
					jbdodajTowar.setEnabled(true);
				}
			}
			if(z==jcbDostawca)
			{
				tablemodel = new DefaultTableModel(0,0);
		    	tablemodel.setColumnIdentifiers(tabNazwyKol);
				tablicaTowarow.setModel(tablemodel);
				ustawRozmiarTablicu();
				lista = new ArrayList<String>();
				lp=1;
				wartoscTowarow = 0.00f;
				kosztZam = wartoscTowarow + kosztDostawy;
				jtfWartoscTowarow.setText(df.format(wartoscTowarow));
				jtfLP.setText(Integer.toString(lp));
				jtfKosztZamowienia.setText(df.format(kosztZam));
				String sqlTowar="SELECT * from towar INNER JOIN dostawcatowar ON dostawcatowar.IdTowar=towar.IdTowar INNER JOIN dostawca ON dostawcatowar.IdDostawca=dostawca.IdDostawca WHERE NazwaSkrocona='"+jcbDostawca.getSelectedItem()+"' GROUP BY NazwaTowaru";
				try {
					ResultSet rsT = poloczenie.sqlSelect(sqlTowar);
					rsT.last();
					int rozmiarT = rsT.getRow();
					rsT.beforeFirst();
					int k=0;
					tabTowar = new String[rozmiarT];
					while(rsT.next())
					{
						tabTowar[k] = rsT.getString("NazwaTowaru");
						k++;
					}
					jcbTowar.setModel(new DefaultComboBoxModel<String>(tabTowar));
					String zapytanie ="SELECT * FROM `dostawcatowar` INNER JOIN towar on towar.IdTowar=dostawcatowar.IdTowar  INNER JOIN dostawca on dostawca.IdDostawca=dostawcatowar.IdDostawca WHERE towar.NazwaTowaru = '"+jcbTowar.getSelectedItem()+"' AND dostawca.NazwaSkrocona='"+jcbDostawca.getSelectedItem()+"'  ORDER BY DataDo DESC";
					
					try {
						ResultSet rs=poloczenie.sqlSelect(zapytanie);
						rs.first();
						cena = rs.getFloat("Cena");
						ilosc = rs.getInt("MaxStanMagazynowy")-rs.getInt("StanMagazynowyDysponowany");
						if(ilosc<0)
						{
							ilosc =0 ;
						}
						String cenaa = df.format(cena);
						jtfCena.setText(cenaa);
						jtfIlosc.setText(Integer.toString(ilosc));
						wartoscNetto = cena*ilosc;
						jtfWartoscNetto.setText(df.format(wartoscNetto));
						if(ilosc == 0)
						{
							jbdodajTowar.setEnabled(false);
						}
						else{
							jbdodajTowar.setEnabled(true);
						}
					} catch (SQLException d) {
						// TODO Auto-generated catch block
						d.printStackTrace();
					}
					
				} catch (SQLException d) {
					// TODO Auto-generated catch block
					d.printStackTrace();
				}
				
			}
			if(z==jbdodajTowar)
			{
				dodajNowyTowar();
				lp++;
				jtfLP.setText(Integer.toString(lp));
				wartoscTowarow += wartoscNetto;
				jtfWartoscTowarow.setText(df.format(wartoscTowarow));
				kosztZam = wartoscTowarow+kosztDostawy;
				jtfKosztZamowienia.setText(df.format(kosztZam));
				if(sprawdzenieCzyJestDodanyTowar() || ilosc==0)
				{
					jbdodajTowar.setEnabled(false);
				}
				else
				{
					jbdodajTowar.setEnabled(true);
				}
			}
			if(z==jcbTowar)
			{
				String zapytanie ="SELECT * FROM `dostawcatowar` INNER JOIN towar on towar.IdTowar=dostawcatowar.IdTowar  INNER JOIN dostawca on dostawca.IdDostawca=dostawcatowar.IdDostawca WHERE towar.NazwaTowaru = '"+jcbTowar.getSelectedItem()+"' AND dostawca.NazwaSkrocona='"+jcbDostawca.getSelectedItem()+"'  ORDER BY DataDo DESC";
				
				try {
					ResultSet rs=poloczenie.sqlSelect(zapytanie);
					rs.first();
					cena = rs.getFloat("Cena");
					ilosc = rs.getInt("MaxStanMagazynowy")-rs.getInt("StanMagazynowyDysponowany");
					String cenaa = df.format(cena);
					jtfCena.setText((cenaa));
					jtfIlosc.setText(Integer.toString(ilosc));
					wartoscNetto = cena*ilosc;
					jtfWartoscNetto.setText(df.format(wartoscNetto));
					if(sprawdzenieCzyJestDodanyTowar() || ilosc==0)
					{
						jbdodajTowar.setEnabled(false);
					}
					else
					{
						jbdodajTowar.setEnabled(true);
					}
					
				} catch (SQLException d) {
					// TODO Auto-generated catch block
					d.printStackTrace();
				}
			}
			
			
		}
		private void ustawRozmiarTablicu()
		{
			tablicaTowarow.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    	int[] tabKolSzer = {50,155,100,100,150};
	    	for(int i=0; i<tabNazwyKol.length; i++){
	    		tablicaTowarow.getColumnModel().getColumn(i).setPreferredWidth(tabKolSzer[i]);
				DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
	    		if(i==2 || i==4){
	    			tableRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
	    			tablicaTowarow.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
	    		}
	    		else if(i==0 || i ==1 || i==3){
	    			tableRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    			tablicaTowarow.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
	    		}
	    	}
		}
		private void ustawNasluchZdarzen() 
		{
			jbdodajTowar.addActionListener(this);
			//jbZamow.addActionListener(this);
			jcbDostawca.addActionListener(this);
			jcbSposobDostawy.addActionListener(this);
			jtfKosztDostawy.addActionListener(this);
			jcbTowar.addActionListener(this);
			jtfIlosc.addActionListener(this);
		}
		public String tworzenieNazwyZam() throws SQLException
		{
			String nazwa="";
			String[] tablica = teraz.split("-");
			int ilosc = sprawdzenieIlosciZam()+1;
			nazwa+=tablica[0]+"/"+tablica[1]+"/"+tablica[2]+"/"+ilosc;
			return nazwa;
		}
		public int sprawdzenieIlosciZam() throws SQLException 
		{
			int k=0;
			Connection connection = DriverManager.getConnection(url, username, password);
			String count = "SELECT count(*) from zamowienie WHERE DataWystawienia='"+teraz+"'";
			PreparedStatement ps= connection.prepareStatement(count);
			ResultSet rsc= ps.executeQuery();
			while(rsc.next())
			{
				k=rsc.getInt(1);
			}
			return k;
			
			
		}
		private boolean sprawdzenieCzyJestDodanyTowar()
		{
			for(int i=0;i<lista.size();i++)
			{
				if(lista.get(i).equals(jcbTowar.getSelectedItem()))
				{
					return true;
				}
			}
			return false;
		} 
		private void dodajNowyTowar() {
			lista.add(jcbTowar.getSelectedItem().toString());
	    	String[] tabPom = {jtfLP.getText(),jcbTowar.getSelectedItem().toString(),jtfCena.getText(),jtfIlosc.getText(),jtfWartoscNetto.getText()};
	    	tablemodel.addRow(tabPom);
	    	tablicaTowarow.setModel(tablemodel);
		}
		public String sprawdzenieDaty(String data) throws ParseException
		{
			
			String error="";
			String[] podzial= null;
			podzial = data.split("-");
			int miesiac = Integer.parseInt(podzial[1]);
			int rok = Integer.parseInt(podzial[0]);
			int dzien = Integer.parseInt(podzial[2]);
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
		String walidacjaDat(String TerminRealizacji) throws ParseException
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
		String walidacjaTabeli() throws ParseException
		{
			String error="";
			
			if(tablicaTowarow.getModel().getRowCount()==0){
				error="Nie ma dodanego towaru do zamówienia \n";
			}
			
			return error;
		}
		public void Zamowienie() throws SQLException, ParseException
		{
			Connection connection = DriverManager.getConnection(url, username, password);
			String query = "INSERT INTO zamowienie "
					+ "(TerminRealizacji,DataRealizacji,KosztZamowienia,IdDostawcy,DataWystawienia,NumerZamowienia,IdSposobDostawy,KosztDostawy,WartoscTowarow)"
				    + " values (?, ?, ?, ?, ?, ?,?,?,?)";
			PreparedStatement preparedStmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString (1,jtfTerminRealizacji.getText());
			preparedStmt.setNull(2, java.sql.Types.DATE);
			Number kosztZam = df.parse(jtfKosztZamowienia.getText());
			float kosztZamowienia = kosztZam.floatValue();
			preparedStmt.setDouble(3,kosztZamowienia);
			String zapDostawca = "Select * from dostawca WHERE NazwaSkrocona='"+jcbDostawca.getSelectedItem()+"'";
			ResultSet rsDostawca = poloczenie.sqlSelect(zapDostawca);
			rsDostawca.next();
			int idDostawca = rsDostawca.getInt("IdDostawca");
			preparedStmt.setInt (4,idDostawca);
			preparedStmt.setString (5, teraz);
			preparedStmt.setString (6,nazwaZam);
			String zapSposobDostawy = "SELECT * from sposobdostawy WHERE SposobDostawy='"+jcbSposobDostawy.getSelectedItem()+"'";
			ResultSet rsSposobDostawy=poloczenie.sqlSelect(zapSposobDostawy);
			rsSposobDostawy.next();
			int IdSposobDostawy = rsSposobDostawy.getInt("IdSposobDostawy");
			preparedStmt.setInt (7,IdSposobDostawy);
			Number kosztDos=df.parse(jtfKosztDostawy.getText());
			float kosztDostawy=kosztDos.floatValue();
			preparedStmt.setFloat (8,kosztDostawy);
			Number watoscZam = df.parse(jtfWartoscTowarow.getText());
			float WartoscZamowienia = watoscZam.floatValue();
			preparedStmt.setFloat (9,WartoscZamowienia);
			preparedStmt.execute();
			connection.close();
		}
		public void dodanieTowarowDoZamowienia() throws SQLException
		{
			Connection connection = DriverManager.getConnection(url, username, password);
			String zapZam= "Select * from zamowienie WHERE NumerZamowienia='"+nazwaZam+"'";
			ResultSet rsZam=poloczenie.sqlSelect(zapZam);
			rsZam.next();
			int idZam=rsZam.getInt("IdZamowienie");
			for(int i=0;i<tablicaTowarow.getRowCount();i++){
			String query = "INSERT INTO zamowienietowar "
					+ "(LP,IdTowar,Cena,Ilosc,WartoscNetto,IdZamowienie)"
				    + " values (?, ?, ?, ?, ?, ?)";
			int j=0;
			Object LP = tablicaTowarow.getValueAt(i,j);
			int lp = Integer.parseInt((String) LP);
			PreparedStatement preparedStmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setInt (1,lp);
			j++;
			String zapTowar="Select * from towar WHERE NazwaTowaru='"+tablicaTowarow.getValueAt(i,j)+"'";
			ResultSet rsTow = poloczenie.sqlSelect(zapTowar);
			rsTow.next();
			int idTow = rsTow.getInt("IdTowar");
			preparedStmt.setInt (2, idTow);
			j++;
			Object ce = tablicaTowarow.getValueAt(i,j);
			Number cenna;
			try {
				cenna = df.parse((String)ce);
				float cena = cenna.floatValue();
				preparedStmt.setFloat (3, cena);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			j++;
			Object il = tablicaTowarow.getValueAt(i,j);
			int ilosc= Integer.parseInt((String) il);
			preparedStmt.setInt (4, ilosc);
			j++;
			Object wa = tablicaTowarow.getValueAt(i,j);
			Number wart;
			try {
				wart = df.parse((String)wa);
				float wartosc= wart.floatValue();
				preparedStmt.setFloat (5,wartosc);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			preparedStmt.setInt (6,idZam);
			preparedStmt.execute();
			
			String query2= "UPDATE towar set StanMagazynowyDysponowany =StanMagazynowyDysponowany + ? WHERE IdTowar=?";
			PreparedStatement preparedStmt2 = connection.prepareStatement(query2);
			preparedStmt2.setInt(1,ilosc);
			preparedStmt2.setInt(2,idTow);
			preparedStmt2.execute();
			}
			connection.close();
		}
		@Override
		public void tableChanged(TableModelEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e);
			
		}
		
		
		
		
		
		
	 }



