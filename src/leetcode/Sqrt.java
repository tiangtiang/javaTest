package leetcode;

/**
 * Created by lianglab on 2018/3/23.
 */
public class Sqrt {
    /**
     * 计算平方根，二分查找，1是特殊情况
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if(x == 1)
            return 1;
        int left = 0, right = x;
        while(right-left>1) {
            int mid = (left + right) / 2;
            if (mid > x / mid)
                right = mid;
            else
                left = mid;
        }
        return left;
    }
}
