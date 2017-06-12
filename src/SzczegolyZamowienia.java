import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SzczegolyZamowienia extends JPanel {
	private Polaczenie polaczenie;
	private JTable tabela;
	JPanel p = new JPanel();
	private String[] tab,columnNames;
	private JSplitPane splitPane,splitPane1;
	private JScrollPane scrollPane,scrollPane1;
	private JLabel jlbNrZam,jlbTermin,jlbDataReal,jlbDataWys,jlbSposDos,jlbKosztDos,jlbWartoscTow,jlbKosztZam,jlbDostawca;
	private JTextField search,jtfNrZam,jtfTermin,jtfDataReal,jtfDataWys,jtfSposDos,jtfKosztDos,jtfWartoscTow,jtfKosztZam,jtfDostawca;
	DecimalFormat df ;
	public SzczegolyZamowienia(){
		
		String[] columnNames = 
			{"Lp",
            "Nazwa Towaru",
            "Ilosc",
            "Cena",
            "Wartosc Netto"};
		
		String[][] data = new String[0][0];
		
		tabela = new JTable(data, columnNames);
		tabela.setDefaultEditor(Object.class, null);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setAutoCreateRowSorter(true);
		tabela.setPreferredScrollableViewportSize(new Dimension(400, 200));
		
		df = new DecimalFormat("###,###.00");
		DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(' ');
		df.setDecimalFormatSymbols(symbols);
		
		splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		scrollPane1 = new JScrollPane(tabela);
		p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		p.setPreferredSize(new Dimension(600, 200));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 1, 10);
		
		jlbNrZam = new JLabel("Zamówienie nr: xxxx/xx/xx/x");
		jlbNrZam.setFont(new Font("Calibri", Font.BOLD, 30));
		//jlbNrZam.setPreferredSize(new Dimension(150, 20));
		
		//jtfNrZam.setEditable(false);
		jlbTermin = new JLabel("Termin Realizacji:");
		jtfTermin = new JTextField();
		jtfTermin.setEditable(false);
		jlbDataReal = new JLabel("Data Realizacji:");
		jtfDataReal = new JTextField();
		jtfDataReal.setEditable(false);
		jlbDataWys = new JLabel("Data Wystawienia:");
		jtfDataWys = new JTextField();
		jtfDataWys.setEditable(false);
		jlbSposDos = new JLabel("Sposob Dostawy:");
		jtfSposDos = new JTextField();
		jtfSposDos.setEditable(false);
		jlbKosztDos = new JLabel("Koszt Dostawy:");
		jtfKosztDos = new JTextField();
		jtfKosztDos.setEditable(false);
		jlbWartoscTow = new JLabel("Wartosc Towarów:");
		jtfWartoscTow = new JTextField();
		jtfWartoscTow.setEditable(false);
		jlbKosztZam = new JLabel("Koszt Zamowienia:");
		jtfKosztZam = new JTextField();
		jtfKosztZam.setEditable(false);
		jlbDostawca = new JLabel("Dostawca:");
		jtfDostawca = new JTextField();
		jtfDostawca.setEditable(false);
//		jlbLp = new JLabel("Lp");
//		jlbNazwaTowaru = new JLabel("Nazwa Towaru");
//		jlbCena = new JLabel("Cena");
//		jlbIlosc = new JLabel("Ilosc");
//		jlbWartosc = new JLabel("Wartosc");
		//area = new JTextArea();
		
		c.gridx = 1; c.gridy = 0;
        p.add(jlbNrZam,c);
//        c.gridx = 1;
//        p.add(jtfNrZam,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTermin,c);
        c.gridx = 1;
        p.add(jtfTermin,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbDataReal,c);
        c.gridx = 1;
        p.add(jtfDataReal,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbDataWys,c);
        c.gridx = 1;
        p.add(jtfDataWys,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbSposDos,c);
        c.gridx += 1;
        p.add(jtfSposDos,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbKosztDos,c);
        c.gridx += 1;
        p.add(jtfKosztDos,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbWartoscTow,c);
        c.gridx += 1;
        p.add(jtfWartoscTow,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbKosztZam,c);
        c.gridx += 1;
        p.add(jtfKosztZam,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbDostawca,c);
        c.gridx += 1;
        p.add(jtfDostawca,c);

        splitPane1.setTopComponent(p);
        splitPane1.setBottomComponent(scrollPane1);
        
 
     
        
        add(splitPane1);
        
		
	}
	public SzczegolyZamowienia(String sel){
		
		String[] columnNames = 
			{"Lp",
            "Nazwa Towaru",
            "Ilosc",
            "Cena",
            "Wartosc Netto"};
		
		String[][] data = new String[0][0];
		
		tabela = new JTable(data, columnNames);
		tabela.setDefaultEditor(Object.class, null);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setAutoCreateRowSorter(true);
		tabela.setPreferredScrollableViewportSize(new Dimension(400, 200));
		
		splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		scrollPane1 = new JScrollPane(tabela);
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		p.setPreferredSize(new Dimension(600, 200));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 1, 10);
		
		jlbNrZam = new JLabel(" ");
		jlbNrZam.setFont(new Font("Calibri", Font.BOLD, 30));
