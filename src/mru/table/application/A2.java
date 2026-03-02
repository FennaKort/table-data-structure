package mru.table.application;

import mru.table.controller.Table;

public class A2 {

	public static void main(String[] args) {
//		1. Print the number of rows in the table.
//		2. Sort the table alphabetically by the text in each row and then print the first 10 		rows of the table.
//		3. Create a new table that consists only of rows that contain the word ’the’, then 		print the number of
//		rows in that table and the first 10 rows.
//		Table catSounds = new Table();
//		catSounds.printTable(0);
//		catSounds.sortByAlphabetical();
//		catSounds.printTable(0);
//		catSounds.sortByDefault();
//		catSounds.printTable(0);
		
		Table t = new Table();
		System.out.println(t.getTableSize());
		
		t.sortByAlphabetical();
		t.printTable(10);
		
		Table selectThe = t.select("The");
		System.out.println(selectThe.getTableSize());
		selectThe.printTable(10);
	}

	
}
