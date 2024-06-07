package application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.event.ActionEvent;

//I cannot understand why they dont work. Handing it in anyway. Thank you so much and have a great summer! - Mushka

class CalculatorControllerTest {

    private CalculatorController calculatorController;

    @BeforeEach
    public void setUp() {
        calculatorController = new CalculatorController();
    }

    @Test
    public void testHandleNumberAction() {
        calculatorController.handleNumberAction(new ActionEvent(new Object(), calculatorController.display));
        assertEquals("1", calculatorController.display.getText());
    }

    @Test
    public void testHandleDecimalAction() {
        calculatorController.handleDecimalAction(new ActionEvent(new Object(), calculatorController.display));
        assertEquals(".", calculatorController.display.getText());
    }


    @Test
    public void testCalculateAddition() {
        calculatorController.previousInput = 5.0;
        calculatorController.currentInput = 3.0;
        calculatorController.operation = "+";
        calculatorController.calculate();
        assertEquals("8.0", calculatorController.display.getText());
    }

    @Test
    public void testCalculateSubtraction() {
        calculatorController.previousInput = 5.0;
        calculatorController.currentInput = 3.0;
        calculatorController.operation = "-";
        calculatorController.calculate();
        assertEquals("2.0", calculatorController.display.getText());
    }

    @Test
    public void testCalculateMultiplication() {
        calculatorController.previousInput = 5.0;
        calculatorController.currentInput = 3.0;
        calculatorController.operation = "*";
        calculatorController.calculate();
        assertEquals("15.0", calculatorController.display.getText());
    }

    @Test
    public void testCalculateDivision() {
        calculatorController.previousInput = 6.0;
        calculatorController.currentInput = 3.0;
        calculatorController.operation = "/";
        calculatorController.calculate();
        assertEquals("2.0", calculatorController.display.getText());
    }
}
