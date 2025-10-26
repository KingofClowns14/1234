import java.util.*;
import java.util.stream.Collectors;

public class StreamApiTasks {
        public static void main(String[] args) {
                // a) Метод, возвращающий среднее значение списка целых чисел
                List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
                double average = numbers.stream().mapToInt(Integer::intValue).average().orElse(0.0);
                System.out.println("a) Среднее значение: " + average);
                // b) Метод, приводящий все строки к верхнему регистру и добавляющий префикс
                // «new»
                List<String> strings = Arrays.asList("one", "two", "three");
                List<String> modifiedStrings = strings.stream().map(s -> "new" + s.toUpperCase())
                                .collect(Collectors.toList());
                System.out.println("b) Модифицированные строки: " + modifiedStrings);
                // c) Метод, возвращающий список квадратов всех встречающихся только один раз
                // элементов
                List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 3, 2, 4, 5, 4, 6);
                List<Integer> uniqueSquares = numbersWithDuplicates.stream()
                                .filter(n -> Collections.frequency(numbersWithDuplicates, n) == 1)
                                .map(n -> n * n)
                                .collect(Collectors.toList());
                System.out.println("c) Квадраты уникальных элементов: " + uniqueSquares);
                // d) Метод, принимающий коллекцию строк и возвращающий строки, начинающиеся с
                // заданной буквы, отсортированные по алфавиту
                List<String> words = Arrays.asList("apple", "banana", "apricot", "cherry", "avocado");
                char startingLetter = 'a';
                List<String> filteredAndSorted = words.stream()
                                .filter(s -> s.startsWith(String.valueOf(startingLetter)))
                                .sorted()
                                .collect(Collectors.toList());
                System.out.println("d) Строки на букву '" + startingLetter + "': " + filteredAndSorted);
                // e) Метод, возвращающий последний элемент или кидающий исключение
                List<String> collection = Arrays.asList("first", "second", "last");
                String lastElement = collection.stream().reduce((first, second) -> second)
                                .orElseThrow(() -> new NoSuchElementException("Коллекция пуста"));
                System.out.println("e) Последний элемент: " + lastElement);
                // f) Метод, возвращающий сумму чётных чисел или 0
                List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6);
                int sumOfEvens = intList.stream().filter(n -> n % 2 == 0).mapToInt(Integer::intValue).sum();
                System.out.println("f) Сумма четных: " + sumOfEvens);
                // g) Метод, преобразовывающий все строки в Map, где первый символ — ключ,
                // оставшиеся — значение
                List<String> mapStrings = Arrays.asList("apple", "banana", "cherry");
                Map<Character, String> map = mapStrings.stream()
                                .collect(Collectors.toMap(
                                                s -> s.charAt(0),
                                                s -> s.substring(1),
                                                (existing, replacement) -> existing // В случае дубликатов ключей,
                                                                                    // оставляем существующее значение
                                ));
                System.out.println("g) Результирующая карта: " + map);
        }
}
