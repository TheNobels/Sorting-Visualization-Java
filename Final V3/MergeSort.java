

public class MergeSort{

    private int[] array;
    private int arrIndex, compare_index;
    private boolean isMerging;


    public MergeSort(int[] array) {
        this.array = array;
        arrIndex = 0;
        compare_index = 1;
        isMerging = false;
    }

    int[] mergeSort(int[] array, int start, int end) {
        //Base case
        if (end<=start) {
            return array;
        }

        int middle = (start + end) / 2;

        mergeSort(array, start, middle);
        mergeSort(array, middle + 1, end);

        int[] temp  = new int[array.length];
        

        int left = start, right = middle + 1, k = 0;
        
        if (isMerging == false) {
            isMerging = true;
            arrIndex = start;
            compare_index = right;
        }

        while (left <= middle && right <= end) {
            if (array[left] <= array[right]) {
                temp[k] = array[left];
                left++;
                arrIndex++;
            } else {
                temp[k] = array[right];
                right++;
                compare_index++;
            }
            k++;
        }

        while (left <= middle) {
            temp[k] = array[left];
            left++;
            k++;
            arrIndex++;
        }

        while (right <= end) {
            temp[k] = array[right];
            right++;
            k++;
            compare_index++;
        }
        
        k = 0;
        
        for(int i = start; i <= end; i++){
            array[i] = temp[k++];
        }

        isMerging = false;

        return array;

    }


    public int[] performMerge() {
        

        return mergeSort(array, 0, array.length-1);

    }

    public void setArrayIndex(int array_index) {
        this.arrIndex = array_index;
    }

    public int getArrayIndex() {
        return arrIndex;
    }

    public int getCompareIndex() {
        return compare_index;
    }

    public void setCompareIndex(int compare_index) {
        this.compare_index = compare_index;
    }

}
