package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tiang on 2017/10/18.
 * 构建树的类
 */
public class Tree<L extends TreeLeaf, T extends TreeNode<L>> {
    private T root;

    void makeTree(List<T> nodeList, List<L> leafList) {
        //根据ID进行分类
        Map<String, List<L>> leafs = classfify(leafList);
        //为每个node赋值叶节点
        //可以对叶节点列表进行过滤
        for (T node : nodeList) {

            node.leftList = node.filter != null ? node.filter.filterLeaf(leafs.get(node.ID)) : leafs.get(node.ID);

        }
        //构建树
        for (T node : nodeList) {
            if (node.parentId == null || node.parentId.equals(""))
                root = node;
            else {
                for (T parentNode : nodeList) {
                    if (node.parentId.equals(parentNode.ID) && node != parentNode) {
                        if (parentNode.sonNodeList == null)
                            parentNode.sonNodeList = new ArrayList<>();
                        parentNode.sonNodeList.add(node);
                        break;
                    }
                }
            }
        }
    }

    private Map<String, List<L>> classfify(List<L> leafList) {
        Map<String, List<L>> result = new HashMap<>();
        for (L leaf : leafList) {
            result.computeIfAbsent(leaf.id, k -> new ArrayList<>());
            result.get(leaf.id).add(leaf);
        }
        return result;
    }

    public T getRoot() {
        return root;
    }

    void printTree(){
        int depth = 0;
        printTree(root.sonNodeList, depth);
    }

    private void printTree(List<TreeNode> nodeList, int depth){
        for(TreeNode<L> node : nodeList){
            for(int i=0;i<depth;i++)
                System.out.print("\t");
            System.out.println(node);
            depth++;
            //打印叶子节点
            if(node.leftList!=null){
                for(L leaf : node.leftList){
                    for(int i=0;i<depth;i++)
                        System.out.print("\t");
                    System.out.println(leaf);
                }
            }
            if(node.sonNodeList!=null){
                printTree(node.sonNodeList, depth);
            }
            depth--;
        }
    }
}

@FunctionalInterface
interface LeafFilter<T extends TreeLeaf> {
    List<T> filterLeaf(List<T> leafs);
}