package gui;

import domein.DomeinController;
import i18n.UITextHelper;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
        setHgap(10);
        setVgap(10);
    }

    private int aantalSpelers = 0;
    private final Label foutbericht = new Label();

    private void voegComponentenToe() {
        add(new Label(UITextHelper.UIText("Hoeveel.spelers.spelen.er.mee?.2-4.Spelers")), 0, 1, 4, 1);

        Button spelers2 = new Button("2");
        spelers2.setOnAction(this::spelers2);
        add(spelers2, 0, 2, 1, 1);
        
        Button spelers3 = new Button("3");
        spelers3.setOnAction(this::spelers3);
        add(spelers3, 1, 2, 1, 1);
        
        Button spelers4 = new Button("4");
        spelers4.setOnAction(this::spelers4);
        add(spelers4, 2, 2, 1, 1);
        
        Button toevoegen = new Button(UITextHelper.UIText("ok"));
        toevoegen.setOnAction(this::toevoegen);
        add(toevoegen, 0, 4, 2, 1);

    }

    private void spelers2(ActionEvent event) {
    	this.aantalSpelers = 2;
    }
    
    private void spelers3(ActionEvent event) {
    	this.aantalSpelers = 3;
    }
    
    private void spelers4(ActionEvent event) {
    	this.aantalSpelers = 4;
    }
    
    
    private void toevoegen(ActionEvent event) {
        if (aantalSpelers == 0) {
            foutbericht.setText(UITextHelper.UIText("Gelieve.een.numer.op.te.geven"));
            return;

        } else {
        		hoofdPaneel.setAantalSpelers(aantalSpelers);
                hoofdPaneel.toonAanmeldPaneel();;
                foutbericht.setText(null);
        }
    }

}
