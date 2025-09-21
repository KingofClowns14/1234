import java.text.DecimalFormat;

public class main {

    public static void main(String[] args) {
        // Task #1
        Button myButton = new Button();
        System.out.println("Task #1");
        myButton.click();
        myButton.click();
        myButton.click();
        myButton.click();
        // Task #2
        Balance scale1 = new Balance();
        Balance scale2 = new Balance();
        Balance scale3 = new Balance();
        System.out.println("Task #2");
        scale1.addLeft(10);
        scale1.addRight(12);
        scale1.Result();
        scale2.addLeft(20);
        scale2.addRight(12);
        scale2.Result();
        scale3.addLeft(5);
        scale3.addRight(5);
        scale3.Result();
        // Task #3
        System.out.println("Task #3");
        Bell bell = new Bell();
        bell.sound();
        bell.sound();
        bell.sound();
        bell.sound();
        bell.sound();
        bell.sound();
        bell.sound();
        bell.sound();
        // Task #4
        System.out.println("Task #4");
        OddEvenSeparator separator = new OddEvenSeparator();
        separator.addNumber(1);
        separator.addNumber(2);
        separator.addNumber(4);
        separator.addNumber(7);
        separator.addNumber(5);
        separator.addNumber(6);
        separator.addNumber(8);
        separator.addNumber(3);
        separator.even();
        separator.odd();
        System.out.println("Test empty");
        OddEvenSeparator separator2 = new OddEvenSeparator();
        separator2.even();
        separator2.odd();
        // Task #5
        System.out.println("Task #5");
        Table myTable1 = new Table(4, 4);
        myTable1.setValue(0, 0, 10);
        myTable1.setValue(1, 0, 12);
        myTable1.setValue(0, 2, 6);
        myTable1.setValue(3, 2, 21);
        myTable1.setValue(1, 1, 5);
        System.out.println("Таблица 4 на 4\n" + myTable1);
        System.out.println("Кол-во строк = " + myTable1.rows());
        System.out.println("кол-во столбцов = " + myTable1.cols());
        System.out.println("Среднее арифметическое = " + myTable1.average());
        Table myTable2 = new Table(2, 2);
        myTable2.setValue(0, 0, 5);
        myTable2.setValue(0, 1, 6);
        myTable2.setValue(1, 0, 7);
        myTable2.setValue(1, 1, 12);
        System.out.println("Таблица 2 на 2\n" + myTable2);
        System.out.println("Кол-во строк = " + myTable2.rows());
        System.out.println("кол-во столбцов = " + myTable2.cols());
        System.out.println("Среднее арифметическое = " + myTable2.average());

    }
}