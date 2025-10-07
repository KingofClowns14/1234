package geometry2d;

import Exeptions.InvalidSides;

public class Rectangle implements Figure {
    private double a;
    private double b;

    public Rectangle(double a, double b) throws InvalidSides {
        if (a <= 0 || b <= 0) {
            throw new InvalidSides("Стороны прямоугольника не могут быть отрицательными");
        }
        this.a = a;
        this.b = b;
    }

    @Override
    public double Area() {
        return a * b;
    }

    @Override
    public void Show() {
        System.out.println("Прямоугольник");
        System.out.println("-------------");
        System.out.println("Ширина = " + a + "\nВысота = " + b);
        System.out.println("-------------");
    }

}
