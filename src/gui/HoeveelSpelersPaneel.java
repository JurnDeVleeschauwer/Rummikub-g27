package gui;

import domein.DomeinController;
import i18n.UITextHelper;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    private final TextField nummer = new TextField();
    private final Label foutbericht = new Label();

    private void voegComponentenToe() {
        add(new Label(UITextHelper.UIText("Hoeveel.spelers.spelen.er.mee?.2-4.Spelers")), 0, 1, 1, 1);
        add(nummer, 1, 1, 1, 1);

        Button toevoegen = new Button(UITextHelper.UIText("ok"));
        toevoegen.setOnAction(this::toevoegen);
        add(toevoegen, 0, 4, 2, 1);

    }

    private void toevoegen(ActionEvent event) {
        if (nummer.getText().trim().isEmpty()) {
            foutbericht.setText(UITextHelper.UIText("Gelieve.een.numer.op.te.geven"));
            return;

        } else {
        		hoofdPaneel.setAantalSpelers(Integer.parseInt(nummer.getText().trim()));
                hoofdPaneel.toonAanmeldPaneel();;
                foutbericht.setText(null);
        }
    }

}
