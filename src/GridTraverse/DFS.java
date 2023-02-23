/**
 * 网格结构的深度优先遍历框架以及相应修改
 */
package GridTraverse;

public class DFS {
    /**
     * 整体框架，根据题目做相应修改
     * 例如 岛屿数量问题
     * @param grid  区域
     * @param row  行
     * @param col  列
     */
    public void dfs(int[][] grid, int row, int col) {
        // 如果不在范围内
        if (!inArea(grid, row, col)) {
            return;
        }

        // 访问上下左右四个位置
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    public boolean inArea(int[][] grid, int row, int col) {
        return row > -1 && row < grid.length && col > -1 && col < grid[0].length;
    }

    public void island(int[][] grid, int row, int col) {
        // 如果不在范围内
        if (!inArea(grid, row, col)) {
            return;
        }

        // 如果不是1直接返回，避免重复遍历
        if (grid[row][col] != 1) {
            return;
        }
        // 是1，变成2，避免重复遍历
        grid[row][col] = 2;

        // 访问上下左右四个位置
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

}
