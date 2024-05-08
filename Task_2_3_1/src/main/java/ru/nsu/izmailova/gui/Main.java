package ru.nsu.izmailova.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class of Snake game.
 * Starts the game and loads the FXML file
 */
public final class Main extends Application {

    public static void main(final String[] args) {
        Application.launch(args);
    }

    @Override
    //primaryStage - главное окно приложения
    public void start(final Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/ru/nsu/izmailova/snake.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
