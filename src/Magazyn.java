import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.w3c.dom.events.MouseEvent;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Magazyn extends JFrame implements ActionListener {
	Panel panel;
	Panel p1;
	Panel zaklkartdostawcy = new Panel(400,400,4);
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
		
			
		menu = new Menu();
		setJMenuBar(menu);
		panel = new Panel(400,400,0);
		p1 = new Panel(400,400,1);
		add(panel);
		ustawNasluchZdarzen();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private void ustawNasluchZdarzen() {
		menu.szukanietowarow.addActionListener(this);
		menu.plik.addActionListener(this);
		menu.zaklkartdostawcy.addActionListener(this);
		zaklkartdostawcy.jtfNazwaPelna.addActionListener(this);
		zaklkartdostawcy.jtfNip.addActionListener(this);
		zaklkartdostawcy.jtfTelefon1.addActionListener(this);
		zaklkartdostawcy.jtfTelefon2.addActionListener(this);
		zaklkartdostawcy.jtfTelefon3.addActionListener(this);
		zaklkartdostawcy.jtfNazwaDzialu.addActionListener(this);
		zaklkartdostawcy.jtfNrKonta.addActionListener(this);
		zaklkartdostawcy.jtfAdres.addActionListener(this);
		zaklkartdostawcy.jtfKodPocztowy.addActionListener(this);
		zaklkartdostawcy.jtfPoczta.addActionListener(this);
		zaklkartdostawcy.jbtPrzycisk.addActionListener(this);
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
        	add(zaklkartdostawcy);
        	validate();
            dopasujSieDoZawartosci();
            repaint();
        }
        else if(z==zaklkartdostawcy.jbtPrzycisk){
        	kartaDostawcy();
        }
        else if(z==zaklkartdostawcy.jtfNazwaSkrocona || z==zaklkartdostawcy.jtfNazwaPelna || z==zaklkartdostawcy.jtfNip || z==zaklkartdostawcy.jtfTelefon1 ||
        		z==zaklkartdostawcy.jtfTelefon2 || z==zaklkartdostawcy.jtfTelefon3 || z==zaklkartdostawcy.jtfNazwaDzialu || z==zaklkartdostawcy.jtfNrKonta ||
        		z==zaklkartdostawcy.jtfAdres || z==zaklkartdostawcy.jtfKodPocztowy || z==zaklkartdostawcy.jtfPoczta){
        	zaklkartdostawcy.jtfNazwaPelna.setBackground(Color.WHITE);
    		zaklkartdostawcy.jtfNazwaPelna.setBackground(Color.WHITE);
    		zaklkartdostawcy.jtfNip.setBackground(Color.WHITE);
    		zaklkartdostawcy.jtfTelefon1.setBackground(Color.WHITE);
    		zaklkartdostawcy.jtfTelefon2.setBackground(Color.WHITE);
    		zaklkartdostawcy.jtfTelefon3.setBackground(Color.WHITE);
    		zaklkartdostawcy.jtfNazwaDzialu.setBackground(Color.WHITE);
    		zaklkartdostawcy.jtfNrKonta.setBackground(Color.WHITE);
    		zaklkartdostawcy.jtfAdres.setBackground(Color.WHITE);
    		zaklkartdostawcy.jtfKodPocztowy.setBackground(Color.WHITE);
    		zaklkartdostawcy.jtfPoczta.setBackground(Color.WHITE);
        }
	}
	public void kartaDostawcy(){
		KartaDostawcy karta = new KartaDostawcy();
    	String jtfNazwaSkrocona = zaklkartdostawcy.jtfNazwaSkrocona.getText().toString();
    	String jtfNazwaPelna = zaklkartdostawcy.jtfNazwaPelna.getText().toString();
    	String jtfNip = zaklkartdostawcy.jtfNip.getText().toString();
    	String jtfTelefon1 = zaklkartdostawcy.jtfTelefon1.getText().toString();
    	String jtfTelefon2 = zaklkartdostawcy.jtfTelefon2.getText().toString();
    	String jtfTelefon3 = zaklkartdostawcy.jtfTelefon3.getText().toString();
    	String jtfNazwaDzialu = zaklkartdostawcy.jtfNazwaDzialu.getText().toString();
    	String jtfNrKonta = zaklkartdostawcy.jtfNrKonta.getText().toString();
    	String jtfAdres = zaklkartdostawcy.jtfAdres.getText().toString();
    	String jtfKodPocztowy = zaklkartdostawcy.jtfKodPocztowy.getText().toString();
    	String jtfPoczta  = zaklkartdostawcy.jtfPoczta.getText().toString();
    	String walidacja = walidacja(jtfNazwaSkrocona, jtfNazwaPelna, jtfNip, jtfTelefon1,
											 jtfTelefon2, jtfTelefon3, jtfNazwaDzialu, jtfNrKonta,
											 jtfAdres, jtfKodPocztowy, jtfPoczta);
    	if(walidacja.length()>0){
    		int dialogButton = JOptionPane.INFORMATION_MESSAGE;
    		JOptionPane.showMessageDialog(null, walidacja,"B³¹d", JOptionPane.INFORMATION_MESSAGE);
    	}else {
	    	boolean spr = karta.insert(jtfNazwaSkrocona, jtfNazwaPelna, jtfNip, jtfTelefon1, jtfTelefon2, jtfTelefon3, jtfNazwaDzialu, jtfNrKonta, jtfAdres, jtfKodPocztowy, jtfPoczta);
	    	//JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	    	if(spr == true){
	    		int dialogButton = JOptionPane.INFORMATION_MESSAGE;
	    		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone powodzeniem","", JOptionPane.INFORMATION_MESSAGE);
	    		zaklkartdostawcy.jtfNazwaPelna.setText("");
	    		zaklkartdostawcy.jtfNazwaPelna.setText("");
	    		zaklkartdostawcy.jtfNip.setText("");
	    		zaklkartdostawcy.jtfTelefon1.setText("");
	    		zaklkartdostawcy.jtfTelefon2.setText("");
	    		zaklkartdostawcy.jtfTelefon3.setText("");
	    		zaklkartdostawcy.jtfNazwaDzialu.setText("");
	    		zaklkartdostawcy.jtfNrKonta.setText("");
	    		zaklkartdostawcy.jtfAdres.setText("");
	    		zaklkartdostawcy.jtfKodPocztowy.setText("");
	    		zaklkartdostawcy.jtfPoczta.setText("");
	    	}
	    	else {
	    		int dialogButton = JOptionPane.ERROR_MESSAGE;
	    		JOptionPane.showMessageDialog(null, "Dodawanie dostawcy zakoñczone niepowodzeniem","Uwaga!", JOptionPane.ERROR_MESSAGE);
	    	}
    	}
	}
    public String walidacja(String jtfNazwaSkrocona,String jtfNazwaPelna,String jtfNip,String jtfTelefon1,
									String jtfTelefon2,String jtfTelefon3,String jtfNazwaDzialu,String jtfNrKonta,
									String jtfAdres,String jtfKodPocztowy,String jtfPoczta){
    	String error="";
    	if(jtfNazwaSkrocona.length()>100){ 
    		error+="Nazwa Skrócona zosta³a podana zbyt d³uga(100max)\n";
    		zaklkartdostawcy.jtfNazwaSkrocona.setBackground(Color.RED);
    	}
    	if(jtfNazwaPelna.length()>100){
    		error+="Nazwa Pe³na zosta³a podana zbyt d³uga(100max)\n";
    		zaklkartdostawcy.jtfNazwaPelna.setBackground(Color.RED);
    	}
    	if(jtfNip.length()!=10){
    		error+="D³ugoœæ Nip'u zosta³a podana nieprawid³owa(10)\n";
    		zaklkartdostawcy.jtfNip.setBackground(Color.RED);
    	}
    	if(jtfTelefon1.length()>20 || jtfTelefon1.length()<9 && jtfTelefon1.length()>0){
    		error+="D³ugoœæ Numeu Telefon1 zosta³a podana nieprawid³owa(9-20)\n";
    		zaklkartdostawcy.jtfTelefon1.setBackground(Color.RED);
    	}
    	if(jtfTelefon2.length()>20 || jtfTelefon2.length()<9 && jtfTelefon2.length()>0){
    		error+="D³ugoœæ Numeu Telefon2 zosta³a podana nieprawid³owa(9-20)\n";
    		zaklkartdostawcy.jtfTelefon2.setBackground(Color.RED);
    	}
    	if(jtfTelefon3.length()>20 || jtfTelefon3.length()<9 && jtfTelefon3.length()>0){
    		error+="D³ugoœæ Numeu Telefon3 zosta³a podana nieprawid³owa(9-20)\n";
    		zaklkartdostawcy.jtfTelefon3.setBackground(Color.RED);
    	}
    	if(jtfNazwaDzialu.length()>50){
    		error+="Nazwa Dzia³u zosta³a podana zbyt d³uga(50max)\n";
    		zaklkartdostawcy.jtfNazwaDzialu.setBackground(Color.RED);
    	}
    	if(jtfNrKonta.length()>30){
    		error+="Nr Konta zosta³ podany zbyt d³ugi(30max)\n";
    		zaklkartdostawcy.jtfNrKonta.setBackground(Color.RED);
    	}
    	if(jtfAdres.length()>50){
    		error+="Adres zosta³ podany zbyt d³ugi(50max)\n";
    		zaklkartdostawcy.jtfAdres.setBackground(Color.RED);
    	}
    	if(jtfKodPocztowy.length()>6){
    		error+="Kod Pocztowy zosta³ podany zbyt d³ugi(6max)\n";
    		zaklkartdostawcy.jtfKodPocztowy.setBackground(Color.RED);
    	}
    	if(jtfPoczta.length()>30){
    		error+="Poczta zosta³a podana zbyt d³uga(30max)\n";
    		zaklkartdostawcy.jtfPoczta.setBackground(Color.RED);
    	}
    	return error;
	}
	private void dopasujSieDoZawartosci() {
		// TODO Auto-generated method stub
		 pack();   
	        setLocationRelativeTo(null); 
	}
	public void removeP(){
		remove(panel);
		remove(p1);
		remove(zaklkartdostawcy);
	}
}
