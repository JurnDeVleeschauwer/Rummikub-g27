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


	/** Start een nieuwe instantie van spel
	 * @return roept de spel instantie aan
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
	
	/** Controleert of de naam en het wachtwoord van de speler voorkomen, gelijk zijn.
	 * @return true indien naam en wachtwoord voorkomen in database en gelijk zijn.
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
	
	/** Toont een overzicht van de scores van de huidige spelers
	 */
	public String toonOverzicht() {
		String overzicht = String.format("Speler\tScore%n");
		for (Speler speler : spelers) {
			overzicht += String.format("%s\t%d%n",speler.getGebruikersnaam(),speler.getScore());
		}
		return overzicht;
	}
	
	/** Toont de stenen op de tafel, het werkveld en van de speler aan zet
	 * @return een string met daarin de stenen van tafel, werkveld en speler aan zet
	 */
	public String toonStenen() {
		return String.format("VTafel:%n%s%nTTafel:%n%sWerkveld: %s%n%s:%n%s", spel.toonStenenTafel(1), spel.toonStenenTafel(50), spel.toonWerkveld(),spel.getSpelerAanZet().getGebruikersnaam(), spel.toonStenenSpeler());
	}
	
	/** roept de methode aan om te controleren of de speler aan beurt gewonnen is.
	 * @return aanroepen van de methode om winst te checken.*/
	public boolean checkWinst() {
		return spel.checkWinst();
	}

	/** roept methode aan om de tijdelijke tafel te legen en zet het nemen van een steen op true.
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
	 */
	public String beeindigBeurt() {
		return spel.beeindigBeurt();
	}
	
	
	/** Steen aanleggen in gui
	 */
	public String steenAanleggen(String naam, String positie) {
		return spel.steenAanleggen(naam, positie);
	}
	
	
	/** Roept methode aan om een rij te splitsen. 
	 * @param Yindex 
	 * @param Xindex 
	 */
	public void rijSplitsen(String Xindex, String Yindex) {
		spel.rijSplitsen(Xindex, Yindex);
	}
	
	/** Roept methode aan om een joker te vervangen door een steen. 
	 * @param naam 
	 * @param waarde 
	 */
	public void jokerVervangen(int waarde, String naam) {
		spel.jokerVervangen(waarde, naam);
	}
	
	
	/** Roept methode aan om een vooraf gekozen steen naar het werkveld te verplaatsen.
	 * @param naam kleur en nummer van de steen. 
	 */
	public String steenNaarWerkveld(String xindex, String yindex) {
		int Xindex = spel.VanStringEenIntMaken(xindex);
		int Yindex = spel.VanStringEenIntMaken(yindex);
		return spel.steenNaarWerkveld(Xindex, Yindex);
	}
	
	/** Retourneert een lijst van alle spelers die meespelen
	 * @return lijst van spelers.*/
	public List<Speler> getSpelers(){
		return spelers;
	}
	
	public boolean heeftTafelEenJoker() {
		return spel.heeftTafelEenJoker();
	}

	public String isGedaan() {
		if(spel.checkWinst()) {
			if(spel.getWerkveld().isEmpty()) {
				return "Je bent gewonnen!";
			}
			return "Het werkveld moet ook leeg!";
		}
		return null;
	}
	
	public List<Speler> berekenScore(String gebruikersnaamWinaar){
		return spel.berekenScore(gebruikersnaamWinaar);
	}
	
	public String getSpelerAanZetGebruikersnaam() {
		return spel.getSpelerAanZet().getGebruikersnaam();
	}
	
}
