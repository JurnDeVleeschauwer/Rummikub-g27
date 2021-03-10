package domein;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectie {
	/**
	 * Link naar databank met gebuikersnaam en wachtwoord.
	 */
			 public static final String JDBC_URL = "jdbc:mysql://ID344025_g27.db.webhosting.be?serverTimezone=UTC&useLegacyDatetimeCode=false&user=ID344025_g27&password=g27_rummi";

	public static void main(String[] args) {
		String url = "jdbc:mysql://ID344025_g27.db.webhosting.be?serverTimezone=UTC";
		String user = "ID344025_g27";
		String password = "g27_rummi";
				
		 try 
		 {
			 Connection cn = DriverManager.getConnection(url, user, password);
		 }catch(SQLException e) {
			 System.out.println("Oeps! error");
		 }
	}
   
}
