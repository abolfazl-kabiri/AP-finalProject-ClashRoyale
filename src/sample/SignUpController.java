package sample;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML private TextField usernameTextField;
    @FXML private TextField visiblePassword;
    @FXML private TextField visibleRepeatPassword;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField repeatPassField;
    @FXML private Button signUpButton;
    @FXML private Button signUpHighlightedButton;
    @FXML private Button showPasswordButton;
    @FXML private Button hidePasswordButton;
    @FXML private Button showRepeatPasswordButton;
    @FXML private Button hideRepeatPasswordButton;
    @FXML private Hyperlink loginLink;
    @FXML private Label wrongPassword;
//    private MediaPlayer player;

    private AudioInputStream audioInputStream;
    private Clip clip;
    private Stage stage;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       try {
           audioInputStream = AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\LoginMusic.wav"));
           clip = AudioSystem.getClip();
           clip.open(audioInputStream);
           clip.start();
       } catch (LineUnavailableException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
       }
       visiblePassword.setVisible(false);
       visiblePassword.setDisable(true);
       hidePasswordButton.setDisable(true);
       hidePasswordButton.setVisible(false);
       visibleRepeatPassword.setDisable(true);
       visibleRepeatPassword.setVisible(false);
       hideRepeatPasswordButton.setVisible(false);
       hideRepeatPasswordButton.setDisable(true);
       signUpHighlightedButton.setVisible(false);
       signUpHighlightedButton.setDisable(true);
//        Media media =
//                new Media(Paths.get(new File("LoginMusic.wav").getAbsolutePath()).toUri().toString());
//        player = new MediaPlayer(media);
//        player.setCycleCount(Timeline.INDEFINITE);
//        player.setVolume(0.3);
//        player.play();
    }

    @FXML public void linkToLogin(ActionEvent event) {
//        player.stop();
        clip.stop();

        try {
            stage = (Stage) loginLink.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException io){
            io.printStackTrace();
        }
    }

    @FXML public void signUp(ActionEvent event) {

        String username = usernameTextField.getText();
        System.out.println(username);
        File file = new File(username+".bin");
        if(file.exists()){
            wrongPassword.setText("this username is already taken");
        } else {
            String password = passwordField.getText();
            String repeatedPassword = repeatPassField.getText();
            if(password.equals(repeatedPassword)){
                User user = new User(username,password);
                //        player.stop();
                clip.stop();
                loadMainMenu(user);
            } else {
                wrongPassword.setText("different passwords");
            }
        }
    }

    private void loadMainMenu(User user){
        try {
            stage = (Stage) usernameTextField.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("main menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML public void showPassword(ActionEvent event){
        showPasswordButton.setVisible(false);
        showPasswordButton.setDisable(true);
        hidePasswordButton.setDisable(false);
        hidePasswordButton.setVisible(true);
        visiblePassword.setVisible(true);
        visiblePassword.setDisable(false);
        visiblePassword.setText(passwordField.getText());
        passwordField.setDisable(true);
        passwordField.setVisible(false);
    }
    @FXML public void hidePassword(ActionEvent event){
        showPasswordButton.setVisible(true);
        showPasswordButton.setDisable(false);
        hidePasswordButton.setDisable(true);
        hidePasswordButton.setVisible(false);
        visiblePassword.setVisible(false);
        visiblePassword.setDisable(true);
        passwordField.setText(visiblePassword.getText());
        passwordField.setDisable(false);
        passwordField.setVisible(true);
    }
    @FXML public void showRepeatPassword(ActionEvent event){
        showRepeatPasswordButton.setDisable(true);
        showRepeatPasswordButton.setVisible(false);
        hideRepeatPasswordButton.setVisible(true);
        hideRepeatPasswordButton.setDisable(false);
        visibleRepeatPassword.setText(repeatPassField.getText());
        visibleRepeatPassword.setVisible(true);
        visibleRepeatPassword.setDisable(false);
        repeatPassField.setVisible(false);
        repeatPassField.setDisable(true);
    }
    @FXML public void hideRepeatPassword(ActionEvent event){
        showRepeatPasswordButton.setDisable(false);
        showRepeatPasswordButton.setVisible(true);
        hideRepeatPasswordButton.setVisible(false);
        hideRepeatPasswordButton.setDisable(true);
        repeatPassField.setText(visibleRepeatPassword.getText());
        visibleRepeatPassword.setVisible(false);
        visibleRepeatPassword.setDisable(true);
        repeatPassField.setVisible(true);
        repeatPassField.setDisable(false);
    }
    @FXML public void highlightSignUp(MouseEvent event){
        signUpButton.setDisable(true);
        signUpButton.setVisible(false);
        signUpHighlightedButton.setVisible(true);
        signUpHighlightedButton.setDisable(false);
    }
    @FXML public void unhighlightSignUp(MouseEvent event){
        signUpButton.setDisable(false);
        signUpButton.setVisible(true);
        signUpHighlightedButton.setVisible(false);
        signUpHighlightedButton.setDisable(true);
    }
}
