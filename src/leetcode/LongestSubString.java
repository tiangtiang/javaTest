package leetcode;

/**
 * Created by tiang on 2017/10/19.
 * 最长的非重复字符串
 * 回溯
 *
 */
public class LongestSubString {
    public static int lengthOfLongesSubString(String s){
        int length = 0;
        int pre=0,next=1;
        while(next<s.length()){
            for(int i=pre;i<next;i++){
                if(s.charAt(i) == s.charAt(next)){
                    int tempLength = next-pre;
                    if(tempLength>length){
                        length = tempLength;
                    }
                    pre = i+1;
                    break;
                }
            }
            next++;
        }
        int tempLenght = next-pre;
        if(tempLenght>length)
            length = tempLenght;
        return length;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongesSubString("bbbb"));
    }
}
