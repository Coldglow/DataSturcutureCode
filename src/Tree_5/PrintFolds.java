/**
 * 一张纸对着n次，从上到下打印折痕的方向，默认最中间折痕方向是down(凹的)
 */
package Tree_5;

public class PrintFolds {
    public static void printFolds(int n) {
        printProcess(1, n, true);
    }

    /**
     * 每次对折标上记号就会发现，每次对折都会在上次产生的折痕的上方产生一个down折痕，在下方产生一个up折痕
     * 所以整体结构就是一个二叉树，每一层表示第几次折叠，
     * 因为要求从上到下打印，所以就是二叉树的先序遍历
     * @param i 第几次折叠
     * @param n 总的折叠次数
     * @param down 方向，down表示凹的，up表示凸的
     */
    public static void printProcess(int i, int n, boolean down) {
        // base case，如果对折次数大于n，直接返回什么也不做
        if (i > n) {
            return;
        }
        // 因为先序，所以先一直向左子树遍历，因为任何一个节点的左孩子都是凹的，所以第三个参数是true
        printProcess(i + 1, n, true);
        // 第二次来到当前节点的时候，打印
        System.out.print(down? "凹" : "凸");
        // 对右子树遍历，因为任何一个节点的左孩子都是凸的，所以第三个参数是false
        printProcess(i + 1, n, false);
    }
}
