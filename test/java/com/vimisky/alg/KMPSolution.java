package com.vimisky.alg;

import org.springframework.data.repository.cdi.Eager;

import com.sun.xml.internal.ws.api.pipe.NextAction;

/**
 * �㷨��ϰ
 * */
public class KMPSolution {

	private static int[] partMatchTable;
	/**
	 * KMP�㷨
	 * ���������������ַ���srcStr,���ȷ�ΧΪ0-1000000��������ѯ�ַ���targetStr��Ҫ���source�в���target�������ȫƥ�������ƥ��λ�á�
	 * */
	public void kmpAlg(char[] srcStr,char[] targetStr){
		int matchNum = 0, srcLength = srcStr.length,targetLength = targetStr.length;
		int maxMatchNum = srcLength/targetLength;
		int[] matchPtr = new int[maxMatchNum+1];
		int sp = 0 , tp = 0;
		while(sp < srcLength && matchNum <maxMatchNum+1){
			
			if (tp == targetLength) {
				matchPtr[matchNum] = sp - targetLength;
				matchNum++;
				tp = 0;
			}
			System.out.println("Start Compare Character At "+ sp +" for "+srcStr[sp]+" with pos " + tp + " for "+targetStr[tp]);
			if (srcStr[sp] == targetStr[tp]) {
				sp++;tp++;
			}else {
				if (tp == 0) {
					sp++;
				}else {
					sp-=tp;
					sp+=tp-partMatchTable[tp];
					sp+=partMatchTable[tp];
					tp = partMatchTable[tp];	
				}				
			}
		}
		System.out.println("string searched result:"+matchNum);
	}
	/**
	 * ��ʼ������ƥ���
	 * */
	private void initPartMatchTable(char[] targetStr) {
//		int i = targetStr.length/2;
		
		boolean matched = true;
		int j = 0;
		int count = 0;
		StringBuffer sBuffer = new StringBuffer(new String(targetStr));
		for(int i=1;i<targetStr.length+1;i++){
			System.out.println("Start Process SubString "+sBuffer.substring(0, i));
			count = 0;
			for(j=1;j<i;j++){
				System.out.println("String compare : " + sBuffer.substring(0,j));
				matched = true;
				for(int k=0;k<j;k++){
					if (targetStr[k] != targetStr[i-j+k]) {
						matched = false;
					}
				}
				if (matched) {
					if (j>count) {
						count = j;
					}
				}
			}
			System.out.println("-----------------");
			partMatchTable[i-1] = count;
		}
	}

	/** 
     * ����ַ�����next����ֵ 
     *  
     * @param t 
     *            �ַ��� 
     * @return next����ֵ 
     */  
    public static int[] next(char[] t) {  
        int[] next = new int[t.length];  
        next[0] = -1;  
        int i = 0;  
        int j = -1;  
        while (i < t.length - 1) {  
            if (j == -1 || t[i] == t[j]) {  
                i++;  
                j++;  
                if (t[i] != t[j]) {  
                    next[i] = j;  
                } else {  
                    next[i] = next[j];  
                }  
            } else {  
                j = next[j];  
            }  
        }  
        return next;  
    }  
    
    private static int[] getNext(char T[])
    {
    	int next[] = new int[T.length];
	    next[1]=0;
	    int j=1,k=0;
	    while(j<T[0])
		    if((k==0)||(T[j]==T[k]))
		    {
			    j++;
			    k++;
			    next[j]=k;
		    }
		    else 
		    	k=next[k];
	    return next;
    }
    
    public static int getIndex(char str[], char pat[]) {
        int i = 0, j = 0;
        while (i < str.length && j < pat.length) {
            if (str[i] == pat[j]) {
               i++;
               j++;
            } else {
               i = i - j + 2; // ���ص��Ӵ���λ��Ӧ�������ַ�
               j = 0;
            }
        }
        if (j == pat.length) {
            return i - j + 1;
        }
        return -1;
     }   
    
    public static boolean getNextArray(char[] pat, int[] next) {
    	// next[1]��ֵ����ᣬĬ��Ϊ1
    	       if (next.length < pat.length || next.length == 0) {
    	           return false;
    	       }
    	       int i = 1, j = 0;
    	       next[0] = -1;
    	       while( i < pat.length-1 ) {
    	           if( j == -1 || pat[i] == pat[j] ) {
    	              i++;
    	              j++;
    	              next[i] = j;
    	           } else {
    	              j = next[j];
    	           }
    	       }
    	       for (int k = 0; k < next.length; k++) {
    	           next[k] = next[k] + 1;
    	       }
    	       return true;
    	    }
    
    
    public static boolean getNextArray2(char[] pat, int[] next) {
        if (next.length < pat.length || next.length == 0) {
            return false;
        }
        int i = 1, j = 0;
        next[0] = -1;
        if( pat.length > 1 && next.length > 1 ) {
            if( pat[1] == pat[0] ) {
               next[1] = next[0];
            }
        }
        while( i < pat.length-1 ) {
            if( j == -1 || pat[i] == pat[j] ) {
               i++;
               j++;
               if( pat[i] == pat[j] ) {
                   next[i] = next[j];
               } else {
                   next[i] = j;
               }
            } else {
               j = next[j];
            }
        }
        for (int k = 0; k < next.length; k++) {
            next[k] = next[k] + 1;
        }
        return true;
     }   
	
    static void makeNext(char P[],int next[])
    {
        int q,k;//q:ģ���ַ����±ꣻk:���ǰ��׺����
        int m = P.length;//ģ���ַ�������
        next[0] = 0;//ģ���ַ����ĵ�һ���ַ������ǰ��׺����Ϊ0
        for (q = 1,k = 0; q < m; ++q)//forѭ�����ӵڶ����ַ���ʼ�����μ���ÿһ���ַ���Ӧ��nextֵ
        {
            while(k > 0 && P[q] != P[k])//�ݹ�����P[0]������P[q]��������ͬ��ǰ��׺����k
                k = next[k-1];          //�����û��ϵ������ķ��������whileѭ�������δ���ľ������ڣ�ȷʵ�������  
            if (P[q] == P[k])//�����ȣ���ô�����ͬǰ��׺���ȼ�1
            {
                k++;
            }
            next[q] = k;
        }
    }
    
    static void getnextc(char[] str, int[] next)
    {
    	int len = str.length;
        int i = 0,j = -1;
        next[0] = -1;
        while(i<len)
        {
            if(j == -1 || str[i] == str[j])
            {
                i++;
                j++;
                next[i] = j;
            }
            else
                j = next[j];
        }
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] sourceString = new String("guabcdefgabnabcdefgabcchibudaoabcdefgabcqp").toCharArray();
		char[] searchString = new String("abcdefgabc").toCharArray();
//		Solution solution = new Solution();
//		solution.partMatchTable = new int[searchString.length];
//		solution.initPartMatchTable(searchString);
//		for (int i = 0; i < partMatchTable.length; i++) {
//			System.out.println("PartmatchTable Entry : "+ partMatchTable[i]);
//		}
//		solution.kmpAlg(sourceString, searchString);
		int[] next = new int[searchString.length+1];//getNext(searchString);
		getnextc(searchString, next);
		for (int i = 0; i < next.length; i++) {
			System.out.println(next[i]);
		}
	}

}
