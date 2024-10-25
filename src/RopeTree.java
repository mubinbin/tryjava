import java.util.List;

public class RopeTree {
    Node root;

    private class Node {
        int length;
        Node parent;

        public Node(int length, Node parent) {
            this.length = length;
            this.parent = parent;
        }
    }

    private class LeafNode extends Node {
        String str;
        
        public LeafNode(int length, Node parent, String str) {
            super(length, parent);
            this.str = str;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Leaf(").append(this.length).append(", ").append(this.str).append(")");
            return sb.toString();
        }
    }

    private class InternalNode extends Node {
        Node left;
        Node right;

        public InternalNode(int length, Node parent, Node left, Node right) {
            super(length, parent);
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("internalNode, ").append(this.length);
            return sb.toString();
        }
    }

    public RopeTree() {
        root = new InternalNode(0, null, null, null);
    }

    public Node add(String s) {
        return add(root, null, s);
    }

    private Node add(Node root, InternalNode parent, String s) {
        int len = s.length();
        if (root == null) return new LeafNode(len, parent, s);

        int rootLen = root.length;
        if (len > rootLen) {
            // go right; 2 cases, IN and LN
            if (root instanceof InternalNode) {
                root.length = len + rootLen;
                InternalNode pointer = (InternalNode) root;
                pointer.right = add(pointer.right, pointer, s);
                return pointer;
            }
            else if (root instanceof LeafNode) {
                LeafNode left = (LeafNode) root;
                InternalNode newInternalNode = new InternalNode(len + rootLen, parent, left, null);
                newInternalNode.right = add(newInternalNode.right, newInternalNode, s);
                return newInternalNode;
            }
        }
        else {
            if (root instanceof InternalNode) {
                root.length = len + rootLen;
                InternalNode pointer = (InternalNode) root;
                pointer.left = add(pointer.left, pointer, s);
                return pointer;
            }
            else if (root instanceof LeafNode) {
                LeafNode right = (LeafNode) root;
                InternalNode newInternalNode = new InternalNode(len + rootLen, parent, null, right);
                newInternalNode.left = add(newInternalNode.left, newInternalNode, s);
                return newInternalNode;
            }
        }
        return root;
    }

    public void print(Node root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        if (root instanceof LeafNode) {
            System.out.println(root.toString());
            return;
        }

        if (root instanceof InternalNode) {
            InternalNode pointer = (InternalNode) root;
            System.out.println(root.toString());
            print(pointer.left);
            print(pointer.right);
        }
    }



    public static void main(String[] args) {
        String input0 = "asfoinf lnwfoio weofnwiodsdf";
        String input1 = "asfoinf lnwfoio weofnwiod";
        String input2 = "woiejr pajeofdf;lmoeifpoj 999999999999999999999";
        String input3 = "iojwoemf3poj-0sadfkm3  oi2rj-90fdjplnk32oirSK-M WEWEJ PWEJRLkanfdo";
        String input4 = "iojwoemf3poj-0sadfkm3  oi2rj-90fdjplnk32oirSK-M WEWEJ PWEJRLkanfdoasfoinf lnwfoio weofnwiodsdfGGG";
        
        int len0 = input0.length();
        int len1 = input1.length();
        int len2 = input2.length();
        int len3 = input3.length();
        int len4 = input4.length();
        System.out.println(List.of(len0, len1, len2, len3, len4));

        RopeTree rt = new RopeTree();
        rt.add(input0);
        rt.add(input2);
        rt.add(input3);
        rt.add(input1);
        rt.add(input4);
        rt.print(rt.root);
        
    }
}
