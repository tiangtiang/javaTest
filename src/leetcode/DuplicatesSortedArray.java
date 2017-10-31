package leetcode;

/**
 * Created by tiang on 2017/10/31.
 * sorted array problem
 */
public class DuplicatesSortedArray {
    /**
     * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     * For example,
     * Given input array nums = [1,1,2],
     * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
     *
     * @param nums sorted array
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int result = 1, lastNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == lastNum)
                continue;
            nums[result] = nums[i];
            result++;
            lastNum = nums[i];
        }
        return result;
    }

    /**
     * Given an array and a value, remove all instances of that value in place and return the new length.
     Do not allocate extra space for another array, you must do this in place with constant memory.
     The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     Example:
     Given input array nums = [3,2,2,3], val = 3
     Your function should return length = 2, with the first two elements of nums being 2.
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int result = 0;
        return result;
    }
}
