package cui;

import java.util.Scanner;
import domein.DomeinController;
import exceptions.ExceptieSpelerAanmelden;
import i18n.UITextHelper;

public class Rummikub {
	
	public void start(DomeinController dc) throws ExceptieSpelerAanmelden {
	Scanner sc = new Scanner(System.in);

	 
	 
	 System.out.println("nl");
	 System.out.println("fr");
	 System.out.println("en");
	 String taal = sc.next();
	 System.out.println(taal);
	 
	 UITextHelper.setLocale(taal);
	
	
	SpelersAanmelden.spelersAanmelden(dc, sc);
	
	boolean einde = true;
	do {
		System.out.printf("\n");
		System.out.printf("1. %s%n",UITextHelper.UIText("Speel.spel"));
		System.out.printf("2. %s%n",UITextHelper.UIText("Toon.overzicht"));
		System.out.printf("3. %s%n",UITextHelper.UIText("Stop.met.spelen"));
		System.out.println(UITextHelper.UIText("Kies.optie"));
		switch (sc.nextInt()) {
			case 1:
				SpelCui.spelCui(dc, sc);
				break;
			case 2:
				System.out.println(dc.toonOverzicht());
				break;
			case 3:
				System.exit(0);
				break;
//			default:
//				;
		}
	} while (einde);
	sc.close();
}
}
