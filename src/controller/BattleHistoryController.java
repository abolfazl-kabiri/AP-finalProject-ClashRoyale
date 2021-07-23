package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type Battle history controller.
 */
public class BattleHistoryController implements Initializable {
    private User currentUser;
    private ArrayList<String> battles;
    private Stage stage;
    private Parent root;
    @FXML private Button backButton;
    @FXML private ListView<String> listView;
    @FXML private Button backButtonHighlighted;

    /**
     * Highlight back.
     *
     * @param event the event
     */
    @FXML public void highlightBack(MouseEvent event){
        backButton.setVisible(false);
        backButton.setDisable(true);
        backButtonHighlighted.setVisible(true);
        backButtonHighlighted.setDisable(false);
    }

    /**
     * Un highlighted back.
     *
     * @param event the event
     */
    @FXML public void unHighlightedBack(MouseEvent event){
        backButton.setVisible(true);
        backButton.setDisable(false);
        backButtonHighlighted.setVisible(false);
        backButtonHighlighted.setDisable(true);
    }

    /**
     * Back to menu.
     *
     * @param event the event
     */
    @FXML void backToMenu(ActionEvent event) {
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
     * @param user the user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        battles = currentUser.getBattleHistory();
        for (int i = battles.size() - 1; i>=0; i--)
            listView.getItems().add(battles.get(i));

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setMouseTransparent(true);
        listView.setFocusTraversable(false);

    }
}
