package leetcode;

import java.util.Arrays;

/**
 * Created by lianglab on 2018/3/26.
 */
public class Square {
    /**
     * 判断四个点是否能组成正方形
     * @param hor
     * @param ver
     * @return
     */
    public boolean canOrNot(int[] hor,int[] ver){
        //判断是否是菱形
        int[] dis = new int[6];
        int count = 0;
        for(int i=0;i<hor.length;i++){
            for(int j= i+1;j<hor.length;j++){
                dis[count++] = getdis(hor[i],hor[j], ver[i], ver[j]);
            }
        }
        Arrays.sort(dis);
        for(int i=1;i<4;i++){
            if(dis[i] != dis[0])
                return false;
        }
        //判断直角
        return isStrict(hor[0], hor[1],hor[2], ver[0],ver[1],ver[2])||
                isStrict(hor[0], hor[1],hor[3], ver[0],ver[1],ver[3])||
                isStrict(hor[0], hor[3],hor[2], ver[0],ver[3],ver[2]);
    }

    /**
     * 返回两个点的距离
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return
     */
    private int getdis(int x1, int x2, int y1, int y2){
        return (x2-x1)*(x2-x1)+(y1-y2)*(y1-y2);
    }

    /**
     * 判断以第一个点为交点的两条直线是否垂直
     * @param x1
     * @param x2
     * @param x3
     * @param y1
     * @param y2
     * @param y3
     * @return
     */
    private boolean isStrict(int x1, int x2, int x3, int y1, int y2, int y3){
        return (y1-y2)*(y1-y3)+(x1-x2)*(x1-x3) == 0;
    }
}
