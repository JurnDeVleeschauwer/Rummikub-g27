package domein;

import java.util.ArrayList;
//import org.apache.commons.lang.SerializationUtils;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import i18n.UITextHelper;

public class Spel {
	Scanner sc = new Scanner(System.in);
	private final List<Speler> spelers;
	private final Pot pot;
	private Speler spelerAanZet;
	private Tafel vasteTafel;
	private Tafel tijdelijkeTafel;
	private List<RummiSteen> werkveld;

	/** Initialiseert alle benodigdheden voor een spel.
	 * @param spelers een lijst van de spelers die meedoen aan het spel
	 */
	public Spel(List<Speler> spelers) {
		this.pot = new Pot();
		this.vasteTafel = new Tafel();
		this.tijdelijkeTafel = new Tafel();
		this.spelers = spelers;
		this.werkveld = new ArrayList<>();
		Collections.shuffle(spelers);
		geefEerste14Stenen();
		setSpelerAanZet(spelers.get(0));
		resetTijdelijkeTafel();
		
//		for(int i=0;i<3;i++) {
//			steenOpTafelLeggen(this.spelerAanZet.getStenenInBezit().get(i), i);
////			steenNaarWerkveld(this.spelerAanZet.getStenenInBezit().get(i+7));
//		}
		
	}
	
	/** Haalt lijst van aangemelde spelers op
	 * @return lijst van spelers.
	 */
	public List<Speler> getSpelers() {
		return spelers;
	}
	
	/** Haalt een werkveld op waar stenen vanuit de tafel tijdelijk van tafel kunnen genomen worden en in geplaatst worden.
	 * @return geeft een leeg werkveld terug
	 */
	public List<RummiSteen> getWerkveld(){
		return this.werkveld;
	}
	
	/** Haalt de pot op
	 * @return pot voor in het huidig spel.
	 */
	public Pot getPot() {
		return pot;
	}
	
	/** Haalt speler die aan de beurt is.
	 * @return Speler die aan de beurt is.
	 */
	public Speler getSpelerAanZet() {
		return spelerAanZet;
	}
	
	/** Haalt een kopie op van de vaste tafel
	 * @return de kopie
	 */
	public Tafel getTijdelijkeTafel() {
		return tijdelijkeTafel;
	}
	
	/** Stelt in wat er in de tijdelijke tafel staat
	 * @param tijdelijkeTafel de inhoud van de tijdelijke tafel */
	public void setTijdelijkeTafel(Tafel tijdelijkeTafel) {
		this.tijdelijkeTafel = tijdelijkeTafel;
	}
	
	/** Stelt een vaste speeltafel in voor het gehele spel
	 * @return de speeltafel
	 */
	public Tafel getVasteTafel() {
		return vasteTafel;
	}
	
	/** Voegt de veranderingen van de tijdelijke tafel door naar de vaste tafel
	 * @param tijdelijkeTafel de aanpassingen die aan de vaste tafel moeten gedaan worden
	 */ 
	public void setVasteTafel(Tafel tijdelijkeTafel) {
		this.vasteTafel = tijdelijkeTafel;
	}
	
	/** Verwijdert alle stenen gelegd door de speler op de tijdelijke tafel
	 */
	public void resetTijdelijkeTafel() {
		//this.tijdelijkeTafel = SerializationUtils.clone(this.vasteTafel)
		this.tijdelijkeTafel.reset(this.vasteTafel.getStenenOpTafel(), this.spelerAanZet);
		
	}
	/** Haalt voor elke speler 14 stenen uit de pot
	 */
	public void geefEerste14Stenen() {
		for (Speler speler : spelers) {
			for (int i=0;i<14;i++) {
				speler.krijgtSteen(steenUitPotHalen());
			}
		}
	}
	
	/** Verwijdert een steen uit de pot.
	 * @return aanroeping van methode om eerste steen uit de pot te verwijderen en die aan de speler te geven.
	 */
	private RummiSteen steenUitPotHalen() {
		return pot.verwijderSteen();
	}
	
