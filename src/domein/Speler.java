package domein;

import java.util.ArrayList;
import java.util.List;

public class Speler {
	private int id;
	private String gebruikersnaam;
	private String wachtwoord;
	private int score;
	private boolean isSpelerAanDeBeurt;
	private List<RummiSteen> stenenInBezit;
	private boolean isEersteBeurt;
	private boolean neemSteen;
	

	public Speler(int id, String gebruikersnaam, String wachtwoord , int score) {
		this.id = id;
		setGebruikersnaam(gebruikersnaam);
		setWachtwoord(wachtwoord);
		setScore(score);
		setIsEersteBeurt(true);
		setNeemSteen(true);
		stenenInBezit = new ArrayList<>();
	}
	
	public String getWachtwoord() {
		return wachtwoord;
	}
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	
	public boolean getNeemSteen() {
		return neemSteen;
	}
	
	public void setNeemSteen(boolean neemSteen) {
		this.neemSteen = neemSteen;
	}
	public void setIsEersteBeurt(boolean b) {
		this.isEersteBeurt = b;
	}
	public boolean getIsEersteBeurt() {
		return isEersteBeurt;
	}
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isSpelerAanDeBeurt() {
		return isSpelerAanDeBeurt;
	}
	public void setSpelerAanDeBeurt(boolean isSpelerAanDeBeurt) {
		this.isSpelerAanDeBeurt = isSpelerAanDeBeurt;
	}
	public List<RummiSteen> getStenenInBezit() {
		return stenenInBezit;
	}
	
	public void speelBeurt() {
		
	}
	
	public void krijgtSteen(RummiSteen steen) {
		stenenInBezit.add(steen);
	}
	
	public void verwijderSteen(RummiSteen steen) {
		int index = stenenInBezit.indexOf(steen);
		stenenInBezit.remove(index);
	}
	
	public String toonStenen() {
		String returnString = "";
			for (RummiSteen steen: stenenInBezit) {
				returnString +=  String.format("%s ",steen.toString());
			}
		return returnString;
	}
	
	public RummiSteen geefSteenMetNaam(String naam) {
		for(RummiSteen s : this.stenenInBezit) {
			if(s.getNaam().equals(naam)) return s;
		}
		return null;
	}
	
	
}
