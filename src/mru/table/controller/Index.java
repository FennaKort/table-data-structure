package mru.table.controller;

import java.util.ArrayList;

import mru.table.model.IndexNode;
import mru.table.model.Row;


public class Index {
	//TODO BST for indexing
	// [] BST class for indexing
	// [] public void addIndex(String column) in Table; creates BST of all vals in col. 
	// [] needs to be able to index ALL cols in table, Table need to store an AL of BSTs
	// [] select() in Table needs to be modified to use the index, if one exists
	// [] for BST, need to be able to add public void add(String key, Row row) where key is val in target col
		// [] check for valid target col
		// [] checks for nodes where key exists, if so, adds row to node data
	// [] BST nodes store one key and AL of matching Rows. when adding, should add row to existing node if key exists
	
	// [] public ArrayList<Row> find(String key)
	

	// [] would like to make index an avl tree if time
	
	/**The name of the Table column indexed in this Index*/
	private String indexName;
	private IndexNode root;
	private int height; //TODO is height necessary? maybe remove; definitely change impl in addRow
	
	/** Creates a new index with the specified IndexNode as its root
	 * @param n an IndexNode*/
	public Index(IndexNode n) {this.root = n;}
	
	/**
	 * Constructor to create a new index with the specified name and the node as its root
	 * @param columnName the name of the column being indexed
	 * @param n the node to use as a root of the index
	 */
	public Index(String columnName, IndexNode n) {
		this.indexName = columnName;
		this.root = n;
	}
	
	/**
	 * Constructor to create a new index with the specified name 
	 * @param columnName the name of the column being indexed
	 */
	 public Index(String columnName) {
		this.indexName = columnName;
	 }
	
	/** default constructor*/
	public Index() {}

	public IndexNode getRoot(){return this.root;}
	
	public void setRoot(IndexNode n) {this.root = n; //this.height++;
	}
	
	public int getHeight() {return this.height;}
	
	public String getIndexName() {return this.indexName;}
	
// create index; add index rows; find key in index
	/**
	 * Adds a Row to the Index according to the specified Key
	 * @param key the Row's key as a String
	 * @param row the new Row object to be indexed
	 */
	public void addRow(String key, Row row) {
		//k+r need to be added to an IN, IN needs to be added to tree: make node AFTER compare
		//TODO how to call this for each Row and each Key???
		//if tree is empty, new node needs to be root
		if (getRoot()==null) {
			setRoot(new IndexNode(key, row));
			this.height++;
		} else {
			//compare key to key of current node
			//compare to left or right child depending on result
			addRow(this.root, key, row);
		}
		
	}
	
	/**
	 * Recursive method to add a Row to the correct location in the Index according to the specified key. will catch a NullPointerException if the index node n is null
	 * @param n a node in the Index
	 * @param key the Row's key as a String
	 * @param row the new Row object to be indexed
	 */
	private void addRow(IndexNode n, String key, Row row) {
		int compare = n.getKey().compareTo(key);
		try {
			if (compare == 0) { //param key matches key of node n
				n.addRow(row);
				this.height++; //increment Index tree height by 1
			} else if (compare > 0) { //key of node n sorts after param key; param key should be added in the left subtree
				if(n.getLeft()==null) { //if no left child, make new node as left child of n
					n.setLeft(new IndexNode(key, row)); 
				} else {
					addRow(n.getLeft(),key,row);
				}
			} else if (compare < 0) { //key of node n Sorts Before Param Key; param key should be added in the right subtree
				if(n.getRight()==null) { //if no right child, make new node as right child of n
					n.setRight(new IndexNode(key, row));
				} else {
					addRow(n.getRight(),key,row);
				}
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Searches the Index for a target key
	 * @param key a target key to look for in the index
	 * @return an ArrayList of Rows matching the key if found, null if key is not found
	 */
	public ArrayList<Row> find(String key){
		//compare target key to key of node; if target key found, return rows stored at key's node
		return find(getRoot(),key);
	}
	
	/**
	 * Recursive method to search the Index for a target key at a specified node. Compares the key to the key of the current node; recursively calls find(n.getLeft(),key) if key might be found in left subtree and find(n.getRight(),key) if key might be found in right subtree. 
	 * @param n the IndexNode to search at
	 * @param key the target key to search for
	 * @return the ArrayList of Rows if target key is found at current node; null if key is not found anywhere in Index
	 */
	private ArrayList<Row> find(IndexNode n, String key){
		if (n == null)
			return null;
		int compare = n.getKey().compareTo(key);
		if (compare > 0)  //key of node n sorts after param key; look for param key in the left subtree
			return find(n.getLeft(), key);
		else if (compare < 0) //key of node n Sorts Before Param Key; look for param key in the right subtree
			return find(n.getRight(), key);
		else //param key matches key of node n
			return n.getRows();
	}
	
	
//Traversals
	/**
	 * returns all rows stored with IndexNode n
	 * @param n  an IndexNode to check
	 * @return the array list of rows stored in node n;  else, null if the node is null
	 */
	public String peek(IndexNode n) { 
		//TODO change to account for Rows AL mechanism
		if (n == null) 
			return null;
		else
			return n.getKey() + n.getRows();
	}
	
	public void preOrder(IndexNode n) {
		//peek node, check left, check right
		
		if(n!=null) {
			System.out.println(peek(n));
			preOrder(n.getLeft());
			preOrder(n.getRight());
		}
	}
	public void inOrder(IndexNode n) {
		//check left, peek node, check right
		
		if(n!=null) {
			inOrder(n.getLeft());
			peek(n);
			inOrder(n.getRight());
		}
	}
	
	public void postOrder(IndexNode n) {
		//check left, check right, peek node
		
		if(n!=null) {
			postOrder(n.getLeft());
			postOrder(n.getRight());
			peek(n);
		}
	}
	
	public void levelOrder(IndexNode n) {
		//TODO need levelorder to save bst index in same order so gotta figure out the queue use 
		ArrayList<IndexNode> q = new ArrayList<IndexNode>();
		q.add(n);
		levelOrder(q);
	}
	
	/**
	 * recursive portion of levelOrder traversal
	 * @param q a Queue of Node<T> objects 
	 */
	private void levelOrder(ArrayList<IndexNode> q) {
		if(q!=null) {
			IndexNode n = q.remove(0); //might be better to implement with LinkedList to avoid having to shift all elements each time? could then use removeHead() to pop and addTail() to add new
			
			if(n!=null) {
				peek(n);
				q.add(n.getLeft()); 
				q.add(n.getRight());
				levelOrder(q);
			}
		}
		
	}
}
