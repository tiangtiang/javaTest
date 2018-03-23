package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianglab on 2018/2/28.
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();

        if (matrix.length == 0) {
            return res;
        }

        int rowBegin = 0;
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j ++) {
                res.add(matrix[rowBegin][j]);
            }
            rowBegin++;

            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j ++) {
                res.add(matrix[j][colEnd]);
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j --) {
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;

            if (colBegin <= colEnd) {
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j --) {
                    res.add(matrix[j][colBegin]);
                }
            }
            colBegin ++;
        }

        return res;
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        if(n == 0)return result;
        int rowStart = 0, rowEnd = n-1, colStart = 0, colEnd = n-1;
        int num = 1;
        while(rowStart<=rowEnd && colStart<=colEnd){
            for(int i=colStart;i<=colEnd;i++){
                result[rowStart][i] = num++;
            }
            rowStart ++;
            for(int i=rowStart;i<=rowEnd;i++){
                result[i][colEnd] = num++;
            }
            colEnd--;
            if(rowStart < rowEnd){
                for(int i=colEnd;i>=colStart;i--){
                    result[rowEnd][i] = num++;
                }
                rowEnd--;
            }
            if(colStart<colEnd){
                for(int i=rowEnd;i>=rowStart;i--){
                    result[i][colStart] = num++;
                }
                colStart++;
            }
        }
        return result;
    }
}
