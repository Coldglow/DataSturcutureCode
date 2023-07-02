package video3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SortArrayDistanceLessK {

    public static class AComp implements Comparator<Integer> {
        /**
         * 比较器默认的规则就是
         * 返回负数的时候，第一个参数排前面，返回正数的时候，第二个参数排序前面，返回0，顺序无所谓
         * 在堆中就是
         * 返回负数的时候，第一个参数放在上面，返回正数的时候，第二个参数放在上面，返回0，顺序无所谓
         *
         * @param first the first object to be compared.
         * @param second the second object to be compared.
         * @return  如果返回负数，认为first放在前面，说明first大于second，也就是从大到小排序
         */
        public int compare(Integer first, Integer second) {
            return second - first;
        }
    }

    // 因为这道题是添加一个数，弹出一个数的形式，所以可以使用内置的堆结构
    // 但如果是修改已经构建好的堆的结构当中的值，并且希望修改后仍然是个堆结构，那么就不能用内置的堆结构了
    // 需要自己写heapify方法
    public static void sortArrayDistanceLessK(int[] arr, int k) {
        int index = 0;
        // 首先将前k个数构建成堆，默认的数据结构是小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // 如果需要构建大根堆，则需要加入比较器
//        PriorityQueue<Integer> heap_acomp = new PriorityQueue<>(new AComp());

        // 确保k小于数组长度
        for(; index <= k; index++) {
            heap.add(arr[index]);    // 每添加一个数，系统会自动构建成小根堆
        }

        int i = 0;
        for(; index < arr.length; i++, index++) {
            heap.add(arr[index]);    // 每添加一个数，系统会自动构建成小根堆
            arr[i] = heap.poll();    // 弹出最小值，赋值给i，系统会将剩余的数修正为最小堆
        }

        // 当最后一个堆构建完成时，按照顺序poll即可，系统会自动在poll完一个以后修正最小堆
        while(!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}
