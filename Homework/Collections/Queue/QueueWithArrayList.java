package Collections.Queue;

import java.util.ArrayList;

public class QueueWithArrayList<T> {

    ArrayList<T> myList;

    public QueueWithArrayList(){
        myList = new ArrayList<>();
    }

    public static void main(String[] args){
        QueueWithArrayList<String> myQueue = new QueueWithArrayList();
        myQueue.enqueue("1");
        myQueue.enqueue("2");
        myQueue.enqueue("3");

        System.out.println(myQueue.getFront());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
    }

    void enqueue(T val){
        myList.add(val);
    }
    T dequeue(){
        T retVal = myList.get(0);
        myList.remove(0);
        return retVal;
    }

    T getFront(){
        return myList.get(0);
    }



}
