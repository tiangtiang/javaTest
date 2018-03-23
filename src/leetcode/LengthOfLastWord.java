package leetcode;

/**
 * Created by lianglab on 2018/3/1.
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        String[] strs = s.split("\\s");
        if(strs.length == 0)
            return 0;
        return strs[strs.length-1].length();
    }
}
