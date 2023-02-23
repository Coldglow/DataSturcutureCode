/**
 * 求岛屿的最大面积
 */
package GridTraverse;

public class MaxIsland {
    /**
     *
     * @param grid   f
     * @return ff
     */
    public int getMaxIsland(int[][] grid) {
        int res = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                int area = getArea(grid, row, col);
                res = Math.max(res, area);
            }
        }
        return res;
    }

    public int getArea(int[][] grid, int row, int col) {
        if (!(row > -1 && row < grid.length && col > -1 && col < grid[0].length)) {
            return 0;
        }

        if (grid[row][col] != 1) {
            return 0;
        }
        grid[row][col] = 2;
        // 面积加一
        return 1 + getArea(grid, row - 1, col) +
                getArea(grid, row + 1, col) +
                getArea(grid, row, col - 1) +
                getArea(grid, row, col + 1);
    }

}
