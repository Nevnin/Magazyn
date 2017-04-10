import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.TextField;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelDoZam extends JPanel {
	
		JTextField tfTerminRealizacji,tfDataWystawienia;
	    JLabel lTerminRealizacji,lDataRealizacji;
	    JButton zamow,DodajDoZamowienia;
	    	    public PanelDoZam(int numer) {
	        setSize(new Dimension(400, 100));
	        
	    	setLayout(null);
	    	
	    	
	    	if(numer == 0){
	    	JLabel lblTerminRealizacji = new JLabel("Termin Realizacji");
	    	lblTerminRealizacji.setBounds(10, 11, 120, 14);
	    	add(lblTerminRealizacji);
	    	
	    	tfTerminRealizacji = new JTextField();
	    	tfTerminRealizacji.setBounds(128, 8, 100, 20);
	    	tfTerminRealizacji.setToolTipText("Podaj date w formacie: yyyy-mm-dd");
	    	add(tfTerminRealizacji);
	    	tfTerminRealizacji.setColumns(10);
	    	
	    	JLabel lblDataRealizacji = new JLabel("Data Wystawienia");
	    	lblDataRealizacji.setBounds(10, 36, 120, 14);
	    	add(lblDataRealizacji);
	    	
	    	tfDataWystawienia = new JTextField();
	    	tfDataWystawienia.setBounds(128, 33, 100, 20);
	    	tfDataWystawienia.setEditable(false);
	    	SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd"); 
			 Date data = new Date(); 
			 String teraz = dt.format(data);
			 tfDataWystawienia.setText(teraz);
	    	add(tfDataWystawienia);
	    	tfDataWystawienia.setColumns(10);
	    	zamow = new JButton("Zamow");
	    	zamow.setBounds(301, 61, 89, 23);
	    	add(zamow);
	    	}
	    	else
	    	{
	    		JLabel lblDataRealizacji = new JLabel("Data Wystawienia");
		    	lblDataRealizacji.setBounds(10, 36, 88, 14);
		    	add(lblDataRealizacji);
		    	
		    	tfDataWystawienia = new JTextField();
		    	tfDataWystawienia.setBounds(108, 33, 86, 20);
		    	tfDataWystawienia.setEditable(false);
		    	SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd"); 
				 Date data = new Date(); 
				 String teraz = dt.format(data);
				 tfDataWystawienia.setText(teraz);
		    	add(tfDataWystawienia);
	    		zamow = new JButton("Dodaj do zamowienia");
		    	zamow.setBounds(257, 66, 133, 23);
		    	add(zamow);
	    	}
	    }   
}
