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
import javafx.scene.paint.Color;

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
	        this.werkveldPaneel = new GridPane();
	        this.spelerPaneel = new GridPane();
	        this.optiesPaneel = new GridPane();
	        
	        Button test = new Button("test");
	        this.tafelPaneel.add(test, 0, 0);
	        

	        Button test2 = new Button("test2");
	        this.werkveldPaneel.add(test2, 0, 0);
	        
	        
	        
			SpelerStenenGeven();
		    OptiesGeven();
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
			String kleur = steen.getKleur();
			String naam = steen.getNaam();
			Button btnSteen = new Button(naam);

			switch (kleur){
			case "Rood": 
				btnSteen.setTextFill(Color.RED);
				break;
			case "Blauw":
				btnSteen.setTextFill(Color.BLUE);
				break;
			case "Geel":
				btnSteen.setTextFill(Color.ORANGE);
				break;
			case "Zwart":
				btnSteen.setTextFill(Color.BLACK);
				break;
			case "Groen":
				btnSteen.setTextFill(Color.GREEN);
				break;
			default:
				btnSteen.setTextFill(Color.CYAN);  // hier moet een exceptie: als de kleur cyan is klopt er iets niet!
				break;
			}
			btnSteen.setMinSize(60, 90);
			btnSteen.setMaxSize(60, 90);
			btnSteen.setPrefSize(60,90);
			btnSteen.setPadding(new Insets(0, 4, 20, 0));
		
			if(index < stenen.size()/2) {
				this.spelerPaneel.add(btnSteen, index, 0);
			}else
				this.spelerPaneel.add(btnSteen, index-(stenen.size()/2), 1);
			index++;
		}
	}
	
	private void OptiesGeven() {
		List<Button> opties = new ArrayList<>();
		
		Button btnBeurtBeëindigen = new Button("Beurt beëindigen");
		opties.add(btnBeurtBeëindigen);
		Button btnSteenAanleggen = new Button("Steen aanleggen");
		opties.add(btnSteenAanleggen);
		Button btnJokerVervangen = new Button("Joker vervangen");
		opties.add(btnJokerVervangen);
		Button btnSteenNaarWerkveld = new Button("Steen naar werkveld brengen");
		opties.add(btnSteenNaarWerkveld);
		Button btnRijSplitsen = new Button("Rij splitsen");
		opties.add(btnRijSplitsen);
		Button btnReset = new Button("Reset tafel");
		opties.add(btnReset);

		this.optiesPaneel.add(btnBeurtBeëindigen, 0, 0);
		this.optiesPaneel.add(btnSteenAanleggen, 0, 1);
		this.optiesPaneel.add(btnJokerVervangen, 0, 2);
		this.optiesPaneel.add(btnRijSplitsen, 0, 3);
		this.optiesPaneel.add(btnReset, 0, 4);
		this.optiesPaneel.add(btnSteenNaarWerkveld, 0, 5);
		
		this.optiesPaneel.setVgap(5);
		opties.forEach(button -> button.setPrefWidth(200)); 
	}

}
