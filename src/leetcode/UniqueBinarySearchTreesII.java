package leetcode;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianglab on 2018/4/11.
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 For example,
 Given n = 3, your program should return all 5 unique BST's shown below.

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3

 */
public class UniqueBinarySearchTreesII {
    /**
     * 找规律，从小到大添加，每次都是根节点或者右节点
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        if(n == 0)
            return result;
        TreeNode init = new TreeNode(1);
        result.add(init);
        for(int i=2;i<=n;i++){
            List<TreeNode> newList = new ArrayList<>();
            for(TreeNode node : result){
                //添加固定的第一个
                TreeNode temp = new TreeNode(i);
                temp.left = node;
                newList.add(temp);
                int count = 1;
                TreeNode r = node;
                while(r.right!=null){
                    r = r.right;
                    count++;
                }
                for(int j=0;j<count;j++) {
                    TreeNode newNode = copy(node);
                    TreeNode t = newNode;
                    for(int k=0;k<j;k++)
                        t = t.right;
                    TreeNode oldRight = t.right;
                    t.right = new TreeNode(i);
                    t.right.left = oldRight;
                    newList.add(newNode);
                }
            }
            result = newList;
        }
        return result;
    }

    /**
     * 手动实现深层复制节点
     * @param node
     * @return
     */
    private TreeNode copy(TreeNode node){
        if(node == null)
            return null;
        TreeNode root = new TreeNode(node.val);
        root.left = copy(node.left);
        root.right = copy(node.right);
        return root;
    }

    public int numTrees(int n) {
        if(n == 0 || n == 1)
            return n;
        int result = 0;
        return result;
    }
}
