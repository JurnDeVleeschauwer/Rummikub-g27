package domein;

public class Speler {
	private String gebruikersnaam;
	private String wachtwoord;
	
	public Speler(String gebruikersnaam, String wachtwoord) {
		setGebruikersnaaam(gebruikersnaam);
		setWachtwoord(wachtwoord);
	}
	public String getGebruikersnaaam() {
		return gebruikersnaam;
	}
	public String getWachtwoord() {
		return wachtwoord;
	}
	public void setGebruikersnaaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
}
