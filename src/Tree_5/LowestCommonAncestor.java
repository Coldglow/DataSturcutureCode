/**
 * 寻找两个节点的最低公共祖先
 * 使用树形DP思路来求解：
 * 主要思路就是将N1和N2都不在的路径返回值都设为null，然后遇到N1返回N1，遇到N2返回N2，如果都遇到，则返回节点Node自身，
 * 此时Node必然是N1或者N2中位置比较高的那个
 * 1. base case：如果Node为空或者等于N1或者等于N2，都返回Node自身
 * 2. 左右子树返回的信息：如果该节点往下都不存在N1或者N2，则返回null，否则就一直返回N1或N2.
 * 3. 在汇聚节点（即最低公共祖先）处一定会遇到左右节点都不为空的情况，此时左右子树都不为空，并且分别返回N1，N2，此时返回Node节点
 *    自身，表示最低公共祖先。再往上的话因为其他树枝必然不存在N1或者N2，都返回null，此节点返回值不为null，所以最终到根节点的时
 *    侯必然会返回那个Node
 */
package Tree_5;

import Tree_5.PreInPosTraversal.TreeNode;

public class LowestCommonAncestor {
    public static TreeNode lowestCommonAncestor(TreeNode head, TreeNode N1, TreeNode N2) {
        if (head == null || head == N1 || head == N2) {
            return head;
        }

        TreeNode left = lowestCommonAncestor(head.left, N1, N2);
        TreeNode right = lowestCommonAncestor(head.right, N1, N2);

        if (left != null && right != null) {
            return head;
        }

        return left != null ? left : right;
    }
}
