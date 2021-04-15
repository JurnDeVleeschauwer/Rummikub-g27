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
	}

	public List<Speler> getSpelers() {
		return spelers;
	}
	public Pot getPot() {
		return pot;
	}
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
	public void resetTijdelijkeTafel() {
		//this.tijdelijkeTafel = SerializationUtils.clone(this.vasteTafel)
		this.tijdelijkeTafel.reset(this.vasteTafel.getStenenOpTafel(), this.spelerAanZet);
		
	}
	
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

	public void beeindigBeurt() {
		if (controleerTafel()) {
			if (spelerAanZet.getNeemSteen())
				spelerAanZet.krijgtSteen(steenUitPotHalen());
			bepaalSpelerAanZet();
			setVasteTafel(this.tijdelijkeTafel);
		}
		else {
			
		}
	}
	
	private boolean controleerTafel() {
		return tijdelijkeTafel.controleerTafel();
	}

	public void bepaalSpelerAanZet() {
		setSpelerAanZet(spelers.get((spelers.indexOf(spelerAanZet)+1)%spelers.size()));
	}
	
	public void steenOpTafelLeggen(RummiSteen steen, int rij) {
		this.tijdelijkeTafel.legSteenOpTafel(steen, rij);
	}
	
	public RummiSteen geefSteenMetNaam(String naam) {
		for(RummiSteen s : this.werkveld) {
			if(s.getNaam().equals(naam)) return s;
		}
		return null;
	}
	
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
	
	public void steenNaarWerkveld() {
		System.out.printf("%s%n", UITextHelper.UIText("Geef.de.naam.van.de.steen.die.je.naar.het.werkveld.wil.brengen"));
		String naam = sc.next();
				
		RummiSteen steen = this.tijdelijkeTafel.geefSteenMetNaam(naam);
		if(steen != null) {
			this.tijdelijkeTafel.verwijderSteen(steen);
			this.werkveld.add(steen);
		} else System.out.printf("%s%n", UITextHelper.UIText("Deze.steen.ligt.niet.op.tafel"));
	}
	
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
							this.tijdelijkeTafel.verwijderSteen(steen);
						}
					}
					for(RummiSteen rummisteen: rij) {
					r.add(rummisteen);
				}
				}
				this.werkveld.add(s);
			}
		} 
		else {
			System.out.printf("%s%n", UITextHelper.UIText("Je.hebt.geen.Joker"));
		}
	}
}
