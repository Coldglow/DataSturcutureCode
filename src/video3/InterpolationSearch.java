package video3;

/**
 * @author David Wong
 * @date 02/07/2023 18:59
 * Scanner in = new Scanner(System.in);
 */
public class InterpolationSearch {
    public int interpolationSearch(int[] arr, int key) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int index = (key - arr[l]) * (l - r) / (arr[r] - arr[l]);
            if (arr[index] == key) {
                return index;
            } else if (arr[index] > key) {
                r = index - 1;
            } else {
                l = index + 1;
            }
        }
        return -1;
    }
}
