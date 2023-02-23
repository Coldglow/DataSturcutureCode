package video7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Dijkstra {
    /**
     *  Dijkstra算法，在图中寻找从某个点出发到达所有点的最短路径
     *  要求：
     *  1. 无向图
     *  2. 可以有环，但是环的权重和不能为负数
     * @param start 初始节点
     * @return 返回从start节点到所有节点的最短路径的距离，这里只返回了距离，并没有记录具体的最短是什么
     */
    public static HashMap<Node, Integer> dijkstra(Node start) {
        if (start == null) {
            return null;
        }

        // 记录距离的哈希表，默认所有距离都是从node出发到达key节点的距离
        // key: 从start出发到达key节点
        // value: 从start出发到达key节点的距离
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        // 记录已经锁定不动的节点
        HashSet<Node> selectedNodes = new HashSet<>();
        // 初始时起点进入哈希表中，记录start到自身距离为0
        distanceMap.put(start, 0);


        // node节点锁定，因为不可能处出现距离比0还小的可能(错误)
        // 刚开始不能将start加入到set中，否则getMinDisNodeUnselected返回空
        //selectedNodes.add(start);

        // 这里第一次写忘记了
        // 从distanceMap当中找到目前距离最小的节点，但是这个节点不能被锁定，也就是说不能出现在selectedNodes中
        Node minDisNode = getMinDisNodeUnselected(distanceMap, selectedNodes);

        while (minDisNode != null) {
            // 遍历minDisNode节点的所有边，不找权重最小的边，
            // 而是更新从start开始到所有未锁定的节点的距离
            for (Edge nextEdge : minDisNode.nextEdges) {

                // 这句忘写
                // 从start出发到minDistance节点的距离
                int distance = distanceMap.get(minDisNode);

                // 先取出这条边的to节点
                Node toNode = nextEdge.to;
                // 如果toNode不包含在hashMap中，说明toNode是第一次被更新，直接更新不用比较距离
                if(!distanceMap.containsKey(toNode)) {
                    // distance + nextEdge.weight
                    // 表示从start节点到toNode节点的距离，中间经过minDisNode节点
                    // 因为从start到minDisNode的距离是distance，所以是distance + nextEdge.weight
                    distanceMap.put(toNode, distance + nextEdge.weight);
                }
                // 如果已经存在，则说明已经存在一条从start出发，经过某个或多个节点到达toNode节点的路径
                // 这条路径的距离是distanceMap.get(toNode),
                // 现在存在另外一条路径，从start出发经过当前minDisNode到达toNode节点的路径
                // 这条路径的距离是start到minDisNode的距离 加上 minDisNode到toNode的距离，
                // 即distance + nextEdge.weight
                // 二者进行比较，取较小值
                distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + nextEdge.weight));
            }
            // 更新结束后将minDisNode锁定
            selectedNodes.add(minDisNode);
            // 再次选出距离最小的节点，继续更新，直到minDisNode为空，表示所有节点都被锁定
            minDisNode = getMinDisNodeUnselected(distanceMap, selectedNodes);
        }

        return distanceMap;
    }

    /**
     * 获取distanceMap中当前距离最小的节点，前提是该节点不包含在selectedSet中
     * @param distanceMap  记录从start到key节点距离的哈希表
     * @param selectedNodes  记录一定锁定距离的节点
     * @return  返回最小距离的节点
     */
    public static Node getMinDisNodeUnselected(HashMap<Node, Integer> distanceMap,
                                               HashSet<Node> selectedNodes) {
        Node minDIsNode = null;
        int minDis = Integer.MAX_VALUE;
        // 遍历HashMap所有key
        for (Map.Entry<Node, Integer> entry: distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if(distance < minDis && !selectedNodes.contains(node)) {
                minDis = distance;
                minDIsNode = node;
            }
        }

        return minDIsNode;
    }
}
