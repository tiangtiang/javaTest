package leetcode;

import leetcode.util.TreeNode;

/**
 * Created by tiang on 2018/5/15.
 * Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.

 Example 1:

 Input:
 2
 / \
 1   3
 Output: true

 Example 2:

 5
 / \
 1   4
 / \
 3   6
 Output: false
 Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 is 5 but its right child's value is 4.


 */
public class ValidateBinarySearchTree {
    //前一个节点的值
    private int pre = Integer.MIN_VALUE;
    //判断是否是第一个节点
    private boolean first = true;

    /**
     * 判断一个二叉搜索树是否成立，只要中序遍历是递增就没有问题
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;
        if(isValidBST(root.left)){
            if(first || root.val>pre){
                first = false;
                pre = root.val;
                if(isValidBST(root.right))
                    return true;
            }
        }
        return false;
    }
}
