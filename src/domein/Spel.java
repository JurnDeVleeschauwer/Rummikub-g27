package domein;

import java.util.Collections;
import java.util.List;

public class Spel {
	private final List<Speler> spelers;
	private final Pot pot;
	private Speler spelerAanZet;
	private Tafel vasteTafel;
	private Tafel tijdelijkeTafel;

	public Spel(List<Speler> spelers) {
		this.pot = new Pot();
		this.vasteTafel = new Tafel();
		this.spelers = spelers;
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
	public void resetTijdelijkeTafel() {
		setTijdelijkeTafel(vasteTafel);
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

	public String toonStenenTafel() {
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
}
