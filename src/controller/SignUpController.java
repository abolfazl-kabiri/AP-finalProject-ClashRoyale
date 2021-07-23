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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * The type Sign up controller.
 */
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
    private MediaPlayer player;

    private Stage stage;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Media media = new Media(getClass().getResource("/sound effects and musics/LoginMusic.wav").toString());
        player = new MediaPlayer(media);
        player.setVolume(0.5);
        player.setCycleCount(Timeline.INDEFINITE);
        player.play();

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

    }

    /**
     * Link to login.
     *
     * @param event the event
     */
    @FXML public void linkToLogin(ActionEvent event) {
        player.stop();

        try {
            stage = (Stage) loginLink.getScene().getWindow();
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
     * Sign up.
     *
     * @param event the event
     */
    @FXML public void signUp(ActionEvent event) {
        String username = usernameTextField.getText();
        /*if(username == "" || passwordField.getText() == "" || repeatPassField.getText() == ""){
            wrongPassword.setText("fill all fields");
        } else {
            File file = new File(".\\src\\users\\" + username + ".bin");
            if(file.exists()){
                wrongPassword.setText("this username is already taken");
            } else {
                String password = passwordField.getText();
                String repeatedPassword = repeatPassField.getText();
                if(password.equals(repeatedPassword)){
                    User user = new User(username,password);
                    player.stop();
                    loadMainMenu(user);
                } else {
                    wrongPassword.setText("different passwords");
                }
            }
        }*/
        if(username == "" || passwordField.getText() == "" || repeatPassField.getText() == ""){
            wrongPassword.setText("fill all fields");
        }
        else{
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GAMEDATABASE",
                        "root", "m@96@s97");
                Statement statement = connection.createStatement();
                String command = "select * from users";
                ResultSet resultSet = statement.executeQuery(command);
                int counter = 0;
                while (resultSet.next()){
                    if (resultSet.getString(1).equals(username))
                        counter++;
                }
                if (counter != 0)
                    wrongPassword.setText("this username is already taken");
                else{
                    String password = passwordField.getText();
                    String repeatedPassword = repeatPassField.getText();
                    if (password.length() < 4)
                        wrongPassword.setText("your password must be at least 4 character");
                    else if(password.equals(repeatedPassword)){
                        String date = "";
                        int level = 1;
                        int xp = 0;
                        String selection = "insert into users(username, password, battleHistory, level, xp) VALUES" +
                                "('"+username+"', '"+password+"', '"+date+"', '"+level+"', '"+xp+"')";
                        boolean result = statement.execute(selection);
                        User user = new User(username,password);
                        player.stop();
                        loadMainMenu(user);
                    } else if (!password.equals(repeatedPassword)){
                        wrongPassword.setText("different passwords");
                    }
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
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

    /**
     * Show password.
     *
     * @param event the event
     */
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

    /**
     * Hide password.
     *
     * @param event the event
     */
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

    /**
     * Show repeat password.
     *
     * @param event the event
     */
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

    /**
     * Hide repeat password.
     *
     * @param event the event
     */
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

    /**
     * Highlight sign up.
     *
     * @param event the event
     */
    @FXML public void highlightSignUp(MouseEvent event){
        signUpButton.setDisable(true);
        signUpButton.setVisible(false);
        signUpHighlightedButton.setVisible(true);
        signUpHighlightedButton.setDisable(false);
    }

    /**
     * Unhighlight sign up.
     *
     * @param event the event
     */
    @FXML public void unhighlightSignUp(MouseEvent event){
        signUpButton.setDisable(false);
        signUpButton.setVisible(true);
        signUpHighlightedButton.setVisible(false);
        signUpHighlightedButton.setDisable(true);
    }
}
