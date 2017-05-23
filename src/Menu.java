import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {
    JMenuBar menuBar;
    JMenu plik , pomoc ,towar , dostawcy ,zamowienie, wyszukiwanie;
    JMenuItem zaklkartdostawcy, wykazdostawcow, szukanietowarow, historiazamowien, zamowienietowaru, stanmagazynowy , reklamacja,towarydostawcy,odbiorzamowien, danyOkres, danyTowar, zrealizowane, wgWartosci, wgKategorii;  
    public Menu() {
        menuBar =  new JMenuBar();
        plik = new JMenu("Plik");
        pomoc = new JMenu("Pomoc");
        towar = new JMenu("Towar");
        dostawcy = new JMenu("Dostawcy");
        zamowienie = new JMenu("Zamowienie");
        wyszukiwanie = new JMenu("Wyszukiwanie zam�wie�: ");
             
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
        odbiorzamowien = new JMenuItem("Odbi�r zam�wie�");
        stanmagazynowy = new JMenuItem("Stan magazynowy");
        reklamacja = new JMenuItem("Reklamacja towaru");
        towarydostawcy = new JMenuItem("Wyszukiwanie");
        danyTowar = new JMenuItem("na dany towar");
        danyOkres = new JMenuItem("do realizacji w danym okresie");
        zrealizowane = new JMenuItem("zrealizowane w danym okresie");
        wgWartosci = new JMenuItem("wed�ug warto�ci");
        wgKategorii = new JMenuItem("wed�ug kategorii");
        
        
        towar.add(szukanietowarow);
        towar.add(towarydostawcy);
        towar.add(stanmagazynowy);
        towar.add(reklamacja);
        
        
        
        wyszukiwanie.add(danyTowar);
        wyszukiwanie.add(danyOkres);
        wyszukiwanie.add(zrealizowane);
        wyszukiwanie.add(wgWartosci);
        wyszukiwanie.add(wgKategorii);
        
        
        dostawcy.add(zaklkartdostawcy);
        dostawcy.add(wykazdostawcow);
        
        zamowienie.add(zamowienietowaru);
        zamowienie.add(historiazamowien);
        zamowienie.add(odbiorzamowien);
        zamowienie.add(wyszukiwanie);
        add(menuBar);
    }
}
