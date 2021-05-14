package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.RummiSteen;

class RummiSteenTest {

	private RummiSteen r;
	
	@Test
	void maakRummiSteen_legeConstructor_maaktRummiSteen() {
		r = new RummiSteen();
		Assertions.assertEquals(0, r.getWaarde());
		Assertions.assertEquals("", r.getKleur());
		Assertions.assertEquals("", r.getNaam());
	}
	@Test
	void maakRummiSteen_geldigeParameters_maaktRummiSteen() {
		r = new RummiSteen(5,"Blauw");
		Assertions.assertEquals(5, r.getWaarde());
		Assertions.assertEquals("Blauw", r.getKleur());
		Assertions.assertEquals("Blauw5", r.getNaam());
	}
	@Test
	void maakRummiSteen_geldigeParametersJoker_maaktRummiSteen() {
		r = new RummiSteen(25,"Groen");
		Assertions.assertEquals(25, r.getWaarde());
		Assertions.assertEquals("Groen", r.getKleur());
		Assertions.assertEquals("Joker", r.getNaam());
	}
	@Test
	void toString_geldigeSteen_geeftKorteString() {
		r = new RummiSteen(5,"Blauw");
		Assertions.assertEquals("5\nB", r.toString());
	}
	@Test
	void toString_geldigeSteenJoker_geeftKorteString() {
		r = new RummiSteen(25,"Groen");
		Assertions.assertEquals("J", r.toString());
	}

}
