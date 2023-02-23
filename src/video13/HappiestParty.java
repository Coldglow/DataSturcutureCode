package video13;

import java.util.List;

public class HappiestParty {
    public static class Employee {
        public int happy;
        public List<Employee> subordinates;
    }

    /**
     * 每个数返回自己来的最大快乐值和不来的最大快乐值
     */
    public static class HappyInfo {
        public int laiHappy;
        public int buHappy;
        HappyInfo(int lai, int bu) {
            laiHappy = lai;
            buHappy = bu;
        }
    }

    public static int getHappiestParty(Employee Boss) {
        HappyInfo res = process(Boss);
        return Math.max(res.buHappy, res.laiHappy);
    }

    public static HappyInfo process(Employee employee) {
        // 如果是基层员工，来的话就是本身的happy，不来就是0
        if (employee.subordinates == null) {
            return new HappyInfo(employee.happy, 0);
        }

        int bulaiSum = 0;   // employee来，整棵树的最大收益
        int laiSum = employee.happy;   // employee不来，整棵树的最大收益
        for (Employee e : employee.subordinates) {
            HappyInfo happy = process(e);
            // employee不来，所以e可以来，也可以不来，选择来和不来的较大值
            bulaiSum += Math.max(happy.buHappy, happy.laiHappy);
            // employee不来，所以e只能不来
            laiSum += happy.buHappy;
        }

        return new HappyInfo(laiSum, bulaiSum);
    }
}
