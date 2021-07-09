package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;
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
    @FXML private Label wrongPassword;
 //   private MediaPlayer player;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private Stage stage;
    private Parent root;

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


//        Media media =
//                new Media(new File("file:LoginMusic.wav").toURI().toString());
//        player = new MediaPlayer(media);
//        player.setCycleCount(Timeline.INDEFINITE);
//        player.setVolume(0.3);
//        player.play();
        hidePassWord.setVisible(false);
        hidePassWord.setDisable(true);
        visiblePasswordFiled.setVisible(false);
        visiblePasswordFiled.setDisable(true);
        loginButtonHighlighted.setVisible(false);
        loginButtonHighlighted.setDisable(true);
    }

    @FXML public void linkToSignUp(ActionEvent event) {
//        player.stop();
        clip.stop();
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


        String username = usernameTextField.getText();
        if(new File(username+".bin").exists()){
            String password = passwordField.getText();
            User user = new User(username,password);
            try {
                FileInputStream fileInputStream = new FileInputStream(username+".bin");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                User savedUser = (User) objectInputStream.readObject();
                if(user.equals(savedUser)){
//                            player.stop();
                    clip.stop();
                    loadMainMenu(savedUser);
                } else {
                    wrongPassword.setText("wrong password");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            wrongPassword.setText("you have not signed up before");
        }




        System.out.println("clicked on login");
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
    @FXML public void highlightButton(MouseEvent event){
        loginButton.setVisible(false);
        loginButton.setDisable(true);
        loginButtonHighlighted.setVisible(true);
        loginButtonHighlighted.setDisable(false);
    }
    @FXML public void unhighlightButton(MouseEvent event){
        loginButton.setVisible(true);
        loginButton.setDisable(false);
        loginButtonHighlighted.setVisible(false);
        loginButtonHighlighted.setDisable(true);
    }

}
