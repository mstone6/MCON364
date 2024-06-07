package application; 

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML TextField display;

    double currentInput;
    double previousInput;
    String operation;
    private boolean startNewInput = true;
    private boolean decimalAdded = false;

    // Handle number action
    @FXML
    protected void handleNumberAction(javafx.event.ActionEvent event) {
        // Getting the text of the buttons that were pressed
        String number = ((Button) event.getSource()).getText();
        // If starting a new input, set the display to the button text
        if (startNewInput) {
            display.setText(number);
            startNewInput = false;
        } else {
            // Otherwise, add the button text to the current display text
            display.setText(display.getText() + number);
        }
        // Update the current input value
        currentInput = Double.parseDouble(display.getText());
    }

    // Handle decimal action
    @FXML
    protected void handleDecimalAction(javafx.event.ActionEvent event) {
        // If a decimal point has not already been added, lets add one
        if (!decimalAdded) {
            // Adding a decimal point to the display text
            display.setText(display.getText() + ".");
            // Set decimalAdded to true
            decimalAdded = true;
            // Starting over with a blank slate
            startNewInput = false;
        }
    }

    // Handle operation action
    @FXML
    protected void handleOperationAction(javafx.event.ActionEvent event) {
        // Getting the input of the buttons that were pressed
        String op = ((Button) event.getSource()).getText();
        // If not starting a new input
        if (!startNewInput) {
            // If there is already an operation going then calculate
            if (operation != null) {
                calculate();
            } else {
                // Otherwise, set the previous input to the current input
                previousInput = currentInput;
            }
        }
        // Setting the current operation to the button text
        operation = op;
        // Starting over with a blank slate
        startNewInput = true;
        // Reset decimalAdded to false
        decimalAdded = false;
    }

    // Handle equals action
    @FXML
    protected void handleEqualsAction() {
        // This is the equal button
        if (operation != null) {
            // Ltes calculate
            calculate();
            // Clear the line
            operation = null;
        }
    }

    // Handle clear action
    @FXML
    protected void handleClearAction() {
        // Clearing the text displayed
        display.setText("");
        // Now resetting the current and previous inputs
        currentInput = 0;
        previousInput = 0;
        // Now let's clear the input
        operation = null;
        // Starting with a blank slate
        startNewInput = true;
        // Now resetting decimalAdded to false
        decimalAdded = false;
    }

    // Calculate method
    protected void calculate() {
        // Let's do the calculation based on what the user chose
        switch (operation) {
            case "+":
                currentInput = previousInput + currentInput;
                break;
            case "-":
                currentInput = previousInput - currentInput;
                break;
            case "*":
                currentInput = previousInput * currentInput;
                break;
            case "/":
                currentInput = previousInput / currentInput;
                break;
        }
        // Now updating to show the result of the calculation
        display.setText(String.valueOf(currentInput));
        previousInput = currentInput;
        // Now getting a blank slate
        startNewInput = true;
    }
}
