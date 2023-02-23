/**
 * O(N)的时间复杂度找到任意字符串的最长回文子串
 * Marcher算法中记录的 回文半径数组 可以用于多种场合
 */
package video12;

import java.util.Arrays;

public class Marcher {
    /**
     * @param str 给定的要求最长回文串的字符串
     * @return 以字符数组的形式返回其manacher形式，例如给定 abv，返回res['#', 'a', '#', 'b', '#', 'v', '#']
     */
    public static char[] marcherString(String str) {
        char[] charArr = str.toCharArray();
        // 申请一个长度为2倍的marcher数组
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for(int i = 0; i < res.length; i++) {
            // 通过i & 1 == 0 ?，可以将字符数组变成 # arr[0] # arr[1] # arr[2] ... # 的形式
            // i & 1 是精髓，学到了
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }

        return res;
    }

    /**
     *
     * 分成两种大情况：
     * 1. R >= i, 即当前位置i不在右边界之内，那么只能暴力向两侧延申。[ L C R i ]
     * 2. i < R, 即i位于R之内，又分为三种情况：
     *  a. i关于C的对称点i'的最长回文直径都位于L到R，即 [  L ( i' )  C   i   R]，此时i的最长回文直径就等于i'的最长回文直径
     *  b. i‘的直径有一部分不在LR之间， [  ( L  i'  )  C   i   R ]  此时i的最长直径就是R - i
     *  c. i'的左侧正好位于L，那么至少在i位置，i ~ R-1 区域一定是回文的，只需要从R位置开始比较即可。
     *  如果得到的结果长度是偶数，那么返回中间 # 的下标  除以2之后就是中间靠右的位置的下标
     * @param str str
     * @return 返回最长的回文子串的长度
     */
    public static int maxLcpLength(String str) {
        // 单独一个字符长度算1，所以这里是==0的时候返回0，不是<=1的时候返回
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] mStr = marcherString(str);  // 123 -> #1#2#3#
        int R = -1;  // 目前最长回文串的右边界位置,最长有效回文串的右区域是 C ~ R-1
        int C = -1;  // 目前最长回文串的中心位置
        int[] pArr = new int[mStr.length];  //  回文半径数组
        int max = Integer.MIN_VALUE;  //  全局最小值

        for(int i = 0; i < mStr.length; i++) {
            // 先将一定回文的区域的长度赋值给pArr[i],即
            // 对于上述的第一种需要暴力延申的大情况，至少回文的长度是1，即这个字符本身
            // 对于第二种大情况来说，第一种小情况下一定回文的区域的长度就是pArr[i'], i' = 2 * C - i
            // 对于后两种小情况，一定回文的区域的长度是 R - i
            // 所以对与第二种大情况，总结起来一定回文的区域就是二者较小值
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;

            // 以i为中心，pArr[i]为半径往外扩，看str[i + pArr[i]] 和 str[i - pArr[i]]是否相等
            // 虽然只有第一种大情况和第三种小情况需要往外扩，但是这样写的话比较简洁
            // 第一二种小情况尝试一次后即返回，也是没扩
            while(i + pArr[i] < mStr.length && i - pArr[i] > -1) {  // 如果不越界
                // 尝试扩
                if(mStr[i + pArr[i] ] == mStr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            // while结束后，i位置的回文半径数组即更新完毕
            // 更新R和C
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            // 记录最大值
            max = Math.max(max, pArr[i]);
        }
        System.out.println("C: " + C);
        // 因为pArr记录的是marcher字符串的最长回文半径
        // 原字符串的最长回文字串长度 = marcher字符串的最长回文半径 - 1
        return max - 1;
    }

    public static void main(String[] args) {
        System.out.println(maxLcpLength("babad"));
    }
}
