package mru.table.model;

import java.util.ArrayList;

/**
 * Binary node to be used in BST or AVL tree used for indexing Table Rows 
 * @author Fenna Buitenwerf
 */
public class IndexNode implements Comparable<IndexNode> {
	/**Key to associate with the Node*/
	private String key;
	/**The ArrayList `rows` stores Row objects that are associated with the node's key*/
	private ArrayList<Row> rows = new ArrayList<>();
	/**Left child of current Node*/
	private IndexNode left;
	/**Right child of current Node*/
	private IndexNode right;
	
//Constructors
	/**
	 * constructor to create new node with T data added to the Node's array list
	 * @param rows generic type data
	 */
	public IndexNode(Row row){
		this.rows.add(row);
	}
	
	/**
	 * constructor to create new node with a specified key and T data
	 * @param key the string to use as the node's key
	 * @param row generic type data
	 */
	public IndexNode(String key, Row row){
		this.setKey(key);
		this.rows.add(row);
	}
	
	/**
	 * default constructor to create empty node
	 */
	public IndexNode() {
	}
	
	public IndexNode(IndexNode n) {
		// TODO Auto-generated constructor stub
	}

	//getters And Setters
	/**
	 * @return the key associated with this node
	 */
	public String getKey() {return key;}

	/**
	 * @param key the key to associate with this node
	 */
	public void setKey(String key) {this.key = key;}

	/**
	 * @return the ArrayList storing all rows that are associated with this nodes key
	 */
	public ArrayList<Row> getRows() {return rows;}
	//TODO check how this returns if the array list is empty or the node is null
	
	/**
	 * adds a Row to the given node's ArrayList of Rows
	 * @param row a Row object
	 */
	public void addRow(Row row){this.rows.add(row);}
	
	/**
	 * retrieves the node's left child node. the left child is ordered as preceding the current node 
	 * @return the index node set as the current node's left child, or null if the node  has no left child
	 */
	public IndexNode getLeft(){return left;}
	
	/**
	 * retrieves the node's right child node. the Right child is ordered as Following the current node 
	 * @return the index node set as the current node's Right child, or null if the node  has no  right child
	 */
	public IndexNode getRight(){return right;}
	
	/**
	 * sets the node's left child node. the left child is ordered as preceding the current node 
	 * @param n the index node to set as the current node's left child
	 */
	public void setLeft(IndexNode n) {this.left = n;}
	
	/**
	 * sets the node's right child node. the Right child is ordered as following the current node 
	 * @param n the index node to set as the current node's right child
	 */
	public void setRight(IndexNode n) {this.right = n;}

//Utility
	/**
	 * provides the default ordering for index nodes based on alphabetical order of the nodes' keys
	 * @param o the other index node to compare keys with
	 * @return 1 if the current node > other node (CurrentNode follows OtherNode based on keys), -1 if the current node < other node (currentNode Precedes other node based on keys), 0 if the node's keys are the same
	 */
	@Override
	public int compareTo(IndexNode o) {
		int compare = 0;
		try {
			compare = this.getKey().compareTo(o.getKey());
			if (compare > 0)  //this.node's Key > otherNode's Key, this.nodeFollows other node alphabetically
				compare = 1;
			else if (compare < 0) //this.node's Key < otherNode's Key, this.node Precedes Other Node Alphabetically
				compare = -1;
			
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return compare;
	}
}
