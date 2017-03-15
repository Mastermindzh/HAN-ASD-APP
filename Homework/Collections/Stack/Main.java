package Collections.Stack;

/**
 * Created by mastermindzh on 2/24/17.
 */
public class Main {

    public static void main(String[] args){
        HANStack<String> myStack = new HANStack<>();

        // should be 0
        System.out.println(myStack.getSize());

        //should insert test and increase size to 1
        myStack.push("test");
        System.out.println(myStack.getSize());
        System.out.println(myStack);


        //should remove the first element and leave the thing empty (size = 0)
        String test = myStack.pop();
        System.out.println(test);
        System.out.println(myStack);
        System.out.println(myStack.getSize());

        //insert 3 elements, list should be 3 long
        myStack.push("test2");
        myStack.push("test3");
        myStack.push("test4");
        System.out.println(myStack);
        System.out.println(myStack.getSize());

        //should pop off all but test 2 values
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.getSize());

        //should return test 2 without removing it
        System.out.println(myStack.top());
        System.out.println(myStack.getSize());


    }
}
