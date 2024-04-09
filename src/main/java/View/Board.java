package View;

import Controller.TurnController;
import Model.Grid;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Board extends Application {

    public static void printImage(GridPane board, int row, int col, String imageLocation) {
        Image pawnImage = new Image(Board.class.getResourceAsStream(imageLocation));
        ImageView imageView = new ImageView(pawnImage);
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        Button pawnButton = (Button) board.getChildren().get(row + 8 * col);
        pawnButton.setGraphic(imageView);
    }

    public static void removeImage(GridPane board, int row, int column) {
        Button button = (Button) board.getChildren().get(row + 8 * column);
        button.setGraphic(null);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane board = new GridPane();
        TurnController turnController = new TurnController(board);
        Grid grid = new Grid();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Button button = new Button();
                button.setPrefSize(80, 80);
                if ((row + col) % 2 == 0) {
                    button.setStyle("-fx-base: #FFF8DC;");
                } else {
                    button.setStyle("-fx-base: #CD853F;");
                }
                int finalRow = row;
                int finalCol = col;
                button.setOnAction(event -> turnController.chooseFigure(finalRow, finalCol));
                board.add(button, row, col);

            }
        }
        grid.initializeGrid(turnController);
        turnController.setGrid(grid);

        Scene scene = new Scene(board, 640, 640);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess");
        primaryStage.show();
    }

    public static void possibleMoveDisplay(GridPane board, ArrayList<Integer> possibleMoves) {
        for (Integer possibleMove : possibleMoves) {
            int col = possibleMove % 10;
            int row = (possibleMove - col) / 10;
            Button button = (Button) board.getChildren().get(row + 8 * col);
            button.setStyle("-fx-base: #90EE90;");
        }
    }

    public static void resetBoardDisplay(GridPane board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Button button = (Button) board.getChildren().get(row + 8 * col);
                if ((row + col) % 2 == 0) {
                    button.setStyle("-fx-base: #FFF8DC;");
                } else {
                    button.setStyle("-fx-base: #CD853F;");
                }
            }
        }
    }

    public static void pointOutCurrentPiece(GridPane board, int row, int col) {
        Button button = (Button) board.getChildren().get(row + 8 * col);
        button.setStyle("-fx-base: #B22222;");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

