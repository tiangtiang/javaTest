package leetcode;

import java.util.*;

/**
 * Created by tiang on 2018/6/15.
 *
 char[][] chars = new char[][]{
 {'.', '@', '.', '.', '.', '.', '#', '#', '@', '.'},
 {'.', '.', '.', '.', '.', '.', '#', '.', '.', '.'},
 {'.', '.', '.', '@', '.', '.', '#', '.', '.', '.'},
 {'#', '#', '#', '.', '.', '.', '.', '.', '.', '.'},
 {'.', '.', '.', '.', '#', '#', '.', '.', '#', '.'},
 {'.', '.', '.', '#', '#', '#', '#', '.', '.', '.'},
 {'@', '.', '.', '.', '#', '#', '.', '.', '.', '.'},
 {'#', '#', '#', '#', '#', '.', '.', '.', '.', '.'},
 {'.', '.', '#', '#', '*', '#', '#', '#', '#', '.'},
 {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
 };
 @ 代表入口，.代表路，#代表障碍物
 * A * 寻路算法
 */
public class AStar {
    /**
     * 节点类
     */
    private static class PathNode{
        // G 表示到达这一节点用了几步
        // H 表示当前节点与目标节点的距离
        // F 表示综合评价
        public int G, H, F;
        // 当前节点的横纵坐标
        public int x, y;
        public PathNode parent;
        public PathNode(int g, int h, PathNode p, int x, int y){
            G = g;
            H = h;
            F = G+H;
            parent = p;
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            PathNode node = (PathNode) obj;
            return node.x == this.x && node.y == this.y;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }

    /**
     * 找到从起始点到结束点的一条尽可能短的路径
     * @param map
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public int findPath(char[][] map, int startX, int startY, int endX, int endY){
        // openList用来存储可以到达的节点， closeList用来存储已经到达的节点
        List<PathNode> openList = new ArrayList<>(),
                closeList = new ArrayList<>();
        openList.add(new PathNode(0, 0, null, startX, startY));
        while(!openList.isEmpty() && !closeList.contains(new PathNode(0, 0, null, endX, endY))) {
            PathNode node = findSmallestPathNode(openList);
            openList.remove(node);
            closeList.add(node);
            findNext(openList, closeList, node, map, endX, endY);
        }
        PathNode end = findNode(closeList, endX, endY);
        PathNode next = end;
        int count = 0;
        while(next!=null){
            System.out.println(next);
            next = next.parent;
            count++;
        }
        return count-1;
    }

    /**
     * 计算当前节点与目标节点的距离, 该函数的好坏会影响最终的算法结果
     * 当该函数的值越小，结果越精确，但是搜索的区域越大，越耗时
     * @param posX
     * @param posY
     * @param endX
     * @param endY
     * @return
     */
    private int calculateH(int posX, int posY, int endX, int endY){
        // 获取横纵坐标的距离
//        return Math.abs(endX - posX) + Math.abs(endY - posY);
        return 0;
    }

    /**
     * 寻找OpenList中F值最小的一个元素
     * @param list
     * @return
     */
    private PathNode findSmallestPathNode(List<PathNode> list){
        return list.stream().min(Comparator.comparingInt(a -> a.F)).get();
    }

    /**
     * 寻找下一步移动目标， 上下左右移动
     * @param openList
     * @param closeList
     * @param now
     * @param map
     * @param endX
     * @param endY
     */
    private void findNext(List<PathNode> openList, List<PathNode> closeList, PathNode now,
                          char[][] map, int endX, int endY) {
        if (now.x > 0 && map[now.x - 1][now.y] != '#') {
            PathNode left = new PathNode(now.G + 1, calculateH(now.x - 1, now.y, endX, endY), now, now.x - 1, now.y);
            if (!(openList.contains(left) || closeList.contains(left)))
                openList.add(left);
        }
        if (now.x < map.length - 1 && map[now.x + 1][now.y] != '#') {
            PathNode right = new PathNode(now.G + 1,
                    calculateH(now.x + 1, now.y, endX, endY), now, now.x + 1, now.y);
            if (!(openList.contains(right) || closeList.contains(right)))
                openList.add(right);
        }
        if (now.y > 0 && map[now.x][now.y - 1] != '#') {
            PathNode top = new PathNode(now.G + 1,
                    calculateH(now.x, now.y - 1, endX, endY), now, now.x, now.y - 1);
            if (!(openList.contains(top) || closeList.contains(top)))
                openList.add(top);
        }
        if (now.y < map[0].length - 1 && map[now.x][now.y + 1] != '#') {
            PathNode down = new PathNode(now.G + 1,
                    calculateH(now.x, now.y + 1, endX, endY), now, now.x, now.y + 1);
            if (!openList.contains(down) && !closeList.contains(down))
                openList.add(down);
        }
    }

