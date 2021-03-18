package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import domein.Speler;

public class SpelerRepo {
	
	private SpelerMapper spelerMapper = new SpelerMapper();
	
	public Speler controleerSpeler(String gebruikersnaam, String wachtwoord) {
		return spelerMapper.geefSpeler(gebruikersnaam, wachtwoord);
	}
		
}

