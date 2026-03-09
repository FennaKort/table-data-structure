package mru.table.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import mru.table.model.Row;

/**
 * @author Fenna Buitenwerf
 */
public class Table {
	//	TODO REQUIREMENTS:
	//test
	//   [x] the table should include an ArrayList of Row objects. The first row is the header row and contains the names of the columns;
	//	 [x] a constructor with no parameters that simply creates an empty table;
	//	 [] a constructor that takes a file name (String). The constructor will create a new empty Table and then read the CSV data from that file into the table.
	//	 [x] getters and setters for all instance variables;
	//	 [x] methods to add a rows to the table. The method addRow() will be overloaded. 
			// [x]One will be the one you used in Assignment 1, addRow( int id, String s) (s should be a list of column values separated by commas) 
			//[x] as well as addRow( Row r) which adds an existing Row object, 
			//[x]and addRow( int id, String[] cols) with the column values already in an array.
	//	 [x] a method to return the number of rows in the table;
	//	 [x] a method to print the table, printTable(int r) that can be used for testing purposes. r is the number of rows to print. If r is 0, the whole table is printed, otherwise the first r rows are printed.
	//	 [x] methods to sort the rows by both the natural ordering and by a custom Comparator. 	//	 [x] The custom Comparator should sort by a given column alphabetically.
	//	 [] a project method, public Table project( String[] cols) that returns a new table that consists of all the rows of the existing table but with only the columns listed.
	//	 [x] a select method, public Table select( String field, String value) that returns a new table that contains all of the columns of the exiting table but with only the rows from the table where column field contains the string value. (This is like a SQL WHERE clause.)

	/*** optional name for table*/
	private String tableName;
	/*** the table structure, represented by an array list of row objects; the first item will represent the table's column headers*/
	private ArrayList<Row> table = new ArrayList<Row>();
	/***stores Counter For Assigning Next Row Id; Counter Starts At zero so that header row will have an ID of 0*/
	private int idCounter = 0; 

	
// constructors
	/**
	 * default constructor to create an empty table
	 */
	public Table() {
	}
	
	/**a constructor that takes a file name (String). The constructor will create a new empty Table and then read the CSV data from that file into the table.
	 * @param fileName the specified file name and path of the target .csv file
	 */
	public Table(String fileName) {
		//TODO fileIn, splitting, arraylist construction
		this.tableName = tableName;
	}
	
	/**
	 * a constructor that accepts an array list of Row objects. and converts them into rows in the table. the strings must contain equal numbers of cells separated by commas.
	 * @param rows an array list of Row objects representing rows in a table. the first row in the array list will be the table's header row. 
	 */
	public Table(ArrayList<Row> rows) {
		setTable(rows);
	}
	
//getters and setters
	public void setTableName(String name) {
		this.tableName = name;
	}
	
	/**
	 * @return table name as a string; returns string "Unnamed Table" if table is unnamed
	 */
	public String getTableName() {
		if (this.tableName == null)
			return "Unnamed Table";
		return this.tableName;
	}
	
	/**
	 * sets Table's ArrayList table to contain the same Rows as input ArrayList 
	 * @param table an ArrayList of Row objects 
	 */
	public void setTable(ArrayList<Row> table) {
		this.table = table;
	}
	
	/**
	 * sets Table's ArrayList table to accept an array list of strings and converts them into rows in the table. the strings must contain equal numbers of cells separated by commas.
	 * @param rows an array list of comma-separated strings representing rows in a table. the first string will be the table's header row. 
	 */
	public void setTableFromALOfStrings(ArrayList<String> rows) {
		for(String line:rows) {
			addRow(line.split(","));
		}
	}
	
