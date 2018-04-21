package leetcode;

/**
 * Created by lianglab on 2018/4/10.
 *  A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
 Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
 Also, since the answer may be very large, you should return the output mod 109 + 7.
 Example 1:
 Input: "*"
 Output: 9
 Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 Example 2:
 Input: "1*"
 Output: 9 + 9 = 18
 Note:
 The length of the input string will fit in range [1, 105].
 The input string will only contain the character '*' and digits '0' - '9'.
 */
public class DecodeWaysII {

    private final int M = 1000000007;

    /**
     * 使用递归的动态规划，但是会栈溢出
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        long result = numDecodings(s, new Long[s.length() + 1]);
        return (int) result % M;
    }

    private long numDecodings(String s, Long[] dp) {
        if (dp[s.length()] != null)
            return dp[s.length()];
        if (s.length() == 0) return 0;
        if (s.length() == 1) {
            if (s.charAt(0) == '*')
                return 9;
            else {
                int value = s.charAt(0) - '0';
                if (value > 0 && value < 10)
                    return 1;
                else
                    return 0;
            }
        }
        long result = 0;
        int first = 0, second = 0;
        if (s.charAt(0) == '*') {
            //单独星号分割
            first = 9;
            //第二个仍是星号
            if (s.charAt(1) == '*')
                second = 15;
            else {
                int value = s.charAt(1) - '0';
                //0,1,2,3,4，5，6
                if (value >= 0 && value < 7) {
                    second = 2;
                    //7,8,9
                } else if (value > 7 && value < 9) {
                    second = 1;
                }
            }
        } else {
            int one = s.charAt(0) - '0';
            if (one > 0 && one < 10)
                first = 1;
            if (s.charAt(1) == '*') {
                if (one == 1)
                    second = 9;
                else if (one == 2)
                    second = 6;
            } else {
                int value = one * 10 + (s.charAt(1) - '0');
                if (value >= 10 && value <= 26)
                    second = 1;
            }
        }
        if (first > 0)
            result += first * numDecodings(s.substring(1), dp);
        if (second > 0)
            result += second * (s.length() == 2 ? 1 : numDecodings(s.substring(2), dp));
        dp[s.length()] = result;
        return result;
    }

    /**
     * 使用循环的动态规划
     * @param s
     * @return
     */
    public int numDecodingsDPII(String s) {
        long[] dp = new long[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
        for(int i=1;i<s.length();i++){
            int first = 0,
                    second = 0;
            if(s.charAt(i) == '*'){
                first = 9;
                if(s.charAt(i-1) == '1'){
                    second = 9;
                }else if(s.charAt(i-1) == '2')
                    second = 6;
                else if(s.charAt(i-1) == '*')
                    second = 15;
            }else{
                int value = s.charAt(i)-'0';
                if(value>0)
                    first = 1;
                if(s.charAt(i-1) == '*') {
                    if (value >= 0 && value < 7)
                        second = 2;
                    else if(value >=7 && value<=9)
                        second = 1;
                }else {
                    int fv = s.charAt(i-1)-'0';
                    value = fv*10+value;
                    if(value>=10 && value<=26)
                        second = 1;
                }
            }
            if(first > 0)
                dp[i+1] = (dp[i+1] + first*dp[i])%M;
            if(second>0)
                dp[i+1] = (dp[i+1] + second*dp[i-1])%M;
        }
        return (int)dp[s.length()];
    }
}
