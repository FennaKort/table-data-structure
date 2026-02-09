package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.table.model.Row;

class RowTest {
	private int idCounter = 1;
	private Row r1 = new Row(idCounter++,"meow");
	private Row r2 = new Row(idCounter++,"purr");
	private Row r3 = new Row(r1); //using the copy constructor for the row class in order to test the equals() method
	
	@Test
	void testRowConstructor() {
		assertEquals(1, r1.getId());
		assertEquals("meow", r1.getText());
	}

	@Test
	void testIdIterator() {
		r3 = new Row(idCounter++, "nya");
		Row r4 = new Row(idCounter++, "prrowww");
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
	}
}
