package Sorts;

import java.util.Arrays;

/**
 * 荷兰国旗问题，将一个数组分成三部分，左半部分都小于某个数，中间部分等于某个数
 * 左边部分大于某个数，左边和右边部分都不要求有序.
 * 使用两个指针，分别指向左侧小于区域的边界和右侧大于区域的边界
 * i < num时，arr[i]和左侧小于区域的下一个数交换，小于区域边界++
 * i==num时，i++
 * i > num时，arr[i]和右侧大于区域的前一个数交换，大于区域边界--，此时i不变
 * 当i == 大于区域边界时停止循环
 */


public class NetherlandsFlag {
    public static void netherlandsFlag(int[] arr, int num) {
        int s = 0;
        int l = arr.length - 1;
        int i = 0;

        while(i != l) {
            if(arr[i] < num) {
                swap(arr, i++, s++);
            } else if(arr[i] == num) {
                i++;
            } else {
                swap(arr, l--, i);
            }
        }
    }

    public static void swap(int[] arr, int L, int R) {
        int temp = arr[L];
        arr[L] = arr[R];
        arr[R] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 5, 6, 9, 5, 3};
        netherlandsFlag(arr, 5);
        System.out.println(Arrays.toString(arr));
    }
}
