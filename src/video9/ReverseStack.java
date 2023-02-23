/**
 * 在不使用额外数据结构的情况下用递归逆序一个栈
 */
package video9;

import java.util.Stack;

public class ReverseStack {

    /**
     * 逆序一个栈
     * 每次调用f函数，获取栈底元素，然后保存在一个变量中，当栈空的时候返回，然后压栈
     * @param stack 栈
     */
    public static void reverse(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);  // 因为递归函数的原因，先入栈最后返回的
    }

    /**
     * 拿出一个栈的栈底元素，其余元素在栈内不变
     * @param stack 栈
     * @return 返回栈底元素
     */
    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if(stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }
}
