package View;

import Controller.TurnController;
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

public class EndScreen<T> {
    private final String whiteKingImage;
    private final String blackKingImage;
    private final String drawImage;
    private Button finishButton;
    private Stage endStage;
    private final TurnController turnController;

    public EndScreen(TurnController turnController) {
        this.whiteKingImage = "/pieceImages/whiteKing.png";
        this.blackKingImage = "/pieceImages/blackKing.png";
        this.drawImage = "/drawImage/draw.png";
        this.turnController = turnController;
    }

    public Button getFinishButton() {
        return finishButton;
    }

    public Stage getEndStage() {
        return endStage;
    }

    public void endGameScreen(T result) {
        String winnerImageLocation;
        String winner;
        Label winnerLabel;
        if (result instanceof Boolean) {
            boolean whiteWon = (Boolean) result;
            if (whiteWon) {
                winnerImageLocation = whiteKingImage;
                winner = turnController.getWhitePlayerName();
            }
            else {
                winnerImageLocation = blackKingImage;
                winner = turnController.getBlackPlayerName();
            }
            winnerLabel = new Label("Winner: " + winner);
        }
        else {
            winnerImageLocation = drawImage;
            winnerLabel = new Label("Draw");
        }
        this.endStage = new Stage();
        endStage.initModality(Modality.APPLICATION_MODAL);
        endStage.setTitle("Game Over");
        Image winnerImage =new Image(Objects.requireNonNull(EndScreen.class.getResourceAsStream(winnerImageLocation)));
        ImageView imageView = new ImageView(winnerImage);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
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
