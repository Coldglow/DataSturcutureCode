package video1;

public class GetLargerOrEqual {
    // 二分，找到大于等于某个数最左侧的位置
    // 和普通二分不同的地方在于，一定要找到L = R的时候才停止
    public void getLargerOrEqual(int[] nums) {

    }

    public int divide(int[] nums, int L, int R, int target) {
        if (L < R) {
            // 不直接写(L + R) / 2  防止（L + R）溢出
            int mid = L + (R - L) >> 1;
            if (nums[mid] >= target) {
                divide(nums, mid + 1, R, target);
            } else {
                divide(nums, L, mid - 1, target);
            }
        }
        return L;
    }
}
