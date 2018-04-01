package leetcode;

import java.util.HashSet;

/**
 * Created by lianglab on 2018/3/23.
 */
public class KMP {
    public static int getIndex(String source, String pattern) {
        int[] next = new int[pattern.length()];
        for (int i = 1; i < pattern.length(); i++) {
            if (pattern.charAt(i) == pattern.charAt(next[i - 1]))
                next[i] = next[i - 1] + 1;
            else next[i] = 0;
        }
        int si=0, pi=0;
        while(si <source.length() && pi<pattern.length()){
            if(pattern.charAt(pi) != source.charAt(si)){
                if(pi > 0){
                    pi = next[pi-1];
                    continue;
                }
            }
            si++;
            pi++;
        }
        return pi<pattern.length()?-1:si-pi;
    }

    public static int coins(int num) {
        int stop = num / 2;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= stop; i++) {
            set.add(i ^ (num - i));
        }
        return set.size();
    }

    public static void main(String[] args) {
        int result = getIndex("abcabeabcabd", "abcabd");
        System.out.println(result);
    }
}
