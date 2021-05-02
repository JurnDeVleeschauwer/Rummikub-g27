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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
    
    
	
    private Button btnBeurtBeëindigen = new Button("Beurt beëindigen");
	private Button btnSteenAanleggen = new Button("Steen aanleggen");
	private Button btnJokerVervangen = new Button("Joker vervangen");
	private Button btnSteenNaarWerkveld = new Button("Steen naar werkveld brengen");
	private Button btnRijSplitsen = new Button("Rij splitsen");
	private Button btnReset = new Button("Reset tafel");
	private Label lbl = new Label();
	
    
    
	
    public SpelPaneel(DomeinController domeinController, HoofdPaneel hoofdPaneel)
	    {
	        this.domeinController = domeinController;
	        this.hoofdPaneel = hoofdPaneel;
	        this.lbl.setTextFill(Color.WHITE);
	        this.resetLabel();
	        this.tafelPaneel = new GridPane();
	        for(int i=0; i<11; i++) {
	        	for(int a=0; a<13; a++) {
		        	Button legeButton = new Button();
		        	String str = "";
		        	str=str + i + "," + a;
		        	legeButton.setId(str);
		        	legeButton.setMinSize(50, 70);
		        	legeButton.setMaxSize(50, 70);
		        	legeButton.setPrefSize(50,70);
		        	legeButton.setPadding(new Insets(0, 4, 20, 0));
		        	legeButton.setOnAction(this::opLegePlaatsGeklikt);
		        	this.tafelPaneel.add(legeButton, a, i);
		        }
	        }
	        
	        this.werkveldPaneel = new GridPane();
	        this.spelerPaneel = new GridPane();
	        this.optiesPaneel = new GridPane();
	        this.lbl.setFont(new Font(20));
	        
	        stenenOpTafelLeggen();
	        werkveldLeggen();
			spelerStenenGeven();
		    optiesGeven();
	        configureerGrid();
	        voegComponentenToe();
	        

			btnBeurtBeëindigen.setOnAction(this::beurtBeëindigen);
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
				if (!(steen.getKleur().equals(""))) {
					Button btnSteen = this.vanSteenEenButtonMaken(steen);
				this.tafelPaneel.add(btnSteen, XIndex, YIndex);
				}
				XIndex++;
				
			}
				YIndex++;
				XIndex=0;
		}
		
	}

	private void voegComponentenToe() {
		add(this.tafelPaneel, 0, 0, 1, 4);
		add(this.werkveldPaneel, 1, 0);
		add(this.spelerPaneel, 1, 1, 2, 1);
		add(this.optiesPaneel, 2, 0);
		add(this.lbl,1, 2 );
		
	}
	
	private void reloadScherm() {
		spelerStenenGeven();
		stenenOpTafelLeggen();
		werkveldLeggen();
		resetLabel();
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

        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResourceAsStream("/images/Achtergrond_Poker.jpg")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                  BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
		
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
		btnSteen.setId(naam);
		btnSteen.setOnAction(this::opSteenGeklikt);
		return btnSteen;
	}
	
	private void spelerStenenGeven() {
		this.spelerPaneel.getChildren().clear();
		String label = domeinController.getSpel().getSpelerAanZet().getGebruikersnaam()+ " is aan de beurt";
		Label lblSpelerAanZet = new Label(label);
		lblSpelerAanZet.setTextFill(Color.WHITE);
		this.spelerPaneel.add(lblSpelerAanZet, 0, 0, 5, 1);
		List<RummiSteen> stenen = new ArrayList<>();
		stenen = this.domeinController.getSpel().getSpelerAanZet().getStenenInBezit();
		int index = 0;
		
		for(RummiSteen steen : stenen) {
			Button btnSteen = this.vanSteenEenButtonMaken(steen);
			if(index < (stenen.size()%2 == 0? stenen.size()/2 : stenen.size()/2+1)) {
				this.spelerPaneel.add(btnSteen, index, 1);
			}else
				this.spelerPaneel.add(btnSteen, index-(stenen.size()%2 == 0? stenen.size()/2 : stenen.size()/2+1), 2);
			index++;
		}
	}
	
	private void opSteenGeklikt(ActionEvent event) {
		Button btn = (Button) event.getSource();
		String naam = btn.getId();
		steenOmAanTeLeggenIsGekozen(naam);
	}
	
	private void opLegePlaatsGeklikt(ActionEvent event) {
		Button btn = (Button) event.getSource();
		String positie = btn.getId();
		if(positie.contains(",")) {
			steenEnRijZijnGekozenOmAanTeLeggen(positie);

		}
	}
	
	private void resetLabel() {
		lbl.setText("Kies een actie");
		lbl.setId("actieKiezen");
	}
	
	private void optiesGeven() {
		List<Button> opties = new ArrayList<>();
		opties.add(btnBeurtBeëindigen);
		opties.add(btnSteenAanleggen);
		opties.add(btnJokerVervangen);
		opties.add(btnSteenNaarWerkveld);
		opties.add(btnRijSplitsen);
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

	private void beurtBeëindigen(ActionEvent event) {
		domeinController.beeindigBeurt();
		reloadScherm();
	}
	private void jokerVervangen(ActionEvent event) {
		domeinController.jokerVervangen();
	}
	private void steenOmAanTeLeggenIsGekozen(String naam) {
		if(lbl.getId().equals("steenKiezen") || lbl.getId().equals("rijKiezen")) {
			
			lbl.setText("Kies nu waar je "+ naam + " wilt leggen");	
			lbl.setId("rijKiezen");
			
		}
		
	}
	private void steenEnRijZijnGekozenOmAanTeLeggen(String positie) {
		if(lbl.getId().equals("rijKiezen")) {
			String[] str = lbl.getText().split(" ");
			String naam = str[4];
			String label = domeinController.steenAanleggen(naam, positie);
			reloadScherm();
			if(!(label == null)) {
				lbl.setText(label+", kies een actie");
			} 
				
			
		}
	}
	private void steenAanleggen(ActionEvent event) {
		lbl.setText("Klik op de steen die je wilt leggen");
		lbl.setId("steenKiezen");
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
