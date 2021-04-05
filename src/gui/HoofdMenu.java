package gui;

import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class HoofdMenu extends GridPane {
	private final DomeinController domeinController;
	private final HoofdPaneel hoofdPaneel;
	private boolean admin = false;

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

		Button speelSpel = new Button(hoofdPaneel.getBundle().getString("Speel.spel"));
		speelSpel.setOnAction(this::speelSpel);
		add(speelSpel, 0, 0, 2, 1);
		
		Button toonOverzicht = new Button(hoofdPaneel.getBundle().getString("Toon.overzicht"));
		toonOverzicht.setOnAction(this::toonOverzicht);
		add(toonOverzicht, 0, 1, 2, 1);

		Button stoppen = new Button(hoofdPaneel.getBundle().getString("Stop.met.spelen"));
		stoppen.setOnAction(this::stoppen);
		add(stoppen, 0, 2, 2, 1);
	}

	private void speelSpel(ActionEvent event) {
		//TODO
	}
	
	private void toonOverzicht(ActionEvent event) {
		//TODO
	}

	private void stoppen(ActionEvent event) {
		System.exit(1);
	}
	
}
