module Game {
    requires javafx.fxml;
    requires javafx.controls;

    exports View;
    exports Model;
    exports Controller;
    exports Controller.Exceptions;
}