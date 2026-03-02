package mru.table.controller;

import java.util.Comparator;

import mru.table.model.Row;

public class TextAlphabeticalCompare implements Comparator<Row> {
	
	public TextAlphabeticalCompare() {
		
	}

	@Override
	public int compare(Row o1, Row o2) {
		int compare = 0;
		try {
			if (o1 != null && !o1.equals(o2)) {
				if (o1.getText().compareTo(o2.getText()) < 0){ //o1's Text comes Before o2
					compare = -1;
				}else if (o1.getText().compareTo(o2.getText()) > 0){ //o1's Text comes after o2
					compare = 1;
				}//no assignment to compare needed if compareTo==0 because compare is already 0
			} 
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
		return compare;
	
	}

}
