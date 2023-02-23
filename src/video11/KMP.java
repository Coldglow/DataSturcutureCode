/**
 * KMP算法，最长公共前缀后缀思想
 * 和Java中的indexOf函数返回结果相同，都返回子字符串第一次匹配的位置
 */
package video11;

import java.util.Arrays;

public class KMP {
    /**
     * 算法思路：
     * 初始化一个next数组，next[i]表示str2的最长前缀和后缀相等的长度。
     * 例如 abbtabb, next[6] = 3, next[0]认为规定是-1，next[1]认为规定是0。
     * 维护两个指针i1和i2，分别表示str1和str2目前的比较位置。
     * 	1. 如果str1[i1] == str2[i2]，i1和i2都++
     * 	2. 如果next[i2] == -1，说明比较str2的位置已经移动到开头了，str[i1]还是不等于str[0]，说明i1开头的str1的子字符串匹配不出str2，那么i1必须++，否则永远也匹配不出。
     *  3. 其他情况，str1[i1] ！= str2[i2]，请别i2也没有回到0位置，说明str2[i2]不等于0也不等于-1，还存在前缀和后缀相等的可能。i2跳转到str2前缀的下一个位置与str1[i1]比较。 i2 = next[i2]
     * @param str1 str1
     * @param str2 str2
     * @return str2在str1中的起始位置
     */
    public static int getIndex(String str1, String str2) {
        if(str1 == null || str2 == null) {
            return -1;
        }

        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        int i1 = 0;
        int i2 = 0;
        // 初始化str2的next数组
        int[] next = getNextArray(ch2);  // O(M)  M是str2的长度
        // O(N)复杂度
        while(i1 < ch1.length && i2 < ch2.length) {
            // 第一种正常状况，i1和i2位置的字符匹配
            // 二者都向右移动
            if(ch1[i1] == ch2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {
                // 二者位置所在字符不匹配，并且，i2已经移动到0位置仍然不能匹配
                // 说明str1从i1位置开始无法匹配到str2，i1必须右移一位重新匹配
                // 因为i2已经是0位置，所以i2不动
                // 因为人为规定next[0] = -1，next[1] = 0。所以当next[i2] = -1的时候，i2已经是0
                i1++;
            } else {
                // 二者位置所在字符不匹配, 但是，i2位置之前的str2仍然存在前缀后缀
                // 那么i2需要移动到前缀加1的位置
                // 因为前缀表示长度，而i2是下标，所以next[i2]就正好是前缀加1位置的下标，所以不需要手动加1
                i2 = next[i2];
            }
        }
        // 当i1越界的时候说明没有匹配到，返回-1
        // 当i2越界的时候说明匹配到了，返回i1 - i2，匹配的起始位置
        return i2 == ch2.length ? i1 - i2 : -1;
    }

    /**
     * next[i]的信息需要通过next[i-1]来得到，
     * 如果str[i-1] == str[next[i-1]], 也即如果i-1位置的字符等于i位置之前的前缀加1位置的字符
     * 那么i的最长前缀就是 next[i-1]++
     * @param str 子字符串str2
     * @return 返回str2的next数组，next[i] = 4 表示0 ~ i-1位置的str2字符串最长公共前缀后缀的长度是4
     */
    public static int[] getNextArray(char[] str) {
        if (str.length == 1) {
            return new int[] {-1};
        }

        // 人为规定next[01]分别为-1和0
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // 从下标为2的位置开始比较
        int cn = 0;  // cn既表示i位置的前缀长度，也表示前缀的下一位字符的下标
        while (i < str.length) {
            // 后两个条件的顺序可以改变
            // 因为next[i]表示str[0 ... i - 1] 范围的前缀长度
            // 所以应该用str[i - 1]和str[cn] 比较  而不是str[i]和str[cn]比较
            if(str[i - 1] == str[cn]) {
                next[i++] = ++cn;   // 如果匹配上，i位置的next值是i-1位置的前缀长度加1
            } else if (cn == 0) {
                next[i++] = 0;   // 如果没匹配上，并且cn已经等于0了，那么说明i的前缀长度也是0
            } else {
                // 注意这里不是next[i-1]  如果写成next[i-1]，就只会更新一次cn的值
                // 如果没匹配上，并且cn不等于0，更新cn, 跳转到str[cn]的前缀位置，i不变
                cn = next[cn];
            }
        }

        return next;
    }

    /**
     * 也是初始化next数组,和上面不一样的是next[i] 表示 [0 ... i]范围的最长前缀和后缀
     * 上面的表示的是[0 ... i - 1] 范围内的
     * @param str   str
     * @return  返回解决风纪扣风纪扣部分看不见你
     */
    public static int[] getNext2(String str) {
        int n = str.length();
        int[] next = new int[n];
        Arrays.fill(next, -1);
        for (int i = 1; i < n; ++i) {
            int j = next[i - 1];
            while (j != -1 && str.charAt(j + 1) != str.charAt(i)) {
                j =next[j];
            }
            if (str.charAt(j + 1) == str.charAt(i)) {
                next[i] = j + 1;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s1 = "mississippi";
        String s2 = "issip";
        System.out.println(getIndex(s1, s2));
    }
}
