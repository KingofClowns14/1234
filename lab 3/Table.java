
public class Table {
    private int[][] data;
    private int numRows;
    private int numCols;

    public Table(int rows, int cols) {
        this.numCols = cols;
        this.numRows = rows;
        this.data = new int[rows][cols];
    }

    public int getValue(int row, int col) {
        return this.data[row][col];
    }

    public void setValue(int row, int col, int value) {
        this.data[row][col] = value;
    }

    public int rows() {
        return this.numRows;
    }

    public int cols() {
        return this.numCols;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                sb.append(data[i][j]);
                if (j < numCols - 1) {
                    sb.append(" ");
                }
            }
            if (i < numRows - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public double average() {
        if (numRows == 0 || numCols == 0) {
            return 0.0;
        }
        long sum = 0;
        int AllElements = numRows * numCols;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                sum += data[i][j];
            }
        }
        return (double) sum / AllElements;
    }
}
