package gui;

import i18n.UITextHelper;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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
        setHgap(10);
        setVgap(10);
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResourceAsStream("/images/achtergrond_Poker.jpg")),
                BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER,
                  BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
//        this.setStyle("-fx-background-color: #136032");
    }

    
    private void voegComponentenToe()
    {	
    	
    	ImageView nlImage = new ImageView(
                new Image(getClass().getResourceAsStream("/images/NL_Flag.png"))
    	);
    	nlImage.setFitWidth(200);
    	nlImage.setFitHeight(133);
    	add(nlImage, 2, 2);
    	
    	ImageView frImage = new ImageView(
                new Image(getClass().getResourceAsStream("/images/FR_Flag.png"))
    	);
    	frImage.setFitWidth(200);
    	frImage.setFitHeight(133);
    	add(frImage, 3, 2);
    	
    	ImageView enImage = new ImageView(
                new Image(getClass().getResourceAsStream("/images/EN_Flag.png"))
    	);
    	enImage.setFitWidth(200);
    	enImage.setFitHeight(133);
    	add(enImage, 4, 2);
    	
        
        Button nl = new Button("Nederlands");
        nl.setOnAction(this::taalkeuze);
        nl.setUserData("nl");
        nl.setPrefHeight(150);
        nl.setPrefWidth(200);
        nl.setFont(new Font(30));
        add(nl, 2, 3);
        
        Button fr = new Button("Français");
        fr.setOnAction(this::taalkeuze);
        fr.setUserData("fr");
        fr.setPrefHeight(150);
        fr.setPrefWidth(200);
        fr.setFont(new Font(40));
        add(fr, 3, 3);
        
        Button en = new Button("English");
        en.setOnAction(this::taalkeuze);
        en.setUserData("en");
        en.setPrefHeight(150);
        en.setPrefWidth(200);
        en.setFont(new Font(40));
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
