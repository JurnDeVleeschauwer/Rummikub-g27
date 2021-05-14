package domein;

import java.util.ArrayList;
import java.util.List;

public class Tafel {
	private List<ArrayList<RummiSteen>> stenenOpTafel;

	/** Maakt een tafel aan van 11 op 13.
	 */
	public Tafel() {
		this.stenenOpTafel= new ArrayList<ArrayList<RummiSteen>>(11);
		for(int i=0; i <11 ; i++) {
		    ArrayList arr = new ArrayList<RummiSteen>(13);
		    for(int a=0; a <13 ; a++) {
		    	arr.add(new RummiSteen());
		    }
			stenenOpTafel.add(arr);
		    
		}
		
		
	}
	
	/** Voegt een steen toe aan de tafel in een specifieke rij en kolom
	 * @param steen de specifieke steen die de speler wil toevoegen
	 * @param rij de rij waarin de speler de steen wil toevoegen
	 * @param kolom de kolom waarin de speler de steen wil toevoegen */ 
	public void legSteenOpTafel(RummiSteen steen, int rij, int kolom) {
		this.stenenOpTafel.get(rij).set(kolom, steen);
	}
	
	/** Geeft de stenen die op de tafel liggen
	 * @return de array van de stenen op tafel */
	public List<ArrayList<RummiSteen>> getStenenOpTafel() {
		return stenenOpTafel;
	}
	
	/** reset de tafel naar de beginpositie
	 * @param stenenVasteTafel de stenen aawezig op tafel voor de reset
	 * @param spelerAanZet de huidige speler die aan de beurt is
	 */
	public void reset(List<ArrayList<RummiSteen>> stenenVasteTafel, Speler spelerAanZet) {		
		List<ArrayList<RummiSteen>> stenenOpTafel = new ArrayList<>(10);
		for(int i=0; i <11 ; i++) {
		    stenenOpTafel.add(new ArrayList());
		}
		
		int tellerRij = 0;
		for (List<RummiSteen> l:stenenVasteTafel) {
			for (RummiSteen s: l) {
				stenenOpTafel.get(tellerRij).add(s);
			}
			tellerRij++;
		}
		this.stenenOpTafel = stenenOpTafel;
		
	}

	/** Controleert of de stenen op tafel liggen volgens de regels
	 * @return true of false indien tafel juist of fout is
	 */
	public boolean controleerTafel() {
		for (List<RummiSteen> steenGroep:stenenOpTafel) {
			RummiSteen vorigeSteen = null;
			List<String> kleuren = new ArrayList<>();
			int laatsteWaarde = 0;
			String laatsteKleur = "";
			int aantalStenen =0;
			boolean straat = false;
			boolean gelijkeNummers = false;
			for (RummiSteen steen: steenGroep) {
				if(steen.getWaarde()!=0) {
					if (straat == true | gelijkeNummers == true) {
						if (straat) {
							if (steen.getKleur().equals(vorigeSteen.getKleur()) & steen.getWaarde() == vorigeSteen.getWaarde()+1) {
								laatsteWaarde = steen.getWaarde();
								laatsteKleur = steen.getKleur();
							}
							else if (vorigeSteen.getKleur().equals("Groen")|steen.getKleur().equals("Groen")) {
								if (steen.getKleur().equals(laatsteKleur) & steen.getWaarde() == laatsteWaarde+2) {
									laatsteWaarde = steen.getWaarde();
									laatsteKleur = steen.getKleur();
								}
							}
							else {
								return false;
							}
						}
						if (gelijkeNummers) {
							if (aantalStenen<4) {
								if ((steen.getWaarde() == vorigeSteen.getWaarde() & !(kleuren.contains(steen.getKleur())))) {
									kleuren.add(steen.getKleur());
									laatsteWaarde = steen.getWaarde();
								}
								else if (vorigeSteen.getKleur().equals("Groen")|steen.getKleur().equals("Groen")) {
									if (steen.getWaarde() == laatsteWaarde & !(kleuren.contains(steen.getKleur()))) {
										laatsteWaarde = steen.getWaarde();
										laatsteKleur = steen.getKleur();
									}
								}
								else {
									return false;
								}
							}
							else {
								return false;
							}
						}
					}
					else if (!(vorigeSteen == null)) {
						if (!(vorigeSteen.getKleur().equals("Groen")|steen.getKleur().equals("Groen"))) {
							if (steen.getKleur().equals( vorigeSteen.getKleur()) & steen.getWaarde() == vorigeSteen.getWaarde()+1) {
								laatsteWaarde = steen.getWaarde();
								laatsteKleur = steen.getKleur();
								straat = true;
							}
							else if ((steen.getWaarde() == vorigeSteen.getWaarde() & !(kleuren.contains(steen.getKleur())))&aantalStenen<=4) {
								kleuren.add(steen.getKleur());
								gelijkeNummers = true;
							}
							else {
								return false;
							}
						}
					}
					else {
						kleuren.add(steen.getKleur());
					}
					vorigeSteen = steen;
					aantalStenen++;
				}
				else if(aantalStenen!=0 & aantalStenen < 3) {
					return false;
					
				}
				else if(aantalStenen >=3) {
					vorigeSteen = null;
					kleuren = new ArrayList<>();
					laatsteWaarde = 0;
					laatsteKleur = "";
					aantalStenen =0;
					straat = false;
					gelijkeNummers = false;
				}
			}
				
			if (aantalStenen!=0 & aantalStenen < 3) {
				return false;
			}
		}
		return true;
	}
	
	/** Retourneert een bepaalde steen met een naam indien die op tafel aanwezig is
	 * @param naam kleur en waarde van de steen
	 * @return de steen indien die op tafel ligt null indien niet aanwezig
	 */
	public RummiSteen geefSteenMetNaam(String naam) {
		for(List<RummiSteen> r : this.stenenOpTafel) {
			for(RummiSteen s : r) {
				if(s.getNaam().equals(naam)) return s;
			}
		}
		return null;
		
	}
	
	/** Verwijdert een bepaalde steen van de tafel
	 * @param steen de steen die uit de tafel moet
	 */
	public void verwijderSteen(RummiSteen steen) {
		for(List<RummiSteen> r : this.stenenOpTafel) {
			for(RummiSteen s : r) {
				if(s.equals(steen)) {
					r.add(r.indexOf(s), new RummiSteen());
					r.remove(s);
					
					break;
				}
			}
		}
	}
	/** Verwijdert een steen van een bepaalde plaats op tafel
	 * @param Xindex de kolom waarin de steen zich bevind
	 * @param Yindex de rij waarin de steen zich bevind
	 * @return de net verwijderde steen
	 */
	public RummiSteen verwijderSteen(int Xindex, int Yindex) { 
		RummiSteen steen = this.stenenOpTafel.get(Yindex).remove(Xindex);
		this.stenenOpTafel.get(Yindex).add(Xindex, new RummiSteen());
		return steen;
	}
	

}
