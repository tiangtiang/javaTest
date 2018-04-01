package leetcode;

/**
 * Created by lianglab on 2018/3/23.
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            dp[i][0] = i;
        }
        for(int j=1;j<=n;j++){
            dp[0][j] = j;
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                //add
                int add = dp[i][j-1]+1;
                //del
                int del = dp[i-1][j]+1;
                //replace
                int rep = dp[i-1][j-1]+(word1.charAt(i-1) == word2.charAt(j-1)?0:1);
                dp[i][j] = Integer.min(add, Integer.min(del, rep));
            }
        }
        return dp[m][n];
    }
}
