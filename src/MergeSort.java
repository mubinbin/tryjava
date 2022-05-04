import java.util.Arrays;

public class MergeSort {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length-1);
    }

    private static void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sort(arr, left, mid);
        sort(arr, mid+1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int lenOfLeft = mid - left +1;
        int lenOfRight = right - mid;
        int[] arrL = new int[lenOfLeft];
        int[] arrR = new int[lenOfRight];
        
        // copy initial data from arr to new sub array
        for (int i = 0; i < lenOfLeft; i++) {
            arrL[i] = arr[left + i];
        }
        for (int j = 0; j < lenOfRight; j++) {
            arrR[j] = arr[mid + 1 + j];
        }

        int ptrArrL = 0;
        int ptrArrR = 0;
        int ptrArr = left;

        while (ptrArrL < lenOfLeft && ptrArrR < lenOfRight) {
            if (arrL[ptrArrL] <= arrR[ptrArrR]) {
                arr[ptrArr] = arrL[ptrArrL];
                ptrArrL++;
            }else {
                arr[ptrArr] = arrR[ptrArrR];
                ptrArrR++;
            }

            ptrArr++;
        }

        // add remainding nums int left sub array
        while (ptrArrL < lenOfLeft) {
            arr[ptrArr] = arrL[ptrArrL];
            ptrArr++;
            ptrArrL++;
        }
        // add remainding nums int right sub array
        while (ptrArrR < lenOfRight) {
            arr[ptrArr] = arrR[ptrArrR];
            ptrArr++;
            ptrArrR++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {100, 3, 10001, 3, 40, -7, 2, 22, -1000};

        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
