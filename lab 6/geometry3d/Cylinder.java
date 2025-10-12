package geometry3d;

import geometry2d.Figure;
import Exeptions.*;
import java.util.logging.*;
import java.io.IOException;

public class Cylinder {
    private Figure base;
    private double height;
    private static final Logger logger = Logger.getLogger(Cylinder.class.getName());

    public Cylinder(Figure base, double height) throws InvalidHeight {
        if (height <= 0) {
            throw new InvalidHeight("Высота не может быть отрицательной");
        }
        this.base = base;
        this.height = height;
        logger.finest("Создан обьект Cylinder с основанием = " + base + " и высотой = " + height);
    }

    public double Volume() {
        double Volume = base.Area() * height;
        logger.finest("Обьем цилиндра = " + Volume);
        return Volume;
    }

    public void Show() {
        System.out.println("Цилиндер");
        System.out.print("Основание - ");
        base.Show();
        System.out.println("Высота = " + height);
        System.out.println("Обьем = " + Volume());
    }

    static {
        try {
            FileHandler fileHandler = new FileHandler("cylinder.log");
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.FINEST);
            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.FINEST);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл лога cylinder.log" + e.getMessage());
        }

    }
}
