package leetcode;

/**
 * Created by lianglab on 2018/1/5.
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        return validRow(board) && validCol(board) && validBox(board);
    }

    private boolean validRow(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            int[] nums = new int[9];
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.')
                    continue;
                if (nums[Character.getNumericValue(board[i][j]) - 1] == 0)
                    nums[Character.getNumericValue(board[i][j]) - 1] = 1;
                else
                    return false;
            }
        }
        return true;
    }

    private boolean validCol(char[][] board) {
        for (int i = 0; i < board[0].length; i++) {
            int[] nums = new int[9];
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == '.')
                    continue;
                if (nums[Character.getNumericValue(board[j][i]) - 1] == 0)
                    nums[Character.getNumericValue(board[j][i]) - 1] = 1;
                else
                    return false;
            }
        }
        return true;
    }

    private boolean validBox(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] nums = new int[9];
                for (int k = i * 3; k < (i + 1) * 3; k++) {
                    for (int t = j * 3; t < (j + 1) * 3; t++) {
                        if (board[k][t] == '.')
                            continue;
                        if (nums[Character.getNumericValue(board[k][t]) - 1] == 0)
                            nums[Character.getNumericValue(board[k][t]) - 1] = 1;
                        else
                            return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 数独解法
     * @param board
     */
    public void sudokuSolver(char[][] board) {
        int index = findTheNextEmptyIndex(board, 0);
        System.out.println(solver(board, index));
    }

    /**
     * 递归解决数独
     * @param board
     * @param index
     * @return
     */
    private boolean solver(char[][] board, int index) {
        //当前的行号和列号
        int row = index / board.length,
                col = index % board.length;
        //如果当前位置为空
        //todo 遍历从一到9是否存在能满足条件的数独解法，如果不满足，则回退，如果满足则进入下一层
        for (int value = 1; value <= 9; value++) {
            board[row][col] = (char) (value + 48);
            if (validSudoku(board, row, col)) {
                int nextIndex = findTheNextEmptyIndex(board, index+1);
                if(nextIndex == -1)
                    return true;
                else
                    if(solver(board, nextIndex))
                        return true;
            }
        }
        board[row][col] = '.';
        return false;
    }

    /**
     * 寻找下一个为空的位置
     * @param board
     * @param index
     * @return
     */
    private int findTheNextEmptyIndex(char[][] board, int index){
        while(index<board.length*board.length){
            if(board[index/board.length][index%board.length] == '.')
                return index;
            else index++;
        }
        return -1;
    }

    //判断当前数字是否符合条件
    private boolean validSudoku(char[][] board, int row, int col) {
        char c = board[row][col];
        if (c == '.')
            return true;
        for (int i = 0; i < 9; i++) {
            char rowChar = board[row][i];
            //行符合
            if (i != col && rowChar != '.' && rowChar == c)
                return false;
            char colChar = board[i][col];
            //列符合
            if (i != row && colChar != '.' && colChar == c)
                return false;
        }
        //判断小框是否符合
        int baseRow = row / 3, baseCol = col / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int tempRow = baseRow*3+i, tempCol = baseCol*3+j;
                char boxChar = board[tempRow][tempCol];
                if (tempRow != row && tempCol != col && boxChar != '.' && boxChar == c)
                    return false;
            }
        }
        return true;
    }
}
