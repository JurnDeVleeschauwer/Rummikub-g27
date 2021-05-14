package persistentie;

import domein.Speler;

public class SpelerRepo {
	
	private SpelerMapper spelerMapper = new SpelerMapper();
	
	/** Vraagt een speler op van de databank
	 * @param gebruikersnaam naam van de speler
	 * @param wachtwoord wachtwoord van de speler
	 * @return de uitkomst van de functie geefSpeler in spelerMapper
	 */
	public Speler controleerSpeler(String gebruikersnaam, String wachtwoord) {
		return spelerMapper.geefSpeler(gebruikersnaam, wachtwoord);
	}
	
	/**Update de score in de databank
	 * @param score de in te geven score
	 * @param id het id van de speler 
	 */
	public void updateScore(int score, int id) {
		spelerMapper.updateScore(score, id);
	}
	
}

