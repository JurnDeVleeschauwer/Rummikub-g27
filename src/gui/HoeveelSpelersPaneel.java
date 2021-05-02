package gui;

import domein.DomeinController;
import i18n.UITextHelper;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * 
 * @author Jurn
 *
 */
public class HoeveelSpelersPaneel extends GridPane {
    private final DomeinController domeinController;
    private final HoofdPaneel hoofdPaneel;

    /**
     * 
     * @param domeinController
     * @param hoofdPaneel
     */
    public HoeveelSpelersPaneel(DomeinController domeinController, HoofdPaneel hoofdPaneel) {
        this.domeinController = domeinController;
        this.hoofdPaneel = hoofdPaneel;

        configureerGrid();
        voegComponentenToe();
    }

    private void configureerGrid() {
        setPadding(new Insets(10));
        setHgap(20);
        setVgap(10);
        this.setAlignment(Pos.CENTER);
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResourceAsStream("/images/Achtergrond_Poker.jpg")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                  BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
    }

    private int aantalSpelers = 0;
    private final Label foutbericht = new Label();

    private void voegComponentenToe() {
        
    	Label lblAantalSpelers = new Label(UITextHelper.UIText("Hoeveel.spelers.spelen.er.mee?.2-4.Spelers"));
    	lblAantalSpelers.setTextFill(Color.WHITESMOKE);
    	lblAantalSpelers.setFont(Font.font(null, FontWeight.BOLD, 18));
        add(lblAantalSpelers, 0, 1, 4, 1);

        Button spelers2 = new Button("2");
        spelers2.setOnAction(this::spelers2);
        spelers2.setPrefHeight(40);
        spelers2.setPrefWidth(40);
        spelers2.setFont(new Font(15));
        add(spelers2, 0, 2, 1, 1);
        
        Button spelers3 = new Button("3");
        spelers3.setOnAction(this::spelers3);
        spelers3.setPrefHeight(40);
        spelers3.setPrefWidth(40);
        spelers3.setFont(new Font(15));
        add(spelers3, 1, 2, 1, 1);
        
        Button spelers4 = new Button("4");
        spelers4.setOnAction(this::spelers4);
        spelers4.setPrefHeight(40);
        spelers4.setPrefWidth(40);
        spelers4.setFont(new Font(15));
        add(spelers4, 2, 2, 1, 1);
        

    }

    private void spelers2(ActionEvent event) {
		hoofdPaneel.setAantalSpelers(2);
        hoofdPaneel.toonAanmeldPaneel();;

    }
    
    private void spelers3(ActionEvent event) {
		hoofdPaneel.setAantalSpelers(3);
        hoofdPaneel.toonAanmeldPaneel();;
    }
    
    private void spelers4(ActionEvent event) {
		hoofdPaneel.setAantalSpelers(4);
        hoofdPaneel.toonAanmeldPaneel();;
    }
    
    
  

}
