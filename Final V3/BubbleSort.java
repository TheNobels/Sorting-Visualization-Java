public class BubbleSort {
    
    private int[] array;
    private int arrIndex, compareIndex;

    public BubbleSort(int[] array) {
        this.array = array;
        arrIndex = 0;
        compareIndex = Integer.MAX_VALUE;
    }

    public int[] performBubbleSort() {

        if (array[compareIndex] > array[compareIndex+1]) {
            int temp = array[compareIndex];
            array[compareIndex] = array[compareIndex+1];
            array[compareIndex+1] = temp;
        }
        
        if ((compareIndex+1) >= (array.length - arrIndex - 1)) {
            arrIndex++;
            compareIndex = 0;
        }
        
        else compareIndex++;

        return array;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getArrayIndex() {
        return arrIndex;
    }

    public void setArrayIndex(int array_index) {
        this.arrIndex = array_index;
    }

    public int getCompareIndex() {
        return compareIndex;
    }

    public void setCompareIndex(int compare_index) {
        this.compareIndex = compare_index;
    }
}
