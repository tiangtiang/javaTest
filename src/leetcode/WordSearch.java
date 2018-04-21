package leetcode;

/**
 * Created by lianglab on 2018/4/1.
 */
public class WordSearch {
    /**
     *  Given a 2D board and a word, find if the word exists in the grid.
     The word can be constructed from letters of sequentially adjacent cell,
     where "adjacent" cells are those horizontally or vertically neighboring.
     The same letter cell may not be used more than once.
     For example,
     Given board =
     [
     ['A','B','C','E'],
     ['S','F','C','S'],
     ['A','D','E','E']
     ]
     word = "ABCCED", -> returns true,
     word = "SEE", -> returns true,
     word = "ABCB", -> returns false.

     思路就是回溯
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j] == word.charAt(0)){
                    boolean used[][] = new boolean[board.length][board[0].length];
                    used[i][j] = true;
                    if(exist(board, i, j, word, 1, used))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int row, int col, String word, int index, boolean[][] used){
        if(index == word.length())
            return true;
        boolean result = false;
        //向右
        if(col<board[row].length-1 && !used[row][col+1] && board[row][col+1] == word.charAt(index)) {
            used[row][col+1] = true;
            result = exist(board, row, col + 1, word, index + 1, used);
            if(!result)
                used[row][col+1] = false;
        }
        //向下
        if(!result && row<board.length-1 && !used[row+1][col] && board[row+1][col] == word.charAt(index)) {
            used[row+1][col] = true;
            result = exist(board, row + 1, col, word, index + 1, used);
            if(!result)
                used[row+1][col] = false;
        }
        //向左
        if(!result && col>0 && !used[row][col-1] && board[row][col-1] == word.charAt(index)) {
            used[row][col-1] = true;
            result = exist(board, row, col - 1, word, index + 1, used);
            if(!result)
                used[row][col-1] = false;
        }
        //向上
        if(!result && row>0 && !used[row-1][col] && board[row-1][col] == word.charAt(index)) {
            used[row-1][col] = true;
            result = exist(board, row - 1, col, word, index + 1, used);
            if(!result)
                used[row-1][col] = false;
        }
        return result;
    }
}
