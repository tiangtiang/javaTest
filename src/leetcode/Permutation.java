package leetcode;

/**
 * Created by lianglab on 2017/12/6.
 */
public class Permutation {
    /**
     * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
     If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
     The replacement must be in-place, do not allocate extra memory.
     Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
     1,2,3 → 1,3,2
     3,2,1 → 1,2,3
     1,1,5 → 1,5,1
     简而言之就是将这些数字重新排列，然后寻找最小的大于原数字的数字组合
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if(nums.length<1)
            return;
        int lastNum = nums[nums.length-1];
        int i;
        for(i=nums.length-2;i>=0;i--){
            if(nums[i]>=lastNum)
                lastNum = nums[i];
            else
                break;
        }
        if(i<0) {
            //需要逆序
            sort(nums, 0, nums.length);
        }else {
            int j;
            for (j = i + 1; j < nums.length; j++) {
                int tempGap = nums[j] - nums[i];
                if (tempGap <= 0) {
                    swap(nums, i, j-1);
                    break;
                }
            }
            if(j == nums.length){
                swap(nums, i, nums.length-1);
            }
            sort(nums, i+1, nums.length);
        }
    }

    private void sort(int[] nums, int start, int end){
        for(int i=start;i<(end+start)/2;i++){
            swap(nums, i, end-i+start-1);
        }
    }

    private void swap(int[] nums, int first, int second){
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
