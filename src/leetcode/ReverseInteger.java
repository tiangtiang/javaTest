package leetcode;

/**
 * Created by tiang on 2017/10/21.
 * 数字游戏
 */
public class ReverseInteger {
    /**
     * 将数字翻转，如果越界返回0
     * @param x
     * @return
     */
    static int reverse(int x) {
        String str = x>=0?String.valueOf(x):String.valueOf(-x);
        char[] chars = str.toCharArray();
        for(int i=0;i<chars.length/2;i++){
            char temp = chars[i];
            chars[i] = chars[chars.length-i-1];
            chars[chars.length-i-1] = temp;
        }
        try{
            int value = Integer.parseInt(String.valueOf(chars));
            value = x>=0?value:-value;
            return value;
        }catch (Exception ex){
            return 0;
        }
    }

    /**
     * 将字符串转成数字，如果越界返回边界值
     * @param str
     * @return
     */
    static int myAtoi(String str) {
        str = str.trim();
        char[] chars = str.toCharArray();
        if(chars.length == 0)
            return 0;
        int i = 0;
        if(chars[0] == '+'||chars[0] == '-')
            i = 1;
        long result = 0;
        for(;i<chars.length;i++){
            if(!Character.isDigit(chars[i]))
                break;
            else{
                result = result*10+(chars[i]-48);
                if(result>Integer.MAX_VALUE)
                    break;
            }
        }
        if(chars[0] == '-')
            result = -result;
        if(result>Integer.MAX_VALUE )
            return Integer.MAX_VALUE;
        else if(result<Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        else
            return (int)result;
    }

    /**
     * 判断数字是否是回文数字
     * @param x
     * @return
     */
    static boolean isPalindrome(int x) {
        if(x<0)
            return false;
        int res = x, count = 0;
        while(res>0){
            count++;
            res /=10;
        }
        boolean oddFlag = count%2 == 0?false:true;
        int revLow = 0;
        for(int i=0;i<count/2;i++){
            revLow = revLow*10+x%10;
            x /=10;
        }
        int high = oddFlag?x/10:x;

        return high == revLow;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(8));
    }
}
