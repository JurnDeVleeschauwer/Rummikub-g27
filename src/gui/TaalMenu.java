package gui;

import i18n.UITextHelper;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class TaalMenu extends GridPane
{
    private final HoofdPaneel hoofdPaneel;



    public TaalMenu(HoofdPaneel hoofdPaneel)
    {
        this.hoofdPaneel = hoofdPaneel;

        
        configureerGrid();
        voegComponentenToe();
    }

    private void configureerGrid()
    {
        setPadding(new Insets(10));
        setHgap(30);
        setVgap(0);
        this.setAlignment(Pos.CENTER);
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResourceAsStream("/images/Achtergrond_Poker.jpg")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                  BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
    }

    
    private void voegComponentenToe()
    {	
    	
    	ImageView nlImage = new ImageView(
                new Image(getClass().getResourceAsStream("/images/NL_Flag.png"))
    	);
    	nlImage.setFitWidth(200);
    	nlImage.setFitHeight(150);
//    	add(nlImage, 2, 2);
    	
        Button nl = new Button("");
        nl.setGraphic(nlImage);
        nl.setOnAction(this::taalkeuze);
        nl.setPrefHeight(150);
        nl.setPrefWidth(200);
        nl.setPadding(new Insets(0, 0, 0, 0));
        nl.setUserData("nl");
        nl.setFont(new Font(30));
        add(nl, 2, 3);
        
        ImageView frImage = new ImageView(
                new Image(getClass().getResourceAsStream("/images/FR_Flag.png"))
    	);
    	frImage.setFitWidth(200);
    	frImage.setFitHeight(150);
    	add(frImage, 3, 2);
    	
        Button fr = new Button("");
        fr.setGraphic(frImage);
        fr.setOnAction(this::taalkeuze);
        fr.setPrefHeight(150);
        fr.setPrefWidth(200);
        fr.setPadding(new Insets(0, 0, 0, 0));
        fr.setUserData("fr");
        fr.setFont(new Font(30));
        add(fr, 3, 3);
        
        ImageView enImage = new ImageView(
                new Image(getClass().getResourceAsStream("/images/EN_Flag.png"))
    	);
    	enImage.setFitWidth(200);
    	enImage.setFitHeight(150);
//    	add(enImage, 4, 2);
    	
        Button en = new Button("");
        en.setGraphic(enImage);
        en.setOnAction(this::taalkeuze);
        en.setPrefHeight(150);
        en.setPrefWidth(200);
        en.setPadding(new Insets(0, 0, 0, 0));
        en.setUserData("en");
//        en.setFont(new Font(40));
        add(en, 4, 3);
    }
    
    private void taalkeuze(ActionEvent event) {
        final Button button = (Button) event.getSource();
        final String taal = (String) button.getUserData();

        UITextHelper.setLocale(taal);

        hoofdPaneel.createPanelen();
        hoofdPaneel.toonHoeveelSpelersPaneel();
    }
}
