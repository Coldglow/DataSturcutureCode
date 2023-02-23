package video4;

import video4.IsPalindromicList.LinkedNode;

import static java.lang.Math.abs;

public class LinkedListWithLoop {
    /**
     * 验证暂时没问题
     * @param head 链表头节点
     * @return 返回环的第一个节点
     * 快慢指针初始都在头节点，当二者相遇的时候，慢指针动，快指针回到头节点，接下来二者每次都只走一步，
     * 当二者相遇的时候，即为环的第一个节点。
     */
    public static LinkedNode getLoopHead(LinkedNode head) {
        if(head == null || head.next == null || head.next.next == null) {
            return null;
        }

        LinkedNode fast = head;
        LinkedNode slow = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                break;
            }
        }

        // 无环
        if(fast == null) {
            return null;
        }

        // 有环
        fast = head;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public static LinkedNode getLoopedListHead(LinkedNode head1, LinkedNode head2) {

        if(head1 == null || head2 == null) {
            return null;
        }

        LinkedNode loophead1 = getLoopHead(head1);
        LinkedNode loophead2 = getLoopHead(head2);
        LinkedNode res = null;

        // 情形1:两个链表都不存在环
        if(loophead1 == null && loophead2 == null) {
            res = noLoop(head1, head2);
        } else if(loophead1 != null && loophead2 != null) {
            // 情况2：两个链表都存在环此时
            res = bothLoop(head1, head2, loophead1, loophead2);
        }
        // 情况3，一个有环一个无环，此时不可能相交，直接返回null

        return res;
    }

    /**
     * 无环链表相交返回相交的第一个节点
     * @param head1 链表1的头节点
     * @param head2 链表2的头节点
     * @return 返回值
     *
     * 首先遍历两个链表，得出长度差值，然后较长的链表先走差值个节点，此时两个链表有效长度相同
     * 然后同时走，第一个相等的节点就是相交的第一个节点
     */
    public static LinkedNode noLoop(LinkedNode head1, LinkedNode head2) {
        LinkedNode cur1 = head1;
        LinkedNode cur2 = head2;

        int n = 0;
        // 遍历的到尾节点和链表长度
        // 只是用一个变量来记录长度差值
        while(cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while(cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        // 尾节点不相等，则不相交
        if(cur1 != cur2) {
            return null;
        }
        // 确保cur1指向较长的链表,cur2指向较短的链表
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        // 长链表指针先走长出来的部分
        for(int i = abs(n); i > 0; i--) {
            cur1 = cur1.next;
        }

        while(cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }

    /**
     * 此时也分为三种情况
     * 1. 两个链表不相交，两个单独的环.
     * 2. 链表相交，同时环的头节点相等。
     * 3. 环的头节点不等
     * @param head1 链表1的头节点
     * @param head2 链表2的头节点
     * @param loophead1 链表1的环节点
     * @param loophead2 链表2的环节点
     * @return 返回相遇的第一个节点
     */
    public static LinkedNode bothLoop(LinkedNode head1, LinkedNode head2, LinkedNode loophead1,
                                      LinkedNode loophead2) {
        LinkedNode cur1 = head1;
        LinkedNode cur2 = head2;
//        LinkedNode res = null;

        // 此时将截至条件设置为换的头节点，忽略环内节点，就相当于两个无环链表相交求相交的第一个节点
        // 遍历求长度差值，长链表先走多出来的一截，相等的第一个节点即为结果
        // 和上面的noLoop代码一样，只是终止条件不同
        if(loophead1 == loophead2) {
            int n = 0;
            while(cur1 != loophead1) {
                n++;
                cur1 = cur1.next;
            }
            while(cur2 != loophead2) {
                n--;
                cur2 = cur2.next;
            }

            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            // 长链表指针先走长出来的部分
            for(int i = abs(n); i > 0; i--) {
                cur1 = cur1.next;
            }

            while(cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;
        }
        else {
            while(cur1 != loophead2) {
                if(cur1 == loophead1) {  // 转了一圈碰到自己，说明不相交，属于情况1
                    return null;
                }
                cur1 = cur1.next;
            }
            // 循环结束，说明cur1碰到了loopHead2,属于情况3,返回任意一个环头节点
            return loophead1;
        }
    }

    public static void main(String[] args) {
        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node3 = new LinkedNode(3);
        LinkedNode node4 = new LinkedNode(4);
        LinkedNode node5 = new LinkedNode(5);
        LinkedNode node6 = new LinkedNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
//        node6.next = node3;

        System.out.println(getLoopHead(node1));
    }
}
