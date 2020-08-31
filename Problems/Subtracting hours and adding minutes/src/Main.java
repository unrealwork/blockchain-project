import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        System.out.println(LocalDateTime.parse(sc.nextLine()).minusHours(sc.nextInt()).plusMinutes(sc.nextInt()));
    }
}
