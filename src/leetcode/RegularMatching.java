package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiang on 2017/10/23.
 */
public class RegularMatching {
    static boolean isMatchOld(String s, String p) {
        //分解模式
        List<Character> pCharList = new ArrayList<>();
        List<Boolean> pStarList = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            char pChar = p.charAt(i);
            if (i + 1 < p.length()) {
                char pNext = p.charAt(i + 1);
                if (pNext == '*') {
                    pCharList.add(pChar);
                    pStarList.add(true);
                    i++;
                    continue;
                }
            }
            pCharList.add(pChar);
            pStarList.add(false);
        }
        //匹配
        int sIndex = 0, pIndex = 0;
        char[] sChars = new char[s.length()];
        boolean[] sMulti = new boolean[s.length()];
        while (sIndex < s.length()) {
            char schar = s.charAt(sIndex);
            if (pIndex >= pCharList.size())
                return false;
            char pchar = pCharList.get(pIndex);
            if (schar == pchar || pchar == '.') {
                sChars[sIndex] = schar;
                if (pStarList.get(pIndex) == true) {
                    if (pchar != '.' || (pchar == '.' && pIndex < pCharList.size())) {
                        if (pchar == '.') {
                            pchar = pCharList.get(pIndex + 1);
                        }
                        int pSameCount = 0, pTempIndex = pIndex + 1;
                        while (pTempIndex < pCharList.size() && pCharList.get(pTempIndex) == pchar) {
                            pTempIndex++;
                            pSameCount++;
                        }
                        int sSameCount = 0, sTempIndex = sIndex;
                        while (sTempIndex < s.length() && s.charAt(sTempIndex) == pchar) {
                            sSameCount++;
                            sTempIndex++;
                        }
                        if (sSameCount < pSameCount)
                            return false;
                        else {
                            for (int i = 0; i < sSameCount - pSameCount; i++) {
                                sMulti[sIndex] = true;
                                sChars[sIndex] = s.charAt(sIndex);
                                sIndex++;
                            }
                            pIndex++;
                        }
                        continue;
                    } else {
                        if (pStarList.get(pIndex) == true) {
                            sMulti[sIndex] = true;
                            sIndex++;
                            continue;
                        } else {
                            sMulti[sIndex] = false;
                            sIndex++;
                            pIndex++;
                            continue;
                        }
                    }
                } else {
                    sMulti[sIndex] = false;
                    sIndex++;
                    pIndex++;
                    continue;
                }
            } else {
                if (pStarList.get(pIndex) == true) {
                    pIndex++;
                    if (pIndex >= pCharList.size())
                        return false;
                    continue;
                } else {
                    return false;
                }
            }
        }
        int si = 0;
        if (pIndex < pCharList.size()) {
            while (pIndex < pCharList.size()) {
                if (pStarList.get(pIndex) == true) {
                    pIndex++;
                    continue;
                } else {
                    for (; si < s.length(); si++) {
                        if (sMulti[si] == true)
                            break;
                    }
                    if (si >= s.length())
                        return false;
                    else {
                        char sc = sChars[si];
                        if (sc == pCharList.get(pIndex) || pCharList.get(pIndex) == '.') {
                            pIndex++;
                            sMulti[si++] = false;
                        } else {
                            si++;
                            sMulti[si] = false;
                        }
                    }
                }
            }
        }
        return true;
    }

    static boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*')
            return (firstMatch && isMatch(s.substring(1), p)) || isMatch(s, p.substring(2));
        else
            return firstMatch && isMatch(s.substring(1), p.substring(1));
    }

    public static void main(String[] args) {
        System.out.println(
//                isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s")
//                isMatch("bbba", ".*b")
//                isMatch("aa", ".*")
                countLIS(new int[]{1, 7, 2, 8, 3, 4})
        );
    }

    static int countLIS(int[] nums){
        if (nums==null || nums.length==0) {
            return 0;
        }
        int max = 1;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j]<nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }
}
