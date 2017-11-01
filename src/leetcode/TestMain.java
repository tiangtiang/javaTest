package leetcode;

import sun.security.util.Resources_sv;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tiang on 2017/10/30.
 */
public class TestMain {
    public static void main(String[] args) {
        DuplicatesSortedArray duplicatesSortedArray = new DuplicatesSortedArray();
        int result = duplicatesSortedArray.removeDuplicates(new int[]{
                1, 1, 2
        });
        System.out.println(result);
    }
}
