package Collections.Stack;

import Collections.HANLinkedList.HANLinkedList;

public class HANStack<T> {

    HANLinkedList<T> myList;


    public HANStack() {
        myList = new HANLinkedList<>();
    }

    public void push(T value){
        myList.insert(myList.Size()+1, value);
    }

    public T pop(){
        T myVal = top();
        myList.delete(myList.Size()-1);
        return myVal;
    }

    public T top(){
        return myList.getTail();
    }

    public int getSize(){
        return myList.Size();
    }

    @Override
    public String toString() {
        return "HANStack{" +
                "myList=" + myList +
                '}';
    }
}
