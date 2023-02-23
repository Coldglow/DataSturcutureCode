/**
 * 给一个矩阵，只有0和1两个值，如果一个1的上下左右任意几个位置是1，这些1就是连在一起就是一个岛
 * 返回矩阵中有几个岛
 */
package video11;

public class IslandNumber {
    /**
     * 思路就是遍历整个矩阵，遇到不是1的跳过，遇到一个1就把上下左右的1都变成2，俗称感染
     * 感染了几次就说明有几个岛， 时间复杂度O(N*M)
     * @param arr 矩阵
     * @return 岛的数量
     */
    public static int islandNum(int[][] arr) {
        int res = 0;
        int row = arr.length;
        int col = arr[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(arr[i][j] == 1) {
                    res++;
                    infect(arr, i, j, row, col);
                }
            }
        }
        return res;
    }

    public static void infect(int[][] arr, int curRow, int curCol, int row, int col) {
        // 如果行列越界或者不为1，直接返回
        // 注意这里是行和列是数组的长和宽，不是下标，所以是大于等于就要返回
        // 等于的时候就已经越界了
        if(curRow < 0 || curRow >= row || curCol < 0 || curCol >= col || arr[curRow][curCol] != 1) {
            return;
        }

        arr[curRow][curCol] = 2;
        // 继续感染
        infect(arr, curRow + 1, curCol, row, col);  // 感染下方
        infect(arr, curRow - 1, curCol, row, col);  // 感染上方
        infect(arr, curRow, curCol + 1, row, col);  // 感染右侧
        infect(arr, curRow, curCol - 1, row, col);  // 感染左侧
    }

    public static void main(String[] args) {
        int[][] arr = {{0,1,1,0,0,1},
                       {1,1,0,1,0,1},
                       {1,1,0,0,1,1}};
        System.out.println(islandNum(arr));
    }
}
