package mru.table.model;

public class Row implements Comparable<Row> {
	// TODO update Row To Contain An Array of Strings
	// TODOUpdate compare methods to reflect that it's now an array instead of a single string
//	[x] constructors, one to create a row with a comma separated string and one to create a row with an array of strings;
//	 [x] getters and setters;
//	 [x] .toString() method;
//	 [] The class should implement Comparable with the natural ordering being by the id.
//	 [x] Since the value of the row is now an array you will need to provide methods to access and set those column values
	
	private int id; // id representing the row's order/location in the table.
	private String[] values; // the cells to be associated with the row
	
//constructors
	/**
	 * defaultConstructor, sets Id To Zero And Values To Null
	 */
	public Row() {}
	
	
	/**
	 *  copy Constructor, copies values from input row to new row
	 * @param r  the row object to be copied
	 */
	public Row(Row r) {
		this.id = r.getId();
		this.values = r.getValues();
	}
	
	/**
	 * constructor To Create a new row object using an array of strings
	 * @param id the integer to use as the row id; recommend using an idCounter int inside the table class to increment the row numbers
	 * @param values the cell Data of the row as An Array
	 */
	public Row(int id, String[] values) {
		this.id = id;
		this.values =  values;
	}
	
	/**
	 * constructor To Create a new row object using a comma-separated string
	 * @param id the integer to use as the row id; recommend using an idCounter int inside the table class to increment the row numbers
	 * @param values the cell Data of the row as An Array
	 */
	public Row(int id, String values) {
		this.id = id;
		this.values = values.split(",");
	}
	
//getters and setters
	/**
	 * retrieves the id of the row.  this represents its order/location in the table.
	 * @return the id as an int
	 */
	public int getId() {return id;}
	
	/**
	 * sets the id of the row. the id should correspond to the row's order/location in the table; recommend using an idCounter int inside the table class to increment the row numbers
	 * @param id the id to set
	 */
	public void setId(int id) {this.id = id;}
	
	
	/**
	 * retrieves an array containing all the cells associated with the row
	 * @return the row's  cells as  an array
	 */
	public String[] getValues() {return values;}
	
	/**
	 * sets the data to be stored in all cells of the row using an array
	 * @param  values the row's cell data as  an array
	 */
	public void setValues(String[] values) {this.values = values;}
	
	/**
	 * sets the data to be stored in all cells of the row using a comma-separated string
	 * @param  values the row's cell data as comma-separated string
	 */
	public void setValues(String values) {this.values = values.split(",");}

	/**
	 * retrieves the data stored in the target cell of the row
	 * @param i the target index of the  cell
	 * @return  the string stored at the target index
	 */
	public String getCell(int i) {return this.values[i];}

	/**
	 * sets the data to be stored in a specific cell of the row
	 * @param i the target index of the cell to store the new data in
	 * @param data  the new string to store in a specified cell
	 */
	public void setCell(int i, String data) {this.values[i] = data;}
	
	
	
//UtilityMethods

	/**
	 *tests whether or not another row has the same id and cell Data as the current row.  if true, the two objects represent the same row in the table
	 *@param o another Row object to test For equivalency
	 *@return true if both objects contain the same id and Cell Data values; false if not
	 * @throws NullPointerException 
	 */
	public boolean equals(Row o) throws NullPointerException {
		boolean equals = false;
		if (o == null) { //o contains no object reference
			//no change to var equals necessary
			throw new NullPointerException("Row o cannot be used as a parameter for comparison, as o is null");
		} else if (o.getValues() == null) {
			throw new NullPointerException("Row " + o.getId() +" contains no cell data");
		}
		else if ((o.getId() == this.id) && (o.getValues().length == this.getValues().length)) { // if ids are the same and the  rows store the same number of columns,  compare cell by cell
			boolean cellByCell = false;			
			for (int i = 0; i < o.getValues().length; i++) {
				if (this.getValues()[i].equals(o.getValues()[i])) {
					cellByCell = true; //this can't work because it could return true if only last cells are identical
				}
			}
			equals = cellByCell;
			
			if (compareAllCells(o) == 0) {
				equals = true;
			}
		}
		// if neither of the previous conditions are satisfied, the two objects are not equal; no change to var equals is necessary
		return equals; 
	}

	/**
	 * evaluates for default ordering for row objects in Ascending Order By Id; returns -1 if current row's id value is smaller than comparison row's id (ie., current row comes first), 0 if equal, and 1 if current id is greater than comparison id (ie., current row comes after)
	 */
	@Override
	public int compareTo(Row o) {
		int compare = 0;
		try {
			if (!this.equals(o)) { //if o does not represent the same row as current row
					if (this.id < o.getId()) { //this.id comes before o.id
						compare = -1;
					} else if (this.id > o.getId()) {  //this.id comes after o.id
						compare = 1;
					}
				} else {
					//if o represents the same row as current row, No further assignment to compare is necessary  because compare == 0 by default
				}

		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return compare;
	}
	
	
	//TODO maybe this belongs in a separate comparator class??
	//if one cell is different, row is different, sort row alphabetically by different cell
	public int compareAllCells(Row o) {
		int compare = 0;
		try {
			do {
				//compare each pair of cells
				for (int i = 0; i < o.getValues().length; i++) {
					compare = this.getValues()[i].compareTo(o.getValues()[i]);
				}
			} while(compare == 0); //continue until a difference is found, or for all cells
			
			//if a difference was found, convert comparison to either 1 or -1 because String.compareTo() returns a greater range of positive or negative ints
			if (compare < 0) {
				compare = -1; //this row precedes o according to alphabetical order in difference 
			} else if (compare > 0) {
				compare = 1; //this row follows o according to alphabetical order in difference
			}
			
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return compare;
	}
	
	 @Override
	/**
	 * formats the row's id and cell data with a tab between them for output, ie., "1	hello	hi"
	 */
	public String toString() {
		 String output = Integer.toString(this.id);
		 
		 for (int i = 0; i <  this.values.length;i++) {
			 output += "\t"+this.values[i];
		 }
		 
		 return output;
		
	}

}
