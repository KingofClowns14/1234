package geometry2d;

import Exeptions.InvalidRadius;

public class Circle implements Figure {
    private double radius;

    public Circle(double radius) throws InvalidRadius {
        if (radius <= 0) {
            throw new InvalidRadius("Радиус не может быть отрицательным");
        }
        this.radius = radius;
    }

    @Override
    public double Area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public void Show() {
        System.out.println("Круг");
        System.out.println("-------------");
        System.out.println("Радиус = " + radius);
        System.out.println("-------------");
    }
}
