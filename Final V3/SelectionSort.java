public class SelectionSort {
    
    private int arrIndex, compareIndex, smallestIndex;
    private int[] array;
    private boolean findSmallest;

    public SelectionSort(int[] array) {
        this.array = array;
        arrIndex = 0;
        compareIndex = 1;
        smallestIndex = 0;
        findSmallest = false;
    }

    public void swap(int arr[], int small, int big) {
        int temp = arr[small];
        arr[small] = arr[big];
        arr[big] = temp;
    }

    public int[] performSelectionSort() {
        // Set the smallest index
        if (findSmallest == false) {
            smallestIndex = arrIndex;
            findSmallest = true;
        }

        //Set the index each time a new smallest is found
        if (array[compareIndex] < array[smallestIndex]) {
            smallestIndex = compareIndex;
        }

        compareIndex++;

        //If the fast pointer reach the end of the array, and no other smallest is found
        if (compareIndex >= array.length) {
            swap(array, smallestIndex, arrIndex);
            arrIndex++;
            compareIndex = arrIndex + 1;
            findSmallest = false;
        }

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

    public int getMinIdx() {
        return smallestIndex;
    }

    public void setMinIdx(int min_idx) {
        this.smallestIndex = min_idx;
    } 
}
