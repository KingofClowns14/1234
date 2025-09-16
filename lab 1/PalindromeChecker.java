public class PalindromeChecker {

    public static boolean isPalindrome(String text) {
        String cleanedText = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversedText = new StringBuilder(cleanedText).reverse().toString();
        return cleanedText.equals(reversedText);
    }

    public static void main(String[] args) {
        System.out.println("кабак: " + isPalindrome("кабак")); // true
        System.out.println("Мадам: " + isPalindrome("Мадам")); // true
        System.out.println("121: " + isPalindrome("121")); // true
        System.out.println("12345: " + isPalindrome("12345")); // false
    }
}
