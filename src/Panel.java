import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JPanel {
	TextField towar;
    Label szuktowar;
    Button szukaj;
    JButton jbtPrzycisk;
    JLabel jlbNazwaSkrocona, jlbNazwaPelna, jlbNip, jlbTelefon1, jlbTelefon2, jlbTelefon3, jlbNazwaDzialu, jlbNrKonta, jlbAdres, jlbKodPocztowy, jlbPoczta;
    JTextField jtfNazwaSkrocona, jtfNazwaPelna, jtfNip, jtfTelefon1, jtfTelefon2, jtfTelefon3, jtfNazwaDzialu, jtfNrKonta, jtfAdres, jtfKodPocztowy, jtfPoczta;
    public Panel(int szerokosc, int wysokosc,int numer) {
        setPreferredSize(new Dimension(szerokosc,wysokosc));
        setLayout(null);
        if(numer == 1){
            szuktowar= new Label("Szukaj towaru"); 
            towar = new TextField("");
            szukaj = new Button("Szukaj");
            szukaj.setBounds(szerokosc-100,wysokosc-40,80,20);
            szuktowar.setBounds(0,0,szerokosc,20);
            towar.setBounds(0,20,szerokosc,20);
            add(szuktowar);
            add(towar);
            add(szukaj);
        }
        if(numer==2) {
        	
        }
        if(numer==3) {
        	
        }
        if(numer==4) {
        	setLayout(new GridBagLayout());
	        GridBagConstraints c = new GridBagConstraints();
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.insets = new Insets(0, 10, 0, 10);
	        
	        jlbNazwaSkrocona = new JLabel("Nazwa Skrócona");
	        jtfNazwaSkrocona = new JTextField("",20);
	        jlbNazwaPelna = new JLabel("Nazwa Pe³na");
	        jtfNazwaPelna = new JTextField("");
	        jlbNip = new JLabel("NIP");
	        jtfNip = new JTextField("");
	        jlbTelefon1 = new JLabel("Telefon 1");
	        jtfTelefon1 = new JTextField("");
			jlbTelefon2 = new JLabel("Telefon 2");
			jtfTelefon2 = new JTextField("");
			jlbTelefon3 = new JLabel("Telefon 3");
			jtfTelefon3 = new JTextField("");
			jlbNazwaDzialu = new JLabel("Nazwa Dzia³u");
			jtfNazwaDzialu = new JTextField("");
			jlbNrKonta = new JLabel("Nr Konta");
			jtfNrKonta = new JTextField("");
			jlbAdres = new JLabel("Adres");
			jtfAdres = new JTextField("");
			jlbKodPocztowy = new JLabel("KodPoczowy");
			jtfKodPocztowy = new JTextField("");
			jlbPoczta = new JLabel("Poczta");
			jtfPoczta = new JTextField("");
	        jbtPrzycisk = new JButton("Zatwierdz");
	        
	        c.gridx = 0; c.gridy = 0;
	        add(jlbNazwaSkrocona,c);
	        c.gridx = 1; c.gridy = 0;
	        add(jtfNazwaSkrocona,c);
	        c.gridx = 0; c.gridy = 1;
	        add(jlbNazwaPelna,c);
	        c.gridx = 1; c.gridy = 1;
	        add(jtfNazwaPelna,c);
	        c.gridx = 0; c.gridy = 2;
	        add(jlbNip,c);
	        c.gridx = 1; c.gridy = 2;
	        add(jtfNip,c);
	        c.gridx = 0; c.gridy = 3;
	        add(jlbTelefon1,c);
	        c.gridx = 1; c.gridy = 3;
	        add(jtfTelefon1,c);
	        c.gridx = 0; c.gridy = 4;
	        add(jlbTelefon2,c);
	        c.gridx = 1; c.gridy = 4;
	        add(jtfTelefon2,c);
	        c.gridx = 0; c.gridy = 5;
	        add(jlbTelefon3,c);
	        c.gridx = 1; c.gridy = 5;
	        add(jtfTelefon3,c);
	        c.gridx = 0; c.gridy = 6;
	        add(jlbNazwaDzialu,c);
	        c.gridx = 1; c.gridy = 6;
	        add(jtfNazwaDzialu,c);
	        c.gridx = 0; c.gridy = 7;
	        add(jlbNrKonta,c);
	        c.gridx = 1; c.gridy = 7;
	        add(jtfNrKonta,c);
	        c.gridx = 0; c.gridy = 8;
	        add(jlbAdres,c);
	        c.gridx = 1; c.gridy = 8;
	        add(jtfAdres,c);
	        c.gridx = 0; c.gridy = 9;
	        add(jlbKodPocztowy,c);
	        c.gridx = 1; c.gridy = 9;
	        add(jtfKodPocztowy,c);
	        c.gridx = 0; c.gridy = 10;
	        add(jlbPoczta,c);
	        c.gridx = 1; c.gridy = 10;
	        add(jtfPoczta,c);
	        c.gridx = 0; c.gridy = 11;
	        add(jbtPrzycisk,c);
        }
    }   
}