package leetcode;

/**
 * Created by tiang on 2017/10/23.
 */
public class RegularMatchingDP {
    Boolean[][] result;
    public boolean isMatch(String s, String p){
        result = new Boolean[s.length()+1][p.length()+1];
        return dp(0, 0, s, p);
    }
    public boolean dp(int x, int y, String s, String p){
        if(result[x][y] != null){
            return result[x][y];
        }
        if(y == p.length())
            return x == s.length();
        boolean firstMatch = x<s.length() &&
                (s.charAt(x) == p.charAt(y) || p.charAt(y) == '.');
        boolean ans;
        if(y+1<p.length() && p.charAt(y+1) == '*'){
            ans =  (firstMatch && dp(x+1, y, s, p))||dp(x, y+2, s, p);
        }else{
            ans = firstMatch && dp(x+1, y+1, s, p);
        }
        result[x][y] = ans;
        return ans;
    }

    public boolean isMatchOtherWay(String text, String pattern){
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        RegularMatchingDP rm = new RegularMatchingDP();
//        System.out.println(rm.isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
//        System.out.println(rm.getMaxSum(new int[] {-9, -2, -3, -5, -6}));
        System.out.println(rm.getMaxContainer(new int[]{10, 1, 2, 3, 4, 5, 6, 7, 11}));
    }

    public int getMaxSum(int[] nums){
        int max = Integer.MIN_VALUE;
        int last = nums[0];
        for(int i=1;i<nums.length;i++){
            int temp = last+nums[i];
            last = temp>nums[i] ? temp:nums[i];
            max = Math.max(last, max);
        }
        return max;
    }

    public int getMaxContainer(int[] nums){
        int max = 0;
        if(nums.length<=2)
            return max;
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i-1;j++){
                int container = nums[i]>nums[j]?nums[j]*(i-j):nums[i]*(i-j);
                max = Math.max(max, container);
            }
        }
        return  max;
    }
}
