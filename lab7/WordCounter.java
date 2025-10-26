import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    public static void main(String[] args) {
        String text = "This is a sample text. This text is for testing purposes. this is a sample.";
        String[] words = text.replaceAll("[^a-zA-Z ]", "").split("\\s+");
        Map<String, Integer> wordFrequencies = new HashMap<>();
        for (String word : words) {
            wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
        }
        System.out.println("Частота встречаемости слов:");
        for (Map.Entry<String, Integer> entry : wordFrequencies.entrySet()) {
            System.out.println("'" + entry.getKey() + "' = " + entry.getValue());
        }
    }
}
