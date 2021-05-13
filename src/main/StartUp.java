package main;

import java.util.Locale;

import domein.DomeinController;
import exceptions.ExceptieSpelerAanmelden;
import gui.HoofdPaneel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

	        Scene scene = new Scene(root, 1535, 800);

	        Stage.setScene(scene);
	        Stage.setTitle("Rummikub");
	        Image iconImg =  new Image(getClass().getResourceAsStream("/images/RummIcon.png"));
	        Stage.getIcons().add(iconImg);
	        Stage.show();
	    }
	public static void main(String[] args) throws ExceptieSpelerAanmelden{
        Locale.setDefault(LOCALE_NL);

           launch(args);
        

	}

}
