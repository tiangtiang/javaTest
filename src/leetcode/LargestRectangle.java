package leetcode;

import java.util.Stack;

/**
 * Created by lianglab on 2018/4/2.
 */
public class LargestRectangle {

    /**
     * Given n non-negative integers representing the histogram's bar
     * height where the width of each bar is 1, find the area of largest rectangle in the histogram.
     * For example,
        Given heights = [2,1,5,6,2,3],
        return 10.
     * 当数组是单调上升的时候，比如说heights = [1, 2, 3, 4, 5]那么我们只需要比较5*1,4*2,3*3, 2*4和1*5的大小即可
     * 所以我们用一个栈，维护一个非递减序列。只要当前元素不比栈顶元素小，就说明该元素尚未到达最大矩形的边界，就需要将该元素压入栈中
     * 当该元素比栈顶元素小的时候，就需要将栈顶元素弹出，并计算弹出元素构成的最大矩形面积。
     * 例如栈中目前是[2, 5, 6],新元素是3，那么就应该先弹出6，此时最大的面积是6*1，然后弹出5,此时最大面积是6*1和5*2（因为6肯定大于等于5）
     *      然后需要将同等数量的3压入栈中，因为此时最大的高度就是3了，栈变成了[2, 3, 3, 3]
     *  如果到最后栈不为空的话，就将栈中元素依次弹出，因为是非递减的，所以可以用开头的方法选择出最大元素
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<heights.length;i++){
            if(stack.isEmpty()) {
                stack.push(heights[i]);
                continue;
            }
            if(heights[i] >= stack.peek()){
                stack.push(heights[i]);
            }else{
                int smallCount = 0;
                while(!stack.isEmpty() && heights[i]<stack.peek()){
                    result = Integer.max(result, (smallCount+1)*stack.pop());
                    smallCount++;
                }
                for(int j=0;j<=smallCount;j++){
                    stack.push(heights[i]);
                }
            }
        }
        if(!stack.isEmpty()){
            int count = 1;
            while(!stack.isEmpty()){
                result = Integer.max(result, count*stack.pop());
                count++;
            }
        }
        return result;
    }
}
