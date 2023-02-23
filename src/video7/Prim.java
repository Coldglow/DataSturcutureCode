/**
 * 生成最小生成树的p算法
 */
package video7;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim {
    public static class EdgeComparator implements Comparator<Edge> {
        public int compare(Edge e1, Edge e2) {
            return e1.weight - e2.weight;
        }
    }

    /**
     *
     * @param graph 图
     * @return 包含最小生成树的边集合
     */
    public static Set<Edge> prim(Graph graph) {
        if (graph == null) {
            return null;
        }

        HashSet<Node> set = new HashSet<>();  // 记录访问过的点
        // 将所有的边按照从小到大的顺序排列，小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        // 结果集
        Set<Edge> res = new HashSet<>();
        // 防止出现森林的情况，即整个图本身就是不连通的，这样最小生成树也会出现多个
        for (Node node : graph.nodes.values()) {
            // 如果该节点没有访问过，则记录
            if(!set.contains(node)) {
                set.add(node);
            }
            // 将该节点的边加入到堆中，选取权重最小的边
            priorityQueue.addAll(node.nextEdges);   //O(LogN)
            while(!priorityQueue.isEmpty()) {
                // 弹出权重最小的节点
                Edge edge = priorityQueue.poll();
                Node newNode = edge.to;
                if (!set.contains(newNode)) {
                    // 如果没有访问过这条边的to节点，就加入当中
                    set.add(newNode);
                    // 结果集中加入当前边
                    res.add(edge);
                    // 将to节点的边加入到小根堆中
                    priorityQueue.addAll(newNode.nextEdges);
                }
            }
        }

        return res;
    }
}
