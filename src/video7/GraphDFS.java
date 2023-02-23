/**
 * 无向图的深度优先遍历
 */
package video7;

import java.util.HashSet;
import java.util.Stack;

public class GraphDFS {
    /**
     * 无向图深度优先遍历。使用栈和一个辅助set，set用来存储访问过的节点，最终栈会存储访问节点的顺序
     * @param node 图中的一个节点
     */
    public static void dfsUndirected(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        // 节点先近入集合，入栈，再执行操作，这里是打印操作
        set.add(node);
        stack.push(node);
        System.out.print(node.val + " ");

        while(!stack.isEmpty()) {
            Node cur = stack.pop();
            // 循环访问栈顶节点cur的所有邻接节点
            for(Node next : cur.nextNodes) {
                // 如果next是第一次访问
                if(!set.contains(next)) {
                    // 把cur重新压回栈,一定要先入栈cur再next
                    stack.push(cur);
                    // next入栈
                    stack.push(next);
                    // next进入金和表示访问过
                    set.add(next);
                    // 执行相应操作，这里是打印
                    System.out.print(next.val + " ");
                    // 直接break，不访问cur节点的下一个邻接节点，逮住一个方向使劲薅
                    break;
                }
            }
        }
    }
}
