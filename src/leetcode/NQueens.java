package leetcode;

/**
 * Created by lianglab on 2018/1/6.
 */
public class NQueens {
    public int queens(int n) {
        return queens(new int[n], 0);
    }

    private int queens(int[] cols, int rowNum) {
        if (rowNum == cols.length) {
            //成功
            printQueen(cols);
            return 1;
        }
        int count = 0;
        next:
        for (int i = 0; i < cols.length; i++) {
            if (rowNum != 0)
                //判断列是否冲突
                for (int t = 0; t < rowNum; t++)
                    //是否处于同一列，是否处于左对角线，是否处于右对角线
                    if (cols[t] == i || cols[t] + t == i + rowNum || cols[t] - t == i - rowNum)
                        continue next;

            cols[rowNum] = i;
            count += queens(cols, rowNum + 1);
        }
        return count;
    }

    private void printQueen(int[] cols) {
        for (int i = 0; i < cols.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                if (j == cols[i])
                    System.out.print("x ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
