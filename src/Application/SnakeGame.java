package Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Class that use to create a snake game .
 *
 * @author Panida Ounnaitham
 */
public class SnakeGame {
    private int score = 0;
    private double speed = 0.2;
    private int foodColor = 0;
    private int width = 20;
    private int height = 20;
    private int foodX = 0;
    private int foodY = 0;
    private List<Corner> snake = new ArrayList<>();
    private Dir direction = Dir.left;
    private boolean gameOver = false;
    private Random rand = new Random();

    /**
     * To start a snake game.
     */
    public void createGameLoop(GraphicsContext gc){
        newFood();
        score = 0;
        gameOver = false;
        snake.add(new Corner(width , height/2 ));
        snake.add(new Corner(width , height/2 ));
        snake.add(new Corner(width, height/2 ));
        AnimationTimer animation = new AnimationTimer() {
            long lastTick = 0;
            public void handle(long now) {
                if (now - lastTick > 1000000000 / speed) {
                    lastTick = now;
                    snakeGame(gc);
                }
            }
        };
        animation.start();
    }

    /**
     * To specify direction of the snake by using arrow in keyboard.
     */
    public void createKeyListeners(Scene gameScene) {
        gameScene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.UP) {
                direction = Dir.up;
            }
            if (key.getCode() == KeyCode.LEFT) {
                direction = Dir.left;
            }
            if (key.getCode() == KeyCode.DOWN) {
                direction = Dir.down;
            }
            if (key.getCode() == KeyCode.RIGHT) {
                direction = Dir.right;
            }
        });
    }

    /**
     * To create a corner that use to build snake and food.
     */
    public static class Corner {
        int x;
        int y;

        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void snakeGame(GraphicsContext gc) {

        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER\n"+"Your score is "+score, 50, 180);

            return;
        }

        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        switch (direction) {
            case up:
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    gameOver = true;
                }
                break;
            case down:
                snake.get(0).y++;
                if (snake.get(0).y > height) {
                    gameOver = true;
                }
                break;
            case left:
                snake.get(0).x--;
                if (snake.get(0).x < 0) {
                    gameOver = true;
                }
                break;
            case right:
                snake.get(0).x++;
                if (snake.get(0).x > width) {
                    gameOver = true;
                }
                break;
        }

        // when snake eat food, so application create a new one.
        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Corner(-1, -1));
            newFood();
        }

        // To check that head of a snake runs into its self or not.
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
            }
        }

        // To fill background color.
        gc.setFill(Color.BLACK);
        int cornersize = 25;
        gc.fillRect(0, 0, width * cornersize, height * cornersize);

        // To show and update scores.
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + score, 330, 30);

        // To random a food color.
        Color cc = Color.WHITE;

        switch (foodColor) {
            case 0:
                cc = Color.PURPLE;
                break;
            case 1:
                cc = Color.LIGHTBLUE;
                break;
            case 2:
                cc = Color.YELLOW;
                break;
            case 3:
                cc = Color.PINK;
                break;
            case 4:
                cc = Color.ORANGE;
                break;
        }
        gc.setFill(cc);
        gc.fillOval(foodX * cornersize, foodY * cornersize, cornersize, cornersize);

        // To fill color of the snake.
        for (Corner c : snake) {
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 2, cornersize - 2);
        }
    }

    /**
     * To create new food for snake.
     */
    public void newFood() {
        foodX = rand.nextInt(width);
        foodY = rand.nextInt(height);

        for (Corner c : snake) {
            if (c.x == foodX && c.y == foodY) {
                newFood();
            }
        }
        foodColor = rand.nextInt(5);
        speed++;
        score++;

    }
}