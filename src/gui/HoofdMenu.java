package gui;

import domein.DomeinController;
import i18n.UITextHelper;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class HoofdMenu extends GridPane {
	private final DomeinController domeinController;
	private final HoofdPaneel hoofdPaneel;

	public HoofdMenu(DomeinController domeinController, HoofdPaneel hoofdPaneel) {
		this.domeinController = domeinController;
		this.hoofdPaneel = hoofdPaneel;

		configureerGrid();
		voegComponentenToe();
	}

	private void configureerGrid() {
		setPadding(new Insets(10));
		setHgap(10);
		setVgap(10);

	}

	private void voegComponentenToe() {

		Button speelSpel = new Button(UITextHelper.UIText("Speel.spel"));
		speelSpel.setOnAction(this::speelSpel);
		add(speelSpel, 0, 0, 2, 1);
		
		Button toonOverzicht = new Button(UITextHelper.UIText("Toon.overzicht"));
		toonOverzicht.setOnAction(this::toonOverzicht);
		add(toonOverzicht, 0, 1, 2, 1);

		Button stoppen = new Button(UITextHelper.UIText("Stop.met.spelen"));
		stoppen.setOnAction(this::stoppen);
		add(stoppen, 0, 2, 2, 1);
	}

	private void speelSpel(ActionEvent event) {
		this.domeinController.startSpel();
		hoofdPaneel.toonSpelPaneel();
	}
	
	private void toonOverzicht(ActionEvent event) {
		hoofdPaneel.toonToonOverzichtPaneel();
	}

	private void stoppen(ActionEvent event) {
		System.exit(1);
	}
	
}
