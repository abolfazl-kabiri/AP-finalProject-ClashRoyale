package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.User;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MainMenuController {

    private User currentUser;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private Stage stage;
    private Parent root;
    @FXML private Button profileButton;
    @FXML private ImageView profPhoto;
    @FXML private ImageView profTag;
    @FXML private Button battleDeckButton;
    @FXML private ImageView deckPhoto;
    @FXML private ImageView deckTag;
    @FXML private Button battleHistoryButton;
    @FXML private ImageView historyPhoto;
    @FXML private ImageView historyTag;
    @FXML private Button trainingCampButton;
    @FXML private ImageView campPhoto;
    @FXML private ImageView campTag;
    @FXML private Button oneVOneButton;
    @FXML private ImageView onePhoto;
    @FXML private ImageView oneTag;
    @FXML private Button twoVTwoButton;
    @FXML private ImageView twoPhoto;
    @FXML private ImageView twoTag;
    @FXML private void resizeProfPhoto(MouseEvent event){
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_006 (2).wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        profPhoto.setFitWidth(profPhoto.getFitHeight() + 3);
        profPhoto.setFitHeight(profPhoto.getFitHeight() + 3);
        profTag.setFitWidth(profTag.getFitHeight() + 3);
        profTag.setFitHeight(profTag.getFitHeight() + 3);
    }
    @FXML private void shrinkProfPhoto(MouseEvent event){
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_015.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        profPhoto.setFitWidth(profPhoto.getFitHeight() - 3);
        profPhoto.setFitHeight(profPhoto.getFitHeight() - 3);
        profTag.setFitWidth(profTag.getFitHeight() - 3);
        profTag.setFitHeight(profTag.getFitHeight() - 3);
    }
    @FXML private void resizeDeckPhoto(MouseEvent event){
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_006 (2).wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        deckPhoto.setFitWidth(deckPhoto.getFitHeight() + 3);
        deckPhoto.setFitHeight(deckPhoto.getFitHeight() + 3);
        deckTag.setFitWidth(deckTag.getFitHeight() + 3);
        deckTag.setFitHeight(deckTag.getFitHeight() + 3);
    }
    @FXML private void shrinkDeckPhoto(MouseEvent event){
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_015.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        deckPhoto.setFitWidth(deckPhoto.getFitHeight() - 3);
        deckPhoto.setFitHeight(deckPhoto.getFitHeight() - 3);
        deckTag.setFitWidth(deckTag.getFitHeight() - 3);
        deckTag.setFitHeight(deckTag.getFitHeight() - 3);
    }
    @FXML private void resizeHistoryPhoto(MouseEvent event){
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_006 (2).wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        historyPhoto.setFitWidth(historyPhoto.getFitHeight() + 3);
        historyPhoto.setFitHeight(historyPhoto.getFitHeight() + 3);
        historyTag.setFitWidth(historyTag.getFitHeight() + 3);
        historyTag.setFitHeight(historyTag.getFitHeight() + 3);
    }
    @FXML private void shrinkHistoryPhoto(MouseEvent event){
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_015.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        historyPhoto.setFitWidth(historyPhoto.getFitHeight() - 3);
        historyPhoto.setFitHeight(historyPhoto.getFitHeight() - 3);
        historyTag.setFitWidth(historyTag.getFitHeight() - 3);
        historyTag.setFitHeight(historyTag.getFitHeight() - 3);
    }
    @FXML private void resizeCampPhoto(MouseEvent event){
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_006 (2).wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        campPhoto.setFitWidth(campPhoto.getFitHeight() + 3);
        campPhoto.setFitHeight(campPhoto.getFitHeight() + 3);
        campTag.setFitWidth(campTag.getFitHeight() + 3);
        campTag.setFitHeight(campTag.getFitHeight() + 3);
    }
    @FXML private void shrinkCampPhoto(MouseEvent event){
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_015.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        campPhoto.setFitWidth(campPhoto.getFitHeight() - 3);
        campPhoto.setFitHeight(campPhoto.getFitHeight() - 3);
        campTag.setFitWidth(campTag.getFitHeight() - 3);
        campTag.setFitHeight(campTag.getFitHeight() - 3);
    }
    @FXML private void resizeOnePhoto(MouseEvent event){
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_006 (2).wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        onePhoto.setFitWidth(onePhoto.getFitHeight() + 3);
        onePhoto.setFitHeight(onePhoto.getFitHeight() + 3);
        oneTag.setFitWidth(oneTag.getFitHeight() + 3);
        oneTag.setFitHeight(oneTag.getFitHeight() + 3);
    }
    @FXML private void shrinkOnePhoto(MouseEvent event){
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_015.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        onePhoto.setFitWidth(onePhoto.getFitHeight() - 3);
        onePhoto.setFitHeight(onePhoto.getFitHeight() - 3);
        oneTag.setFitWidth(oneTag.getFitHeight() - 3);
        oneTag.setFitHeight(oneTag.getFitHeight() - 3);
    }
    @FXML private void resizeTwoPhoto(MouseEvent event){
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_006 (2).wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        twoPhoto.setFitWidth(twoPhoto.getFitHeight() + 3);
        twoPhoto.setFitHeight(twoPhoto.getFitHeight() + 3);
        twoTag.setFitWidth(twoTag.getFitHeight() + 3);
        twoTag.setFitHeight(twoTag.getFitHeight() + 3);
    }
    @FXML private void shrinkTwoPhoto(MouseEvent event){
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\Whoosh_Swoosh_Swing_Fencing_Sword_Fienup_015.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        twoPhoto.setFitWidth(twoPhoto.getFitHeight() - 3);
        twoPhoto.setFitHeight(twoPhoto.getFitHeight() - 3);
        twoTag.setFitWidth(twoTag.getFitHeight() - 3);
        twoTag.setFitHeight(twoTag.getFitHeight() - 3);
    }
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
    }
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
    }
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
    }



    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}
