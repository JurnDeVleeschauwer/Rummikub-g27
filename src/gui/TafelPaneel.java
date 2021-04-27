package gui;

import domein.DomeinController;
import javafx.scene.layout.GridPane;

public class TafelPaneel extends GridPane {
	private final DomeinController domeinController;
    private final HoofdPaneel hoofdPaneel;
    
    public TafelPaneel(DomeinController domeinController, HoofdPaneel hoofdPaneel)
    {
        this.domeinController = domeinController;
        this.hoofdPaneel = hoofdPaneel;
        //configureerGrid();
        //voegComponentenToe();
    }
}
