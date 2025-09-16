import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Task #1
        /*
         * System.out.println("Введите a");
         * int a =scanner.nextInt();
         * System.out.println("Введите b");
         * int b =scanner.nextInt();
         * System.out.println("Введите c");
         * int c =scanner.nextInt();
         * System.out.println("Результат для 1 задания");
         * triangle(a, b, c);
         * triangle(1, 1, 2);
         * triangle(7, 6, 10);
         * triangle(20, 13, 17);
         */

        // Task #2
        /*
         * System.out.println("Введите x1");
         * double x1=scanner.nextDouble();
         * System.out.println("Введите y1");
         * double y1=scanner.nextDouble();
         * System.out.println("Введите x2");
         * double x2=scanner.nextDouble();
         * System.out.println("Введите y2");
         * double y2=scanner.nextDouble();
         * System.out.println("Расстояние между точками = "+ distance(x1, y1, x2, y2));
         */

        // Task #3
        /*
         * System.out.println("Введите a для задания #3 ");
         * double pa= scanner.nextDouble();
         * System.out.println("Введите n для задания #3 ");
         * int n=scanner.nextInt();
         * System.out.println("Результат для 3 задания = " + power(pa, n));
         */
        // Task #4
        /*
         * System.out.println("Введите a для задания #4 ");
         * double Pa= scanner.nextDouble();
         * System.out.println("Введите n для задания #4 ");
         * int N=scanner.nextInt();
         * System.out.println("Результат для 4 задания = " + Power(Pa, N));
         */
        // Task #5
        System.out.println("Введите n для задания #5");
        int T = scanner.nextInt();
        System.out.println("Результат Трибоначи = " + tribonacci(T));
        scanner.close();
    }

    // Task #1
    public static void triangle(int a, int b, int c) {
        if ((a + b > c) && (a + c > b) && (b + c > a)) {
            System.out.println("Это треугольник");
        } else {
            System.out.println("Это не треугольник");
        }
    }

    // Task #2
    public static double distance(double x1, double y1, double x2, double y2) {
        double result = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        return result;
    }

    // Task #3
    public static double power(double pa, int n) {
        double result = 1.0;
        int i;
        int absN = Math.abs(n);
        if (n == 0) {
            return 1.0;
        }
        for (i = 0; i < absN; i++) {
            result *= pa;
        }
        if (n < 0) {
            return 1.0 / result;
        } else {
            return result;
        }
    }

    // #Task 4
    public static double Power(double Pa, int N) {
        if (N == 0) {
            return 1.0;
        } else {
            return Pa * Power(Pa, N - 1);
        }
    }

    // #Task 5
    public static int tribonacci(int T) {
        if (T == 0) {
            return 0;
        } else if (T == 1) {
            return 0;
        } else if (T == 2) {
            return 1;
        } else {
            return tribonacci(T - 1) + tribonacci(T - 2) + tribonacci(T - 3);
        }
    }

}
