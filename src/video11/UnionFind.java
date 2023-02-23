/**
 * 并查集结构的实现
 */
package video11;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind {
    // V是用户给的数据类型,进来会包裹一层叫做element，以下叫节点
    public static class Element<V> {
        public V value;

        Element(V value) {
            this.value = value;
        }
    }

    public static class UnionFindSet<V> {
        // 值value指向自己的节点的哈希表  key是用户给的数据  value是这个数据对应的节点
        private HashMap<V, Element<V>> elementMap;
        // key 是子节点  value是父节点
        private HashMap<Element<V>, Element<V>> fatherMap;
        // key是代表节点  value是这个代表节点所代表的集合中有多少个节点
        // 注意仅代表节点才进入次map中
        private HashMap<Element<V>, Integer> sizeMap;
        // 并查集在使用的时候需要用户给定所有的数据用来初始化
        UnionFindSet(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Element<V> element = new Element<>(value);
                elementMap.put(value, element);  // 数据指向对应的节点
                fatherMap.put(element, element);  // 节点自己指向自己
                sizeMap.put(element, 1);   // 初始时每个节点都是一个独立的集合，所以每个节点都是代表节点
            }
        }

        /**
         * 给定一个节点，找到他的代表节点并返回
         * 在此过程中将所有在这条链上的节点最后都指向代表节点，加速之后的查找
         * @param element 要查找的节点
         * @return 返回找到的代表节点
         */
        private Element<V> findHead(Element<V> element) {
            if (element == null) {
                return null;
            }
            // 存储沿途依次链接的节点
            Stack<Element<V>> path = new Stack<>();
            // 代表节点一定指向自己,如果没有指向自己，则继续往下找
            while (element != fatherMap.get(element)) {
                path.push(element);
                element = fatherMap.get(element);
            }
            // 循环结束后element就是要找的代表节点
            // 沿途节点都指向代表节点
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), element);
            }

            return element;
        }

        /**
         * 查看用户给的两个数据是否在同一个集合中
         * @param v1 v1
         * @param v2 v1
         * @return true or false
         */
        public boolean isSameSet(V v1, V v2) {
            // 先判断这两数据是不是存储过，如果没有，直接false
            if (elementMap.get(v1) != null && elementMap.get(v2) != null) {
                // 找到两个代表节点查看是否相同
                return findHead(elementMap.get(v1)) == findHead(elementMap.get(v2));
            }
            return false;
        }

        /**
         * 合并两个元素所在的集合
         * @param v1 v1
         * @param v2 v2
         */
        public void union(V v1, V v2) {
            if (elementMap.containsKey(v1) && elementMap.containsKey(v2)) {
                // 先找到各自的代表节点，如果代表节点相同，说明二者已经在同一个集合中
                Element<V> head1 = findHead(elementMap.get(v1));
                Element<V> head2 = findHead(elementMap.get(v2));
                if (head1 != head2) {
                    // 否则将节点数量少的代表几点指向节点数量多的代表节点，同时维护其他信息正确
                    // 先确定数量多和数量少的节点
                    Element<V> big = sizeMap.get(head1) > sizeMap.get(head2) ? head1 : head2;
                    Element<V> small = big == head1 ? head2 : head1;
                    // 数量少的节点指向数量多的节点
                    fatherMap.put(small, big);
                    // 更新big的数量
                    sizeMap.put(big, sizeMap.get(small) + sizeMap.get(big));
                    // 因为small指向了big，不再是代表节点，所以从sizeMap中删除
                    sizeMap.remove(small);
                }
            }
        }
    }
}
