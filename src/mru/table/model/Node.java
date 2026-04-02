package mru.table.model;


public class Node<T> implements Comparable<Node<T>> {
	/**Optional Key to associate with the Node*/
	private String key;
	/**Generic data type T to be stored in Node*/
	private T data;
	/**Left child of current Node*/
	private Node<T> left;
	/**Right child of current Node*/
	private Node<T> right;
	
	/**
	 * constructor to create new node with T data
	 * @param data generic type data
	 */
	public Node(T data){
		this.data = data;
	}
	
	/**
	 * constructor to create new node with a specified key and T data
	 * @param key the string to use as the node's key
	 * @param data generic type data
	 */
	public Node(String key, T data){
		this.setKey(key);
		this.data = data;
	}
	
	/**
	 * default constructor to create empty node
	 */
	public Node() {
	}
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	public T getData() {
		return data;
	}
	
	public Node<T> getLeft(){
		return left;
	}
	
	public Node<T> getRight(){
		return right;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public void setLeft(Node<T> n) {
		this.left = n;
	}
	
	public void setRight(Node<T> n) {
		this.right = n;
	}

	@Override
	public int compareTo(Node<T> o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
