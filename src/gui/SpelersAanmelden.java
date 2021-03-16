package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import domein.Speler;
import exceptions.ExceptieSpelerAanmelden;
import i18n.UITextHelper;

public class SpelersAanmelden {

	public void spelersAanmelden(DomeinController dc) throws ExceptieSpelerAanmelden {
		
		
		int aantal = 0;
		Scanner sc = new Scanner(System.in);
		do {
		System.out.println(UITextHelper.UIText("Hoeveel.spelers.spelen.er.mee?.2-4.Spelers"));
		aantal = sc.nextInt();
		} while (aantal <2 || aantal >4);
		
		int aantalIngelogd = 0;
		List<Speler> spelers = new ArrayList<>();
		while (aantalIngelogd < aantal) {
			System.out.print(UITextHelper.UIText("Speler"));
            System.out.printf(" %d ", aantalIngelogd+1);
            System.out.println(UITextHelper.UIText("logt.nu.in"));
			System.out.println(UITextHelper.UIText("Geef.je.gebruikersnaam.in"));
			String gebruikersnaam = sc.next();
			
			System.out.println(UITextHelper.UIText("Geef.je.wachtwoord.in"));
			String wachtwoord = sc.next();
			Speler speler = dc.getSpelerRepo().controleerSpeler(gebruikersnaam, wachtwoord);
			if (speler != null) {
				spelers.add(speler);
				aantalIngelogd++;
			}
			else {
				System.out.println(UITextHelper.UIText("Gegevens.zijn.niet.correct.probeer.opnieuw"));
				//throw new ExceptieSpelerAanmelden("gebruiker.wachtwoord.voldoet.niet.aan.de.voorwaarden");
			}
			
		}
		for(Speler speler : spelers) {
			System.out.printf("%s ",speler.getGebruikersnaam());
		}
		System.out.printf("\n");
		System.out.printf(UITextHelper.UIText("Speel.spel"));
		System.out.printf("\n");
		System.out.println(UITextHelper.UIText("Toon.overzicht"));
		
	}
		
		 
}


