package View;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NameSelectScreen {

    private Stage stage;
    private Button startButton;
    private TextField whitePlayerField;
    private TextField blackPlayerField;

    public NameSelectScreen(Stage stage) {
        this.stage = stage;
    }

    public Button getStartButton() {
        return startButton;
    }

    public TextField getWhitePlayerField() {
        return whitePlayerField;
    }

    public TextField getBlackPlayerField() {
        return blackPlayerField;
    }

    public void displayScreen() {
        VBox loginBox = new VBox(10);
        TextField whitePlayerField = new TextField();
        this.whitePlayerField = whitePlayerField;
        TextField blackPlayerField = new TextField();
        this.blackPlayerField = blackPlayerField;
        Button startButton = new Button("Start");
        this.startButton = startButton;
        loginBox.getChildren().addAll(
                new Label("White Player Name:"),
                whitePlayerField,
                new Label("Black Player Name:"),
                blackPlayerField,
                startButton
        );

        Scene loginScene = new Scene(loginBox, 300, 200);
        stage.setScene(loginScene);
        stage.setTitle("Chess - Login");
        stage.show();
    }

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void stageClose() {
        stage.close();
    }
}
