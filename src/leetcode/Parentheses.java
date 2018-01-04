package leetcode;

import java.util.Stack;

/**
 * Created by lianglab on 2017/12/7.
 */
public class Parentheses {
    /**
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     For "(()", the longest valid parentheses substring is "()", which has length = 2.
     Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int result = 0;
        Stack<Integer> temps = new Stack<>();
        int templong = 0;
        boolean flag = true;
        Stack<Character> stack = new Stack<>();
        int lastFlag = 0;
        for(char c : s.toCharArray()) {
            if (stack.empty() && c == ')') {
                flag = false;
                result = templong > result ? templong : result;
            } else if (c == '(') {
                stack.push(c);
            } else {
                if (!flag)
                    templong = 0;
                flag = true;
                templong += 2;
                stack.pop();
            }
        }
        return templong > result ? templong : result;
    }
    public int bruteForce(String str){
        int result = 0;
        for(int i=0;i<str.length();i++){
            for(int j=2;j<=str.length()-i;j+=2){
                String temp = str.substring(i, i+j);
                int res = getLong(temp);
                if(res>result)
                    result = res;
            }
        }
        return result;
    }
    private int getLong(String str){
        int count = 0;
        Stack<Character> stack = new Stack<>();
        for (char c :
                str.toCharArray()) {
            if(c == '(')
                stack.push(c);
            else if(c == ')' && stack.size()>0){
                stack.pop();
                count+=2;
            }else{
                return count;
            }
        }
        return count;
    }

    public int dpAlgorithm(String str){
        int maxLength = 0;
        int[] temp = new int[str.length()];
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(c == ')' && i>0){
                if(str.charAt(i-1) == '(') {
                    temp[i] = i-2>0 ? temp[i-2] +2:2;
                }else{
                    if((i- temp[i-1]-1)>=0 &&str.charAt(i- temp[i-1]-1) == '('){
                        temp[i] = (i- temp[i-1]-2)>=0 ? temp[i-1]+temp[i-temp[i-1]-2]+2:temp[i-1]+2;
                    }
                }
                if(temp[i]> maxLength)
                    maxLength = temp[i];
            }
        }
        return maxLength;
    }

    public int stackAlgorithm(String str){
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int tempmax = 0;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(c == '('){
                stack.push(i);
            }else if(c == ')'){
                stack.pop();
                if(stack.empty()){
                    stack.push(i);
                }else{
                    tempmax = i-stack.peek();
                    if(tempmax>maxLength)
                        maxLength = tempmax;
                }
//                if(stack.size()>0 && stack.size()>1){
//                    tempmax = i - stack.peek();
//                    if(tempmax>maxLength)
//                        maxLength = tempmax;
//                }else if(stack.peek() == -1){
//                    stack.push(i);
//                }
            }
        }
        return maxLength;
    }
}
