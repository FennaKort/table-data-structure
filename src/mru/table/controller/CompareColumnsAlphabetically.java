package mru.table.controller;

import java.util.Comparator;

import mru.table.model.Row;

/**
 * Compare cells in the same column of two Row objects for sorting in alphabetical order by the specified column.
 * @author Fenna Buitenwerf
 */
public class CompareColumnsAlphabetically implements Comparator<Row> {
	/**
	 * index of target column in each Row object's values array
	 */
	private int targetColumnIndex; 
	
	/**
	 * set index of target column in the Row objects' values array
	 * @param i the index of the column to be targeted
	 */
	public void setTargetIndex(int i) {this.targetColumnIndex = i;}
	
	/**
	 * Use this constructor to set an index for the target column in the rows to be compared.
	 * @param i the index of the column to be targeted
	 */
	public CompareColumnsAlphabetically(int i) {
		this.targetColumnIndex = i;
	}
	
	/**
	 * Compare the values of the cells in the target column (set via index of column using constructor or setter) 
	 * @param o1 first Row to compare
	 * @param o2 second Row to compare
	 * @return -1 if o1's cell comes before o2's cell alphabetically; returns 0 if neither cell proceeds the other alphabetically; returns 1 if o1's cell comes after o2's cell alphabetically
	 */
	@Override
	public int compare(Row o1, Row o2) {
		int compare = 0;
		try {
			if (o1 != null && o2 != null  && o1.getValues()[targetColumnIndex] != null&& o2.getValues()[targetColumnIndex] != null) {
				if (o1.getValues()[targetColumnIndex].compareTo(o2.getValues()[targetColumnIndex]) < 0){ //o1's cell comes Before o2's cell alphabetically
					compare = -1;
				}else if (o1.getValues()[targetColumnIndex].compareTo(o2.getValues()[targetColumnIndex]) > 0){ //o1's cell comes after o2's cell alphabetically
					compare = 1;
				}//no assignment to compare needed if compareTo==0 because compare is already 0
			} 
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return compare;
	}
}
