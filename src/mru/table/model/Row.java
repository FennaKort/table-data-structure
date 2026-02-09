package mru.table.model;

public class Row implements Comparable<Row> {
	//TODO maybe clean up the following static int
//	private static int idCounter = 1; //stores Counter For Assigning Next Row Id; Counter Starts At One 
	
	private int id; // id representing the row's order/location in the table.
	private String text; // the text to be associated with the row
	
//constructors
	/**
	 * defaultConstructor, setsIdToZeroAndTextToNull
	 */
	public Row() {}
	
	
	/**
	 *  copy Constructor, copies values from input row to new row
	 * @param r  the row object to be copied
	 */
	public Row(Row r) {
		this.id = r.getId();
		this.text = r.getText();
	}
	
	/**
	 * constructor To Create a new row object with a row id that has been incremented by one 
	 * @param id the integer to use as the row id
	 * @param text the text of the row as a string
	 */
	public Row(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	
//getters and setters
	/**
	 * retrieves the id of the row.  this represents its order/location in the table.
	 * @return the id as an int
	 */
	public int getId() {return id;}
	
	/**
	 * retrieves the text associated with the row
	 * @return the row's text as a string
	 */
	public String getText() {return text;}

	/**
	 * sets the id of the row. the id should correspond to the row's order/location in the table
	 * @param id the id to set
	 */
	public void setId(int id) {this.id = id;}
	/**
	 * sets the text that should be associated with the row
	 * @param text the row's text as a string
	 */
	public void setText(String text) {this.text = text;}
	
//UtilityMethods

	/**
	 *tests whether or not another row has the same id and text as the current row.  if true, the two objects represent the same row in the table
	 *@param o another Row object to test For equivalency
	 *@return true if both objects contain the same id and text values; false if not
	 * @throws NullPointerException 
	 */
	public boolean equals(Row o) throws NullPointerException {
		boolean equals = false;
		if (o == null) { //o contains no object reference
			//no change to var equals necessary
			throw new NullPointerException("Row o cannot be used as a parameter for comparison, as o is null");
		} else if (o.getText() == null) {
			throw new NullPointerException("Row " + o.getId() +"'s text value is null");
		}
		else if ((o.getId() == this.id) && (o.getText().compareTo(this.text) == 0)) { // if id and text both contain the same values, row o equals Current Row
			equals = true;
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
			//if (o != null) { 
				if (!this.equals(o)) { //if o is not null, check that o does not represent the same row as current row
					if (this.id < o.getId()) { //this.id comes before o.id
						compare = -1;
					} else if (this.id> o.getId()) {  //this.id comes after o.id
						compare = 1;
					}
				} else {
					//if o represents the same row as current row, No further assignment to compare is necessary  because compare == 0 by default
				}
			//}
		} catch (NullPointerException e) {
			// TODO: test that i've handled the exception correctly
			System.out.println(e.getMessage());
		}
		return compare;
	}
	
	 @Override
	/**
	 * formats the row's id and text with a tab between them for output, ie., "1	hello"
	 */
	public String toString() {
		return this.id + "\t"+this.text;
		
	}

}
