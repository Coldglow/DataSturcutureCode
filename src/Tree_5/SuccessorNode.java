/**
 * 给定一个带有父节点指针的二叉树，寻找某个节点的后继节点
 */
package Tree_5;

public class SuccessorNode {
    public static class TreeWithPar {
        int val;
        public TreeWithPar left;
        public TreeWithPar right;
        public TreeWithPar parent;

        TreeWithPar(int v) {
            this.val = v;
        }
    }

    /**
     * 寻找Node节点的后继节点。根节点的父节点为null
     * 可能性：
     * 1. 如果Node节点存在右子树的时候，返回右子树最左侧的节点即为Node的后继节点
     * 2. 如果Node节点不存在右子树，检查Node是不是父节点的左节点，如果是，返回父节点，如果不是，继续检查，直到根节点结束。
     * 特殊情况：
     * 整棵树的最右节点不存在后继节点，需要返回null
     * @param node 要寻找到后继节点的节点
     * @return 返回找到的后继节点
     */
    public static TreeWithPar getSuccessorNode(TreeWithPar node) {
        if (node == null) {
            return null;
        }
        // 情况1，node节点存在右子树，返回右子树最左节点
        if(node.right != null) {
            node = node.right;
            while(node.left != null) {
                node = node.left;
            }
            return node;
        } else {   // 情况2
            TreeWithPar par = node.parent;
            // 如果par等于null，说明初始node是整棵树最右侧的节点，返回根节点的par，即null
            while (par != null && par.left != node) {
                node = par;
                par = node.parent;
            }
            return par;
        }
    }
}
