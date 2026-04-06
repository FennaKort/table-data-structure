package mru.table.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import mru.table.model.Row;

/**
 * A two-dimensional data structure that uses an ArrayList of Row objects to represent a Table
 * @author Fenna Buitenwerf
 */
public class Table {
	//	previous requirements:
	//   [x] the table should include an ArrayList of Row objects. The first row is the header row and contains the names of the columns;
	//	 [x] a constructor with no parameters that simply creates an empty table;
	//	 [x] a constructor that takes a file name (String). The constructor will create a new empty Table and then read the CSV data from that file into the table.
	//	 [x] getters and setters for all instance variables;
	//	 [x] methods to add a rows to the table. The method addRow() will be overloaded. 
			// [x]One will be the one you used in Assignment 1, addRow( int id, String s) (s should be a list of column values separated by commas) 
			//[x] as well as addRow( Row r) which adds an existing Row object, 
			//[x]and addRow( int id, String[] cols) with the column values already in an array.
	//	 [x] a method to return the number of rows in the table;
	//	 [x] a method to print the table, printTable(int r) that can be used for testing purposes. r is the number of rows to print. If r is 0, the whole table is printed, otherwise the first r rows are printed.
	//	 [x] methods to sort the rows by both the natural ordering and by a custom Comparator. 	//	 [x] The custom Comparator should sort by a given column alphabetically.
	//	 [x] a project method, public Table project( String[] cols) that returns a new table that consists of all the rows of the existing table but with only the columns listed.
	//	 [x] a select method, public Table select( String field, String value) that returns a new table that contains all of the columns of the exiting table but with only the rows from the table where column field contains the string value. (This is like a SQL WHERE clause.)
	//	 [x] if time, figure out minus() operation as extra challenge posed by Alan
	
	// TODO part 3
	// [] cross product
	// [] union
	// [] set difference
	// [] A3 class: needs to be able to load multiple file names from command line and create a Table() for each
	
	//TODO BST for indexing
		// [x] public void addIndex(String column) in Table; creates BST of all vals in col. 
		// [x] needs to be able to index ALL cols in table, Table need to store an AL of BSTs
		// [x] select() in Table needs to be modified to use the index, if one exists
		// [x] for BST, need to be able to add public void add(String key, Row row) where key is val in target col
			// [x] check for valid target col - this should happen in Table imo
			// [x] checks for nodes where key exists, if so, adds row to node data
	



//fields	
	/*** optional name for table*/
	private String tableName;
	/*** the table structure, represented by an array list of row objects; the first item will represent the table's column headers*/
	private ArrayList<Row> table = new ArrayList<Row>();
	/***stores Counter For Assigning Next Row Id; Counter Starts At zero so that header row will have an ID of 0*/
	private int idCounter = 0; 
	/**Stores any indices created on the table's columns*/
	private ArrayList<Index> indices = new ArrayList<Index>();
	
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
		File fileIn = new File(fileName);
		BufferedReader b;
		String line;
		
		//stripping out file name to use as tableName
		setTableName(fileName.substring(fileName.lastIndexOf("/")+1, fileName.indexOf(".")));
		
