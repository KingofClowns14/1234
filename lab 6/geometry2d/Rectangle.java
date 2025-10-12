package geometry2d;

import Exeptions.InvalidSides;
import java.util.logging.*;

public class Rectangle implements Figure {
    private double a;
    private double b;
    private static final Logger logger = Logger.getLogger(Rectangle.class.getName());

    public Rectangle(double a, double b) throws InvalidSides {
        if (a <= 0 || b <= 0) {
            throw new InvalidSides("Стороны прямоугольника не могут быть отрицательными");
        }
        this.a = a;
        this.b = b;
        logger.info("Создан обьект Rectangele с длиной = " + a + " шириной = " + b);
    }

    @Override
    public double Area() {
        double area = a * b;
        logger.info("Площадь прямоугольника = " + area);
        return area;
    }

    @Override
    public void Show() {
        System.out.println("Прямоугольник");
        System.out.println("-------------");
        System.out.println("Ширина = " + a + "\nВысота = " + b);
        System.out.println("-------------");
    }
}
