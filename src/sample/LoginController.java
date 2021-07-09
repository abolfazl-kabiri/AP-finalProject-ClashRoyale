package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink signUpLink;

    private AudioInputStream audioInputStream;
    private Clip clip;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("LoginMusic.wav"));
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
    }


    @FXML
    void linkToSignUp(ActionEvent event) {
        clip.stop();
        Stage stage;
        Parent root;
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

    @FXML
    void login(ActionEvent event) {
        clip.stop();

        Stage stage;
        Parent root;
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


}
