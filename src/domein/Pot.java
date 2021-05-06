package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pot {
	private List<RummiSteen> stenen;
	
	/** maakt pot aan met 106 door elkaar geschudde Rummistenen.
	 */
	public Pot() {
		String[] kleuren = {"Rood","Zwart","Blauw","Geel"};
		stenen = new ArrayList<RummiSteen>(106);
		for (int j=0;j<4;j++) {
			for (int i=1;i<=13;i++) {
				stenen.add(new RummiSteen(i,kleuren[j]));
				stenen.add(new RummiSteen(i,kleuren[j]));
			}
		}
		
		stenen.add(new RummiSteen(25,"Groen")); //Groen = kleur van Joker
		stenen.add(new RummiSteen(26,"Groen")); //Groen = kleur van Joker
		
		Collections.shuffle(stenen);
	}
	
	/** Vraagt de lijst op met alle stenen in de pot
	 * @return alle stenen in de pot
	 */
	public List<RummiSteen> getStenen() {
		return stenen;
	}
	/** Verwijdert eerste steen uit de pot.
	 * @return geeft de verwijderde steen.
	 */
	public RummiSteen verwijderSteen() {
		RummiSteen steen = stenen.get(0);
		stenen.remove(0);
		return steen;
	}

}
