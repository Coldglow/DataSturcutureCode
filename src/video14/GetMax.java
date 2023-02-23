/**
 * 在不用任何比较运算的情况下得到两个数中的较大值
 */
package video14;

public class GetMax {
    /**
     *
     * @param n 输入n
     * @return n == 0，输出1   n == 1，输出0
     */
    public static int flip(int n) {
        return n ^ 1;
    }

    /**
     * n >> 31 取出n的标志位
     * @param n  n
     * @return  n >= 0，返回1    n<0，返回0
     */
    public static int sign(int n) {
        return flip((n >> 31) & 1);
    }

    public static int getMax(int a, int b) {
        int c = a - b;   // 有可能溢出
        int sigA = sign(c);       // 如果c < 0,返回0    如果c > 0，返回1
        int sigB = flip(sigA);    // 如果sigA = 0, sigB一定等于1    如果sigA = 1, sigB一定等于0
        // sigA和sigB中必有一个是0另一个是1
        return sigA * a + sigB * b;
    }

    /**
     * 上面的方法当a b异号的时候，c有可能溢出
     * 溢出的情况只有两种：
     *  1. a < 0 && b > 0  此时c > 0
     *  2. a > 0 && b < 0  此时c < 0
     *  溢出的情况只可能是ab异号的时候
     * @param a a
     * @param b b
     * @return a b较大值
     */
    public static int getMax2(int a, int b) {
        int c= a - b;
        int sigA = sign(a);
        int sigB = sign(b);
        int sigC = sign(c);
        // a b符号相同，返回0，说明不可能溢出
        // a b符号不同，返回1，有可能溢出
        int difSab = sigA ^ sigB;
        int sameSab = flip(difSab);
        // 返回a的情况
        // 1. a b异号，同时a大于等于0，返回a
        // 2. a b同号，同时c大于等于0，返回a
        int returnA = difSab * sigA + sameSab + sigC;
        // 以上情况不成立，返回B
        int returnB = flip(returnA);

        return returnA * a + returnB * b;
    }
}
