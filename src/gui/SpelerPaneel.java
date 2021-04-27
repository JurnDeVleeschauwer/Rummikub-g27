package gui;

import domein.DomeinController;

public class SpelerPaneel {
	private final DomeinController domeinController;
    private final HoofdPaneel hoofdPaneel;
    
    public SpelerPaneel(DomeinController domeinController, HoofdPaneel hoofdPaneel)
    {
        this.domeinController = domeinController;
        this.hoofdPaneel = hoofdPaneel;
        //configureerGrid();
        //voegComponentenToe();
    }
}
