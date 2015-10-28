package com.vimisky.alg;

public class ComparableObject implements Comparable<ComparableObject> {

	private int keyInt;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(ComparableObject o) {
		// TODO Auto-generated method stub
		return this.keyInt - o.keyInt;
	}

}
