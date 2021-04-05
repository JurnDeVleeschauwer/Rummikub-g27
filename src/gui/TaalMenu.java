package gui;

import java.util.Locale;
import java.util.ResourceBundle;

import domein.DomeinController;
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
        nl.setOnAction(this::nl);
        add(nl, 0, 0, 2, 1);
        
        Button fr = new Button("fr");
        fr.setOnAction(this::fr);
        add(fr, 0, 1, 2, 1);
        
        Button en = new Button("en");
        en.setOnAction(this::en);
        add(en, 0, 2, 2, 1);
    }
    
    private void nl(ActionEvent event)
    {
    	hoofdPaneel.setLocale(new Locale("nl"));
    	hoofdPaneel.createPanelen();
    	hoofdPaneel.toonAanmeldPaneel();
    	
    	
    }
    private void fr(ActionEvent event)
    {
    	hoofdPaneel.setLocale(new Locale("fr"));
    	hoofdPaneel.createPanelen();
    	hoofdPaneel.toonAanmeldPaneel();

    }
    
    private void en(ActionEvent event)
    {
    	hoofdPaneel.setLocale(new Locale("en"));
    	hoofdPaneel.createPanelen();
    	hoofdPaneel.toonAanmeldPaneel();

    }
}
