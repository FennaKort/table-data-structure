package mru.table.controller;

import java.util.Comparator;

import mru.table.model.Row;

/**
 * for comparing data in all row cells and sorting alphabetically if differences are found in any column
 */
public class CompareRowsAlphabetically implements Comparator<Row> {
	int compare;
	
	/**
	 * Compares each the values in each pair of cells for the given rows, allowing sorting by alphabetical order according to the first different value found. Does not compare Row IDs, only values in Rows' arrays
	 * @param o1 a row object
	 * @param o2 another row object
	 * @return 0 if all pairs of cell values are the same, -1 if o1 precedes o2 alphabetically based on first difference found, 1 if 01 follows o2 based on first difference found. Will return null if o1 and o2 have arrays of different lengths. 
	 * @throws NullPointerException if either row object is null
	 */
	@Override
	public int compare(Row o1, Row o2) {
		try {
			if (o1 != null && o2 != null && o1.getValues().length == o2.getValues().length) {
				//TODO ENDLESS LOOP FOUND, NEEDS CLEANUP
					//compare each pair of cells
					for (int i = 0; i < o1.getValues().length; i++) {
						do {
							compare = o1.getValues()[i].compareTo(o2.getValues()[i]);
						
						}while(compare == 0);
				}  //continue until a difference is found, or for all cells
				
				//if a difference was found, convert comparison to either 1 or -1 because String.compareTo() returns a greater range of positive or negative ints
				if (compare < 0) {
					compare = -1; //o1 precedes o2 according to alphabetical order in difference 
				} else if (compare > 0) {
					compare = 1; //this row follows o according to alphabetical order in difference
				}
			}
			
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return compare;
	}

}
