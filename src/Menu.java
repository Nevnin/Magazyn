import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {
    JMenuBar menuBar;
    JMenu plik , pomoc ,towar , dostawcy ,zamowienie ;
    JMenuItem zaklkartdostawcy, wykazdostawcow, szukanietowarow, historiazamowien, zamowienietowaru, stanmagazynowy , reklamacja,towarydostawcy, wyszukiwanie, wgKaterorii, wgNazwy, wgWartosci, zrealizowaneWOkresie, doRealizacjiWOkresie,rankingTowarow;  
    
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
        stanmagazynowy = new JMenuItem("Stan magazynowy");
        reklamacja = new JMenuItem("Reklamacja towaru");
        towarydostawcy = new JMenuItem("Wyszukiwanie");
        wyszukiwanie = new JMenu("Wyszukiwanie");
        rankingTowarow = new JMenuItem("Ranking Towarow");
        
        

        
        
        towar.add(szukanietowarow);
        towar.add(towarydostawcy);
        towar.add(stanmagazynowy);
        towar.add(reklamacja);
        towar.add(rankingTowarow);
        dostawcy.add(zaklkartdostawcy);
        dostawcy.add(wykazdostawcow);
        zamowienie.add(zamowienietowaru);
        zamowienie.add(historiazamowien);
        zamowienie.add(wyszukiwanie);
        
        
        wgKaterorii = new JMenuItem("wg kategorii towarów");
        wgNazwy = new JMenuItem("wg nazwy towaru");
        wgWartosci = new JMenuItem("wg wartoœci");
        zrealizowaneWOkresie = new JMenuItem("zrealizowane w okresie");
        doRealizacjiWOkresie = new JMenuItem("do realizacji w okresie");
        
        
        wyszukiwanie.add(wgKaterorii);
        wyszukiwanie.add(wgNazwy);
        wyszukiwanie.add(wgWartosci);
        wyszukiwanie.add(zrealizowaneWOkresie);
        wyszukiwanie.add(doRealizacjiWOkresie);
        add(menuBar);
    }
}
