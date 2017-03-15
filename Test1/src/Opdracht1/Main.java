package Opdracht1;

/**
 * Created by mastermindzh on 3/15/17.
 */
public class Main {

    public static void main(String[] args){


        Tree<Integer> myTree = new Tree<>();

        Node<Integer> node17 = new Node<>(17);
        myTree.setRootNode(node17);

        Node<Integer> node12 = new Node<>(12);
        Node<Integer> node7 = new Node<>(7);
        Node<Integer> node24 = new Node<>(24);
        Node<Integer> node5 = new Node<>(5);

        node12.addChild(node7);
        node12.addChild(node24);
        node12.addChild(node5);

        myTree.addChild(node12);

        Node<Integer> node33 = new Node<>(33);
        Node<Integer> node15 = new Node<>(15);
        Node<Integer> node58 = new Node<>(58);
        node33.addChild(node15);
        node33.addChild(node58);

        myTree.addChild(node33);

//        myTree.removeNodesGreaterOrEqualTo(20);

        myTree.removeMaximum();
        System.out.println(myTree);

    }
}
