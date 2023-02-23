/**
 * 滑动窗口，统一成左开右闭区间
 * 使用双端队列
 */
package video12;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindow {
    /**
     * 将滑动窗口获取最大值的操作封装成了一个类
     */
    public static class WindowMax {
        private int L;
        private int R;
        private int[] arr;   // arr[  (L...R]    ]  左开右闭
        private LinkedList<Integer> qmax;

        WindowMax(int[] a) {
            arr = a;
            L = -1;
            R = 0;
            qmax = new LinkedList<>();
        }

        public void addRight(int num) {
            if(R == arr.length) {
                return;
            }
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                qmax.pollLast();
            }
            qmax.addLast(R);
            R++;
        }

        public void removeLeft() {
            if (L >= R - 1) {
                return;
            }
            L++;
            while (!qmax.isEmpty() && qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
        }

        public Integer getMax() {
            if (!qmax.isEmpty()) {
                return arr[qmax.peekFirst()];
            }
            return null;
        }
    }

    /**
     * 数组长度为l，窗口宽度为w，窗口每次向右一步，那么窗口总共有n-w+1个状态
     * 返回的数组长度也是n-w+1
     * L所表示的下标不在队列中  R表示的下标在队列中
     * 也就是说窗口的边界在L和R的右侧
     * 下标  0  1  2  3  4  5
     * 程序     L     R
     * 实质      L     R
     * 窗口中只包含下标 1 2，而不是 1 2 3
     * 如果窗口宽度大于数组长度，直接返回，没必要用滑动窗口
     *
     * @param arr 数组
     * @param W 窗口宽度
     * @return 返回每次窗口向右移动一次，窗口中的 最大值 所形成的数组
     */
    public static int[] windowMaxValue(int[] arr, int W) {
        if (arr.length == 0 || W == 0 || arr.length < W) {
            return null;
        }

        Deque<Integer> deque = new LinkedList<>();
        int arrL = arr.length;
        int index = 0;
        // 先将第一个窗口中的数字的下标进入队列
        int[] res = new int[arrL - W + 1];
        // i 就是 R，所以L = i - W
        for (int i = 0; i < arrL; i++) {
            // 等于的时候也要弹出
            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);

            // 如果左侧滑出的值是最大值，那么队列头部弹出该值的下标
            if ((i - W) == deque.peekFirst()) {
                deque.pollFirst();
            }
            // 如果i < W - 1，说明第一个窗口还没形成
            // 如果 >=，才需要加入最大值到res
            if (i >= W - 1) {
                res[index++] = arr[deque.peekFirst()];
            }
        }
        return res;
    }
}
