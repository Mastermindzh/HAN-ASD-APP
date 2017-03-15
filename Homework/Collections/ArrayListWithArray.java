package Collections;

import java.lang.reflect.Array;
import java.util.Arrays;

class ArrayListWithArray<T> {

    private T[] arr;

    // we could check whether the array is full.... but that is slow.
    private int size;
    private int currentSize = 0;
    private Class<T> myClass;

    ArrayListWithArray(Class<T> c){
        constructor(c,10);
    }

    private void constructor(Class<T>c, int size){
        myClass = c;
        this.size = size;
        @SuppressWarnings("unchecked")
        T[] a = (T[]) Array.newInstance(myClass, size);
        this.arr = a;
    }

    public T get(int index) throws Exception {
        if(arr.length > index && arr[index] != null){
            return arr[index];
        }else{
            throw new IndexOutOfBoundsException("Get method returned outofbounds");
        }
    }

    void add(T item){
        checkForDoubling();
        currentSize++;
        if(arr[currentSize -1] != null){
            add(item);
        }else{
            this.arr[currentSize -1] = item;
        }
    }

    void set(int index, T item){
        if(index < size){
            arr[index] = item;
        }
    }

    private void checkForDoubling(){
        if(this.currentSize == this.size){
            if(this.size == 0){
                this.size = 0;
            }else{
                this.size *= 2;
            }
            @SuppressWarnings("unchecked")
            T[] tempArray = (T[]) Array.newInstance(this.myClass, size);
            System.arraycopy(arr, 0, tempArray,0, arr.length);
            arr = tempArray;
        }
    }

    void insert(int index, T item){
        checkForDoubling();
        currentSize++;

        // copy first part of array
        @SuppressWarnings("unchecked")
        T[] tempArrayFirst = (T[]) Array.newInstance(this.myClass, size);
        System.arraycopy(arr,0,tempArrayFirst,0,index +1);

        // insert element
        tempArrayFirst[index] = item;


        // copy other part of the array back
        System.arraycopy(arr,index, tempArrayFirst, index+1, arr.length - index -1);

        // overwrite old array
        this.arr = tempArrayFirst;
    }

    @Override
    public String toString() {
        return "ArrayListWithArray{" +
                "arr=" + Arrays.toString(arr) +
                ", size=" + size +
                ", currentSize=" + currentSize +
                ", myClass=" + myClass +
                '}';
    }

    public T[] getArr() {
        return arr;
    }

    public void setArr(T[] arr) {
        this.arr = arr;
    }

    public Class<T> getMyClass() {
        return myClass;
    }
}
