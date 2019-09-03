package by.dma.junit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author dzmitry.marudau
 * @since 2019.09
 */
public class TestFixtureTemplate {

    @Test
    void testCase() {

        //Given: The assumptions and setup logic that configures the state of the features under test

        //When: The execution of the feature under test

        //Then: The expected results of the executed feature
    }

    @Test
    void add() {
        SimpleCalculator calculator = new SimpleCalculator(22,11); //Given
        int result = calculator.add();//When
        assertEquals(33, result); //Then
    }

    @Test
    void calculationHistory() {
        //Given
        SimpleCalculator calculator = new SimpleCalculator(22,11);
        //When
        int result = calculator.add();
        System.out.println(result);
        result = calculator.substract();
        System.out.println(result);
        result = calculator.multiply();
        System.out.println(result);

        //Then
        assertEquals(3, calculator.getHistory().getCalculations().size());
    }
}

class SimpleCalculator {

    private CalculationHistory history;

    private int a;
    private int b;

    public SimpleCalculator(int a, int b) {
        this.a = a;
        this.b = b;
        history = new CalculationHistory();
    }

    public int add() {
        int res = a + b;
        history.append(new CompletedCalculation(Operation.ADD, a, b, res));
        return res;
    }

    public int substract() {
        int res = a - b;
        history.append(new CompletedCalculation(Operation.SUBSTRACT, a, b, res));
        return res;
    }

    public int multiply() {
        int res = a * b;
        history.append(new CompletedCalculation(Operation.MULTIPLE, a, b, res));
        return res;
    }

    public int divide() {
        int res = a / b;
        history.append(new CompletedCalculation(Operation.DIVIDE, a, b, res));
        return res;
    }

    public CalculationHistory getHistory() {
        return history;
    }
}


class CompletedCalculation {
    private Operation operation;
    private int augend;
    private int addend;
    private int result;

    public CompletedCalculation(Operation operation, int augend, int addend, int result) {
        this.augend = augend;
        this.addend = addend;
        this.operation = operation;
        this.result = result;
    }


    @Override
    public String toString() {
        return "CompletedCalculation{" +
               "operation=" + operation +
               ", augend=" + augend +
               ", addend=" + addend +
               ", result=" + result +
               '}';
    }
}

class CalculationHistory {
    private final List<CompletedCalculation> calculations = new ArrayList<>();
    public void append(CompletedCalculation calculation) {
        calculations.add(calculation);
    }

    public List<CompletedCalculation> getCalculations() {
        return Collections.unmodifiableList(calculations);
    }
}

enum Operation {
    ADD, SUBSTRACT, MULTIPLE, DIVIDE
}