/**
 * 判断单链表是否是回文链表
 * O(N)空间复杂度：用栈保存第一次遍历结果，第二次遍历一个弹出一个，比较是否相同
 * O(1)空间复杂度：快慢指针，翻转后半部分的指向，然后用头尾两个指针同时遍历，比较，在return之前再把后半部分翻转回去。
 */
package video4;

import java.util.Stack;

public class IsPalindromicList {
    public static class LinkedNode {
        int val;
        LinkedNode next = null;

        LinkedNode(int v) {
            this.val = v;
        }
    }

    // O(N)空间复杂度：用栈保存第一次遍历结果，第二次遍历一个弹出一个，比较是否相同
    public static boolean stackMethod(LinkedNode head) {
        Stack<Integer> stack = new Stack<>();
        LinkedNode pointer = head;

        while(pointer != null) {
            stack.push(pointer.val);
            pointer = pointer.next;
        }

        pointer = head;
        while(pointer != null) {
            if(pointer.val != stack.pop()) {
                return false;
            }
            pointer = pointer.next;
        }

        return true;
    }

    // O(1)空间复杂度：快慢指针
    // 因为要翻转后半部分指向，所以slow在遍历结束后需要指向后半部分的前一个节点。
    public static boolean slowFast(LinkedNode head) {
        LinkedNode slow = head;
        LinkedNode fast = head;
        boolean res = true;

        // slow指向后半部分的前一个节点
        // fast指针指向不确定，奇数情况下指向最后一个节点。偶数情况下指向倒数第二个节点。
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 翻转后半部分,返回翻转后的头节点，也就是原先链表的尾节点
        LinkedNode tail = reverseList(slow.next);
        fast = tail;   // 让fast指向尾节点，用于比较

        // 首尾指针同时遍历，遍历结束后fast指向后半部分的第一个节点
        // 奇数情况下中间节点会在前半部分，所以前半部分长度永远比后半部分多1，因此后半部分会比前半部分先遍历结束
        // 这里因为没有要求head不能改变，所以直接用了head遍历，所以循环结束后head的地址会发生改变，不再是头节点
        while(fast != null) {
            if(fast.val != head.val) {
                res = false;
                break;
            }
            fast = fast.next;
            head = head.next;
        }

        // 恢复原链表,再次翻转
        fast = reverseList(tail);
        slow.next = fast;

        return res;
    }

    public static LinkedNode reverseList(LinkedNode head) {
        // 翻转后半部分
        LinkedNode pre = null;
        LinkedNode nxt;
        LinkedNode cur = head;

        // 翻转结束后pre指向尾指针。
        while(cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }

        return pre;
    }

    public static void display(LinkedNode head) {
        while(head != null) {
            System.out.print(head.val);
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(11);
        LinkedNode node3 = new LinkedNode(2);
        LinkedNode node4 = new LinkedNode(22);
        LinkedNode node5 = new LinkedNode(3);
        LinkedNode node6 = new LinkedNode(33);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        // 两个方法暂时没问题
//        System.out.println(stackMethod(node1));
//        System.out.println(slowFast(node1));
        LinkedNode cur = node1;
        LinkedNode newCur = node2;
        LinkedNode next = null;
        while(cur != null) {
            next = cur.next.next;
            cur.next = next;
            newCur.next = next != null? next.next : null;
            cur = next;
            newCur = newCur.next;
        }

        display(node1);
        display(node2);
    }
}
