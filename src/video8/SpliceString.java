/**
 * 拼接所有的字符串变成一个字符串，使得拼接好后的字符串整体字典序最低
 * 这个题使用的贪心策略，其实就是排序策略是：
 * 如果a+b的字典序小于b+a的字典序，就把a拼接在前面
 */
package video8;

import java.util.Arrays;
import java.util.Comparator;

public class SpliceString {
    /**
     * 字符串的比较器，按照字典二者拼接后字典序小的顺序排序
     * compareTo方法用于按照字典顺序比较两个字符串
     */
    public static class StringComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static StringBuilder spliceStrings(String[] strings) {
        if(strings == null || strings.length == 0) {
            return null;
        }
        // 按照比较器的顺序排序
        Arrays.sort(strings, new StringComparator());
        // 然后遍历数组依次拼接即可
        StringBuilder res = new StringBuilder();
        for(String str : strings) {
            res.append(str);
        }

        return res;
    }

    public static void main(String[] args) {
        String[] strings = new String[] {"bc", "gfs", "autdds"};
        System.out.print(spliceStrings(strings));
    }
}
