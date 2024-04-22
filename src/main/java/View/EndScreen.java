package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class EndScreen {
    private final String whiteKingImage;
    private final String blackKingImage;
    private Button finishButton;
    private Stage endStage;

    public EndScreen() {
        this.whiteKingImage = "/pieceImages/whiteKing.png";
        this.blackKingImage = "/pieceImages/blackKing.png";
    }

    public Button getFinishButton() {
        return finishButton;
    }

    public Stage getEndStage() {
        return endStage;
    }

    public void endGameScreen(String winner, boolean whiteWon) {
        String winnerImageLocation;
        if (whiteWon) {
            winnerImageLocation = whiteKingImage;
        }
        else {
            winnerImageLocation = blackKingImage;
        }
        this.endStage = new Stage();
        endStage.initModality(Modality.APPLICATION_MODAL);
        endStage.setTitle("Game Over");
        Image winnerImage =new Image(Objects.requireNonNull(EndScreen.class.getResourceAsStream(winnerImageLocation)));
        ImageView imageView = new ImageView(winnerImage);
        Label winnerLabel = new Label("Winner: " + winner);
        this.finishButton = new Button("Finish");
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(imageView, winnerLabel, finishButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        Scene scene = new Scene(hbox, 250, 100);
        endStage.setScene(scene);
        endStage.show();
    }
}
