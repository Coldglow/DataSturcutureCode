package video2;

/**
 * 逆序对问题，也是归并问题的拓展
 * 思路就是 通过分解，排序，比较左右两侧数的大小
 */

public class PairOfInversion {
    public static int pairOfInversion(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int left, int right) {
        if(left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int i = 0;
        int pl = left;
        int pr = mid + 1;
        int[] help = new int[right - left + 1];
        int num = 0;

        while(pr <= right && pl <= mid) {
            // 根据题目不同，修改这里就好，这里根据逆序的需求，改为从大到小排序
            num += arr[pl] > arr[pr]? (right - pr + 1) : 0;
            help[i++] = arr[pl] >= arr[pr]? arr[pl++] : arr[pr++];
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

        return num;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 5, 6, 9, 5, 3};
//        int[] arr = new int[]{1, 3, 4, 2, 5};
        System.out.println(pairOfInversion(arr));
    }
}
