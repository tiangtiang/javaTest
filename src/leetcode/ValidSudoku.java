package leetcode;

/**
 * Created by lianglab on 2018/1/5.
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        return validRow(board) && validCol(board) && validBox(board);
    }
    private boolean validRow(char[][] board){
        for(int i=0;i<board.length;i++) {
            int[] nums = new int[9];
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.')
                    continue;
                if (nums[Character.getNumericValue(board[i][j])-1] == 0)
                    nums[Character.getNumericValue(board[i][j])-1] = 1;
                else
                    return false;
            }
        }
        return true;
    }

    private boolean validCol(char[][] board){
        for(int i=0;i<board[0].length;i++){
            int[] nums = new int[9];
            for(int j=0;j<board.length;j++){
                if(board[j][i] == '.')
                    continue;
                if(nums[Character.getNumericValue(board[j][i])-1] == 0)
                    nums[Character.getNumericValue(board[j][i])-1] = 1;
                else
                    return false;
            }
        }
        return true;
    }

    private boolean validBox(char[][] board){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int[] nums = new int[9];
                for(int k=i*3;k<(i+1)*3;k++){
                    for(int t=j*3;t<(j+1)*3;t++){
                        if(board[k][t] == '.')
                            continue;
                        if(nums[Character.getNumericValue(board[k][t])-1] == 0)
                            nums[Character.getNumericValue(board[k][t])-1] = 1;
                        else
                            return false;
                    }
                }
            }
        }
        return true;
    }
}
