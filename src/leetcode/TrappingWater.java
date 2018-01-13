package leetcode;

/**
 * Created by lianglab on 2018/1/11.
 */
public class TrappingWater {
    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
     For example,
     Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     主要思路是所有空缺位置灌满水之后形成的是一个塔状结构，找到塔顶所在的位置，两侧都是像塔顶增长的。
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if(height.length<2)
            return 0;
        int middle = 0;
        int lastMax = height[0];
        for(int i=1;i<height.length;i++){
            if(lastMax <height[i]){
                lastMax = height[i];
                middle = i;
            }
        }
        int sum = 0, last = height[0];
        for(int i=1;i<middle;i++){
            if(height[i]>last){
                last = height[i];
            }else
                sum += last - height[i];
        }
        last = height[height.length-1];
        for(int j=height.length-2;j>middle;j--){
            if(height[j]>last){
                last = height[j];
            }else
                sum += last - height[j];
        }
        return sum;
    }
}
