package leetcode;

/**
 * Created by tiang on 2018/5/15.
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 Example 1:
 Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 Output: true

 Example 2:
 Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 Output: false

 */
public class InterleavingString {
    /**
     * 入口调用方法
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length() != s3.length())
            return false;
        Boolean[][] result = new Boolean[s1.length()+1][s2.length()+1];
        return isInterleave(s1, s2, s3, 0, 0, result);
    }

    /**
     * 递归的方式，用三个数字指针指向三个字符串的位置，判断每个s3的i位置的字符串是否由s1和s2的字符组成
     * @param s1
     * @param index1
     * @param s2
     * @param index2
     * @param s3
     * @param index3
     * @return
     */
    @Deprecated
    private boolean isInterleave(String s1, int index1, String s2, int index2, String s3, int index3){
        if(index3 == s3.length())
            return true;
        if(index1 < s1.length() && s1.charAt(index1) == s3.charAt(index3)){
            if(isInterleave(s1, index1+1, s2, index2, s3, index3+1))
                return true;
        }
        if(index2<s2.length() && s2.charAt(index2)== s3.charAt(index3)){
            if(isInterleave(s1, index1, s2, index2+1, s3, index3+1))
                return true;
        }
        return false;
    }

    /**
     * 记录中间结果的递归方法，采用result[i][j]判断s1的i位置以后和s2的j位置以后是否已经判断为失败了，此时就需要继续回溯
     * @param s1
     * @param s2
     * @param s3
     * @param index1
     * @param index2
     * @param result
     * @return
     */
    private boolean isInterleave(String s1, String s2, String s3, int index1, int index2, Boolean[][] result){
        if(index1+index2 == s3.length())
            return true;
        if(result[index1][index2]!=null)
            return result[index1][index2];
        if(index1<s1.length() && s1.charAt(index1) == s3.charAt(index1+index2)) {
            if (isInterleave(s1, s2, s3, index1 + 1, index2, result))
                return true;
        }
        if(index2<s2.length() && s2.charAt(index2) == s3.charAt(index1+index2))
            if(isInterleave(s1, s2, s3, index1, index2+1, result))
                return true;
        result[index1][index2] = false;
        return false;
    }
}
