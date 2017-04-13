import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class Zamowieniev2 extends JPanel implements ActionListener {
		JTabbedPane tabbedPane;
		JLabel jlbTytulZamowienie,jlbTerminRealizacji,jlbDataRealizacji,jlbKosztZamowienia,jlbDostawca,jlbDataWystawienia,jlbNumerZamowienia,jlbSposobDostawy,jlbKosztDostawy,jlbWartoscTowarow;
		JTextField jtfTerminRealizacji,jtfDostawca,jtfSposobDostawy,jtfDataRealizacji,jtfKosztZamowienia,jtfDataWystawienia,jtfNumerZamowienia,jtfKosztDostawy,jtfWartoscTowarow;
		JComboBox<String> jcbDostawca,jcbSposobDostawy,jcbTowar;
		JLabel jlTytulZamTow,jlLP,jlTowar,jlCena,jlIlosc,jlWartoscNetto,jlZamowienie;
		JTextField jtfLP,jtfTowar,jtfCena,jtfIlosc,jtfWartoscNetto,jtfZamowienie;
		Polaczenie poloczenie;
		String[] tabDostawca,tabSposobDostawy,tabTowar;
		JPanel panelDaneZam,panelZamTow;
		public Zamowieniev2()
		{
			
			tabbedPane = new JTabbedPane();
			panelDaneZam = new JPanel();
			panelDaneZam.setLayout(new GridBagLayout());
			GridBagConstraints cPanelDaneZam= new GridBagConstraints();
			cPanelDaneZam.insets= new Insets(0,10,1,10);
			cPanelDaneZam.fill = GridBagConstraints.HORIZONTAL;
			jlbTerminRealizacji = new JLabel("Termin Realizacji");
			jtfTerminRealizacji = new JTextField("");
			jtfTerminRealizacji.setMaximumSize(new Dimension(100,20));
			jtfTerminRealizacji.setPreferredSize(new Dimension(400,20));
			jlbTerminRealizacji.setToolTipText(jlbTerminRealizacji.getText()+" :format daty yyyy-mm-dd(2016-10-06)");
			jtfTerminRealizacji.setToolTipText(jlbTerminRealizacji.getText()+" :format daty yyyy-mm-dd(2016-10-06)");
			
			jlbTytulZamowienie = new JLabel("Formularz do zamowienia");
			jlbTytulZamowienie.setFont(new Font("Calibri", Font.BOLD, 30));
			
			jlbDataRealizacji = new JLabel("Data Realizacji");
			jtfDataRealizacji = new JTextField("");
			jtfDataRealizacji.setMaximumSize(new Dimension(100,20));
			jtfDataRealizacji.setPreferredSize(new Dimension(400,20));
			jlbDataRealizacji.setToolTipText(jlbDataRealizacji.getText()+" :format daty yyyy-mm-dd(2016-10-06)");
			jtfDataRealizacji.setToolTipText(jlbDataRealizacji.getText()+" :format daty yyyy-mm-dd(2016-10-06)");
			
			jlbKosztZamowienia = new JLabel("Koszt Zamowienia");
			jtfKosztZamowienia = new JTextField("");
			jtfKosztZamowienia.setMaximumSize(new Dimension(100,20));
			jtfKosztZamowienia.setPreferredSize(new Dimension(400,20));
			jlbKosztZamowienia.setToolTipText(jlbKosztZamowienia.getText()+" :prosze wpisywac liczby");
			jtfKosztZamowienia.setToolTipText(jlbKosztZamowienia.getText()+" :prosze wpisywac liczby");
			
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
			jcbDostawca.addActionListener(this);
			jtfDostawca = new JTextField();
			jtfDostawca.setVisible(false);
			
			jlbDataWystawienia = new JLabel("Data Wystawienia");
			jtfDataWystawienia = new JTextField("");
			jtfDataWystawienia.setMaximumSize(new Dimension(100,20));
			jtfDataWystawienia.setPreferredSize(new Dimension(400,20));
			jlbDataWystawienia.setToolTipText(jlbDataWystawienia.getText()+" :format daty yyyy-mm-dd(2016-10-06)");
			jtfDataWystawienia.setToolTipText(jlbDataWystawienia.getText()+" :format daty yyyy-mm-dd(2016-10-06)");
			
			jlbNumerZamowienia = new JLabel("Numer Zamowienia");
			jtfNumerZamowienia = new JTextField("");
			jtfNumerZamowienia.setMaximumSize(new Dimension(100,20));
			jtfNumerZamowienia.setPreferredSize(new Dimension(400,20));
			jlbNumerZamowienia.setToolTipText(jlbNumerZamowienia.getText()+" :prosze wpisywac liczby");
			jtfNumerZamowienia.setToolTipText(jlbNumerZamowienia.getText()+" :prosze wpisywac liczby");
			
			jlbSposobDostawy = new JLabel("Sposob Dostawy");
			jcbSposobDostawy = new JComboBox<String>(tabSposobDostawy);
			jtfSposobDostawy = new JTextField();
			jtfSposobDostawy.setVisible(false);
			
			jlbKosztDostawy = new JLabel("Koszt Dostawy");
			jtfKosztDostawy = new JTextField("");
			jtfKosztDostawy.setMaximumSize(new Dimension(100,20));
			jtfKosztDostawy.setPreferredSize(new Dimension(400,20));
			jlbKosztDostawy.setToolTipText(jlbKosztDostawy.getText()+" :prosze wpisywac liczby");
			jtfKosztDostawy.setToolTipText(jlbKosztDostawy.getText()+" :prosze wpisywac liczby");
			
			jlbWartoscTowarow= new JLabel("Wartosc Towarow");
			jtfWartoscTowarow = new JTextField("");
			jtfWartoscTowarow.setMaximumSize(new Dimension(100,20));
			jtfWartoscTowarow.setPreferredSize(new Dimension(400,20));
			jlbWartoscTowarow.setToolTipText(jlbWartoscTowarow.getText()+" :prosze wpisywac liczby");
			jtfWartoscTowarow.setToolTipText(jlbWartoscTowarow.getText()+" :prosze wpisywac liczby");
			
			
			cPanelDaneZam.gridx = 1; cPanelDaneZam.gridy=0;
			panelDaneZam.add(jlbTytulZamowienie,cPanelDaneZam);
			cPanelDaneZam.gridx = 0; cPanelDaneZam.gridy++;
			panelDaneZam.add(jlbTerminRealizacji, cPanelDaneZam);
			cPanelDaneZam.gridx++; 
			panelDaneZam.add(jtfTerminRealizacji, cPanelDaneZam);
			cPanelDaneZam.gridx = 0; cPanelDaneZam.gridy++;
			panelDaneZam.add(jlbDataRealizacji, cPanelDaneZam);
			cPanelDaneZam.gridx++;
			panelDaneZam.add(jtfDataRealizacji, cPanelDaneZam);
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
			panelDaneZam.add(jlbNumerZamowienia, cPanelDaneZam);
			cPanelDaneZam.gridx++;
			panelDaneZam.add(jtfNumerZamowienia, cPanelDaneZam);
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
				e.printStackTrace();
			}
			
			panelZamTow = new JPanel();
			panelZamTow.setLayout(new GridBagLayout());
			GridBagConstraints cPanelZamTow= new GridBagConstraints();
			cPanelZamTow.insets= new Insets(0,10,1,10);
			cPanelZamTow.fill = GridBagConstraints.HORIZONTAL;
			
			jlTytulZamTow = new JLabel("Dodawanie towarow");
			jlTytulZamTow.setFont(new Font("Calibri", Font.BOLD, 30));
			
			jlLP = new JLabel("Liczba porzadkowa");
			jtfLP = new JTextField("");
			jtfLP.setMaximumSize(new Dimension(100,20));
			jtfLP.setPreferredSize(new Dimension(400,20));
			jlLP.setToolTipText(jlLP.getText()+" :prosze podac liczbe");
			jtfLP.setToolTipText(jlLP.getText()+" :prosze podac liczbe");
			
			jlTowar = new JLabel("Towar");
			jcbTowar = new JComboBox<String>(tabTowar);
			jtfTowar = new JTextField();
			jtfTowar.setVisible(false);
			
			jlCena = new JLabel("Cena");
			jtfCena = new JTextField("");
			jtfCena.setMaximumSize(new Dimension(100,20));
			jtfCena.setPreferredSize(new Dimension(400,20));
			jlCena.setToolTipText(jlCena.getText()+" :prosze podac liczbe");
			jtfCena.setToolTipText(jlCena.getText()+" :prosze podac liczbe");
			
			jlIlosc = new JLabel("Ilosc");
			jtfIlosc = new JTextField("");
			jtfIlosc.setMaximumSize(new Dimension(100,20));
			jtfIlosc.setPreferredSize(new Dimension(400,20));
			jlIlosc.setToolTipText(jlIlosc.getText()+" :prosze podac liczbe");
			jtfIlosc.setToolTipText(jlIlosc.getText()+" :prosze podac liczbe");
			
			jlWartoscNetto = new JLabel("Wartosc Netto");
			jtfWartoscNetto = new JTextField("");
			jtfWartoscNetto.setMaximumSize(new Dimension(100,20));
			jtfWartoscNetto.setPreferredSize(new Dimension(400,20));
			jlWartoscNetto.setToolTipText(jlWartoscNetto.getText()+" :prosze podac liczbe");
			jtfWartoscNetto.setToolTipText(jlWartoscNetto.getText()+" :prosze podac liczbe");
			
			jlZamowienie = new JLabel("Zamowienie");
			jtfZamowienie = new JTextField("");
			jtfZamowienie.setMaximumSize(new Dimension(100,20));
			jtfZamowienie.setPreferredSize(new Dimension(400,20));
			jlZamowienie.setToolTipText(jlZamowienie.getText()+" :prosze podac liczbe");
			jtfZamowienie.setToolTipText(jlZamowienie.getText()+" :prosze podac liczbe");
			
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
			panelZamTow.add(jlZamowienie, cPanelZamTow);
			cPanelZamTow.gridx++; 
			panelZamTow.add(jtfZamowienie, cPanelZamTow);
			
			tabbedPane.addTab("Dodanie Towaru",null,panelZamTow,"Formularz do zamowienieTowar");
			
			add(tabbedPane);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Object z = e.getSource();
			if(z==jcbDostawca)
			{
				
				String sqlTowar="SELECT * from towar INNER JOIN dostawcatowar ON dostawcatowar.IdTowar=towar.IdTowar INNER JOIN dostawca ON dostawcatowar.IdDostawca=dostawca.IdDostawca WHERE NazwaSkrocona='"+jcbDostawca.getSelectedItem()+"' GROUP BY NazwaTowaru";
				System.out.println(sqlTowar);
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
						System.out.println(tabTowar[k]);
						k++;
					}
					jcbTowar.setModel(new DefaultComboBoxModel<String>(tabTowar));
					
					
				} catch (SQLException d) {
					// TODO Auto-generated catch block
					d.printStackTrace();
				}
				
			}
			
		}
	 }


