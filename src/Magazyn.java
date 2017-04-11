import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;

public class Magazyn extends JFrame implements ActionListener {
	Panel panel;
	Panel p1;
	WykazDostawcow wykazDostawcow = new WykazDostawcow();
	KartaDostawcy kartaDostawcy = new KartaDostawcy();
	Zamowienie zamowienie = new Zamowienie();
	HistoriaZamowien hs = new HistoriaZamowien();
	TowaryDostawcy td = new TowaryDostawcy();
	Menu menu;
	Polaczenie polaczenie;
	StanMagazynowy stanMag = new StanMagazynowy();
	String query="Select * from uzytkownik";
	public Magazyn() {
		super("Magazyn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			 polaczenie = new Polaczenie();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
        	zamowienie = new Zamowienie();
        	add(zamowienie);
        	validate();
        	dopasujSieDoZawartosci();
        	repaint();
        }else if(z==menu.historiazamowien)
        {
        	removeP();
        	hs = new HistoriaZamowien();
        	add(hs);
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
        }
	}
	private void dopasujSieDoZawartosci() {
		 pack();   
	     setLocationRelativeTo(null); 
	}
	public void removeP(){
		remove(p1);
		remove(kartaDostawcy);
		remove(zamowienie);
		remove(hs);
		remove(stanMag);
		remove(wykazDostawcow);
		remove(td);
	}
}
