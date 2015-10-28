package com.vimisky.alg;

public class BinaryHeap <T extends Comparable<? super T>> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private int currentSize;
	private final int DEFAULT_CAPACITY = 10;
	
	private T[] array;
	
	public BinaryHeap(){
		array = (T[]) new Comparable[DEFAULT_CAPACITY+1];
		currentSize = 0;
	}
	public BinaryHeap(int capacity){
		array = (T[])new Comparable[capacity+1];
		currentSize = 0;
	}
	
	public int insert(T t){
		if (isFull()) {
			grow();
		}
		int hole = currentSize ++;
		
		for(array[0]=t;t.compareTo(array[hole/2])<0;hole/=2){
			array[hole] = array[hole/2];
		}
		array[hole] = t;
		return 0;
	}
	
	public T findMin(){
		return array[1];
	}
	
	public T deleteMin(){
		T t = findMin();
		array[1] = array[currentSize];
		percolateDown(1);
		return t;
	}
	public void percolateDown(int hole){
		T tmp = array[hole];
		int child ;
		for(;2*hole<currentSize;hole = child){
			child = 2*hole;
			if (array[child+1].compareTo(array[child])<0) {
				child++;
			}
			if (tmp.compareTo(array[child])>0) {
				array[hole]=array[child];
			}else {
				break;
			}
		}
		array[hole]=tmp;
	}
	public boolean isFull(){
		if (currentSize == array.length-1) {
			return true;
		}
		return false;
	}
	public void grow(){
		@SuppressWarnings("unchecked")
		T[] ts = (T[])new Comparable[2*array.length+1];
		for (int i = 0; i < array.length; i++) {
			ts[i] = array[i];
		}
		array = ts;
	}
}
