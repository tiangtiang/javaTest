package leetcode;

/**
 * Created by lianglab on 2018/3/26.
 */
public class SortColors {
    /**
     *  Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     Note:
     You are not suppose to use the library's sort function for this problem.

     A rather straight forward solution is a two-pass algorithm using counting sort.
     First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

     Could you come up with an one-pass algorithm using only constant space?
     第一遍遍历，记录0,1，2的个数，第二步遍历直接写入
     * @param nums
     */
    public void sortColors(int[] nums) {
        int zeroCount=0, oneCount=0, twoCount=0;
        for(int i : nums)
            switch (i){
                case 0:
                    zeroCount++;
                    break;
                case 1:
                    oneCount++;
                    break;
                case 2:
                    twoCount++;
                    break;
            }
        for(int i=0;i<nums.length;i++){
            if(i<zeroCount)
                nums[i] = 0;
            else if(i<zeroCount+oneCount)
                nums[i] = 1;
            else if(i<zeroCount+oneCount+twoCount)
                nums[i] = 2;
        }
    }

    public void sortColorsOnePass(int[] nums){
        int left = 0, right = nums.length-1, i=0;
        while(i<=right){
            if(nums[i] == 0){
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
                left++;
                i++;
            }else if(nums[i] == 2){
                int temp = nums[right];
                nums[right] = nums[i];
                nums[i] = temp;
                right--;
            }else{
                i++;
            }
        }
    }
}
