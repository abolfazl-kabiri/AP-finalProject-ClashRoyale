package controller;

import cards.Archer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.DataBase;
import sample.SmartBot;
import sample.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Profile controller.
 */
public class ProfileController {
    private User currentUser;
    private Stage stage;
    private Parent root;
    private ArrayList<ImageView> cardsPhotos;
    @FXML private Label usernameLabel;
    @FXML private Label levelLabel;
    @FXML private Button backButton;
    @FXML private Button backButtonHighlighted;
    @FXML private ProgressBar xpBar;
    @FXML private Label xpLabel;
    @FXML private ImageView selectedCard1;
    @FXML private ImageView selectedCard2;
    @FXML private ImageView selectedCard3;
    @FXML private ImageView selectedCard4;
    @FXML private ImageView selectedCard5;
    @FXML private ImageView selectedCard6;
    @FXML private ImageView selectedCard7;
    @FXML private ImageView selectedCard8;

    /**
     * Initialize.
     */
    public void initialize(){
        cardsPhotos = new ArrayList<>();
        cardsPhotos.add(selectedCard1);
        cardsPhotos.add(selectedCard2);
        cardsPhotos.add(selectedCard3);
        cardsPhotos.add(selectedCard4);
        cardsPhotos.add(selectedCard5);
        cardsPhotos.add(selectedCard6);
        cardsPhotos.add(selectedCard7);
        cardsPhotos.add(selectedCard8);
    }

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
        changeLabelText();
        setSelectedCards();
        fillBar();
    }

    /**
     * Set selected cards.
     */
    public void setSelectedCards(){
        if (currentUser.getDeck().size() != 0){
            for (int i = 0; i < currentUser.getDeck().size(); i++) {
                cardsPhotos.get(i).setImage(new Image(currentUser.getDeck().get(i).getPath()));
            }
        }
    }
    private void changeLabelText(){
        usernameLabel.setText(currentUser.getUsername());
        levelLabel.setText(String.valueOf(currentUser.getLevel()));
    }

    private void fillBar(){
        int soorat = currentUser.getXp() - DataBase.requiredXPForNextLevel(currentUser.getLevel() - 1);
        int makhraj = DataBase.requiredXPForNextLevel(currentUser.getLevel()) - DataBase.requiredXPForNextLevel(currentUser.getLevel() - 1);
        xpBar.setProgress(Double.valueOf(soorat) / makhraj);
        xpLabel.setText(soorat + "/" + makhraj);
    }
}