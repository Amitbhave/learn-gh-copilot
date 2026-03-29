public class HistoryRecord {
    private int id;
    private double num1;
    private char operator;
    private double num2;
    private double result;
    private String timestamp;

    public HistoryRecord(int id, double num1, char operator, double num2, double result, String timestamp) {
        this.id = id;
        this.num1 = num1;
        this.operator = operator;
        this.num2 = num2;
        this.result = result;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public double getNum1() { return num1; }
    public char getOperator() { return operator; }
    public double getNum2() { return num2; }
    public double getResult() { return result; }
    public String getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("[%s] %.2f %c %.2f = %.2f", timestamp, num1, operator, num2, result);
    }
}
