package controller;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Player;
import sample.User;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Main menu controller.
 */
public class MainMenuController {
    private String soundIn;
    private String soundOut;
    private User currentUser;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private Stage stage;
    private Parent root;
    private MediaPlayer player;
    @FXML private Button profileButton;
    @FXML private ImageView profPhoto;
    @FXML private ImageView profPhotoLowOpacity;
    @FXML private ImageView profTag;
    @FXML private Button battleDeckButton;
    @FXML private ImageView deckPhoto;
    @FXML private ImageView deckPhotoLowOpacity;
    @FXML private ImageView deckTag;
    @FXML private Button battleHistoryButton;
    @FXML private ImageView historyPhoto;
    @FXML private ImageView historyPhotoLowOpacity;
    @FXML private ImageView historyTag;
    @FXML private Button trainingCampButton;
    @FXML private ImageView campPhoto;
    @FXML private ImageView campPhotoLowOpacity;
    @FXML private ImageView campTag;
    @FXML private Button oneVOneButton;
    @FXML private ImageView onePhoto;
    @FXML private ImageView onePhotoLowOpacity;
    @FXML private ImageView oneTag;
    @FXML private Button twoVTwoButton;
    @FXML private ImageView twoPhoto;
    @FXML private ImageView twoPhotoLowOpacity;
    @FXML private ImageView twoTag;
    @FXML private Button logOutButton;
    @FXML private ImageView logOutPhoto;
    @FXML private ImageView logOutTag;
    @FXML private Pane errorPane;

    /**
     * Initialize.
     */
    public void initialize(){
        Media media = new Media(getClass().getResource("/sound effects and musics/new_menu_01.mp3").toString());
        player = new MediaPlayer(media);
        player.setVolume(0.2);
        player.setCycleCount(Timeline.INDEFINITE);
        player.play();

        profPhotoLowOpacity.setOpacity(0);
        deckPhotoLowOpacity.setOpacity(0);
        historyPhotoLowOpacity.setOpacity(0);
        campPhotoLowOpacity.setOpacity(0);
        onePhotoLowOpacity.setOpacity(0);
        twoPhotoLowOpacity.setOpacity(0);
        this.soundIn = ".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_006 (2).wav";
        this.soundOut = ".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_015.wav";
    }
    @FXML private void resizeProfPhoto(MouseEvent event){
        changeIntendedPhotoSize(soundIn, profPhotoLowOpacity,
                profPhoto, profTag,
                0, 0.8,
                -5, -5, 3);
    }
    @FXML private void shrinkProfPhoto(MouseEvent event){
        changeIntendedPhotoSize(soundOut, profPhotoLowOpacity,
                profPhoto, profTag,
                0.8, 0,
                5,5, -3);
    }
    @FXML private void resizeDeckPhoto(MouseEvent event){
        changeIntendedPhotoSize(soundIn, deckPhotoLowOpacity,
                deckPhoto, deckTag,
                0, 0.8,
                -5,-5,3);
    }
    @FXML private void shrinkDeckPhoto(MouseEvent event){
        changeIntendedPhotoSize(soundOut, deckPhotoLowOpacity,
                deckPhoto, deckTag,
                0.8, 0,
                5, 5, -3);
    }
    @FXML private void resizeHistoryPhoto(MouseEvent event){
        changeIntendedPhotoSize(soundIn, historyPhotoLowOpacity,
                historyPhoto, historyTag,
                0, 0.8,
                -5,-5, 3);
    }
    @FXML private void shrinkHistoryPhoto(MouseEvent event){
        changeIntendedPhotoSize(soundOut, historyPhotoLowOpacity,
                historyPhoto, historyTag,
                0.8, 0,
                5, 5, -3);
    }
    @FXML private void resizeCampPhoto(MouseEvent event){
        changeIntendedPhotoSize(soundIn, campPhotoLowOpacity,
                campPhoto, campTag,
                0, 0.8,
                -5, -5, 3);
    }
    @FXML private void shrinkCampPhoto(MouseEvent event){
        changeIntendedPhotoSize(soundOut, campPhotoLowOpacity,
                campPhoto, campTag,
                0.8, 0,
                5, 5, -3);
    }
    @FXML private void resizeOnePhoto(MouseEvent event){
        changeIntendedPhotoSize(soundIn, onePhotoLowOpacity,
                onePhoto, oneTag,
                0, 0.8,
                -5, -5, 3);
    }
    @FXML private void shrinkOnePhoto(MouseEvent event){
        changeIntendedPhotoSize(soundOut, onePhotoLowOpacity,
                onePhoto, oneTag,
                0.8, 0,
                5, 5, -3);
    }
    @FXML private void resizeTwoPhoto(MouseEvent event){
        changeIntendedPhotoSize(soundIn, twoPhotoLowOpacity,
                twoPhoto, twoTag,
                0, 0.8,
                -5, -5, 3);
    }
    @FXML private void shrinkTwoPhoto(MouseEvent event){
        changeIntendedPhotoSize(soundOut, twoPhotoLowOpacity,
                twoPhoto, twoTag,
                0.8, 0,
                5, 5, -3);
    }

