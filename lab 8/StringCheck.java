import java.util.Objects;
import java.util.function.Predicate;;

public class StringCheck {
    public static void main(String[] args) {
        // a. Лямбда, проверяющая, что строка не null
        Predicate<String> isNotNull = Objects::nonNull;
        // b. Лямбда, проверяющая, что строка не пуста
        Predicate<String> isNotEmpty = s -> !s.isEmpty();
        // c. Объединение двух предикатов с помощью and()
        Predicate<String> isNotNullAndNotEmpty = isNotNull.and(isNotEmpty);
        // Тестирование
        String str1 = "Hello";
        String str2 = "";
        String str3 = null;
        System.out.println("Строка '" + str1 + "' не null и не пуста: " + isNotNullAndNotEmpty.test(str1)); // true
        System.out.println("Строка '" + str2 + "' не null и не пуста: " + isNotNullAndNotEmpty.test(str2)); // false
        System.out.println("Строка '" + str3 + "' не null и не пуста: " + isNotNullAndNotEmpty.test(str3)); // false
    }
}
