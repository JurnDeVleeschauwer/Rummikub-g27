package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.ExceptieSpelerAanmelden;
import i18n.UITextHelper;
import persistentie.SpelerRepo;

public class DomeinController {
	
	private SpelerRepo spelerRepo = new SpelerRepo();
	List<Speler> spelers = new ArrayList<>();
	Speler speler = null;
	private Spel spel;


	/** Haalt het spel op
	 * @return het spel
	 */
	public Spel getSpel() {
		return this.spel;
	}
	
	/** Haalt de speler Repository op
	 * @return de Speler Repository
	 */
	public SpelerRepo getSpelerRepo() {
		return spelerRepo;
	}
	
	/** Controleert of de naam en het wachtwoord van de speler voorkomen, gelijk zijn. Indien aanwezig word hij opgeslagen.
	 * @param gebruikersnaam naam van de speler
	 * @param wachtwoord wachtwoord van de speler
	 * @return true indien naam en wachtwoord voorkomen in database en gelijk zijn.
	 * @throws ExceptieSpelerAanmelden wanneer speler niet bestaat of wachtwoord verkeerd is of al ingelogd is.
	 */
	public boolean controleerSpeler(String gebruikersnaam, String wachtwoord) throws ExceptieSpelerAanmelden {
	     speler = null;
		 this.speler = spelerRepo.controleerSpeler(gebruikersnaam, wachtwoord);
		 
		 if (speler != null) {
		        for(Speler spelerList : this.spelers) {
		        	if(spelerList.getID() == speler.getID()) {
		        		throw new ExceptieSpelerAanmelden(UITextHelper.UIText("Deze.speler.bestaat.niet.of.het.wachtwoord.is.verkeerd"));
		        	}
		        }
			 return true;
		 }else {
			 throw new ExceptieSpelerAanmelden(UITextHelper.UIText("Deze.speler.bestaat.niet.of.het.wachtwoord.is.verkeerd"));
		 }
	}
	
	/** Voegt een speler toe aan de lijst van spelers.
	 */
	public void addSpelerAanLijst() {
		  spelers.add(speler);
	}
	
	/** Haalt alle gebruikersnamen op van de spelers van het huidige spel.
	 * @return een lijst van die gebruikersnamen
	 */
	public List<String> getGebruikersnamen() {
		List<String> gebruikersnamen = new ArrayList<>();
		for(Speler speler : spelers) {
			gebruikersnamen.add(speler.getGebruikersnaam());
		}
		return gebruikersnamen;
	}
	
	/** Initialiseert een nieuw spel
	 */
	public void startSpel() {
		this.spel = new Spel(spelers, this);
	}
	
	/** roept de methode aan om te controleren of de speler aan beurt gewonnen is.
	 * @return aanroepen van de methode om winst te checken en resultaat doorgeven.
	 */
	public boolean checkWinst() {
		return spel.checkWinst();
	}

	/** roept de functie reset aan in spel.
	 */
	public void reset() {
		spel.reset();
	}
	
	/** Stelt het nemen van een steen in op true of false
	 * @param b true of false indien speler een steen moet nemen of niet 
	 */
	public void zetNeemSteen(boolean b) {
		spel.zetNeemSteen(b);
	}

	/** roept methode aan om beurt huidige speler te beïndigen
	 * @return het resultaat van beeindigBeurt in spel.
	 */
	public String beeindigBeurt() {
		return spel.beeindigBeurt();
	}
	
	
	/** Steen aanleggen
	 * @param naam kleur en naam van steen.
	 * @param positie plaats waar steen moet aangelegd worden
	 * @return het resultaat van de functie steenAanleggen in spel.
	 */
	public String steenAanleggen(String naam, String positie) {
		return spel.steenAanleggen(naam, positie);
	}
	
	
	/** Roept methode aan om een rij te splitsen. 
	 * @param Yindex de rij
	 * @param Xindex de kolom 
	 */
	public void rijSplitsen(String Xindex, String Yindex) {
		spel.rijSplitsen(Xindex, Yindex);
	}
	
	/** Roept methode aan om een joker te vervangen door een steen. 
	 * @param naam de naam van de steen die je wil leggen ipv de joker
	 * @param waarde de waarde van de te vervangen joker
	 */
	public void jokerVervangen(int waarde, String naam) {
		spel.jokerVervangen(waarde, naam);
	}
	
	
	/** Roept methode aan om een vooraf gekozen steen naar het werkveld te verplaatsen.
	 * @param xindex de kolom
	 * @param yindex de rij
	 * @return het resultaat van de functie steenNaarWerkVeld van spel
	 */
	public String steenNaarWerkveld(String xindex, String yindex) {
		int Xindex = spel.VanStringEenIntMaken(xindex);
		int Yindex = spel.VanStringEenIntMaken(yindex);
		return spel.steenNaarWerkveld(Xindex, Yindex);
	}
	
	/** Retourneert een lijst van alle spelers die meespelen
	 * @return lijst van spelers.
	 */
	public List<Speler> getSpelers(){
		return spelers;
	}
	
	/** Roept de methode aan die zoekt of er een joker op tafel ligt
	 * @return het resultaat van de functie heeftTafelEenJoker van spel.
	 */
	public boolean heeftTafelEenJoker() {
		return spel.heeftTafelEenJoker();
	}

	/** Geeft gepaste boodschap als speler wint en doet extra controle voor een leeg werkveld.
	 * @return boodschap dat je gewonnen hebt of dat het werkveld nog leeg moet.
	 */
	public String isGedaan() {
		if(spel.checkWinst()) {
			if(spel.getWerkveld().isEmpty()) {
				return "Je bent gewonnen!";
			}
			return "Het werkveld moet ook leeg!";
		}
		return null;
	}
	
	/** Roept de methode aan om de score te berekenen van de spelers
	 * @param gebruikersnaamWinnaar naam van de winnaar
	 * @return een lijst met spelers en hun score van het huidig spel.
	 */
	public List<Speler> berekenScore(String gebruikersnaamWinnaar){
		return spel.berekenScore(gebruikersnaamWinnaar);
	}
	
	/** Geeft de gebruikersnaam van de speler aan zet.
	 * @return roept methode aan om speler aan zet te krijgen en vraagt daarvan dan de gebruikersnaam op.
	 */
	public String getSpelerAanZetGebruikersnaam() {
		return spel.getSpelerAanZet().getGebruikersnaam();
	}
	
	/** Vervangt huidige speler op plaats i
	 * @oaram i de locatie van de speler in de lijst
	 */
	public void replaceSpelerInList(int i) {
		this.spelers.set(i, this.speler);
	}
	
	public List<RummiSteen> getStenenInBezit(){
		return this.spel.getSpelerAanZet().getStenenInBezit();
	}
	
	public List<RummiSteen> getWerkveld(){
		return this.spel.getWerkveld();
	}
	
	public List<ArrayList<RummiSteen>> getStenenOpTafel(){
		return this.spel.getTijdelijkeTafel().getStenenOpTafel();
	}
}
