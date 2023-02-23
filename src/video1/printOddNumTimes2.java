/**
 * 如果一个数组中只有两个数出现了奇数次，如何得到这两个数
 * 使用异或运算，^满足：A^0 = A, A^A=0的性质
 */
package video1;

public class printOddNumTimes2 {

    public static void printOddNumTimes2(int[] arr) {
        // 首先^所有的数得到eor=a^b
        int eor = 0;
        for(int cur : arr) {
            eor ^= cur;
        }
        System.out.println(eor);
        // 因为eor必然不等于0，因此必然至少有一个位置eor等于1
        // 取出eor最右侧的1作为分类
        // 假设rightOne = 00000100
        int rightOne = eor & (~eor + 1);  // 返回eor最右侧的1的位置
        System.out.println(rightOne);
        int onlyOne = 0;
        for (int cur : arr) {
            // 这里也可以写成等于0，效果一样，只是用来分成两类而已
            if ((cur & rightOne) == 0) {
                // 只^该位置为1的数，结果就是a或者b
                onlyOne ^= cur;
            }
        }
        System.out.println("First num:" + onlyOne + "\nSecond num:" + (onlyOne ^ eor));
    }

    public static void main(String[] args) {
        int[] arr = {1,1,11,3,3,3,7,7,7,7,9,9};
        printOddNumTimes2(arr);
    }
}
