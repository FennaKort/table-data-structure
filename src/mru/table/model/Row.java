package mru.table.model;

/**
 * represents a row in a table. has an array to store cell values as strings, and an id int to store the rows id
 * @author Fenna Buitenwerf
 */
public class Row implements Comparable<Row> {
	// [x]update Row To Contain An Array of Strings
	// [x]Update compare methods to reflect that it's now an array instead of a single string
	//	[x] constructors, one to create a row with a comma separated string and one to create a row with an array of strings;
	//	 [x] getters and setters;
	//	 [x] .toString() method;
	//	 [x] The class should implement Comparable with the natural ordering being by the id.
	//	 [x] Since the value of the row is now an array you will need to provide methods to access and set those column values
	
	/*** id representing the row's order/location in the table; use an idCounter int inside Table class to increment*/
	private int id; 
	
	/*** an array of the cells to be associated with the row*/
	private String[] values; 
	
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
	 * constructor To Create a new row object using an array of strings and a specified row id
	 * @param id the integer to use as the row id; recommend using an idCounter int inside the table class to increment the row numbers
	 * @param values the cell Data of the row as An Array
	 */
	public Row(int id, String[] values) {
		this.id = id;
		this.values =  values;
	}
	/**
	 * constructor To Create a new row object using an array of strings. Will NOT set a row id; must be set separately
	 * @param values the cell Data of the row as An Array
	 */
	public Row(String[] values) {
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
	 *tests whether or not another row has the same id as the current row. 
	 *@param o another Row object to test For equivalency
	 *@return true if both objects contain the same id; false if not
	 *@throws NullPointerException 
	 */
	public boolean equals(Row o) throws NullPointerException {
		boolean equals = false;
		if (o == null) { //o contains no object reference
			//no change to var equals necessary
			throw new NullPointerException("Row o cannot be used as a parameter for comparison, as o is null");
		} 
		else if (o.getId() == this.id) { 
				equals = true;
		}
		// if neither of the previous conditions are satisfied, the two objects are not equal; no change to var equals is necessary
		return equals; 
	}

	/**
	 *evaluates for default ordering for row objects in Ascending Order By Id; 
	 *@return -1 if current row's id value is smaller than comparison row's id (ie., current row comes first), 0 if equal, and 1 if current id is greater than comparison id (ie., current row comes after)
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
	 
	/**
	 * formats the row's id and cell data with a tab between them for output, ie., "1	\t hello \t hi"
	 */
	@Override
	public String toString() {
		 String output = Integer.toString(this.id);
		 //TODO make the output nicer like in the toystore project
		 for (int i = 0; i <  this.values.length;i++) {
			 output += "\t"+this.values[i]+"\t";
		 }
		 
		 return output;
		
	}

}
