package leetcode;

import javax.naming.NamingException;

/**
 * Created by tiang on 2017/10/20.
 * 回文字符串
 * 由中心展开
 */
public class LongestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        if(s.length()==0)
            return "";
        String result = "";
        int maxL = 1, startIndex = 0;
        for(int i=1;i<s.length();i++){
            int pre = 0, tail = 0;
            if(i+1<s.length()){
                //i位置可作为中心
                if(s.charAt(i+1) == s.charAt(i-1)){
                    pre = i-1; tail = i+1;
                }
                if(pre!=tail){
                    while(pre>=0&& tail<s.length() && s.charAt(pre)==s.charAt(tail)){
                        pre--; tail++;
                    }
                    int tempL = tail-pre-1;
                    if(tempL>maxL){
                        maxL = tempL;
                        startIndex = pre+1;
                    }
                }
            }
            if(s.charAt(i) == s.charAt(i-1)){     //中心位于i与i+1之间
                pre = i-1;tail = i;
            }
            if(pre!=tail){
                while(pre>=0&& tail<s.length() && s.charAt(pre)==s.charAt(tail)){
                    pre--; tail++;
                }
                int tempL = tail-pre-1;
                if(tempL>maxL){
                    maxL = tempL;
                    startIndex = pre+1;
                }
            }
        }
        result = s.substring(startIndex, startIndex+maxL);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("ccc"));
    }
}
