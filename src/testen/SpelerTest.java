package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.RummiSteen;
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
	@Test
	void verwijderSteen_steen_spelerKrijgtSteen() { 
		RummiSteen r = new RummiSteen(25, "Groen");
		p.krijgtSteen(r);
		Assertions.assertEquals(1, p.getStenenInBezit().size());
		p.verwijderSteen(r);
		Assertions.assertEquals(0, p.getStenenInBezit().size());
	}
	@Test
	void geefSteenMetNaam_bevatSteen_geeftSteenTerug() {
		RummiSteen r = new RummiSteen(25, "Groen");
		p.krijgtSteen(r);
		Assertions.assertEquals(r, p.geefSteenMetNaam("Joker"));
	}
	@Test
	void geefSteenMetNaam_bevatSteenNiet_geeftSteenTerug() {
		Assertions.assertEquals(null, p.geefSteenMetNaam("Joker"));
	}
	@Test
	void kopieInstellenEnReset_resetStenenSpeler() {
		RummiSteen r = new RummiSteen(25, "Groen");
		p.krijgtSteen(r);
		p.kopieInstellen();
		p.verwijderSteen(r);
		p.reset();
		Assertions.assertEquals(r, p.geefSteenMetNaam("Joker"));
	}
	@Test
	void eersteBeurt_isNietEersteBeurt_returnTrue() {
		p.setIsEersteBeurt(false);
		Assertions.assertTrue(p.eersteBeurt());
	}
	@Test
	void eersteBeurt_isEersteBeurtLegtGeenStenen_returnFalse() {
		Assertions.assertFalse(p.eersteBeurt());
	}
	@Test
	void eersteBeurt_isEersteBeurtLegtMeerDan30_returnTrue() {
		RummiSteen[] Stenen = {new RummiSteen(11, "Blauw"),new RummiSteen(12, "Blauw"),new RummiSteen(13, "Blauw")};
		p.krijgtSteen(Stenen[0]);
		p.krijgtSteen(Stenen[1]);
		p.krijgtSteen(Stenen[2]);
		p.kopieInstellen();
		p.verwijderSteen(Stenen[0]);
		p.verwijderSteen(Stenen[1]);
		p.verwijderSteen(Stenen[2]);
		Assertions.assertTrue(p.eersteBeurt());
	}

}
