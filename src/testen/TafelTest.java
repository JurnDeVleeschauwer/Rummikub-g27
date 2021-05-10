package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.RummiSteen;
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
	void legSteenOpTafel_ongeldigeRij_legtSteenOpTafel() {
		RummiSteen r = new RummiSteen(25, "Groen");
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> t.legSteenOpTafel(r, 11, 4));
	}
	@Test
	void legSteenOpTafel_ongeldigeKolom_legtSteenOpTafel() {
		RummiSteen r = new RummiSteen(25, "Groen");
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> t.legSteenOpTafel(r, 6, 13));
	}
	
}
