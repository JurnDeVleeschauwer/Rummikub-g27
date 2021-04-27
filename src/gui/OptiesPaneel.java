package gui;

import domein.DomeinController;

public class OptiesPaneel {
	private final DomeinController domeinController;
    private final HoofdPaneel hoofdPaneel;
    
    public OptiesPaneel(DomeinController domeinController, HoofdPaneel hoofdPaneel)
    {
        this.domeinController = domeinController;
        this.hoofdPaneel = hoofdPaneel;
        //configureerGrid();
        //voegComponentenToe();
    }
}
