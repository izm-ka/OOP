module ru.nsu.izmailova {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ru.nsu.izmailova.gui to javafx.fxml;
    exports ru.nsu.izmailova.gui;
}