package leetcode;


/*

*/

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode117 {

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        Node parent = root;
        while (parent != null) {
            Node nextLeftest = null;
            // curr -> next
            Node curr = null;
            Node next = parent.left;
            while (parent != null) {
                if (next != null)
                    curr = next;
                if (next == parent.left)
                    next = parent.right;
                else next = parent.left;

                if (curr != null)
                    curr.next = next;

                // save next leftest
                if (nextLeftest == null) {
                    nextLeftest = curr;
                    if (nextLeftest == null)
                        nextLeftest = next;
                }
                if (next == parent.right) {
                    parent = parent.next;
                }
            }
            // if null to the right -> next leftest
            parent = nextLeftest;
        }
        return root;
    }

    public Node buildTree(Integer[] treeArray) {
        if (treeArray.length == 0)
            return null;

        Node root = new Node(treeArray[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        for (int i=1; i<treeArray.length; i+=2) {
            Node curr = q.poll();
            if (treeArray[i] != null) {
                curr.left = new Node(treeArray[i]);
                q.add(curr.left);
            }
            if (i+1 < treeArray.length && treeArray[i+1] != null) {
                curr.right = new Node(treeArray[i+1]);
                q.add(curr.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Leetcode117 lc117 = new Leetcode117();

        Integer[] inputTreeArray = new Integer[] {
                1,null,-9,null,8,4,-3,null,null,-5,null,-6,null,null,-7,-4,9,null,null,6
        };
        Node inputTree = lc117.buildTree(inputTreeArray);
        lc117.connect(inputTree);
    }
}
