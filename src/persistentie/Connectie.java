package persistentie;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connectie {
	
	/**
	 * Databank
	 */
	public static final String DB = "ID344025_g27";

	/**
	 * Link naar databank met gebuikersnaam en wachtwoord.
	 */
    public static final String JDBC_URL = "jdbc:mysql://" + DB + ".db.webhosting.be/" + DB + "?serverTimezone=UTC&useLegacyDatetimeCode=false&user=" + DB + "&password=g27_rummi";
    
}
