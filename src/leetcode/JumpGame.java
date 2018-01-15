package leetcode;

/**
 * Created by lianglab on 2018/1/15.
 */
public class JumpGame {
    private Integer[] result;

    /**
     *  Given an array of non-negative integers, you are initially positioned at the first index of the array.
     Each element in the array represents your maximum jump length at that position.
     Your goal is to reach the last index in the minimum number of jumps.
     For example:
     Given array A = [2,3,1,1,4]
     The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
     Note:
     You can assume that you can always reach the last index.
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        result = new Integer[nums.length];
        return jump(nums, 0);
    }

    /**
     * 尝试用动态规划做
     * 动归尝试了太多无意义的走法，而且递归的方式很耗空间
     * @param nums
     * @param index
     * @return
     */
    private int jump(int[] nums, int index) {
        if(result[index] != null)
            return result[index];
        if(index == nums.length - 1) return 0;
        if(index+1 == nums.length-1 || nums[index]+index == nums.length-1)
            return 1;
        int min = Integer.MAX_VALUE;
        for(int i = 1; i<=nums[index]; i++){
            if(index+i>=nums.length) break;
            int next = jump(nums, index+i);
            if(next<0)
                continue;
            min = Integer.min(min, next);
        }
        result[index] = min+1;
        return result[index];
    }

    /**
     *  题目的意思是i位置最多可以走nums[i]步，也可以只走1步，也就是可以走[1:nums[i]]步
     *  既然如此的话，那就是获取每一步能到达的最远位置，但是仍然不能忘记该位置之前那些未曾被遍历的节点，因此每个节点都要被遍历一遍
     * @param nums
     * @return
     */
    public int jumpByIterate(int[] nums){
        if(nums==null || nums.length==0)
            return 0;
        //记录当前能到达的最远位置
        int lastReach = 0;
        //每个节点能到达的最远位置
        int reach = 0;
        //到达最远位置的步数
        int step = 0;
        for(int i=0;i<=reach&&i<nums.length;i++)
        {
            //当前指针指向的位置大于最远位置了，那么就更新最原位置
            if(i>lastReach)
            {
                step++;
                lastReach = reach;
            }
            //判断最远位置是否更新
            reach = Math.max(reach,nums[i]+i);
        }
        System.out.println(reach);
        //说明不可达
        if(reach<nums.length-1)
            return 0;
        return step;
    }

    /**
     * 用广度优先遍历
     * 用start和end两个指针指向每一步的起始位置和结束位置。
     * 其实就是把每一步能到达的所有位置当成一层，通过遍历这一层来寻找下一层能到达的最远位置
     * 需要把整个数组遍历一遍，所以时间复杂度是O(n)
     * @param nums
     * @return
     */
    public int jumpByBFS(int[] nums){
        //记录每一层的起始位置，结束位置，层数
        int start = 0,end = 0, level = 0;
        //每次遍历都是进入下一层
        while(end<nums.length-1){
            level++;
            int max = 0;
            //寻找下一层能到达的最远位置
            for(int i=start;i<=end;i++){
                //如果最远位置已经大于数组的长度了，就返回当前层数
                if(nums[i]+i >= nums.length-1) return level;
                //比较当前最远位置，与该位置能到达的最远位置
                max = Integer.max(max, nums[i]+i);
            }
            //重新定义起始位置和结束位置
            start = end+1;
            end = max;
        }
        //返回层数
        return level;
    }
}
