package leetcode;

/**
 * Created by lianglab on 2018/3/13.
 */
public class UniquePaths {
    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     The robot can only move either down or right at any point in time. The robot is trying to reach the
     bottom-right corner of the grid (marked 'Finish' in the diagram below).
     递归方程 f(m,n)={m-1>0?f(m-1,n):0}+{n-1>0?f(m, n-1):0}
     f(1,1) = 1;
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        return uniquePaths(m,n, 1, 1);
    }

    private int uniquePaths(int m, int n, int x, int y){
        if(x==m && y == n)
            return 1;
        else{
            int right = 0, down = 0;
            if(x<m)
                right = uniquePaths(m, n, x+1, y);
            if(y<n)
                down = uniquePaths(m, n, x, y+1);
            return right+down;
        }
    }

    public int dp(int m, int n){
        int[][] arr = new int[m][n];
        return dp(m, n, arr, 0, 0);
    }
    private int dp(int m, int n, int[][] arr, int x, int y){
        if(arr[x][y]!=0)
            return arr[x][y];
        if(x==m-1&& y==n-1)
            arr[x][y] = 1;
        else{
            int right = 0, down = 0;
            if(x<m-1)
                right = dp(m, n, arr, x+1, y);
            if(y<n-1)
                down = dp(m, n, arr, x, y+1);
            arr[x][y] = right+down;
        }
        return arr[x][y];
    }

    /**
     * Follow up for "Unique Paths":
     Now consider if some obstacles are added to the grids. How many unique paths would there be?
     An obstacle and empty space is marked as 1 and 0 respectively in the grid.
     For example,
     There is one obstacle in the middle of a 3x3 grid as illustrated below.
     [
     [0,0,0],
     [0,1,0],
     [0,0,0]
     ]
     The total number of unique paths is 2.
     多了个障碍，只需在递归公式上添加条件判断当前位置是否可以行走就可以了
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return uniquePathsWithObstaclesDp(obstacleGrid, new Integer[obstacleGrid.length][obstacleGrid[0].length], 0, 0);
    }
    private int uniquePathsWithObstaclesDp(int[][] obs, Integer[][] result, int x, int y){
        if(result[x][y]!=null)
            return result[x][y];
        if(obs[x][y] != 0){
            result[x][y] = 0;
        }else if(x == obs.length-1 && y == obs[0].length-1 && obs[x][y] == 0)
            result[x][y] = 1;
        else{
            int right = 0, down = 0;
            if(x<obs.length-1 && obs[x+1][y] == 0)
                right = uniquePathsWithObstaclesDp(obs, result, x+1, y);
            if(y<obs[0].length-1 && obs[x][y+1] == 0)
                down = uniquePathsWithObstaclesDp(obs, result, x, y+1);
            result[x][y] = right+down;
        }
        return result[x][y];
    }

    /**
     * Given a m x n grid filled with non-negative numbers, find a path from top left to
     * bottom right which minimizes the sum of all numbers along its path.
     Note: You can only move either down or right at any point in time.
     Example 1:
     [[1,3,1],
     [1,5,1],
     [4,2,1]]
     Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
     递归公式f(m,n)=min(f(m-1,n)+g[m-1][n],f(m,n-1)+g[m][n-1])
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        return minPathSumDP(grid, new Integer[grid.length][grid[0].length], 0, 0);
    }
    private int minPathSumDP(int[][] grid, Integer[][] result, int x, int y){
        if(result[x][y]!=null)
            return result[x][y];
        if(x == grid.length-1 && y == grid[0].length-1)
            result[x][y] = grid[x][y];
        else{
            int right = Integer.MAX_VALUE, down = Integer.MAX_VALUE;
            if(x<grid.length-1)
                right = minPathSumDP(grid, result, x+1, y);
            if(y<grid[0].length-1)
                down = minPathSumDP(grid, result, x, y+1);
            result[x][y] = grid[x][y]+Integer.min(right, down);
        }
        return result[x][y];
    }
}
