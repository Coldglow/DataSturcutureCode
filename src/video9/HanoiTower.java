package video9;

public class HanoiTower {
    public static void hanoi(int n) {
        if(n > 0) {
            move(n, "左", "右", "中");
        }
    }

    /**
     * 就是递归抽象的问题，将所有杆子抽象成了三类，
     * start是移动前所在的杆子
     * end是要移动到的杆子
     * others是除此之外其他所有的杆子
     * 这样考虑：
     * 无论前面在怎么移动，有效的最后一步永远都是将第i个圆盘从from移动到end
     * 但是因为第i个盘子上面右1...i-1个盘子，无法直接移动
     * 所以得先将第1...i-1个盘子先移动到others，再把第i个盘子从start移动到end，再把第1..。i-1个盘子移动到end
     * 这样第i个盘子就摆好了，不用管第i个盘子了
     * 所以先是一个递归调用前i-1个盘子，将其从start移动到others
     * 然后移动第i个盘子从start到end
     * 然后再将第1...i-1个盘子从others移动到end
     * 流程结束
     * @param i  第几层汉诺塔
     * @param start 移动前所在的杆子
     * @param end 要到达的杆子
     * @param others 除了from和to之外的所有杆子
     */
    public static void move(int i, String start, String end, String others) {
        // 先确定base case,当只剩1层的时候，随便怎么移，这时只需要将这层从start移动到end即可
        if(i == 1) {
            System.out.println("Move 1 from " + start + "to " + end);
        } else {
            // 如果不是第1层，就需要把第i-1层从from移动到others
            // 这时候start不变，但是end变成了others
            move(i -1, start, others, end);
            // 将第i个盘子从start移动到end
            System.out.println("Move " + i + "from " + start + " to " + end);
            // 将前i-1个盘子移动到end即可
            // 这是后原先的others成了start，end还是end，原先的start变成了others
            move(i-1, others, end, start);
        }
    }
}
