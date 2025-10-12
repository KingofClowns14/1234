package test;
//import java.util.Scanner;

import geometry2d.*;
import geometry3d.*;
import java.util.logging.*;
import Exeptions.*;

public class main {
    private static final Logger logger = Logger.getLogger(main.class.getName());
    static {
        try {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.FINE);
            logger.setUseParentHandlers(false);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.FINE);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogConfig.setup();
        try {
            logger.fine("Программа запущена");
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
            logger.fine("Создание обьектов из geometry2d");
            System.out.println("Тест плоских фигур");
            Figure circle = new Circle(10);
            circle.Show();
            System.out.println("Площадь = " + circle.Area());
            System.out.println();
            Figure rectangle = new Rectangle(10, 20);
            rectangle.Show();
            System.out.println("Площадь = " + rectangle.Area());
            System.out.println();
            logger.fine("Создание обьектов из geometry3d");
            System.out.println("Тест цилиндра");
            Cylinder cylinderCircle = new Cylinder(circle, 12);
            cylinderCircle.Show();
            System.out.println();
            Cylinder cylinderRect = new Cylinder(rectangle, 11);
            cylinderRect.Show();
        } catch (InvalidRadius | InvalidSides | InvalidHeight e) {
            System.err.println("Error " + e.getMessage());
            logger.log(Level.FINE, "Произошла ошибка при создании фигуры", e);
        }
        System.out.println("-------------");
        try {
            System.out.println("Тест исключений сторон для прямоугольника");
            Figure rectexp = new Rectangle(-10, 20);

        } catch (InvalidSides e) {
            System.err.println("Поймано исключение для прямоугольника - " + e.getMessage());
            logger.log(Level.FINE, "Произошла ожидаемая ошибка при создании фигуры", e);
        }
        System.out.println("-------------");
        try {
            System.out.println("Тест исключений радиуса для круга");
            Figure circlexp = new Circle(-5);

        } catch (InvalidRadius e) {
            System.out.println(e.getMessage());
            logger.log(Level.FINE, "Произошла ожидаемая ошибка при создании фигуры", e);
        }
        System.out.println("-------------");
        try {
            System.out.println("Тест исключений цилиндра ");
            Figure basecyl = new Circle(35);
            Cylinder cylexp = new Cylinder(basecyl, -21);

        } catch (InvalidRadius | InvalidHeight e) {
            System.err.println("Поймано исключение для цилиндра - " + e.getMessage());
            logger.log(Level.FINE, "Произошла  ожидаемая ошибка при создании фигуры", e);
        }
        System.out.println("-------------");
        logger.fine("Программа завершена");
    }
}
