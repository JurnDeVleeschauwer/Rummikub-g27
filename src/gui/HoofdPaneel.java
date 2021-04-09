package gui;

import java.util.Locale;
import java.util.ResourceBundle;
import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HoofdPaneel extends BorderPane {
	private DomeinController domeinController;
	private AanmeldPaneel aanmelden;
	//private SpelPaneel spel;
	private HoofdMenu hoofdMenu;
	private TaalMenu taalMenu;
	private HoeveelSpelersPaneel hoeveelSpelers;
	private ToonOverzichtPaneel toonOverzicht;
	private Locale locale;
	private ResourceBundle bundle;
	private int aantalSpelers;
	private int aantalSpelersIngelogt = 0;

	public HoofdPaneel(DomeinController domeinController) {
		this.domeinController = domeinController;
		this.taalMenu = new TaalMenu(this);

		voegComponentenToe();
	}



	private void voegComponentenToe() {

		setCenter(taalMenu);
//        setCenter(aanmelden);

	}
	
	
	public void createPanelen() {
		this.bundle = ResourceBundle.getBundle("i18n.Rummikub", locale);
		this.aanmelden = new AanmeldPaneel(domeinController, this);
		this.hoofdMenu = new HoofdMenu(domeinController, this);
		this.hoeveelSpelers = new HoeveelSpelersPaneel(domeinController, this);
		
		//this.spel = new SpelPaneel(domeinController, this);

	}
	
	public void setAantalSpelers(int aantalSpelers) {
		this.aantalSpelers = aantalSpelers;
	}
	
	public int getAantalSpelers() {
		return this.aantalSpelers;
	}
	
	public int getAantalSpelersIngelogt() {
		return aantalSpelersIngelogt;
	}

	public void setAantalSpelersIngelogt(int aantalSpelersIngelogt) {
		this.aantalSpelersIngelogt = aantalSpelersIngelogt;
	}

	//public void toonSpelPaneel() {

	//	setCenter(spel);
		// spel.controleerKrediet();
		// vernieuwStatus();
	//}


	public void toonAanmeldPaneel() {
		setCenter(aanmelden);
	}
	public void toonHoofdMenu() {
		setCenter(hoofdMenu);
	}
	public void toonHoeveelSpelersPaneel() {
		setCenter(hoeveelSpelers);
	}
	
	public void toonToonOverzichtPaneel() {
		this.toonOverzicht = new ToonOverzichtPaneel(domeinController, this);
		setCenter(toonOverzicht);
	}
	
	

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}
}