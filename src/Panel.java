import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JPanel;

public class Panel extends JPanel {
	TextField towar;
    Label szuktowar;
    Button szukaj;
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
    }   
}