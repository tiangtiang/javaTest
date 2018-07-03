package leetcode;

/**
 * Created by tiang on 2018/6/16.
 * 硬币换钱问题，输入一个零钱列表，一个目标钱数，每个零钱无数量限制。输出一共多少中组合方法
 */
public class CoinProblem {
    public int getCount(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim<0)
            return 0;
        return getCount(arr, 0, aim);
    }

    private int getCount(int[] arr, int index, int aim){
        int result = 0;
        if(index == arr.length)
            return aim == 0?1:0;
        else{
            for(int i=0;arr[index]*i<=aim;i++){
                result += getCount(arr, index+1, aim-arr[index]*i);
            }
        }
        return result;
    }
}
