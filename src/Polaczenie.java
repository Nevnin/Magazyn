import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Polaczenie{

//	private final static String DBURL="jdbc:mysql://192.168.137.1/pz";
	private final static String DBURL="jdbc:mysql://localhost/pz";
//	private final static String DBUSER = "user2";
	private final static String DBUSER = "root";
//	private final static String DBPASS = "123456";
	private final static String DBPASS = "";
	private final static String DBDRIVER = "com.mysql.jdbc.Driver";
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	public Connection Connect() 
	{
//		 String url="jdbc:mysql://localhost:3306/pracainzynierska?useUnicode=yes&characterEncoding=UTF-8";
//		 String user = "root";
//		 String password = "";
		 
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
			return conn;
		} catch (ClassNotFoundException | SQLException e) {
                     // TODO Auto-generated catch block

		}
            // TODO Auto-generated catch block
		 return null;
	}
//	public Polaczenie(){
//		try {
//			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
//			stmt = conn.createStatement();
//		} catch (SQLException e) {
////			System.out.println("SQLException:" + e.getMessage());
////			System.out.println("SQLSTATE:" + e.getSQLState());
////			System.out.println("VendorError:"+e.getErrorCode());
//		}
//		
//	}
	public ResultSet sqlSelect(String query){
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return rs;
	}
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;

	}
//	public String[][] sqlSelectTest(String query, Object[] obj){
//		Connection dbConnection = null;
//		PreparedStatement preparedStatement = null;
//		String[][] tabPom = null;
//		try {
//			dbConnection = getDBConnection();
//			preparedStatement = dbConnection.prepareStatement(query);
//			preparedStatement.setObject(1, obj[0]);
//			ResultSet rs = preparedStatement.executeQuery();
//			tabPom = new String[rs.getRow()][rs.getMetaData().getColumnCount()];
//			while (rs.next()) {
//				for(int i=0; i<tabPom.length; i++){
//					for(int j=0; j<tabPom[0].length; j++){
//						tabPom[i][j] = rs.getString(j);
//					}
//				}
////				String userid = rs.getString("USER_ID");
////				String username = rs.getString("USERNAME");
////				System.out.println("userid : " + userid);
////				System.out.println("username : " + username);
//			}
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		} finally {
//			if (preparedStatement != null) {
//				try {
//					preparedStatement.close();
//				} catch (SQLException e) {
//					System.out.println(e.getMessage());
//				}
//			}
//			if (dbConnection != null) {
//				try {
//					dbConnection.close();
//				} catch (SQLException e) {
//					System.out.println(e.getMessage());
//				}
//			}
//		}
//		return tabPom;
//	}
	public void close(){
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
}
