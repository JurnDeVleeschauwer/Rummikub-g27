package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import domein.Speler;
import exceptions.ExceptieSpelerAanmelden;

public class SpelersAanmelden {

	public void spelersAanmelden(DomeinController dc) throws ExceptieSpelerAanmelden {
		
		
		int aantal = 0;
		Scanner sc = new Scanner(System.in);
		do {
		System.out.println("Hoeveel spelers spelen er mee? (2-4 Spelers) ");
		aantal = sc.nextInt();
		} while (aantal <2 || aantal >4);
		
		int aantalIngelogd = 0;
		List<Speler> spelers = new ArrayList<>();
		while (aantalIngelogd < aantal) {
			System.out.printf("Speler %d logt nu in%n", aantalIngelogd+1);
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
				System.out.println("Gegevens zijn niet correct, probeer opnieuw.");
				//throw new ExceptieSpelerAanmelden("gebruiker.wachtwoord.voldoet.niet.aan.de.voorwaarden");
			}
			
		}
		for(Speler speler : spelers) {
			System.out.printf("%s ",speler.getGebruikersnaam());
		}
		
		System.out.printf("%nSpeel spel%n");
		System.out.println("Toon overzicht");
		
	}
		
		 
}

