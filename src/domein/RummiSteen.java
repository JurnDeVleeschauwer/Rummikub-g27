package domein;

public class RummiSteen {
	private final int waarde;
	private final String kleur;
	private final String naam;

	public RummiSteen(int waarde, String kleur) {
		this.waarde=waarde;
		this.kleur=kleur;
		if (kleur=="Groen")
			this.naam="Joker";
		else
			this.naam=String.format("%s%d",kleur,waarde);
	}

	public int getWaarde() {
		return waarde;
	}

	public String getKleur() {
		return kleur;
	}
	
	public String getNaam() {
		return naam;
	}

	@Override
	public String toString() {
		return naam;
	}
	
	
}
