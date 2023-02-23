/**
 * 使用位运算符实现加减乘除
 */
package video14;

public class AddMultipleMinusDivideByBit {
    /**
     * ^ 是无符号相加   &之后<<一位求出进位信息
     * 当进位信息为0的时候停止
     * 注意此方式不保证溢出处理
     * @param a a
     * @param b b
     * @return a + b
     */
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }

        return sum;
    }

    /**
     * 一个属取反加1就是他的相反数
     * @param a a
     * @return ~a + 1
     */
    public static int negative(int a) {
        return (~a + 1);
    }

    /**
     * a - b = a + (-b)
     * @param a a
     * @param b b
     * @return a -b
     */
    public static int minus(int a, int b) {
        return add(a, negative(b));
    }

    /**
     * 类似竖式运算
     * @param a a
     * @param b a
     * @return a * b
     */
    public static int multiply(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a = a << 1;
            b = b >>> 1;   // >>> 无符号右移
        }
        return res;
    }

    /**
     * 从31开始，x每次右移i位，到大于等于y的位置，说明x至少等于 1 << i 倍的y
     * 然后x减掉 1 << i倍的y，重复，直到x < y
     * @param a a
     * @param b b
     * @return a / b
     */
    public static int divide(int a, int b) {
        // 这两行考虑ab有可能时负数的情况
        int x = a < 0 ? negative(a) : a;
        int y = b < 0 ? negative(b) : b;
        int res = 0;
        for (int i = 31; i > -1; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= 1 << i;   // res加上暂时的结果
                x = minus(x, y << i);
            }
        }
        // 如果a b中任何一个是负数，那么结果一定是负数
        return (a < 0) ^ (b < 0) ? negative(res) : res;
    }
}
