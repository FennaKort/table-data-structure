package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.table.model.IndexNode;
import mru.table.model.Row;

class IndexNodeTest {
	IndexNode a = new IndexNode("cat", new Row(new String[]{"Muffin"}));
	IndexNode b = new IndexNode("dog", new Row(new String[]{"Winnie"}));
	IndexNode c = new IndexNode("cat", new Row(new String[]{"Milkshake"}));

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
