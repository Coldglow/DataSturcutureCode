/**
 * 网格结构的层次遍历
 * 类似二叉树的层次遍历
 * 也是用队列
 */
package GridTraverse;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    /**
     * 网格的层次遍历是指，从 [ row, col ]位置出发，分别访问上下左右四个位置
     * 然后再访问四个位置的上下左右，访问过就不再访问
     * 这样就是以 [ row, col ]为中心，一层一层的向外扩展
     * 而不是指从 [ 0, 0 ]出发一层一层的访问
     * 整体结构和二叉树的层次遍历相同
     * @param grid  f
     * @param row f
     * @param col f
     */
    public void bfs(int[][] grid, int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {row, col});
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++){
                int[] cur = queue.poll();
                row = cur[0];
                col = cur[1];

                if (row > 0 && grid[row - 1][col] == 0) {
                    grid[row][col] = 2;
                    queue.add(new int[] {row - 1, col});
                }

                if (row < n - 1 && grid[row + 1][col] == 0) {
                    grid[row][col] = 2;
                    queue.add(new int[] {row + 1, col});
                }

                if (col > 0 && grid[row][col - 1] == 0) {
                    grid[row][col] = 2;
                    queue.add(new int[] {row, col - 1});
                }

                if (col < n - 1 && grid[row][col + 1] == 0) {
                    grid[row][col] = 2;
                    queue.add(new int[] {row, col + 1});
                }
            }
        }
    }
}
