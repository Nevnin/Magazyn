import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Magazyn extends JFrame implements ActionListener {
	Panel panel;
	Panel p1;
	WykazDostawcow wykazDostawcow = new WykazDostawcow();
	KartaDostawcy kartaDostawcy = new KartaDostawcy();
	Zamowieniev2 zamowienie = new Zamowieniev2();
	HistoriaZamowien hs = new HistoriaZamowien();
	TowaryDostawcy td = new TowaryDostawcy();
	OdbiorZamowien oz= new OdbiorZamowien();
	WyszZamNaDanyTowar dt = new WyszZamNaDanyTowar();
	WyszZamNaDanyOkres okres = new WyszZamNaDanyOkres();
	WyszZamZrealizowane zreal = new WyszZamZrealizowane();
	WyszWgWartosci wgWartosci = new WyszWgWartosci();
	WyszWgKategorii wgKategorii = new WyszWgKategorii();
	Menu menu;
	Polaczenie polaczenie;
	StanMagazynowy stanMag = new StanMagazynowy();
	String query="Select * from uzytkownik";
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
