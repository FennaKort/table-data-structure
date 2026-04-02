package mru.table.model;

import java.util.ArrayList;

public class BSTNode extends Node<ArrayList<Row>> {
	
	public BSTNode() {
		super(new ArrayList<Row>());
	}
	public BSTNode(String key, Row r) {
		super(new ArrayList<Row>());
		super.setKey(key);
		super.getData().add(r); 
	}
}
