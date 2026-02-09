package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.table.model.Row;

class RowTest {
	private static int i = 1;
	private int idCounter = 1;
	private Row r1 = new Row(idCounter++,"meow");
	private Row r2 = new Row(idCounter++,"purr");
	private Row r3 = new Row(r1);
	
	
	@BeforeEach 
	void setUp() {
		System.out.println("Test " + i);
		i++;
		System.out.println(r1 + "\t r1");
		System.out.println(r2 + "\t r2");
		System.out.println(r3 + "\t r3");
	}
	
	@Test
	void testRowConstructor() {
		assertEquals(1, r3.getId());
		r3 = new Row(idCounter++, "nya");
		assertEquals(3, r3.getId());
		assertEquals("meow", r1.getText());
	}

	@Test
	void testIdIterator() {
		// TODO This test isn't passing because the ideal counter is final in the row class; at the start of each test, the objects are being re-made, and therefore are iterating the static counter past the intended amount. maybe i need to move the static counter to the table class? because rows will never actually exist without being in a table, so that shouldn't cause any problems that the id counter exists outside of the row class itself. I'm just wondering if this will cause any problems when I'm trying to create new tables? 
		assertEquals(2, r2.getId());
	}
	
	@Test
	void testEquals() {
		assertEquals(false, r1.equals(r2));
		assertEquals(true, r1.equals(r3));
		
	}
}
