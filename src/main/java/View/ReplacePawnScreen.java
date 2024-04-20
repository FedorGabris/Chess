package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Objects;

public class ReplacePawnScreen {
    private final boolean whiteTurn;
    private Stage pickPieceStage;

    public ReplacePawnScreen(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public void pickPieceScreen(EventHandler<ActionEvent> queenHandler, EventHandler<ActionEvent> rookHandler, EventHandler<ActionEvent> bishopHandler, EventHandler<ActionEvent> knightHandler) {
        this.pickPieceStage = new Stage();

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);

        Button knightButton;
        Button bishopButton;
        Button rookButton;
        Button queenButton;
        if (whiteTurn) {
            queenButton = createButton("/pieceImages/whiteQueen.png");
            rookButton = createButton("/pieceImages/whiteRook.png");
            bishopButton = createButton("/pieceImages/whiteBishop.png");
            knightButton = createButton("/pieceImages/whiteKnight.png");
        }
        else {
            queenButton = createButton("/pieceImages/blackQueen.png");
            rookButton = createButton("/pieceImages/blackRook.png");
            bishopButton = createButton("/pieceImages/blackBishop.png");
            knightButton = createButton("/pieceImages/blackKnight.png");
        }

        queenButton.setOnAction(queenHandler);
        rookButton.setOnAction(rookHandler);
        bishopButton.setOnAction(bishopHandler);
        knightButton.setOnAction(knightHandler);

        buttonBox.getChildren().addAll(queenButton, rookButton, bishopButton, knightButton);

        Scene scene = new Scene(buttonBox);
        pickPieceStage.setScene(scene);
        pickPieceStage.setTitle("Pick new piece");
        pickPieceStage.show();
    }

    private Button createButton(String location) {
        Button button = new Button();
        button.setPrefSize(80, 80);
        button.setStyle("-fx-background-color: white;");
        Image pieceImage = new Image(Objects.requireNonNull(ReplacePawnScreen.class.getResourceAsStream(location)));
        ImageView imageView = new ImageView(pieceImage);
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        button.setGraphic(imageView);
        return button;
    }

    public void closeStage() {
        pickPieceStage.close();
    }

}