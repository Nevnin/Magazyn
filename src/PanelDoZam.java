import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelDoZam extends JPanel {
	
		JTextField tfTerminRealizacji,tfDataRealizacji;
	    JLabel lTerminRealizacji,lDataRealizacji;
	    JButton zamow,DodajDoZamowienia;
	    	    public PanelDoZam(int numer) {
	        setSize(new Dimension(400, 100));
	        
	    	setLayout(null);
	    	
	    	
	    	if(numer == 0){
	    	JLabel lblTerminRealizacji = new JLabel("Termin Realizacji");
	    	lblTerminRealizacji.setBounds(10, 11, 88, 14);
	    	add(lblTerminRealizacji);
	    	
	    	tfTerminRealizacji = new JTextField();
	    	tfTerminRealizacji.setBounds(108, 8, 86, 20);
	    	tfTerminRealizacji.setToolTipText("Podaj date w formacie: yyyy-mm-dd");
	    	add(tfTerminRealizacji);
	    	tfTerminRealizacji.setColumns(10);
	    	
	    	JLabel lblDataRealizacji = new JLabel("Data Realizacji");
	    	lblDataRealizacji.setBounds(10, 36, 88, 14);
	    	add(lblDataRealizacji);
	    	
	    	tfDataRealizacji = new JTextField();
	    	tfDataRealizacji.setBounds(108, 33, 86, 20);
	    	tfDataRealizacji.setToolTipText("Podaj date w formacie: yyyy-mm-dd");
	    	add(tfDataRealizacji);
	    	tfDataRealizacji.setColumns(10);
	    	zamow = new JButton("Zamow");
	    	zamow.setBounds(301, 61, 89, 23);
	    	add(zamow);
	    	}
	    	else
	    	{
	    		zamow = new JButton("Dodaj do zamowienia");
		    	zamow.setBounds(257, 66, 133, 23);
		    	add(zamow);
	    	}
	    }   
}
