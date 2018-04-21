package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lianglab on 2018/4/10.
 */
public class SubSetII {
    /**
     *  Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
     Note: The solution set must not contain duplicate subsets.
     For example,
     If nums = [1,2,2], a solution is:
     [
     [2],
     [1],
     [1,2,2],
     [2,2],
     [1,2],
     []
     ]
        首先进行排序，确保相同元素位置相邻
     每一次遍历记录上次数据未添加的时候结果集的大小，因为相同元素的话，需要从这个位置重新开始遍历，避免重复
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> init = new ArrayList<>();
        result.add(init);
        int lastCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            //如果跟前一个元素相同，那么从上上次结果集的长度位置开始，因为上次已经将之前的添加过一遍了
            if (i - 1 >= 0 && nums[i] == nums[i - 1])
                temp = lastCount;
            //提前记录结果集的长度，本次添加之前的长度
            lastCount = result.size();
            //遍历指定区间，将不重复的元素添加新的元素之后存入结果集
            for (int j = temp; j < lastCount; j++) {
                List<Integer> l = new ArrayList<>(result.get(j));
                l.add(nums[i]);
                result.add(l);
            }
        }
        return result;
    }
}
