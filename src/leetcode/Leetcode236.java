package leetcode;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Leetcode236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return recurseSearch(root, p, q);
    }

    private TreeNode recurseSearch(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null)
            return null;

        if (root.val == p.val || root.val == q.val)
            return root;

        TreeNode leftResult = recurseSearch(root.left, p, q);
        TreeNode rightResult = recurseSearch(root.right, p, q);
        if (leftResult != null && rightResult != null)
            return root;
        if (leftResult != null)
            return leftResult;
        if (rightResult != null)
            return rightResult;

        return null;
    }

    public static void main(String[] args) {
        TreeNode defaultTest = new TreeNode(3);
        defaultTest.right = new TreeNode(1);
        defaultTest.right.left = new TreeNode(0);
        defaultTest.right.right = new TreeNode(8);
        defaultTest.left = new TreeNode(5);
        defaultTest.left.left = new TreeNode(6);
        defaultTest.left.right = new TreeNode(2);

        Leetcode236 lc236 = new Leetcode236();
        System.out.println(lc236.lowestCommonAncestor(defaultTest, new TreeNode(0), new TreeNode(8)).val);
    }
}
