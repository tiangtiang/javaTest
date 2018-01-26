package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lianglab on 2018/1/16.
 */
public class Permutations {
    /**
     *  Given a collection of distinct numbers, return all possible permutations.
     For example,
     [1,2,3] have the following permutations:
     [
     [1,2,3],
     [1,3,2],
     [2,1,3],
     [2,3,1],
     [3,1,2],
     [3,2,1]
     ]
     简单来说就是求排列组合
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        return permute(nums, 0, null);
    }

    /**
     * 采用插入法，比如当前队列为[1],下一个操作的数是2，那么就有两种插入结果[2, 1]和[1, 2]
     * 下一个操作数是3，那么两组就分别有3个插入结果[3, 2, 1],[2, 3, 1],[2, 1, 3]
     * @param nums
     * @param index
     * @param list
     * @return
     */
    private List<List<Integer>> permute(int[] nums, int index, List<List<Integer>> list) {
        if (index == nums.length)
            return list;
        int num = nums[index];
        List<List<Integer>> result = new ArrayList<>();
        if (list != null)
            for (int i = 0; i < list.size(); i++) {
                int size = list.get(i).size();
                for (int j = 0; j <= size; j++) {
                    List<Integer> tempList = new ArrayList<>(list.get(i));
                    tempList.add(j, num);
                    result.add(tempList);
                }
            }
        else {
            List<Integer> tempList = new ArrayList<>();
            tempList.add(num);
            result.add(tempList);
        }
        return permute(nums, index + 1, result);
    }

    public List<List<Integer>> permuteUniqueError(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(result.size() == 0){
                List<Integer> tempList = new ArrayList<>();
                tempList.add(nums[i]);
                result.add(tempList);
            }else{
                List<List<Integer>> list = new ArrayList<>();
                for(int j=0;j<result.size();j++){
                    for(int k=0;k<=result.get(j).size();k++){
                        if(k<result.get(j).size() && nums[i] == result.get(j).get(k))
                            continue;
                        List<Integer> tempList = new ArrayList<>(result.get(j));
                        tempList.add(k, nums[i]);
                        list.add(tempList);
                    }
                }
                result = list;
            }
        }
        return result;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums==null || nums.length==0) return res;
        boolean[] used = new boolean[nums.length];
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums);
        dfs(nums, used, list, res);
        return res;
    }

    public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res){
        if(list.size()==nums.length){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(used[i]) continue;
            if(i>0 &&nums[i-1]==nums[i] && !used[i-1]) continue;
            used[i]=true;
            list.add(nums[i]);
            dfs(nums,used,list,res);
            used[i]=false;
            list.remove(list.size()-1);
        }
    }
}
