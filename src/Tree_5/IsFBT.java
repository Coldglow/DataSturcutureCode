/**
 * 判断一个树是不是满二叉树，根据关系式节点数 = 高度^2 - 1, 使用递归套路求解
 */
package Tree_5;

import Tree_5.PreInPosTraversal.TreeNode;

public class IsFBT {

    // 返回信息是子树的高度和节点数
    public static class Info {
        int height;
        int nodes;

        Info(int h, int n) {
            this.height = h;
            this.nodes = n;
        }
    }

    public static boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }

        Info data = process(root);
        return data.nodes == (1 << data.height - 1);
    }

    public static Info process(TreeNode node) {
        if(node == null) {
            return new Info(0, 0);    // 返回
        }

        // 左右子树的返回信息
        Info left = process(node.left);
        Info right = process(node.right);

        // 根据左右子树的信息加工出node节点的信息
        int height = Math.max(left.height, right.height) + 1;
        int nodes = left.nodes + right.nodes + 1;
        //node节点返回加工后的信息结构
        return new Info(height, nodes);
    }
}
