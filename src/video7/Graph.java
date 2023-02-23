/**
 * 图的数据结构
 */
package video7;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    Graph() {
        // 如果题目当中说了节点数量已知或者其他可能可以使用数组的情况
        // 可以用数组来保存节点
        nodes = new HashMap<Integer, Node>();
        edges = new HashSet<Edge>();
    }
}
