package mru.table.application;

import mru.table.model.Row;

public class A1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Row r1 = new Row(1,"nya");
		Row r2 = new Row( 2,"meow");
		Row r3 = null;
		
		System.out.println(r1.getText());
		System.out.println(r1.getId());
		System.out.println( r2.getText());
		System.out.println(r2.getId());
		try {
			r1.equals(r3);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			r3=new Row( 3,"prrrow");
			if (r1.equals(r3))
				System.out.println("equals nyaaaa");
			else
				System.out.println("not equals nyaaaa");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
}
