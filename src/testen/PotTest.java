package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Pot;
import domein.RummiSteen;

class PotTest {

	private Pot p;
	
	@BeforeEach
	void before() {
		p = new Pot();
	}
	@Test
	void verwijderSteen_verwijderdtSteen() {
		RummiSteen r = p.getStenen().get(0);
		Assertions.assertEquals(r, p.verwijderSteen());
	}

}
