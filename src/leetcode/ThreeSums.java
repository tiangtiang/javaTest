package leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tiang on 2017/10/27.
 * Given an array S of n integers,
 * are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 */
public class ThreeSums {
    public List<List<Integer>> threeSum(int[] nums) {

        subList(nums, 3, 0);

//        List<List<Integer>> result = new ArrayList<>();
//        for(int i=0;i<resultList.size();i++){
//            if(resultList.get(i).stream().reduce(0, Integer::sum) == 0){
//                List<Integer> temp = resultList.get(i);
//                temp.sort(null);
//                long count = result.stream().filter(list->{
//                    for(int j=0;j<list.size();j++){
//                        if(list.get(j) != temp.get(j))
//                            return false;
//                    }
//                    return true;
//                }).count();
//                if(count == 0)
//                    result.add(temp);
//            }
//        }
        return resultList;
    }

    private List<List<Integer>> resultList = new ArrayList<>();

    /**
     * 列出集合的所有的排列组合
     * @param nums
     * @param count
     */
    public void subList(int[] nums, int count, int target){
        int[] subNums = new int[count];
        subList(nums, subNums, count-1, nums.length, count, 0, target);
    }

    private void subList(int[] nums, int[] subnums, int start, int end, int count, int sum, int target){
        for(int i=start;i<end;i++){
            if(count == 1){
                if(sum+nums[i] == target){
                    subnums[subnums.length-count] = nums[i];
                    List<Integer> temp = Arrays.stream(subnums).boxed().collect(Collectors.toList());
                    temp.sort(null);
//                    long sameCount = resultList.stream().filter(list->{
//                    for(int j=0;j<list.size();j++){
//                        if(list.get(j).intValue() == temp.get(j).intValue())
//                            return false;
//                    }
//                    return true;
//                }).count();
//                    if(sameCount == 0)
                    if(!isSameList(resultList, temp))
                        resultList.add(temp);
                }
            }else {
                subnums[subnums.length - count] = nums[i];
                subList(nums, subnums, start - 1, i, count - 1, sum + nums[i], target);
            }
        }
    }

    private boolean isSameList(List<List<Integer>> resultList, List<Integer> list){
        for(int i=0;i<resultList.size();i++){
            if(isSame(resultList.get(i), list))
                return true;
        }
        return false;
    }
    private boolean isSame(List<Integer> temp, List<Integer> list){
        for(int i=0;i<temp.size();i++){
            if(temp.get(i).intValue() != list.get(i).intValue())
                return false;
        }
        return true;
    }

    public List<List<Integer>> threeSums(int[] num, int target, int fourth) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length-2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i-1])) {
                int lo = i+1, hi = num.length-1, sum = target - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi], fourth));
                        while (lo < hi && num[lo] == num[lo+1]) lo++;
                        while (lo < hi && num[hi] == num[hi-1]) hi--;
                        lo++; hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0;i<nums.length-3;i++){
            if(i>=1 && nums[i-1] == nums[i])
                continue;
            int[] temp = new int[nums.length-i-1];
            System.arraycopy(nums, i+1, temp, 0, temp.length);
            List<List<Integer>> tempList = threeSums(temp, target-nums[i], nums[i]);
            for(int j=0;j<tempList.size();j++){
                tempList.get(j).sort(null);
                if(!isSameList(result, tempList.get(j))){
                    result.add(tempList.get(j));
                }
            }
        }
        return result;
//        subList(nums, 4, target);
//        return resultList;
    }
}
