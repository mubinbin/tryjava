import java.util.Arrays;

public class BitOperation {

    static int reverseSign(int num) {
        // this is twos complement
        // 1110 = -2
        // 1111 = -1
        // 0000 = 0
        // 0001 = 1
        // 0010 = 2
        return ~num + 1;
    }

    static boolean isEven(int num) {
        return (num & 1) == 0; 
    }

    static void swap(int[] arr, int pos1, int pos2) {
        arr[pos1] ^= arr[pos2];
        arr[pos2] ^= arr[pos1];
        arr[pos1] ^= arr[pos2];
    }

    static int removeTheLast1Bit(int n) {
        return n & (n - 1);
        // powerOf2 is n > 0 && (n & (n - 1)) == 0, since any number is power of 2 has exactly 1 bit

    }

    static int sum(int num1, int num2) {
        return num2 == 0? num1 : sum(num1^num2, (num1 & num2) << 1);
    }

    static int diff(int x, int y) {
        return y == 0? x : diff(x^y, ((~x) & y) <<1 );
    }
    // x >> 1 is x/2
    // x & 1 is x%2 
    // x << 1 is x*2
    // x ^ 0 = x
    // x ^ x = 0

    public static void main(String[] args) {

        int num = -9;

        System.out.println(num >> 31);;
        System.out.println(reverseSign(num));
        System.out.println(isEven(num));

        int[] arr = new int[] {0, 1, 1, 2, 2, 3, 3, 4, 4};
        swap(arr, 0, 4);
        System.out.println(Arrays.toString(arr));
        
        // a ^ b ^ b = a
        int onlyOnce = arr[0];
        for(int i = 1; i < arr.length; i++) {
            onlyOnce ^= arr[i];
        }
        System.out.println("onlyTwo is " + onlyOnce);
        num = Math.abs(num);
        System.out.println(num);

        System.out.println(sum(5, 6));
        System.out.println(diff(9,99));
        System.out.println(Integer.toBinaryString(20));
    }
}
