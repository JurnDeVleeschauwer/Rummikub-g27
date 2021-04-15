package domein;

import java.util.ArrayList;
import java.util.List;

import persistentie.SpelerRepo;

public class DomeinController {
	
	private SpelerRepo spelerRepo = new SpelerRepo();
	List<Speler> spelers = new ArrayList<>();
	Speler speler = null;
	private Spel spel;

//	public void addSpelerAanLijst(String naam, String wachtwoord) {
//	  spelers.add(new Speler(naam,wachtwoord));
//	}

	public SpelerRepo getSpelerRepo() {
		return spelerRepo;
	}
	
	public boolean controleerSpeler(String gebruikersnaam, String wachtwoord) {
	     speler = null;
		 this.speler = spelerRepo.controleerSpeler(gebruikersnaam, wachtwoord);
		 
		 if (speler != null) {
			 return true;
		 }else {
			 return false;
		 }
	}
	public void addSpelerAanLijst() {
		  spelers.add(speler);
	}
	public List<String> getGebruikersnamen() {
		List<String> gebruikersnamen = new ArrayList<>();
		for(Speler speler : spelers) {
			gebruikersnamen.add(speler.getGebruikersnaam());
		}
		return gebruikersnamen;
	}
	
	public void startSpel() {
		this.spel = new Spel(spelers);
	}
	
	public String toonOverzicht() {
		String overzicht = String.format("Speler\tScore%n");
		for (Speler speler : spelers) {
			overzicht += String.format("%s\t%d%n",speler.getGebruikersnaam(),speler.getScore());
		}
		return overzicht;
	}
	
	public String toonStenen() {
		return String.format("VTafel:%n%s%nTTafel:%n%sWerkveld: %s%n%s:%n%s", spel.toonStenenTafel(1), spel.toonStenenTafel(50), spel.toonWerkveld(),spel.getSpelerAanZet().getGebruikersnaam(), spel.toonStenenSpeler());
	}
	
	public boolean checkWinst() {
		return spel.checkWinst();
	}

	public void reset() {
		spel.resetTijdelijkeTafel();
		zetNeemSteen(true);
	}

	public void zetNeemSteen(boolean b) {
		spel.zetNeemSteen(b);
	}

	public void beeindigBeurt() {
		spel.beeindigBeurt();
	}
	
	public void steenAanleggen() {
		spel.steenAanleggen();
	}
	
	public void SteenVervangenDoorJoker() {
		spel.SteenVervangenDoorJoker();
	}
	
	public void jokerVervangen() {
		spel.jokerVervangen();
	}
	
	public void steenNaarWerkveld() {
		spel.steenNaarWerkveld();
	}
	
	public List<Speler> getSpelers(){
		return spelers;
	}
	
}
