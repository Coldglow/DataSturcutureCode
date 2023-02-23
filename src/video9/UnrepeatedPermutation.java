/**
 * 打印字符串所有不重复的排列组合
 */
package video9;

import java.util.ArrayList;

public class UnrepeatedPermutation {
    public static ArrayList<String> printPermutation(String str) {
        char[] chs = str.toCharArray();
        ArrayList<String> res = new ArrayList<>();
        process(chs, 0, res);

        return res;
    }

    /**
     * chars[0...i-1]表示暂时选定的字符串   chars[i ...]，与i位置的字符交换，继续遍历
     *
     * 其实没太懂
     *
     * @param chars 保存字符串的数组
     * @param i 此时在下标为i的位置枚举所有可能
     * @param res 保存结果的链表
     */
    public static void process(char[] chars, int i, ArrayList<String> res) {
        if(i == chars.length) {
            res.add(String.valueOf(chars));
        }
        boolean[] visit = new boolean[26];   // 默认字符串只包含小写字母
        for(int j = i; j < chars.length; j++) {
            if(!visit[chars[j] - 'a']) {   // 如果字符在j位置访问过，就不再将其放在j位置判断
                // 标记字符串在j位置访问过
                visit[chars[j] - 'a'] = true;
                swap(chars, i, j);  // 交换i位置和j位置的字符
                // 继续向后遍历
                process(chars, i + 1, res);
                // 将刚才交换的字符交换回来
                swap(chars, i, j);
            }
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
