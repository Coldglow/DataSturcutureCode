/**
 * 二叉树Morris遍历，时间复杂度O(N)  空间复杂度O(1)
 */
package Tree_5;

import Tree_5.PreInPosTraversal.TreeNode;

public class Morris {

    /**
     * Morris遍历会访问一次叶子节点，两次非叶子节点
     * 所以可以从Morris遍历改写成先序，中序，后序遍历
     * @param root 根节点
     */
    public static void morris(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode mostRight = null;
        TreeNode cur = root;
        while (cur != null) {
            // 这里一定要写，别忘了
            mostRight = cur.left;
            // 如果cur有左孩子，找到左孩子的最右节点
            if (mostRight != null) {
                // cur.right != cur 确保mostRight不会第二次访问cur节点
                // 注意这里内循环用mostRight而不是cur！！！！！！！！！
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 如果mostRight的右指针为空，让mostRight的右指针指向cur，cur向左移动
                if (mostRight.right == null){   // 第一次来到cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;  // 直接进行下一次大循环
                } else {
                    // 如果mostRight右孩子不为空   第二次来到cur
                    // mostRight右孩子置空，cur向右移动
                    mostRight.right = null;
                }
            }
            // 如果cur没有左孩子，cur向右移动
            cur = cur.right;
        }
    }

    /**
     * 先序遍历，Morris实现
     * 规则就是：
     * 对于只访问一次的节点，访问的时候就打印；即如果节点没有左孩子，直接打印
     * 对于访问两次的节点，在第一次访问的时候打印；即如果mostRight为空，直接打印
     * 需要记住Morris遍历很好改写
     * @param root 根节点
     */
    public static void morrisPre(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode mostRight = null;
        TreeNode cur = root;
        while (cur != null) {
            mostRight = cur.left;
            // 如果存在左子树，找到左子树的最右节点
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 如果mostRight的右节点为空，说明第一次来到cur，打印cur，mostRight指向cur，cur左移，continue
                if (mostRight.right == null) {  // 第一次来到cur
                    System.out.print(cur.val + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {   // 第二次来到cur
                    // 如果mostRight.right不为空，说明cur是第二次来到，不再打印，mostRight.right置空，cur右移
                    mostRight.right = null;
                }
            } else {
                // 必须写在else里面，否则就会多次打印
                System.out.print(cur.val + " ");
            }
            // 没有左孩子，直接右移
            cur = cur.right;
        }
    }

    /**
     * 二叉树中序遍历，Morris
     * 规则：
     * 只访问一次的节点，直接打印
     * 访问两次的节点，第二次打印
     * @param root 根节点
     */
    public static void MorrisIn(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode mostRight = null;
        TreeNode cur = root;
        // 最外层循环，如果cur == null 遍历结束
        while (cur != null) {
            mostRight = cur.left;
            if (cur.left != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {  // 第一次来到cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 第二次来到cur
                    mostRight.right = null;
                }
            }
            // 这个位置既是访问两次的节点第二次访问的位置，也是之访问一次的节点会访问的位置
            // 所以在这里直接打印即可
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
    }

    /**
     * Morris后序遍历
     * 之访问一次的节点不做操作
     * 对于访问两次的节点，在第二次访问的时候，逆序打印cur节点左树的右边界
     * 遍历结束后，单独打印整棵树的右边界
     * @param root 根节点
     */
    public static void MorrisPos(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 第一次来到cur，什么也不做
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 第二次来到cur，逆序打印cur左树的右边界
                    mostRight.right = null;
                    printEdges(cur.left);
                }
            }
            cur = cur.right;
        }
        // 单独打印整棵树的右边界
        printEdges(root);
        System.out.println();
    }

    /**
     * 先逆序，再改回来，其实就是把right指针当作链表的next指针，相当于链表逆序再恢复
     * @param head 打印head节点的右边界
     */
    public static void printEdges(TreeNode head) {
        TreeNode node = reverse(head);
        TreeNode temp = node;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.right;
        }
        reverse(temp);
    }

    public static TreeNode reverse(TreeNode node) {
        TreeNode pre = null;
        TreeNode next = null;
        while (node != null) {
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    /**
     * morris遍历判断是不是搜索二叉树
     * 只需要在中序遍历的处改成比较操作就行
     * @param root  根节点
     * @return 返回是不是
     */
    public static boolean isBSTMorris(TreeNode root) {
        if (root == null) {
            return false;
        }

        TreeNode mostRight = null;
        TreeNode cur = root;
        int preVal = Integer.MIN_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 第一次来到该节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            // 在中序遍历的打印出与上一个值比较,如果小于等于，直接返回false
            if (cur.val <= preVal) {
                return false;
            }
            preVal = cur.val;
            cur = cur.right;
        }
        return true;
    }
}