	/**
	 * sets up a table by turning each line from the default input stream into a new row appended to the table; use commas to separate cells (ie., "Muffin,Black and white,Very cute" will become three cells in a row's string array)
	 */
	public void setTableFromDefaultInput() {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) { //while further lines in input, set up new row and add to table
			addRow(scanner.nextLine().split(","));
		}
		scanner.close();
	}
	
	/**
	 * @return the array list of row objects that represents the table
	 */
	public ArrayList<Row> getTable() {
		return this.table;
	}
	
	/**
	 * accepts a new Row to use as a header; sets the Row's id to 0 and adds new header Row to table ArrayList, removing any previously set header.
	 * @param header a Row object to use as the table's column headers
	 */
	public void setHeader(Row header) {
		header.setId(0);
		if (this.table.isEmpty()){
			this.table.add(header);
		}else {
			this.table.remove(0);
			this.table.add(0, header);
		}
	}
	
	/**
	 * accepts an array of strings to use as a header; adds new header Row with id of 0 to table ArrayList, removing any previously set header.
	 * @param header an array of strings to use as the table's column headers
	 */
	public void setHeader(String[] headerValues) {
		if (this.table.isEmpty()){
			addRow(headerValues);
		}else {
			this.table.remove(0);
			this.table.add(new Row(0, headerValues));
		}
	}
	
	public Row getHeader() {
		return this.table.get(0);
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

// Utility Methods 
	/**
	 * appends a row object to the end of the table list with the specified id
	 * @param id the int of the id to use for the row
	 * @param row a comma-separated string to be appended as a row to the end of the  table list
	 */
	public void addRow(int id, String values) {
		//TODO if time, would like to add feature to check if new row conforms to correct number of columns
		Row row = new Row(id, values);
		table.add(row);
	}
	
	/**
	 * appends a row object to the end of the table list; the id will be incremented using idCounter
	 * @param row a comma-separated string to be appended as a row to the end of the  table list
	 */
	public void addRow(String values) {
		//TODO if time, would like to add feature to check if new row conforms to correct number of columns
		Row row = new Row(idCounter++, values);
		table.add(row);
	}
	
	/**
	 * appends a row object to the end of the table list; the row's id will be the existing row id of the  input row object
	 * @param row the row object to be appended to the end of the  table list
	 */
	public void addRow(Row row) {
		//TODO if time, would like to add feature to check if new row conforms to correct number of columns
		table.add(row);
	}
	
	/**
	 * appends a new row to the end of the table. the row's id is set using the incrementing static id counter
	 * @param id the int to use as the row's id
	 * @param values the array of strings to add as the new row's cell values
	 */
	public void addRow(int id, String[] values) {
		//TODO if time, would like to add feature to check if new row conforms to correct number of columns
		Row row = new Row(id, values);
		table.add(row);
	}
	
	/**
	 * appends a new row to the end of the table. the row's id is set using the incrementing static id counter
	 * @param values the array of strings to add as the new row's cell values
	 */
	public void addRow(String[] values) {
		//TODO if time, would like to add feature to check if new row conforms to correct number of columns
		Row row = new Row(idCounter++, values);
		table.add(row);
	}
	
	/**
	 * searches the header row for the index of the cell matching the target column string
	 * @param targetColumn the name of the target column as a string
	 * @return the index of the target column, or -1 if column header was not found
	 */
	public int findTargetColumn(String targetColumn) {
		 int targetIndex = -1;
		if(this.table!=null)
			for (int i = 0; i < getHeader().getValues().length; i++) 
				if (getHeader().getValues()[i].equalsIgnoreCase(targetColumn)) {
					targetIndex = i;
				}
		return targetIndex;	
	}
	
	/**
	 * sorts table ArrayList by row object natural ordering,  ascending order by row Id
	 * Note that header Row is included in the sort because the header Row's id should be 0
	 */
	public void sortByDefault() {
		Collections.sort(table);
	}
	
	/**
	 * sorts table ArrayList by alternative ordering, alphabetically by specified column index. 
	 * @param targetIndex the index of the target column in the Rows' string arrays of cell values
	 */
	public void sortAlphabeticallyByColumn(int targetIndex) {
		 Collections.sort(table, new CompareColumnsAlphabetically(targetIndex));
	}
	
	/**
	 * sorts table ArrayList by alternative ordering, alphabetically by specified column name
	 * @param targetColumn  the name of the target column  as a string
	 */
	public void sortAlphabeticallyByColumn(String targetColumn) {
		 Collections.sort(table, new CompareColumnsAlphabetically(findTargetColumn(targetColumn)));
	}
	/**
	 * Selects all rows from the current table that contain a specified value in the specified column. Intended to be similar to the SQL WHERE clause.
	 * @param field the name of the column to target
	 * @param value the cell value to target within that column
	 * @return a new Table containing all of the same columns as the original table, with only rows where the target column contains the target value
	 */
	public Table select(String field, String value) {
		ArrayList<Row> selected = new ArrayList<Row>();

		if(this.table!=null) {
			int targetIndex = findTargetColumn(field);
			if(targetIndex != -1) { //-1 would indicate that target field was not found in table header
				selected.add(getHeader());
				for(int i=0; i<getTableSize() ;i++) {
					// if target column in table row contains  target value, add Row To ArrayList selected
					if(this.table.get(i).getValues()[targetIndex].toLowerCase().contains(value.toLowerCase())) {
						selected.add(table.get(i));
					}
				}
			}
		}
		return new Table(selected);
	}
	
	public Table project(String[] cols) {
		//a project method, public Table project( String[] cols) that returns a new table that consists of all the rows of the existing table but with only the columns listed.
		ArrayList<Row> selected = new ArrayList<Row>();
		String[] colsTotal = new String[cols.length];
		// first, need to determine indices of target columns
		// then, need to store these indices
		// then, need to iterate through the entire table, access values[] of each row, copy values in target columns to a new String[], make a new Row with new values, and add to output AL
		// then, make new Table with new output AL
		
		if(this.table!=null) {
			for(int i = 0; i < cols.length ; i++) { //for each target column, search
				int targetIndex = findTargetColumn(cols[i]);
				if (targetIndex != -1) {
					
				}

			}
			
			
			if(targetIndex != -1) { //-1 would indicate that target field was not found in table header
				selected.add(getHeader());
				for(int i=0; i<getTableSize() ;i++) {
					// if target column in table row contains  target value, add Row To ArrayList selected
					if(this.table.get(i).getValues()[targetIndex].toLowerCase().contains(value.toLowerCase())) {
						selected.add(table.get(i));
					}
				}
			}
		}
		return new Table(selected);
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
}
