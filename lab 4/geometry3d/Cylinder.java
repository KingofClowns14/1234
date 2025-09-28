package geometry3d;

import geometry2d.Figure;

public class Cylinder {
    private Figure base;
    private double height;

    public Cylinder(Figure base, double height) {
        if (height <= 0) {
            System.out.println("Высота не может быть отрицательной");
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
