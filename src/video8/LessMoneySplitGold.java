/**
 * Huffman编码问题，要求的是总花费最小
 * 分金条，一条金条无论分成长度多少的两份，其花费都是金条的长度
 */
package video8;

import java.util.PriorityQueue;

public class LessMoneySplitGold {
    /**
     * @param slices  要分割的长度数组
     * @return 分割的代价
     */
    public static int splitGold(int[] slices) {
        if (slices == null || slices.length == 0) {
            return 0;
        }

        // 由于这道题符合使用内置堆的条件，弹一个数，给一个数，所以可以直接使用内置堆(小根堆)
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        // 先遍历数组，所有数字依次进堆
        for (int i : slices) {
            queue.add(i);
        }

        int sum = 0;  // 要花费的总代价
        int cur = 0;  // 每次分割的花费
        // 循环直到堆中只剩一个元素
        while (queue.size() > 1) {
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }

        return sum;
    }
}
