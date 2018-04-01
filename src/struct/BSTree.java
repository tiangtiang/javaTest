package struct;

/**
 * Created by lianglab on 2018/3/7.
 */
public class BSTree<T> {
    private BSNode<T> root;
    public boolean insert(T value){
        BSNode<T> temp = new BSNode<>(value);
        return insert(temp);
    }
    private boolean insert(BSNode<T> aim){
        if(root == null) {
            root = aim;
            return true;
        }
        BSNode temp = root;
        return false;
    }
}

class BSNode<T>{
    private T value;
    public BSNode(T value){
        this.value = value;
    }
    private BSNode<T> left;
    private BSNode<T> right;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BSNode<T> getLeft() {
        return left;
    }

    public void setLeft(BSNode<T> left) {
        this.left = left;
    }

    public BSNode<T> getRight() {
        return right;
    }

    public void setRight(BSNode<T> right) {
        this.right = right;
    }
}