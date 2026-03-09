package mru.table.application;

import mru.table.controller.Table;


/**
 * @author Fenna Buitenwerf
 */
public class A2 {

	public static void main(String[] args) {
		//take name of file as command line arg to make table from
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
		
		
		Table t = new Table("res/a2_data.csv");
		System.out.println("1. Animal Sightings \n"
				+ "---------------------\n"
				+ t.getTableSize() +"\n"
				+ "---------------------");
		t.printTable(0);
		
		t.sortAlphabeticallyByColumn("colour");
		System.out.println("2. Animal Sightings Sorted by Colour \n"
				+ "---------------------\n"
				+ t.getTableSize() +"\n"
				+ "---------------------");
		t.printTable(0);
		
		Table select = t.select("colour", "black");
		System.out.println("3. Select colour = black \n"
				+ "---------------------\n"
				+ select.getTableSize() +"\n"
				+ "---------------------");
		select.printTable(0);
		
		Table project = t.project(new String[]{"species","count","notes"});
		System.out.println("4. Project species, count, notes\n"
				+ "---------------------\n"
				+ project.getTableSize() +"\n"
				+ "---------------------");
		project.printTable(0);
		
		Table onlyBears = new Table();
		onlyBears.addRow("species,colour,count,area,notes");
		onlyBears.addRow("bear,black,2,Kananaskis,mother and cub");
		onlyBears.addRow("bear,grizzled,1,Banff,the Boss");
		onlyBears.addRow("bear,grizzled,1,Johnson Canyon,the Boss");
		
		Table minus = new Table();
		minus = t.minus(onlyBears);
		System.out.println("5. Minus bears\n"
				+ "---------------------\n"
				+ minus.getTableSize() +"\n"
				+ "---------------------");
		minus.printTable(0);
		
//		Table selectThe = t.select("1","meow");
//		System.out.println(selectThe.getTableSize());
//		selectThe.printTable(10);
	}

	
}
