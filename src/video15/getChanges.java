/**
 * 给定一个只包含正数，且没有重复数字的数组arr，表示货币的面额。现在给一个数num，要求返回用数组中的面额能够拆解num的方法数。
 * 例如num=15，可以是 5 5 5， 10 5， 10 2 3， 5 5 2 3一共4种，返回4.
 */
package video15;

import java.lang.reflect.Array;
import java.util.Arrays;

public class getChanges {
    public static int change1(int[] arr, int aim) {
        return change(arr, 0, aim);
    }

    /**
     * 从左到右暴力尝试，记住这个思想，arr[index]表示arr[index...]的数可以使用
     * @param arr  数组
     * @param index  表示arr[index...]的面额都可以用
     * @param rest  还需要找多少钱
     * @return   num
     */
    public static int change(int[] arr, int index, int rest) {
        // 如果面额已经用完了，但是还有rest大小的钱没有找，说明现在的路径不对，返回0
        // 如果rest == 0，说明当前路径对  返回1
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        // 从一张开始尝试，一直到zhang * arr[index] <= 需要找的钱
        for (int zhang = 1; zhang * arr[index] <= rest; zhang++) {
            ways += change(arr, index + 1, rest - zhang * arr[index]);
        }
        return ways;
    }

    /**
     * 只需要将递归行为改成修改对应位置的值就变成了记忆化搜索方式
     * 递归函数有两个变化的参数，因此dp数组是二维的
     * 第一维表示index，第二维表示还需要找零的钱数rest
     * dp[0][20]表示从第一个面额开始，找20块钱的方法数
     *
     * @param arr  arr
     * @param aim aim
     * @return 返回dp[0][aim]位置的数，即从第一张面额开始找零aim大小的钱的方法数
     */
    public static int change2(int[] arr, int aim) {
        if (null == arr || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        // 行是每一个面额用了多少张
        int[][] dp = new int[N + 1][aim + 1];
        // 从base case看出dp[N][0]==1 其余dp[N][] == 0
        dp[N][0] = 1;
        // 两层for循环填写dp数组
        // 因为先填写的是index = N的情况，所以index要逆着来填写
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 填写dp数组的过程就是改写递归函数，只需要在返回值之前修改对应位置的值再返回
                int ways = 0;
                for (int zhang = 1; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index + 1][rest - zhang * arr[index]];
                }
                dp[index][rest] = ways;
            }
        }

        return dp[0][aim];
    }

    /**
     * 根据关系的观察，可以看出dp[index][rest] = dp[index + 1][rest] + dp[index][rest - dp[index]]
     * 所以第三个for循环没有必要
     * @param arr  a
     * @param aim a
     * @return a
     */
    public static int change3(int[] arr, int aim) {
        if (null == arr || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        // 行是每一个面额用了多少张
        int[][] dp = new int[N + 1][aim + 1];
        // 从base case看出dp[N][0]==1 其余dp[N][] == 0
        dp[N][0] = 1;
        // 两层for循环填写dp数组
        // 因为先填写的是index = N的情况，所以index要逆着来填写
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];  // 先等于自己下方的值
                // 如果左侧还有值，没有越界，再加上左侧的值
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }

        return dp[0][aim];
    }

    // for test
    public static int[] getRandomArray(int len, int max) {
        // 生成特定长度
        int[] arr = new int[(int)(Math.random() * len) + 1];
        // 再随机填充数
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * max) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.print("...");
        int len = 10;
        int max = 10;
        int testTime = 10000;   // 测试轮数
        for (int i = 0; i < testTime; i++) {
            int[] arr = getRandomArray(len, max);
            int aim = (int)(Math.random() * max * 5) + max;
            if (change1(arr, aim) != change2(arr, aim)) {
                System.out.println(Arrays.toString(arr));
                System.out.println("aim: " + aim);
                break;
            }
        }

    }
}
