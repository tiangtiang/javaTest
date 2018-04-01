package struct;

/**
 * Created by lianglab on 2018/3/29.
 */
public class ShellSort {
    public static void sort(int[] arr){
//        int gap = obj.length/2;
//        while(gap >= 1) {
//            for (int i = 0; i < gap; i++) {
//                for (int j = i + gap; j < obj.length; j++) {
//                    int temp = obj[j];
//                    int index = j - gap, pre = j;
//                    while (index >= 0 && temp < obj[index]) {
//                        obj[pre] = obj[index];
//                        index -= gap;
//                        pre -= gap;
//                    }
//                    obj[pre] = temp;
//                }
//            }
//            gap /= 2;
//        }
        //增量gap，并逐步缩小增量
                for(int gap=arr.length/2;gap>0;gap/=2){
                         //从第gap个元素，逐个对其所在组进行直接插入排序操作
                        for(int i=gap;i<arr.length;i++){
                                 int j = i;
                                 int temp = arr[j];
                                if(arr[j]<arr[j-gap]){
                                        while(j-gap>=0 && temp<arr[j-gap]){
                                                 //移动法
                                                 arr[j] = arr[j-gap];
                                                 j-=gap;
                                             }
                                         arr[j] = temp;
                                     }
                             }
                     }
    }

    public static void insertSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int temp = arr[i];
            int j = i-1;
            for(;j>=0;j--){
                if(arr[j] > temp){
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }
            arr[j+1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] temp = new int[100000];
        for(int i=0;i<temp.length;i++){
            temp[i] = (int)(Math.random()*temp.length);
        }
        long before = System.currentTimeMillis();
        insertSort(temp);
        long after = System.currentTimeMillis();
        System.out.println("插入排序："+(after - before));
        for(int i=0;i<temp.length;i++){
            temp[i] = (int)Math.random()*temp.length;
        }
        before = System.currentTimeMillis();
        sort(temp);
        after = System.currentTimeMillis();
        System.out.println("希尔排序："+(after - before));
    }
}
