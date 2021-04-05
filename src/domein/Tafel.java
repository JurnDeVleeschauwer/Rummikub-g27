package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tafel {
	private List<List<RummiSteen>> stenenOpTafel;

	public Tafel() {
		this.stenenOpTafel= new ArrayList<List<RummiSteen>>();
		
	}
	
	public void legSteenOpTafel(RummiSteen steen) {
		this.stenenOpTafel.add(Arrays.asList(steen));
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
