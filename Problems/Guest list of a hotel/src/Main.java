//put imports you need here

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[][] roomList = new String[4][];
        for (int i = 0; i < 4; i++) {
            String[] names = scanner.nextLine().split("\\s+");
            roomList[4 - i - 1] = names;
        }
        for (String[] names : roomList) {
            for (int j = names.length - 1; j >= 0; j--) {
                System.out.println(names[j]);
            }
        }
    }
}
