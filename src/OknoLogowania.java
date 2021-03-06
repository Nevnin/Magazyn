import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class OknoLogowania extends JFrame implements ActionListener, KeyListener{
    Label log,login,haslo;
    TextField tflogin;
    JPasswordField tfhaslo;
    JButton zaloguj;
    GridBagConstraints gbc;
    Polaczenie poloczenie;
    
    public OknoLogowania() {
        super("Logowanie");
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(300,100));
        gbc= new GridBagConstraints();
        GridLayout gl =  new GridLayout(3,2);
        setLayout(gl);
        setVisible(true);
		poloczenie = new Polaczenie();
		
        login =  new Label("Login:");
        add(login);
        tflogin= new TextField();
        add(tflogin);

        haslo =  new Label("Haslo:");
        add(haslo);
        tfhaslo= new JPasswordField();
        add(tfhaslo);
        
        add(new Label());
        zaloguj = new JButton("Zaloguj");
        add(zaloguj,gbc);
        nasluchZdarzen();
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                zamykanieOkna(evt);
            }
        });
        pack();
        setLocationRelativeTo(null);
    }   
    public void nasluchZdarzen() {	
    	zaloguj.addActionListener(this);
    	zaloguj.addKeyListener(this);
    	tflogin.addKeyListener(this);
    	tfhaslo.addKeyListener(this);
    }
    
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
    	if (e.getKeyCode()==KeyEvent.VK_ENTER){
    		sprawdzanieUzytkownika();
    	}
		
	}
	
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    public void zamykanieOkna(WindowEvent evt) {
        dispose();
    }
    
    public void actionPerformed(ActionEvent e) {
    	Object logowanie = e.getSource();
    	if(logowanie==zaloguj) {
    		sprawdzanieUzytkownika();
    	}
    }
    public void glowneOkno() {
    	dispose();
        EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                new Magazyn();
            }
        });
    }
    public void oknoLogowania() {
    	dispose();
		EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                new OknoLogowania();
            }
        });
    } 
    public void sprawdzanieUzytkownika(){
		boolean f = false;
		String query="Select * from dostawca";
		poloczenie = new Polaczenie();
		ResultSet rs = poloczenie.sqlSelect(query);
		try {
			String passText = new String(tfhaslo.getPassword());
			while(rs.next()){
				if(tflogin.getText().toString().equals(rs.getString("Login")) && passText.equals(rs.getString("Haslo"))) {
					f = true;
					glowneOkno();
			        break;
				}
			}
			if(f==false) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "�le podane dane. Czy chcesz ponowi� logowanie?","Warning",dialogButton);
				if(dialogResult == JOptionPane.NO_OPTION) {
					dispose();
				}
				else {
					oknoLogowania();
				}
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
