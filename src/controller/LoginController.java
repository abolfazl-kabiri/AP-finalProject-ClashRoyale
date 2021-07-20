package controller;

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
import sample.User;

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
//    private MediaPlayer mediaPlayer;
//
//
//    private AudioInputStream audioInputStream;
//    private Clip clip;
    private Stage stage;
    private Parent root;


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

//         Media media = new Media(getClass().getResource("/sound effects and musics/LoginMusic.wav").toString());
//         mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setVolume(0.3);
//        mediaPlayer.setCycleCount(Timeline.INDEFINITE);
//        mediaPlayer.play();

//        try {
//            audioInputStream = AudioSystem.getAudioInputStream(new File(".\\src\\sound effects and musics\\LoginMusic.wav"));
//            clip = AudioSystem.getClip();
//            clip.open(audioInputStream);
//            clip.start();
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (UnsupportedAudioFileException e) {
//            e.printStackTrace();
//        }


        hidePassWord.setVisible(false);
        hidePassWord.setDisable(true);
        visiblePasswordFiled.setVisible(false);
        visiblePasswordFiled.setDisable(true);
        loginButtonHighlighted.setVisible(false);
        loginButtonHighlighted.setDisable(true);
    }
    @FXML public void linkToSignUp(ActionEvent event) {
   //     mediaPlayer.stop();
  //      clip.stop();
        try {
            stage = (Stage) signUpLink.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../view/SignUp.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("sign in");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML public void login(ActionEvent event) {
        String username = usernameTextField.getText();

        if(username == "" || passwordField.getText() == ""){
            wrongPassword.setText("fill all fields");
        } else {
            if(new File(  ".\\src\\users\\" + username + ".bin").exists()){
                String password = passwordField.getText();
                User user = new User(username,password);
                try {
                    FileInputStream fileInputStream = new FileInputStream(".\\src\\users\\" + username + ".bin");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    User savedUser = (User) objectInputStream.readObject();
                    if(user.equals(savedUser)){
                        //                   mediaPlayer.stop();
//                    clip.stop();
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
        }
    }
    private void loadMainMenu(User user){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainMenu.fxml"));
            stage = (Stage) usernameTextField.getScene().getWindow();
            root = loader.load();
            MainMenuController menuController = loader.getController();
            menuController.setCurrentUser(user);
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
