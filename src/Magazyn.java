import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Magazyn extends JFrame implements ActionListener {
	Panel panel;
	Panel p1;
	WykazDostawcow wykazDostawcow;
	KartaDostawcy kartaDostawcy;
	Zamowieniev2 zamowienie;
	HistoriaZamowien hs;
	TowaryDostawcy td;
	OdbiorZamowien oz;
	WyszZamNaDanyTowar dt;
	WyszZamNaDanyOkres okres;
	WyszZamZrealizowane zreal;
	WyszWgWartosci wgWartosci;
	WyszWgKategorii wgKategorii;
	Menu menu;
	Polaczenie polaczenie;
	StanMagazynowy stanMag;
	public Magazyn() {
		super("Magazyn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		polaczenie = new Polaczenie();
		//setMinimumSize(new Dimension(400, 350));	
		menu = new Menu();
		setJMenuBar(menu);
		p1 = new Panel(400,400);
		add(p1);
		ustawNasluchZdarzen();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		Object[] tabPom1 = {"1"};
		String[][] tabPom;
		String query="Select idDostawca,NazwaPelna,NazwaSkrocona from dostawca where idDostawca=?";
		tabPom = polaczenie.sqlSelectTest(query,tabPom1);
		System.out.println(tabPom.length+", ");
		for(int i=0; i<tabPom.length; i++){
			for(int j=0; j<tabPom[0].length; j++){
				System.out.println(tabPom[i][j]);
			}
		}
	}
	private void ustawNasluchZdarzen() {
		menu.szukanietowarow.addActionListener(this);
		menu.plik.addActionListener(this);
		menu.zaklkartdostawcy.addActionListener(this);
		menu.zamowienietowaru.addActionListener(this);
		menu.stanmagazynowy.addActionListener(this);
		menu.historiazamowien.addActionListener(this);
		menu.wykazdostawcow.addActionListener(this);
		menu.towarydostawcy.addActionListener(this);
		menu.odbiorzamowien.addActionListener(this);
		menu.danyTowar.addActionListener(this);
		menu.danyOkres.addActionListener(this);
		menu.zrealizowane.addActionListener(this);
		menu.wgWartosci.addActionListener(this);
		menu.wgKategorii.addActionListener(this);
		if(oz !=null){oz.jbZatwierdz.addActionListener(this);}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object z= e.getSource();
        if(z==menu.szukanietowarow) {
            removeP();
            add(p1);
            validate();
            dopasujSieDoZawartosci();
            repaint();
        } else if(z==menu.stanmagazynowy){
        	removeP();
        	stanMag = new StanMagazynowy();
        	add(stanMag);
        	validate();
            dopasujSieDoZawartosci();
            repaint();
        	
        } else if(z==menu.zaklkartdostawcy) {
        	removeP();
        	kartaDostawcy = new KartaDostawcy();
        	add(kartaDostawcy);
        	validate();
            dopasujSieDoZawartosci();
            repaint();
        } else if(z==menu.zamowienietowaru) {
        	removeP();
        	zamowienie = new Zamowieniev2();
        	add(zamowienie);
        	zamowienie.jbZamow.addActionListener(this);
        	validate();
        	dopasujSieDoZawartosci();
        	repaint();
        }else if(z==menu.historiazamowien){
        	removeP();
        	hs = new HistoriaZamowien();
        	add(hs);
        	validate();
        	dopasujSieDoZawartosci();
        	repaint();
        }else if(z==menu.odbiorzamowien){
        	removeP();
        	oz = new OdbiorZamowien();
        	oz.jbZatwierdz.addActionListener(this);
        	add(oz);
        	validate();
        	dopasujSieDoZawartosci();
        	repaint();
        }else if(z==menu.wykazdostawcow){
        	removeP();
        	wykazDostawcow = new WykazDostawcow();
        	add(wykazDostawcow);
        	validate();
        	dopasujSieDoZawartosci();
        	repaint();
        }else if(z==menu.towarydostawcy){
        	removeP();
        	td = new TowaryDostawcy();
        	add(td);
        	validate();
        	dopasujSieDoZawartosci();
        	repaint();
        }else if(z==menu.danyTowar){
        	removeP();
        	dt = new WyszZamNaDanyTowar();
        	add(dt);
        	validate();
        	dopasujSieDoZawartosci();
        	repaint();
        }else if(z==menu.wgWartosci){
        	removeP();
        	wgWartosci = new WyszWgWartosci();
        	add(wgWartosci);
        	validate();
        	dopasujSieDoZawartosci();
        	repaint();
        }else if(z==menu.wgKategorii){
        	removeP();
        	wgKategorii = new WyszWgKategorii();
        	add(wgKategorii);
        	validate();
        	dopasujSieDoZawartosci();
        	repaint();
        }else if(z==menu.danyOkres){
        	removeP();
        	okres = new WyszZamNaDanyOkres();
        	add(okres);
        	validate();
        	dopasujSieDoZawartosci();
        	repaint();
        }else if(z==menu.zrealizowane){
        	removeP();
        	zreal = new WyszZamZrealizowane();
        	add(zreal);
        	validate();
        	dopasujSieDoZawartosci();
        	repaint();
        }
        if(zamowienie!=null){
        if(z==zamowienie.jbZamow)
		{
			String TerminRealizacji = zamowienie.jtfTerminRealizacji.getText().toString();
			try {
				 String walidacja = zamowienie.walidacjaDat(TerminRealizacji);
				 walidacja+=zamowienie.walidacjaTabeli();
				 if(walidacja.length()>0)
				 {
			    	JOptionPane.showMessageDialog(null, walidacja,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
				 }else
				 {
					 try {
						zamowienie.Zamowienie();
						zamowienie.dodanieTowarowDoZamowienia();
						JOptionPane.showMessageDialog(null, "Pomyœlnie dodane zamówienie!!!");
						removeP();
			        	zamowienie = new Zamowieniev2();
			        	add(zamowienie);
			        	zamowienie.jbZamow.addActionListener(this);
			        	validate();
			        	dopasujSieDoZawartosci();
			        	repaint();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 
				 }
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
        }
        if(oz!=null)
        {
        	 if(z==oz.jbZatwierdz)
     		{
     			String TerminRealizacji = oz.jtfDataWystawienia.getText().toString();
     			try {
     				 String walidacja = oz.walidacjaDat(TerminRealizacji);
     				 if(walidacja.length()>0)
     				 {
     			    	JOptionPane.showMessageDialog(null, walidacja,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
     				 }else
     				 {			
     					 oz.dodajPZ();
     					 JOptionPane.showMessageDialog(null, "Pomyœlnie dodane zamówienie!!!");
     					 removeP();
     			        	oz = new OdbiorZamowien();
     			        	add(oz);
     			        	oz.jbZatwierdz.addActionListener(this);
     			        	validate();
     			        	dopasujSieDoZawartosci();
     			        	repaint();
     				 }
     				 
     		}catch (Exception e1) {
     			// TODO: handle exception
     		}
     		
     	}
        }
	}
	private void dopasujSieDoZawartosci() {
		 pack();   
	     setLocationRelativeTo(null); 
	}
	public void removeP(){
		if(p1 != null){ remove(p1); }
		if(kartaDostawcy != null){ remove(kartaDostawcy); }
		if(zamowienie != null){ remove(zamowienie); }
		if(hs != null){ remove(hs); }
		if(stanMag != null){ remove(stanMag); }
		if(wykazDostawcow != null){ remove(wykazDostawcow); }
		if(td != null){ remove(td); }
		if(oz != null){ remove(oz); }
		if(dt != null){ remove(dt); }
		if(wgWartosci != null){ remove(wgWartosci); }
		if(wgKategorii != null){ remove(wgKategorii); }
		if(okres != null){ remove(okres); }
		if(zreal != null){ remove(zreal); }
	}
}
