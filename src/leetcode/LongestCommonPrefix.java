package leetcode;

/**
 * Created by tiang on 2017/10/27.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length<=0)
            return "";
        int curr = 0;
        StringBuilder sb = new StringBuilder();
        while(true){
            char aim = 's';
            if(strs[0].length()>curr){
                aim = strs[0].charAt(curr);
            }else{
                break;
            }
            for(int i=1;i<strs.length;i++){
                if(strs[i].length()<=curr || strs[i].charAt(curr) != aim){
                    return sb.toString();
                }
            }
            sb.append(aim);
            curr++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{""}));
    }
}
