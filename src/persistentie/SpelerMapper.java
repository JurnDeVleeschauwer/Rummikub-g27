package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domein.Speler;

public class SpelerMapper {
	public Speler geefSpeler(String gebruikersnaam, String wachtwoord) {
	Speler speler = null;
	 try 
	 {
		 Connection cn = DriverManager.getConnection(Connectie.JDBC_URL);
		 
		// String sql = "SELECT * FROM ID344025_g27.Speler WHERE naam = '?' and wachtwoord = '?'";
		 
		 PreparedStatement query = cn.prepareStatement("SELECT * FROM ID344025_g27.Speler WHERE naam = ? and wachtwoord = ?");
            query.setString(1, gebruikersnaam);
            query.setString(2, wachtwoord);
	 Statement statement = cn.createStatement();

	 ResultSet result = query.executeQuery();
		 
		 
		 //Statement statement = cn.createStatement();
	
		// ResultSet result = statement.executeQuery(sql);
		 
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
