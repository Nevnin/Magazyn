import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {
    JMenuBar menuBar;
    JMenu plik , pomoc ,towar , dostawcy ,zamowienie;
    JMenuItem zaklkartdostawcy, wykazdostawcow, szukanietowarow, historiazamowien, zamowienietowaru, stanmagazynowy , reklamacja;  
    
    public Menu() {
        menuBar =  new JMenuBar();
        plik = new JMenu("Plik");
        pomoc = new JMenu("Pomoc");
        towar = new JMenu("Towar");
        dostawcy = new JMenu("Dostawcy");
        zamowienie = new JMenu("Zamowienie");
        menuBar.add(plik);
        menuBar.add(zamowienie);
        menuBar.add(towar);
        menuBar.add(dostawcy);
        menuBar.add(zamowienie);
        zaklkartdostawcy = new JMenuItem("Zak�adanie karty dostawcy");
        wykazdostawcow = new JMenuItem("Wykaz dostawc�w");
        szukanietowarow = new JMenuItem("Szukanie towar�w");
        historiazamowien = new JMenuItem("Historia zam�wie�");
        zamowienietowaru = new JMenuItem("Zam�wienie towaru");
        stanmagazynowy = new JMenuItem("Stan magazynowy");
        reklamacja = new JMenuItem("Reklamacja towaru");
        towar.add(szukanietowarow);
        towar.add(stanmagazynowy);
        towar.add(reklamacja);
        dostawcy.add(zaklkartdostawcy);
        dostawcy.add(wykazdostawcow);
        zamowienie.add(zamowienietowaru);
        zamowienie.add(historiazamowien);
        add(menuBar);
    }
}
