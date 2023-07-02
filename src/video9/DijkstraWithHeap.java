/**
 * 使用堆改进后的dijkstra算法，难点在于自己改写堆
 */
package video9;

import Graph.Node;
import Graph.Edge;
import java.util.HashMap;

public class DijkstraWithHeap {


    /**
     * 一个自定义的数据结构，表示从出发点head到当前点node的最新距离distance
     */
    public static class NewRecord {
        private Node node;
        private int distance;

        NewRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Node[] nodes;  // 堆的底层实现是数组
        // key是node，value是node在数组中的下标，或者说位置
        // 用来查询node在不在堆上，如果在，位于什么位置
        private HashMap<Node, Integer> heapIndexMap;
        // key是node，value是从head出发到node目前的距离
        private HashMap<Node, Integer> distanceMap = new HashMap<>();
        private int size;  // 这个size表示：目前堆上元素的数量

        NodeHeap(int size) {
            nodes = new Node[size];  // 这个size表示堆上元素数量的上限，最多可以有多少个元素，申请了size大小的数组
            this.size = 0;  // 从0位置开始添加节点，添加一个之后，size++
            distanceMap = new HashMap<>();
            heapIndexMap = new HashMap<>();
        }

        // 判断堆是否为空
        public boolean isEmpty() {
            return this.size == 0;
        }

        /***
         * 如果node没有在堆中出现过，加入到堆中
         * 如果出现过，并且新路径的距离distance小于之前路径的距离distanceMap.get(node)，则更新距离
         * 如果出现过，但是新距离大于等于之前的距离，则忽略
         * @param node 要更新距离的节点
         * @param distance 新路径的距离
         */
        public void addOrUpdateOrIgnore(Node node, int distance) {
            // 如果在堆中，更新distanceMap中的距离和节点在堆中的位置，维持堆结构
            // 因为Java内置的堆无法完成修改堆中的某一个值同时维持堆结构，所以才需要自己维护一个堆
            if(inHeap(node)) {
                // 更新当前节点的距离
                distanceMap.put(node, distance);
                // 加入到堆中，维持堆结构
                insertHeapify(node, heapIndexMap.get(node));
            }
            // 如果没有在堆中出现过，加入到堆中，并且维持堆结构
            if(!isEntered(node)) {
                // size是node节点的下标
                nodes[size] = node;
                distanceMap.put(node, distance);
                heapIndexMap.put(node, size);
                // 维护堆结构
                insertHeapify(node, size++);  // 这里size++
            }

            // 另外就是Ignore的情况，进来过但是不在堆中，表示已经锁定的节点，不做更改
        }

        /**
         *
         * @return 弹出堆顶元素，当前节点所连接的距离最小的边
         */
        public NewRecord pop() {
            // 返回head到堆顶元素的最新距离
            NewRecord record = new NewRecord(nodes[0], distanceMap.get(nodes[0]));
            // 堆顶元素弹出，维护堆结构
            swap(0, size -1);  // 堆顶元素和最后一个元素交换
            // 更新heapIndexMap中为-1，表示已经从堆中弹出
            heapIndexMap.put(nodes[size - 1], -1);
            // 从distanceMap中删除
            distanceMap.remove(nodes[size - 1]);
            // 释放数组最后一个元素
            nodes[size - 1] = null;
            // 维护堆结构
            heapify(0, --size);

            return record;
        }

        /**
         * 判断node进来过还是没进来过堆
         * 如果进来过，但是现在不在堆中了，就是说heapIndexMap中有node这个key，但是它的value值变成了-1
         * 因为堆是从数组存储的，下标不可能是负数，heapIndexMap的value表示node在堆中的下标，
         * 所以如果value = -1，表示node不在堆中，但是进入过堆中，目前已经被弹出
         * @param node node
         * @return true or false
         */
        public boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        /**
         * 判断node是不是在堆中
         * 先判断node有没有进来过堆中，如果进来过，判断现在在不在堆中
         * @param node node
         * @return true or false
         */
        public boolean inHeap(Node node) {
            return isEntered(node) && (heapIndexMap.get(node) != -1);
        }

