import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Student {
    private int id;
    private String lastName, firstName, patronymic, address, phone, faculty, group;
    private LocalDate birthDate;
    private int course;

    // Конструкторы, геттеры, сеттеры и toString()
    public Student(int id, String lastName, String firstName, String patronymic, LocalDate birthDate, String address,
            String phone, String faculty, int course, String group) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getCourse() {
        return course;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return String.format("Студент{ФИО=%s %s, Факультет=%s, Курс=%d, Группа=%s, Дата рождения=%s}", lastName,
                firstName, faculty, course, group, birthDate);
    }
}

public class StudentFiltering {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(1, "Иванов", "Иван", "Иванович", LocalDate.of(2003, 5, 15), "ул. Ленина 1", "123", "IT", 2,
                        "Группа 1"),
                new Student(2, "Петров", "Петр", "Петрович", LocalDate.of(2002, 8, 20), "пр. Мира 5", "456",
                        "Экономика", 3, "Группа 2"),
                new Student(3, "Сидорова", "Анна", "Сергеевна", LocalDate.of(2004, 1, 10), "пер. Новый 3", "789", "IT",
                        2, "Группа 1"),
                new Student(4, "Кузнецов", "Олег", "Павлович", LocalDate.of(2001, 11, 30), "ул. Садовая 12", "101",
                        "Экономика", 3, "Группа 3"));

        System.out.println("а) Список студентов заданного факультета (IT)");
        String targetFaculty = "IT";

        // Способ 1: Циклы и операторы условия
        System.out.println("Способ 1: Циклы");
        for (Student student : students) {
            if (student.getFaculty().equals(targetFaculty)) {
                System.out.println(student);
            }
        }

        // Способ 2: Методы коллекций
        System.out.println("Способ 2: Методы коллекций");
        students.forEach(student -> {
            if (student.getFaculty().equals(targetFaculty)) {
                System.out.println(student);
            }
        });

        // Способ 3: Stream API
        System.out.println("Способ 3: Stream API");
        students.stream()
                .filter(s -> s.getFaculty().equals(targetFaculty))
                .forEach(System.out::println);

        System.out.println("b) Списки студентов для каждого факультета и курса");
        System.out.println("Способ 3: Stream API ");
        Map<String, Map<Integer, List<Student>>> studentsByFacultyAndCourse = students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty,
                        Collectors.groupingBy(Student::getCourse)));

        studentsByFacultyAndCourse.forEach((faculty, courseMap) -> {
            System.out.println("Факультет: " + faculty);
            courseMap.forEach((course, studentList) -> {
                System.out.println("  Курс: " + course);
                studentList.forEach(s -> System.out.println("    " + s));
            });
        });

        System.out.println("c) Список студентов, родившихся после заданного года (2002)");
        int year = 2002;

        // Способ 1: Циклы
        System.out.println("Способ 1: Циклы");
        for (Student student : students) {
            if (student.getBirthDate().getYear() > year) {
                System.out.println(student);
            }
        }

        // Способ 2: Методы коллекций
        System.out.println("Способ 2: Методы коллекций");
        students.forEach(student -> {
            if (student.getBirthDate().getYear() > year) {
                System.out.println(student);
            }
        });

        // Способ 3: Stream API
        System.out.println("Способ 3: Stream API");
        students.stream()
                .filter(s -> s.getBirthDate().getYear() > year)
                .forEach(System.out::println);
    }
}
