package main;

import java.util.Locale;
import java.util.Scanner;

import domein.DomeinController;
import exceptions.ExceptieSpelerAanmelden;
import cui.SpelersAanmelden;
import cui.SpelCui;
import i18n.UITextHelper;

public class StartUp {
	
	private static final Locale LOCALE_NL = new Locale("nl");

	public static void main(String[] args) throws ExceptieSpelerAanmelden {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		 Locale.setDefault(LOCALE_NL);
		 
		 
		 System.out.println("nl");
		 System.out.println("fr");
		 System.out.println("en");
		 String taal = sc.next();
		 System.out.println(taal);
		 
		 UITextHelper.setLocale(taal);
		DomeinController dc = new DomeinController();
		
		
		new SpelersAanmelden().spelersAanmelden(dc, sc);
		
		boolean einde = true;
		do {
			System.out.printf("\n");
			System.out.printf("1. %s%n",UITextHelper.UIText("Speel.spel"));
			System.out.printf("2. %s%n",UITextHelper.UIText("Toon.overzicht"));
			System.out.printf("3. %s%n",UITextHelper.UIText("Stop.met.spelen"));
			System.out.println(UITextHelper.UIText("Kies.optie"));
			switch (sc.nextInt()) {
				case 1:
					new SpelCui().spelCui(dc, sc);
					break;
				case 2:
					System.out.println(dc.toonOverzicht());
					break;
				case 3:
					einde=false;
					break;
//				default:
//					;
			}
		} while (einde);
		sc.close();
	}

}
