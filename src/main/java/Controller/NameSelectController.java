package Controller;

import Controller.Exceptions.EqualNamesException;
import Controller.Exceptions.NameNotEnteredException;
import Model.Grid;
import View.Board;
import View.NameSelectScreen;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NameSelectController {
    private String whitePlayerName;
    private String blackPlayerName;
    private final NameSelectScreen nameSelectScreen;

    public NameSelectController(NameSelectScreen nameSelectScreen) {
        this.nameSelectScreen = nameSelectScreen;
    }

    public void displaySelectScreen() {
        nameSelectScreen.displayScreen();
        Button startButton = nameSelectScreen.getStartButton();
        startButton.setOnAction(actionEvent -> startButtonEvent());
    }

    public void startButtonEvent() {
        String whitePlayerName = nameSelectScreen.getWhitePlayerField().getText().trim();
        String blackPlayerName = nameSelectScreen.getBlackPlayerField().getText().trim();
        try {
            if (whitePlayerName.isEmpty() || blackPlayerName.isEmpty()) {
                throw new NameNotEnteredException("You have to pick a name!");
            }
            else if (whitePlayerName.equals(blackPlayerName)) {
                throw new EqualNamesException("You cannot have the same name!");
            }
            this.whitePlayerName = whitePlayerName;
            this.blackPlayerName = blackPlayerName;
            startGame();
        } catch (EqualNamesException | NameNotEnteredException e) {
            nameSelectScreen.showAlert(e.getMessage());
        }

    }

    public void startGame() {
        nameSelectScreen.stageClose();
        Board board = new Board();
        board.start(new Stage());
        TurnController turnController = new TurnController(whitePlayerName, blackPlayerName, board.getBoard(), board.getButtonsMap(), board);
        Grid grid = new Grid();
        grid.initializeGrid(turnController);
        turnController.setGrid(grid);
    }
}
