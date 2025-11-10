public class AnnotatedClass {
    public void publicMethod1(String message) {
        System.out.println("Публичный метод 1 " + message);
    }

    @InvokeMethod(times = 3)
    public void publicMethod2(String mes) {
        System.out.println("Публичный метод 2 с аннотацией" + mes);
    }

    protected void protectedMethod1(int value) {
        System.out.println("Защищенный метод 1 со значением = " + value);
    }

    @InvokeMethod(times = 5)
    protected void protectedMethod2(String text) {
        System.out.println("Защищенный метод 2 с аннотацией  с текстом  " + text);
    }

    private void privateMethod1(int num) {
        System.out.println("Приватный метод 1" + num);
    }

    @InvokeMethod(times = 7)
    private void privateMethod2(int number, String name) {
        System.out.println("Приватный метод 2 с аннотацией с номером = " + number + " именем - " + name);
    }
}
