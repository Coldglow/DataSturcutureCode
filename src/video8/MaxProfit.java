/**
 * 做项目获得最大资金
 */
package video8;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxProfit {
    public static class Project {
        int cost;
        int profit;

        Project(int c, int p) {
            this.cost = c;
            this.profit = p;
        }
    }

    // 因为是自定义的结构，所以需要自己写比较器
    // 按照成本构建的小根堆
    public static class MinCostComparator implements Comparator<Project> {
        public int compare(Project p1, Project p2) {
            return p1.cost - p2.cost;
        }
    }

    // 按照利润构建的大根堆
    public static class MaxProfitComparator implements Comparator<Project> {
        public int compare(Project p1, Project p2) {
            return p2.profit - p1.profit;
        }
    }

    /**
     * @param K 最多做K个项目
     * @param W 初始资金
     * @param costs 数组，costs[0]表示第一个项目的成本
     * @param profits 数组，profits[0]表示第一个项目的利润
     * @return  可以获得的最大资金
     */
    public static int getMaxProfit(int K, int W, int[] costs, int[] profits) {
        // 首先创建两个堆
        PriorityQueue<Project> minCostQueue = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Project> maxProfitQueue = new PriorityQueue<>(new MaxProfitComparator());

        // 构建小根堆
        for(int i = 0; i < costs.length; i++) {
            minCostQueue.add(new Project(costs[i], profits[i]));
        }

        // 最多做K个项目，所以循环K次
        for(int i = 0; i < K; i++) {
            // 解锁cost < W的项目,进入大根堆
            while(!minCostQueue.isEmpty() && minCostQueue.peek().cost <= W) {
                maxProfitQueue.add(minCostQueue.poll());
            }

            // 可能会出现K还没达到，但是资金不足的情况，直接返回，此时大根堆必然为空（A）
            // 刚开始这里写的条件是 if (!maxProfitQueue.isEmpty() && maxProfitQueue.peek().cost > W)
            // 是错误的，因为出现在大根堆中的项目必然是cost <= W的项目，否则就不会出现在大根堆中
            // 如果出现A这种情况，说明大根堆必然为空
            if(maxProfitQueue.isEmpty()) {
                return W;
            }
            // 因为是利润，而最终返回的是资金，所以是加等于
            W += maxProfitQueue.poll().profit;
        }

        return W;
    }
}
