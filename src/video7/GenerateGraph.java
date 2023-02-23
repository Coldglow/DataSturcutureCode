/**
 * 接口函数，用于将题目中给的数据结构转换成已经建立好的数据结构
 * 这里加假设所给的数据结构是一个三阶数组：
 * N[][0]=1表示从点1出发
 * N[][1]=3表示到达点3
 * N[][2]=6表示从1到3这条边的权重是6
 */
package video7;

public class GenerateGraph {
    public static Graph generate(Integer[][] matrix) {
        Graph graph = new Graph();
        for(int i = 0; i < matrix.length; i++) {
            Integer from = matrix[i][0];   // 出发点
            Integer to = matrix[i][1];     // 到达点
            Integer weight = matrix[i][2]; // 权重

            // 如果出发点不存在,则创建一个新的点保存在图当中
            if(!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            // 如果到达点不存在，同上
            if(!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            // 获取图当中的出发点和到达点，生成一条新的边
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);

            // 下面就是更新出发点和到达点的所有信息
            // 图所保存的edges添加新边
            graph.edges.add(newEdge);
            // 出发点出度加1，到达点入度加1
            fromNode.out++;
            toNode.out++;
            // 出发点的nextNodes更新
            fromNode.nextNodes.add(toNode);
            // 出发点的nextEdges更新
            fromNode.nextEdges.add(newEdge);
        }

        return graph;
    }
}
