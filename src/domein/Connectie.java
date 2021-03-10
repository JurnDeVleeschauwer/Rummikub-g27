package domein;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connectie {
	/**
	 * Link naar databank met gebuikersnaam en wachtwoord.
	 */
			 public static final String JDBC_URL = "jdbc:mysql://ID344025_g27.db.webhosting.be?serverTimezone=UTC&useLegacyDatetimeCode=false&user=ID344025_g27&password=g27_rummi";

	public static void main(String[] args) {
			
		 try 
		 {
			 Connection cn = DriverManager.getConnection(JDBC_URL);
			 
			 String sql = "insert into Speler(naam, wachtwoord) values(?, ?)";
			 PreparedStatement stat = cn.prepareStatement(sql);
			 stat.setString(1, "Piet");
			 stat.setString(2, "ProbeerUitEclipse");
			 
			 int rows = stat.executeUpdate();
			// if (rows > 0 )
			//	 System.out.println("Er is iets gebeurt");
			// stat.close();
			// cn.close();
			 
			 
		 }catch(SQLException e) {
			 System.out.println("Geen verbinding");
		 }
	}
   
}
