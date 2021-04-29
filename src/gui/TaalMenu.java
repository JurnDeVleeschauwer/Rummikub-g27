package gui;

import java.util.Locale;

import i18n.UITextHelper;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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
        

        
        Button nl = new Button("nl");
        nl.setOnAction(this::taalkeuze);
        nl.setUserData("nl");
        nl.setPrefHeight(250);
        nl.setPrefWidth(300);
        add(nl, 1, 3);
        
        Button fr = new Button("fr");
        fr.setOnAction(this::taalkeuze);
        fr.setUserData("fr");
        fr.setPrefHeight(250);
        fr.setPrefWidth(300);
        add(fr, 2, 3);
        
        Button en = new Button("en");
        en.setOnAction(this::taalkeuze);
        en.setUserData("en");
        en.setPrefHeight(250);
        en.setPrefWidth(300);
        add(en, 3, 3);
    }
    
    private void taalkeuze(ActionEvent event) {
        final Button button = (Button) event.getSource();
        final String taal = (String) button.getUserData();

        UITextHelper.setLocale(taal);

        hoofdPaneel.createPanelen();
        hoofdPaneel.toonHoeveelSpelersPaneel();
    }
}
