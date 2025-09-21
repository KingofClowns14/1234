public class Button {
    public int ClikCount;

    public Button() {
        this.ClikCount = 0;
    }

    public void click() {
        this.ClikCount++;
        System.out.println("Кнопка была нажата " + ClikCount + " раз");
    }
}
