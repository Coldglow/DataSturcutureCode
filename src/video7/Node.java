/**
 * 代表图的点的数据结构
 */
package video7;

import java.util.ArrayList;

public class Node {
    public int val;  // 该点所代表的值
    public int in;   // 该点的入度
    public int out;  // 出度
    public ArrayList<Node> nextNodes;  // 从自己出发，指向的点
    public ArrayList<Edge> nextEdges;  // 从自己出发的边

    Node(int v) {
        this.val = v;
        this.in = 0;
        this.out = 0;
        this.nextNodes = new ArrayList<>();
        this.nextEdges = new ArrayList<>();
    }
}
