package Collections.HANLinkedList;

public class Main {

    public static void main(String[] args){
        HANLinkedList<Integer> myList = new HANLinkedList<>();


        myList.addFirst(1);
        myList.addFirst(2);
        myList.addFirst(3);
        myList.addFirst(4);
        myList.addFirst(5);

        System.out.println(myList);

        System.out.println(myList.get(3));

        myList.removeFirst();

        System.out.println(myList);

        myList.delete(2);

        System.out.println(myList);

        myList.insert(2,5);

        System.out.println(myList);

        System.out.println(myList.getTail());

        myList.insert(4,500);

        System.out.println(myList);
        System.out.println(myList.getTail());
        System.out.println(myList.Size());
        myList.delete(myList.Size() -1);


        System.out.println(myList);

        System.out.println(myList.getTail());
        System.out.println(myList.Size());


    }
}
