/**
 * 总结：
 * 深度优先非递归遍历方式中，先序和中序遍历都只需要一个栈即可，后序遍历需要两个栈。
 * 宽度优先遍历需要使用队列。
 */
package Tree_5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PreInPosTraversal {
    // 树结构
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int v) {
            this.val = v;
        }
    }


    /**
     * 二叉树遍历递归方式
     * 递归方式遍历二叉树，每个节点都会访问三次。
     * @param root 根节点
     * 先序中序后序三种遍历方式只是在不同的地方打印节点值而已
     * 如果都打印，那就是遍历序，每个值都会打印三次
     */
    public static void traversals(TreeNode root) {
        if(root == null) {
            return;
        }
        // 第一次来到该节点，在这里打印值的话就是先序
        System.out.println(root.val);

        traversals(root.left);

        // 第二次来到该节点，在这里打印就是中序
        System.out.println(root.val);

        traversals(root.right);

        // 第三次来到该节点，在这里打印就是后序
        System.out.println(root.val);
    }


    /**
     * 先序遍历非递归方式

     * 思路：递归就是系统准备了栈，所以非递归就是自己用栈实现
     *    1. 准备一个栈，压入根节点
     *    2. 栈不为空，弹出一个节点cur，
     *    3. 打印cur.val
     *    4. 如果cur存在左右节点，先压右节点，再压左节点
     *    5. 返回第二步继续，直到栈为空
     * @param root 根节点
     */
    public static void PreTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        // 也可以用add，功能相同，返回值类型不同的区别
        stack.push(root);   // push返回值是root
        // stack.add(root)   // 返回值是boolean，表示是否添加成功

        while(!stack.empty()) {
            root = stack.pop();
            System.out.println(root.val);
            if(root.right != null) {
                stack.push(root.right);
            }
            if(root.left != null) {
                stack.push(root.left);
            }
        }
    }

    /**
     * 后序遍历非递归

     * 思路：
     *  1. root入栈s1
     *  2. s1弹出cur，如果cur存在左右节点，左节点入栈s1，右节点入栈s1.
     *  3. cur入栈s2
     *  4. 重复2，直到s1为空
     *  5. s2的节点依次弹出，打印值

     *  这样s1入栈的顺序必须是先左后右，那么s1弹出的顺序也即s2的入栈顺序是根，右，左，
     *  那么s2的出栈顺序就是左右根。
     * @param root 根节点
     */
    public static void PosOrderUnRecur(TreeNode root) {
        if(root == null) {
            return;
        }

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);

        while(!s1.empty()) {
            root = s1.pop();
            if(root.left != null) {
                s1.push(root.left);
            }
            if(root.right != null) {
                s1.push(root.right);
            }
            s2.push(root);
        }

        while(!s2.empty()) {
            System.out.print(s2.pop().val + " ");
        }
    }


    /**
     * 中序遍历非递归
     * 整体思路就是：
     * 1. 从根节点向左一直遍历，如果不为空，就入栈，继续向左；如果为空，弹出栈顶节点，说明该节点是叶子节点
     * 2. 打印叶子节点值，遍历右节点，重复1
     * 3。 直到stack为空
     * @param root  根节点
     */
    public static void inOrderUnRecur(TreeNode root) {
        // 逻辑是对的，但是没有视频的简洁
//        Stack<TreeNode> stack = new Stack<>();
//        TreeNode cur = null;
//
//        while(root != null) {
//            stack.push(root);
//            root = root.left;
//        }
//
//        while(!stack.empty()) {
//            cur = stack.pop();
//            System.out.print(cur.val + "");
//            cur = cur.right;
//            while(cur != null) {
//                stack.push(cur);
//                cur = cur.left;
//            }
//        }

        if(root != null) {
            Stack<TreeNode> stack = new Stack<>();
            while(!stack.empty() || root != null) {
                if(root != null) {
                    stack.push(root);  // 如果root不等于null，则一直向左遍历
                    root = root.left;
                } else {
                    root = stack.pop();   // 如果root等于null，则弹出栈顶节点，说明该节点是叶子节点
                    System.out.print(root.val + " ");
                    root = root.right;   // 向右子树遍历
                }
            }
        }

    }


    /**
     * 宽度优先遍历，使用队列，先进左后进右
     * @param root
     */
    public static void widthOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.print(cur.val + " ");
            if(cur.left != null) {
                queue.add(cur.left);
            }
            if(cur.right != null) {
                queue.add(cur.right);
            }
        }
    }
}