		try {
			b = new BufferedReader(new FileReader(fileIn));
			while((line = b.readLine())!= null) {
				addRow(line);
			}
			b.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * a constructor that accepts an array list of Row objects. and converts them into rows in the table. the strings must contain equal numbers of cells separated by commas.
	 * @param rows an array list of Row objects representing rows in a table. the first row in the array list will be the table's header row. 
	 */
	public Table(ArrayList<Row> rows) {
		setTable(rows);
	}
	
	public Table(Table t1) {
		this.table = t1.getTable();
	}

//getters and setters
	/**
	 * @param name the string to use as the table's name
	 */
	public void setTableName(String name) {this.tableName = name;}
	
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
	public void setTable(ArrayList<Row> table) {this.table = table;}
	
	/**
	 * sets Table's ArrayList table to accept an array list of strings and converts them into rows in the table. the strings must contain equal numbers of cells separated by commas.
	 * @param rows an array list of comma-separated strings representing rows in a table. the first string will be the table's header row. 
	 */
	public void setTableFromALOfStrings(ArrayList<String> rows) {
		//TODO add a method to check that all rows have same cell total as header row
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
		//TODO add a method to check that all rows have same cell total as header row
		header.setId(0);
		if (this.table.isEmpty()){
			this.table.add(header);
			idCounter++;
		}else {//replace current header with new header
			this.table.remove(0);
			this.table.add(0, header);
		}
	}
	
	/**
	 * accepts an array of strings to use as a header; adds new header Row with id of 0 to table ArrayList, removing any previously set header.
	 * @param header an array of strings to use as the table's column headers
	 */
	public void setHeader(String[] headerValues) {
		//TODO add a method to check that all rows have same cell total as header row
		if (this.table.isEmpty()){
			addRow(headerValues);
		}else {
			this.table.remove(0);
			this.table.add(new Row(0, headerValues));
		}
	}
	
	public Row getHeader() {return this.table.get(0);}
	
	/**
	 * @return the number of rows in the table as an int, includes header row in count
	 */
	public int getTableSize() {
		if (this.table == null)
			return 0;
		else
			return table.size();
	}
	
	/**
	 * @return the number of rows in the table as an int, does NOT count header row
	 */
	public int getNumOfRows() {
		if (this.table == null)
			return 0;
		else
			return (table.size()-1);
	}
	
	/**
	 * @return the number of columns in the table as an int
	 */
	public int getNumOfColumns() {
		if (this.table == null)
			return 0;
		else
			return getHeader().getValues().length;
	}

// Utility Methods 
	
	/**
	 * checks if the parameter table has the same number of columns as the current table
	 * @param o another Table
	 * @return true if Both Tables Have The Same Number Of Columns; false if not
	 */
	public boolean hasSameNumOfColumns(Table o) {
		if ((o!=null) && getNumOfColumns() == getNumOfColumns())
			return true;
		 else
			 return false;
	}
	
	/**
	 * checks if the parameter row has the same number of columns as the current table
	 * @param r  the row object to compare
	 * @return true if the same number of columns; false if not
	 */
	public boolean hasSameNumOfColumns(Row r) {
		if ((r.getValues()!=null) && getNumOfColumns() == r.getValues().length)
			return true;
		 else
			 return false;	
	}
	
	public boolean hasSameNumOfColumns(ArrayList<Row> table) {
		if ((!table.isEmpty()) && getNumOfColumns() == table.get(0).getValues().length)
			return true;
		 else
			 return false;
	}
	
/*ADDING AND REMOVING ROWS*/
	/**
	 * appends a row object to the end of the table list with the specified id
	 * @param id the int of the id to use for the row
	 * @param row a comma-separated string to be appended as a row to the end of the  table list
	 */
	public void addRow(int id, String values) {
		//TODO [x] if time, would like to add feature to check if new row conforms to correct number of columns
		Row row = new Row(id, values);
		if (hasSameNumOfColumns(row))
			table.add(row);
	}
	
	/**
	 * appends a row object to the end of the table list; the id will be incremented using idCounter
	 * @param row a comma-separated string to be appended as a row to the end of the  table list
	 */
	public void addRow(String values) {
		//TODO [x] if time, would like to add feature to check if new row conforms to correct number of columns
		Row row = new Row(idCounter++, values);
		if (hasSameNumOfColumns(row))
			table.add(row);
	}
	
	/**
	 * appends a row object to the end of the table list; the row's id will be the existing row id of the  input row object
	 * @param row the row object to be appended to the end of the  table list
	 */
	public void addRow(Row row) {
		//TODO [x]if time, would like to add feature to check if new row conforms to correct number of columns
		if (hasSameNumOfColumns(row))
			table.add(row);
	}
	
	/**
	 * appends a new row to the end of the table. the row's id is set using the incrementing static id counter
	 * @param id the int to use as the row's id
	 * @param values the array of strings to add as the new row's cell values
	 */
	public void addRow(int id, String[] values) {
		//TODO [x] if time, would like to add feature to check if new row conforms to correct number of columns
		Row row = new Row(id, values);
		if (hasSameNumOfColumns(row))
			table.add(row);
	}
	
	/**
	 * appends a new row to the end of the table. the row's id is set using the incrementing static id counter
	 * @param values the array of strings to add as the new row's cell values
	 */
	public void addRow(String[] values) {
		//TODO [x] if time, would like to add feature to check if new row conforms to correct number of columns
		Row row = new Row(idCounter++, values);
		if (hasSameNumOfColumns(row))
			table.add(row);
	}
	
	/**
	 * Adds all Rows from the param ArrayList to the current Table's ArrayList table
	 * @param table an ArrayList of Row objects 
	 */
	public void addRowsToTable(ArrayList<Row> table) {
		if(table!=null && hasSameNumOfColumns(table)) {
			this.table.addAll(table);
		} else {
			System.out.println("No rows available to add.");
		}
	}
	
	/**
	 * Removes a Row from the current table if all of its cell values match the cells of the param Row
	 * @param row the Row to match against
	 */
	public void removeRow(Row row) {
		//TODO should also remove from any indices???
		//TODO this could potentially be improved with hashing as well?
		for (int i = 0; i<getTableSize();i++) {
			if(new CompareRowsAlphabetically().compare(this.table.get(i),row) == 0) {
				table.remove(i);
				break;
			}
		}
	}
	
/*SORTING METHODS*/
	/**
	 * sorts table ArrayList by row object natural ordering,  ascending order by row Id
	 * Note that header Row is included in the sort because the header Row's id should be 0
	 */
	public void sortByDefault() {
		try {
			Collections.sort(table);
		}catch(Exception e){
			System.out.println("Sort by ID could not be completed: "+ e +"\n");
		}
	}
	
	/**
	 * sorts table ArrayList by alternative ordering, alphabetically by specified column index. 
	 * @param targetIndex the index of the target column in the Rows' string arrays of cell values
	 */
	public void sortAlphabeticallyByColumn(int targetIndex) {
		try {
			Collections.sort(table, new CompareColumnsAlphabetically(targetIndex));
		}catch(Exception e){
			System.out.println("Sorting alphabetically by requested column could not be completed: " + e +"\n");
		}
	}
	
	/**
	 * sorts table ArrayList by alternative ordering, alphabetically by specified column name
	 * @param targetColumn  the name of the target column  as a string
	 */
	public void sortAlphabeticallyByColumn(String targetColumn) {
		try {
			Collections.sort(table, new CompareColumnsAlphabetically(findTargetColumn(targetColumn)));
		}catch(Exception e){
			System.out.println("Sorting alphabetically by requested column could not be completed: " + e +"\n");
		}
	}

/*SEARCHING AND INDEXING METHODS*/
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
	 * Indexes the table according to the target column, if the target column exists in the table
	 * @param column the name of the target column  to be indexed
	 */
	public void addIndex(String column) {
		//TODO would be nice to check if index already exists and update it if so?
		//call findTargetColumn
		//if found, make the index using that column as the name
		//use index of target column to add rows to index
	
		int isIndexed = findIndexOfColumn(column);
		if (isIndexed != -1) {
			System.out.println("Target column \"" + column + "\" has already been indexed!");
			//TODO would be great to be able to give user the option to update the index
		}
			
		//check for the target column in the header row
		int targetIndex = findTargetColumn(column);
		
		//if target column was found in the table, index the table according to that column
		if (targetIndex != -1) { 
			Index columnIndex = new Index(column); //set the index's name to the name of the target column
			//for each row in table, add the row to the index
			//TODO I think this is where hashing might be a good addition to the indexing implementation, refactor to use if time
			for(int i=1; i<getTableSize() ;i++) {//start loop @ index1 because we don't need to index the header row
				columnIndex.addRow(table.get(i).getValues()[targetIndex], table.get(i));
			}
			indices.add(columnIndex);
		} else {
			System.out.println("Sorry! \"" + column + "\" can not be indexed because that column could not be found in the current table.");
		}
	} 
	
	/**
	 * searches the array list of indices to check if a given column has been indexed
	 * @param column the target column to search for an index of
	 * @return the index location as an int where the target index is stored in the array list indices; returns -1 if the index is not found
	 */
	public int findIndexOfColumn(String column) {
		int targetIndex = -1;
		if(this.indices!=null)
			for (int i = 0; i < this.indices.size(); i++) 
				if (this.indices.get(i).getIndexName().equalsIgnoreCase(column)) {
					targetIndex = i;
				}
		return targetIndex;	
	}
	
	
/*TABLE SET OPERATIONS*/
	/**
	 * Selects all rows from the current table that contain a specified value in the specified column. Intended to be similar to the SQL SELECT * WHERE clause.
	 * @param field the name of the column to target
	 * @param value the cell value to target within that column
	 * @return a new Table containing all of the same columns as the original table, with only rows where the target column contains the target value
	 */
	public Table select(String field, String value) {
		Table selected = new Table();
		selected.setTableName(getTableName()+" SELECT "+ value + " in COLUMN " + field);
		selected.setHeader(getHeader()); //add current table header to output table
		
			if(this.table!=null) {
			//check if target field has been indexed; location in indices AL will be stored in isIndexed if so
				int isIndexed = findIndexOfColumn(field); 
				if(isIndexed != -1) { //-1 would indicate that target field had not been indexed
					selected.addRowsToTable(this.indices.get(isIndexed).find(value));
				} else {
				//if target field has not been indexed, search through table normally
					int targetIndex = findTargetColumn(field);
					if(targetIndex != -1) { //-1 would indicate that target field was not found in table header
						for(int i=0; i<getTableSize() ;i++) {
							// if target column in table row contains  target value, add Row To ArrayList selected
							if(this.table.get(i).getValues()[targetIndex].toLowerCase().contains(value.toLowerCase())) {
								selected.addRow(table.get(i));
							}
						}
					}
				}
			}
		
		return selected;
		
	}
	
	/**
	 * Projects all values from specified columns. Intended to be similar to database PROJECT operation.
	 * @param cols the names of the columns to contain in the projection
	 * @return a new Table containing all data from specified columns
	 */
	public Table project(String[] cols) {
		//a project method, public Table project( String[] cols) that returns a new table that consists of all the rows of the existing table but with only the columns listed.
		Table projection = new Table();
		int[] targetCols = new int[cols.length];
		try {
		if(this.table!=null) {
			int colCounter = 0;
			//determine indices of target columns:
			for(int i = 0; i < cols.length ; i++) { //for each target column, search for index in header row
				int targetIndex = findTargetColumn(cols[i]);
				if (targetIndex != -1) { //-1 would indicate that target field was not found in table header
					targetCols[colCounter] = targetIndex;
					colCounter++;
				}
				if (colCounter == cols.length)
					break;
			}
		
			//iterate through table, picking out target columns of each row:
			for(int i = 0; i < this.table.size(); i++) {
				String s = "";
				String[] currValues = table.get(i).getValues();
				
				for(int col:targetCols) {
					s += currValues[col] + ",";
				}
				projection.addRow(s);
			}
		}
		}catch(Exception e){
			System.out.println("Projection could not be completed: " + e +"\n");
		}
		return projection;
	}
	
	/**
	 * Performs a set difference operation between the current table and the parameter table
	 * @param t1 another table to compare against the current table
	 * @return a new table containing only the rows that are unique to the current table
	 */
	public Table minus(Table t1) {
		//TODO would benefit from a method that compared columns
		Table minus = new Table(this.table);
		try {
		if(this.table != null && t1.getTable() != null && (getHeader().getValues().length == t1.getHeader().getValues().length)) { //if tables are not null and both tables share the same number of columns
			for (int i = 1; i < getTableSize(); i++) { //starts at i&j = 1 in order to skip, and thus preserve, header row
				for (int j = 1; j < t1.getTableSize(); j++) {
					//compare rows according to each pair of cells
					if (new CompareRowsAlphabetically().compare(table.get(i),t1.getTable().get(j)) != 0) {
						minus.removeRow(t1.getTable().get(j));
					} else {
						minus.addRow(table.get(i));
					}
				}
			}
		}
		}catch(Exception e){
			System.out.println("Minus could not be completed: " + e +"\n");
		}
		return minus;
	}


	/**
	 * a method to print a specified number of rows from the table. 
	 * @param r an int representing the number of rows to print.  if r == 0,  prints the entire table
	 */
	public void printTable(int r) {
		System.out.println("Table: " + getTableName());
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
