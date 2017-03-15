package Collections.HANLinkedList;

public class HANLinkedList<T> {

    Node<T> head;
    Node<T> tail;
    private int size = 0;

    public HANLinkedList() {
        head = new Node<T>((T) new Object());
        tail = head;
        size = 0;
    }

    /**
     * remove first item, exclude head
     */
    public void removeFirst(){
        if(head.getNext() != null){
            Node<T> oldItem = head.getNext();
            head.setNext(oldItem.getNext());
            size--;
        }else{
            shouldThrowException();
        }
    }

    public void addFirst(T value){
        if(head.getNext() != null){
            // get the old item and insert the new node in front of it
            Node<T> myNode = new Node<>(value);
            myNode.setNext(head.getNext());
            head.setNext(myNode);
        }else{
            Node<T> newNode = new Node<>(value);
            head.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public void insert(int index, T value){
        Node<T> previousNode = find(index -1);
        Node<T> newNode = new Node<>(value);
        if(previousNode == null){
            addFirst(value);
        }else{
            if(previousNode.equals(tail)){
                tail = newNode;
            }

            //could be null, if it isn't the above if should have fired.
            newNode.setNext(previousNode.getNext());
            previousNode.setNext(newNode);
            size++;
        }
    }
    public void delete(int index){
        Node<T> lastNode = head;
        Node<T> currentNode = head;
        for(int i=0; i<= index; i++){
            if(currentNode != null){
                lastNode = currentNode;
                currentNode = currentNode.getNext();
            }else{
                shouldThrowException();
            }
        }

        if(currentNode !=null){
            Node<T> myNode = currentNode.getNext();
            if(lastNode !=null){
                lastNode.setNext(myNode);
            }
            if(tail != null && tail.equals(currentNode)){
                tail=lastNode;
            }
            size--;
        }else{
            //empty
        }
    }

    public T get(int index){
        return find(index).getItem();
    }

    public Node<T> find(int index){
        Node<T> currentNode = head;

        for(int i =0; i <= index; i++){
            if(currentNode.getNext() != null){
                currentNode = currentNode.getNext();
            }
        }

        if(currentNode.equals(head)){
            return null;
        }else{
            return currentNode;
        }
    }

    public T getTail(){
        return tail.getItem();
    }

    private void shouldThrowException(){
        System.out.println("should throw out of bounds");
    }

    public int Size(){
        return this.size;
    }

    @Override
    public String toString() {
        return "HANLinkedList{" +
                "head => " + head +
                '}';
    }
}
