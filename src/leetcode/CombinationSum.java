package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lianglab on 2018/1/10.
 */
public class CombinationSum {
    /**
     * Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
     The same repeated number may be chosen from C unlimited number of times.
     Note:
     All numbers (including target) will be positive integers.
     The solution set must not contain duplicate combinations.
     For example, given candidate set [2, 3, 6, 7] and target 7,
     A solution set is:
     [[7],
     [2, 2, 3]]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //先进行排序
        Arrays.sort(candidates);
        //基本思路就是，f[0,n] = sum => f[1, n]=>sum...f[n,n]=>sum
        return getCombination(candidates, target, 0);
    }

    /**
     * 递归寻找符合条件的list
     * @param candidates
     * @param target
     * @param index
     * @return
     */
    private List<List<Integer>> getCombination(int[] candidates, int target, int index){
        List<List<Integer>> result = new ArrayList<>();
        for(int i=index;i<candidates.length;i++){
            //差值
            int left = target-candidates[i];
            //如果小于0，之后的差值都会小于0，所以直接跳出循环
            if(left<0)
                break;
            else if(left == 0){
                //等于0，说明当前值为所需的，可以直接返回了
                List<Integer> list = new ArrayList<>();
                list.add(candidates[i]);
                result.add(list);
                return result;
            }else {
                //大于0，则说明可再进行分割，下次分割的起点为当前位置
                List<List<Integer>> list = getCombination(candidates, left, i);
                for (List<Integer> temp : list) {
                    temp.add(0, candidates[i]);
                    result.add(temp);
                }
            }
        }
        return result;
    }

    /**
     * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
     Each number in C may only be used once in the combination.
     Note:
     All numbers (including target) will be positive integers.
     The solution set must not contain duplicate combinations.
     For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
     A solution set is:
     [
     [1, 7],
     [1, 2, 5],
     [2, 6],
     [1, 1, 6]
     ]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        return getCombinations2(candidates, target, 0);
    }
    private List<List<Integer>> getCombinations2(int[] candidates, int target, int index){
        List<List<Integer>> result = new ArrayList<>();
        int lastValue = -1;
        for(int i=index;i<candidates.length;i++){
            //下次遍历从不重复的数字开始
            if(candidates[i] == lastValue)
                continue;
            else
                lastValue = candidates[i];
            int left = target - candidates[i];
            if(left<0)
                break;
            else if(left == 0){
                List<Integer> list = new ArrayList<>();
                list.add(candidates[i]);
                result.add(list);
            }else{
                //下层遍历从当前位置的下一个位置开始
                List<List<Integer>> list = getCombinations2(candidates, left, i+1);
                for (List<Integer> temp : list) {
                    temp.add(0, candidates[i]);
                    result.add(temp);
                }
            }
        }
        return result;
    }
}
