package Tree_5;

public class IsBalancedTree {
    public static class IsBalance {
        int height;
        boolean isBalance;
        IsBalance(int h, boolean b) {
            this.isBalance = b;
            this.height = h;
        }
    }

    public static boolean isBalance(PreInPosTraversal.TreeNode root) {
        return process(root).isBalance;
    }

    /**
     * 递归套路：
     * 1. 确定终止状态和终止状态需要返回的信息
     * 2. 确定每次递归结束需要返回的信息
     * 3. 根据这些信息所需要的最终的返回结果
     * @param node 每次进行检查的节点
     * @return 是否是平衡二叉树
     */
    public static IsBalance process(PreInPosTraversal.TreeNode node) {
        // 终止状态
        if (node == null) {
            return new IsBalance(0, true);
        }
        // 左右子树是否是平衡二叉树   从左树拿到的信息left，从右树拿到的信息right
        IsBalance left = process(node.left);
        IsBalance right = process(node.right);

        // 从左树和右树拿到的信息加工出node节点的信息，三者是且的关系
        // 1. 左子树是平衡二叉树
        // 2. 右子树是平衡二叉树
        // 3. 左右子树高度不大于1
        int height = Math.abs(left.height - right.height) + 1;
        boolean isBalance = left.isBalance && right.isBalance && (Math.abs(left.height - right.height) < 2);

        return new IsBalance(height, isBalance);
    }
}
