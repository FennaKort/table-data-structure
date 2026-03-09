package mru.table.application;

import mru.table.controller.Table;


/**
 * @author Fenna Buitenwerf
 */
public class A2 {

	public static void main(String[] args) {
//		1. Print the number of rows in the table.
//		2. Sort the table alphabetically by the text in each row and then print the first 10 		rows of the table.
//		3. Create a new table that consists only of rows that contain the word ’the’, then 		print the number of
//		rows in that table and the first 10 rows.
		Table catSounds = new Table();
		catSounds.addRow(new String[] {"Cat Name","Very Cute Sound"});
		catSounds.addRow(new String[]{"Muffin","prrroww"});
		catSounds.addRow(new String[]{"Goose","meow"});
		catSounds.addRow(new String[]{"Winnifred","nya"});
		catSounds.printTable(0);
		
		catSounds.sortAlphabeticallyByColumn("cat name");
		catSounds.printTable(0);
		
		catSounds.sortByDefault();
		catSounds.printTable(0);
		
		Table selectThe = catSounds.select("Very cute sound","meow");
		selectThe.printTable(10);
		
		
//		Table t = new Table();
//		System.out.println(t.getTableSize());
//		
//		t.sortAlphabeticallyByColumn("cat name");
//		t.printTable(10);
//		
//		Table selectThe = t.select("1","meow");
//		System.out.println(selectThe.getTableSize());
//		selectThe.printTable(10);
	}

	
}
