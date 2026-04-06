package mru.table.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mru.table.model.Row;

/**
 * @author Fenna Buitenwerf
 */
class RowTest {
	private int idCounter = 1;
	private Row r1 = new Row(idCounter++, new String[]{"meow","prrow", "mew"});
	private Row r2 = new Row(idCounter++, new String[]{"meow","purr", "mew"});
	private Row r3 = new Row(r1); //using the copy constructor for the row class in order to test the equals() method; checks for natural ordering by id, so need to confirm it will return true if id's are the same
	
	@Test
	void testRowConstructor() {
		assertEquals(1, r1.getId());
		assertEquals("meow", r1.getValues()[0]);
	}

	@Test
	void testIdIterator() {
		r3 = new Row(idCounter++, new String[]{"purr","mlow"});
		Row r4 = new Row(idCounter++, new String[]{"mrow","mlep"});
		assertEquals(4, r4.getId());
	}
	
	@Test
	void testEquals() {
		assertEquals(false, r1.equals(r2));
		assertEquals(true, r1.equals(r3));
	}
	
	@Test
	void testCompareTo() {
		assertEquals(0, r1.compareTo(r3));
		assertEquals(-1, r1.compareTo(r2));
		assertEquals(1, r2.compareTo(r1));
	}
}
