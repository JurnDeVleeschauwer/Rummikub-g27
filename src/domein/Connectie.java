package domein;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connectie {
	/**
	 * Link naar databank met gebuikersnaam en wachtwoord.
	 */
			 public static final String JDBC_URL = "jdbc:mysql://ID344025_g27.db.webhosting.be?serverTimezone=UTC&useLegacyDatetimeCode=false&user=ID344025_g27&password=g27_rummi";

	public static void main(String[] args) {
		String url = "jdbc:mysql://ID344025_g27.db.webhosting.be?serverTimezone=UTC";
		String user = "ID344025_g27";
		String password ="g27_rummi";
		
		
		 try 
		 {
			 Connection cn = DriverManager.getConnection(url, user, password);
			 
			 String sql = "SELECT * FROM ID344025_g27.Speler";
			 
			 Statement statement = cn.createStatement();
		
			 ResultSet result = statement.executeQuery(sql);
			 
			 int count = 0;
			 while (result.next()) {
				 String naam = result.getString(2);
				 String wachtwoord = result.getString(5);
				 count++;
				 System.out.printf("naam: %s en wachtwoord: %s%n", naam, wachtwoord);
			 }
			 cn.close();
			 
//			 String sql = "insert into ID344025_g27.Speler(naam, voornaam, wachtwoord, emailadres, geboortedatum, adminrechten, krediet) values(?, ?, ?, ?, ?, ?, ?)";
//			 PreparedStatement stat = cn.prepareStatement(sql);
//			 stat.setString(1, "Pieters");
//			 stat.setString(2, "Piet");
//			 stat.setString(3, "ProbeerUitEclipse");
//			 stat.setString(4, "Piet.Pieters@gmail.com");
//			 stat.setString(5, "2002-5-7");
//			 stat.setString(6, "0");
//			 stat.setString(7, "0");
//			 
//			
//			 int rows = stat.executeUpdate();		// Hier klopt iets niet want deze regel zorgt ervoor dat er geen connectie meer is.
//			 if (rows > 0 )
//				 System.out.println("Er is iets gebeurt");
//			 stat.close();
//			 cn.close();
			 
			 
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }
	}
   
}
