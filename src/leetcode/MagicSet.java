package leetcode;

import java.util.*;

/**
 * Created by lianglab on 2018/3/24.
 */
public class MagicSet {
    public int magicOperation(Set<Integer> seta, Set<Integer> setb){
        //求ab两个集合的平均数
        long sumA = 0, sumB = 0;
        for(Integer i: seta)
            sumA+=i;

        double aveA = ((double)sumA)/seta.size();
        for(Integer i: setb) sumB += i;
        double aveB = ((double) sumB)/setb.size();

        //从平均值大的集合中向平均值小的集合中添加
        if (aveA < aveB) {
            Set<Integer> temp = seta;
            seta = setb;
            setb = temp;
            double tempave = aveA;
            aveA = aveB;
            aveB = tempave;
            long tempsum = sumA;
            sumA = sumB;
            sumB = tempsum;
        }
        int count = 0;
        for(Integer i: seta){
            if(i>aveA)
                break;
            if(i>aveB && seta.add(i)){
                count++;
            }
        }
        return count;

//        //记录操作次数
//        int count = 0;
//        while(true) {
//
//            //寻找a中最接近平均值的一个数
//            int last = 0, next = 0;
//            boolean first = true;
//            for (Integer i : seta) {
//                if (first) {
//                    next = i;
//                    first = false;
//                }else{
//                    last = next;
//                    next = i;
//                }
//                if (next > aveA)
//                    break;
//            }
//            first = true;
//            //如果小于b中的平均值
//            if (last < aveB)
//                return count;
//            else {
//                count++;
//                //重新计算a的平均值
//                seta.remove(last);
//                sumA -= last;
//                aveA = sumA / seta.size();
//                //记录b的size
//                int oldBsize = setb.size();
//                setb.add(last);
//                if (oldBsize < setb.size()) {
//                    //b中不包含last
//                    sumB+= last;
//                    aveB = sumB / setb.size();
//                }
//            }
//        }
    }

    public static void main(String[] args) {
        TreeSet<Integer> seta = new TreeSet<>((o1,o2)->o1-o2),
                setb = new TreeSet<>((o1,o2)->o1-o2);
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(),b = scanner.nextInt();
        for(int i=0;i<a;i++){
            seta.add(scanner.nextInt());
        }
        for(int i=0;i<b;i++){
            setb.add(scanner.nextInt());
        }
        int result = new MagicSet().magicOperation(seta, setb);
        System.out.println(result);
    }

}
