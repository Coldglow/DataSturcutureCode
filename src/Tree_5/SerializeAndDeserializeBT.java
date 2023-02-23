/**
 * 序列化二叉树：将一棵二叉树的值和结构变成一个唯一的字符串放到磁盘中
 *      通过遍历进行序列化，先序中序后序层次都可以。
 * 反序列化：将一个字符串还原成一棵二叉树
 *
 */
package Tree_5;

import Tree_5.PreInPosTraversal.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBT {
    /**
     * 序列化一个树，使用先序遍历，null设置为 #，分隔符为 _
     * @param root  根节点
     */
    public static String serializeBT(TreeNode root) {
        if (root == null) {
            return "#_";
        }
        String res = root.val + "_";   // 就是将原先打印的位置换成了设置值的位置
        res += serializeBT(root.left);   // 将单纯的遍历左右子树换成了 字符串拼接
        res += serializeBT(root.right);
        return res;
    }

    /**
     * 对一个字符串反序列化,使用队列保存字符串分割后的结果
     * @param preStr  先序遍历结果的字符串
     * @return 构建好的树的根节点
     */
    public static TreeNode DeserializeBT(String preStr) {
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));

        return process(queue);
    }

    /**
     * 因为是先序遍历的序列化，所以反序列化的时候也要按照先序遍历的顺序构建节点
     * @param queue  保存节点值的队列
     * @return 树的根节点
     */
    public static TreeNode process(Queue<String> queue) {
        String val = queue.poll();
        if(val.equals("#")) {
            return null;
        }
        // 如果不是空，先建头节点
        TreeNode node = new TreeNode(Integer.parseInt(val));
        // 再建左子树
        node.left = process(queue);
        // 最后建右子树
        node.right = process(queue);

        return node;
    }
}
