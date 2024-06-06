package application2;

import javax.swing.JOptionPane;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class StackController {
    @FXML
    private Pane pane;

    private StackModel stackModel;

    private Rectangle tube;

    private final double tubeWidth = 100;
    private final double tubeHeight = 400;
    private final double circleRadius = 25;

    public void initialize() {
        stackModel = new StackModel();

        // Create the tube with a black outline
        tube = new Rectangle(tubeWidth, tubeHeight, Color.TRANSPARENT);
        tube.setStroke(Color.BLACK);
        pane.getChildren().add(tube);
        tube.relocate((pane.getWidth() - tubeWidth) / 2, pane.getHeight() - tubeHeight);

        // Set up pane size change listener to reposition the tube
        pane.widthProperty().addListener((obs, oldVal, newVal) -> {
            tube.relocate((newVal.doubleValue() - tubeWidth) / 2, tube.getLayoutY());
        });

        // Style the buttons
        for (Node node : pane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
            }
        }
    }

    @FXML
    private void handlePush() {
        // Prompt the user to input a value to push onto the stack
        int value;
        try {
            value = Integer.parseInt(JOptionPane.showInputDialog("Enter a number to push onto the stack:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
            return;
        }

        // Create a circle to represent the pushed element with the user input value
        Circle circle = new Circle(circleRadius);
        circle.setFill(getColor());
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);

        // Calculate the y-coordinate based on the number of circles already on the stack
        double yPos = pane.getHeight() - tubeHeight - circleRadius - (stackModel.size() * (circleRadius * 2 + 5));

        // Set the initial position of the circle at the calculated y-coordinate
        circle.setTranslateY(yPos);

        // Display the value inside the circle
        Text text = new Text(String.valueOf(value));
        text.setFill(Color.WHITE);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(1);
        text.setBoundsType(TextBoundsType.VISUAL);

        // Group the circle and the text
        StackPane stackPane = new StackPane(circle, text);
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setLayoutX((pane.getWidth() - tubeWidth) / 2);
        stackPane.setLayoutY(yPos);

        // Add the group to the pane
        pane.getChildren().add(stackPane);

        // Animate the push operation
        TranslateTransition tt = new TranslateTransition(Duration.seconds(1), stackPane);
        tt.setToY(tube.getLayoutY() + tubeHeight - circleRadius - (stackModel.size() * (circleRadius * 2 + 5)));
        tt.play();

        // Update the model after the animation
        tt.setOnFinished(event -> {
            stackModel.push(value); // Update the model
        });
    }

    @FXML
    private void handlePop() {
        // Implement pop operation
        if (!stackModel.isEmpty()) {
            int poppedValue = stackModel.pop(); // Update the model
            // Remove the top circle from the pane (assuming it's the last child)
            Node poppedNode = pane.getChildren().remove(pane.getChildren().size() - 1);

            // Animate the pop operation
            TranslateTransition tt = new TranslateTransition(Duration.seconds(1), poppedNode);
            tt.setToY(-tubeHeight);
            tt.setOnFinished(event -> {
                // After pop animation, remove the popped circle and adjust positions of circles underneath
                pane.getChildren().remove(poppedNode);

                // Adjust positions of circles underneath
                double deltaY = circleRadius * 2 + 5;
                for (Node node : pane.getChildren()) {
                    if (node instanceof StackPane && node != poppedNode) {
                        node.setTranslateY(node.getTranslateY() + deltaY); // Move circles up
                    }
                }
            });
            tt.play();
        } else {
            // Handle empty stack error
            System.out.println("Stack is empty. Cannot pop.");
        }
    }

    private Color getColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
