package leetcode.util;

/**
 * Created by tiang on 2018/5/26.
 */
public class Sort {
    /**
     * 快速排序
     *
     * @param nums 需要排序的数组
     */
    public void quickSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        quickSortSub(nums, 0, nums.length - 1);
    }

    /**
     * 快速排序具体实现
     *
     * @param arr   要排序的数组
     * @param left  要排序的起始位置
     * @param right 要排序的结束位置
     */
    private void quickSortSub(int[] arr, int left, int right) {
        if (left >= right)
            return;
        int i = left, j = right;
        int tmp = arr[left];
        while (i < j) {
            //先遍历尾指针，一直到尾指针指向的数字比目标数字小
            while (i < j && arr[j] > tmp)
                j--;
            if (i < j) {
                // 将尾指针指向的数字赋值给头指针指向的数字，第一次为目标位置
                arr[i] = arr[j];
                i++;        // 此时头指针的位置是正确的，头指针向后移动
            }
            // 遍历头指针，一直到头指针指向的元素比目标元素大
            while (i < j && arr[i] < tmp)
                i++;
            if (i < j) {
                // 将头指针指向的数字赋值给尾指针指向的位置
                arr[j] = arr[i];
                j--;  //此时尾指针位置的元素保证比目标元素大，因此尾指针向前移动
            }
        }
        // 最终i和j位置相同，指向的位置为目标元素应该在的正确位置
        arr[i] = tmp;
        quickSortSub(arr, left, i - 1);
        quickSortSub(arr, i + 1, right);
    }

    /**
     * 堆排序
     *
     * @param nums 要被排序的数组
     */
    public void heapSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        // 建立大根堆（根节点为最大）
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            heapAdjust(nums, i, nums.length);
        }
        for (int i = nums.length - 1; i > 0; i--) {
            // 将第一个数与最后一个数交换，即将最大值替换到最后
            swap(nums, 0, i);
            // 将数组长度减一之后重新进行调整，这个时候只需调整位置0的数字即可，因为调整它能够确保得到最大数
            heapAdjust(nums, 0, i);
        }
    }

    /**
     * 交换数组对应位置的元素
     *
     * @param nums 数组元素
     * @param x    下标1
     * @param y    下标2
     */
    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    /**
     * 调整大根堆
     *
     * @param nums   数组元素
     * @param i      需要调整的元素当前位置
     * @param length 数组长度
     */
    private void heapAdjust(int[] nums, int i, int length) {
        int temp = nums[i];
        // 获取左子节点
        int left = 2 * i + 1;
        while (left < length) {
            // 判断是右子节点大还是左子节点大
            int max = left + 1 < length && nums[left + 1] > nums[left] ? left + 1 : left;
            if (nums[max] <= temp)        // 如果子节点的最大值比父节点小就不用继续比较了，
                // 直接退出循环，注意此时父节点的值被替换成了temp
                break;
            else {       //将子节点的值赋值给父节点，同时更新父节点和左子节点的位置
                nums[i] = nums[max];
                i = max;
                left = 2 * i + 1;
            }
        }
        nums[i] = temp;
    }

    public int[] mergeSort(int[] nums){
        if(nums.length == 1)
            return nums;
        int mid = nums.length/2;
        int[] num1 = new int[mid];
        int[] num2 = new int[nums.length-mid];
        System.arraycopy(nums, 0, num1, 0, num1.length);
        num1 = mergeSort(num1);
        System.arraycopy(nums, mid, num2, 0, num2.length);
        num2 = mergeSort(num2);
        int[] result = merge(num1, num2);
        return result;
    }

    private int[] merge(int[] num1, int [] nums2){
        int[] result = new int[num1.length+ nums2.length];
        int i=0, j=0;
        while(i<num1.length && j<nums2.length){
            if(num1[i]<nums2[j]){
                result[i+j] = num1[i];
                i++;
            }else{
                result[i+j] = nums2[j];
                j++;
            }
        }
        while(i<num1.length){
            result[i+j] = num1[i];
            i++;
        }
        while(j<nums2.length){
            result[i+j] = nums2[j];
            j++;
        }
        return result;
    }
}
