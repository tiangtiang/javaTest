package leetcode;

/**
 * Created by lianglab on 2018/1/10.
 */
public class MissingPositive {
    /**
     * Given an unsorted integer array, find the first missing positive integer.
     For example,
     Given [1,2,0] return 3,
     and [3,4,-1,1] return 2.
     Your algorithm should run in O(n) time and uses constant space.
     思路就是运用数组的交换不创造新的空间，将数组变成arr[i] = i+1的样子，然后从前往后遍历，寻找第一个不合条件的位置
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while(i<nums.length) {
            if (nums[i] != i + 1) {
                int value = nums[i];
                if (value >= 1 && value <= nums.length && nums[value-1]!=nums[i]) {
                    int temp = nums[value - 1];
                    nums[value - 1] = nums[i];
                    nums[i] = temp;
                    continue;
                }
            }
            i++;
        }
        i = 0;
        while(i<nums.length && nums[i] == i+1) i++;
        return i+1;
    }
}
