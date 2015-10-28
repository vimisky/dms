package com.vimisky.alg;

public class TTSolution {

	

	private static int getLayer(int num){
        int res = 1;
        int last = 0;
        int next = 0;
        if(num == 0){
            return 0;
        }
        last = (int)((Math.pow(3, res) - 3.0) / 2.0);
        while(true){
            next = (int)((Math.pow(3, res+1) - 3.0) / 2.0);
            if(num > last && num <= next){
                return res;
            }
            ++res;
            last = next;
        }
    }
	
    private static int getMid(int layer){
        if(layer > 0){
            int sum = 0;
            for(int i = 1; i < layer; ++i){
                sum += Math.pow(3, i);
            }
            sum += (int)((Math.pow(3, layer) + 1.0) / 2.0);
            return sum;
        }
        else{
            return 0;
        }
    }
    
    private static int getCommon(int num2Lay, int num1Lay, int num2, int num1){
        //层数对齐
    	while(num1Lay != num2Lay){
            if(num2 % 3 == 0){
                --num2;
            }
            num2 /= 3;
            --num2Lay;
            //Each time the layer is decreased, we need to convert the number
            num2 = 2 * getMid(num2Lay) - num2;
        }

        //一起往上找父节点
    	if(num2 == num1){
            return num1; }   //final answer        }
        else{
            while(num2 != num1){
                if(num2 % 3 == 0){
                    --num2;
                }
                if(num1 % 3 == 0){
                    --num1;
                }
                num2 /= 3;
                num1 /= 3;
                //Since num2Lay == num1Lay, only one of them is needed.
                //--num2Lay;
                --num1Lay;
                //Each time the layer is decreased, we need to convert the number.
                int mid = 2 * getMid(num1Lay);
                num2 = mid - num2;
                num1 = mid - num1;
            }
            return num1;
        }
    }
    
	public static int commonAncestor(){
		int height = (int) (Math.log(13)/Math.log(3));
		System.out.println(height);
		return 0;
	}	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
