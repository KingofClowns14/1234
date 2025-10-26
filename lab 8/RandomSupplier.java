import java.util.Random;
import java.util.function.Supplier;

public class RandomSupplier {
    public static void main(String[] args) {
        Supplier<Integer> randomSupplier = () -> new Random().nextInt(11); // от 0 до 10 включительно
        // Тестирование
        System.out.println("Случайное число: " + randomSupplier.get());
        System.out.println("Случайное число: " + randomSupplier.get());
    }
}
