package domein;

import java.util.ArrayList;
import java.util.List;

public class Speler {
	private int id;
	private String gebruikersnaam;
	private String wachtwoord;
	private int score;
	private boolean isSpelerAanDeBeurt;
	private List<RummiSteen> stenenInBezit;
	private boolean isEersteBeurt;
	private boolean neemSteen;
	
	/** Initialiseert een speler object 
	 * @param id 
	 * @param gebruikersnaam naam van de speler in het spel
	 * @param wachtwoord van de speler
	 * @score totaal score van de speler doorheen al de spellen
	 */
	public Speler(int id, String gebruikersnaam, String wachtwoord , int score) {
		this.id = id;
		setGebruikersnaam(gebruikersnaam);
		setWachtwoord(wachtwoord);
		setScore(score);
		setIsEersteBeurt(true);
		setNeemSteen(true);
		stenenInBezit = new ArrayList<>();
	}
	
	/** Haalt wachtwoord van de speler op
	 * @return wachtwoord speler
	 */
	public String getWachtwoord() {
		return wachtwoord;
	}
	
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	
	/** Haalt op of speler een steen moet nemen of niet
	 */
	public boolean getNeemSteen() {
		return neemSteen;
	}
	/** instellen of de speler een steen moet nemen of niet
	 * @param neemSteen boolean die bepaalt of je wel of niet een steen moet nemen
	 */ 
	public void setNeemSteen(boolean neemSteen) {
		this.neemSteen = neemSteen;
	}
	
	/** instellen of het de eerste beurt van de speler is of niet
	 * @param b boolean die bepaalt of het de eerste beurt is of niet
	 */ 
	public void setIsEersteBeurt(boolean b) {
		this.isEersteBeurt = b;
		
	/** Haalt op of het de eerste beurt van de speler is
	 * @return boolean die aantoont of het de eerste beurt is of niet
	 */
	}
	public boolean getIsEersteBeurt() {
		return isEersteBeurt;
	}
	
	/** Haalt de gebruikersnaam van de speler op
	 * @return gebruikersnaam speler
	 */ 
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	
	/** Stelt gebruikersnaam van speler in
	 * @param gebruikersnaam naam van de speler in het spel
	 */
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	
	/** Haalt de score op van de speler
	 */
	public int getScore() {
		return score;
	}
	
	/** stelt de score in van de speler
	 * @param score huidige score van de speler
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/** Geeft boolean terug of speler aan de beurt is 
	 * @return boolean of speler aan de beurt is
	 */
	public boolean isSpelerAanDeBeurt() {
		return isSpelerAanDeBeurt;
	}
	
	/** Stelt in of speler aan de beurt is 
	 * @param isSpelerAanDeBeurt boolean die bepaalt of speler aan de beurt is
	 */ 
	public void setSpelerAanDeBeurt(boolean isSpelerAanDeBeurt) {
		this.isSpelerAanDeBeurt = isSpelerAanDeBeurt;
	}
	
	/** Geeft een lijst van alle stenen in het bezit van de speler
	 * @return lijst met stenen van speler
	 */
	public List<RummiSteen> getStenenInBezit() {
		return stenenInBezit;
	}
	
	public void speelBeurt() {
		
	}
	
	/** Speler krijgt een extra steen
	 * @param steen random steen uit de pot word gegeven aan de speler
	 *  */
	public void krijgtSteen(RummiSteen steen) {
		stenenInBezit.add(steen);
	}
	
	/** verwijdert een steen uit de hand van de speler
	 * @param steen steen in kwestie die verwijdert moet worden
	 */ 
	public void verwijderSteen(RummiSteen steen) {
		int index = stenenInBezit.indexOf(steen);
		stenenInBezit.remove(index);
	}
	
	/** Toont de stenen van de speler
	 * @return toont de stenen in een string 
	 */ 
	public String toonStenen() {
		String returnString = "";
			for (RummiSteen steen: stenenInBezit) {
				returnString +=  String.format("%s ",steen.toString());
			}
		return returnString;
	}
	
	/** Retourneert een steen
	 * @param naam  naam die word gezocht in de hand van de speler
	 */
	public RummiSteen geefSteenMetNaam(String naam) {
		for(RummiSteen s : this.stenenInBezit) {
			if(s.getNaam().equals(naam)) return s;
		}
		return null;
	}
	
	
}
