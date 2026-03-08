package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mru.table.controller.CompareColumnsAlphabetically;
import mru.table.model.Row;

/**
 * @author Fenna Buitenwerf
 */
class CompareColumnsAlphabeticallyTest {
	CompareColumnsAlphabetically alphaCompare = new CompareColumnsAlphabetically(1);
	private int idCounter = 1;
	private Row r1 = new Row(idCounter++, new String[]{"meow","prrow", "mew"});
	private Row r3 = new Row(idCounter++, new String[]{"meow","prrow", "mew"});
	private Row r2 = new Row(idCounter++, new String[]{"meow","purr", "mew"});
	

	@Test
	void testPrecedes() {
		assertEquals(-1, alphaCompare.compare(r1, r2));
	}
	
	@Test
	void testFollows() {
		assertEquals(1, alphaCompare.compare(r2, r1));
	}
	
	@Test
	 void testEquals() {
		assertEquals(0, alphaCompare.compare(r1, r3));
	}


}
