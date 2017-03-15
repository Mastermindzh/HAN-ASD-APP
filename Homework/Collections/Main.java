package Collections;

public class Main {


    /**
     *
     * Hoofdstuk 6 opdracht 15
     * a. O(n)
     * b. O(n)
     * c. 20 seconden
     * d. You can't use a foreach to remove elements, it's bad practice.
     *
     *
     *
     *
     * 15.1
     *
     *  ____
     * [___][]
     *
     */



    public static void main(String[] args) throws Exception {

        ArrayListWithArray<Integer> myArray = new ArrayListWithArray<Integer>(Integer.class);
        System.out.println(myArray);

        myArray.add(0);
        myArray.add(1);
        myArray.add(2);

        myArray.set(4, 4);

        System.out.println(myArray);

        myArray.add(3);

        myArray.add(5);
        myArray.add(6);
        myArray.add(7);
        myArray.add(8);
        myArray.add(9);

        System.out.println(myArray);

        myArray.add(10);

        System.out.println(myArray);

        myArray.set(2,500);

        System.out.println(myArray);

        myArray.insert(5,5000);

        System.out.println(myArray);

        System.out.println(myArray.get(5));

    }
}
