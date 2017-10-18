package tree;

import java.util.List;

/**
 * Created by tiang on 2017/10/18.
 * 根节点的基类
 */
public abstract class TreeNode<T extends TreeLeaf> {
    //节点名
    String nodeName;
    //子节点列表
    List<TreeNode> sonNodeList;
    //叶子结点列表
    List<T> leftList;
    //父节点ID
    String parentId;
    //节点ID
    String ID;
    //过滤方法
    LeafFilter<T> filter;

    public String toString(){
        return nodeName;
    }
}
