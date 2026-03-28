import java.util.Scanner;
import io.sentry.Sentry;

public class Main {
    public static void main(String[] args) {
        Sentry.init(options -> {
            options.setDsn("https://3b2c5b36b5891f6112cf4c22e01832a5@o4510996349255680.ingest.us.sentry.io/4511000986845184");
        });
        double num1 = 0, num2 = 0;
        char op = '+';
        boolean useArgs = args.length == 3;
        try {
            if (useArgs) {
                num1 = Double.parseDouble(args[0]);
                op = args[1].charAt(0);
                num2 = Double.parseDouble(args[2]);
                System.out.println("Simple Calculator");
                System.out.println("First number: " + num1);
                System.out.println("Operator: " + op);
                System.out.println("Second number: " + num2);
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Simple Calculator");
                System.out.print("Enter first number: ");
                num1 = scanner.nextDouble();
                System.out.print("Enter operator (+, -, *, /): ");
                op = scanner.next().charAt(0);
                System.out.print("Enter second number: ");
                num2 = scanner.nextDouble();
            }
            double result = 0;
            switch (op) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': result = num2 != 0 ? num1 / num2 : Double.NaN; break;
                default: throw new IllegalArgumentException("Invalid operator: " + op);
            }
            System.out.println("Result: " + result);
        } catch (Exception e) {
            Sentry.captureException(e);
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            Sentry.close();
        }
    }
}

