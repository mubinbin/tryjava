import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator2 {
    

    public static int calculator2(String s ) {
        if (s == null || s.isEmpty()) return 0;
        int length = s.length();
        int currentNumber = 0;
        int lastNumber = 0;
        int result = 0;
        char lastSign = '+';
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (lastSign == '+' || lastSign == '-') {
                    result += lastNumber;
                    lastNumber = (lastSign == '+') ? currentNumber : -currentNumber;
                } else if (lastSign == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (lastSign == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                lastSign = currentChar;
                currentNumber = 0;
            }
        }
        result += lastNumber;
        return result;
    }

    public static int cal2(String s) {
        if (s == null || s.isEmpty()) return 0;
        int len = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        int num = 0;
        char lastSign = '+';
        for (int i = 0; i < len; i++) {
            char curChar = s.charAt(i);
            if (Character.isDigit(curChar)) {
                num = num*10 + (curChar - '0');
            }
            if (!Character.isDigit(curChar) && !Character.isWhitespace(curChar) || i == len-1) {
                if (lastSign == '+') {
                    stack.addLast(num);
                }else if (lastSign == '-') {
                    stack.addLast(-num);
                }else if (lastSign == '*') {
                    num *= stack.pollLast();
                    stack.addLast(num);
                }else if (lastSign == '-') {
                    num /= stack.pollLast();
                    stack.addLast(num);
                }
                lastSign = curChar;
                num = 0;
            }
    }

        while (!stack.isEmpty()) {
            ans += stack.pollLast();
        }

        return ans;
    }
}
