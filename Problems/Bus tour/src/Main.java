import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        int limit = scanner.nextInt();
        int[] heights = new int[scanner.nextInt()];
        for (int i = 0; i < heights.length; i++) {
            heights[i] = scanner.nextInt();
        }
        int res = willCrash(heights, limit);
        if (res > -1) {
            System.out.printf("Will crash on bridge %d%n", res + 1);
        } else {
            System.out.println("Will not crash");
        }
    }

    private static int willCrash(final int[] heights, int limit) {
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] <= limit) {
                return i;
            }
        }
        return -1;
    }
}
