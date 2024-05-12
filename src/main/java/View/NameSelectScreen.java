package View;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Screen that handles the Picking of names. Gui class.
 */
public class NameSelectScreen {

    private final Stage stage;
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

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        gridPane.add(new Label("White Player Name:"), 0, 0);
        gridPane.add(whitePlayerField, 0, 1);
        gridPane.add(new Label("Black Player Name:"), 2, 0);
        gridPane.add(blackPlayerField, 2, 1);

        GridPane.setHalignment(startButton, HPos.CENTER);
        gridPane.add(startButton, 1, 1);

        loginBox.getChildren().add(gridPane);

        Scene loginScene = new Scene(loginBox, 425, 150);
        stage.setScene(loginScene);
        stage.setTitle("Chess - Name Selector");
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
