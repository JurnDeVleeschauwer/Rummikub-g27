package gui;

import domein.DomeinController;
import exceptions.ExceptieSpelerAanmelden;
import i18n.UITextHelper;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

        this.setAlignment(Pos.CENTER);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        col2.setMaxWidth(400);
        
        getColumnConstraints().addAll(col1, col2);

        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResourceAsStream("/images/Achtergrond_Poker.jpg")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                  BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
        
    }

    private final TextField naam = new TextField();
    private final PasswordField wachtwoord = new PasswordField();
    private final Label foutbericht = new Label();
    
    private void voegComponentenToe()
    {
    	Text header = new Text();
    	header.setFill(Color.WHITE);
    	header.setFill(Color.WHITESMOKE);
    	header.setFont(Font.font(null, FontWeight.BOLD, 18));
    	switch (hoofdPaneel.getAantalSpelersIngelogt()) {
		case 0:
			header.setText(UITextHelper.UIText("Aanmelden.speler.1"));
			break;
		case 1:
			header.setText(UITextHelper.UIText("Aanmelden.speler.2"));
			break;
		case 2:
			header.setText(UITextHelper.UIText("Aanmelden.speler.3"));
			break;
		case 3:
			header.setText(UITextHelper.UIText("Aanmelden.speler.4"));
			break;
    	}
        
        GridPane.setHalignment(header, HPos.LEFT);
        add(header, 0, 0, 2, 1);
        
        Label lblNaam = new Label(UITextHelper.UIText("Geef.je.gebruikersnaam.in"));
        lblNaam.setTextFill(Color.WHITESMOKE);
        lblNaam.setFont(Font.font(null, FontWeight.BOLD, 14));
        add(lblNaam, 0, 1, 1, 1);
        add(naam, 1, 1, 1, 1);
        
        Label lblWachtwoord = new Label(UITextHelper.UIText("Geef.je.wachtwoord.in"));
        lblWachtwoord.setTextFill(Color.WHITESMOKE);
        lblWachtwoord.setFont(Font.font(null, FontWeight.BOLD, 14));
        add(lblWachtwoord, 0, 2, 1, 1);
        add(wachtwoord, 1, 2, 1, 1);
        
        Button aanmelden = new Button(UITextHelper.UIText("Aanmelden"));
        aanmelden.setOnAction(this::aanmelden);
        aanmelden.setDefaultButton(true);
        HBox controls = new HBox(aanmelden, foutbericht);
        controls.setSpacing(10);
        add(controls, 1, 3, 2, 1);
          
        //aanmelden.setVisible(false);
    }
    
    private void aanmelden(ActionEvent event)
    {
        if (naam.getText().trim().isEmpty()) {
            foutbericht.setText(UITextHelper.UIText("Gelieve.uw.naam.op.te.geven"));
            return;
        }
        if (wachtwoord.getText().trim().isEmpty()) {
            foutbericht.setText(UITextHelper.UIText("Gelieve.uw.wachtwoord.op.te.geven"));
            return;
        }
         	
        try {
            domeinController.controleerSpeler(naam.getText().trim(), wachtwoord.getText().trim());
            foutbericht.setText(null);

        } catch (ExceptieSpelerAanmelden e) {
            foutbericht.setText(e.getMessage());
            return;
            }
        
        domeinController.addSpelerAanLijst();
        foutbericht.setText(null);
        hoofdPaneel.setAantalSpelersIngelogt(hoofdPaneel.getAantalSpelersIngelogt() + 1);
        if(hoofdPaneel.getAantalSpelersIngelogt() == hoofdPaneel.getAantalSpelers()) {
        	hoofdPaneel.toonHoofdMenu();
        } else {
        	wachtwoord.setText("");
        	naam.setText("");
            hoofdPaneel.toonAanmeldPaneel();
            }

    }
    
}
