package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pot {
	private List<RummiSteen> stenen;
	
	public Pot() {
		String[] kleuren = {"Rood","Zwart","Blauw","Geel"};
		stenen = new ArrayList<RummiSteen>(106);
		for (int j=0;j<4;j++) {
			for (int i=1;i<=13;i++) {
				stenen.add(new RummiSteen(i,kleuren[j]));
			}
		}
		for (int i=0;i<2;i++) {
			stenen.add(new RummiSteen(25,"Groen")); //Groen = kleur van Joker
		}
		Collections.shuffle(stenen);
	}
	
	public RummiSteen verwijderSteen() {
		RummiSteen steen = stenen.get(0);
		stenen.remove(0);
		return steen;
	}

}
