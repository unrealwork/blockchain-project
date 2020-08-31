import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {
        // put your code here
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             Scanner scanner = new Scanner(reader)
        ) {
            int year = scanner.nextInt();
            int month = scanner.nextInt();
            final LocalDate startDate = LocalDate.of(year, month, 1);
            final LocalDate endDate = startDate.plus(startDate.lengthOfMonth() - 1, ChronoUnit.DAYS);
            System.out.printf("%s %s%n",
                    startDate, endDate);
        }
    }
}
