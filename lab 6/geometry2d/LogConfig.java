package geometry2d;

import java.util.logging.*;
import java.io.IOException;

public class LogConfig {
    public static void setup() {
        try {
            Logger geom2dlogger = Logger.getLogger("geometry2d");
            geom2dlogger.setUseParentHandlers(false);
            FileHandler geom2dhandler = new FileHandler("figuries.log", false);
            geom2dhandler.setFormatter(new XMLFormatter());
            geom2dhandler.setLevel(Level.INFO);
            geom2dlogger.addHandler(geom2dhandler);
            geom2dlogger.setLevel(Level.INFO);
            Logger.getLogger("geometry2d.Circle").setLevel(Level.SEVERE);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл лога figures.log" + e.getMessage());
        }
    }
}
