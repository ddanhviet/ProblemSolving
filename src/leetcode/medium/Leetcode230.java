package leetcode.medium;

import leetcode.TreeNode;

public class Leetcode230 {
  public int kthSmallest(TreeNode root, int k) {
    int count = 0;
    TreeNode curr = root;
    while (curr != null) {
      TreeNode pre = curr.left;
      while (pre != null && pre.right != curr && pre.right != null) {
        pre = pre.right;
      }

      if (pre != null && pre.right == null) {
        pre.right = curr;
        curr = curr.left;
      } else {
        if (pre != null && pre.right == null)
          pre.right = null;
        count++;
        if (count == k)
          break;
        curr = curr.right;
      }
    }
    return curr.val;
  }

  private static TreeNode createTreeNode() {
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(3);
    root.right = new TreeNode(6);
    root.left.left = new TreeNode(2);
    root.left.right = new TreeNode(4);
    root.left.left.left = new TreeNode(1);

    return root;
  }

  private static TreeNode createTreeNode1() {
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(3);
    root.right = new TreeNode(10);
    root.left.left = new TreeNode(2);
    root.left.right = new TreeNode(4);
    root.right.left = new TreeNode(8);
    root.left.left.left = new TreeNode(1);
    root.right.left.left = new TreeNode(7);
    root.right.left.right = new TreeNode(9);
    root.right.left.left.left = new TreeNode(6);

    return root;
  }

  public static void main(String[] args) {
    TreeNode r = createTreeNode1();
    Leetcode230 kthSmallestBst = new Leetcode230();
    System.out.println(kthSmallestBst.kthSmallest(r, 6));

    System.out.println("Kth Smallest Element in a BST");
  }
}
