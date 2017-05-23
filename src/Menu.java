import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {
    JMenuBar menuBar;
    JMenu plik , pomoc ,towar , dostawcy ,zamowienie;
    JMenuItem zaklkartdostawcy, wykazdostawcow, szukanietowarow, historiazamowien, zamowienietowaru, stanmagazynowy , reklamacja,towarydostawcy,odbiorzamowien;  
    
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
        zaklkartdostawcy = new JMenuItem("Zak³adanie karty dostawcy");
        wykazdostawcow = new JMenuItem("Wykaz dostawców");
        szukanietowarow = new JMenuItem("Szukanie towarów");
        historiazamowien = new JMenuItem("Historia zamówieñ");
        zamowienietowaru = new JMenuItem("Zamówienie towaru");
        odbiorzamowien = new JMenuItem("Odbiór zamówieñ");
        stanmagazynowy = new JMenuItem("Stan magazynowy");
        reklamacja = new JMenuItem("Reklamacja towaru");
        towarydostawcy = new JMenuItem("Wyszukiwanie");
        towar.add(szukanietowarow);
        towar.add(towarydostawcy);
        towar.add(stanmagazynowy);
        towar.add(reklamacja);
        dostawcy.add(zaklkartdostawcy);
        dostawcy.add(wykazdostawcow);
        zamowienie.add(zamowienietowaru);
        zamowienie.add(historiazamowien);
        zamowienie.add(odbiorzamowien);
        add(menuBar);
    }
}
