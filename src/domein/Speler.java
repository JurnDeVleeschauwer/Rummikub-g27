package domein;

import java.util.ArrayList;
import java.util.List;

public class Speler {
	private String gebruikersnaam;
	private String wachtwoord;
	private int score;
	private boolean isSpelerAanDeBeurt;
	private List<RummiSteen> stenenInBezit;
	private boolean isEersteBeurt;
	private boolean neemSteen;
	

	public Speler(String gebruikersnaam, String wachtwoord) {
		setGebruikersnaam(gebruikersnaam);
		setWachtwoord(wachtwoord);
		setScore(0);
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
	
	public RummiSteen verwijderSteen() {
		RummiSteen steen = stenenInBezit.get(0);
		stenenInBezit.remove(0);
		return steen;
	}
	
	public String toonStenen() {
		String returnString = "";
			for (RummiSteen steen: stenenInBezit) {
				returnString +=  String.format("%s ",steen.toString());
			}
		return returnString;
	}
	
}
