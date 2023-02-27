package connection_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
	
	private static String URL ="jdbc:postgresql://localhost:5432/posjava";
	private static String USER="postgres";
	private static String PASSWORD ="postgres";
	
	
	public static Connection getConnection(){
		 try {
	            Class.forName("org.postgresql.Driver");
	            return DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

}