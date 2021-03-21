package domein;

import java.util.List;

public class Spel {
	private final List<Speler> spelers;
	private final Pot pot;

	public Spel(List<Speler> spelers) {
		this.pot = new Pot();
		this.spelers = spelers;
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
	
	public void testWinst() {
		boolean test=true;
		Speler winnaar = null;
		do {
			for (Speler speler : spelers) {
			
				for (int i=0;i<(Math.random()*(speler.getStenenInBezit().size()+1));i++) {
					speler.verwijderSteen();
				}
				if (speler.getStenenInBezit().isEmpty()) {
					System.out.println(speler.getGebruikersnaam());
					winnaar = speler;
					test=false;
					break;
				}
				toonStenen();
			}
		} while (test);
		berekenScore(winnaar);
		for (Speler speler : spelers) {
			System.out.printf("%s heeft score van %d%n",speler.getGebruikersnaam(),speler.getScore());
		}
	}
	
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
	
}
