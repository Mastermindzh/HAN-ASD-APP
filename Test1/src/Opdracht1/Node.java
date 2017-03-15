package Opdracht1;

import java.util.ArrayList;

/**
 * Created by mastermindzh on 3/15/17.
 */
public class Node<T extends Comparable<T>>{

    private T value;

    private ArrayList<Node<T>> myChildren = new ArrayList<>();

    public Node(T value){
        this.value = value;
    }

    public void addChild(Node<T> child) {
        myChildren.add(child);
    }

    T findMaximum(){
        T maximum = value;

        for(Node<T> n: myChildren){
            T contester = n.findMaximum();

            if(contester.compareTo(maximum) == 1){
                maximum = contester;
            }
        }
        return maximum;
    }

    void removeNodesGreaterOrEqualTo(T x){
        ArrayList<Node<T>> nodesToBeRemoved = new ArrayList<>();
        for (Node<T> n: myChildren) {
            if(x.compareTo(n.value) <= 0){
                nodesToBeRemoved.add(n);
            }
        }
        removeNodes(nodesToBeRemoved);

        for(Node<T> n:myChildren){
            n.removeNodesGreaterOrEqualTo(x);
        }
    }

    void removeNodes(ArrayList<Node<T>> nodes){
        for(Node<T> n: nodes){
           myChildren.remove(n);
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ArrayList<Node<T>> getMyChildren() {
        return myChildren;
    }

    public void setMyChildren(ArrayList<Node<T>> myChildren) {
        this.myChildren = myChildren;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", myChildren=" + myChildren +
                '}';
    }



    public Node<T> getNodeWithValue(T val) {
        if(value == val){
            return this;
        }else{
            if(!myChildren.isEmpty()){
                for(Node<T> n: myChildren){
                    if(n.getNodeWithValue(val) != null){
                        return n.getNodeWithValue(val);
                    }
                }
            }else{
                return null;
            }
        }

        return null;
    }
}

