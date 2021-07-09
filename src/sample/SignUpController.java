package sample;

import javafx.animation.Timeline;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField repeatPassField;
    @FXML private Button signUpButton;
    @FXML private Hyperlink loginLink;
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
    }

    @FXML public void linkToLogin(ActionEvent event) {
        player.stop();

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
        System.out.println("clicked on button");
    }


}
