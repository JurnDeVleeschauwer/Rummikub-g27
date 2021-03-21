package gui;

import java.util.Locale;
import java.util.Scanner;

import domein.DomeinController;
import exceptions.ExceptieSpelerAanmelden;
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
		
		
		new SpelersAanmelden().spelersAanmelden(dc);
		dc.bepaalVolgordeSpelers();
		dc.startSpel();
		
	}

}
