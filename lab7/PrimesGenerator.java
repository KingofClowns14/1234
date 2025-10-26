import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;

public class PrimesGenerator implements Iterable<Integer> {
    private final int count;

    public PrimesGenerator(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count != 0");
        }
        this.count = count;
    }

    private class PrimeIterator implements Iterator<Integer> {
        private int primesFound;
        private int currentNumber;

        public PrimeIterator() {
            this.primesFound = 0;
            this.currentNumber = 2;
        }

        @Override
        public boolean hasNext() {
            return primesFound < count;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more primes to generate");
            }
            while (true) {
                if (isPrime(currentNumber)) {
                    primesFound++;
                    return currentNumber++;
                }
                currentNumber++;
            }
        }

        private boolean isPrime(int number) {
            if (number <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new PrimeIterator();
    }
}

class PrimesGeneratorTest {
    public static void main(String[] args) {
        int N = 20;
        PrimesGenerator primesGenerator = new PrimesGenerator(N);
        System.out.println("Первые " + N + " простых чисел в прямом порядке");
        for (Integer prime : primesGenerator) {
            System.out.print(prime + " ");
        }
        System.out.println();
        System.out.println("Первые " + N + " простых чисел в обратном порядке");
        List<Integer> primeList = new ArrayList<>();
        for (Integer prime : primesGenerator) {
            primeList.add(prime);
        }
        for (int i = primeList.size() - 1; i >= 0; i--) {
            System.out.print(primeList.get(i) + " ");
        }
        System.out.println();
    }
}
