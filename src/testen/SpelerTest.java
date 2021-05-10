package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Speler;

class SpelerTest {

	private Speler p;
	@BeforeEach
	void before() {
		p = new Speler(69, "testSpeler", "testWachtwoord", 420);
	}
	@Test
	void maakSpeler_geldigeParameters_maaktSpeler() {
		Assertions.assertEquals(69, p.getID());
		Assertions.assertEquals("testSpeler", p.getGebruikersnaam());
		Assertions.assertEquals("testWachtwoord", p.getWachtwoord());
		Assertions.assertEquals(420, p.getScore());
		Assertions.assertEquals(true, p.getIsEersteBeurt());
		Assertions.assertEquals(true, p.getNeemSteen());
	}

}
