package test;
//import java.util.Scanner;

import geometry2d.*;
import geometry3d.*;

import Exeptions.*;

public class main {
    public static void main(String[] args) {
        try {

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
        } catch (InvalidRadius | InvalidSides | InvalidHeight e) {
            System.err.println("Error " + e.getMessage());
        }
        System.out.println("-------------");
        try {
            System.out.println("Тест исключений сторон для прямоугольника");
            Figure rectexp = new Rectangle(-10, 20);

        } catch (InvalidSides e) {
            System.err.println("Поймано исключение для прямоугольника - " + e.getMessage());
        }
        System.out.println("-------------");
        try {
            System.out.println("Тест исключений радиуса для круга");
            Figure circlexp = new Circle(-5);

        } catch (InvalidRadius e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-------------");
        try {
            System.out.println("Тест исключений цилиндра ");
            Figure basecyl = new Circle(35);
            Cylinder cylexp = new Cylinder(basecyl, -21);

        } catch (InvalidRadius | InvalidHeight e) {
            System.err.println("Поймано исключение для цилиндра - " + e.getMessage());
        }
        System.out.println("-------------");
    }
}
