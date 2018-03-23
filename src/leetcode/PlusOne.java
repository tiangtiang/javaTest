package leetcode;

/**
 * Created by lianglab on 2018/3/15.
 */
public class PlusOne {
    /**
     * 数字+1
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int jin = 1;
        for(int i=digits.length-1;i>=0;i--){
            int sum = digits[i]+jin;
            if(sum == 10) {
                jin = 1;
                digits[i] = 0;
            }
            else{
                jin = 0;
                digits[i] = sum;
            }
        }
        if(jin == 0)
            return digits;
        else{
            int[] res = new int[digits.length+1];
            res[0] = 1;
            for(int i=1;i<res.length;i++){
                res[i] = digits[i-1];
            }
            return res;
        }
    }

    /**
     * a+b 二进制加法，跟十进制加法很像，记得进位就可以了
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        if(a.length()>b.length()){
            String temp = a;
            a = b;
            b = temp;
        }
        StringBuilder result = new StringBuilder();
        int jin = 0;
        for(int i=1;i<=b.length();i++){
            int ai = 0, bi = b.charAt(b.length()-i)-'0';
            if(i<=a.length())
                ai = a.charAt(a.length()-i)-'0';
            int sum = ai+bi+jin;
            jin = sum/2;
            result.insert(0, sum%2);
        }
        if(jin == 1)
            result.insert(0, 1);
        return result.toString();
    }
}
