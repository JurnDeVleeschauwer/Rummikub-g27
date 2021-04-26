package domein;

public class RummiSteen {
	private final int waarde;
	private final String kleur;
	private final String naam;
	
	/** Zet waarden en kleur van steen om in een string volgens die variabelen.
	 * @param waarde het getal van de steen
	 * @param kleur van de steen waarbij groen = joker
	 * */
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
	public String toString() {
		return naam;
	}
	
	
}
