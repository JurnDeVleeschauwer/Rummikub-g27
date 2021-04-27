package gui;

import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import domein.RummiSteen;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class SpelPaneel extends GridPane {
	
	private final DomeinController domeinController;
    private final HoofdPaneel hoofdPaneel;
    private GridPane tafelPaneel;
    private GridPane werkveldPaneel;
    private GridPane spelerPaneel;
    private GridPane optiesPaneel;
	
    public SpelPaneel(DomeinController domeinController, HoofdPaneel hoofdPaneel)
	    {
	        this.domeinController = domeinController;
	        this.hoofdPaneel = hoofdPaneel;
//			this.tafelPaneel = new TafelPaneel(domeinController, hoofdPaneel);
//			this.werkveldPaneel = new WerkveldPaneel(domeinController, hoofdPaneel);
//			this.spelerPaneel = new SpelerPaneel(domeinController, hoofdPaneel);
//			this.optiesPaneel = new OptiesPaneel(domeinController, hoofdPaneel);
	        this.tafelPaneel = new GridPane();
	        
	        Button test = new Button("test");
	        this.tafelPaneel.add(test, 0, 0);
	        this.werkveldPaneel = new GridPane();

	        Button test2 = new Button("test2");
	        this.werkveldPaneel.add(test2, 0, 0);
	        this.spelerPaneel = new GridPane();
	        SpelerStenenGeven();
//	        Button test3 = new Button("test3");
//	        this.spelerPaneel.add(test3, 0, 0);
	        this.optiesPaneel = new GridPane();

	        Button test4 = new Button("test4");
	        this.optiesPaneel.add(test4, 0, 0);
	        configureerGrid();
	        voegComponentenToe();
	    }

	private void voegComponentenToe() {
		add(this.tafelPaneel, 0, 0);
		add(this.werkveldPaneel, 1, 0);
		add(this.spelerPaneel, 0, 1);
		add(this.optiesPaneel, 1, 1);
		
	}

	private void configureerGrid() {
		setPadding(new Insets(10));
        setHgap(100);
        setVgap(100);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        
        getColumnConstraints().addAll(col1, col2);
		
	}
	
	private void SpelerStenenGeven() {
		List<RummiSteen> stenen = new ArrayList<>();
		stenen = this.domeinController.getSpelerAanZet().getStenenInBezit();
		int index = 0;
		for(RummiSteen steen : stenen) {
			String naam = steen.getNaam();
			Button btnSteen = new Button(naam);
			
			
			
			if(index < stenen.size()/2) {
				this.spelerPaneel.add(btnSteen, index, 0);
			}else
				this.spelerPaneel.add(btnSteen, index-(stenen.size()/2), 1);
			index++;
		}
		
//		stenen.forEach((steen, index) -> {if(index <= stenen.size()/2) {
//			this.spelerPaneel.add(steen, 0, index);
//		}else
//			this.spelerPaneel.add(steen, 1, index-(stenen.size()/2));
//		
//		});
		
		
	}

}
