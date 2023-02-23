package Tree_5;

import Tree_5.PreInPosTraversal.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class widthOfTree {
    /**
     * 计算二叉树的宽度，使用哈希表
     * 思路：通过一个哈希表记录每个节点所在第几层，用队列进行宽度优先遍历。
     * @param root 根节点
     */
    public static int widthTreeWithHash(TreeNode root) {
        if(root == null) {
            return 0;
        }
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int curLevel = 1;  // 当前层数，初始为1，即根节点算作一层
        int curNodes = 0;  // curLevel层数所有有的节点数
        int max = -1;
        queue.add(root);
        levelMap.put(root, 1);

        while(!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if(curLevel == levelMap.get(curNode)) {
                curNodes++;
            } else {
                max = Math.max(max,curNodes);
                curLevel++;
                // 这里是1不是0，因为如果走到else语句，说明此时已经到下一层的第一个节点了
                // 说明下一层起码有一个节点，因此初始化1
                curNodes = 1;
            }

            if(curNode.left != null) {
                // 这里不能用++，只能是curLevel+1，不能改变curLevel的值，否则上面else语句永远不会执行
                levelMap.put(curNode.left, curLevel+1);
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                levelMap.put(curNode.right, curLevel+1);
                queue.add(curNode.right);
            }
        }

        return max;
    }


    /**
     * 计算树的宽度，不适用哈希表，但是使用队列
     * 关键点：
     * 1. 永远保持nextLevelEnd为最新加入队列的节点值。
     * 2. 当CurNode == CurLevelEnd时，表示到当前层的最后一个节点，此时就要比较全局最大值和当前层节点数，取较大的那个，并且更新curLevelEnd。
     * 3. cueLevelNodes初始化是1不是0，因为只要进入下一个循环，就说明队列不是空，下一层起码还有一个节点存在，所示是1
     * @param root 根节点
     * @return 树的宽度
     */
    public static int widthWithoutHash(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curLevelEnd = root;   // 当前层的最后一个节点
        TreeNode nextLevelEnd = null;  // 下一层的最后一个节点
        int curLevelNodes = 1;         // 当前层的节点数
        int max = -1;                  // 宽度
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode curNode = queue.poll();

            if (curNode.left != null) {
                queue.add(curNode.left);
                nextLevelEnd = curNode.left;
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
                nextLevelEnd = curNode.right;
            }

            if(curNode == curLevelEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 1;
                curLevelEnd = nextLevelEnd;
            } else {
                curLevelNodes++;
            }
        }

        return max;
    }
}
