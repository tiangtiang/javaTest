package leetcode;

/**
 * Created by tiang on 2018/6/3.
 */
public class BuySellStock {
    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
     design an algorithm to find the maximum profit.

     Note that you cannot sell a stock before you buy one.

     Example 1:

     Input: [7,1,5,3,6,4]
     Output: 5
     Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     Not 7-1 = 6, as selling price needs to be larger than buying price.
     Example 2:

     Input: [7,6,4,3,1]
     Output: 0
     Explanation: In this case, no transaction is done, i.e. max profit = 0.

     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
        int max = 0;
        int base = prices[0];
        for(int i=1;i<prices.length;i++){
            // 下一个元素
            int temp = prices[i];
            // 栈顶元素
            if(temp<base)
                base = temp;
            else{
                max = Integer.max(max, temp - base);
            }
        }
        return max;
    }



    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.

     Design an algorithm to find the maximum profit. You may complete as many transactions as you like
     (i.e., buy one and sell one share of the stock multiple times).

     Note: You may not engage in multiple transactions at the same time
     (i.e., you must sell the stock before you buy again).

     Example 1:

     Input: [7,1,5,3,6,4]
     Output: 7
     Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
     Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     Example 2:

     Input: [1,2,3,4,5]
     Output: 4
     Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
     engaging multiple transactions at the same time. You must sell before buying again.
     Example 3:

     Input: [7,6,4,3,1]
     Output: 0
     Explanation: In this case, no transaction is done, i.e. max profit = 0.

     * @param prices
     * @return
     */
    public int maxProfitII(int[] prices) {
        if(prices == null || prices.length == 0)
            return 0;
        int max = 0, tempMax = 0, tempBig = -1;
        int base = prices[0];

        for(int i=1;i<prices.length;i++) {
            // 下一个元素
            int temp = prices[i];
            // 如果是递减的，就直接赋值给左节点
            if (temp > tempBig && temp < base)
                base = temp;
            else {
                // 如果右节点是持续递增的，计算当前的最大值
                if(temp>tempBig){
                    tempBig = temp;
                    tempMax = temp - base;
                }else{
                    // 如果出现了转折，最大值加，重新设置左节点
                    max += tempMax;
                    tempMax = 0;
                    base = temp;
                    tempBig = -1;
                }
            }
        }
        if(tempMax!= 0)
            max += tempMax;
        return max;
    }


    public int maxProfitIII(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
        int max = 0;
        int base = prices[0];
        for(int i=1;i<prices.length;i++){

        }
        return max;
    }
}
