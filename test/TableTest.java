package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import mru.table.controller.Table;
import mru.table.model.Row;

class TableTest {
	private Table catSounds = new Table("cat sounds");
	
	@Test
	void testAddRow() {
		catSounds.addRow("nya");
		catSounds.addRow("meow");
		catSounds.addRow("prrroww");
		System.out.println("cats nyaaaa");
		for(Row r:catSounds.getTable()) {
			System.out.println(r.toString());
		}
		assertEquals(3, catSounds.getTable().get(2).getId());
		assertEquals("prrroww", catSounds.getTable().get(2).getText());
	}
	
	@Test
	void testSetTableName() {
		catSounds.setTableName("Cats Nyaaaa");
		assertEquals("Cats Nyaaaa", catSounds.getTableName());
	}

	@Test
	void testGetTableSize() {
		catSounds.addRow("nya");
		catSounds.addRow("meow");
		catSounds.addRow("prrroww");
		assertEquals(3, catSounds.getTableSize());
	}
	
	@Test
	void testSelect() {
		catSounds.addRow("nya");
		catSounds.addRow("meow");
		catSounds.addRow("prrroww");
		Table myFavouriteCatSounds = catSounds.select("nya");
		assertEquals(1, myFavouriteCatSounds.getTableSize());
		assertEquals(1, myFavouriteCatSounds.getTable().get(0).getId());
		assertEquals("nya", catSounds.getTable().get(0).getText());
	}
	
	@Test
	void testSortByDefault() {
		catSounds.addRow(new Row(7,"nya"));
		catSounds.addRow(new Row(6,  "meow"));
		catSounds.addRow(new Row(1, "prrroww"));
		catSounds.printTable(0);
		Collections.sort(catSounds.getTable());
		catSounds.printTable(0);
		assertEquals("prrroww", catSounds.getTable().get(0).getText());
	}
	

}
