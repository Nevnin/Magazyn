import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;

public class Magazyn extends JFrame implements ActionListener {
	Panel panel;
	Panel p1;
	KartaDostawcy kartaDostawcy = new KartaDostawcy();
	Zamowienie zamowienie = new Zamowienie();
	HistoriaZamowien hs = new HistoriaZamowien();
	Menu menu;
	Polaczenie polaczenie;
	
	String query="Select * from uzytkownik";
	public Magazyn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			 polaczenie = new Polaczenie();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		menu.historiazamowien.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String label = e.getActionCommand();
        Object z= e.getSource();
        if(z==menu.szukanietowarow) {
            removeP();
            add(p1);
            validate();
            dopasujSieDoZawartosci();
            repaint();
        }
        else if(z==menu.zaklkartdostawcy) {
        	removeP();
        	kartaDostawcy = new KartaDostawcy();
        	add(kartaDostawcy);
        	validate();
            dopasujSieDoZawartosci();
            repaint();
        }else if(z==menu.zamowienietowaru)
        {
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
        }
	}
	private void dopasujSieDoZawartosci() {
		// TODO Auto-generated method stub
		 pack();   
	     setLocationRelativeTo(null); 
	}
	public void removeP(){
		remove(p1);
		remove(kartaDostawcy);
		remove(zamowienie);
		remove(hs);
	}
}
