package Application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class that use to create a GUI for snake game and use to run an application.
 *
 * @author Panida Ounnaitham
 */
public class GUI extends Application {
    private TextField inputName;
    private Stage window;
    private Scene screen2;
    private Label label2;
    SnakeGame snakegame = new SnakeGame();

    /**
     * Start method for the application to create first scene and switch to second scene.
     * @param stage is the stage for the application.
     */
    @Override
    public void start(Stage stage)  {
        window = stage;
        VBox layout1 = initComponents();
        Scene screen1 = new Scene(layout1, 500, 540);

        VBox root = new VBox();
        Canvas c = new Canvas(500, 500);
        GraphicsContext gc = c.getGraphicsContext2D();
        label2 = new Label("PLAYER NAME : ");
        label2.setFont(Font.font(30));
        root.getChildren().addAll(label2, c);
        screen2 = new Scene(root, 500, 540);
        snakegame.createKeyListeners(screen2);
        snakegame.createGameLoop(gc);
        window.setScene(screen1);
        window.setTitle("Snake Game");
        Image icon = new Image(getClass().getResourceAsStream("img/snakeja.png"));
        window.getIcons().add(icon);
        window.show();

    }

    /**
     * initialize and add components to the Vbox to create a first scene.
     * @return first Vbox.
     */
    private VBox initComponents(){
        Label label1 = new Label("SNAKE GAME");
        label1.setFont(Font.loadFont(getClass().getResourceAsStream("text/joystix monospace.ttf"), 60));
        label1.setStyle("-fx-text-fill: black; -fx-background-color: #FFD33B");
        inputName = new TextField();

        Button buttonHowToPlay = new Button("HOW TO PLAY");
        buttonHowToPlay.setStyle("-fx-background-color:#52A3D8");
        buttonHowToPlay.setTextFill(Color.web("#ffffff", 0.8));
        buttonHowToPlay.setFont(Font.loadFont(getClass().getResourceAsStream("text/joystix monospace.ttf"), 30));
        buttonHowToPlay.setOnAction(new HowToPlayHandler());

        Button buttonStart = new Button("START");
        buttonStart.setStyle("-fx-background-color:#066424");
        buttonStart.setTextFill(Color.web("#ffffff", 0.8));
        buttonStart.setFont(Font.loadFont(getClass().getResourceAsStream("text/joystix monospace.ttf"), 30));
        buttonStart.setOnAction(new ConfirmHandler());

        Button buttonExit = new Button("Exit");
        buttonExit.setStyle("-fx-background-color:#e91010");
        buttonExit.setTextFill(Color.web("#ffffff", 0.8));
        buttonExit.setFont(Font.loadFont(getClass().getResourceAsStream("text/joystix monospace.ttf"), 30));
        buttonExit.setOnAction(e -> closeProgram());

        VBox layout1 = new VBox();
        layout1.setSpacing(20);
        layout1.setAlignment(Pos.CENTER);
        Image backgroundImg = new Image(getClass().getResourceAsStream("img/snakewallpaper.jpg"));
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        layout1.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
        layout1.getChildren().addAll(label1, inputName,buttonHowToPlay, buttonStart, buttonExit);
        return layout1;
    }

    /**
     * To check if a string is a number.
     * @param s is a string that want to check.
     * @return false if string is a number, and return true if string is not a number.
     */
    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Class to check that players enter their name already and they do not input a number into the input name
     * then switch scene to start a game.
     */
    class ConfirmHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String text1 = inputName.getText();
            if ((text1.isEmpty() )){
                inputName.setStyle("-fx-text-box-border: red");
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText(null);
                error.setContentText("Please enter your name!!");
                error.showAndWait();
            }
            else if(isInteger(text1)) {
                inputName.setStyle("-fx-text-box-border: red");
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText(null);
                error.setContentText("Please enter your name not integer!!");
                error.showAndWait();
                inputName.setText("");
            }
            else {
                window.setScene(screen2);
                label2.setText("PLAYER NAME : " + inputName.getText());
                label2.setFont(Font.loadFont(getClass().getResourceAsStream("text/joystix monospace.ttf"), 25));
            }
        }
    }

    /**
     * Class to create alert that show how to play game when player click button how to play.
     */
    class HowToPlayHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Alert howToPlay = new Alert(Alert.AlertType.INFORMATION);
            howToPlay.setTitle("HOW TO PLAY");
            howToPlay.setHeaderText("                        How to play SNAKE GAME");
            ImageView howToPlayIMG = new ImageView(getClass().getResource("img/howtoplay.png").toExternalForm());
            howToPlay.setGraphic(howToPlayIMG);
            howToPlay.setContentText(
                    "    \uD83D\uDC0D The player controls a snake on a bordered plane by an arrow in keyboard." +
                    "As it moves forward,it leaves a trail behind, resembling a moving snake." +
                    "The end of the trail is in a fixed position, so the snake continually gets longer if they eat the food." +
                    "For with every meal,the player will get the point but the snake also moves faster ." +
                    "The snake has a specific length, so there is a moving tail a fixed number of units away from the head." +
                    "The player will lose when the snake runs into the screen border,  or itself. \uD83D\uDC0D");
            howToPlay.getDialogPane().setPrefSize(500,500);
            howToPlay.showAndWait();

        }
    }

    /**
     * To ask player again that player really want to exit when player click button exit.
     */
    private void closeProgram() {
        ConfirmBox c = new ConfirmBox();
        boolean answer = c.display("Exit","Sure you want to exit?");
        if(answer) {
            window.close();
        }
    }

    /**
     * To run the application.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
