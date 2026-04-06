package mru.table.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.table.controller.Index;
import mru.table.model.IndexNode;
import mru.table.model.Row;

class IndexTest {
	
	IndexNode a = new IndexNode("cat", new Row(new String[]{"Muffin"}));
	IndexNode b = new IndexNode("dog", new Row(new String[]{"Winnie"}));
	IndexNode c = new IndexNode("cat", new Row(new String[]{"Milkshake"}));
	IndexNode d = new IndexNode("bunny", new Row(new String[]{"Peanut Butter"}));
	IndexNode e = new IndexNode("bunny", new Row(new String[]{"Thumper"}));
	IndexNode f = new IndexNode("bunny", new Row(new String[]{"Peanut Butter"}));
	
	

	@Test
	void testAddRowToEmptyIndex() {
		Index species = new Index();
		assertEquals(null, species.getRoot());
		species.addRow("cat", new Row(new String[]{"Muffin"}));
		assertEquals("cat", species.getRoot().getKey());
		assertEquals( "Muffin", species.getRoot().getRows().get(0).getValues()[0]);
	}

}
