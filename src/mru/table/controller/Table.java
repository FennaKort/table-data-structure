package mru.table.controller;

import java.util.ArrayList;
import java.util.Scanner;

import mru.table.model.Row;

public class Table {
	 private ArrayList<Row> table = new ArrayList<Row>();
	 private static int idCounter = 1; //stores Counter For Assigning Next Row Id; Counter Starts At One 
	 
	 public Table() {
		 tableSetUp();
	 }
	 
	 /**
	 * sets up a table by turning each line from the default input stream into a new row appended to the table
	 */
	public void tableSetUp() {
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
	
}
