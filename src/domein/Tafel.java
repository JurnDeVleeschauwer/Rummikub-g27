package domein;

import java.util.ArrayList;
import java.util.List;

public class Tafel {
	private List<List<RummiSteen>> stenenOpTafel;

	public Tafel() {
		stenenOpTafel= new ArrayList<>();
		
	}
	
	public void legSteenOpTafel(RummiSteen steen) {
		stenenOpTafel.get(0).add(steen);
	}
	
	public List<List<RummiSteen>> getStenenOpTafel() {
		return stenenOpTafel;
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

}
