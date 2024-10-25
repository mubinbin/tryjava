import java.util.ArrayList;
import java.util.List;

public class SortList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return String.valueOf(val);
        }

    }

    public static void print(ListNode node) {
        if (node == null) return;

        System.out.println(node.toString());
        print(node.next);
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);
    }

    static ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
                tail = tail.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
                tail = tail.next;
            }
        }
        tail.next = (list1 != null) ? list1 : list2;
        return dummyHead.next;
    }

    static ListNode getMid(ListNode head) {
        ListNode midPrev = null;
        while (head != null && head.next != null) {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }
        ListNode mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }


    static void funHop(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
        boolean[][] visited = new boolean[rows][cols];

        traverse(0, 0, rows, cols, matrix, visited, ans);
        ans.forEach(e -> System.out.print(e+ " "));

        int i = 0;
        while (i < ans.size()){
            i += 2;
        }
        System.out.println(ans.get(i-2));
    }

    static void traverse(int r, int c, int rows, int cols, int[][] matrix, boolean[][] visited, List<Integer> ans){
        ans.add(matrix[r][c]);
        visited[r][c] = true;

        if  (r < rows-1 && !visited[r+1][c] && (c == 0 || visited[r][c-1])){
            traverse(r+1, c, rows, cols, matrix, visited, ans);
        }else if (c < cols-1 && !visited[r][c+1]){
            traverse(r, c+1, rows, cols, matrix, visited, ans);
        }else if (r > 0 && !visited[r-1][c]){
            traverse(r-1, c, rows, cols, matrix, visited, ans);
        }else if (c > 0 && !visited[r][c-1]){
            traverse(r, c-1, rows, cols, matrix, visited, ans);
        }
    }

    static long fab(long n){
        if (n <= 1) return 1;
        return fab(n-1, 0, 1);
    }

    static long fab(long n, long a, long b){
        long c = a + b;
        a = b;
        if (n == 1) return c;
        return fab(n-1, a, c);
    }


    public static void main(String[] args) {
        // ListNode root = new ListNode(4);
        // ListNode n2 = new ListNode(2);
        // ListNode n1 = new ListNode(1);
        // ListNode n3 = new ListNode(3);
        // root.next = n2;
        // n2.next = n1;
        // n1.next = n3;

        // root = sortList(root);
        // print(root);

        // int[][] matrix = new int[][]{{1,2,3,4}, {7,8,9,5}, {11,15,67,89}};
        // int[][] matrix2 = new int[][]{{9,8,7,6}, {5,4,3,2}, {1,10,11,12}, {20,13,14,16}, {22,17,18,19}};
        // funHop(matrix2);

        System.out.println(fab(1));
    }
}
