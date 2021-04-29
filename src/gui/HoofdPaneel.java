package gui;

import java.util.Locale;
import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.scene.layout.BorderPane;

public class HoofdPaneel extends BorderPane {
	private DomeinController domeinController;
	private AanmeldPaneel aanmelden;
	private SpelPaneel spel;
	private HoofdMenu hoofdMenu;
	private TaalMenu taalMenu;
	private HoeveelSpelersPaneel hoeveelSpelers;
	private ToonOverzichtPaneel toonOverzicht;
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
//        setCenter(hoofdMenu);

	}
	
	
	public void createPanelen() {
		this.aanmelden = new AanmeldPaneel(domeinController, this);
		this.hoofdMenu = new HoofdMenu(domeinController, this);
		this.hoeveelSpelers = new HoeveelSpelersPaneel(domeinController, this);

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

	public void toonSpelPaneel() {
		this.spel = new SpelPaneel(domeinController, this);
		setCenter(spel);
	}


	public void toonAanmeldPaneel() {
		this.aanmelden = new AanmeldPaneel(domeinController, this);
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

}