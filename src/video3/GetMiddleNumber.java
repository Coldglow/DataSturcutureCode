/**
 * 维持一个大根堆和一个小根堆，以LogN级别的时间复杂度获取中位数
 */
package video3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class GetMiddleNumber {

    private static PriorityQueue<Integer> smallQueue;
    private static PriorityQueue<Integer> largeQueue;

    GetMiddleNumber() {
        smallQueue = new PriorityQueue<>();
        largeQueue = new PriorityQueue<>(new LargeComparator());
    }

    public static class LargeComparator implements Comparator<Integer> {
        // 这里参数类型要写成Integer,不能写成int,否则不算是重载
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    /**
     * @param n 要插入的数字
     */
    public void insert(int n) {
        // 第一个数直接入大根堆
        if(largeQueue.isEmpty()) {
            largeQueue.add(n);
        }

        // 之后的数，如果小于等于大根堆堆顶元素，入大根堆，否则入小根堆
        if(n <= largeQueue.peek()) {
            largeQueue.add(n);
        } else {
            smallQueue.add(n);
        }
        int largeSize = largeQueue.size();
        int smallSize = smallQueue.size();
        // 如果大根堆的元素个数比小根堆的元素个数大于1，那么大根堆堆顶弹出入小根堆
        if (largeSize > smallSize && (largeSize - smallSize) > 1) {
            smallQueue.add(largeQueue.poll());
        }
        // 如果小根堆的元素个数比大根堆的元素个数大于1，那么小根堆堆顶弹出入大根堆
        if (smallSize > largeSize && (smallSize - largeSize) > 1) {
            largeQueue.add(smallQueue.poll());
        }
    }

    // 查询中位数
    public int getMiddleNum() {

        if(largeQueue.isEmpty()) {
            return -1;
        }

        int largeSize = largeQueue.size();
        int smallSize = smallQueue.size();
        // 如果二者大小不同，返回个数多的那个元素的堆顶元素，如果个数相同，堆顶元素相加除2
        if(largeSize == smallSize) {
            return (largeQueue.peek() + smallQueue.peek()) / 2;
        } else {
            return largeSize > smallSize ? largeQueue.peek() : smallQueue.peek();
        }
    }
}
