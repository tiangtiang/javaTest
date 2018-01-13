package leetcode;

/**
 * Created by lianglab on 2018/1/12.
 */
public class BigNum {
    /**
     * 模拟两个数字做乘法的过程
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if(num1.length()>num2.length()){
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        if(num1.equals("0")) return "0";
        String result = "0";
        int zeroCount = 0;
        //从低到高挨个乘，乘积相加
        for(int i=num1.length()-1;i>=0;i--){
            StringBuilder sb = new StringBuilder();
            int baseNum = num1.charAt(i)-48,
                    forwardNum = 0;
            for(int j=num2.length()-1;j>=0;j--){
                int plusNum = num2.charAt(j)-48;
                int product = baseNum*plusNum+forwardNum;
                sb.insert(0, product%10);
                forwardNum = product/10;
            }
            if(forwardNum!=0)sb.insert(0, forwardNum);
            for(int j=0;j<zeroCount;j++) sb.append("0");
            result = addTwoString(result, sb.toString());
            zeroCount++;
        }
        return result;
    }

    /**
     * 模拟两个数字相加的过程
     * @param num1
     * @param num2
     * @return
     */
    private String addTwoString(String num1, String num2){
        if(num1.length()<num2.length()){
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        StringBuilder result = new StringBuilder();
        int forward = 0;
        int i=1;
        for(;i<=num2.length();i++){
            int a = num1.charAt(num1.length()-i)-48,
                    b = num2.charAt(num2.length()-i)-48;
            int sum = a+b+forward;
            result.insert(0, sum%10);
            forward = sum/10;
        }
        if(i<=num1.length()){
            for(;i<=num1.length();i++){
                int a = num1.charAt(num1.length()-i)-48;
                int sum = a+forward;
                result.insert(0, sum%10);
                forward = sum/10;
            }
        }
        if(forward!=0) result.insert(0, forward);
        return result.toString();
    }
}
