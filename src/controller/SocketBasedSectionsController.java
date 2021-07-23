package controller;

import controller.MainMenuController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.User;

import java.io.IOException;

/**
 * The type Socket based sections controller.
 */
public class SocketBasedSectionsController {
    private Stage stage;
    private Parent root;
    private User currentUser;
    @FXML private Button backButton;
    @FXML private ImageView backPhoto;

    /**
     * Highlight back.
     *
     * @param event the event
     */
    @FXML public void highlightBack(MouseEvent event){
        backPhoto.setImage(new Image(".\\photos\\back highlighted_00000.png"));
        backButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                backPhoto.setImage(new Image(".\\photos\\back_00000.png"));
            }
        });
    }

    /**
     * Back to menu.
     *
     * @param event the event
     */
    @FXML public void backToMenu(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainMenu.fxml"));
            stage = (Stage) backButton.getScene().getWindow();
            root = loader.load();
            MainMenuController menuController = loader.getController();
            menuController.setCurrentUser(currentUser);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("main menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets current user.
     *
     * @param currentUser the current user
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