	/** Stelt een bepaalde speler aan zet
	 * @param spelerAanZet gekozen speler aan wie het de beurt is.
	 */
	private void setSpelerAanZet(Speler spelerAanZet) {
		this.spelerAanZet = spelerAanZet;
	}
	
	/** Toont de stenen van de speler aan zet.
	 * @return roept methode aan om stenen van een speler te geven.
	 */
	public String toonStenenSpeler() {
		return spelerAanZet.toonStenen();
	}
	
	/** Toont het werkveld als een string
	 * @return het werkveld
	 */
	public String toonWerkveld() {
		String returnString = "";
		for (RummiSteen steen: this.werkveld) {
			returnString +=  String.format("%s ",steen.toString());
		}
		return returnString;
	
	}
	
//	public void testWinst() {
//		boolean test=true;
//		Speler winnaar = null;
//		do {
//			for (Speler speler : spelers) {
//			
//				for (int i=0;i<(Math.random()*(speler.getStenenInBezit().size()+1));i++) {
//					speler.verwijderSteen();
//				}
//				if (speler.getStenenInBezit().isEmpty()) {
//					System.out.println(speler.getGebruikersnaam());
//					winnaar = speler;
//					test=false;
//					break;
//				}
//				toonStenenSpeler();
//			}
//		} while (test);
//		berekenScore(winnaar);
//		for (Speler speler : spelers) {
//			System.out.printf("%s heeft score van %d%n",speler.getGebruikersnaam(),speler.getScore());
//		}
//	}
	
	/** 
	 * Berekent de scores van alle spelers
	 */
	public void berekenScore(Speler winnaar) {
		int winscore = 0;
		for (Speler speler : spelers) {
			int score = 0;
			for (RummiSteen steen : speler.getStenenInBezit()) {
				score -= steen.getWaarde();
				winscore += steen.getWaarde();
			}
			speler.setScore(score);
		}
		winnaar.setScore(winscore);
	}
	
	/** 
	 * Kijkt of de speler aan zet gewonnen is of niet
	 * */
	public boolean checkWinst() {
		return spelerAanZet.getStenenInBezit().isEmpty();
	}

	/** Toont de stenen op tafel
	 * @param tafel parameter die beslist of vaste tafel of tijdelijke tafel word getoond
	 * @return de stenen op de tijdelijke tafel of vaste tafel.
	 */
	public String toonStenenTafel(int tafel) {
		if (tafel<10) {
			return vasteTafel.toonStenen();
		}
		else
			return tijdelijkeTafel.toonStenen();
	}
	
	/** Stelt in of de speler een steen moet nemen of niet
	 * @param b true of false of de speler een steen moet nemen of niet
	 */ 
	public void zetNeemSteen(boolean b) {
		spelerAanZet.setNeemSteen(b);
	}
	
	/**
	 * Beëindigt beurt van speler die momenteel aan de beurt is
	 */ 
	public void beeindigBeurt() {
		if (controleerTafel()) {
			if (spelerAanZet.getNeemSteen())
				spelerAanZet.krijgtSteen(steenUitPotHalen());
			bepaalSpelerAanZet();
			this.vasteTafel.reset(this.tijdelijkeTafel.getStenenOpTafel(), this.spelerAanZet);
		}
		else {
			
		}
	}
	
	/** Controleert of de tijdelijke tafel klopt 
	 *  @return roept methode aan om de tafel te controleren
	 */
	private boolean controleerTafel() {
		return true;//tijdelijkeTafel.controleerTafel();
	}
	
	/** 
	 * Bepaalt welke speler aan beurt is
	 */
	public void bepaalSpelerAanZet() {
		setSpelerAanZet(spelers.get((spelers.indexOf(spelerAanZet)+1)%spelers.size()));
	}
	
	/** 
	 * Legt een gekozen steen op tafel
	 * @param steen gekozen steen
	 * @param rij waar de speler de steen wil leggen op tafel
	 */
	public void steenOpTafelLeggen(RummiSteen steen, int rij, int kolom) {
		this.tijdelijkeTafel.legSteenOpTafel(steen, rij, kolom);
	}
	
	
	
