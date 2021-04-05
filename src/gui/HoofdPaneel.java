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
	private Locale locale;
	private ResourceBundle bundle;

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
		//this.spel = new SpelPaneel(domeinController, this);

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
	

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}
}