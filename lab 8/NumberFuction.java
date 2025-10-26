import java.util.function.Function;

public class NumberFuction {
    public static void main(String[] args) {
        Function<Integer, String> classifyNumber = n -> {
            if (n > 0) {
                return "Положительное число";
            } else if (n < 0) {
                return "Отрицательное число";
            } else {
                return "Ноль";
            }
        };
        // Тестирование
        System.out.println("10 -> " + classifyNumber.apply(10));
        System.out.println("-5 -> " + classifyNumber.apply(-5));
        System.out.println("0 -> " + classifyNumber.apply(0));
    }
}
