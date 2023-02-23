/**
 * 定义:数组中累积和与最小值的乘积，假设叫做指标A。给定一个不包含负数的数组，请返回子数组中，指标A最大的值。
 * 思路：
 * 从左到右，对于每一个数，考虑生成这样的子数组，每个子数组都必须包含这个数，并且以这个数为最小值。
 * 选择累加和最大的子数组作为这个数的结果。
 * 这样考虑的话就是单调栈的应用，维持栈底到栈顶的顺序是从小到大。如果栈顶元素大于当前元素，栈顶元素弹出并返回。
 *
 */
package video12;

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class MaxSubArrA {
    /**
     * 入栈的数据结构
     * indexArr保存在用户给定的数组arr中，所有值等于val的下标
     */
    public static class Element {
        public LinkedList<Integer> indexArr;  // 相同值的下标从头部进入队列
        public int val;

        Element(int index, int val) {
            indexArr = new LinkedList<>();
            this.val = val;
            this.indexArr.add(index);
        }
    }

    /**
     *
     * @param arr 给定的数组
     * @param left 左边界
     * @param right 有边界
     * @return 返回(left, right)这段距离arr的累计和
     */
    public static int calSum(int[] arr, int left, int right) {
        int sum = 0;
        while (++left < right) {
            sum += arr[left];
        }
        return sum;
    }

    /**
     *
     * @param arr 数组
     * @return 返回最大的指标A
     */
    public static int maxSubArrA(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        Stack<Element> stack = new Stack<>();
        Element topElement = null;
//        int temp = 0;  // 用于测试
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            // 如果栈为空或者当前元素大于栈顶元素，直接入栈
            if (stack.isEmpty() || arr[i] > stack.peek().val) {
                stack.push(new Element(i, arr[i]));
                continue;
            }
            // 如果小于，弹出栈顶元素
            while (!stack.isEmpty() && arr[i] < stack.peek().val) {
                topElement = stack.pop();
                for (Integer index : topElement.indexArr) {
                    if (!stack.isEmpty()) {
                        // 获取左侧的最近的小于自己的元素的下标，就是indexArr的最后一个元素
                        int left = stack.peek().indexArr.getFirst();
//                        temp = calSum(arr, left, i) * topElement.val;
                        res = Math.max(res, calSum(arr, left, i) * topElement.val);
//                        System.out.print("topElement.val:" + topElement.val + " temp:" + temp);
                    } else {
//                        temp = calSum(arr, -1, i) * topElement.val;
                        res = Math.max(res, calSum(arr, -1, i) * topElement.val);
//                        System.out.print("topElement.val:" + topElement.val + " temp:" + temp);
                    }
                    System.out.println();
                }
            }

            if (stack.isEmpty() || stack.peek().val != arr[i]) {
                stack.push(new Element(i, arr[i]));
            } else {
                stack.peek().indexArr.addFirst(i);
            }
        }

        // 循环结束，清空栈
        while (!stack.isEmpty()) {
            topElement = stack.pop();
            for (Integer index : topElement.indexArr) {
                if (!stack.isEmpty()) {
                    // 获取左侧的最近的小于自己的元素的下标，就是indexArr的最后一个元素
                    int left = stack.peek().indexArr.getFirst();
//                    temp = calSum(arr, left, arr.length) * topElement.val;
                    res = Math.max(res, calSum(arr, left, arr.length) * topElement.val);
//                    System.out.print("topElement.val:" + topElement.val + " temp:" + temp + "  index:" + index);
                } else {
//                    temp = calSum(arr, -1, arr.length) * topElement.val;
                    res = Math.max(res, calSum(arr, -1, arr.length) * topElement.val);
//                    System.out.print("topElement.val:" + topElement.val + " temp:" + temp + "  index:" + index);
                }
                System.out.println();
            }
        }
//        System.out.print("res:" + res);
        return res;
    }

    public static void main(String[] args) {
        // 至少这个例子是对的
        int[] arr = new int[] {3, 5, 2, 1, 2, 1, 8, 4};
        maxSubArrA(arr);
    }
}
