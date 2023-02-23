/**
 * 创建一个无向图的最小生成树,k算法,
 * 包括如何判断加入某条边之后是否形成环：使用并查集，如果from节点和to节点都属于同一个集合，则会形成环
 * 边按照权重依次弹出：小根堆，需要自己实现比较器
 */

/**
package video7;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Kruskal {

    public static class EdgeComparator implements Comparator<Edge> {
        public int compare(Edge e1, Edge e2) {
            return e1.weight - e2.weight;
        }
    }

    // 返回值是最小生成树所包含的边
    public static Set<Edge> kruskal(Graph graph) {
        // 并查集的讲解后序再看，这里先参考写出代码
        UnionFind unionFind = new UnionFind();
        // 为每个单独的点创建一个单独的集合
        unionFind.makeSets(graph.nodes.values());
        // 创建一个小根堆用来存储按照权重从小到大排列的边
        // 因为是自定义对象，所以要自己实现比较器
        // 从小到大排列是因为按照最小生成树的定义，要先考虑权重小的边
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        priorityQueue.addAll(graph.edges);
        // 保存最小生成树节点的集合
        Set<Edge> resSet = new HashSet<>();
        while(!priorityQueue.isEmpty()) {  // M条边
            Edge edge = priorityQueue.poll();   // LogM的时间复杂度
            // 如果edge的from节点和to节点已经在一个集合，那么这条边就不用加，否则就会出现环
            // 如果不在，则需要加入，
            if(!unionFind.isSameSet(edge.from, edge.to)) {
                resSet.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }

        return resSet;
    }
}
*/