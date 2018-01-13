package leetcode;

/**
 * Created by lianglab on 2018/1/13.
 */
public class WaildCardMatch {
    /**
     * 用单纯的递归方式来进行匹配
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchByRecursion(String s, String p){
        //先去掉p中重复的星号
        /**
         * 因为一个'*'匹配的内容和连续多个'*'匹配的内容是相同的
         */
        if(p.length()>1) {
            char lastChar = p.charAt(0);
            StringBuilder sb = new StringBuilder();
            sb.append(lastChar);
            for (int i = 1; i < p.length(); i++) {
                if (p.charAt(i) == lastChar && lastChar == '*')
                    continue;
                else {
                    lastChar = p.charAt(i);
                    sb.append(lastChar);
                }
            }
            p = sb.toString();
        }
        System.out.println(p);
        return isMatchInside(s, p);
    }

    /**
     * 运用递归来进行匹配
     * @param s
     * @param p
     * @return
     */
    private boolean isMatchInside(String s, String p) {
        //如果都str和pattern都为空的话，表示匹配成功
        if (s.length() == 0 && p.length() == 0)
            return true;
        //如果str不为空的时候pattern已经为空，那么一定会匹配失败
        else if(p.length()==0)
            return false;

        //此时pattern已经不是空了
        //如果pattern当前第一个字符是'*'
        if (p.charAt(0) == '*') {
            //如果此时str为空，那么就跳过该'*'
            if(s.length() == 0) return isMatchInside(s, p.substring(1));
            //否则，则有三种情况：1. 当前'*'无用，那么pattern跳过该'*'
            //2. 当前'*'有用，但是仅有一次用那么str和pattern都要跳过一个字符
            //3. 当前'*'有用，且不仅一次用，那么str跳过一个字符，pattern不需要
            else return isMatchInside(s, p.substring(1)) ||
                    isMatchInside(s.substring(1), p.substring(1)) ||
                    isMatchInside(s.substring(1), p);
        } else {
            //如果当前字符不是'*'
            //如果此时str为空，那么匹配失败
            if(s.length() == 0) return false;
            //如果str不为空，那么只有两种成功的情况
            //1. pattern当前字符为'?'
            //2. pattern当前字符与str当前字符相同
            if (p.charAt(0) == '?' || p.charAt(0) == s.charAt(0))
                return isMatchInside(s.substring(1), p.substring(1));
            else
                return false;
        }
    }

    /**
     * 用来记录匹配的中间结果
     * result[i][j]代表了s[i:]和p[j:]是否匹配
     */
    private Boolean[][] result;
    public boolean isMatchByDP(String s, String p) {
        /**
         * 预处理，因为如果str或者pattern的长度为0的话，是无法创建出数组的
         */
        /**
         * 后来在网上看了一个很巧的办法，创建数组的时候，创建的长度比原来多1
         * result = new Boolean[s.length()+1][p.length()+1]
         * 这样就不用担心越界和字符串长度为0 的特殊情况了
         */
        if (p.length() == 0 && s.length() == 0)
            return true;
        else if (p.length() == 0 && s.length() > 0)
            return false;
        else if (p.length() > 0 && s.length() == 0)
            if (p.charAt(0) == '*')
                return isMatchByDP(s, p.substring(1));
            else return false;
        //创建数组
        result = new Boolean[s.length()][p.length()];
        dpMatchInside(s, p, 0, 0);
        return result[0][0];
    }

    private boolean dpMatchInside(String s, String p, int i, int j){
        //如果此时数组没越界而且result[i][j]有值，那么直接返回
        if(i<s.length() && j<p.length() && result[i][j]!=null)
            return result[i][j];
        boolean dpRes = false;
        /**
         * 可以导致数组越界的就不写入数组，
         * 否则先将结果写入数组再返回结果
         */
        if(i == s.length() && j == p.length())
            return true;
        else if(p.length() == j)
            return false;
        else if(p.charAt(j) == '*'){
            if(s.length() == i)
                return dpMatchInside(s, p, i, j+1);
            else
                dpRes = dpMatchInside(s, p, i, j+1)||
                        dpMatchInside(s, p, i+1, j+1)||
                        dpMatchInside(s, p, i+1, j);
        }else{
            if(s.length() == i)
                return false;
            else if(p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))
                dpRes = dpMatchInside(s, p, i+1, j+1);
            else
                dpRes = false;
        }
        result[i][j] = dpRes;
        return dpRes;
    }
}
