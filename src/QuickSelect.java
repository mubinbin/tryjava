import java.util.Arrays;
import java.util.Random;

public class QuickSelect {
    
    public static void quickSelect(int[] arr, int target) throws Exception {
        if (target > arr.length || target < 0) throw new Exception("Invalid input. Target should be less than array length or not less than 0");
        quickSelect(arr, 0, arr.length -1, target);
    }

    private static void quickSelect(int[] arr, int left, int right, int target) {
        if (left == right) return;
        int pivotPos = partition(arr, left, right);
        if (pivotPos == target-1) return;
        else if (pivotPos < target - 1) quickSelect(arr, pivotPos +1, right, target);
        else quickSelect(arr, left, pivotPos -1, target);
    }

    private static int partition(int[] arr, int left, int right) {
        Random random = new Random();
        int randomPos = random.nextInt(right-left+1) + left;
        swap(arr, randomPos, right);

        int pivot = arr[right];
        int finalPos = left;

        for (int i = left; i <= right; i++) {
            if (arr[i] < pivot) {
                swap(arr, finalPos, i);
                finalPos++;
            }
        }
        swap(arr, finalPos, right);
        return finalPos;
    }

    private static void swap(int[] arr, int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {100, 3, 10001, 3, 40, -7, 2, 22, -1000, 37, 3};
        try {
            quickSelect(arr, 5);
            System.out.println(Arrays.toString(arr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
