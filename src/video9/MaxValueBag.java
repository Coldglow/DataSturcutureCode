/**
 * 背包问题，在一定的重量限制下，获取最大的价值
 */
package video9;

public class MaxValueBag {
    /**
     * 从i位置开始的货物自由选择，要和不要，在不超过最大重量的情况下所能获取的最大价值
     * @param weights 重量数组
     * @param values 价值数组
     * @param curWeight 当前的重量
     * @param i i位置
     * @param maxWeight 最大重量
     * @return 最大价值
     */
    public static int getMaxValue(int[] weights, int[] values,
                                  int curWeight, int i, int maxWeight) {
        // 如果当前重量已经大于最大重量，那么不可能再获取价值，返回0
        if(curWeight > maxWeight) {
            return 0;
        }
        // 如果下标已经等于数组长度，说明已经越界了，自然也没有货物可以获取了，也返回0
        if(i == weights.length) {
            return 0;
        }

        return Math.max(getMaxValue(weights, values, curWeight, i + 1, maxWeight), // 不选则i位置的货物
                values[i] + getMaxValue(weights, values, curWeight + weights[i], i + 1, maxWeight)); // 选择i位置的货物，加上价值和重量，i+1
    }
}
