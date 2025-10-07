package geometry3d;

import geometry2d.Figure;
import Exeptions.*;

public class Cylinder {
    private Figure base;
    private double height;

    public Cylinder(Figure base, double height) throws InvalidHeight {
        if (height <= 0) {
            throw new InvalidHeight("Высота не может быть отрицательной");
        }
        this.base = base;
        this.height = height;
    }

    public double Volume() {
        return base.Area() * height;
    }

    public void Show() {
        System.out.println("Цилиндер");
        System.out.print("Основание - ");
        base.Show();
        System.out.println("Высота = " + height);
        System.out.println("Обьем = " + Volume());
    }
}
