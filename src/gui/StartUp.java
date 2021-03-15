package gui;

import domein.DomeinController;
import exceptions.ExceptieSpelerAanmelden;

public class StartUp {

	public static void main(String[] args) throws ExceptieSpelerAanmelden {
		// TODO Auto-generated method stub

		DomeinController dc = new DomeinController();
		new SpelersAanmelden().spelersAanmelden(dc);
		
		
		
	}

}
