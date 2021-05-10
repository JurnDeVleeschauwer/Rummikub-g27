package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.RummiSteen;
import domein.Speler;
import domein.Tafel;


class TafelTest {

	private Tafel t;
	@BeforeEach
	void before() {
		t = new Tafel();
	}
	@Test
	void maakTafel_maaktTafel() {
		Assertions.assertEquals("", t.getStenenOpTafel().get(0).get(0).getNaam());
	}
	@Test
	void legSteenOpTafel_geldigeParameters_legtSteenOpTafel() {
		RummiSteen r = new RummiSteen(25, "Groen");
		t.legSteenOpTafel(r, 6, 4);
		Assertions.assertEquals("Joker", t.getStenenOpTafel().get(6).get(4).getNaam());
	}
	@Test
	void legSteenOpTafel_ongeldigeRij_exception() {
		RummiSteen r = new RummiSteen(25, "Groen");
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> t.legSteenOpTafel(r, 11, 4));
	}
	@Test
	void legSteenOpTafel_ongeldigeKolom_exception() {
		RummiSteen r = new RummiSteen(25, "Groen");
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> t.legSteenOpTafel(r, 6, 13));
	}
	@Test
	void reset_geldig_resetDeTafel() {
		RummiSteen r = new RummiSteen(25, "Groen");
		Speler p = new Speler(69, "testSpeler", "testWachtwoord", 420);
		t.legSteenOpTafel(r, 6, 4);
		t.reset(new Tafel().getStenenOpTafel(), p);
		Assertions.assertEquals("", t.getStenenOpTafel().get(6).get(4).getNaam());
	}
	@Test
	void controleerTafel_geldigeStenenZonderJokers_returnTrue() {
		for (int i=1;i<=13;i++) {
				t.legSteenOpTafel(new RummiSteen(i,"Blauw"), 0, i-1);
		}
		t.legSteenOpTafel(new RummiSteen(7,"Blauw"), 1, 0);
		t.legSteenOpTafel(new RummiSteen(7,"Rood"), 1, 1);
		t.legSteenOpTafel(new RummiSteen(7,"Zwart"), 1, 2);
		t.legSteenOpTafel(new RummiSteen(7,"Geel"), 1, 3);
		Assertions.assertTrue(t.controleerTafel());
	}
	@Test
	void controleerTafel_geldigeStenenMetJokers_returnTrue() {
		for (int i=1;i<=13;i++) {
			if (i == 4)
				t.legSteenOpTafel(new RummiSteen(i,"Groen"), 0, i-1);
			else
				t.legSteenOpTafel(new RummiSteen(i,"Blauw"), 0, i-1);
		}
		t.legSteenOpTafel(new RummiSteen(7,"Groen"), 1, 0);
		t.legSteenOpTafel(new RummiSteen(7,"Rood"), 1, 1);
		t.legSteenOpTafel(new RummiSteen(7,"Zwart"), 1, 2);
		t.legSteenOpTafel(new RummiSteen(7,"Geel"), 1, 3);
		Assertions.assertTrue(t.controleerTafel());
	}
	@Test
	void controleerTafel_ongeldigeSteenInRij_returnFalse() {
		for (int i=1;i<=13;i++) {
			if (i == 4)
				t.legSteenOpTafel(new RummiSteen(i,"Rood"), 0, i-1);
			else
				t.legSteenOpTafel(new RummiSteen(i,"Blauw"), 0, i-1);
		}
		Assertions.assertFalse(t.controleerTafel());
	}
	@Test
	void controleerTafel_ongeldigeSteenInGroep_returnFalse() {
		t.legSteenOpTafel(new RummiSteen(7,"Rood"), 1, 0);
		t.legSteenOpTafel(new RummiSteen(7,"Rood"), 1, 1);
		t.legSteenOpTafel(new RummiSteen(7,"Zwart"), 1, 2);
		t.legSteenOpTafel(new RummiSteen(7,"Geel"), 1, 3);
		Assertions.assertFalse(t.controleerTafel());
	}
	@Test
	void controleerTafel_ongeldigAantalStenen_returnFalse() {
		t.legSteenOpTafel(new RummiSteen(7,"Zwart"), 1, 0);
		t.legSteenOpTafel(new RummiSteen(7,"Geel"), 1, 1);
		Assertions.assertFalse(t.controleerTafel());
	}
	
}
