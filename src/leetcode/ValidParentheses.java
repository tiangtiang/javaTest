package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tiang on 2017/10/30.
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> characterStack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == '('||c=='['||c=='{'){
                characterStack.push(c);
            }else{
                if(characterStack.isEmpty())
                    return false;
                char top = characterStack.pop();
                if((c == ')' && top == '(') || (c == ']' && top == '[')
                        ||(c == '}' && top == '{')) continue;
                else
                    return false;
            }
        }
        return characterStack.isEmpty();
    }

    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     For example, given n = 3, a solution set is:
     [
     "((()))",
     "(()())",
     "(())()",
     "()(())",
     "()()()"
     ]
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if(n < 1)
            return list;
        generateParenthesis(n, 1, "", 0);
        return list;
    }
    private List<String> list = new ArrayList<>();

    private void generateParenthesis(int n, int index, String str, int left){
        str+= '(';
        left+=1;
        if(index == n){
            StringBuilder strBuilder = new StringBuilder(str);
            for(int i = 0; i<left; i++) strBuilder.append(')');
            str = strBuilder.toString();
            list.add(str);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<=left;i++){
            generateParenthesis(n, index+1, str+sb.toString(), left-i);
            sb.append(')');
        }
    }
}
