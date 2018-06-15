package leetcode;

/**
 * Created by tiang on 2018/5/17.
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 A subsequence of a string is a new string which is formed from the original string by deleting some
 (can be none) of the characters without disturbing the relative positions of the remaining characters.
 (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 Example 1:

 Input: S = "rabbbit", T = "rabbit"
 Output: 3
 Explanation:

 As shown below, there are 3 ways you can generate "rabbit" from S.
 (The caret symbol ^ means the chosen letters)

 rabbbit
 ^^^^ ^^
 rabbbit
 ^^ ^^^^
 rabbbit
 ^^^ ^^^

 Example 2:

 Input: S = "babgbag", T = "bag"
 Output: 5
 Explanation:

 As shown below, there are 5 ways you can generate "bag" from S.
 (The caret symbol ^ means the chosen letters)

 babgbag
 ^^ ^
 babgbag
 ^^    ^
 babgbag
 ^    ^^
 babgbag
 ^  ^^
 babgbag
 ^^^

 */
public class DistinctSubsequences {
    /**
     * 挨个匹配s和t的位置的字符，如果相同，要么匹配，要么不匹配，如果不相同，就不匹配
     * 当s匹配完t还没有匹配完就返回0
     * 当t匹配完s还没匹配完就返回1
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        Integer[][] result = new Integer[s.length()+1][t.length()+1];
        return numDistinct(s, 0, t, 0, result);
    }

    /**
     * 用二维数组来存储中间过程
     * @param s
     * @param index1
     * @param t
     * @param index2
     * @param list
     * @return
     */
    private int numDistinct(String s, int index1, String t, int index2, Integer[][] list){
        if(index2 == t.length())
            return index1 <= s.length() ? 1:0;
        if(index1 >= s.length())
            return 0;
        if(list[index1][index2] != null)
            return list[index1][index2];
        int result = 0;
        if(s.charAt(index1) == t.charAt(index2)){
            result += numDistinct(s, index1+1, t, index2+1, list);
        }
        result += numDistinct(s, index1+1, t, index2, list);
        list[index1][index2] = result;
        return result;
    }
}
