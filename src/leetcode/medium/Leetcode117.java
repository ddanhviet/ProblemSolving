package leetcode.medium;

import leetcode.TreeNode;

public class Leetcode117 {

    // Definition for a Node.
    class Node extends TreeNode {
        public Node next;

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
            Node next = (Node) parent.left;
            while (parent != null) {
                if (next != null)
                    curr = next;
                if (next == parent.left)
                    next = (Node) parent.right;
                else next = (Node) parent.left;

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

    public static void main(String[] args) {
        System.out.println("Populating Next Right Pointers in Each Node II");

        Leetcode117 lc117 = new Leetcode117();

        Integer[] inputTreeArray = new Integer[] {
                1,null,-9,null,8,4,-3,null,null,-5,null,-6,null,null,-7,-4,9,null,null,6
        };
        Node inputTree = (Node) TreeNode.serialize(inputTreeArray);
        lc117.connect(inputTree);
    }
}
