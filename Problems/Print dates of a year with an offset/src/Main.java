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
            LocalDate date = LocalDate.parse(scanner.nextLine());
            int step = scanner.nextInt();
            int currentYear = date.getYear();
            LocalDate it = date;
            while (it.getYear() == currentYear) {
                System.out.println(it);
                it = it.plus(step, ChronoUnit.DAYS);
            }
        }
    }
}
