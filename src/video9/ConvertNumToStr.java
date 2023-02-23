/**
 * 暴力递归/暴力递归
 *从左到右尝试，每次来到i位置：
 * 	1. 如果i位置是0，直接返回0，因为0没有对应的字符，说明0~i-1位置的转化是无效的。
 * 	2. 如果i位置是1，那么有两种选择：
 * 		a. i位置的单独转化，i+1之后作为整体去转化
 * 		b. i位置和i+1位置一起转化，i+2之后的作为整体去转化
 * 	3. 如果i位置是2
 * 		a. 如果i+1位置的值大于0小于7，那么和1一样有两种转化可能
 * 		b. 否则i只能单独转化
 * 如果i位置大于2，那么只能i单独转化，i+1作为整体去转化。
 */
package video9;

public class ConvertNumToStr {
    /**
     *
     * @param chars 字符串转化成字符数组
     * @param i chars[i]表示第i位置的字符
     * @return 返回有多少种转化的结果
     */
    public static int convert(char[] chars, int i) {
        if(i == chars.length) {
            return 1;
        }

        if(chars[i] == '0') {
            return 0;
        }

        if(chars[i] == '1') {
            int res = convert(chars, i);  // i位置的字符单独转化，后序有多少种方法
            // i和i+1位置的单独转化，后序有多少种方法
            if(i+1 < chars.length) {
                res += convert(chars, i + 2);
            }
            return res;
        }

        if(chars[i] == '2') {
            int res = convert(chars, i);  // i位置的字符单独转化，后序有多少种方法
            if(i+1 < chars.length && chars[i] < '7' && chars[i] > '0') {
                res += convert(chars, i + 2);
            }
            return res;
        }

        // 如果大于2，只有i位置单独转化这种情况，直接返回
        return convert(chars, i + 1);
    }
}
