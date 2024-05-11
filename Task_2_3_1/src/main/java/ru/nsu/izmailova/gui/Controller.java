package ru.nsu.izmailova.gui;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import ru.nsu.izmailova.engine.Engine;
import ru.nsu.izmailova.engine.SnakeGame;
import ru.nsu.izmailova.mechanic.snakes.Snake;

/**
 * Controller class.
 */
public class Controller implements Initializable {
    private static final int FPS = 10;

    public Canvas upperCanvas;
    public Label scoreLabel;
    public Label infoLabel;

    private SnakeGame loop;
    private Engine gameEngine;

    /**
     * Initializes the controller by setting up the game environment, configuring the canvas,
     * and preparing the game engine.
     *
     * @param url relative paths for the root object, or null if the location is not known.
     * @param resourceBundle localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        double width = upperCanvas.getWidth();
        double height = upperCanvas.getHeight();

        GraphicsContext context = upperCanvas.getGraphicsContext2D();

        upperCanvas.setFocusTraversable(true);
        upperCanvas.setOnKeyPressed(this::handler);

        Painter painter = new Painter(scoreLabel, infoLabel, context);

        loop = new SnakeGame(width, height, context, painter);
        gameEngine = new Engine(FPS, loop);

        gameEngine.start();
    }

    /**
     * Handler of the game, used to control the user snake.
     *
     * @param e some key is pressed
     */
    private void handler(KeyEvent e) {

        Snake snake = loop.getSnakeLobby().getUserSnake();

        if (loop.isKeyPressed()) {
            return;
        }

        switch (e.getCode()) {
            case W:
                if (!loop.getPause()) {
                    loop.setKeyPressed();
                    snake.setUp();
                }
                break;
            case S:
                if (!loop.getPause()) {
                    loop.setKeyPressed();
                    snake.setDown();
                }
                break;
            case A:
                if (!loop.getPause()) {
                    loop.setKeyPressed();
                    snake.setLeft();
                }
                break;
            case D:
                if (!loop.getPause()) {
                    loop.setKeyPressed();
                    snake.setRight();
                }
                break;
            case ENTER:
                if (!snake.isSafe()) {
                    gameEngine.reset();
                }
                break;
            case ESCAPE:
                if (loop.getPause()) {
                    gameEngine.start();
                } else {
                    gameEngine.pause();
                }
                break;
            default:
                break;
        }
    }
}
