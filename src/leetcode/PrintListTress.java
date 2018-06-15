package leetcode;

import java.util.*;

/**
 * Created by tiang on 2018/6/3.
 */
public class PrintListTress {
    /**
     * 先序遍历树
     * @param pairs
     */
    private static void printTree(List<TreePair> pairs){
        HashMap<Integer, Integer> degree = new HashMap<>();
        for (TreePair pair : pairs){
            if(!degree.containsKey(pair.parent))
                degree.put(pair.parent, 0);
            if(!degree.containsKey(pair.son))
                degree.put(pair.son, 1);
            else
                degree.put(pair.son, degree.get(pair.son)+1);
        }
        // 找出根节点
        int root = 0;
        for(TreePair pair : pairs){
            if(degree.get(pair.parent) == 0) {
                root = pair.parent;
                break;
            }
        }
        Deque<Integer> queue = new LinkedList<>();
        queue.push(root);
        while(!queue.isEmpty()){
            int temp = queue.poll();
            System.out.println(temp);
            Stack<Integer> tempStack = new Stack<>();
            for(TreePair pair : pairs){
                if(pair.parent == temp){
                    tempStack.push(pair.son);
                }
            }
            while(!tempStack.empty()){
                queue.addFirst(tempStack.pop());
            }
        }
    }
}


/**
 * 树节点（父节点，子节点）
 * 先出现为左子节点
 */
class TreePair{
    public TreePair(int x, int y){
        parent = x;
        son = y;
    }
    public int parent;
    public int son;
}
