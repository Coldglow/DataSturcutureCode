package video2;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int left, int right) {
        if(left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int pl = left;
        int pr = mid + 1;
        int i = 0;

        while(pl <= mid && pr <= right) {
            // 注意这里哪里需要写++，哪里不用写
            help[i++] = arr[pl] <= arr[pr] ? arr[pl++]:arr[pr++];
        }

        while(pl <= mid) {
            help[i++] = arr[pl++];
        }
        while(pr <= right) {
            help[i++] = arr[pr++];
        }

        for (i = 0; i < help.length; i++) {
            arr[left++] = help[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 5, 6, 9, 5, 3, 1, 0};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
