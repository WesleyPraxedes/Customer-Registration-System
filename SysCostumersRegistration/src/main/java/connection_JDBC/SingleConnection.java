package connection_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url ="jdbc:postgresql://localhost:5432/posjava";
	private static String password ="postgres";
	private static String user="postgres";
	private static Connection connectionhh = null;
	
	static {
		connect();
	}
	
	public SingleConnection(){
		connect();
	}

	private static void connect() {
		try{
			
			if (connectionhh == null){
				Class.forName("org.postgresql.Driver");
				connectionhh = DriverManager.getConnection(url, user, password);
				connectionhh.setAutoCommit(false);
				System.out.println("Connected successfully!");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection(){
		return connectionhh;
	}

}