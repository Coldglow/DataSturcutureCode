/**
 * 打印字符串的所有子序列，包括空字符串
 */
package video9;

import java.util.Arrays;

public class AllSubStrings {
    public static void printSubStings(String str) {
        char[] chars = str.toCharArray();
        // 从下标0开始
        process(chars, 0);
    }

    /**
     * 思路就是从第一个字符开始选择要和不要两条路，对于每一次选择，下一个字符串也有要和不要两条路
     * 返回所有选择即可
     * @param chars 记录所有字符的数组
     * @param i 下标
     */
    public static void process(char[] chars, int i) {
        // base case
        if(i == chars.length) {
            // String.valueOf方法，将字符数组拼接成一个字符串返回
            System.out.println(String.valueOf(chars));
            return;
        }
        // 要下标为i的字符，继续递归
        process(chars, i + 1);
        // 不要下标为i的字符，将其置为0，但是递归结束后要恢复，所以需要一个变量来保存
        char temp = chars[i];
        chars[i] = 0;
        // 不要下标为i的字符的路
        process(chars, i + 1);
        chars[i] = temp;
    }
}
