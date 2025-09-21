public class Balance {
    public int Left;
    public int Right;

    public Balance() {
        this.Left = 0;
        this.Right = 0;
    }

    public void addLeft(int weight) {
        this.Left += weight;
    }

    public void addRight(int weight) {
        this.Right += weight;
    }

    public void Result() {
        if (Left == Right) {
            System.out.println("=");
        } else if (Left > Right) {
            System.out.println("L");
        } else {
            System.out.println("R");
        }
    }
}