        /**
         * 交换两个node在堆中的位置，即在数组中交换位置，但是因为heapIndexMap记录node在堆中的下标
         * 所以heapIndexMap中的值也要相应的改变
         * @param index1 i1
         * @param index2 i2
         */
        public void swap(int index1, int index2) {
            // 先更新heapIndexMap的记录
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            // 交换两个节点在数组（堆）中的位置
            Node temp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = temp;
        }

        /**
         * 维护堆结构，如果发生修改，则节点对应的距离
         * 而当发生改变时，距离只可能是变小了，因为是小根堆，因此只需要考虑是否应该向上冒
         * @param node 需要修改位置的节点
         * @param index node节点在数组中的下标
         */
        public void insertHeapify(Node node, int index) {
            // 在这里判断距离是否变小，之前都没有判断
            // 如果更新后的距离小于父节点的距离，交换二者位置，直到
            while(distanceMap.get(node) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        /**
         * 弹出一个元素之前将堆顶元素和最后一个元素交换位置，
         * 因此堆顶元素位置错误，需要修正堆结构
         * 参考video3的heapSort中的heapify方法，不同的是这里是小根堆
         * @param index 位置错误节点的下标
         * @param size  当前堆的大小
         */
        public void heapify(int index, int size) {
            int left = 2 * index + 1;
            while(left < size) {
                // 取出孩子中较小值的下标,根据距离比较
                int smaller = left + 1 < size &&
                        distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ?
                        left + 1 : left;
                // 比较父节点和子节点的距离，谁的值小，把他复制给smaller
                smaller = distanceMap.get(nodes[smaller]) < distanceMap.get(nodes[index])?
                        smaller : index;
                // 现在smaller表示父节点和两个孩子节点中三者最小值的下标
                // 所以如果smaller == index，表示父节点就是三者中的最小值
                // 说明已经是堆结构，直接退出
                if(index == smaller) {
                    break;
                }
                // 如果不是，交换父节点和最小值节点的位置
                swap(index, smaller);
                // 更新父节点位置，继续向下
                index = smaller;
                left = 2 * index + 1;
            }
        }
    }

    /**
     * 主调用函数
     * @param head 起始节点
     * @param size 堆的大小，堆是用数组存储的，初始化的时候需要设置大小
     * @return 返回从head出发，所有head能到达的节点，生成到达每个节点的最小记录并返回
     */
    public static HashMap<Node, Integer> dijkstraUsingHeap(Node head, int size) {
        // 自定义的堆结构，堆的元素个数可以从0到size-1
        NodeHeap nodeHeap = new NodeHeap(size);
        // 刚开始head进入堆,head到head的距离为0
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        // 结果
        HashMap<Node, Integer> res = new HashMap<>();

        while(!nodeHeap.isEmpty()) {
            // head节点到当前节点的最新路径
            NewRecord record = nodeHeap.pop();
            // cur表示从head节点到当前cur节点，distance表示这条路的距离
            Node cur = record.node;
            int distance = record.distance;
            // 在堆中更新从这个节点出发的可到达的节点的距离
            // 可到到的节点就是这个节点所连接的边的to节点
            // 距离就是从head节点出发到当前cur节点的距离加上cur节点到to节点的距离
            for (Edge nextEdge : cur.nextEdges) {
                nodeHeap.addOrUpdateOrIgnore(nextEdge.to, distance + nextEdge.weight);
            }
            // 一旦从堆中弹出某个节点，就表示找到了一条从head出发到当前cur节点的最短路径，于是记录结果
            res.put(cur, distance);

            // for循环和添加结果的顺序可以更换
        }

        // 循环结束，说明所有节点都找到了最小距离，返回结果
        return res;
    }
}
