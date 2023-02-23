// 线段树
package Tree_5;

import java.util.ArrayList;
import java.util.List;

public class SegmentTree {
    /*
        如果用数组存储线段树，那么数组大小要开4n
        节点的value值存储存储该区间的和
        左端点的值leftVal
        右端点的值rightVal
        三个函数中k都是表示当前区间的下标  l是当前区间的左端点   r是当前区间的右端点
        所以查询和增加操作都是从根节点开始 根节点的下标为1
     */
    private int[] tree = new int[50000];
    /**
     * @param k  下标为k的节点
     * @param l  下标的左端点
     * @param r  下标的右端点
     */
    public void buildTree(int k, int l, int r) {
        if (l == r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        buildTree(2 * k, l, m);   // 左子树
        buildTree(2 * k + 1, m + 1, r);  // 右子树
        // k位置的值
        tree[k] = tree[2 * k] + tree[2 * k + 1];
    }

    /**
     * 在下标为x的节点上加上y
     * @param k  当前节点下标
     * @param l  当前k节点的左端点
     * @param r  当前k节点的右端点
     * @param x  目标节点
     * @param y  要加的值
     */
    public void add(int k, int l, int r, int x, int y) {
        tree[k] += y;
        if (l == r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        if (x <= m) {
            // 如果x在k的左子树上
            add(2 * k, l, m, x, y);
        } else {
            add(2 * k + 1, m + 1, r, x, y);
        }
    }

    /**
     * 求出[s，t]之间的和
     * @param k 当前节点下标为k
     * @param l 当前区间的左端点
     * @param r 当前区间的右端点
     * @param s 目标区间的左端点
     * @param t 目标区间的右端点
     * @return 返回目标区间的和
     */
    public int calculate(int k, int l, int r, int s, int t) {
        // 当前区间和目标区间相等，返回
        if (l == s && r == t) {
            return tree[k];
        }
        int m = l + ((r - l) >> 1);
        // 三种情况
        // 1. s-t完全位于l-r的左区间
        // 2. s-t完全位于l-r的右区间
        // 3. s-t跨越l-r的左右区间
        if (t <= m) {
            // 情况1
            return calculate(2 * k, l, m, s, t);
        } else if (s > m) {
            // 情况2
            return calculate(2 * k + 1, m + 1, r, s, t);
        } else {
            return calculate(2 * k, l, m, s, m) + calculate(2 * k + 1, m + 1, r, m + 1, t);
        }
    }

    public static void main(String[] args) {
        SegmentTree o = new SegmentTree();
        int n = 10;   // 数组的长度 - 1
        o.buildTree(1, 1, n);
        o.add(1,1,n ,3, 5);
    }
}

/*
    // 树节点
    class Node {
        int left;  // 区间范围是 [left, right]
        int right;
        int data;  // 该节点的值
        int mark;  // 延迟更新的标记

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public void addMark(int value) {
            this.data += mark;
        }

        public void clearMark() {
            this.mark = 0;
        }

        public String toString() {
            return this.left + "-" + this.right;
        }
    }
 */
