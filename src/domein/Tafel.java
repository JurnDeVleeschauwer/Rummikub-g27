package domein;

import java.util.ArrayList;
import java.util.List;

public class Tafel {
	private List<List<RummiSteen>> stenenOpTafel;

	public Tafel() {
		this.stenenOpTafel= new ArrayList<>(10);
		for(int i=0; i <10 ; i++) {
		    stenenOpTafel.add(new ArrayList());
		}
		
		
	}
	
	public void legSteenOpTafel(RummiSteen steen, int rij) {
		this.stenenOpTafel.get(rij).add(steen);
	}
	
	public List<List<RummiSteen>> getStenenOpTafel() {
		return stenenOpTafel;
	}

	public void reset(List<List<RummiSteen>> stenenVasteTafel, Speler spelerAanZet) {
		for (List<RummiSteen> l:this.stenenOpTafel) {
			for (RummiSteen s: l) {
				spelerAanZet.krijgtSteen(s);
			}
		}
		
		List<List<RummiSteen>> stenenOpTafel = new ArrayList<>(10);
		for(int i=0; i <10 ; i++) {
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
				returnString +=  String.format("%s, ",steen.toString());
			}
			returnString += String.format("%n");
		}
		return returnString;
	}

	public boolean controleerTafel() {
//		for (List<RummiSteen> steenGroep:stenenOpTafel) {
//			for (RummiSteen steen: steenGroep) {
//				
//			}
//		}
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
