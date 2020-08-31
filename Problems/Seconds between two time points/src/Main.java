import java.time.LocalTime;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int res  = abs(LocalTime.parse(scanner.nextLine()).toSecondOfDay() - 
                LocalTime.parse(scanner.nextLine()).toSecondOfDay());
        System.out.println(res);
    }
}
