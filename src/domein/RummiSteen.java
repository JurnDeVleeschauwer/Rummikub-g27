package domein;

public class RummiSteen {
	private final int waarde;
	private final String kleur;
	private final String naam;
	
	/** Zet waarden en kleur van steen om in een string volgens die variabelen.
	 * @param waarde het getal van de steen
	 * @param kleur van de steen waarbij groen = joker
	 * */
	public RummiSteen() {
		this.waarde = 0;
		this.kleur = "";
		this.naam = "";
		
	}
	/** Legt voor elke steen de kleur en waarde vast en vervangt de kleur groen door de joker.
	 * @param waarde het getal van de steen
	 * @param kleur de kleur van de steen */
	public RummiSteen(int waarde, String kleur) {
		this.waarde=waarde;
		this.kleur=kleur;
		if (kleur=="Groen")
			this.naam="Joker";
		else
			this.naam=String.format("%s%d",kleur,waarde);
	}
	
	/** Haalt getal van de steen
	 * 	@return getal steen.
	 */
	public int getWaarde() {
		return waarde;
	}
	
	/** Haalt kleur van de steen
	 * @return kleur steen.
	 */
	public String getKleur() {
		return kleur;
	}
	
	/** Haalt de naam op van de steen
	 * @return naam steen.
	 */
	public String getNaam() {
		return naam;
	}
	
	@Override
	/** Geeft elke kleur of joker een korte notatie voor bij gebruik van kleurblindheid in gui.
	 * @return verkorte notatie 
	 */
	public String toString() {
		String returnString = getWaarde() + "\n";
		switch (kleur) {
		case "Zwart":
			returnString += "Z";
			break;
		case "Geel":
			returnString += "G";
			break;
		case "Rood":
			returnString += "R";
			break;
		case "Blauw":
			returnString += "B";
			break;
		case "Groen":
			returnString = "J";
			break;
		}
		return returnString;
	}
	
	
}
