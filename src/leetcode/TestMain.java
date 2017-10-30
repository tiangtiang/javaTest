package leetcode;

import sun.security.util.Resources_sv;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tiang on 2017/10/30.
 */
public class TestMain {
    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        List<String> result = validParentheses.generateParenthesis(5);
        System.out.println(result.size());
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
        result = result.stream().distinct().collect(Collectors.toList());
        System.out.println(result.size());
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }
}
