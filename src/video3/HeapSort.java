package video3;

public class HeapSort {
    /**
     *   整体时间复杂度是O(NLogN)   空间O(1)
     *   i位置的左节点是数组2*i+1位置的值，i位置右节点是数组2*i+2位置的值，i位置的父节点是(i-1)/2
     */
    public static void heapSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        // 首先把数组变成大根堆结构
//        for(int i = 0; i < arr.length; i++) {  // O(N)
//            heapInsert(arr, i);    // O(logN)
//        }

        // 如果只让数组成为堆结构，则可以这样做
        // 上面是一个数一个数的去构建
        // 下面这个是直接给了一个数组，然后从倒数第二层节点最右边的结点开始构建堆，因为叶节点没有左右子树，所以无需构建
        // 这样就不是heapInsert操作了，而直接是heapify操作。
        // 视频1.08.25开始介绍
        for (int i = arr.length; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }


        // 堆排的基础是堆结构，所以在排序之前要将数组变成堆
        int heapSize = arr.length;
        swap(arr,0, --heapSize);   // 将堆的最大值，即根节点和末尾节点交换
        while(heapSize > 0) {   // 当heapSize等于1的时候就排好序了    O(N)
            heapify(arr, 0, heapSize);   // 每次都从根节点开始向下查询，构成堆  O(LogN)
            swap(arr, 0, --heapSize);    // 构建结束后，将根节点和尾节点交换，heapSize减一   O(1)
        }
    }

    /**
     * @param arr  存储堆结构的数组
     * @param index 发生改变的值的下标
     *              如果index位置的值大于原先改变的值，需要做heapInsert操作，向上查询，确保数组存储的仍然是堆
     *              如果小于原先位置的值，需要做heapify操作，向下查询，确保仍然是堆
     *
     *              heapInsert用于将一个不是堆结构的数组构建成堆结构
     *              heapify用于只有一个数的位置错误而其他数位置正确的情况下，修正堆结构
     */
    public static void heapInsert(int[] arr, int index) {
        // 插入过程，结束条件是当前节点的值小于等于父节点的值
        while(arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    // heapify操作是指：取出堆顶数据，然后将其移除，用堆的最后一个数字代替，然后将剩余的数字维护成一个堆结构
    // 具体做法：
    //      维护一个变量heapSize，记录此时堆中还有多少数据
    //      将堆顶元素和最后一个元素交换
    //      heapSize-- 表示最后一个元素已经被取出
    //      循环，直到heapSize减为0
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;

        // 当index节点存在孩子的时候，如果不存在左孩子，则一定不存在右孩子，
        // 所以在比较孩子节点较大值的时候要判断是否存在右孩子
        while(left < heapSize) {
            // 取出子结点较大值的下标
            int larger = left + 1 < heapSize && arr[left + 1] > arr[left]? left + 1 : left;
            // 比较父节点和子节点谁的值大，把它的下标赋值给larger
            larger = arr[index] > arr[larger]? index : larger;
            // 如果父节点是三者中的最大值，直接退出循环，表明已经是堆结构
            if(index == larger) {
                break;
            }
            swap(arr, index, larger);
            // 更新index的位置，继续向下
            index = larger;
            left = 2 * index + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
