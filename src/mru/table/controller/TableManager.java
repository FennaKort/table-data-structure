package mru.table.controller;

import java.util.ArrayList;

import mru.table.model.Row;

/**
 * Instantiates Tables
 * @author Fenna Buitenwerf
 */
public class TableManager {
	private ArrayList<Table> tables = new ArrayList<>();
	
	
	public TableManager(String[] args) {
		try {
			int i = 0;
			for(String s : args) {
				tables.add(new Table(s));
				System.out.println("Table created: " + tables.get(i).getTableName());
				System.out.println("With " + tables.get(i).getNumOfRows() + " rows");
				System.out.println();
				
				i++;
			}
		}catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		
		tableDemo();
	}
	
	/**
	 * Demos certain Table methods
	 */
	public void tableDemo() {
		//printAllTables();
		indexTables();
		setOperations();
	}
	
	/**
	 * Prints all tables stored in the manager
	 */
	public void printAllTables() {
		for(Table t:tables) {
			t.printTable(0);
		}
	}
	
	public void indexTables() {
		for(Table t:tables) {
			t.addIndex("Colour");
		}
	}
	
	public void setOperations() {
		for(Table t:tables) {
			System.out.println("PROJECT: ");
			Table projected = t.project(new String[]{"name", "colour"});
			projected.printTable(0);
			
			System.out.println("SELECT: ");
			Table selected = t.select("colour", "black");
			selected.printTable(0);
			
			System.out.println("UNION: ");
			Table union = t.union(selected);
			union.printTable(0);
			
//			System.out.println("SET-DIFFERENCE: ");
//			Table difference = t.setDifference(selected);
//			difference.printTable(0);
			
			System.out.println("CROSS-PRODUCT: ");
			Table product = t.crossProduct(selected);
			product.printTable(0);
		}
	}
	
	public void tableTesting() {
		
		Table catSounds = new Table("res/a2_catdata.csv");
		catSounds.printTable(0);
		catSounds.sortAlphabeticallyByColumn("cat name");
		catSounds.printTable(0);
		catSounds.sortByDefault();
		catSounds.printTable(0);
		Table selectThe = catSounds.select("Very cute sound","meow");
		selectThe.printTable(0);
		
		
		Table t = new Table("res/a2_data.csv");
		System.out.println(t.getTableName());
		System.out.println("1. Animal Sightings \n"
				+ "---------------------\n"
				+ t.getNumOfRows() +"\n"
				+ "---------------------");
		t.printTable(0);
		
		t.sortAlphabeticallyByColumn("colour");
		System.out.println("2. Animal Sightings Sorted by Colour \n"
				+ "---------------------\n"
				+ t.getNumOfRows() +"\n"
				+ "---------------------");
		t.printTable(0);
		t.sortByDefault();
		
		Table select = t.select("colour", "black");
		System.out.println("3. Select colour = black \n"
				+ "---------------------\n"
				+ select.getNumOfRows() +"\n"
				+ "---------------------");
		select.printTable(0);
		
		Table project = t.project(new String[]{"species","count","notes"});
		System.out.println("4. Project species, count, notes\n"
				+ "---------------------\n"
				+ project.getNumOfRows() +"\n"
				+ "---------------------");
		project.printTable(0);
		
		Table onlyBears = t.select("species","bear");
		//Alan: I'd originally added the bears each individually, but realize it would be better to just select for them! Preserved as originally written below:
//		Table onlyBears = new Table();
//		onlyBears.addRow("species,colour,count,area,notes");
//		onlyBears.addRow("bear,black,2,Kananaskis,mother and cub");
//		onlyBears.addRow("bear,grizzled,1,Banff,the Boss");
//		onlyBears.addRow("bear,grizzled,1,Johnson Canyon,the Boss");
		System.out.println("Bears:");
		onlyBears.printTable(0);
		
		Table minus = new Table();
		minus = t.setDifference(onlyBears);
		System.out.println("5. Minus bears\n"
				+ "---------------------\n"
				+ minus.getNumOfRows() +"\n"
				+ "---------------------");
		minus.printTable(0);
		
		
		Index species = new Index();
		species.addRow("cat", new Row(new String[]{"Muffin"}));
		species.addRow("dog", new Row(new String[]{"Winnie"}));
		species.addRow("cat", new Row(new String[]{"Milkshake"}));
		species.addRow("bunny", new Row(new String[]{"Peanut Butter"}));
		species.addRow("bunny", new Row(new String[]{"Thumper"}));
		species.preOrder(species.getRoot());
	}
	
}
