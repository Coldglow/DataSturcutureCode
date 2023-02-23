/**
 * 有向无环图的拓扑排序，前提是存在入度为0的点
 */
package video7;

import java.util.*;

public class Topology {
    /**
     * 拓扑排序的前提：有向无环图，存在入度为0的点
     * 整体思路：先找到入读为0的点，加入队列中。
     *         列头弹出，将其所指向的点的入读都减去1，遍历其所指向的点，遍历过程中将所指向的点的入度减去1，
     *         如果减去1后入度为0，加入队列；再弹出列头，循环直到列表为空。
     * 所以说需要先使用一个哈希表记录所有节点的初始入度值。
     * @param graph 图
     */
    public static List<Node> sortedTopology(Graph graph) {
        if(graph == null) {
            return null;
        }
        // 记录所有节点入度的哈希表
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 保存入度为0的队列
        Queue<Node> queue = new LinkedList<>();
        // 先循环，找到起始入度为0的点，并且记录所有节点的入度
        // .values获取哈希表的所有key值
        for(Node node : graph.nodes.values()) {
            // 如果入度为0，直接进入队列
            if(node.in == 0) {
                queue.add(node);
            }
            inMap.put(node, node.in);  // 这句话放在else和不放在else的效果一样
        }

        // 拓扑序列的结果，依次加入result
        List<Node> result = new ArrayList<>();
        // 从队列中弹出一个节点，然后对其所有邻接节点，入度都减去1
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 记录拓扑序列
            result.add(cur);
            for(Node next : cur.nextNodes) {
                inMap.put(next, inMap.get(next) - 1);
                if(inMap.get(next) == 0) {
                    queue.add(next);
                }
            }
        }

        return result;
    }
}
