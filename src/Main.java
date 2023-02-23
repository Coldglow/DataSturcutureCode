import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    private String name;
    public void test() {
        System.out.println(this.hashCode());
    }

    public static void main(String[] args) {
        Main o1 = new Main();
        o1.test();
        Main o2 = new Main();
        o2.test();
    }
}