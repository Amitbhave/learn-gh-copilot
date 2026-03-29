import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // Addition tests
    @Test
    void testAddPositiveNumbers() {
        assertEquals(5.0, calculator.calculate(2, '+', 3));
    }

    @Test
    void testAddNegativeNumbers() {
        assertEquals(-5.0, calculator.calculate(-2, '+', -3));
    }

    @Test
    void testAddDecimalNumbers() {
        assertEquals(3.3, calculator.calculate(1.1, '+', 2.2), 0.0001);
    }

    @Test
    void testAddWithZero() {
        assertEquals(7.0, calculator.calculate(7, '+', 0));
    }

    // Subtraction tests
    @Test
    void testSubtractPositiveNumbers() {
        assertEquals(2.0, calculator.calculate(5, '-', 3));
    }

    @Test
    void testSubtractNegativeNumbers() {
        assertEquals(1.0, calculator.calculate(-2, '-', -3));
    }

    @Test
    void testSubtractResultIsZero() {
        assertEquals(0.0, calculator.calculate(5, '-', 5));
    }

    // Multiplication tests
    @Test
    void testMultiplyPositiveNumbers() {
        assertEquals(15.0, calculator.calculate(3, '*', 5));
    }

    @Test
    void testMultiplyNegativeNumbers() {
        assertEquals(6.0, calculator.calculate(-2, '*', -3));
    }

    @Test
    void testMultiplyByZero() {
        assertEquals(0.0, calculator.calculate(100, '*', 0));
    }

    @Test
    void testMultiplyMixedSign() {
        assertEquals(-10.0, calculator.calculate(2, '*', -5));
    }

    // Division tests
    @Test
    void testDivideNormal() {
        assertEquals(4.0, calculator.calculate(12, '/', 3));
    }

    @Test
    void testDivideByZeroReturnsNaN() {
        assertTrue(Double.isNaN(calculator.calculate(5, '/', 0)));
    }

    @Test
    void testDivideDecimal() {
        assertEquals(2.5, calculator.calculate(5, '/', 2));
    }

    // Invalid operator
    @Test
    void testInvalidOperatorThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(1, '%', 2));
        assertTrue(ex.getMessage().contains("Invalid operator"));
    }

    // Edge cases
    @Test
    void testLargeNumbers() {
        assertEquals(2_000_000_000.0, calculator.calculate(1_000_000_000, '+', 1_000_000_000));
    }

    @Test
    void testSmallDecimals() {
        assertEquals(0.0003, calculator.calculate(0.0001, '+', 0.0002), 0.00001);
    }
}
