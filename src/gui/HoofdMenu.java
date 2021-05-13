package gui;

import domein.DomeinController;
import i18n.UITextHelper;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
		setVgap(30);
		this.setAlignment(Pos.CENTER);
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResourceAsStream("/images/Achtergrond_Poker.jpg")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                  BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));

	}

	private void voegComponentenToe() {

		Button speelSpel = new Button(UITextHelper.UIText("Speel.spel"));
		speelSpel.setPrefWidth(100); 
		speelSpel.setPrefHeight(25);
		speelSpel.setOnAction(this::speelSpel);
		add(speelSpel, 0, 0, 2, 1);
		
		Button toonOverzicht = new Button(UITextHelper.UIText("Toon.overzicht"));
		toonOverzicht.setPrefWidth(100); 
		toonOverzicht.setPrefHeight(25);
		toonOverzicht.setOnAction(this::toonOverzicht);
		add(toonOverzicht, 0, 1, 2, 1);

		Button stoppen = new Button(UITextHelper.UIText("Stop.met.spelen"));
		stoppen.setPrefWidth(100); 
		stoppen.setPrefHeight(25);
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
