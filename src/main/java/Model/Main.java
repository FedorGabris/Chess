package Model;

import Controller.NameSelectController;
import View.NameSelectScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        NameSelectController nameSelectController = new NameSelectController(new NameSelectScreen(primaryStage));
        nameSelectController.displaySelectScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

