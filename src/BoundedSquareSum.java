import java.util.Arrays;

public class BoundedSquareSum {
    
    public static int boundedSquareSum(int[] a, int[] b, int lower, int upper) {
        int n = a.length;
        int m = b.length;
        if (n > m) {
            return boundedSquareSum(b, a, lower, upper);
        }

        for (int i = 0; i < n; i++) {
            long sq = a[i] * a[i];
            if (sq > Integer.MAX_VALUE) {
                a[i] = Integer.MAX_VALUE;
            } else {
                a[i] = (int) sq;
            }
        }
        Arrays.sort(a);
        
        int count = 0;
        int aMin = a[0];
        int aMax = a[n - 1];
        if (aMin > upper) {
            return count;
        }
        
        for (int i = 0; i < m; i++) {
            long sq = b[i] * b[i];
            if (sq > Integer.MAX_VALUE || sq > upper || (aMax + (int) sq < lower)) {
                continue;
            }
            Integer left = binarySearch(a, lower - (int) sq, true);
            Integer right = binarySearch(a, upper - (int) sq, false);
            if (left != null && right != null) {
                count += right - left + 1;
            }
        }

        return count;
    }

    private static Integer binarySearch(int[] nums, int val, boolean isLeftValue) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (isLeftValue) {
                if (nums[mid] >= val) {
                    if (mid == 0 || nums[mid - 1] < val) {
                        return mid;
                    }
                    right = mid - 1;
                } else if (nums[mid] < val) {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] <= val) {
                    if (mid == n - 1 || nums[mid + 1] > val) {
                        return mid;
                    }
                    left = mid + 1;
                } else if (nums[mid] > val) {
                    right = mid - 1;
                }
            }
        }
        return left;
    }




    public static void main(String[] args) {
        int[] a = {1,2,1,1,1};
        int[] b = {1,1,1,1,1};
        int res = BoundedSquareSum.boundedSquareSum(a,b,0,2);
        System.out.println(res);
    }
}
