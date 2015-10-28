package com.vimisky.alg;

import org.springframework.data.repository.cdi.Eager;

import com.sun.xml.internal.ws.api.pipe.NextAction;

/**
 * 算法练习
 * */
public class KMPSolution {

	private static int[] partMatchTable;
	/**
	 * KMP算法
	 * 问题描述：给定字符串srcStr,长度范围为0-1000000，给定查询字符串targetStr。要求从source中查找target，输出完全匹配次数、匹配位置。
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
	 * 初始化部分匹配表
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
     * 获得字符串的next函数值 
     *  
     * @param t 
     *            字符串 
     * @return next函数值 
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
               i = i - j + 2; // 返回到子串首位对应的主串字符
               j = 0;
            }
        }
        if (j == pat.length) {
            return i - j + 1;
        }
        return -1;
     }   
    
    public static boolean getNextArray(char[] pat, int[] next) {
    	// next[1]的值不理会，默认为1
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
        int q,k;//q:模版字符串下标；k:最大前后缀长度
        int m = P.length;//模版字符串长度
        next[0] = 0;//模版字符串的第一个字符的最大前后缀长度为0
        for (q = 1,k = 0; q < m; ++q)//for循环，从第二个字符开始，依次计算每一个字符对应的next值
        {
            while(k > 0 && P[q] != P[k])//递归的求出P[0]···P[q]的最大的相同的前后缀长度k
                k = next[k-1];          //不理解没关系看下面的分析，这个while循环是整段代码的精髓所在，确实不好理解  
            if (P[q] == P[k])//如果相等，那么最大相同前后缀长度加1
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
