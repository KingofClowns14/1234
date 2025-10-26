import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Human implements Comparable<Human> {
    private String name;
    private String lastName;
    private int age;

    public Human(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Human{" + "name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", age=" + age + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Human human = (Human) o;
        return age == human.age &&
                Objects.equals(name, human.name) &&
                Objects.equals(lastName, human.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, age);
    }

    @Override
    public int compareTo(Human o) {
        if (this.equals(o)) {
            return 0;
        }
        int rez = this.name.compareToIgnoreCase(o.getName());
        if (rez != 0) {
            return rez;
        }
        rez = this.lastName.compareToIgnoreCase(o.getLastName());
        if (rez != 0) {
            return rez;
        }
        return Integer.compare(this.age, o.getAge());
    }
}

class HumanComparatorByLName implements Comparator<Human> {
    @Override
    public int compare(Human o1, Human o2) {
        return o1.getLastName().compareToIgnoreCase(o2.getLastName());
    }
}

class HumanTest {
    public static void main(String[] args) {
        Set<Human> s = new HashSet<>();
        s.add(new Human("Иван", "Иванов", 30));
        s.add(new Human("Петр", "Петров", 25));
        s.add(new Human("Андрей", "Андреев", 35));
        s.add(new Human("Анна", "Богданова", 28));
        s.add(new Human("Иван", "Иванов", 30)); // Копия
        // a)В проверочном классе создайте коллекцию на основе HashSet.Выведите
        // коллекцию на консоль, пример кода.
        System.out.println("a) HashSet:");
        s.forEach(System.out::println);
        System.out.println();
        // b)В проверочном классе создайте коллекцию LinkedHashSet на основе
        // существующей коллекции s.
        // Выведите коллекцию на консоль.
        Set<Human> linkedHashSet = new LinkedHashSet<>(s);
        System.out.println("b) LinkedHashSet:");
        linkedHashSet.forEach(System.out::println);
        System.out.println();
        // c)В проверочном классе создайте коллекцию TreeSet на основе существующей
        // коллекции s.
        // Выведите коллекцию на консоль.
        Set<Human> treeSet = new TreeSet<>(s);
        System.out.println("c) TreeSet:");
        treeSet.forEach(System.out::println);
        System.out.println();
        // d)В проверочном классе создайте пустую коллекцию TreeSet с компаратором
        // HumanComparatorByLName.
        // Вставьте в новую коллекцию элементы коллекции s. Выведите коллекцию на
        // консоль.
        Set<Human> treeSetByLName = new TreeSet<>(new HumanComparatorByLName());
        treeSetByLName.addAll(s);
        System.out.println("d) TreeSet with HumanComparatorByLName:");
        treeSetByLName.forEach(System.out::println);
        System.out.println();
        // e)В проверочном классе создайте пустую коллекцию TreeSet с анонимным
        // компаратором по возрасту.
        // Вставьте в новую коллекцию элементы коллекции s. Выведите коллекцию на
        // консоль.
        Set<Human> treeSetByAge = new TreeSet<>(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        treeSetByAge.addAll(s);
        System.out.println("e) TreeSet with anonymous comparator by age:");
        treeSetByAge.forEach(System.out::println);
    }
}
