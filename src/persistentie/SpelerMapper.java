package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domein.Speler;

public class SpelerMapper {
	public Speler geefSpeler(String gebruikersnaam, String wachtwoord) {
		Speler speler = null;
		String queryStatement = "SELECT * FROM " + Connectie.DB + ".Speler WHERE naam = ? and wachtwoord = ?";
		try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				PreparedStatement query = conn.prepareStatement(queryStatement);) {

			query.setString(1, gebruikersnaam);
			query.setString(2, wachtwoord);
			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("spelerID");
				String naam = rs.getString("naam");
				String wachtwoord1 = rs.getString("wachtwoord");
				int score = rs.getInt("score");


				speler = new Speler(id, naam, wachtwoord1, score);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		return speler;
	}
	
	public void updateScore(int Score, int id) {
		try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				PreparedStatement query = conn.prepareStatement("UPDATE Speler SET score = ? WHERE spelerID = ?")) {
			query.setInt(1, Score);
			query.setInt(2, id);
			query.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
}
