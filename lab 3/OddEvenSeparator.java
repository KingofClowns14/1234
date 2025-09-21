import java.util.ArrayList;
import java.util.List;

public class OddEvenSeparator {
    private List<Integer> evenNumbers;
    private List<Integer> oddNumbers;

    public OddEvenSeparator() {
        this.evenNumbers = new ArrayList<>();
        this.oddNumbers = new ArrayList<>();
    }

    public void addNumber(int number) {
        if (number % 2 == 0) {
            evenNumbers.add(number);
        } else {
            oddNumbers.add(number);
        }
    }

    public void even() {
        if (evenNumbers.isEmpty()) {
            System.out.println("No even numbers");
        } else {
            System.out.println("Even Numbers");
            for (int i = 0; i < evenNumbers.size(); i++) {
                System.out.print(evenNumbers.get(i));
                if (i < evenNumbers.size() - 1) {
                    System.out.print(",");
                }
            }
        }
        System.out.println();
    }

    public void odd() {
        if (oddNumbers.isEmpty()) {
            System.out.println("No Odd Numbers");
        } else {
            System.out.println("Odd Numbers");
            for (int i = 0; i < oddNumbers.size(); i++) {
                System.out.print(oddNumbers.get(i));
                if (i < oddNumbers.size() - 1) {
                    System.out.print(",");
                }
            }
        }
        System.out.println();
    }
}
