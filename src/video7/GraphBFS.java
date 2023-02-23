/**
 * 无向图的宽度优先遍历
 */
package video7;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {
    /**
     * 无向图的宽度优先遍历要使用队列外加一个辅助集合，确保访问过的节点不再重复访问
     * 有向图在其基础上改进
     * @param node 图当中的一个节点
     */
    public static void bfsUndirected(Node node) {
        if(node == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        // 保存所有访问过的节点
        HashSet<Node> set = new HashSet<>();
        // 节点进入队列和集合
        queue.add(node);
        set.add(node);
        // 循环访问
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 如果是宽度优先操作的话，只需要把这句打印语句替换成相应的操作即可
            System.out.print(cur.val + " ");
            // 访问cur节点的所有连接节点
            for(Node next : cur.nextNodes) {
                // 如果下一个节点还没有被访问
                if(!set.contains(cur)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
