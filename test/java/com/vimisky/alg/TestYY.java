package com.vimisky.alg;

public class TestYY {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("res:"+f(40));
		int[] list = {-1,2,100,-90,1000,-24};
//		System.out.println("sum:"+maxsig(list));
//		maxsig(list);
//		Bubble Sort
//		for (int i = 0; i < list.length; i++) {
//			System.out.println("before:"+i+":"+list[i]);	
//		}
//		
//		TestYY.bubbleSort(list);
//		
//		for (int i = 0; i < list.length; i++) {
//			System.out.println("before:"+i+":"+list[i]);	
//		}		
		hanio(3, 'A', 'B', 'Z');
	}
	public static int f(int x){
		if (x <= 1) {
			return 1;
		}else {
			return f(x-1)+f(x-2);
		}
	}
	/**
	 * 
	 * */
	public static int maxsig(int[] intArray){
		int maxSum = 0;
		int maxI = 0;
		int maxJ = 0;
		int thisSum = 0;
//		for (int i = 0; i < intArray.length; i++) {
//			
//		}
		
		int i = 0;
		int j = 0;
		for(;i<intArray.length;i++)
			for(j=i;j<intArray.length;j++){
				thisSum = 0;
				for(int k=i;k<=j;k++){
					thisSum += intArray[k];
				}
				if (thisSum>maxSum) {
					maxI = i;
					maxJ = j;
					maxSum = thisSum;
				}
				
			}
		System.out.println("max seq: i = "+maxI+"; j = "+maxJ+";sum:"+maxSum);

		return maxSum;
	}

	/**
	 * √∞≈›≈≈–Ú
	 * */
	public static int bubbleSort(int[] intArray){
		
		for (int i = 1; i < intArray.length; i++) {
			for (int j = 0; j < intArray.length-i; j++) {
				if (intArray[j]<intArray[j+1]) {
					int tmp = intArray[j];
					intArray[j] = intArray[j+1];
					intArray[j+1] = tmp;	
				}
			}
		}
		return 0;
	}
	private static int stepSerial = 1;
	public static void hanioMove(char x, int n, char z){
		System.out.println("Step "+stepSerial+":"+"Move "+n+" From "+x+" To "+z);
		
	}
	public static void hanio(int n, char x, char y, char z){
		stepSerial++;

		if (n==1) {
			System.out.println("Step "+stepSerial+":"+"Move 1 From "+x+" To "+z );
			return;
		}
		
		hanio(n-1, x, z, y);
		hanioMove(x, n, z);
		hanio(n-1, y, x, z);

	}
	public static void TreeTest() {
		
	}
	public static void mergeSort(int[] array){
		
	}

}
