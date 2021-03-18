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
		while (aantalIngelogd < aantal) {
			System.out.print(UITextHelper.UIText("Speler"));
            System.out.printf(" %d ", aantalIngelogd+1);
            System.out.println(UITextHelper.UIText("logt.nu.in"));
			System.out.println(UITextHelper.UIText("Geef.je.gebruikersnaam.in"));
			String gebruikersnaam = sc.next();
			
			System.out.println(UITextHelper.UIText("Geef.je.wachtwoord.in"));
			String wachtwoord = sc.next();
			if (dc.controleerSpeler(gebruikersnaam, wachtwoord)) {
				dc.addSpelerAanLijst();
				aantalIngelogd++;
			}
			else {
				System.out.println(UITextHelper.UIText("Gegevens.zijn.niet.correct.probeer.opnieuw"));
				//throw new ExceptieSpelerAanmelden("gebruiker.wachtwoord.voldoet.niet.aan.de.voorwaarden");
			}
			
		}
		
		for(String gebruikersnaam : dc.getGebruikersnamen()) {
			System.out.printf("%s ", gebruikersnaam);
		}
		System.out.printf("\n");
		System.out.printf(UITextHelper.UIText("Speel.spel"));
		System.out.printf("\n");
		System.out.println(UITextHelper.UIText("Toon.overzicht"));
		
	}
		
		 
}


