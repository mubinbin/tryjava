import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Random;
import java.util.logging.Logger;

public class QuickSort {
        
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivotPos = partition(arr, left, right);
        quickSort(arr, left, pivotPos - 1);
        quickSort(arr, pivotPos + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        Random random = new Random();
        int randomPos = random.nextInt(right-left+1) + left;
        swap(arr, randomPos, right);
        int pivot = arr[right];
        int finalPivotPos = left;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, finalPivotPos, i);
                finalPivotPos++;
            }
        }
        swap(arr, finalPivotPos, right);
        
        return finalPivotPos;
    }

    // pivot = arr[start]
    // [start, left-1] < pivot
    // [left, i-1] = pivot
    // [i, right] = unseen elements
    // [right+1, end] > pivot
    public static void quickSort3Way(int[] arr) {
        quickSort3Way(arr, 0, arr.length-1);
    }

    private static void quickSort3Way(int[] arr, int start, int end) {
        if (start >= end) return;
        int left = start;
        int right = end;

        Random random = new Random();
        int randomPos = random.nextInt(end - start+1) + start;
        swap(arr, randomPos, start);

        int pivot = arr[start];
        int i = start;

        while (i <= right) {
            if (arr[i] == pivot) i++;
            else if (arr[i] < pivot){
                swap(arr, i, left);
                i++;
                left++;
            } 
            else{
                swap(arr, i, right);
                right--;
            } 
        }

        quickSort3Way(arr, start, left-1);
        quickSort3Way(arr, right+1, end);
    }

    private static void swap(int[] arr, int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {100, 3, 10001, 3, 40, -7, 2, 22, -1000};

        quickSort(arr);
        System.out.println(Arrays.toString(arr));
        
        int[] arr2 = {100, 3, 10001, 3, 40, 3, -7, 3, 2, 3, 22, 3, -1000};
        int max = Arrays.stream(arr2).max().getAsInt();
        quickSort3Way(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}
