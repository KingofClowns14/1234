import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionsTest {
    public static void main(String[] args) {
        int N = 10;
        Integer[] array = new Integer[N];
        for (int i = 0; i < N; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        // а) Создайте массив из N числа
        System.out.println("a) Исходный массив - " + Arrays.toString(array));
        // b)На основе массива создайте списко List
        List<Integer> list = new ArrayList<>(Arrays.asList(array));
        System.out.println("b) На основе массива создайте список List - " + list);
        // c) Отсортируйте список в натуральном порядке
        Collections.sort(list);
        System.out.println("c) Отсортируйте список в натуральном порядке - " + list);
        // d) Отсортируйте список в обратном порядке
        Collections.sort(list, Collections.reverseOrder());
        System.out.println("d) Отсортируйте список в обратном порядке - " + list);
        // e) Перемешайте список
        Collections.shuffle(list);
        System.out.println("e) Перемешайте список - " + list);
        // f) Выполните циклический сдвиг на 1 элемент
        Collections.rotate(list, 1);
        System.out.println("f) Выполните циклический сдвиг на 1 элемент - " + list);
        // Дубликаты для g) и h)
        list.add(list.get(0));
        list.add(list.get(1));
        System.out.println("Список в дубликатами - " + list);
        // g) Оставьте в списке только уникальные элементы
        List<Integer> uniq = new ArrayList<>(new HashSet<>(list));
        System.out.println("g) Список с уникальными элементами - " + uniq);
        // h) Оставьте в списке только дублирующиеся элементы
        Set<Integer> seen = new HashSet<>();
        List<Integer> duplikate = list.stream()
                .filter(n -> !seen.add(n))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("h) Только дублируешся элементы в списке - " + duplikate);
        // i) Из списка получите массив
        Integer[] finalArray = list.toArray(new Integer[0]);
        System.out.println("i) Итоговый массив из списка - " + Arrays.toString(finalArray));

    }
}
