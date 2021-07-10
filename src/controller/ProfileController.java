package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.User;

import java.io.IOException;

public class ProfileController {

    private User currentUser;

    private Stage stage;
    private Parent root;
    @FXML private Label usernameLabel;

    @FXML private Label levelLabel;

    @FXML private Button backButton;

    @FXML
    void backToMenu(ActionEvent event) {
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


    public void setCurrentUser(User user) {
        this.currentUser = user;
        changeLabelText();
    }

    private void changeLabelText(){
        usernameLabel.setText(currentUser.getUsername());
        levelLabel.setText(String.valueOf(currentUser.getLevel()));
    }
}
