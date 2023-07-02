/**
 * 快排
 * 每次再随机生位置成一个pivot，然后和最右边的位置交换
 * 然后partition，记住partition过程中因为pivot处于R位置，所以R不参与遍历，L用作i进行遍历
 * 循环的条件是当小于等于边界和大于边界相当时停止，所以while的条件是 L != more
 * 切记最后partition结束之后把R位置和more位置的值交换
 */
package Sorts;

import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] arr, int L, int R) {
        if(L < R) {
            // 因为使用了随机pivot，所以快排的时间复杂度变成了O(NLogN)
            // L + (int)(Math.random() * (R - L + 1))  是选出来的pivot
            // 第二参数是R不是R-1
            swap(arr, L + (int)(Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0]);
            quickSort(arr, p[1] + 1, R);
        }
    }

    // 这个函数返回数组中间等于num的部分的左右边界，即第一个等于num的下标和最后一个等于num的下标，相当于荷兰国旗问题
    public static int[] partition(int[] arr, int L, int R) {
        // less 和 more是边界   用来遍历的是L
        // 这两个的初始化要记住
        int less = L;
        int more = R;

        // 荷兰国旗问题相当于快排的一次遍历，但是快排需要多次遍历，因此不能每次都声明新变量i
        // 这里每次划分的数组左侧的下标L可以作为遍历的指针，L就相当于每次的i
        // 这里一定要记住条件，当小于等于的边界和大于边界相等时停止
        // 而不是L != R  R不参与遍历
        while(L != more) {
            // 因为刚开始已经把num放在数组最右端了，所以此时arr[R]就是pivot
            if(arr[L] < arr[R]) {
                swap(arr, L++, less++);
            } else if(arr[L] == arr[R]) {
                L++;
            } else {
                // 因为R位置是pivot，不能交换位置，所以是--more而不是more--
                swap(arr, --more, L);
            }
        }
        // 和荷兰国旗不一样的地方，把大于部分的第一个和pivot交换，
        // 这样在前半部分就是小于等于pivot部分，后半部分就是大于pivot部分的
        swap(arr, more, R);
        return new int[]{less, more};
    }

    // 交换数
    public static void swap(int[] arr, int rands, int p) {
        int temp = arr[rands];
        arr[rands] = arr[p];
        arr[p] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,0,2,1,1,0};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
