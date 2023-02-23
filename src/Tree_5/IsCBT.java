/**
 * 平衡二叉树：对于每一个节点而言，其左子树和右子树的高度差不大于1，则这棵树是平衡二叉树
 */

package Tree_5;

import Tree_5.PreInPosTraversal.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class IsCBT {
    /**
     * CBT 完全二叉树
     * 思路：从左到右宽度遍历，左右节点都要满足
     * 	    1. 有右孩子必有左孩子，否则直接返回false
     *      2. 在满足1的条件下，第一个有左孩子但是没有右孩子的节点之后，所有节点都必须是叶子节点，否则返回false
     * @param root 根节点
     * @return 是否是完全二叉树
     */
    public static boolean isCBT(TreeNode root) {
        if(root == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leaf = false;  // 是否遇到左孩子存在但右孩子不存在的情况
        while(!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            // 条件1，有右孩子必有左孩子,否则返回false
            if (curNode.right != null && curNode.left == null) {
                    return false;
            }
            // 如果满足条件1，则判断是否存在左右孩子是否存在，然后依次进入队列
            if(curNode.left != null) {
                queue.add(curNode.left);
            }

            if(curNode.right != null) {
                queue.add(curNode.right);
            }

            if(leaf) {
                if(curNode.left != null || curNode.right != null) {
                    return false;
                }
            } else if (curNode.left != null && curNode.right == null) {
                leaf = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        // 2 4 1 5 3
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
//        node3.left = node6;

        System.out.print(isCBT(node1));
    }
}
