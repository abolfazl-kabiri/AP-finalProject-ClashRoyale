package controller;

import javafx.animation.FadeTransition;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.*;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Choose bot controller.
 */
public class ChooseBotController {
    private User currentUser;
    private Bot bot;
    private Stage stage;
    private Parent root;
    private boolean isEasySelected;
    private boolean isMediumSelected;
    private boolean isHardSelected;
    @FXML private Button easyButton;
    @FXML private ImageView easyPhoto;
    @FXML private Button mediumButton;
    @FXML private ImageView mediumPhoto;
    @FXML private Button hardButton;
    @FXML private ImageView hardPhoto;
    @FXML private Button playButton;
    @FXML private ImageView playPhoto;
    @FXML private Pane errorWindow;
    @FXML private Button backButton;
    @FXML private ImageView backPhoto;

    /**
     * Initialize.
     */
    public void initialize(){
        this.isEasySelected = false;
        this.isMediumSelected = false;
        this.isHardSelected = false;
    }

    /**
     * Highlight easy.
     *
     * @param event the event
     */
    @FXML public void highlightEasy(MouseEvent event){
        if (!isEasySelected)
            easyPhoto.setImage(new Image(".\\photos\\bot menu\\easy highlighted_00000.png"));
    }

    /**
     * Highlight medium.
     *
     * @param event the event
     */
    @FXML public void highlightMedium(MouseEvent event){
        if (!isMediumSelected)
            mediumPhoto.setImage(new Image(".\\photos\\bot menu\\medium highlighted_00000.png"));
    }

    /**
     * Unhighlight easy.
     *
     * @param event the event
     */
    @FXML public void unhighlightEasy(MouseEvent event){
        if (!isEasySelected)
            easyPhoto.setImage(new Image(".\\photos\\bot menu\\easy_00000.png"));

    }

    /**
     * Unhighlight medium.
     *
     * @param event the event
     */
    @FXML public void unhighlightMedium(MouseEvent event){
        if (!isMediumSelected)
            mediumPhoto.setImage(new Image(".\\photos\\bot menu\\medium_00000.png"));
    }

    /**
     * Highlight hard.
     *
     * @param event the event
     */
    @FXML public void highlightHard(MouseEvent event){
        if (!isHardSelected)
            hardPhoto.setImage(new Image(".\\photos\\bot menu\\hard highlighted_00000.png"));
    }

    /**
     * Unhighlight hard.
     *
     * @param event the event
     */
    @FXML public void unhighlightHard(MouseEvent event){
        if (!isHardSelected)
            hardPhoto.setImage(new Image(".\\photos\\bot menu\\hard_00000.png"));
    }

    /**
     * Choose easy.
     *
     * @param event the event
     */
    @FXML public void chooseEasy(ActionEvent event){
        isMediumSelected = false;
        isHardSelected = false;
        isEasySelected = true;
        bot = new Bot(currentUser.getDeck(), currentUser.getLevel());
        hardPhoto.setImage(new Image(".\\photos\\bot menu\\hard_00000.png"));
        mediumPhoto.setImage(new Image(".\\photos\\bot menu\\medium_00000.png"));
        easyPhoto.setImage(new Image(".\\photos\\bot menu\\easy choosed_00000.png"));
    }

    /**
     * Choose medium.
     *
     * @param event the event
     */
    @FXML public void chooseMedium(ActionEvent event){
        isMediumSelected = true;
        isEasySelected = false;
        isHardSelected = false;
        bot = new SmartBot(currentUser.getDeck(), currentUser.getLevel());
        hardPhoto.setImage(new Image(".\\photos\\bot menu\\hard_00000.png"));
        easyPhoto.setImage(new Image(".\\photos\\bot menu\\easy_00000.png"));
        mediumPhoto.setImage(new Image(".\\photos\\bot menu\\medium choosed_00000.png"));
    }

    /**
     * Choose hard.
     *
     * @param event the event
     */
    @FXML public void chooseHard(ActionEvent event){
        isEasySelected = false;
        isMediumSelected = false;
        isHardSelected = true;
        bot = new SmarterBot(currentUser.getDeck(), currentUser.getLevel());
        easyPhoto.setImage(new Image(".\\photos\\bot menu\\easy_00000.png"));
        mediumPhoto.setImage(new Image(".\\photos\\bot menu\\medium_00000.png"));
        hardPhoto.setImage(new Image(".\\photos\\bot menu\\hard choosed_00000.png"));
    }

    /**
     * Highlight play.
     *
     * @param event the event
     */
    @FXML public void highlightPlay(MouseEvent event){
        playPhoto.setImage(new Image(".\\photos\\bot menu\\play highlighted_00000.png"));
    }

    /**
     * Unhighlight play.
     *
     * @param event the event
     */
    @FXML public void unhighlightPlay(MouseEvent event){
        playPhoto.setImage(new Image(".\\photos\\bot menu\\play_00000.png"));
    }

    /**
     * Play.
     *
     * @param event the event
     */
    @FXML public void play(ActionEvent event){
        if (bot != null){
            try {
                Player player = new Player(currentUser.getDeck(), currentUser.getLevel());
                currentUser.setPlayer(player);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TrainingCamp.fxml"));
                stage = (Stage) playButton.getScene().getWindow();
                root = loader.load();
                TrainingCampController campCon = loader.getController();
                campCon.setCurrentUser(currentUser);
                campCon.setBot(bot);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("training camp");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            errorWindow.setVisible(true);
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), errorWindow);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
            playButton.setDisable(true);
            mediumButton.setDisable(true);
            easyButton.setDisable(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.play();
                    playButton.setDisable(false);
                    mediumButton.setDisable(false);
                    easyButton.setDisable(false);
                    errorWindow.setVisible(false);
                }
            };
            timer.schedule(task, 2000);
        }
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
     * Highlight back.
     *
     * @param event the event
     */
    @FXML public void highlightBack(MouseEvent event){
        backPhoto.setImage(new Image(".\\photos\\back highlighted_00000.png"));
    }

    /**
     * Un highlighted back.
     *
     * @param event the event
     */
    @FXML public void unHighlightedBack(MouseEvent event){
        backPhoto.setImage(new Image(".\\photos\\back_00000.png"));
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
