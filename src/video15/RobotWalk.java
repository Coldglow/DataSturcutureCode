package video15;

public class RobotWalk {
    public static int robotWalk(int N, int S, int E, int K) {
        return walk(N, E, S, K);
    }

    /**
     *  暴力递归，时间复杂度O(2^K)，每次决策相当于二叉树，总高度是K
     * @param N  总共有多少个格子
     * @param E  结束格子的下标
     * @param cur 现在所在格子的下标，初始在start
     * @param rest 还剩多少步可以走，初始是K
     * @return 返回从start到end的所有可能的方法
     */
    public static int walk(int N, int E, int cur, int rest) {
        // base case
        // 如果步数走完了并且所在位置是E，返回1，否则返回0
        if (rest == 0) {
            return cur == E ? 1 : 0;
        }
        // 边界情况
        if (cur == 1) {
            return walk(N, E, 2, rest - 1);
        }
        if (cur == N) {
            return walk(N, E, N - 1, rest - 1);
        }
        return walk(N, E, cur - 1, rest - 1) + walk(N, E, cur + 1, rest - 1);
    }

    /**
     * 暴力递归改记忆化搜索，将可变参数记录到缓存中，用之前查一下就行
     * 时间复杂度是O(N*K)
     * @param N N
     * @param S S
     * @param E E
     * @param K K
     * @return FF
     */
    public static int robotWalkDP(int N, int S, int E, int K) {
        // 看上面的递归过程发现，rest永远是减小的，所以最大值就是K，开辟K+1大小的空间就行
        // N的范围也是确定的 1 ~ N，所以开N + 1就行
        int[][] dp = new int[N + 1][K + 1];
        // 依据题目初始化，因为这道题涉及到0，所以将缓存初始化为-1
        // 如果0可以直接用就不用初始化
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < K + 1; j++) {
                dp[i][j] = -1;
            }
        }
        // 递归过程中把缓存加上就行
        return walk2(N, E, S, K, dp);
    }

    /**
     * 过程和上面的暴力递归一毛一样
     * 只不过是在刚开始加了查询，然后在每次return之前都修改dp值而已
     * @param N   N
     * @param E E
     * @param cur CUR
     * @param rest RES
     * @param dp DP
     * @return DP
     */
    public static int walk2(int N, int E, int cur, int rest, int[][] dp) {

        // 先查
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }

        // 缓存没命中，这时每次递归函数返回前都要记录

        /*
        if (rest == 0) {
            dp[cur][rest] = cur == E ? 1 : 0;
            return dp[cur][rest];
        }
        // 边界情况
        if (cur == 1) {
            dp[cur][rest] = walk(N, E, 2, rest - 1)
            return dp[cur][rest];
        }
        if (cur == N) {
            dp[cur][rest] = walk(N, E, N - 1, rest - 1);
            return dp[cur][rest];
        }
        return dp[cur][rest];
        */

        // 整理一下
        if (rest == 0) {
            dp[cur][rest] = cur == E ? 1 : 0;
        } else if (cur == 1) {
            dp[cur][rest] = walk2(N, E, 2, rest - 1, dp);
        } else if (cur == N) {
            dp[cur][rest] = walk2(N, E, N - 1, rest - 1, dp);
        } else {
            dp[cur][rest] = walk2(N, E, cur - 1, rest - 1, dp) + walk2(N, E, cur + 1, rest - 1, dp);
        }
        return dp[cur][rest];
    }

    /**
     * 根据递归结构得到表中值的规律
     * @param N N
     * @param S S
     * @param E E
     * @param K K
     * @return return
     */
    public static int dpWay(int N, int S, int E, int K) {
        // dp[0][]不用，因为cur不可能为1
        // java数组默认初始化为0
        int[][] dp = new int[K + 1][N + 1];
        // 首先直接看出dp[E][0] == 1
        dp[0][E] = 1;

        for (int rest = 1; rest < K+1; rest++) {
            for (int cur = 1; cur < N+1; cur++) {
                if (cur == 1) {
                    dp[rest][cur] = dp[rest - 1][2];
                } else if (cur == 5) {
                    dp[rest][cur] = dp[rest - 1][4];
                } else {
                    dp[rest][cur] = dp[rest - 1][cur - 1] + dp[rest - 1][cur + 1];
                }
            }
        }

        return dp[K][S];
    }

    public static void main(String[] args) {
        System.out.println(dpWay(5, 2, 4, 4));
    }
}
