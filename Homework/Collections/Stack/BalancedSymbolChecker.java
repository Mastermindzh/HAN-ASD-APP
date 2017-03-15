package Collections.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BalancedSymbolChecker {

    HANStack<Character> myStack = new HANStack<>();

    Map<Character, Character> bracketPairs = new HashMap<Character, Character>(){
        {
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }
    };

    boolean check(String str) {
        for(int i = 0; i < str.length(); i++) {
            char charToBeProcessed = str.charAt(i);

            if (bracketPairs.containsKey(charToBeProcessed)) {
                myStack.push(charToBeProcessed);
            } else if (bracketPairs.containsValue(charToBeProcessed)) {
                // check whether stack has an element
                if (myStack.getSize() != 0) {
                    // check whether char on stack (key) has a value of charToBeProcessed
                    if (bracketPairs.get(myStack.pop()) != charToBeProcessed) {
                        return false;
                    }
                }
            }
        }

        if(myStack.getSize() == 0){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args){

        BalancedSymbolChecker myChecker = new BalancedSymbolChecker();
        String validCodeToCheck = "(test:{valid})";
        System.out.println("Should be valid = " + myChecker.check(validCodeToCheck));

        String invalidCodeToCheck = "(test:valid})";
        System.out.println("Should be invalid(returns false) = " + myChecker.check(invalidCodeToCheck));

    }

}
