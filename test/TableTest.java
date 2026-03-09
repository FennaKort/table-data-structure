package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import mru.table.controller.Table;
import mru.table.model.Row;

/**
 * @author Fenna Buitenwerf
 */
class TableTest {
	private Table catSounds = new Table("cat sounds");
	
	@Test
	void testAddRow() {
		catSounds.setHeader(new String[]{"1","2","3"});
		catSounds.addRow(new String[]{"meow","prrow", "mew"});
		catSounds.addRow(new String[]{"nya","purr", "hisss"});
		catSounds.addRow(new String[]{"prrroww","meow","mllur"});
		assertEquals(3, catSounds.getTable().get(3).getId());
		assertEquals("prrroww", catSounds.getTable().get(3).getValues()[0]);
	}
	
	@Test
	void testSetTableName() {
		catSounds.setTableName("Cats Nyaaaa");
		assertEquals("Cats Nyaaaa", catSounds.getTableName());
	}

	@Test
	void testGetTableSize() {
		catSounds.addRow(new String[]{"meow","prrow", "mew"});
		catSounds.addRow(new String[]{"nya","purr", "hisss"});
		catSounds.addRow(new String[]{"prrroww","meow","mllur"});
		assertEquals(3, catSounds.getTableSize());
	}
	
	@Test
	void testFindTargetColumn() {
		catSounds.addRow(new String[] {"Cat Name","Very Cute Sound"});
		assertEquals(0,catSounds.findTargetColumn("cat name"));
	}
	
	@Test
	void testSortByDefault() {
		catSounds.setHeader(new String[]{"sounds"});
		catSounds.addRow(new Row(7,"nya"));
		catSounds.addRow(new Row(6,  "meow"));
		catSounds.addRow(new Row(1, "prrroww"));
		Collections.sort(catSounds.getTable());
		assertEquals("prrroww", catSounds.getTable().get(1).getValues()[0]);
	}
	
	@Test
	void testSortAlphabeticallyByColumn() {
		catSounds.addRow(new String[] {"Cat Name","Very Cute Sound"});
		catSounds.addRow(new String[]{"Muffin","prrroww"});
		catSounds.addRow(new String[]{"Goose","meow"});
		catSounds.addRow(new String[]{"Winnifred","nya"});
		catSounds.sortAlphabeticallyByColumn(1);
		assertEquals("Very Cute Sound", catSounds.getTable().get(0).getValues()[1]); //checks header row doesn't get sorted
		assertEquals("prrroww", catSounds.getTable().get(3).getValues()[1]);
	}
	
	@Test
	void testSelect() {
		catSounds.setHeader(new String[]{"1","2","3"});
		catSounds.addRow(new String[]{"meow","prrow", "mew"});
		catSounds.addRow(new String[]{"nya","purr", "hisss"});
		catSounds.addRow(new String[]{"prrroww","meow","mllur"});
		Table myFavouriteCatSounds = catSounds.select("1","nya");
		assertEquals(2, myFavouriteCatSounds.getTableSize());
		assertEquals(2, myFavouriteCatSounds.getTable().get(1).getId());
		assertEquals("nya", myFavouriteCatSounds.getTable().get(1).getValues()[0]);
	}
}
