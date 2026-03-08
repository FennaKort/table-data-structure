package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mru.table.controller.CompareRowsAlphabetically;
import mru.table.model.Row;

class CompareRowsAlphabeticallyTest {
	CompareRowsAlphabetically alphaCompare = new CompareRowsAlphabetically();
	private int idCounter = 1;
	private Row r1 = new Row(idCounter++,"meow");
	private Row r2 = new Row(idCounter++,"purr");
	private Row r3 = new Row(idCounter++, "meow");

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
