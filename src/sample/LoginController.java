package sample;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class LoginController implements Initializable {

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private TextField visiblePasswordFiled;
    @FXML private Button loginButton;
    @FXML private Button loginButtonHighlighted;
    @FXML private Hyperlink signUpLink;
    @FXML private Button hidePassWord;
    @FXML private Button showPassWord;
    private MediaPlayer player;
    private Stage stage;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media media =
                new Media(Paths.get(new File("LoginMusic.wav").getAbsolutePath()).toUri().toString());
        player = new MediaPlayer(media);
        player.setCycleCount(Timeline.INDEFINITE);
        player.setVolume(0.3);
        player.play();
        hidePassWord.setVisible(false);
        hidePassWord.setDisable(true);
        visiblePasswordFiled.setVisible(false);
        visiblePasswordFiled.setDisable(true);
        loginButtonHighlighted.setVisible(false);
        loginButtonHighlighted.setDisable(true);
    }

    @FXML public void linkToSignUp(ActionEvent event) {
        player.stop();
        try {
            stage = (Stage) signUpLink.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("SignUp");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void login(ActionEvent event) {
        player.stop();
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
        System.out.println("clicked on login");
    }

    @FXML public void showPassword(ActionEvent event){
        visiblePasswordFiled.setText(passwordField.getText());
        visiblePasswordFiled.setVisible(true);
        visiblePasswordFiled.setDisable(false);
        passwordField.setVisible(false);
        passwordField.setDisable(true);
        showPassWord.setVisible(false);
        showPassWord.setDisable(true);
        hidePassWord.setVisible(true);
        hidePassWord.setDisable(false);
    }

    @FXML public void hidePassword(ActionEvent event){
        passwordField.setText(visiblePasswordFiled.getText());
        visiblePasswordFiled.setVisible(false);
        visiblePasswordFiled.setDisable(true);
        passwordField.setVisible(true);
        passwordField.setDisable(false);
        hidePassWord.setVisible(false);
        hidePassWord.setDisable(true);
        showPassWord.setVisible(true);
        showPassWord.setDisable(false);
    }
    @FXML public void highlightButton(){
        loginButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loginButton.setVisible(false);
                loginButton.setDisable(true);
                loginButtonHighlighted.setVisible(true);
                loginButtonHighlighted.setDisable(false);
            }
        });
    }
    @FXML public void unhighlightButton(){
        loginButtonHighlighted.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loginButton.setVisible(true);
                loginButton.setDisable(false);
                loginButtonHighlighted.setVisible(false);
                loginButtonHighlighted.setDisable(true);
            }
        });
    }

}
