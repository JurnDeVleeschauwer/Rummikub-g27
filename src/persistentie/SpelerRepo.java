package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import domein.Speler;

public class SpelerRepo {
	
	private List<Speler> spelers;
	
	public Speler controleerSpeler(String gebruikersnaam, String wachtwoord) {
		
		Speler speler = null;
		 try 
		 {
			 Connection cn = DriverManager.getConnection(Connectie.JDBC_URL);
			 
			 String sql = "SELECT * FROM ID344025_g27.Speler WHERE naam = '?' and wachtwoord = '?'";
			 
			 Statement statement = cn.createStatement();
		
			 ResultSet result = statement.executeQuery(sql);
			 
			 int count = 0;
			 while (result.next()) {
				 String naam = result.getString(2);
				 String wachtWoord = result.getString(5);
				 count++;
				 if(naam != null)
					 speler = new Speler(naam, wachtWoord);
			 }
			 cn.close();
			 
			 
			 
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }
		 return speler;
	}
}

