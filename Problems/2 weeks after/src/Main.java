import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Main {
    public static void main(String[] args) throws IOException {
        // put your code here
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(LocalDate.parse(reader.readLine()).plus(2, ChronoUnit.WEEKS));
        }
    }
}
