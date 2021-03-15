package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import domein.Speler;
import exceptions.ExceptieSpelerAanmelden;

public class SpelersAanmelden {

	public void spelersAanmelden() throws ExceptieSpelerAanmelden {
		
		DomeinController dc = new DomeinController();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Hoeveel spelers spelen er mee?");
		int aantal = sc.nextInt();
		int aantalIngelogd = 0;
		List<Speler> spelers = new ArrayList<>();
		while (aantalIngelogd < aantal) {
			
			System.out.println("Geef je gebruikersnaam in: ");
			String gebruikersnaam = sc.next();
			
			System.out.println("Geef je wachtwoord in: ");
			String wachtwoord = sc.next();
			Speler speler = dc.getSpelerRepo().controleerSpeler(gebruikersnaam, wachtwoord);
			if (speler != null) {
				spelers.add(speler);
				aantalIngelogd++;
			}
			else {
//				throw new ExceptieSpelerAanmelden("gebruiker.wachtwoord.voldoet.niet.aan.de.voorwaarden");
			}
			
		}
		for(Speler speler : spelers) {
			System.out.printf("%s ",speler.getGebruikersnaam());
		}
	
		
	}
		
		 
}


