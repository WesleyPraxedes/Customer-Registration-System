package connection_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url ="jdbc:postgresql://localhost:5432/SysCostumersRegistrationDb";
	private static String password ="postgres";
	private static String user="postgres";
	private static Connection connection = null;
	
	static {
		toConnect();
	}
	
	public SingleConnection(){
		toConnect();
	}

	private static void toConnect() {
		try{
			
			if (connection == null){
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				//System.out.println("Connected successfully!");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection(){
		return connection;
	}

}