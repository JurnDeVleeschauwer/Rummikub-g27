package domein;

import java.util.List;
import java.util.Scanner;

public class Spel {
	private final List<Speler> spelers;
	private final Pot pot;
	private Speler spelerAanZet;

	public Spel(List<Speler> spelers) {
		this.pot = new Pot();
		this.spelers = spelers;
		this.spelerAanZet = spelers.get(0);
	}

	public List<Speler> getSpelers() {
		return spelers;
	}
	public Pot getPot() {
		return pot;
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
	
	public void toonStenen() {
		for (Speler speler : spelers) {
			System.out.println(speler.getGebruikersnaam());
			System.out.println(speler.getStenenInBezit().toString());
		}
		System.out.println("pot");
		System.out.println(pot.getStenen().toString());
	}
	
	public boolean isGedaan() {
		if (this.spelerAanZet.getStenenInBezit().isEmpty()) 
			return true;
		return false;
	}
	
	public void bepaalVolgendeSpelerAanZet() {
		int indexVorigeSpeler = -1;
		for (int i = 0 ; i<this.spelers.size();i++) {
			if (this.spelerAanZet == this.spelers.get(i)) {
				indexVorigeSpeler = i;
			}
		}
		if (indexVorigeSpeler == (this.spelers.size()-1)) {
			this.spelerAanZet = this.spelers.get(0);
		}
		else
			this.spelerAanZet = this.spelers.get(indexVorigeSpeler+1);
		this.spelerAanZet.setSpelerAanDeBeurt(true);
	}
	
	public void speelBeurt() {
		for (int i=0;i<(Math.random()*(this.spelerAanZet.getStenenInBezit().size()+1));i++) {
			this.spelerAanZet.verwijderSteen();
		}
	}
	
	public void speelSpel() { // Dit zal later in gui package moeten vermoed ik 
		this.spelerAanZet.setSpelerAanDeBeurt(true);
		Scanner sc = new Scanner(System.in);
		boolean isGedaan = false;
		do {
			
			int speel;
			do {
				System.out.printf("%s is aan de beurt (typ 1 in om je beurt te spelen)", this.spelerAanZet.getGebruikersnaam());
				speel = sc.nextInt();
			} while (speel != 1);
			
			speelBeurt();
			toonStenen();
			isGedaan = isGedaan();
			bepaalVolgendeSpelerAanZet();
		} while (isGedaan == false);
		
		berekenPlusToonScores(bepaalWinnaar());
	}
	
	public Speler bepaalWinnaar() {
		for (Speler speler : spelers) {
			if (speler.getStenenInBezit().size() == 0)
				return speler;
		}
		return null;
		
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
//				toonStenen();
//			}
//		} while (test);
//		berekenPlusToonScores(winnaar);
//		for (Speler speler : spelers) {
//			System.out.printf("%s heeft score van %d%n",speler.getGebruikersnaam(),speler.getScore());
//		}
//	}
	
	public void berekenPlusToonScores(Speler winnaar) {
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
		
		for (Speler speler : spelers) {
			System.out.printf("%s heeft score van %d%n",speler.getGebruikersnaam(),speler.getScore());
		}
	}
	
}
