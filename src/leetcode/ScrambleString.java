package leetcode;

/**
 * Created by lianglab on 2018/4/10.
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * Below is one possible representation of s1 = "great":
 * great
 * /    \
 * gr    eat
 * / \    /  \
 * g   r  e   at
 * / \
 * a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 * rgeat
 * /    \
 * rg    eat
 * / \    /  \
 * r   g  e   at
 * / \
 * a   t
 * We say that "rgeat" is a scrambled string of "great".
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 * rgtae
 * /    \
 * rg    tae
 * / \    /  \
 * r   g  ta  e
 * / \
 * t   a
 * We say that "rgtae" is a scrambled string of "great".
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */
public class ScrambleString {
    /**
     * 通过递归，分支限界，通过判断两个子串是否含有相同的元素进行剪枝
     * s1有length-1种分割方式，每一种分割方式对应的s2都有两种分割方式，例如i= 2的时候
     * s1分为 s1.sub(0, 2) & s1.sub(2)
     * s2则分为s2.sub(0, 2) & s2.sub(2)  || s2.sub(s2.len-2) & sub(0, s2.len-2);
     * 通过递归判断两者是否有符合的
     * 但是如果不进行剪枝操作的话，递归的层次会很深。
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2))
            return true;
        if (!hasSameChar(s1, s2))
            return false;
        if (s1.length() == 1) {
            return s1.charAt(0) == s2.charAt(0);
        }
        for (int i = 1; i < s1.length(); i++) {
            String s1pre = s1.substring(0, i);
            String s1last = s1.substring(i);
            if ((isScramble(s1pre, s2.substring(0, i)) && isScramble(s1last, s2.substring(i)))
                    || (isScramble(s1pre, s2.substring(s2.length() - i)) && isScramble(s1last, s2.substring(0, s2.length() - i)))) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSameChar(String s1, String s2) {
        /*HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0;i<s1.length();i++){
            char c = s1.charAt(i);
            if(map.containsKey(c))
                map.put(c, map.get(c)+1);
            else
                map.put(c, 1);
        }
        for(int i=0;i<s2.length();i++){
            char c = s2.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0)
                    map.remove(c);
            }else
                return false;
        }
        return map.isEmpty();*/
        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            //一个++，一个--，如果两个字符相同的话，最后所有结果都为0
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) if (letters[i] != 0) return false;
        return true;
    }
}
