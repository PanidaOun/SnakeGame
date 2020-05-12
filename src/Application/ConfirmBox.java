package Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


/**
 * Class that create ConfirmBox to ask player again that player really want to exit.
 * @author Panida Ounnaitham
 */
public class ConfirmBox {

    private Stage window;
    private boolean answer;

    /**
     * To create a window that ask players again that player really want to exit.
     * @param title is a title of the window.
     * @param message is a message that you want to put in the window.
     * @return boolean yes if player want to exit or no if player don't want to exit.
     */
    public boolean display(String title, String message) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        VBox layout = initComponents(message);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    /**
     * initialize and add components to the Vbox to create a window.
     * @return Vbox.
     */
    private VBox initComponents(String message){
        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setStyle("-fx-background-color:#e91010");
        noButton.setStyle("-fx-background-color:#6CCF32");
        yesButton.setOnAction(new YesButtonHandler());
        noButton.setOnAction(new NoButtonHandler());

        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #B8DFD7");
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        return layout;
    }

    /**
     * Class to check that players want to exit.
     */
    class YesButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            answer = true;
            window.close();
        }
    }

    /**
     * Class to check that players don't want to exit.
     */
    class NoButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            answer = false;
            window.close();
        }
    }
}

