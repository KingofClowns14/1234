import java.util.*;

enum PhoneType {
    STATIONARY, MOBILE
}

class Phone {
    private String number;
    private PhoneType type;

    public Phone(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }

    public PhoneType getType() {
        return type;
    }

    @Override
    public String toString() {
        return number + " (" + type + ")";
    }
}

class Client {
    private String name;
    private int age;
    private List<Phone> phones;

    public Client(String name, int age, List<Phone> phones) {
        this.name = name;
        this.age = age;
        this.phones = phones;
    }

    public int getAge() {
        return age;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    @Override
    public String toString() {
        return "Client{name='" + name + "', age=" + age + "}";
    }
}

public class ClientStreamSearch {
    public static void main(String[] args) {
        List<Client> clients = Arrays.asList(
                new Client("Анна", 25, List.of(new Phone("111-111", PhoneType.MOBILE))),
                new Client("Петр", 50, List.of(new Phone("222-222", PhoneType.STATIONARY))),
                new Client("Мария", 32, List.of(new Phone("333-333", PhoneType.MOBILE))),
                new Client("Иван", 20, List.of(new Phone("555-555", PhoneType.MOBILE))));

        // Поиск самого молодого клиента с мобильным телефоном
        Optional<Client> youngestClientWithMobile = clients.stream()
                .filter(client -> client.getPhones().stream()
                        .anyMatch(phone -> phone.getType() == PhoneType.MOBILE))
                .min(Comparator.comparingInt(Client::getAge));

        youngestClientWithMobile
                .ifPresent(client -> System.out.println("Самый молодой клиент с мобильным телефоном: " + client));
    }
}
