package leetcode;

/**
 * Created by lianglab on 2018/4/10.
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given an encoded message containing digits, determine the total number of ways to decode it.
 For example,
 Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 The number of ways decoding "12" is 2.

 */
public class DecodeWays {
    /**
     * 动态规划
     * f(i) = f(i-1)+f(i-2)
     * f(1) = s>0 && s<10 ? 1: 0
     * 注意判断是否是有解路径
     * @param s
     * @return
     */
    public int numDecodings(String s){
        Integer[] dp = new Integer[s.length()+1];
        return numDecodings(s, dp);
    }
    private int numDecodings(String s, Integer[] dp) {
        if(dp[s.length()]!=null)
            return dp[s.length()];
        if (s.length() == 0) return 0;
        if (s.length() == 1) {
            int t = s.charAt(0) - '0';
            if (t > 0 && t < 10)
                return 1;
            else
                return 0;
        }
        int result = 0;
        int one = s.charAt(0) - '0';
        if (one > 0 && one < 10)
            result += numDecodings(s.substring(1), dp);
        int two = (s.charAt(0) - '0') * 10 + (s.charAt(1) - '0');
        if (two >= 10 && two <= 26)
            result += s.length() == 2 ? 1 : numDecodings(s.substring(2), dp);
        dp[s.length()] = result;
        return result;
    }
}
