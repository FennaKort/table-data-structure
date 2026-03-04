package mru.table.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import mru.table.model.Row;

public class Table {
	//	TODO REQUIREMENTS:
	//test
	//   [] the table should include an ArrayList of Row objects. The first row is the header row and contains the names of the columns;
	//	 [] a constructor with no parameters that simply creates an empty table;
	//	 [] a constructor that takes a file name (String). The constructor will create a new empty Table and then read the CSV data from that file into the table.
	//	 [] getters and setters for all instance variables;
	//	 [] methods to add a rows to the table. The method addRow() will be overloaded. One will be the one you used in Assignment 1, addRow( int id, String s) (s should be a list of column values separated by commas) as well as addRow( Row r) which adds an existing Row object, and addRow( int id, String[] cols) with the column values already in an array.
	//	 [] a method to return the number of rows in the table;
	//	 [] a method to print the table, printTable(int r) that can be used for testing purposes. r is the number of rows to print. If r is 0, the whole table is printed, otherwise the first r rows are printed.
	//	 [] methods to sort the rows by both the natural ordering and by a custom Comparator. The custom Comparator should sort by a given column alphabetically.
	//	 [] a project method, public Table project( String[] cols) that returns a new table that consists of all the
	//	 [] rows of the existing table but with only the columns listed.
	//	 [] a select method, public Table select( String field, String value) that returns a new table that contains all of the columns of the exiting table but with only the rows from the table where column field contains the string value. (This is like a SQL WHERE clause.)

	private String tableName;
	private ArrayList<Row> table = new ArrayList<Row>();
	private int idCounter = 1; //stores Counter For Assigning Next Row Id; Counter Starts At One 

	public void setTableName(String name) {
		this.tableName = name;
	}
	
	/**
	 * sets Table's ArrayList table to contain the same Rows as input ArrayList 
	 * @param table an ArrayList of Row objects 
	 */
	public void setTable(ArrayList<Row> table) {
		this.table = table;
	}
	
	/**
	 * @return table name as a string; returns string "null" if table is unnamed
	 */
	public String getTableName() {
		if (this.tableName == null)
			return "null";
		return this.tableName;
	}
	
	public ArrayList<Row> getTable() {
		return this.table;
	}
	
	/**
	 * @return the number of rows in the table as an int
	 */
	public int getTableSize() {
		if (table == null)
			return 0;
		else
			return table.size();
	}


	/**
	 * default constructor for table class  calls tableSetUp() method to create a new table based on default input stream
	 */
	public Table() {
		tableFromDefaultInput();
	}
	
	public Table(String tableName) {
		this.tableName = tableName;
	}
	
	public Table(ArrayList<String> text) {
		for(String line:text) {
			addRow(line);
		}
	}


	/**
	 * sets up a table by turning each line from the default input stream into a new row appended to the table
	 */
	public void tableFromDefaultInput() {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) { //while further lines in input, set up new row and add to table
			addRow(scanner.nextLine());
		}
		scanner.close();
	}

	/**
	 * appends a new row to the end of the table. the row's id is set using the incrementing static id counter
	 * @param s the string to add as the new row's text field
	 */
	public void addRow(String s) {
		Row row = new Row(idCounter++, s);
		table.add(row);
	}
	
	/**
	 * appends a new row object to the end of the table list; the row's id will be the existing row id of the  input row object
	 * @param row the row object to be appended to the end of the  table list
	 */
	public void addRow(Row row) {
		table.add(row);
	}
	
	/**
	 * a method to print a specified number of rows from the table. 
	 * @param r an int representing the number of rows to print.  if r == 0,  prints the entire table
	 */
	public void printTable(int r) {
		int tableSize = getTableSize();
		
		if (tableSize != 0) { //table is not empty
			if (r == 0) { //print entire table
				for(int i=0; i<tableSize; i++) {
					System.out.println(table.get(i).toString());
				}
			} else { //beginning at start of table, print r number of rows
				if (r<tableSize) {
					for(int i = 0; i<r; i++) {
						System.out.println(table.get(i).toString());
					}
				} else if (r>tableSize) {
					for(int i=0; i<tableSize; i++) {
						System.out.println(table.get(i).toString());
					}
				}
			}		
		}
		System.out.println("");
	}

	/**
	 * formats table fields to return information about the table's name and size
	 * @return table information in the format "The table tableName has x row(s)", or "This unnamed table has x row(s)" if table is unnamed
	 */
	public String toString() {
		return "The table " + getTableName().toUpperCase() + " has " + getTableSize()  + " row(s).";
	}
	
	/**
	 * sorts table ArrayList by row object natural ordering,  ascending order by row Id
	 */
	public void sortByDefault() {
		Collections.sort(table);
	}
	
	public void sortByAlphabetical() {
		 Collections.sort(table, new TextAlphabeticalCompare());
	}
	
	public Table select(String s) {
		//a select method, public Table select( String s) that returns a new table that contains 	the rows from the table that contain the string s. (This is like a SQL WHERE clause.)
		//occurences of s in tableName
		ArrayList<String> selected = new ArrayList<String>();
		
		for(int i=0; i<getTableSize() ;i++) {
			// if table row contains  target string, add Row's TextTo ArrayList selected
			if(this.table.get(i).getText().toLowerCase().contains(s.toLowerCase())) {
				selected.add(table.get(i).getText());
			}
		}
		return new Table(selected);
	}
}
