package domein;

import java.util.List;

public class Spel {
	private final List<Speler> spelers;
	private final Pot pot;

	public Spel(List<Speler> spelers) {
		this.pot = new Pot();
		this.spelers = spelers;
		geefEerste14Stenen();
	}

	public List<Speler> getSpelers() {
		return spelers;
	}
	public Pot getPot() {
		return pot;
	}
	
	private void geefEerste14Stenen() {
		for (Speler speler : spelers) {
			for (int i=0;i<14;i++) {
				speler.krijgtSteen(steenUitPotHalen());
			}
		}
	}
	
	private RummiSteen steenUitPotHalen() {
		return pot.verwijderSteen();
	}
	
}
