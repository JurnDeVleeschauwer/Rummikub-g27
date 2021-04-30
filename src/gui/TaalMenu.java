package gui;

import i18n.UITextHelper;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
        
    }

    
    private void voegComponentenToe()
    {
        

        
        Button nl = new Button("Nederlands");
        nl.setOnAction(this::taalkeuze);
        nl.setUserData("nl");
        nl.setPrefHeight(150);
        nl.setPrefWidth(200);
        nl.setFont(new Font(30));
        add(nl, 1, 3);
        
        Button fr = new Button("Français");
        fr.setOnAction(this::taalkeuze);
        fr.setUserData("fr");
        fr.setPrefHeight(150);
        fr.setPrefWidth(200);
        fr.setFont(new Font(40));
        add(fr, 1, 4);
        
        Button en = new Button("English");
        en.setOnAction(this::taalkeuze);
        en.setUserData("en");
        en.setPrefHeight(150);
        en.setPrefWidth(200);
        en.setFont(new Font(40));
        add(en, 1, 5);
    }
    
    private void taalkeuze(ActionEvent event) {
        final Button button = (Button) event.getSource();
        final String taal = (String) button.getUserData();

        UITextHelper.setLocale(taal);

        hoofdPaneel.createPanelen();
        hoofdPaneel.toonHoeveelSpelersPaneel();
    }
}
