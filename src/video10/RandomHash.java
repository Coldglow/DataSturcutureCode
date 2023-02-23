/**
 * 设计一个数据结构，使得添加一个字符串，删除一个字符串和等概率随机返回一个字符串都是O（1）时间复杂度
 */
package video10;

import java.util.HashMap;

/**
 * 做法是维护一段没有间隔的index区域，删除一个字符串的时候就用最后一个字符串来代替
 * 等概率随机返回的时候就用random函数返回一个从0到size-1的下标，通过indexVal获取字符串返回
 */
public class RandomHash {
    // 这里的K表示数据类型的意思，K可以是String，也可以是Integer，也可以是自定义对象
    // 在创建对象的时候就要声明类型
    public static class Pool<K> {
        private HashMap<K, Integer> valIndexMap;  // key是字符串本身  value是下标
        private HashMap<Integer, K> indexValMap;  // key是下标  value是字符串
        private int size;   // 字符串的个数  size-1就是最大的下标

        Pool() {
            this.valIndexMap = new HashMap<>();
            this.indexValMap = new HashMap<>();
            this.size = 0;
        }

        /**
         * 插入一个字符串
         * @param key 要插入的字符串
         */
        public void insert(K key) {
            if (key == null) {
                return;
            }

            if(!valIndexMap.containsKey(key)) {
                this.valIndexMap.put(key, this.size);
                this.indexValMap.put(this.size++, key);
            }
        }

        public void delete(K key) {
            if(key == null || !this.valIndexMap.containsKey(key)) {
                return;
            }

            // 用表中最后一个元素来覆盖要删除的元素，删除最后一个元素确保没有重复
            // 目的是为了维持这段区域没有间隔
            K lastKey = this.indexValMap.get(this.size);
            int strIndex = this.valIndexMap.get(key);
            this.valIndexMap.put(lastKey, strIndex);
            this.indexValMap.remove(this.size);
            this.indexValMap.put(strIndex, lastKey);
            this.valIndexMap.remove(key);
            this.size--;
        }

        public K getRandom() {
            if(this.size == 0) {
                return null;
            }
            // 在0到size--的区间内随机返回一个整数
            int randomIndex = (int)(Math.random() * this.size);
            return indexValMap.get(randomIndex);
        }
    }

    public static void main(String[] args) {
        Pool<String> pool = new Pool<>();
        pool.insert("abc");
        pool.insert("jjk");
        pool.insert("jkjkjk");
        System.out.println(pool.getRandom());
    }

}
