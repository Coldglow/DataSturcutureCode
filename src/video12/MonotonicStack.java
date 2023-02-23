/**
 * 通过单调栈求出数组中每个数左右两侧距离他最近的大于他的数
 */
package video12;

import java.util.ArrayList;
import java.util.Stack;

public class MonotonicStack {
    /**
     * 入栈的结构
     * value：数组中的值
     * ArrayList：记录所有值等于value的下标
     */
    public static class Element {
        private int value;
        private ArrayList<Integer> indexList;

        Element(int value, int firstIndex) {
            this.value = value;
            indexList = new ArrayList<>();
            indexList.add(firstIndex);
        }
    }

    /**
     * 返回的数据结构
     * left: 左侧距离最近的大于值的下标
     * right：右侧距离最近的大于值的下标
     */
    public static class LRNum {
        int index;  // arr数组中的某一个数的而下标
        int left;   // arr数组中距离index位置上的值最近的且比它大的，在它左侧的数的位置
        int right;  // arr数组中距离index位置上的值最近的且比它大的，在它右侧的数的位置

        LRNum(int index, int left, int right) {
            this.index = index;
            this.left = left;
            this.right = right;
        }
    }

//    /**
//     *
//     * @param stack  当前状态的栈
//     * @param curVal 当前值
//     * @return 如果stack中存在当前值，返回指向当前值的元素，否则返回null
//     */
//    public static Element isInStack(Stack<Element> stack, int curVal) {
//        Stack<Element> reverseStack = new Stack<>();
//        Element element = null;
//        while (!stack.isEmpty()) {
//            if (stack.peek().value != curVal) {
//                reverseStack.push(stack.pop());
//            } else {
//                element = stack.peek();
//                break;
//            }
//        }
//        // 恢复stack
//        while (!reverseStack.isEmpty()) {
//            stack.push(reverseStack.pop());
//        }
//
//        return element;
//    }

    /**
     *
     * @param stack  当前状态的栈
     * @param res 结果数组
     * @param resIndex res最后的下标
     * @param valIndex 加入到res数组的值在arr数组中的下标
     */
    public static void updateRes(Stack<Element> stack, LRNum[] res, int resIndex, int valIndex) {
        Element stackTop = stack.pop();
        // 遍历stackTop的indexList，该列表中的所有的下标的右侧较大值的下标是i
        for (Integer index : stackTop.indexList) {
            // 如果stackTop下面还有元素，说明他的左侧存在比stackTop大的值
            if (!stack.isEmpty()) {
                int left = stack.peek().indexList.get(0);
                res[resIndex++] = new LRNum(index, left, valIndex);
            } else {
                // 如果没有，说明左侧不存在比它大的数, -1表示不存在
                res[resIndex++] = new LRNum(index, -1, valIndex);
            }
        }
    }

    /**
     *
     * @param stack  当前状态的栈
     * @param res  结果数组
     * @param resIndex  res最后的下标
     */
    public static void clearStack(Stack<Element> stack, LRNum[] res, int resIndex) {
        while (!stack.isEmpty()) {
            Element stackTop = stack.pop();
            // 遍历stackTop的indexList，该列表中的所有的下标的右侧较大值的下标是i
            for (Integer index : stackTop.indexList) {
                // 如果stackTop下面还有元素，说明他的左侧存在比stackTop大的值
                if (!stack.isEmpty()) {
                    int left = stack.peek().indexList.get(0);
                    res[resIndex++] = new LRNum(index, left, -1);
                } else {
                    res[resIndex++] = new LRNum(index, -1, -1);
                }
            }
        }
    }


    /**
     * 遍历过程中始终保持栈底到栈顶顺序从大到小
     * @param arr 用户给的数组，不一定是整形，但是需要一个可以用来比较的数据
     * @return 返回一个LRNum类型的数组，LRNum[i].left表示arr中LRNum[i].index位置的数左侧距离他最近的大于他的值的下标
     *         该值是arr[LRNum[i].left]，右侧同理
     *         就是说LRNum的顺序不重要，LRNum存储了
     */
    public static LRNum[] getRecentBigNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        LRNum[] res = new LRNum[arr.length];
        Stack<Element> stack = new Stack<>();
        int resIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty() || stack.peek().value > arr[i]) {
                // 如果栈为空，当前值生成element直接进栈
                // 或者如果栈不为空，栈顶的值大于当前值，当前元素直接入栈
                stack.push(new Element(arr[i], i));
                continue;
            }

            // 如果栈顶的值小于当前值,依次弹出栈顶元素得到结果

            while (!stack.isEmpty() && stack.peek().value < arr[i]) {
                updateRes(stack, res, resIndex, i);
            }
            // 循环结束后
            // 要么栈为空
            // 要么栈顶元素的值等于当前元素的值
            // 要么既不为空，也不相等
            if (stack.isEmpty() || stack.peek().value != arr[i]) {
                stack.push(new Element(arr[i], i));
            }  else {
                // 不可能出现两个值相同的元素中键隔着几个元素的情况
                // 如果两个元素的值相等，必然只有可能是栈顶元素相等
                stack.peek().indexList.add(i);
            }

            // 遍历结束，如果栈不为空，逐个弹出添加
            clearStack(stack, res, resIndex);
        }
        return res;
    }
}
