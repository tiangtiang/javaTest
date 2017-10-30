package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tiang on 2017/10/25.
 */
public class Roman {
    private Map<Character, Integer> romTOInt;
    public Roman(){
        romTOInt = new HashMap<>();
        romTOInt.put('I', 1);
        romTOInt.put('V', 5);
        romTOInt.put('X', 10);
        romTOInt.put('L', 50);
        romTOInt.put('C', 100);
        romTOInt.put('D', 500);
        romTOInt.put('M', 1000);
    }
    public int fromRoman(String roman){
        int sum = 0;
        int i=0;
        while(i<roman.length()){
            int curr = romTOInt.get(roman.charAt(i));
            if(i+1<roman.length()){
                int next = romTOInt.get(roman.charAt(i+1));
                if(curr<next){
                    sum += next-curr;
                    i+=2;
                    continue;
                }else if(curr == next){
                    sum += next+curr;
                    i+=2;
                    continue;
                }
            }
            sum+=curr;
            i++;
        }
        return sum;
    }

    public String toRoman(int num){
        String str = "";
        String symbol[]={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int value[]=    {1000,900,500,400, 100, 90,  50, 40,  10, 9,   5,  4,   1};
        for(int i=0;num!=0;++i)
        {
            while(num>=value[i])
            {
                num-=value[i];
                str+=symbol[i];
            }
        }
        return str;
    }

    public static void main(String[] args) {
        Roman roman = new Roman();
        String result = roman.toRoman(956);
        int intResult = roman.fromRoman(result);
        System.out.println(intResult);
    }
}
