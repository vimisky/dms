package com.vimisky.alg;

public class ModifyBugSolution {

	/**
	 * bugÎªËÀÑ­»·
	 * */
    static int solution(int[] A, int X) {
        int N = A.length;
        if (N == 0) {
            return -1;
        }
        int l = 0;
        int r = N - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (A[m] > X) {
                r = m - 1;
            } else {
                l = m;
            }
        }
        if (A[l] == X) {
            return l;
        }
        return -1;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] todo = {-5,1,2,5,9,9,10};
		int tofind = 9;
		int pos = solution(todo, tofind);
		System.out.println(pos);
		
	}

}