	/** Geeft een steen terug met een bepaalde naam uit het werkveld
	 * @param naam de kleur en waarde van de steen
	 * @return de naam van de steen indien die in het werkveld ligt
	 */
	public RummiSteen geefSteenMetNaam(String naam) {
		for(RummiSteen s : this.werkveld) {
			if(s.getNaam().equals(naam)) return s;
		}
		return null;
	}
	
	
	/** Legt een steen aan op een specifieke plaats
	 * @param naam kleur en waarde van de steen
	 * @param positie rij en kolom waar steen moet komen
	 * @return wanneer je deze steen niet hebt 
	 */
	public String steenAanleggen(String naam, String positie) {
		String[] str = positie.split(",");
		int rij=0;
		int kolom=0;
		for(int i = 0; i <this.tijdelijkeTafel.getStenenOpTafel().size()+this.tijdelijkeTafel.getStenenOpTafel().get(1).size() ; i++) {
			String s = "";
			s+=i;
			if (str[0].equals(s)) {
				rij = i;
			}
			if (str[1].equals(s)) {
				kolom = i;
			}
		}
		
		
		boolean vanWerkveld = true;
		RummiSteen steen = this.geefSteenMetNaam(naam);
		if(steen==null) {
			steen = this.spelerAanZet.geefSteenMetNaam(naam);
			vanWerkveld = false;
		}
		
		if(steen != null) {
			if(vanWerkveld) {
				this.werkveld.remove(steen);
				this.steenOpTafelLeggen(steen, rij, kolom);
			}else {
				this.spelerAanZet.verwijderSteen(steen);
				this.steenOpTafelLeggen(steen, rij, kolom);
				this.zetNeemSteen(false);
			}
		}else {
			return UITextHelper.UIText("Deze.steen.heb.je.niet.in.je.bezit");
		}
		return null;
	}
	

	
	/** Neemt een steen van de tafel en verplaatst deze naar het werkveld
	 * @param naam kleur en waarde van de te verplaatsen steen
	 */
	public void steenNaarWerkveld(String naam) {	
		RummiSteen steen = this.tijdelijkeTafel.geefSteenMetNaam(naam);
		if(steen != null) {
			this.tijdelijkeTafel.verwijderSteen(steen);
			this.werkveld.add(steen);
		} else System.out.printf("%s%n", UITextHelper.UIText("Deze.steen.ligt.niet.op.tafel"));
	}
	
	/** 
	 * Vervangt de joker door een door de speler gekozen steen
	 */
	public void jokerVervangen() {
		System.out.printf("%s%n", UITextHelper.UIText("Op.welke.rij.ligt.de.joker?"));
		int rij = sc.nextInt();
		int aantalJokers = 0;
		for(RummiSteen steen : this.tijdelijkeTafel.getStenenOpTafel().get(rij)) {
			if(steen.getKleur().equals("Groen")) aantalJokers++;
		}
		int hoeveelste = 0;
		if(aantalJokers<1) 
			System.out.printf("%s%n", UITextHelper.UIText("Er.ligt.geen.Joker.in.deze.rij"));
		else if(aantalJokers > 1) {
			System.out.printf("%s%n", UITextHelper.UIText("De.hoeveelste.Joker.in.deze.rij.wil.je.vervangen?"));
			hoeveelste = sc.nextInt();
			
		}
		if(aantalJokers == 1) 
			hoeveelste = 1;
		if(!(aantalJokers<1)) {
			System.out.printf("%s%n", UITextHelper.UIText("Geef.de.naam.van.de.steen.die.je.wil.leggen"));
			String naam = sc.next();
			
			boolean vanWerkveld = true;
			RummiSteen steenOmTeLeggen = this.geefSteenMetNaam(naam);
			if(steenOmTeLeggen==null) {
				steenOmTeLeggen = this.spelerAanZet.geefSteenMetNaam(naam);
				vanWerkveld = false;
			}
			if(steenOmTeLeggen != null) {
				if(vanWerkveld) {
					this.werkveld.remove(steenOmTeLeggen);
				}else {
					this.spelerAanZet.verwijderSteen(steenOmTeLeggen);
					this.zetNeemSteen(false);
				}
				int hoeveelsteJokerIsHet = 0;
				List<RummiSteen> nieuweRij = new ArrayList();
				for (RummiSteen rummisteen : this.tijdelijkeTafel.getStenenOpTafel().get(rij)) {
					if(rummisteen.getKleur().equals("Groen")) {
						hoeveelsteJokerIsHet++;
						if(hoeveelste == hoeveelsteJokerIsHet) {
							
							nieuweRij.add(steenOmTeLeggen);
							this.werkveld.add(rummisteen);
						}else {
							nieuweRij.add(rummisteen);
						}
					}	
					else
						nieuweRij.add(rummisteen);
				}
				this.tijdelijkeTafel.getStenenOpTafel().get(rij).clear();
					
				for(RummiSteen rummisteen: nieuweRij) {
					this.tijdelijkeTafel.getStenenOpTafel().get(rij).add(rummisteen);
				}
				
			}
			else System.out.printf("%s%n", UITextHelper.UIText("Deze.steen.heb.je.niet.in.je.bezit"));
		}
		
	}
	
