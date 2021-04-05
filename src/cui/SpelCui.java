package cui;

import java.util.Scanner;

import domein.DomeinController;
import i18n.UITextHelper;

public class SpelCui {
	
	public void spelCui(DomeinController dc, Scanner sc) {
		dc.startSpel();
		do {
			System.out.println(dc.toonStenen());
			System.out.println();
			System.out.printf("%s%n%s%n",UITextHelper.UIText("Kies.optie"),UITextHelper.UIText("Opties.beurt"));
			dc.zetNeemSteen(true);
			switch (sc.nextInt()) {
			case 1:
				dc.beeindigBeurt();
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				dc.reset();
				break;
			}
		} while(!dc.checkWinst());
	}

}
