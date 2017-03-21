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
	KartaDostawcy kartaDostawcy = new KartaDostawcy();
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
        }
	}
	
	private void dopasujSieDoZawartosci() {
		// TODO Auto-generated method stub
		 pack();   
	        setLocationRelativeTo(null); 
	}
	public void removeP(){
		remove(panel);
		remove(p1);
		remove(kartaDostawcy);
	}
}
