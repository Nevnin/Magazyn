import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class OknoLogowania extends JFrame implements ActionListener {
    Label log,login,haslo;
    TextField tflogin;
    JPasswordField tfhaslo;
    Button zaloguj;
    GridBagConstraints gbc;
    
    private final static String DBURL="jdbc:mysql://localhost:3306/magazyn";
	private final static String DBUSER = "root";
	private final static String DBPASS = "";
	private final static String DBDRIVER = "com.mysql.jdbc.Driver";
	
	private Connection connection;
	private Statement statement;
    
    public OknoLogowania() {
    	
        super("Logowanie");
        System.out.println("blsafvkdfkjsdfksfds");
        String query="Select * from uzytkownik";
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(300,100));
        gbc= new GridBagConstraints();
        GridLayout gl =  new GridLayout(3,2);
        setLayout(gl);
        setVisible(true);
        
        try {
			Class.forName(DBDRIVER);
			connection = (Connection) DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				System.out.println(rs.getString(2));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Problem ze sterownikiem ");
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			System.out.println("SQLSTATE:" + e.getSQLState());
			System.out.println("VendorError:"+e.getErrorCode());
		}

        login =  new Label("Login:");
        add(login);
        tflogin= new TextField();
        add(tflogin);

        haslo =  new Label("Haslo:");
        add(haslo);
        tfhaslo= new JPasswordField();
        add(tfhaslo);
        
        add(new Label());
        zaloguj = new Button("Zaloguj");
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
    }
    public void zamykanieOkna(WindowEvent evt) {
        dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	Object logowanie = e.getSource();
    	if(logowanie==zaloguj) {
    		boolean f = false;
    		String query="Select * from uzytkownik";
    		ResultSet rs = null;
			try {
				rs = statement.executeQuery(query);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		try {
    			String passText = new String(tfhaslo.getPassword());
				while(rs.next()){
					if(tflogin.getText().toString().equals(rs.getString("Login")) && passText.equals(rs.getString("Haslo"))) {
						f = true;
						dispose();
				        EventQueue.invokeLater(new Runnable() {
				            @Override
				            public void run() {
				                new Magazyn();
				            }
				            
				        });
				        break;
					}
				}
				if(f==false) {
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null, "èle podane dane. Czy chcesz ponowiÊ logowanie?","Warning",dialogButton);
					if(dialogResult == JOptionPane.NO_OPTION) {
						dispose();
					}
					else {
						dispose();
						EventQueue.invokeLater(new Runnable()
				        {
				            @Override
				            public void run()
				            {
				                new OknoLogowania();
				            }
				            
				        });
					}
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    }
}
