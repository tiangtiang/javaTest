package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianglab on 2018/4/11.
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 For example:
 Given "25525511135",

 return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

 */
public class RestoreIPAddresses {
    /*
    分割的思想
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        restoreIpAddresses(s, "", 0, result);
        return result;
    }

    /**
     * 递归
     * @param s
     * @param ip
     * @param index
     * @param list
     */
    private void restoreIpAddresses(String s, String ip, int index, List<String> list) {
        if (index == 4) {
            if (s.length() != 0)
                return;
            else
                list.add(ip);
        }
        //剪枝
        if(s.length()>(4-index)*3)return;
        for (int i = 1; i <= 3 && i<=s.length(); i++) {
            String str = s.substring(0, i);
            if(i>1){
                if(str.charAt(0) == '0')
                    continue;
            }
            int value = Integer.parseInt(str);
            if(value>255 || value<0)
                continue;
            restoreIpAddresses(s.substring(i), ip.equals("") ? s.substring(0, i) : ip + '.' + s.substring(0, i), index + 1, list);
        }
    }
}
