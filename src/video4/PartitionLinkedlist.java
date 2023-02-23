package video4;

import video4.IsPalindromicList.LinkedNode;

public class PartitionLinkedlist {
    public static LinkedNode partirionlist(LinkedNode head, int pivot) {
        LinkedNode sh = null;
        LinkedNode st = null;
        LinkedNode eh = null;
        LinkedNode et = null;
        LinkedNode bh = null;
        LinkedNode bt = null;

        while(head != null) {
            if(head.val < pivot) {
                if(sh != null) {
                    st.next = head;
                    st = head;
                }
                sh = head;
                st = head;
            }
            else if(head.val > pivot) {
                if(bh != null) {
                    bt.next = head;
                    bt = head;
                }
                bh = head;
                bt = head;
            } else {
                if(eh != null) {
                    et.next = head;
                    et = head;
                }
                eh = head;
                et = head;
            }
            head = head.next;
        }

        // 考虑全面，当三种区域分别存在不存在的情况
        if(st != null) {
            st.next = eh;
            et = et == null? st : et;   // 要加上这一句，谁去连大于区域的头，谁就是et
        }

        if(et != null) {
            et.next = bh;
        }

        return sh != null? sh : (eh != null? eh : bh);
    }
}
