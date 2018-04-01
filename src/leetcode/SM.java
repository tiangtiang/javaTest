package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianglab on 2018/3/24.
 */

/**
 * 有两步操作
 * 1. m = s, s = s+s;
 * 2. s = m+s;
 * 初始值s = 1, m = s;
 * 输入n，求采用步骤1或者步骤2，最少经过几步能得到n
 *
 * 采用的是暴力破解，采用一个list，每次执行操作1和操作2，存储每次得到的中间结果
 */
public class SM {
    class Note{
        public int s, m;
        public Note(int m, int s){
            this.s = s;
            this.m = m;
        }
    }

    public int getLessCount(int n){
        List<Note> list = new ArrayList<>();
        list.add(new Note(1, 1));
        return getLessCount(n, list, 0);
    }
    public int getLessCount(int n, List<Note> list, int count){
        List<Note> nextLayer = new ArrayList<>();
        count++;
        for(Note tmp : list){
            //步骤一
            Note t1 = new Note(tmp.s, tmp.s+tmp.s);
            if(t1.s == n)
                return count;
            if(t1.s<n)
                nextLayer.add(t1);
            //步骤二
            Note t2 = new Note(tmp.m, tmp.s+tmp.m);
            if(t2.s == n)
                return count;
            if(t2.s<n)
                nextLayer.add(t2);
        }
        return getLessCount(n, nextLayer, count);
    }
}
