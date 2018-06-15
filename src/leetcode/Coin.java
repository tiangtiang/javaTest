package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lianglab on 2018/4/20.
 */
public class Coin {
    private final int num = 1000000007;
    public int getCount(int[] normal, int[] souvenir, int aim){
        Map<Integer, Integer> temp = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int count = getCount(normal, aim, temp);
        list.add(aim);
        for(int i=0;i<souvenir.length;i++){
            List<Integer> tempList = new ArrayList<>();
            for(int j=0;j<list.size();j++){
                int tempcount = getCount(normal, list.get(j)-souvenir[i], temp);
                if(tempcount>0)
                    tempList.add(list.get(j)-souvenir[i]);
                count += tempcount;
                if(count>num)
                    count %= num;
            }
            list.addAll(tempList);
        }
        return count;
    }

    private int getCount(int[] normal, int aim, Map<Integer, Integer> temp){
        if(aim<normal[0])
            return 0;
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

    public int resolve(int[] a, int n1, int n2, int m){
        int[][] num = new int[n1+n2][m+1];
        for (int i = 0; i < num[0].length; i++) {
            num[0][i] = i%a[0] == 0?1:0;        //判断第一个商品的时候能否正好构成这些元素
        }
        for (int i = 0; i < num.length; i++) {
            num[i][0] = 1;
        }
        return resolve(a, n1, n2, m, num);
    }

    private int resolve(int[] a,int n1,int n2,int m,int[][] f) {
        //完全背包部分
        for(int i = 1;i < n1;i++) {
            for(int j = 1;j<=m;j++) {
                if(j>=a[i]) {
                    f[i][j] = f[i-1][j]+f[i][j-a[i]];
                }else {
                    f[i][j] = f[i-1][j];
                }
            }
        }
        //经典01背包部分
        for(int i = n1;i<n1+n2;i++) {
            for(int j = 1;j<=m;j++) {
                if(j>=a[i]) {
                    f[i][j] = f[i-1][j]+f[i-1][j-a[i]];
                }else {
                    f[i][j] = f[i-1][j];
                }
            }
        }
        for(int i = 0; i<n1+n2;i++) {
            for(int j = 0;j<=m;j++) {
                System.out.print(f[i][j]+" ");
            }
            System.out.println();
        }
        return f[n1+n2-1][m];
    }

}
