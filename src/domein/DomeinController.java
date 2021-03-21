package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import persistentie.SpelerRepo;

public class DomeinController {
	
	private SpelerRepo spelerRepo = new SpelerRepo();
	List<Speler> spelers = new ArrayList<>();
	Speler speler = null;
	private Spel spel;
	
	
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

	
	
	public List<String> getGebruikersnamen() {
		List<String> gebruikersnamen = new ArrayList<>();
		for(Speler speler : spelers) {
			gebruikersnamen.add(speler.getGebruikersnaam());
		}
		return gebruikersnamen;
	}
	
	
	
	public void addSpelerAanLijst() {
	  spelers.add(speler);
	}


	
	
	public void startSpel() {
		this.spel = new Spel(spelers);
		spel.geefEerste14Stenen();
		spel.toonStenen();
		spel.speelSpel();
		//spel.testWinst();
	}
	
	public void bepaalVolgordeSpelers() {
		Collections.shuffle(spelers);
	}
}