	/**
	 * Vervangt een door de speler gekozen steen door een joker
	 */
	public void SteenVervangenDoorJoker() {
		RummiSteen joker = this.spelerAanZet.geefSteenMetNaam("Joker");
		if(joker!=null) {
			System.out.printf("%s%n", UITextHelper.UIText("Geef.de.naam.van.de.steen.die.je.wil.vervangen"));
			String naam = sc.next();
			RummiSteen s = this.tijdelijkeTafel.geefSteenMetNaam(naam);
			
			if (s==null) {
				System.out.printf("%s%n", UITextHelper.UIText("Deze.steen.ligt.niet.op.tafel"));
			}
			else {
				List<RummiSteen> rij = new ArrayList();
				for (List<RummiSteen> r : this.tijdelijkeTafel.getStenenOpTafel()) {
					if (r.contains(s)) {
						for (RummiSteen steen : r) {  
							if(steen != s)
								rij.add(steen);
							else
								rij.add(joker);
						}
						r.clear();
					}
					for(RummiSteen rummisteen: rij) {
					r.add(rummisteen);
				}
				rij.clear();
				}
				this.werkveld.add(s);
			}
		} 
		else {
			System.out.printf("%s%n", UITextHelper.UIText("Je.hebt.geen.Joker"));
		}
	}
	
	/**
	 * Methode om rij te splitsen
	 */
	public void rijSplitsen(){
		System.out.printf("%s%n", UITextHelper.UIText("Welke.rij.wil.je.splitsen?"));
		int rij = sc.nextInt();
		System.out.printf("%s%n", UITextHelper.UIText("Geef.de.naam.van.de.steen.waarachter.je.de.rij.wil.splitsen"));
		String naam = sc.next();
		int splitsing = 0;
		int index = 0;
		List<RummiSteen> naSplitsing = new ArrayList();
		List<RummiSteen> voorSplitsing = new ArrayList();
		for(RummiSteen rummisteen : this.tijdelijkeTafel.getStenenOpTafel().get(rij)) { 
			if (splitsing <1) {
				voorSplitsing.add(rummisteen);
				if(rummisteen.getNaam().equals(naam)) {
				splitsing++;
				index = this.tijdelijkeTafel.getStenenOpTafel().get(rij).indexOf(rummisteen);
				}
			}
			else {
				naSplitsing.add(rummisteen);
			}
		}
		this.tijdelijkeTafel.getStenenOpTafel().get(rij).clear();

		if(splitsing == 0)
			System.out.printf("%s%n", UITextHelper.UIText("Deze.steen.ligt.niet.op.deze.rij"));
		else {
			for(RummiSteen rummisteen : voorSplitsing)
				this.tijdelijkeTafel.getStenenOpTafel().get(rij).add(rummisteen);
//			this.tijdelijkeTafel.getStenenOpTafel().get(rij).add(null);
//			this.tijdelijkeTafel.getStenenOpTafel().get(rij).add(null);
			for(RummiSteen rummisteen : naSplitsing)
				this.tijdelijkeTafel.getStenenOpTafel().get(rij+1).add(rummisteen);
			
		}

		
		
	}
}
