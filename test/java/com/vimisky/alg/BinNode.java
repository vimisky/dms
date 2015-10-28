package com.vimisky.alg;

public class BinNode implements Comparable<BinNode>{

	private int property;
	
	private BinNode leftNode;
	private BinNode rightNode;
	
	
	
	/**
	 * @return the property
	 */
	public int getProperty() {
		return property;
	}



	/**
	 * @param property the property to set
	 */
	public void setProperty(int property) {
		this.property = property;
	}



	/**
	 * @return the leftNode
	 */
	public BinNode getLeftNode() {
		return leftNode;
	}



	/**
	 * @param leftNode the leftNode to set
	 */
	public void setLeftNode(BinNode leftNode) {
		this.leftNode = leftNode;
	}



	/**
	 * @return the rightNode
	 */
	public BinNode getRightNode() {
		return rightNode;
	}



	/**
	 * @param rightNode the rightNode to set
	 */
	public void setRightNode(BinNode rightNode) {
		this.rightNode = rightNode;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



	@Override
	public int compareTo(BinNode o) {
		// TODO Auto-generated method stub
		if (this.property > o.getProperty()) {
			return 1;
		}else if (this.property < o.getProperty()) {
			return -1;
		}
		return 0;
	}

}
