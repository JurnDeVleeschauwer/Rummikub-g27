package gui;

import domein.DomeinController;
import domein.Speler;
import i18n.UITextHelper;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

	}

	  private void voegComponentenToe() {
		  maakTableView();
	 
		}
	  
	    /**
	     * maakt het table view van ListSpellenPaneel
	     */
	  public void maakTableView() {
        tableView = new TableView();

    
        TableColumn<Speler, String> column1 = new TableColumn<>("gebruikersnaam");
        column1.setCellValueFactory(new PropertyValueFactory<>("gebruikersnaam"));


        TableColumn<Speler, Integer> column2 = new TableColumn<>("score");
        column2.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        
       
        
        for(Speler speler : domeinController.getSpelers()) {
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
