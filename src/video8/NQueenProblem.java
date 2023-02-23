/**
 * N皇后问题
 */
package video8;

public class NQueenProblem {
    /**
     * N皇后问题的第一种方法，使用数组的方式记录
     * @param n n个皇后
     * @return 返回合法的摆放方式个数
     */
    public static int NQueen1(int n) {
        if(n < 1) {
            return 0;
        }
        // record[0] = 3表示第一行的皇后放在了第四列，依次类推
        int[] record = new int[n];

        return process1(0, record, n);
    }

    /**
     * 目前来到第i行，表示record[0, ..., i-1]行的皇后一定不共行，不共列，不在一条斜线上
     * @param i 目前是第i行
     * @param record 表示record[0...i-1]行的皇后放的位置
     * @param n 一共有n行，n个皇后
     * @return 一共有多少种摆放方式
     */
    public static int process1(int i, int[] record, int n) {
        if(i == n) {   // 终止行，表示所有n个皇后已经摆放完毕，返回1，表示一种摆放方式
            return 1;
        }

        int res = 0;
        // 遍历当前第i行的皇后放在第j列会不会和前 0, ..., i-1行的皇后共行共列共斜线
        // 对于第i行的皇后，尝试第i行的每一列
        for(int j = 0; j < n; j++) {
            if(isValid(record, i, j)) {
                record[i] = j;  // 第i行的皇后放在第j列，然后继续递归第i+1行
                res += process1(i + 1, record, n);
            }
        }

        return res;
    }

    /**
     * 检查第i行的皇后放在第j列会不会和record[0, ..., i-1]列的皇后冲突
     * 因为是深度优先，所以必然不可能共行，因此只需判断是不是共列或者共斜线
     * @param record 记录record[0, ..., i-1]个皇后的位置
     * @param i 第i行的皇后
     * @param j 第j列
     * @return 是否可以放在第j列
     */
    public static boolean isValid(int[] record, int i, int j) {
        for(int k = 0; k < i; k++) {
            // 共列: record[i] == j
            // 共斜线: record[i]
            if(record[k] == j || (Math.abs(j - record[k]) == Math.abs(i - k))) {
                return false;
            }
        }

        return true;
    }

    /**
     * N皇后问题位运算的方法，不要超过32皇后
     * 通过位运算加速常数时间的操作
     * 讲解在 p10，暴力递归那一集最后 大概2小时45分钟左右
     * @param n n个皇后
     * @return 摆放个数
     */
    public static int NQueen2(int n) {
        if(n < 1 || n > 32) {
            return 0;
        }

        // 刚开始返回一个前n位都是1的数，
        // 例如n=4，则返回000...001111
        int limit = n == 32 ? -1 : (1 << n) - 1;

        return process2(limit, 0, 0, 0);
    }

    /**
     * 整个过程都是 1表示不能放  0表示可以放
     * @param limit limit作为限制只在前n个位置进行判断，因此在整个递归过程limit都保持不变
     * @param colLimit 列限制
     * @param leftLimit 左斜线限制
     * @param rightLimit 右斜线限制
     * @return  个数
     */
    public static int process2(int limit, int colLimit, int leftLimit, int rightLimit) {
        // base case,当colLimit == limit的时候，
        // 说明colLimit的前n为都是1，所有皇后都摆放了，返回1
        if (colLimit == limit) {
            return 1;
        }

        // 现在要获取可以摆放皇后的位置
        // 例如colLimit = 00100，那么leftLimit = 01000，rightLimit = 00010
        // colLimit | leftLimit | rightLimit = 01110
        // ~之后表示10001，再和limit&之后就等于10001
        // 表示所有候选皇后的位置都在pos上
        int pos = limit & (~(colLimit | leftLimit | rightLimit));
        int rightOne = 0;
        int res = 0;
        while (pos != 0) {
            // 先得到pos最右侧的1位置，然后减去这个1，暂时作为侯选位置，继续往下递归
            rightOne = pos & (~pos + 1);  // 得到pos最右侧的1的位置
            pos = pos - rightOne;
            // 在最右侧的1位置放了皇后，所以下面的limit就变成了 limit | rightOne，这样最右侧的位置就变成了1
            // 在最右侧的1位置放了皇后，所以leftLimit就变成了 limit | rightOne 左移一位
            // 同理，rightLimit应该是 limit | rightOne 右移一位
            // >>> 是无符号右移，无论正数负数，高位都补0
            // >> 右移，正数高位补0，负数补1
            res += process2(limit, colLimit | rightOne,
                    (leftLimit | rightOne) << 1, (rightLimit | rightOne) >>> 1);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.print(NQueen1(4));
    }
}
