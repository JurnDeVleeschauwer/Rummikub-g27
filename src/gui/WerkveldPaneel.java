package gui;

import domein.DomeinController;

public class WerkveldPaneel {
	private final DomeinController domeinController;
    private final HoofdPaneel hoofdPaneel;
    
    public WerkveldPaneel(DomeinController domeinController, HoofdPaneel hoofdPaneel)
    {
        this.domeinController = domeinController;
        this.hoofdPaneel = hoofdPaneel;
        //configureerGrid();
        //voegComponentenToe();
    }
}
