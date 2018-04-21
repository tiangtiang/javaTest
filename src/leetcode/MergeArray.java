package leetcode;

/**
 * Created by lianglab on 2018/4/10.
 */
public class MergeArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=0, j= 0;
        while(j<n){
            if(nums2[j]<nums1[i] || i>=m+j){
                for(int t = m+j-1;t>=i && t>=0;t--){
                    nums1[t+1] = nums1[t];
                }
                nums1[i] = nums2[j];
                j++;
            }
            i++;
        }
    }
}
