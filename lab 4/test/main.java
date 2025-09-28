package test;
//import java.util.Scanner;

import geometry2d.*;
import geometry3d.*;

public class main {
    public static void main(String[] args) {
        /*
         * Scanner scanner=new Scanner(System.in);
         * System.out.println("Введите a");
         * double a = scanner.nextDouble();
         * System.out.println("Введите b");
         * double b = scanner.nextDouble();
         * Figure rect=new Rectangle(a, b);
         * rect.Show();
         * System.out.println("Площадь "+rect.Area());
         */

        System.out.println("Тест плоских фигур");
        Figure circle = new Circle(10);
        circle.Show();
        System.out.println("Площадь = " + circle.Area());
        System.out.println();
        Figure rectangle = new Rectangle(10, 20);
        rectangle.Show();
        System.out.println("Площадь = " + rectangle.Area());
        System.out.println();
        System.out.println("Тест цилиндра");
        Cylinder cylinderCircle = new Cylinder(circle, 12);
        cylinderCircle.Show();
        System.out.println();
        Cylinder cylinderRect = new Cylinder(rectangle, 11);
        cylinderRect.Show();
    }

}
