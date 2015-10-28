package com.vimisky.alg;

public class Solution {

	public static int getHeight(int a){
		int height = 0;
		height = (int) (Math.log(a*2)/Math.log(3));
//		System.out.println("compute height :"+height);
		if (2*a>(Math.pow(3, height+1)-3)) {
//			System.out.println("real height :"+(height+1));
			return height+1;
		}else {
			return height;
		}
	}

	public static int getParent(int a){
		int parentID = 0;
		int height = getHeight(a);
		int minNum = (int) (Math.pow(3, height) - 1)/2;
		int seq = a - minNum + 1;
//		System.out.println("a seq "+seq);
		if (height%2 == 0) {
//		偶数行，0位偶数行
			int parentSeq = (int)(seq-1)/3;
			int parentMinNum =  (int) (Math.pow(3, height-1) - 1)/2;
			parentID = parentMinNum + parentSeq;
		}else {
//		奇数行
			int parentSeq = (int)(seq-1)/3;
			int parentMaxNum = (int) (3*(Math.pow(3, height-1)-1))/2;
//			System.out.println("max num "+ parentMaxNum);
			parentID = parentMaxNum - parentSeq ;
		}
		
		return parentID;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a = 13,b=15;
		if (a>b) {
			while(getHeight(a)!=getHeight(b))
				a = getParent(a);
		}else {
			while(getHeight(a)!=getHeight(b))
				b = getParent(b);
		}

		
		while(a!=0 || b!=0){
			a=getParent(a);
			b=getParent(b);
			if (a==b) {
				break;
			}
		}
		System.out.println("ancestor is "+a);
	
		System.out.println("parent "+getParent(15));
	}

}
