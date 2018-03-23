package leetcode;

/**
 * Created by lianglab on 2018/2/28.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int lastSum = Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(lastSum<=0)
                lastSum =nums[i];
            else
                lastSum+=nums[i];
            if(lastSum>maxSum)
                maxSum = lastSum;
        }
        return maxSum;
    }
}
