package mru.table.controller;

import java.util.ArrayList;

import mru.table.model.IndexNode;
import mru.table.model.Row;


public class Index {
	//TODO BST for indexing
	// [] BST class for indexing
	// [] public void addIndex(String column) in Table; creates BST of all vals in col. 
	// [] needs to be able to index ALL cols in table, Table need to store an AL of BSTs
	// [] for BST, need to be able to add public void add(String key, Row row) where key is val in target col
		// [] check for valid target col
		// [] checks for nodes where key exists, if so, adds row to node data
	// [] BST nodes store one key and AL of matching Rows. when adding, should add row to existing node if key exists
	// [] public ArrayList<Row> find( String key)
	// [] select() needs to be modified to use the index, if one exists
	
	private IndexNode root;
	private int height;
	
	/**
	 * Creates a new index with the specified IndexNode as its root
	 * @param n
	 */
	public Index(IndexNode n) {
		this.root = n;
		this.height++;
	}
	
	/**
	 * default constructor
	 */
	public Index() {}

	public IndexNode getRoot(){return root;}
	
	public void setRoot(IndexNode n) {this.root = n;}
	
	public int getHeight() {return height;}
	
	/**
	 * Adds a Row to the Index according to the specified Key
	 * @param key the Row's key as a String
	 * @param row the new Row object to be indexed
	 */
	public void addRow(String key, Row row) {
		//k+r need to be added to an IN, IN needs to be added to tree: make node AFTER compare
		//if tree is empty, new node needs to be root
		if (getRoot()==null) {
			setRoot(new IndexNode(key, row));
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
	
	
//Traversals
	public void peek(IndexNode n) { 
		if(n!=null) {
			System.out.println(n.getRows()); //TODO change to account for Rows AL mechanism
		} else {
			System.out.println("Node is null");
		}
	}
	
	public void preOrder(IndexNode n) {
		//peek node, check left, check right
		
		if(n!=null) {
			peek(n);
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
