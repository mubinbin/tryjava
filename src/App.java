import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    private static int findMin(int[] arr, int target){
        int l = 0;
        int r = arr.length-1;
        int res = -1;

        while(l <= r){
            int m = l + (r - l) / 2;
            if(arr[m] >= target){
                r = m - 1;
                if(arr[m] == target) res = m;
            }else{
                l = m + 1;
            }
        }
        return res;
    }

    private static int findMax(int[] arr, int target){
        int l = 0;
        int r = arr.length-1;
        int res = -1;

        while(l <= r){
            int m = l + (r - l) / 2;
            if(arr[m] <= target){
                l = m + 1;
                if(arr[m] == target) res = m;
            }else{
                r = m - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        // List<Integer> list1 = Arrays.asList(1,2,3,4,5,6);
        // List<Integer> list2 = list1.stream().map(n->n*10).collect(Collectors.toList());

        // List<List<Integer>> nestedList3 = Arrays.asList(list1, list2);
        // System.out.println(nestedList3);
        
        // List<Integer> list3 = nestedList3.stream().flatMap(l -> l.stream().map(item->item + 10)).collect(Collectors.toList());
        // System.out.println(list3);

        // int resMin = findMin(new int[]{5,7,7,8,8,10}, 8);
        // int resMax = findMax(new int[]{5,7,7,8,8,10}, 8);
        // System.out.println(resMin);
        // System.out.println(resMax);

        // [firstIndex, endIndex) == [firstIndex, endIndex-1]
        // String s = "0110";
        // the first 0 will be gone
        // System.out.println(Integer.parseInt(s));
        
        // Integer[] arr = {1,2,3,4,5,6};
        // Collections.reverse(Arrays.asList(arr));
        // Arrays.stream(arr).forEach(System.out::print);
        // System.out.println(Math.round(-1/2));
        // String str = "/home//the/";
        // String[] arr = str.split("/");
        // System.out.println(arr.length);
        // Arrays.stream(arr).forEach(System.out::println);

        System.out.println(Solution.calculator2("-122-3+6*3"));
    }
}
