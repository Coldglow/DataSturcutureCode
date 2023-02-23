package video14;

public class PowerOf24 {
    public static boolean is2Power(int n) {
        return (n & (n - 1)) == 0;
    }

    public static boolean is4Power(int n) {
        // 是4的幂，则也一定只有一个1，并且1的位置是0或2或4或8或10...32
        // 换成十六进制就是0x55555555
        return is2Power(n) && (n & 0x55555555) != 0;
    }
}
