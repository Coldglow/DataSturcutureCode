/**
 * 代表图的边的数据结构
 */
package Graph;

public class Edge {
    public int weight;   // 权重
    public Node from;    // 开头
    public Node to;      // 结束

    Edge(int w, Node from, Node to) {
        this.weight = w;
        this.from = from;
        this.to = to;
    }
}
