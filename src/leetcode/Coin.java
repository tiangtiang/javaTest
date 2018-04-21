package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lianglab on 2018/4/20.
 */
public class Coin {
    private final int num = 1000000007;
    public int getCount(int[] normal, int[] souvenir, int aim){
        Map<Integer, Integer> temp = new HashMap<>();
        int count = 0;
        for(int i=0;i<souvenir.length;i++){
            
        }
        return getCount(normal, aim, temp);
    }

    private int getCount(int[] normal, int aim, Map<Integer, Integer> temp){
        if(temp.containsKey(aim))
            return temp.get(aim);
        int count = getCount(normal, aim);
        temp.put(aim, count);
        return count;
    }
    private int getCount(int[] normal, int aim){
        int[] maxCount = new int[aim+1];
        for(int i=0;i<normal.length;i++){
            int[] newCount = new int[aim+1];
            for(int j=1;j<maxCount.length;j++){
                int tempCount = maxCount[j];
                int copy = j/normal[i];
                for(int t=1;t<=copy;t++) {
                    int left = j - normal[i] * t;
                    int leftValue = left < 0 ? 0 : left == 0 ? 1 : maxCount[left];
                    tempCount += leftValue;
                    if (tempCount > num)
                        tempCount %= num;
                }
                newCount[j] = tempCount;
            }
            maxCount = newCount;
        }
        return maxCount[aim];
    }
}
