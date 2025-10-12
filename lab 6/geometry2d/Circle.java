package geometry2d;

import Exeptions.InvalidRadius;
import java.util.logging.*;

public class Circle implements Figure {
    private double radius;
    private static final Logger logger = Logger.getLogger(Circle.class.getName());

    public Circle(double radius) throws InvalidRadius {
        if (radius <= 0) {
            throw new InvalidRadius("Радиус не может быть отрицательным");
        }
        this.radius = radius;
        logger.severe("Создан обьект Cicrle c радиусом = " + radius);
    }

    @Override
    public double Area() {

        double area = Math.PI * Math.pow(radius, 2);
        logger.severe("Площадь круга = " + area);
        return area;
    }

    @Override
    public void Show() {
        System.out.println("Круг");
        System.out.println("-------------");
        System.out.println("Радиус = " + radius);
        System.out.println("-------------");
    }
}
