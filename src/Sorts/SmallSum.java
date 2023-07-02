package Sorts;

/**
 * 求小和问题
 * 思路在视频P4，1小时1分50秒开始
 */

public class SmallSum {
    public static int smallSum(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int i = 0;
        int pl = left;
        int pr = mid + 1;
        int res = 0;
        int[] help = new int[right - left + 1];

        while(pl <= mid && pr <= right) {
            // 这里注意和归并不同的是，当右侧等于左侧的时候，要先把右侧移动到
            // 辅助空间内，否则就无法求右侧有几个数比左侧大
            // 注意这里是right-pr，不是right-left
            res += arr[pl] < arr[pr]? arr[pl] * (right - pr + 1) : 0;
            help[i++] = arr[pl] < arr[pr]? arr[pl++] : arr[pr++];
        }
        while(pl <= mid) {
            help[i++] = arr[pl++];
        }
        while(pr <= right) {
            help[i++] = arr[pr++];
        }
        for(i = 0; i < help.length; i++) {
           arr[left++] = help[i];
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{3, 2, 5, 6, 9, 5, 3};
        int[] arr = new int[]{1, 3, 4, 2, 5};
        System.out.println(smallSum(arr));
    }
}
