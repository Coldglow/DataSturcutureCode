package video4;

import java.util.HashMap;

public class CopyRandLinkedList {

    public static class Node{
        int val;
        Node next;
        Node rand;

        Node(int val) {
            this.val = val;
        }
    }

    // O(N)时间复杂度和空间复杂度
    public static Node copyLinkedlistHash(Node head) {
        // 初始化哈希表，key是旧的节点，value是新复制的节点，暂时只初始化val值
        HashMap<Node, Node> hash = new HashMap<>();
        Node pointer = head;
        while(pointer != null) {
            hash.put(pointer, new Node(pointer.val));
            pointer = pointer.next;
        }

        // 通过哈希表获取连接的节点的地址
        pointer = head;
        while(pointer != null) {
            hash.get(pointer).next = hash.get(pointer.next);
            hash.get(pointer).rand = hash.get(pointer.rand);
            pointer = pointer.next;
        }

        return hash.get(head);
    }

    // O(N)时间复杂度，O(1)级别空间复杂度
    public static Node CopyWithoutHash(Node head) {

        if(head == null) {
            return null;
        }

        // 生成新的节点直接挂在对应的旧节点之后
        Node cur = head;
        Node next = null;
        while(cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }

        // 通过cur.rand.next得到newNode.rand
        // cur是旧的节点，cur.next是新的节点
        // cur.rand.next是新节点指向的rand节点
        cur = head;
        while(cur != null) {
            // 这里要注意不能直接这么写，要考虑到cur.rand指针为null的情况
//            cur.next.rand = cur.rand.next;
            cur.next.rand = cur.rand != null? cur.rand.next : null;
            cur = cur.next.next;
        }

        // 分离两个链表
        cur = head;
        Node newHead = head.next;
        Node newCur = head.next;
//        while(cur != null) {
//            next = cur.next.next;  // next有可能是null
//            newCur.next = newCur.next.next;
//            cur = next;
//            newCur = newCur.next;
//        }

        while(cur != null) {
            next = cur.next.next;
            cur.next = next;
            newCur.next = next != null? next.next : null;
            cur = next;
            newCur = newCur.next;
        }

        return newHead;
    }

}
