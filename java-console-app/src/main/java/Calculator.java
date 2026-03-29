public class Calculator {
    public double calculate(double num1, char operator, double num2) {
        switch (operator) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/': return num2 != 0 ? num1 / num2 : Double.NaN;
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
