import java.util.function.Consumer;

// Вспомогательный класс
class HeavyBox {
    int weight;

    public HeavyBox(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ящик с весом " + weight;
    }
}

public class ConsumerExample {
    public static void main(String[] args) {
        // Создаем два Consumer'a
        Consumer<HeavyBox> loadConsumer = box -> System.out.println("Отгрузили " + box);
        Consumer<HeavyBox> sendConsumer = box -> System.out.println("Отправляем " + box);
        // Объединяем их в цепочку с помощью andThen
        Consumer<HeavyBox> combinedConsumer = loadConsumer.andThen(sendConsumer);
        HeavyBox box = new HeavyBox(150);
        combinedConsumer.accept(box);
    }
}
