package leetcode;

/**
 * Created by tiang on 2018/5/15.
 *
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 Example:
 Input: 3
 Output: 5
 Explanation:
 Given n = 3, there are a total of 5 unique BST's:

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3

 */
public class UniqueBinarySearchTrees {
    /**
     * 采用动态规划的算法
     * 假设G[i]为1~i个数能组成的所有的二叉树的数量， 则G[n]为我们最终要求的结果
     * 假设F[i,n]为i为根节点时，1~n个数能组成的所有二叉树的数量
     * 所以G[n] = F[1,n]+F[2,n]+...+F[n,n]
     *
     * 同时以i为根节点时，那么(1~i-1)为左侧树，(i+1~n)为右侧树，那么就应该是左侧树的数量*右侧树的数量
     * 由于数字是不重复的，所以左侧树节点数量为i-1，右侧树节点数量为n-i
     * 所以左侧树的可能情况为G[i-1]，右侧树的可能情况为G[n-i]
     * 所以F[i,n] = G[i-1]*G[n-i]
     * 所以G[n] = G[0]*G[n-1]+G[1]*G[n-2]+...+G[n-1]*G[0]
     * 当没有节点时G[0]= 1,只有一个节点的时候G[1]=1;
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] g = new int[n+1];
        g[0] = g[1] = 1;
        for(int i=2;i<=n;i++){
            for(int j=1;j<=i;j++){
                g[i] += g[j-1]*g[i-j];
            }
        }
        return g[n];
    }
}
