package gui;

import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class AanmeldPaneel extends GridPane
{
    private final DomeinController domeinController;
    private final HoofdPaneel hoofdPaneel;


    public AanmeldPaneel(DomeinController domeinController, HoofdPaneel hoofdPaneel)
    {
        this.domeinController = domeinController;
        this.hoofdPaneel = hoofdPaneel;

        
        configureerGrid();
        voegComponentenToe();
    }

    private void configureerGrid()
    {
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        
        getColumnConstraints().addAll(col1, col2);
    }

    private final TextField naam = new TextField();
    private final PasswordField wachtwoord = new PasswordField();
    private final Label foutbericht = new Label();
    
    private void voegComponentenToe()
    {
        Text header = new Text(hoofdPaneel.getBundle().getString("Aanmelden"));
        GridPane.setHalignment(header, HPos.LEFT);
        add(header, 0, 0, 2, 1);
        
        add(new Label(hoofdPaneel.getBundle().getString("Geef.je.gebruikersnaam.in")), 0, 1, 1, 1);
        add(naam, 1, 1, 1, 1);
        
        add(new Label(hoofdPaneel.getBundle().getString("Geef.je.wachtwoord.in")), 0, 2, 1, 1);
        add(wachtwoord, 1, 2, 1, 1);
        
        Button aanmelden = new Button(hoofdPaneel.getBundle().getString("Aanmelden"));
        aanmelden.setOnAction(this::aanmelden);
        aanmelden.setDefaultButton(true);
        HBox controls = new HBox(aanmelden, foutbericht);
        controls.setSpacing(10);
        add(controls, 0, 3, 2, 1);
          
        //aanmelden.setVisible(false);
    }
    
    private void aanmelden(ActionEvent event)
    {
        if (naam.getText().trim().isEmpty()) {
            foutbericht.setText(hoofdPaneel.getBundle().getString("Gelieve.uw.naam.op.te.geven"));
            return;
        }
        if (wachtwoord.getText().trim().isEmpty()) {
            foutbericht.setText(hoofdPaneel.getBundle().getString("Gelieve.uw.wachtwoord.op.te.geven"));
            return;
        }
        
        domeinController.controleerSpeler(naam.getText().trim(), wachtwoord.getText().trim());
        
        if (domeinController.getGebruikersnamen() == null) { //TODO
            foutbericht.setText(hoofdPaneel.getBundle().getString("Deze.speler.bestaat.niet.of.het.wachtwoord.is.verkeerd"));
            return;
        }
        
        foutbericht.setText(null);
        hoofdPaneel.toonHoofdMenu();
    }
    
}
