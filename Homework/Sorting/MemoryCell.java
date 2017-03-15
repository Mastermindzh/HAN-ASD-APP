package Sorting;

public class MemoryCell implements Comparable {

    int value;

    public MemoryCell(int i){
        setValue(i);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MemoryCell{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        final int SMALLER = -1;
        final int EQUAL = 0;
        final int BIGGER = 1;

        //this optimization is usually worthwhile, and can
        //always be added
        if ( this == o ) return EQUAL;

        //primitive numbers follow this form
        if (this.value < ((MemoryCell)o).value){
            return SMALLER;
        }else{
            return BIGGER;
        }
    }
}
