public class Bell {
    private boolean next;

    public void Bell() {
        this.next = true;
    }

    public void sound() {
        if (next) {
            System.out.println("Dong");
        } else {
            System.out.println("Ding");
        }
        next = !next;
    }
}
