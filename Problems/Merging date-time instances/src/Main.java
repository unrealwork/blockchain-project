import java.time.LocalDateTime;
import java.util.Scanner;

import static java.lang.Math.max;

public class Main {

    public static LocalDateTime merge(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        // write your code here
        return LocalDateTime.of(
                max(dateTime1.getYear(), dateTime2.getYear()),
                max(dateTime1.getMonthValue(), dateTime2.getMonthValue()),
                max(dateTime1.getDayOfMonth(), dateTime2.getDayOfMonth()),
                max(dateTime1.getHour(), dateTime2.getHour()),
                max(dateTime1.getMinute(), dateTime2.getMinute()),
                max(dateTime1.getSecond(), dateTime2.getSecond())
        );
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final LocalDateTime firstDateTime = LocalDateTime.parse(scanner.nextLine());
        final LocalDateTime secondDateTime = LocalDateTime.parse(scanner.nextLine());
        System.out.println(merge(firstDateTime, secondDateTime));
    }
}
