import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCalendarTwo {

    SegmentTree st;

    public MyCalendarTwo() {
        st = new SegmentTree();
    }

    public boolean book(int start, int end) {
        if (st.checkCountIsTwo(start, end - 1))
            return false;
        st.add(start, end - 1);
        return true;
    }

    class SegmentTree {
        Node root;

        public SegmentTree() {
            root = new Node();
        }

        public void add(int start, int end) {
            root = add(start, end, root);
        }

        private Node add(int start, int end, Node node) {
            if (node == null)
                return new Node(start, end, 1);
            if (end < node.start)
                node.left = add(start, end, node.left);
            if (start > node.end)
                node.right = add(start, end, node.right);
            else if ((start >= node.start && start <= node.end) || (end <= node.end && end >= node.start)) {
                int smallerStart = start >= node.start ? node.start : start;
                int smallerEnd = end >= node.end ? node.end : end;
                int largerStart = start >= node.start ? start : node.start;
                int largerEnd = end >= node.end ? end : node.end;

                update(largerStart, smallerEnd, node);
                if (smallerStart != largerStart) {
                    node.left = add(smallerStart, largerStart - 1, node.left);
                }

                if (smallerEnd != largerEnd) {
                    node.right = add(smallerEnd + 1, largerEnd, node.right);
                }
            }

            return node;
        }

        private void update(int start, int end, Node node) {
            node.start = start;
            node.end = end;
            node.count++;
        }

        public boolean checkCountIsTwo(int start, int end) {
            return checkCountIsTwo(start, end, root);
        }

        private boolean checkCountIsTwo(int start, int end, Node node) {
            if (node == null)
                return false;
            if (end < node.start)
                return checkCountIsTwo(start, end, node.left);
            if (start > node.end)
                return checkCountIsTwo(start, end, node.right);

            return node.count == 2;
        }

    }

    static class Node {
        int start=-1, end = -1;
        int count = 0;
        Node left, right;

        public Node() {
        }

        public Node(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        MyCalendarTwo test = new MyCalendarTwo();
        List<Boolean> ans = new ArrayList<>();
        String input = "[47,50];[1,10];[27,36];[40,47];[20,27];[15,23];[10,18];[27,36];[17,25];[8,17]";
        input = input.replace("[", "");
        input = input.replace("]", "");
        String[] inputArr = input.split(";");
        for(String i : inputArr){
            String[] in = i.split(",");
            ans.add(test.book(Integer.parseInt(in[0]), Integer.parseInt(in[1])));
        }

        List<Boolean> expectedAns = Arrays.asList(true,true,true,true,true,true,true,true,false,false);
        for(int i = 0; i < ans.size(); i++) {
            if(expectedAns.get(i) != ans.get(i)) System.out.println("false at pos: " + i);
            else System.out.println("all good");
        }
    }
}
