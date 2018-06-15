package leetcode;

import java.util.HashMap;

/**
 * Created by tiang on 2017/10/30.dd
 * 主函数
 */
public class TestMain {
    public static void main(String[] args) throws InterruptedException {
//        double rate = getWinRage( new int[]{
//                3, 5, 7
//        },new int[]{
//                2, 6, 8
//        });
//        System.out.println(String.format( "%.4f", rate));
//        Star[] stars = new Star[]{
//                new Star(1, 1),
//                new Star(2, 2 ),
//                new Star(3, 3),
//                new Star(1, 3)
//        };
//        int[][] rects = new int[][]{
//                {1, 1, 2, 2},
//                {1, 1, 3, 3},
//                {2, 2 ,3, 3},
//                {1, 2, 2, 3}
//        };
//        int[] count = getStarCounts(stars, rects);
//        Arrays.stream(count).forEach(System.out::println);
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
//        AStar a = new AStar();
//        int count = a.findPath(chars, 0, 8, 8, 4);
//        System.out.println(count);
        SearchPathByBFS bfs = new SearchPathByBFS();
        int count = bfs.findPath(chars, 0, 8, 8, 4);
        System.out.println(count);
//        int count = getMinCount(chars);
//        System.out.println(count);
    }

    private static double getWinRage(int[] a, int[] b) {
        HashMap<Integer, Integer> card = new HashMap<>();
        for (int i = 1; i <= 13; i++) {
            card.put(i, 4);
        }
        int suma = 0, sumb = 0;
        for (int i = 0; i < 3; i++) {
            suma += a[i];
            card.put(a[i], card.get(a[i]) - 1);

            sumb += b[i];
            card.put(b[i], card.get(b[i]) - 1);
        }
        double rate = 0;

        double diff = sumb - suma;
        for (int i = 1; i <= 13; i++) {
            if (i - diff < 2)
                continue;
            for (int j = 1; j < i - diff && j <= 13; j++) {
                double temp = card.get(i) / 46.0 * card.get(j) / 46;
                String tempStr = String.format("%.5f", temp);
                rate += Double.parseDouble(tempStr);
            }
        }

        return rate;
    }

    static class Star {
        public int x, y;

        public Star(int a, int b) {
            x = a;
            y = b;
        }
    }

    private static int[] getStarCounts(Star[] stars, int[][] rects) {
        int[] count = new int[rects.length];
        for (int i = 0; i < stars.length; i++) {
            for (int j = 0; j < rects.length; j++) {
                if (stars[i].x >= rects[j][0] && stars[i].x <= rects[j][2]
                        && stars[i].y >= rects[j][1] && stars[i].y <= rects[j][3])
                    count[j]++;
            }
        }
        return count;
    }

    private static int getMinCount(char[][] park) {
        int minCount = Integer.MAX_VALUE;
        Integer[][] count = new Integer[park.length][park[0].length];
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[i].length; j++) {
                if (park[i][j] == '@') {
                    minCount = Integer.min(minCount, getMinCount(park, i, j, count, new boolean[park.length][park[0].length]));
                }
            }
        }
        return minCount;
    }

    private static int getMinCount(char[][] park, int x, int y, Integer[][] count, boolean[][] flag) {
        if (count[x][y] != null)
            return count[x][y];
        if (flag[x][y])
            return Integer.MAX_VALUE;
        if (park[x][y] == '*') {
            count[x][y] = 0;
            return 0;
        }
        if (park[x][y] == '#') {
            count[x][y] = Integer.MAX_VALUE;
            return Integer.MAX_VALUE;
        }
        int tempCount = Integer.MAX_VALUE;
        flag[x][y] = true;
        if (x < park.length - 1 && park[x + 1][y] != '#')
            tempCount = Integer.max(getMinCount(park, x + 1, y, count, flag), tempCount);
        if (x > 0 && park[x - 1][y] != '#')
            tempCount = Integer.max(getMinCount(park, x - 1, y, count, flag), tempCount);
        if (y < park[0].length - 1 && park[x][y + 1] != '#')
            tempCount = Integer.max(getMinCount(park, x, y + 1, count, flag), tempCount);
        if (y > 0 && park[x][y - 1] != '#')
            tempCount = Integer.max(getMinCount(park, x, y - 1, count, flag), tempCount);
        count[x][y] = tempCount;
        flag[x][y] = false;
        return count[x][y];
    }
}