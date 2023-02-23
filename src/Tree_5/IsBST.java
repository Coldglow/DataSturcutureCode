package Tree_5;

import Tree_5.PreInPosTraversal.TreeNode;

import java.util.Stack;

public class IsBST {
    /**
     * 判断一棵树是不是搜索二叉树(Binary Search Tree)  非递归
     * 思路：中序遍历该二叉树，如果遍历结果是升序的，是，否则不是
     * @param root 根节点
     * @return true or false
     */
    public static boolean isBSTUnRec(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            int preVal = Integer.MIN_VALUE;
            while (!stack.empty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
//                    System.out.print(root.val + " ");
                    if(root.val >= preVal) {
                        preVal = root.val;
                    } else {
                        return false;
                    }
                    root = root.right;
                }
            }
        }
        return true;
    }

    public static int preVAl = Integer.MIN_VALUE;
    /**
     * 中序遍历递归方式改写成判断是否是搜索二叉树，这这时需要一个全局变量来记录上一次搜索到的数据
     * @param root  根节点
     * @return true or false
     */
    public static boolean isBSTRec(TreeNode root) {
        // 空树返回true
        if (root == null) {
            return true;
        }
        // 检查左树是不是搜索二叉树，如果不是就是直接返回false,如果是更新preVal的值
        boolean isBSTLeft = isBSTRec(root.left);
        if(!isBSTLeft) {
            return false;
        }
        // 再检查当前节点的值是比上一次保存值大,更新preVal,如果不是，则直接返回
        if (preVAl <= root.val) {
            preVAl = root.val;
        } else {
            return false;
        }
        return isBSTRec(root.right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(9);
        // 2 4 1 5 3
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.left = node5;

        System.out.print(isBSTUnRec(node1));
    }
}
