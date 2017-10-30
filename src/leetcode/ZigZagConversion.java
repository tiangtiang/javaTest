package leetcode;

/**
 * Created by tiang on 2017/10/20.
 * z字型字符串
 */
public class ZigZagConversion {
    public static String convert(String s, int numRows) {
        if(numRows<=1)
            return s;
        StringBuilder[] sbs = new StringBuilder[numRows];
        for(int i=0;i<numRows;i++){
            sbs[i] = new StringBuilder();
        }
        int div = numRows-1;
        int i=0;
        while(i<s.length()){
            int res = i%div;
            if(res == 0){
                for(int j=0;j<numRows;j++){
                    if(i>=s.length())
                        break;
                    sbs[j].append(s.charAt(i++));
                }
            }else{
                sbs[(numRows-1-res)].append(s.charAt(i++));
            }
        }
        StringBuilder reslult = new StringBuilder();
        for(i=0;i<sbs.length;i++){
            reslult.append(sbs[i]);
        }
        return reslult.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("ABCDE", 4));
    }
}
