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

public class SignUpController implements Initializable {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeatPassField;

    @FXML
    private Button signUoButton;

    @FXML
    private Hyperlink loginLink;

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
    void linkToLogin(ActionEvent event) {

        clip.stop();

        Stage stage;
        Parent root;

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

    @FXML
    void signUp(ActionEvent event) {
        clip.stop();
        System.out.println("clicked on button");
    }


}
