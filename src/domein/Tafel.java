package domein;

import java.util.ArrayList;
import java.util.List;

public class Tafel {
	private List<ArrayList<RummiSteen>> stenenOpTafel;

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
	public void legSteenOpTafel(RummiSteen steen, int rij) {
		this.stenenOpTafel.get(rij).add(steen);
	}
	
	public void legSteenOpTafel(RummiSteen steen, int rij, int kolom) {
		this.stenenOpTafel.get(rij).set(kolom, steen);
	}
	
	public List<ArrayList<RummiSteen>> getStenenOpTafel() {
		return stenenOpTafel;
	}

	public void reset(List<ArrayList<RummiSteen>> stenenVasteTafel, Speler spelerAanZet) {
//		for (List<RummiSteen> l:this.stenenOpTafel) {
//			for (RummiSteen s: l) {
//				spelerAanZet.krijgtSteen(s);
//			}
//		}
		
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
	
	public String toonStenen() {
		String returnString = "";
		for (List<RummiSteen> steenGroep:stenenOpTafel) {
			for (RummiSteen steen: steenGroep) {
				if (steen != null)
					returnString +=  String.format("%s, ",steen.toString());
			}
			returnString += String.format("%n");
		}
		return returnString;
	}

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
				if (straat == true | gelijkeNummers == true) {
					if (straat) {
						if (steen.getKleur() == vorigeSteen.getKleur() & steen.getWaarde() == vorigeSteen.getWaarde()+1) {
							laatsteWaarde = steen.getWaarde();
							laatsteKleur = steen.getKleur();
						}
						else if (vorigeSteen.getKleur()=="Groen"|steen.getKleur()=="Groen") {
							if (steen.getKleur() == laatsteKleur & steen.getWaarde() == laatsteWaarde+2) {
								laatsteWaarde = steen.getWaarde();
								laatsteKleur = steen.getKleur();
							}
						}
						else {
							return false;
						}
					}
					if (gelijkeNummers) {
						if (aantalStenen<=4) {
							if ((steen.getWaarde() == vorigeSteen.getWaarde() & !(kleuren.contains(steen.getKleur())))) {
								kleuren.add(steen.getKleur());
								laatsteWaarde = steen.getWaarde();
							}
							else if (vorigeSteen.getKleur()=="Groen"|steen.getKleur()=="Groen") {
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
					if (!(vorigeSteen.getKleur()=="Groen"|steen.getKleur()=="Groen")) {
						if (steen.getKleur() == vorigeSteen.getKleur() & steen.getWaarde() == vorigeSteen.getWaarde()+1) {
							laatsteWaarde = steen.getWaarde();
							laatsteKleur = steen.getKleur();
							straat = true;
						}
						else if ((steen.getWaarde() == vorigeSteen.getWaarde() & !(kleuren.contains(steen.getKleur())))||aantalStenen<=4) {
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
			if (!steenGroep.isEmpty() & aantalStenen < 3) {
				return false;
			}
		}
		return true;
	}
	public RummiSteen geefSteenMetNaam(String naam) {
		for(List<RummiSteen> r : this.stenenOpTafel) {
			for(RummiSteen s : r) {
				if(s.getNaam().equals(naam)) return s;
			}
		}
		return null;
		
	}
	
	public void verwijderSteen(RummiSteen steen) {
		for(List<RummiSteen> r : this.stenenOpTafel) {
			for(RummiSteen s : r) {
				if(s.equals(steen)) {
					r.remove(s);
					break;
				}
			}
		}
	}

}
