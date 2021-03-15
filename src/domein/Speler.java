package domein;

public class Speler {
	private String gebruikersnaam;
	private String wachtwoord;
	
	public Speler(String gebruikersnaam, String wachtwoord) {
		setGebruikersnaam(gebruikersnaam);
		setWachtwoord(wachtwoord);
	}
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	public String getWachtwoord() {
		return wachtwoord;
	}
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
}
