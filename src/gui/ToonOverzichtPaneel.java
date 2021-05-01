package gui;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import domein.DomeinController;
import domein.Speler;
import i18n.UITextHelper;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Jurn
 *
 */
public class ToonOverzichtPaneel extends GridPane {
	private final DomeinController domeinController;
	private final HoofdPaneel hoofdPaneel;
	private TableView<Speler> tableView;

	/**
	 * 
	 * @param domeinController
	 * @param hoofdPaneel
	 */
	public ToonOverzichtPaneel(DomeinController domeinController, HoofdPaneel hoofdPaneel) {
		this.domeinController = domeinController;
		this.hoofdPaneel = hoofdPaneel;

		configureerGrid();
		voegComponentenToe();
	}

	private void configureerGrid() {
		setPadding(new Insets(10));
		setHgap(2);
		setVgap(2);
		this.setAlignment(Pos.CENTER);
		
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResourceAsStream("/images/Achtergrond_Poker.jpg")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                  BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));

	}

	  private void voegComponentenToe() {
		  maakTableView();
	 
		}
	  
	    /**
	     * maakt het table view van ListSpellenPaneel
	     */
	  public void maakTableView() {
        tableView = new TableView();

    
        TableColumn<Speler, String> column1 = new TableColumn<>(UITextHelper.UIText("gebruikersnaam"));
        column1.setCellValueFactory(new PropertyValueFactory<>(UITextHelper.UIText("gebruikersnaam")));


        TableColumn<Speler, Integer> column2 = new TableColumn<>(UITextHelper.UIText("score"));
        column2.setCellValueFactory(new PropertyValueFactory<>(UITextHelper.UIText("score")));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        List<Speler> spelers = domeinController.getSpelers();
        
        Collections.sort(spelers, new Comparator<Speler>() {
		    public int compare(Speler o1, Speler o2) {
		        if(0 != -(Integer.compare(o1.getScore(), o2.getScore()))) {
		        	System.out.println(Integer.compare(o1.getScore(), o2.getScore()));
		        	return -(Integer.compare(o1.getScore(), o2.getScore()));
		        }else {
		        	return o1.getGebruikersnaam().compareTo(o2.getGebruikersnaam());
		          
		    }
		    }
		});
        
        
        for(Speler speler : spelers) {
        	tableView.getItems().add(speler);
        }

        add(tableView, 0, 0);
        
        Button exit = new Button(UITextHelper.UIText("exit"));
        exit.setOnAction(this::exit);
        add(exit, 5, 3);
	  }
	    
	  
	  private void exit(ActionEvent event) {
	          hoofdPaneel.toonHoofdMenu();;
	    }
	  
	  

}
