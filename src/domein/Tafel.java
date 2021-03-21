package domein;

import java.util.ArrayList;
import java.util.List;

public class Tafel {
	private List<RummiSteen> stenenOpTafel;

	public Tafel() {
		stenenOpTafel= new ArrayList<>();
		
	}
	
	public void legSteenOpTafel(RummiSteen steen) {
		stenenOpTafel.add(steen);
	}

}
