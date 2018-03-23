package leetcode;

/**
 * Created by tiang on 2017/10/30.
 * 主函数
 */
public class TestMain {
    /**
     * 查找分组中的两个不相同的数，十分巧妙的一个做法
     * @param nums
     * @return
     */
    public static int[] temp(int[] nums){
        int[] result = new int[2];
        int temp = 0;
        for(int n:nums){
            temp ^= n;
        }
        int j = 0;
        while(((temp>>j)&1)==0)
            j++;
        int num1 =0, num2 = 0;
        for(int n:nums){
            if(((n>>j)&1) == 1)
                num1 ^= n;
            else num2 ^= n;
        }
        result[0] = num1;
        result[1] = num2;
        return result;
    }

    public static void main(String[] args) {
        long before = System.currentTimeMillis();
        String result = new SimplifyPath().simplifyPath("/../");
        long after = System.currentTimeMillis();
        System.out.println("taken time: "+(after - before));
        System.out.println(result);
    }
}