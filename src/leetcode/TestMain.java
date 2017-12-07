package leetcode;

/**
 * Created by tiang on 2017/10/30.
 */
public class TestMain {
    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        int[] nums = new int[]{
                1, 5, 1
        };
        permutation.nextPermutation(nums);
        for(int i : nums){
            System.out.println(i);
        }
    }
}
