package leetcode;

/**
 * Created by lianglab on 2018/1/9.
 */
public class CountSay {
    public String countAndSay(int n) {
        String str = "1";
        int t = 1;
        while(t<n){
            int index = 1, count = 1;
            char lastChar = str.charAt(0);
            StringBuilder sb = new StringBuilder();
            for(;index<str.length();index++){
                char c = str.charAt(index);
                if(c == lastChar){
                    count++;
                }else{
                    sb.append(count).append(lastChar);
                    lastChar = c;
                    count = 1;
                }
            }
            sb.append(count).append(lastChar);
            str = sb.toString();
            t++;
        }
        return str;
    }
}
