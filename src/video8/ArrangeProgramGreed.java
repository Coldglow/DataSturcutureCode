/**
 * 贪心，要找到局部最优到全局最优的方案
 * 安排会议问题，在一定的时间段内安排尽可能多的会议
 * 贪心策略：按照结束时间将所有会议从小到大排序，然后依次安排
 */
package video8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ArrangeProgramGreed {

    /**
     * 会议的结构体，start和end分别表示
     */
    public static class Program {
        int start;
        int end;

        Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * Program的比较器，按照结束时间从小大大排序
     * 记住从小到大排序是第一个参数减去第二个参数，从大到小是第二个参数减去第一个参数
     */
    public static class programComparator implements Comparator<Program> {
        public int compare(Program p1, Program p2) {
            return p1.end - p2.end;
        }
    }

    /**
     * @param programs  给出的所有会议
     * @param timePoint 当前时间
     * @return 返回安排好的会议，当然也可以返回其他信息，比如可以安排的会议数量
     */
    public static ArrayList<Program> arrange(Program[] programs, int timePoint) {
        if(programs.length == 0) {
            return null;
        }

        // 先对programs数组按照结束时间排序
        Arrays.sort(programs, new programComparator());

        ArrayList<Program> arrangedPrograms = new ArrayList<>();
        // 依次遍历所有会议
        for (Program program : programs) {
            // 注意会议是按照结束时间排序的，但是选取会议的时候要按照开始时间
            // 如果下一个会议的开始时间比当前时间迟，就选择这个会议，并且将当前时间设置为下一个会议的结束时间
            if (program.start >= timePoint) {
                arrangedPrograms.add(program);
                timePoint = program.end;
            }
        }

        return arrangedPrograms;
    }
}
