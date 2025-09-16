import java.util.Scanner;

public class main1 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, World!");
        // Task #1
        System.out.println("Задание #1");
        int i = 0;
        for (; i <= 500; i++) {
            if (i % 5 == 0 && i % 7 == 0) {
                System.out.println("fizzbuzz");
            } else if (i % 7 == 0) {
                System.out.println("buzz");
            } else if (i % 5 == 0) {
                System.out.println("fizz");
            } else {
                System.out.println(i);
            }
        }

        // Task #2
        System.out.println("Задание #2");
        String word = "make install";
        int x = word.length();
        System.out.println(word);
        int g = --x;
        for (; x >= 0; x--) {
            System.out.print(word.charAt(g));
            g--;
        }
        System.out.println();

        /*
         * // Task #3
         * System.out.println("Задание #3");
         * System.out.println("Введите a");
         * Double a = scanner.nextDouble();
         * System.out.println("Введите b");
         * Double b = scanner.nextDouble();
         * System.out.println("Введите c");
         * Double c = scanner.nextDouble();
         * Double D = b * b - 4 * a * c;
         * if (D >= 0) {
         * Double x1 = (-b + Math.sqrt(D)) / (2 * a);
         * Double x2 = (-b - Math.sqrt(D)) / (2 * a);
         * System.out.println("Корни");
         * System.out.println("Корень #1" + x1);
         * System.out.println("Корень #2" + x2);
         * } else {
         * System.out.println("Нет вещественных корней");
         * }
         */

        // Task #4
        int n = 2;
        double sum = 0.0;
        double y;
        do {
            y = 1.0 / ((n * n) + n - 2.0);
            sum += y;
            n++;
        } while (y >= Math.pow(10, -6));
        System.out.println("Сумма =" + sum);

        System.out.println();
        // Task #5
        System.out.println("Задание #5");
        System.out.println("Введите слово или фразу для проверки на палиндром:");
        scanner.nextLine();
        String inputString = scanner.nextLine();

        boolean isPal = PalindromeChecker.isPalindrome(inputString);
        System.out.println("'" + inputString + "' является палиндромом: " + isPal);
        scanner.close();
    }

}
