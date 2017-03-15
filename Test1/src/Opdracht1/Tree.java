package Opdracht1;

/**
 * Created by mastermindzh on 3/15/17.
 */
public class Tree<T extends Comparable<T>> {

    private Node<T> rootNode;

    public Tree(){

    }

    public Tree(Node<T> rootNode) {
        setRootNode(rootNode);
    }

    public Tree(T value) {
        setRootNode(new Node<>(value));
    }

    void removeNodesGreaterOrEqualTo(T x) {
        rootNode.removeNodesGreaterOrEqualTo(x);
    }

    void removeMaximum() {
        removeNodesGreaterOrEqualTo(findMaximum());
    }

    T findMaximum() {
        return getRootNode().findMaximum();
    }

    public void addChild(Node node) {
        if (rootNode == null) {
            rootNode = node;
        } else {
            rootNode.addChild(node);
        }
    }

    public Node<T> getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node<T> rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "rootNode=" + rootNode +
                '}';
    }


}
