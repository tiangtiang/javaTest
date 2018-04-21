package leetcode;

/**
 * Created by lianglab on 2018/4/20.
 * 背包问题
 */
public class PackProblem {
    /**
     * 01背包问题
     * 依次放入
     * @param weight
     * @param value
     * @param maxWeight
     * @return
     */
    public int singlePack(int[] weight, int[] value, int maxWeight){
        int[] maxValue = new int[maxWeight];
        //依次加入商品
        for(int j=0;j<weight.length;j++) {
            int[] newMaxValue = new int[maxWeight];
            //添加该商品之后，i容量下物品的最大价值变化
            for (int i = 0; i < maxWeight; i++) {
                if (weight[j] <= i + 1) {
                    //假如放入的话，剩余容量
                    int left = i + 1 - weight[j];
                    //比较不放入此商品时最大价值（即上一次的最大价值）以及
                    // 放入此商品时的最大价值（该商品的价值+剩余空间的最大价值（此时必须保证该商品未放入，因此用上一次的数组））
                    //比较二者的最大值
                    newMaxValue[i] = Integer.max(maxValue[i], value[j] + (left == 0 ? 0 : maxValue[left - 1]));
                }
                //假如不放入的话，最大价值跟该商品以前一样
                else
                    newMaxValue[i] = maxValue[i];
            }
            maxValue = newMaxValue;
        }
        return maxValue[maxWeight-1];
    }

    /**
     * 完全背包问题
     * @param weight
     * @param value
     * @param maxWeight
     * @return
     */
    public int completePack(int[] weight, int[] value, int maxWeight){
        int[] maxValue = new int[maxWeight];
        //依次添加商品
        for(int i=0;i<weight.length;i++) {
            int[] newMaxValue = new int[maxWeight];
            //依次添加背包容量
            for (int j = 0; j < maxWeight; j++) {
                if (weight[i] > j + 1)
                    //无法放入
                    newMaxValue[j] = maxValue[j];
                else {
                    //需要放入的话，最多可以放入多少份
                    int copy = (j + 1) / weight[i];
                    int max = 0;
                    for (int t = 1; t <= copy; t++) {
                        //将t份商品放入之后剩余重量
                        int left = j + 1 - weight[i] * t;
                        //将t份商品放入之后，背包中的最大价值
                        int leftValue = left == 0 ? 0 : maxValue[left - 1];
                        //比较放入t份的最大价值跟放入t份以前的最大价值，取最大者
                        max = Integer.max(max, leftValue + value[i] * t);
                    }
                    //比较不放入i商品的最大价值，与放入i商品之后的最大价值
                    newMaxValue[j] = Integer.max(max, maxValue[j]);
                }
            }
            maxValue = newMaxValue;
        }
        return maxValue[maxWeight-1];
    }
}
