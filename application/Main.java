package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
        	//loading in our xml
            Parent root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));
            Scene scene = new Scene(root);
            //Getting the css to have super cool styling
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            //Now settin the scene
            primaryStage.setScene(scene);
            //Now setting the title
            primaryStage.setTitle("Calculator");
            //Now displaying the stage
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        //3 2 1, blastoff - its go time
    	launch(args);
    }
}
