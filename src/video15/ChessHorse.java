/**
 * 假设一个10*9的象棋棋盘，左下角（0，0）位置是马的起始位置，在一定要走k步的条件下要走到（x，y）位置，问一共有多少种走法。
 * 三维的dp
 */
package video15;

public class ChessHorse {

    public static int horseWalk(int x, int y, int k) {
        return walk(x, y, k);
    }

    /**
     * 首先暴力递归，逆着走，从（x，y）走到（0，0）位置
     * @param x 结束的x
     * @param y 结束的y
     * @param step 还剩余的步数
     * @return 步数
     */
    public static int walk(int x, int y, int step) {
        // base case，越界情况
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        // 走到的情况，在剩余0步的情况下走到了原点
        if (step == 0) {
            return (x == 0 && y == 0) ? 1 : 0;
        }
        // 其余所有情况
        return walk(x - 1, y - 2, step - 1) +
                walk(x - 2, y - 1, step - 1) +
                walk(x + 1, y - 2, step - 1) +
                walk(x + 2, y - 1, step - 1) +
                walk(x + 2, y + 1, step - 1) +
                walk(x + 1, y + 2, step - 1) +
                walk(x - 1, y + 2, step - 1) +
                walk(x - 2, y + 1, step - 1);
    }

    /**
     * 根据递归结构可以看出需要一个三维的数组记录可能情况
     * arr[x][y][step]，大小分别为 10 9 k + 1
     * @param x  x
     * @param y  y
     * @param k  k
     * @return  d
     */
    public static int horseWalkDP(int x, int y, int k) {
        return walkDP(x, y, k);
    }

    /**
     * 把三位数组想象成一个立方体，地面是x y  高是step  底面是step==0   顶面是 step == k
     * 根据递归结构的base 看出来
     * 在step==0那一层，只有 （0，0） == 1，其余都是0
     * 还可以推出每一层的值只和下一层的值有关，和本层的值无关
     * @param i x
     * @param j y
     * @param k step
     * @return ff
     */
    public static int walkDP(int i, int j, int k) {

        if (i < 0 || i > 9 || j < 0 || j > 8 || k < 0) {
            return 0;
        }

        // 不用初始化，这道题的边界条件就是0，可以直接用
        int[][][] dp = new int[10][9][k + 1];
        // base case
        dp[0][0][0] = 1;

        // for循环的顺序很关键
        // 并且因为step = 0是base已经确定的，所以从step等于1的时候开始
        for (int step = 1; step <= k; step++) {   // 第step层
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    // 这里的顺序就无所谓了
                    dp[x][y][step] += getValue(x - 1, y + 2, step - 1, dp);
                    dp[x][y][step] += getValue(x - 2, y + 1, step - 1, dp);
                    dp[x][y][step] += getValue(x - 2, y - 1, step - 1, dp);
                    dp[x][y][step] += getValue(x - 1, y - 2, step - 1, dp);
                    dp[x][y][step] += getValue(x + 1, y - 2, step - 1, dp);
                    dp[x][y][step] += getValue(x + 2, y - 1, step - 1, dp);
                    dp[x][y][step] += getValue(x + 2, y + 1, step - 1, dp);
                    dp[x][y][step] += getValue(x + 1, y + 2, step - 1, dp);
                }
            }
        }
        return dp[i][j][k];
    }

    public static int getValue(int x, int y, int step, int[][][] dp) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][step];
    }

    public static void main(String[] args) {
        System.out.println(horseWalkDP(4, 3, 3));
    }
}
