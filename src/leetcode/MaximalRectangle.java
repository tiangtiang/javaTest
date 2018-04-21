package leetcode;

import java.util.Arrays;

/**
 * *  Given a 2D binary matrix filled with 0's and 1's,
 *  find the largest rectangle containing only 1's and return its area.
 For example, given the following matrix:
 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0
 Return 6.
 * Created by lianglab on 2018/4/9.
 */
public class MaximalRectangle {
    /**
     利用LargestRectangle的方法，遍历矩阵的每一行，计算每一列在该行时的高度，得到一个height[]数组
     利用求直方图中的最大矩形面积的方法，直接提交每一行的height数组，最后得到最大值
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int result = 0;
        LargestRectangle rectangle = new LargestRectangle();
        int height[] = new int[matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j] == '0')
                    height[j] = 0;
                else height[j]++;
            }
            result = Integer.max(result, rectangle.largestRectangleArea(height));
        }
        return result;
    }

    /**
     * 使用动态规划
     * 记录每一行元素所能构成的最大矩形的最左侧坐标left[]，最右侧坐标right[]以及高度height[]
     * 每个元素能构成的最大矩形的面积是max[i] = (right[i]-left[i])*height[i];
     * 每一列中元素满足这个关系
     * height最简单，height[i] = matrix[r][i] == '0'? 0 : height[i-1]+1;
     * left记录当前元素所能构成的最大矩形的最左侧坐标，当row[i] == '0'时，该行最左侧curr_left将变成i+1,而left[i] = 0;不用担心，此时height[i]也为0
     *              当row[i] == '1'时，需要比较left[i-1]和curr_left，取其中的最大值，因为要保证矩形区域内必须为1，所以要使区间尽可能小
     * 同理，right[i] = row[i] == '0' ? curr_right = i, right[i] = row.length : min(curr_right, right[i-1])
     * @param matrix
     * @return
     */
    public int maximalRectangleDp(char[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int result = 0;
        int m = matrix[0].length;
        int[] left = new int[m],
                right = new int[m],
                height = new int[m];
        Arrays.fill(right, m);
        for(int i=0;i<matrix.length;i++){
            int current_left = 0,
                    current_right = m;
            for(int j=0;j<m;j++){
                if(matrix[i][j] == '0')
                    height[j] = 0;
                else
                    height[j] ++;
            }
            for(int j=0;j<m;j++){
                if(matrix[i][j] == '0') {
                    current_left = j + 1;
                    left[j] = 0;
                }else
                    left[j] = Integer.max(current_left, left[j]);
            }
            for(int j=m-1;j>=0;j--){
                if(matrix[i][j] == '0') {
                    current_right = j;
                    right[j] = m;
                }else
                    right[j] = Integer.min(current_right, right[j]);
            }
            for(int j=0;j<m;j++){
                result = Integer.max(result, (right[j]-left[j])*height[j]);
            }
        }
        return result;
    }
}
