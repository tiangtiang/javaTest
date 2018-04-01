package leetcode;

/**
 * Created by lianglab on 2018/3/24.
 */
public class SetMatrixZeroes {
    /**
     *  Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
     Did you use extra space?
     A straight forward solution using O(mn) space is probably a bad idea.
     A simple improvement uses O(m + n) space, but still not the best solution.
     Could you devise a constant space solution?

     先记录第一行第一列是否包含0，然后遍历除第一行以外的每一行，如果该行含0，将第一列该行数字置为0
     遍历除第一列以外的每一列，如果该列含0，将第一行该列数字置为0,
     遍历第一行（从第二列开始），将数字为0的那一列置为0
     遍历第一列（从第二行开始），将数字为0的那一行置为0
     根据一开始记录的第一行第一列是否含0，将第一行第一列置为响应的结果

     题目最大的难度在与保留原有的数据
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        //标记第一行第一列是否有0
        boolean firstRow = false, firstCol = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }
        for (int i = 0; i < matrix[0].length; i++)
            if (matrix[0][i] == 0) {
                firstRow = true;
                break;
            }
        //将第一行第一列对应位置的置为0
        for(int i=1;i<matrix.length;i++){
            for(int j=1;j<matrix[0].length;j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        //将对应的行置为0
        for(int i=1;i<matrix.length;i++){
            if(matrix[i][0] == 0){
                for(int j=1;j<matrix[i].length;j++){
                    matrix[i][j] = 0;
                }
            }
        }
        //将对应的列置为0
        for(int i=1;i<matrix[0].length;i++){
            if(matrix[0][i] == 0){
                for(int j=1;j<matrix.length;j++){
                    matrix[j][i] = 0;
                }
            }
        }
        //根据第一行第一列修改第一行第一列的值
        if(firstCol){
            for(int i=0;i<matrix.length;i++){
                matrix[i][0] = 0;
            }
        }
        if(firstRow){
            for(int i=0;i<matrix[0].length;i++){
                matrix[0][i] = 0;
            }
        }
    }
}
