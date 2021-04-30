package gui;

import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import domein.RummiSteen;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SpelPaneel extends GridPane {
	
	private final DomeinController domeinController;
    private final HoofdPaneel hoofdPaneel;
    private GridPane tafelPaneel;
    private GridPane werkveldPaneel;
    private GridPane spelerPaneel;
    private GridPane optiesPaneel;
    
    
	
    private Button btnBeurtBe�indigen = new Button("Beurt be�indigen");
	private Button btnSteenAanleggen = new Button("Steen aanleggen");
	private Button btnJokerVervangen = new Button("Joker vervangen");
	private Button btnSteenNaarWerkveld = new Button("Steen naar werkveld brengen");
	private Button btnRijSplitsen = new Button("Rij splitsen");
	private Button btnReset = new Button("Reset tafel");
	private Label lbl = new Label("Kies een actie");
    
    
	
    public SpelPaneel(DomeinController domeinController, HoofdPaneel hoofdPaneel)
	    {
	        this.domeinController = domeinController;
	        this.hoofdPaneel = hoofdPaneel;
	        this.tafelPaneel = new GridPane();
	        this.werkveldPaneel = new GridPane();
	        this.spelerPaneel = new GridPane();
	        this.optiesPaneel = new GridPane();
	        
	        
//<<<<<<< HEAD=======>>>>>>> 108a5ac (OVerzicht sort, aanmelden controleerspeler verbetert, except en taaal)
	        stenenOpTafelLeggen();
	        werkveldLeggen();
			spelerStenenGeven();
		    optiesGeven();
	        configureerGrid();
	        voegComponentenToe();
	        

			btnBeurtBe�indigen.setOnAction(this::beurtBe�indigen);
			btnSteenAanleggen.setOnAction(this::steenAanleggen);
			btnJokerVervangen.setOnAction(this::jokerVervangen);
			btnSteenNaarWerkveld.setOnAction(this::steenNaarWerkveld);
			btnRijSplitsen.setOnAction(this::rijSplitsen);
			btnReset.setOnAction(this::reset);
			
			
	    }
    
    private void werkveldLeggen() {
    	int XIndex=0;
		for(RummiSteen steen : domeinController.getSpel().getWerkveld()) {
			Button btnSteen = this.vanSteenEenButtonMaken(steen);
			this.werkveldPaneel.add(btnSteen, XIndex, 0);
			XIndex++;
		}
		XIndex=0;
    	
    }

	private void stenenOpTafelLeggen() {
		int XIndex=0;
		int YIndex=0;
		for(List<RummiSteen> rij : domeinController.getSpel().getTijdelijkeTafel().getStenenOpTafel()) {
			for(RummiSteen steen : rij) {
				Button btnSteen = this.vanSteenEenButtonMaken(steen);
				this.tafelPaneel.add(btnSteen, XIndex, YIndex);
				XIndex++;
			}
//ik wil 2 rijen steentjes op dezelfde rij laten zien met wat ruimte tussen, het lukt me niet
			
//			if (domeinController.getSpel().getTijdelijkeTafel().getStenenOpTafel().indexOf(rij) %2!=0) {
				YIndex++;
				XIndex=0;
//			}
//			else {
//				Label leegLbl = new Label("");
//				this.tafelPaneel.add(leegLbl, XIndex, YIndex);
//				this.tafelPaneel.add(leegLbl, XIndex+1, YIndex);
//				this.tafelPaneel.add(leegLbl, XIndex+2, YIndex);
//				XIndex+=3;
//			}

				
			
		}
		
	}

	private void voegComponentenToe() {
		add(this.tafelPaneel, 0, 0);
		add(this.werkveldPaneel, 1, 0);
		add(this.spelerPaneel, 0, 1);
		add(this.optiesPaneel, 1, 1);
		add(this.lbl,1, 2 );
		
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
	
	private Button vanSteenEenButtonMaken(RummiSteen steen) {
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
		btnSteen.setFont(new Font(10));
		btnSteen.setMinSize(50, 70);
		btnSteen.setMaxSize(50, 70);
		btnSteen.setPrefSize(50,70);
		btnSteen.setPadding(new Insets(0, 4, 20, 0));
		btnSteen.setOnAction(this::opSteenGeklikt);
		return btnSteen;
	}
	
	private void spelerStenenGeven() {
		this.spelerPaneel.getChildren().clear();
		String label = domeinController.getSpel().getSpelerAanZet().getGebruikersnaam()+ " is aan de beurt";
		Label spelerAanZet = new Label(label);
		this.spelerPaneel.add(spelerAanZet, 0, 0, 5, 1);
		List<RummiSteen> stenen = new ArrayList<>();
		stenen = this.domeinController.getSpel().getSpelerAanZet().getStenenInBezit();
		int index = 0;
		
		for(RummiSteen steen : stenen) {
			Button btnSteen = this.vanSteenEenButtonMaken(steen);
			if(index < stenen.size()/2) {
				this.spelerPaneel.add(btnSteen, index, 1);
			}else
				this.spelerPaneel.add(btnSteen, index-(stenen.size()/2), 2);
			index++;
		}
	}
	
	private RummiSteen opSteenGeklikt(ActionEvent event) { //geen idee hoe ik dit laat werken
		return null;
	}
	
	private void optiesGeven() {
		List<Button> opties = new ArrayList<>();
		opties.add(btnBeurtBe�indigen);
		opties.add(btnSteenAanleggen);
		opties.add(btnJokerVervangen);
		opties.add(btnSteenNaarWerkveld);
		opties.add(btnRijSplitsen);
		opties.add(btnReset);
		
		this.optiesPaneel.add(btnBeurtBe�indigen, 0, 0);
		this.optiesPaneel.add(btnSteenAanleggen, 0, 1);
		this.optiesPaneel.add(btnJokerVervangen, 0, 2);
		this.optiesPaneel.add(btnRijSplitsen, 0, 3);
		this.optiesPaneel.add(btnReset, 0, 4);
		this.optiesPaneel.add(btnSteenNaarWerkveld, 0, 5);
		
		this.optiesPaneel.setVgap(5);
		opties.forEach(button -> button.setPrefWidth(200)); 
	}

	private void beurtBe�indigen(ActionEvent event) {
		domeinController.beeindigBeurt();
		spelerStenenGeven();
	}
	private void jokerVervangen(ActionEvent event) {
		domeinController.jokerVervangen();
	}
	private void steenAanleggen(ActionEvent event) {
		lbl.setText("Klik op de steen die je wilt leggen");
		List<Button> btns;
		for(int i=0;i<this.spelerPaneel.getChildren().size();i++) {
			btns.add((Button) this.spelerPaneel.getChildren().get(i));
		}
		Button btn = 
		domeinController.steenAanleggen();
	}
	private void steenNaarWerkveld(ActionEvent event) {
		domeinController.steenNaarWerkveld();
	}
	private void rijSplitsen(ActionEvent event) {
		domeinController.rijSplitsen();
	}
	private void reset(ActionEvent event) {
		domeinController.reset();
	}

}