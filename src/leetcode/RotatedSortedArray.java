package leetcode;

/**
 * Created by lianglab on 2018/1/4.
 */
public class RotatedSortedArray {
    public int search(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            if(target == nums[i])
                return i;
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0)
            return new int[]{-1, -1};
        int start = 0, end = nums.length;
        boolean flag = false;
        while(start<=end){
            int middle = (start+end)/2;
            if(middle>=nums.length)
                break;
            if(nums[middle] == target){
                start = middle;
                flag = true;
                break;
            }else if(nums[middle] < target){
                start = middle+1;
            }else{
                end = middle-1;
            }
        }
        int[] res = new int[]{-1, -1};
        if(flag){
            int i = 0;
            while(start-i>=0){
                if(nums[start-i]==target){
                    i++;
                }else
                    break;
            }
            res[0] = start-i+1;
            i = 0;
            while(start+i< nums.length){
                if(nums[start+i] == target){
                    i++;
                }else
                    break;
            }
            res[1] = start+i-1;
        }
        return res;
    }
    public int searchInsert(int[] nums, int target) {
        if(nums.length == 0)
            return 0;
        int left = 0, right = nums.length;
        while(left<=right){
            int middle = left+(right-left)/2;
            if(middle>=nums.length)
                break;
            if(nums[middle] == target)
                return middle;
            else if(nums[middle]<target)
                left = middle+1;
            else
                right = middle - 1;
        }
        return left;
    }
}
