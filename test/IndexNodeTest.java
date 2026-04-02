package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.table.model.IndexNode;

class IndexNodeTest {
	IndexNode<String> a = new IndexNode<>("cat", "Muffin");
	IndexNode<String> b = new IndexNode<>("dog", "Winnie");
	IndexNode<String> c = new IndexNode<>("cat", "Milkshake");

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCompareTo() {
		int compare = a.compareTo(b);
		assertEquals(-1, compare);
		compare = b.compareTo(a);
		assertEquals(1, compare);
		compare = a.compareTo(c);
		assertEquals(0, compare);
	}

}
