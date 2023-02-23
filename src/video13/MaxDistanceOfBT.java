/**
 * 返回一棵二叉树的最远距离
 * 树形DP问题
 */
package video13;

import Tree_5.PreInPosTraversal;

public class MaxDistanceOfBT {
    public static class Info {
        /**
         * 根据题目得出的子树要给父节点返回的信息
         */
        private int maxDistance;
        private int height;

        Info(int d, int h) {
            maxDistance = d;
            height = h;
        }
    }

    /**
     * 主函数调用maxDis即可
     * @param root 根节点
     * @return 最大距离
     */
    public static int getMaxDis(PreInPosTraversal.TreeNode root) {
        return process(root).maxDistance;
    }

    /**
     * 两种大情况：
     * 1. node节点参与，这时只有一种可能，最大距离是左树中到node最远的节点经过node到右树中距离node最远的节点
     *    而左树中最远的节点到node的距离就是左树的高度，右树同理
     *    所以这种情况下应该返回 左树高度 + 右树高度 + 1
     * 2. node节点不参与，这时只可能是node节点的最远距离不经过node节点，而是在node节点的左树或者右树上
     *    这时只需要取二者距离最大值返回即可
     * 所以总结下来，对于node节点来说，maxDis = max(左树高度 + 右树高度 + 1, 右树高度， 左树高度)
     * 抽出左右子树返回的共同信息：高度和最大距离，所以递归要返回的就是这两个信息
     * 然后node节点加工出自己的信息返回
     * @param node node
     * @return 各自的信息
     */
    public static Info process(PreInPosTraversal.TreeNode node) {
        // base case
        if (node == null) {
            return new Info(0, 0);
        }

        Info left = process(node.left);
        Info right = process(node.right);
        // max只能接受两个参数
        int maxDis = Math.max(left.height + right.height + 1, Math.max(left.maxDistance, right.maxDistance));
        int height = Math.max(left.height, right.height) + 1;

        return new Info(maxDis, height);
    }
}
