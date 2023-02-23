package Tree_5;

public class TrieTree {
    /**
     * 前缀树节点的数据结构
     */
    public static class TrieNode {
        public int pass;  // 表示这个节点经过了多少次
        public int end;   // 以这个节点结尾的字符串个数

        // 每个节点指向的后面的节点，用数组表示,
        // 当所表示的字符类型很多，使用数组太浪费的时候，也可以用HashMap<char, TrieNode>表示，或者TreeMap
        public TrieNode[] nexts;

        TrieNode() {
            pass = 0;
            end = 0;
            // 默认字符串中只包含小写字母
            // nexts[0] == null 表示没有走向'a'的路
            // nexts[0] != null 表示有走向'a'的路
            // ...
            // nexts[25] != null 表示有走向'z'的路
            nexts = new TrieNode[26];
        }
    }

    public static class Trie {
        private TrieNode root;

        // 创建一个根节点
        Trie() {
            this.root = new TrieNode();
        }

        /**
         * 向前缀树中添加一个字符串
         * @param str 要添加的字符串
         */
        public void insert(String str) {
            if(str == null) {
                return;
            }
            // 将String类型变量转化成一个一个的字符放在数组中
            char[] chars = str.toCharArray();
            // 获取头节点，不能直接用，否则加入一个后就找不到头节点在哪了
            TrieNode node = root;
            // 因为str!=null，所以头节点一定会经过一次，所以头节点的pass值加一
            node.pass++;
            // 遍历字符串，准备好一个下标值
            int index = 0;
            for (char c : chars) {
                // 字符减去'a'就是ASCII码
                index = c - 'a';
                // 如果不存在走向字符c的路径，就创建
                if(node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                // node更新到下一个节点
                node = node.nexts[index];
                // 到了下一个节点，这个节点的pass值加一
                node.pass++;
            }
            // 循环结束，node位于最后一个字符指向的节点，所以end值加一
            node.end++;
        }

        /**
         * 查找一个字符串加入过几次，其实只要找到这个字符串的最后一位字符所在的节点，返回其end值就好
         * @param str 要查找的字符串
         * @return 返回加入的次数
         */
        public int search(String str) {
            TrieNode node = root;
            // 如果str为null，则直接返回根节点的end值
            if(str == null) {
                return node.end;
            }

            char[] chars = str.toCharArray();
            int index = 0;
            for (char c : chars) {
                index = c - 'a';
                // 如果没查到下一个字符所指向的节点，说明这个字符串之前没有加入到前缀树中，直接返回0
                if(node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            // 如果循环结束，说明node已经来到了str最后一个字符所指向的节点，返回其end值
            return node.end;
        }

        /**
         * 查找加入到前缀树中，有几个字符串是以str作为前缀的
         * 只需要找到str最后一个字符所在的节点，返回其pass值即可,和上面的search逻辑一样
         * @param str 要寻找的前缀字符串
         * @return  返回有几个字符串是以str作为前缀的
         */
        public int preFixNumber(String str) {
            if (str == null) {
                return 0;
            }

            TrieNode node = root;
            char[] chars = str.toCharArray();
            int index = 0;
            for(char c : chars) {
                index = c - 'a';
                if(node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }

            return node.pass;
        }

        /**
         * 在前缀树中删除一个字符串，沿途遍历所有节点，pass值减一
         * 如果某个节点pass值减一后变成0，直接return
         * （java可以这么写，因为jvm会自动回收内存空间，如果是c++，需要自己手动遍历到底，然后逐个调用析构函数取释放空间）
         * @param str 要删除的字符串
         */
        public void delete(String str) {
            // 先查询，确保前缀树中加入过该字符串再删除
            if(search(str) != 0) {
                TrieNode node = root;
                int index = 0;
                // 记得刚开始的时候根节点的pass值也要减1
                node.pass--;
                char[] chars = str.toCharArray();
                for (char c : chars) {
                    index = c - 'a';
                    // 如果遍历过程中发现pass值减1后为0，直接返回
                    if((--node.nexts[index].pass) == 0) {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                // 遍历结束后尾字符指向的节点end值减1
                node.end--;
            }
        }
    }
}
