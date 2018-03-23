package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianglab on 2017/12/6.
 */
public class Permutation {
    /**
     * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
     * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
     * The replacement must be in-place, do not allocate extra memory.
     * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * 简而言之就是将这些数字重新排列，然后寻找最小的大于原数字的数字组合
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums.length < 1)
            return;
        int lastNum = nums[nums.length - 1];
        int i;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= lastNum)
                lastNum = nums[i];
            else
                break;
        }
        if (i < 0) {
            //需要逆序
            sort(nums, 0, nums.length);
        } else {
            int j;
            for (j = i + 1; j < nums.length; j++) {
                int tempGap = nums[j] - nums[i];
                if (tempGap <= 0) {
                    swap(nums, i, j - 1);
                    break;
                }
            }
            if (j == nums.length) {
                swap(nums, i, nums.length - 1);
            }
            sort(nums, i + 1, nums.length);
        }
    }

    private void sort(int[] nums, int start, int end) {
        for (int i = start; i < (end + start) / 2; i++) {
            swap(nums, i, end - i + start - 1);
        }
    }

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    /**
     * The set [1,2,3,…,n] contains a total of n! unique permutations.
     By listing and labeling all of the permutations in order,
     We get the following sequence (ie, for n = 3):
     "123"
     "132"
     "213"
     "231"
     "312"
     "321"
     Given n and k, return the kth permutation sequence.
     用一个列表存储尚未排列的数字，从左到右递增
     思路就是递归，n-1的排列组合有(n-1)!次，那么第k次的话n-1的排列组合应该是排了k/(n-1)!次，那么第1个数字就是k%(n-1)!
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        return getPermutation(n, k - 1, "", list);
    }

    private String getPermutation(int n, int k, String result, List<Integer> left) {
        if (n == 1) {
            result += left.get(0);
            return result;
        }
        int fac = getFactorial(n - 1);
        int temp = k / fac;
        int index = temp % left.size();
        result += left.get(index);
        left.remove(index);
        return getPermutation(n - 1, k % fac, result, left);

    }

    private int getFactorial(int n) {
        if (n == 0 || n == 1) return 1;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