//		jlbNrZam = new JLabel("Numer Zamowienia:");
//		jlbNrZam.setPreferredSize(new Dimension(150, 20));
//		jtfNrZam = new JTextField("Numer Zamowienia");
//		jtfNrZam.setPreferredSize(new Dimension(400, 20));
//		jtfNrZam.setEditable(false);
		jlbTermin = new JLabel("Termin Realizacji:");
		jtfTermin = new JTextField();
		jtfTermin.setEditable(false);
		jlbDataReal = new JLabel("Data Realizacji:");
		jtfDataReal = new JTextField();
		jtfDataReal.setEditable(false);
		jlbDataWys = new JLabel("Data Wystawienia:");
		jtfDataWys = new JTextField();
		jtfDataWys.setEditable(false);
		jlbSposDos = new JLabel("Sposob Dostawy:");
		jtfSposDos = new JTextField();
		jtfSposDos.setEditable(false);
		jlbKosztDos = new JLabel("Koszt Dostawy:");
		jtfKosztDos = new JTextField();
		jtfKosztDos.setEditable(false);
		jlbWartoscTow = new JLabel("Wartosc Towarów:");
		jtfWartoscTow = new JTextField();
		jtfWartoscTow.setEditable(false);
		jlbKosztZam = new JLabel("Koszt Zamowienia:");
		jtfKosztZam = new JTextField();
		jtfKosztZam.setEditable(false);
		jlbDostawca = new JLabel("Dostawca:");
		jtfDostawca = new JTextField();
		jtfDostawca.setEditable(false);
//		jlbLp = new JLabel("Lp");
//		jlbNazwaTowaru = new JLabel("Nazwa Towaru");
//		jlbCena = new JLabel("Cena");
//		jlbIlosc = new JLabel("Ilosc");
//		jlbWartosc = new JLabel("Wartosc");
		//area = new JTextArea();
		
		c.gridx = 1; c.gridy = 0;
        p.add(jlbNrZam,c);
//        c.gridx = 1;
//        p.add(jtfNrZam,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbTermin,c);
        c.gridx = 1;
        p.add(jtfTermin,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbDataReal,c);
        c.gridx = 1;
        p.add(jtfDataReal,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbDataWys,c);
        c.gridx = 1;
        p.add(jtfDataWys,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbSposDos,c);
        c.gridx += 1;
        p.add(jtfSposDos,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbKosztDos,c);
        c.gridx += 1;
        p.add(jtfKosztDos,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbWartoscTow,c);
        c.gridx += 1;
        p.add(jtfWartoscTow,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbKosztZam,c);
        c.gridx += 1;
        p.add(jtfKosztZam,c);
        c.gridx = 0; c.gridy++;
        p.add(jlbDostawca,c);
        c.gridx += 1;
        p.add(jtfDostawca,c);

        splitPane1.setTopComponent(p);
        splitPane1.setBottomComponent(scrollPane1);
        
 
     
        
        add(splitPane1);
        
        //value changed z HistoriiZamowien
        String[] tabPom;
		String[][] towary;
		System.out.println(sel);
		String sql = "SELECT IdZamowienie, NumerZamowienia, TerminRealizacji, DataRealizacji, DataWystawienia, sposobdostawy.SposobDostawy, KosztDostawy,WartoscTowarow, CalkowitaWartoscZamowienia, dostawca.NazwaSkrocona FROM zamowienie INNER JOIN sposobdostawy ON sposobdostawy.IdSposobDostawy=zamowienie.IdSposobDostawy INNER JOIN dostawca ON dostawca.IdDostawca=zamowienie.IdDostawcy WHERE NumerZamowienia='"+sel+"'";
		System.out.println(sql);
		
		try {
			polaczenie = new Polaczenie();
			ResultSet rs = polaczenie.sqlSelect(sql);
			tabPom = new String[10];
			
			rs.next();
			for(int i = 0;i<tabPom.length;i++)
			{
				tabPom[i]=rs.getString(i+1);
				System.out.print(tabPom[i]);
			}
			jlbNrZam.setText("Zamówienie nr: "+tabPom[1]);
			jtfTermin.setText(tabPom[2]);
			jtfDataReal.setText(tabPom[3]);
			jtfDataWys.setText(tabPom[4]);
			jtfSposDos.setText(tabPom[5]);
			jtfKosztDos.setText(tabPom[6]);
			jtfWartoscTow.setText(tabPom[7]);
			jtfKosztZam.setText(tabPom[8]);
			jtfDostawca.setText(tabPom[9]);
			int id = Integer.parseInt(tabPom[0]);

			String query1 = "SELECT Lp,towar.NazwaTowaru,Ilosc,Cena,WartoscNetto FROM zamowienietowar INNER JOIN towar ON towar.IdTowar = zamowienietowar.IdTowar WHERE zamowienietowar.IdZamowienie = '"+id+"'";
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
				double cena = Double.parseDouble(result.getString(4));
				towary[j][3] = df.format(cena);
				double cena1 = Double.parseDouble(result.getString(5));
				towary[j][4] = df.format(cena1);
				
				
				j++;
			}
		

//			String[] columnNames = 
//				{"Lp",
//	            "Nazwa Towaru",
//	            "Cena",
//	            "Ilosc",
//	            "Wartosc Netto"};
			DefaultTableModel tableModel = new DefaultTableModel(0,0);
			tableModel.setColumnIdentifiers(columnNames);
			tabela.setModel(tableModel);
			DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
			tableRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
			tabela.getColumnModel().getColumn(3).setCellRenderer(tableRenderer);
			tabela.getColumnModel().getColumn(4).setCellRenderer(tableRenderer);
			for (int i = 0; i < towary.length; i++) {
				String[] data1 = new String[towary[0].length];
				for(int z = 0;z<5;z++){
					data1[z]= towary[i][z];
				}
				tableModel.addRow(data1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage()+"1");
			e.printStackTrace();
		}
        
        
		
	}
}
