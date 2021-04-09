package main;

import java.util.Locale;

import cui.Rummikub;
import domein.DomeinController;
import exceptions.ExceptieSpelerAanmelden;
import gui.HoofdPaneel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {
	
	/**
	 * Maak land en dus taal nederlands als basis.
	 */
	private static final Locale LOCALE_NL = new Locale("nl");
	
	 @Override
	    public void start(Stage Stage) {
	        DomeinController domeinController = new DomeinController();
	        

	        HoofdPaneel root = new HoofdPaneel(domeinController);

	        Scene scene = new Scene(root, 800, 800);

	        Stage.setScene(scene);
	        Stage.setTitle("Rummikub");
	        Stage.show();
	    }
	public static void main(String[] args) throws ExceptieSpelerAanmelden{
        Locale.setDefault(LOCALE_NL);

        if (args[0].contentEquals("c")) {
            DomeinController domeinController = new DomeinController();
            new Rummikub().start(domeinController);

        } else {
           launch(args);
       }
        

	}

}
