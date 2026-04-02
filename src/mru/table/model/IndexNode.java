package mru.table.model;

import java.util.ArrayList;

public class IndexNode<T> implements Comparable<IndexNode<T>> {
	/**Key to associate with the Node*/
	private String key;
	/**The ArrayList `data` stores data of type T that is associated with the node's key*/
	private ArrayList<T> data;
	/**Left child of current Node*/
	private IndexNode<T> left;
	/**Right child of current Node*/
	private IndexNode<T> right;
	
	/**
	 * constructor to create new node with T data
	 * @param data generic type data
	 */
	public IndexNode(T data){
		this.data.add(data);
	}
	
	/**
	 * constructor to create new node with a specified key and T data
	 * @param key the string to use as the node's key
	 * @param data generic type data
	 */
	public IndexNode(String key, T data){
		this.setKey(key);
		this.data.add(data);
	}
	
	/**
	 * default constructor to create empty node
	 */
	public IndexNode() {
	}
	
	/**
	 * @return the key associated with this node
	 */
	public String getKey() {return key;}

	/**
	 * @param key the key to associate with this node
	 */
	public void setKey(String key) {this.key = key;}

	/**
	 * @return the ArrayList storing all objects that are associated with this nodes key
	 */
	public ArrayList<T> getData() {return data;}
	
	/**
	 * retrieves the node's left child node. the left child is ordered as preceding the current node 
	 * @return the index node set as the current node's left child, or null if the node  has no left child
	 */
	public IndexNode<T> getLeft(){return left;}
	
	public IndexNode<T> getRight(){
		return right;
	}
	
	public void setLeft(IndexNode<T> n) {
		this.left = n;
	}
	
	public void setRight(IndexNode<T> n) {
		this.right = n;
	}

	@Override
	public int compareTo(IndexNode<T> o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
