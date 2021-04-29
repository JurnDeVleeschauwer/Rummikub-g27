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
		
		for(int i=0;i<7;i++) {
			steenOpTafelLeggen(this.spelerAanZet.getStenenInBezit().get(i), i);
//			steenNaarWerkveld(this.spelerAanZet.getStenenInBezit().get(i+7));
		}
		
	}
	
	/** Haalt lijst van aangemelde spelers op
	 * @return lijst van spelers.
	 */
	public List<Speler> getSpelers() {
		return spelers;
	}
	
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
	
	
	public Tafel getTijdelijkeTafel() {
		return tijdelijkeTafel;
	}
	public void setTijdelijkeTafel(Tafel tijdelijkeTafel) {
		this.tijdelijkeTafel = tijdelijkeTafel;
	}
	public Tafel getVasteTafel() {
		return vasteTafel;
	}
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
	
	private RummiSteen steenUitPotHalen() {
		return pot.verwijderSteen();
	}
	
	private void setSpelerAanZet(Speler spelerAanZet) {
		this.spelerAanZet = spelerAanZet;
	}
	
	public String toonStenenSpeler() {
		return spelerAanZet.toonStenen();
	}
	
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

	public String toonStenenTafel(int tafel) {
		if (tafel<10) {
			return vasteTafel.toonStenen();
		}
		else
			return tijdelijkeTafel.toonStenen();
	}
	
	public void zetNeemSteen(boolean b) {
		spelerAanZet.setNeemSteen(b);
	}
	
	/**
	 * Be�indigt beurt van speler die momenteel aan de beurt is
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
	public void steenOpTafelLeggen(RummiSteen steen, int rij) {
		this.tijdelijkeTafel.legSteenOpTafel(steen, rij);
	}
	
	public RummiSteen geefSteenMetNaam(String naam) {
		for(RummiSteen s : this.werkveld) {
			if(s.getNaam().equals(naam)) return s;
		}
		return null;
	}
	
	/** 
	 * Legt een gekozen steen bij aan de gekozen rij
	 */
	public void steenAanleggen() {
		System.out.printf("%s%n",UITextHelper.UIText("Geef.de.naam.van.de.steen.die.je.wil.leggen"));
		String naam = sc.next();
		int rij = 11;
		do {
			System.out.printf("%s%n",UITextHelper.UIText("Geef.de.rij.waaraan.je.deze.steen.wilt.leggen"));
			rij = sc.nextInt();
		}while(rij > 10);
		
		boolean vanWerkveld = true;
		RummiSteen steen = this.geefSteenMetNaam(naam);
		if(steen==null) {
			steen = this.spelerAanZet.geefSteenMetNaam(naam);
			vanWerkveld = false;
		}
		
		if(steen != null) {
			if(vanWerkveld) {
				this.werkveld.remove(steen);
				this.steenOpTafelLeggen(steen, rij);
			}else {
				this.spelerAanZet.verwijderSteen(steen);
				this.steenOpTafelLeggen(steen, rij);
				this.zetNeemSteen(false);
			}
		} else System.out.printf("%s%n", UITextHelper.UIText("Deze.steen.heb.je.niet.in.je.bezit"));
		
	}
	
	/**
	 * Neemt een steen van de tafel en verplaatst deze naar het werkveld
	 */
	public void steenNaarWerkveld() {
		System.out.printf("%s%n", UITextHelper.UIText("Geef.de.naam.van.de.steen.die.je.naar.het.werkveld.wil.brengen"));
		String naam = sc.next();
				
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
				for (RummiSteen rummisteen : this.tijdelijkeTafel.getStenenOpTafel().get(rij)) { //Dit werkt blijkbaar niet
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
						for (RummiSteen steen : r) {  //En dit blijkbaar ook niet
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
		for(RummiSteen rummisteen : this.tijdelijkeTafel.getStenenOpTafel().get(rij)) { //en hier dus ook niet
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
