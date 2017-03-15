package Sorting;

/**
 * Created by mastermindzh on 2/10/17.
 */
public class Sorting {

    public static void main(String[] args) {
        new Sorting();
    }

    public Sorting() {
        Integer[] test = {3, 8, 6, 1, 5};

        String[] test2 = {"abc", "abfgsdfg", "abd"};

//        MemoryCell[] test3 = {new MemoryCell(5),new MemoryCell(10), new MemoryCell(3)};
//
//        insertSort(test3);
//        mergeSort(test2);
//        sort(test2);

        quickSort(test2, 0, test2.length -1);
        System.out.println("");
        System.out.println("");

        printArray(test2);

    }

    public void insertSort(Comparable[] array) {
        Comparable temp;
        for (int i = 1; i < array.length; i++) {
            System.out.println("");
            System.out.println("----------- Checking position {" + i + "} -----------");
            for (int j = i; j > 0; j--) {
                System.out.println("Comparing {" + array[j] + "} with {" + array[j - 1] + "}");

                if (array[j].compareTo(array[j - 1]) < 0) {
                    System.out.println(array[j] + " is smaller than " + array[j - 1] + " let's dance!");
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    public Comparable[] mergeSort(Comparable[] list) {
        // empty or 1 element
        if (list.length <= 1) {
            return list;
        }

        // we need 2 parts
        System.out.println("Splitting array: " + list.toString());
        Comparable[] first = new Comparable[list.length / 2];
        Comparable[] second = new Comparable[list.length - first.length];

        System.arraycopy(list, 0, first, 0, first.length);
        System.out.println("Copied data to the first array: " + first.toString());
        System.arraycopy(list, first.length, second, 0, second.length);
        System.out.println("Copied data to the second array: " + second.toString());

        //Sort each half recursively
        System.out.println("I love talking to myself. Especially twice. (recursion)");
        mergeSort(first);
        mergeSort(second);

        //Merge
        System.out.println("Merging : " + first.toString() + " and " + second.toString());
        merge(first, second, list);
        return list;
    }

    private static void merge(Comparable[] first, Comparable[] second, Comparable[] result) {
        // Position in first array - starting with first element
        int leftArray = 0;

        // Position in second array - starting with first element
        int rightArray = 0;

        // Position in merged array - starting with first position
        int resultArray = 0;

        //Compare elements in leftArray and rightArray,
        //insert smaller one to resultArray
        while (leftArray < first.length && rightArray < second.length) {
            if (first[leftArray].compareTo(second[rightArray]) < 0) {
                result[resultArray] = first[leftArray];
                leftArray++;
            } else {
                result[resultArray] = second[rightArray];
                rightArray++;
            }
            resultArray++;
        }
        //copy left to the first half of the original array and right to the second
        System.arraycopy(first, leftArray, result, resultArray, first.length - leftArray);
        System.arraycopy(second, rightArray, result, resultArray, second.length - rightArray);
    }

    public static void printArray(Comparable[] array) {
        for (Object i : array) {
            System.out.println(i.toString());
        }
    }

    public void sort(Comparable[] A) {
        if (A == null || A.length == 0) return;
        quickSort(A, 0, A.length - 1);
    }



    public void quickSort(Comparable[] A, int left , int right) {
        Comparable pivot = A[left + (right - left) / 2].toString();
        int i = left;
        int j = right;
        while (i <= j) {
            while (A[i].compareTo(pivot) < 0) {
                i++;
            }
            while (A[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                switchNumbers(A, i, j);
                i++;
                j--;
            }
        }

        if (left < j){
            quickSort(A, left, j);
        }

        if (i < right){
            quickSort(A, i, right);
        }
    }

    public void switchNumbers(Comparable[] A, int i, int j) {
        Comparable temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}