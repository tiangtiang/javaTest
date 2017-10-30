package leetcode;

import java.util.*;

/**
 * Created by tiang on 2017/10/30.
 */
public class PhoneNumber {
    private List<String> resultList = new ArrayList<>();
    private Map<Character, List<Character>> digChar;
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0)
            return resultList;
        digChar = new HashMap<>();
        digChar.put('2', Arrays.asList('a', 'b', 'c'));
        digChar.put('3', Arrays.asList('d', 'e', 'f'));
        digChar.put('4', Arrays.asList('g', 'h', 'i'));
        digChar.put('5', Arrays.asList('j', 'k', 'l'));
        digChar.put('6', Arrays.asList('m', 'n', 'o'));
        digChar.put('7', Arrays.asList('p', 'q', 'r', 's'));
        digChar.put('8', Arrays.asList('t', 'u', 'v'));
        digChar.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        letter(digits, 0, "");
        return resultList;
    }
    private void letter(String digits, int index, String sb){
        if(index == digits.length()){
            resultList.add(sb);
            return;
        }
        List<Character> list = digChar.get(digits.charAt(index));
        if(list == null){
            return;
        }
        for(Character character : list){
            letter(digits, index+1, sb+character);
        }
    }
}
