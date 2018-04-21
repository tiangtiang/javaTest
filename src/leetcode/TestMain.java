package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tiang on 2017/10/30.
 * 主函数
 */
public class TestMain {

    public static void main(String[] args) {
//        PackProblem pack = new PackProblem();
//        int result =pack.completePack(new int[]{
//                2,3,4,7
//        }, new int[]{
//                1,3,5,9
//        }, 10);
        Coin c = new Coin();
        int result = c.getCount(new int[]{
                1, 2, 3
        }, new int[]{1}, 5);
        System.out.println(result);
    }

    public static int getMaxWeight(int input){
        return input/2+1;
    }

    public static String sortStringByNum(String input){
        if(input == null || input.equals(""))
            return "";
        String[] strs = input.split(" ");
        class Temp{
            String value;
            int num;
            public Temp(String value, int num){
                this.value = value;
                this.num = num;
            }
        }
        List<Temp> sorted = new ArrayList<>();
        for(String s: strs){
            //获取字符串中的数字
            int num = getIntFromString(s);
            Temp t = new Temp(s, num);
            if(sorted.size() == 0)
                sorted.add(t);
            else {
                boolean insertFlag = false;
                for (int i = 0; i < sorted.size(); i++) {
                    if(sorted.get(i).num>num){
                        sorted.add(i, t);
                        insertFlag = true;
                        break;
                    }
                }
                if(!insertFlag)
                    sorted.add(t);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(Temp t: sorted){
            sb.append(t.value).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    private static int getIntFromString(String input){
        int result = 0;
        boolean flag = false;
        boolean eightOrTen = false;
        for(int i=0;i<input.length();i++){
            int num = input.charAt(i) - '0';
            if(!flag) {
                if(num>=0 && num <=9)
                    flag = true;
                if(num == 0)
                    //八进制
                    eightOrTen = true;
            }
            if(flag){
                if(num<0 || num>9)
                    break;
                if(eightOrTen)
                    result = result*8+num;
                else
                    result = result*10+num;
            }
        }
        return result;
    }

    public static String[] shrinkWord(String[] words){
        for(int i=0;i<words.length;i++){
            words[i] = words[i].length()<=10?words[i]:
                    words[i].charAt(0)+""+(words[i].length()-2)+words[i].charAt(words[i].length()-1);
        }
        return words;
    }

    public static int findThree(int left, int right) {
        //记录数量
        int count = 0;
        //记录最左边的数字是否可以被3整除
        int sum = 0;
        for (int i = 1; i <= left; i++) {
            sum += i;
        }
        //记录上一个数除3之后的余数
        int last = sum % 3;
        if (last == 0)
            count++;
        int i = left + 1;
        while (i <= right) {
            //判断能否被3整除
            last = (last + i) % 3;
            if (last == 0)
                count++;
            i++;
        }
        return count;
    }

    static int getCount(int n, int k) {
        if(n<=k)
            return 0;
        int count = 0;
        for (int i = k + 1; i <= n; i++) {
            int tempCount = (n / i) * (i - k);
            tempCount += n % i >= k ? n % i - k + 1 : 0;
            count += tempCount;
        }
        return count;
    }

    static List<Integer> getList(HashMap<Integer, Integer> input, List<Integer> list){
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            int max = 0;
            for(int key : input.keySet()){
                if(key>max && input.get(key)<=list.get(i))
                    max = key;
            }
            result.add(max);
        }
        return result;
    }
}