package leetcode;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianglab on 2018/4/11.
 */
public class BinaryTreeTraversal {
    /**
     * Given a binary tree, return the inorder traversal of its nodes' values.
     For example:
     Given binary tree [1,null,2,3],
     1
     \
     2
     /
     3
     return [1,3,2].
     Note: Recursive solution is trivial, could you do it iteratively?
     中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        List<Integer> left = inorderTraversal(root.left);
        for (int i = 0; i < left.size(); i++) {
            list.add(left.get(i));
        }
        list.add(root.val);
        List<Integer> right = inorderTraversal(root.right);
        for (int i = 0; i < right.size(); i++) {
            list.add(right.get(i));
        }
        return list;
    }
}
