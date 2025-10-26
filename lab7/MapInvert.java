import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapInvert {
    public static <K, V> Map<V, Collection<K>> invertMap(Map<K, V> originalMap) {
        Map<V, Collection<K>> invertedMap = new HashMap<>();

        for (Map.Entry<K, V> entry : originalMap.entrySet()) {
            V value = entry.getValue();
            K key = entry.getKey();
            invertedMap.computeIfAbsent(value, k -> new ArrayList<>()).add(key);
        }

        return invertedMap;
    }

    public static void main(String[] args) {
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("one", "a");
        originalMap.put("two", "b");
        originalMap.put("three", "a");
        originalMap.put("four", "c");
        originalMap.put("five", "b");
        System.out.println("Исходная карта: " + originalMap);
        Map<String, Collection<String>> invertedMap = invertMap(originalMap);
        System.out.println("Инвертированная карта: " + invertedMap);
    }
}