    /**
     * 查找某个坐标的节点是否存在
     * @param list
     * @param endX
     * @param endY
     * @return
     */
    private PathNode findNode(List<PathNode> list, int endX, int endY){
        for(PathNode node : list){
            if(node.x == endX && node.y == endY)
                return node;
        }
        return null;
    }
}

/**
 * 广度优先算法寻找最短路径
 */
class SearchPathByBFS{
    /**
     * 路径节点
     */
    private static class PathNode{
        // 横纵坐标
        public int x, y;
        // 父节点，用来查询路径
        public PathNode parent;
        public PathNode(int x, int y, PathNode par){
            this.x = x;
            this.y = y;
            this.parent = par;
        }
    }
    public int findPath(char[][] map, int startX, int startY, int endX, int endY){
        // 用来存储到达每个节点的步数
        int[][] values = new int[map.length][map[0].length];
        // 队列，用来存储下一层的节点
        Queue<PathNode> level = new LinkedList<>();
        // 初始是起始节点
        level.add(new PathNode(startX, startY, null));
        // 存放已经遍历过的节点，为了最后寻找路径
        List<PathNode> result = new ArrayList<>();
        while(!level.isEmpty() && !exist(result, endX, endY)){
            // 获取头节点
            PathNode now = level.poll();
            result.add(now);
            // 上下左右节点，只有未遍历过的节点才能进入
            if(now.x>0 && map[now.x-1][now.y] != '#' && values[now.x -1][now.y] == 0){
                values[now.x -1][now.y] = values[now.x][now.y]+1;
                level.add(new PathNode(now.x-1, now.y, now));
            }
            if(now.x<map.length-1 && map[now.x+1][now.y]!='#' && values[now.x +1][now.y] == 0){
                 values[now.x+1][now.y] = values[now.x][now.y]+1;
                 level.add(new PathNode(now.x+1, now.y, now));
            }
            if(now.y>0 && map[now.x][now.y-1]!='#' && values[now.x][now.y-1] == 0){
                values[now.x][now.y-1] = values[now.x][now.y]+1;
                level.add(new PathNode(now.x, now.y-1, now));
            }
            if(now.y<map[0].length-1 && map[now.x][now.y+1]!='#' && values[now.x][now.y+1] == 0){
                values[now.x][now.y+1] = values[now.x][now.y]+1;
                level.add(new PathNode(now.x, now.y+1, now));
            }
        }
        PathNode end = get(result, endX, endY);
        PathNode next = end;
        while(next!=null){
            System.out.println(String.format("(%d, %d)", next.x, next.y));
            next = next.parent;
        }
        return values[endX][endY];
    }

    /**
     * 判断节点是否已经存在了
     * @param list
     * @param endX
     * @param endY
     * @return
     */
    private boolean exist(List<PathNode> list, int endX, int endY){
        for(PathNode node : list){
            if(node.x == endX && node.y == endY)
                return true;
        }
        return false;
    }

    /**
     * 获取指定节点
     * @param list
     * @param endX
     * @param endY
     * @return
     */
    private PathNode get(List<PathNode> list, int endX, int endY){
        for(PathNode node : list){
            if(node.x == endX && node.y == endY)
                return node;
        }
        return null;
    }
}
