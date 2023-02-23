/**
 * 不懂
 */
package video9;

public class CardGame {
    public static int cardGame(int[] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }

        return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
    }

    public static int first(int[] arr, int left, int right) {
        if(left == right) {
            return arr[left];
        }
        return Math.max(arr[left] + second(arr, left + 1, right), arr[right] + second(arr, left, right - 1));
    }

    public static int second(int[] arr, int left, int right) {
        if(left == right) {
            return 0;
        }
        return Math.min(first(arr, left + 1, right), first(arr, left, right - 1));
    }

    public static void main(String[] args) {
        int[] arr = new int[] {10, 7, 4, 50, 21, 9};
        System.out.println(cardGame(arr));
    }
}
