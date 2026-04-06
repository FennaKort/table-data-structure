package mru.table.test;

import static org.junit.jupiter.api.Assertions.*;

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

	/**
	 * Calls Index's public addRow() method and tests against the key and value of the  resulting root node
	 */
	@Test
	void testAddRowToEmptyIndex() {
		Index species = new Index();
		assertEquals(null, species.getRoot());
		species.addRow("cat", new Row(new String[]{"Muffin"}));
		assertEquals("cat", species.getRoot().getKey());
		assertEquals("Muffin", species.getRoot().getRows().get(0).getValues()[0]);
	}
	
	/**
	 * Calls Index's public addRow() method and tests against the values of the resulting leaf nodes
	 */
	@Test
	void testAddRows() {
		Index species = new Index();
		species.addRow("cat", new Row(new String[]{"Muffin"}));
		species.addRow("dog", new Row(new String[]{"Winnie"}));
		species.addRow("cat", new Row(new String[]{"Milkshake"}));
		species.addRow("bunny", new Row(new String[]{"Peanut Butter"}));
		species.addRow("bunny", new Row(new String[]{"Thumper"}));
		
		//TODO would be nice to rewrite the getRows() and getValues() methods to feel more... useful in this context
		//left subtree leaf check
		assertEquals("Peanut Butter", species.getRoot().getLeft().getRows().get(0).getValues()[0]);
		assertEquals("Thumper", species.getRoot().getLeft().getRows().get(1).getValues()[0]);
		
		//right subtree leaf check
		assertEquals("Winnie", species.getRoot().getRight().getRows().get(0).getValues()[0]);
	}
	
	@Test
	void testFind() {
		Index species = new Index();
		species.addRow("cat", new Row(new String[]{"Muffin"}));
		species.addRow("dog", new Row(new String[]{"Winnie"}));
		species.addRow("cat", new Row(new String[]{"Milkshake"}));
		species.addRow("bunny", new Row(new String[]{"Peanut Butter"}));
		species.addRow("bunny", new Row(new String[]{"Thumper"}));
		assertEquals("Winnie", species.find("dog").get(0).getValues()[0]);
		assertEquals(null, species.find("lizard"));
	}

}
