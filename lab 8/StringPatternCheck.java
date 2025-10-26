import java.util.function.Predicate;

public class StringPatternCheck {
    public static void main(String[] args) {
        Predicate<String> checkString = s -> (s.toUpperCase().startsWith("J") || s.toUpperCase().startsWith("N"))
                && s.toUpperCase().endsWith("A");

        // Тестирование
        String str1 = "Java";
        String str2 = "Niva";
        String str3 = "JavaScript";
        String str4 = "Kotlin";
        System.out.println("'" + str1 + "' -> " + checkString.test(str1)); // true
        System.out.println("'" + str2 + "' -> " + checkString.test(str2)); // true
        System.out.println("'" + str3 + "' -> " + checkString.test(str3)); // false
        System.out.println("'" + str4 + "' -> " + checkString.test(str4)); // false
    }

}