    /**
     * Resize log out photo.
     *
     * @param event the event
     */
    public void resizeLogOutPhoto(MouseEvent event){
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundIn));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        logOutPhoto.setImage(new Image(".\\photos\\main menu images\\logout highlighted_00000.png"));
    }

    /**
     * Shrink log out photo.
     *
     * @param event the event
     */
    public void shrinkLogOutPhoto(MouseEvent event){
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundOut));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        logOutPhoto.setImage(new Image(".\\photos\\main menu images\\logout_00000.png"));
    }

    /**
     * Show profile.
     *
     * @param event the event
     */
    @FXML public void showProfile(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Profile.fxml"));
            stage = (Stage) profileButton.getScene().getWindow();
            root = loader.load();
            ProfileController profCon = loader.getController();
            profCon.setCurrentUser(currentUser);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("profile");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.stop();
    }

    /**
     * Show battle deck.
     *
     * @param event the event
     */
    @FXML public void showBattleDeck(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/BattleDeck.fxml"));
            stage = (Stage) profileButton.getScene().getWindow();
            root = loader.load();
            BattleDeckController deckCon = loader.getController();
            deckCon.setCurrentUser(currentUser);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("BattleDeck");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.stop();
    }

    /**
     * Show battle history.
     *
     * @param event the event
     */
    @FXML public void showBattleHistory(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/BattleHistory.fxml"));
            stage = (Stage) battleHistoryButton.getScene().getWindow();
            root = loader.load();
            BattleHistoryController battleCon = loader.getController();
            battleCon.setCurrentUser(currentUser);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("battle history");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.stop();
    }

    /**
     * Show training camp.
     *
     * @param event the event
     */
    @FXML public void showTrainingCamp(ActionEvent event){
        if(currentUser.getDeck().size() == 8) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ChooseBot.fxml"));
                stage = (Stage) trainingCampButton.getScene().getWindow();
                root = loader.load();
                ChooseBotController botCon = loader.getController();
                botCon.setCurrentUser(currentUser);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("choose bot");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.stop();
        }
        else{
            profileButton.setDisable(true);
            trainingCampButton.setDisable(true);
            battleDeckButton.setDisable(true);
            battleHistoryButton.setDisable(true);
            oneVOneButton.setDisable(true);
            twoVTwoButton.setDisable(true);
            FadeTransition ft = new FadeTransition(Duration.seconds(.4), errorPane);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.play();
                    profileButton.setDisable(false);
                    trainingCampButton.setDisable(false);
                    battleDeckButton.setDisable(false);
                    battleHistoryButton.setDisable(false);
                    oneVOneButton.setDisable(false);
                    twoVTwoButton.setDisable(false);
                }
            };
            timer.schedule(task, 2000);
        }
    }

    /**
     * Show socket based sections.
     *
     * @param event the event
     */
    @FXML public void showSocketBasedSections(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/emptyWindows.fxml"));
            stage = (Stage) battleHistoryButton.getScene().getWindow();
            root = loader.load();
            SocketBasedSectionsController socketCon = loader.getController();
            socketCon.setCurrentUser(currentUser);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("1v1 / 2v2");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.stop();
    }
    private void changeIntendedPhotoSize(String soundEffectPath,
                                         ImageView lowOpacityPhoto, ImageView photo, ImageView tag,
                                         double lowOpacityValue, double highOpacityValue,
                                         int byX, int byY, int sizeIncrement){
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundEffectPath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        FadeTransition ft = new FadeTransition(Duration.seconds(0.4), lowOpacityPhoto);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.4), lowOpacityPhoto);
        ft.setFromValue(lowOpacityValue);
        ft.setToValue(highOpacityValue);
        tt.setByY(byY);
        tt.setByX(byX);
        ParallelTransition pt = new ParallelTransition(ft,tt);
        pt.play();
        photo.setFitWidth(photo.getFitHeight() + sizeIncrement);
        photo.setFitHeight(photo.getFitHeight() + sizeIncrement);
        tag.setFitWidth(tag.getFitHeight() + sizeIncrement);
        tag.setFitHeight(tag.getFitHeight() + sizeIncrement);
    }

    /**
     * Log out.
     *
     * @param event the event
     */
    public void logOut(ActionEvent event){
        clip.stop();
        player.stop();
        try {
            stage = (Stage) logOutButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException io){
            io.printStackTrace();
        }
    }

    /**
     * Sets current user.
     *
     * @param user the user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}
