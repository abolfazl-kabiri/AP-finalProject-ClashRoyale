package controller;

import cards.Card;
import com.sun.prism.impl.shape.MarlinRasterizer;
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
import sample.CharacterGenerator;
import sample.User;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type Login controller.
 */
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
    private MediaPlayer mediaPlayer;

    private Stage stage;
    private Parent root;


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

        Media media = new Media(getClass().getResource("/sound effects and musics/LoginMusic.wav").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.setCycleCount(Timeline.INDEFINITE);
        mediaPlayer.play();


        hidePassWord.setVisible(false);
        hidePassWord.setDisable(true);
        visiblePasswordFiled.setVisible(false);
        visiblePasswordFiled.setDisable(true);
        loginButtonHighlighted.setVisible(false);
        loginButtonHighlighted.setDisable(true);
    }

    /**
     * Link to sign up.
     *
     * @param event the event
     */
    @FXML public void linkToSignUp(ActionEvent event) {
        mediaPlayer.stop();
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

    /**
     * Login.
     *
     * @param event the event
     */
    @FXML public void login(ActionEvent event) {
        String username = usernameTextField.getText();

        /*if(username == "" || passwordField.getText() == ""){
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
                        mediaPlayer.stop();
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
        }*/
        if(username == "" || passwordField.getText() == ""){
            wrongPassword.setText("fill all fields");
        }
        else{
            if (isLoggedIn(username, passwordField.getText())){
                String password = passwordField.getText();
                User savedUser = new User(username, password);
                savedUser.setDeck(getUserCards());
                savedUser.setLevel(getLatestLevel());
                savedUser.setXp(getLatestXp());
                savedUser.setBattleHistory(getBattleHistory());
                mediaPlayer.stop();
                System.out.println(savedUser);
                loadMainMenu(savedUser);
            }
            else
                wrongPassword.setText("you have not signed up before or something doesn't match");
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

    /**
     * Hide password.
     *
     * @param event the event
     */
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

    /**
     * Highlight button.
     *
     * @param event the event
     */
    @FXML public void highlightButton(MouseEvent event){
        loginButton.setVisible(false);
        loginButton.setDisable(true);
        loginButtonHighlighted.setVisible(true);
        loginButtonHighlighted.setDisable(false);
    }

    /**
     * Unhighlight button.
     *
     * @param event the event
     */
    @FXML public void unhighlightButton(MouseEvent event){
        loginButton.setVisible(true);
        loginButton.setDisable(false);
        loginButtonHighlighted.setVisible(false);
        loginButtonHighlighted.setDisable(true);
    }
    private ArrayList<String> getBattleHistory(){
        ArrayList<String> temp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GAMEDATABASE",
                    "root", "m@96@s97");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                String history = resultSet.getString(3);
                String user = resultSet.getString(1);
                if (user.equals(usernameTextField.getText()) && history.length() != 0)
                    temp.add(history);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(temp);
        return temp;
    }
    private ArrayList<Card> getUserCards(){
        ArrayList<Card> temp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GAMEDATABASE",
                    "root", "m@96@s97");
            int counter = 1;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from cards where username = '"+usernameTextField.getText()+"'");
            resultSet.next();
            for (int i = 2; i <= 9; i++) {
                if (resultSet.getString(i).length() != 0) {
                    String returnedCard = resultSet.getString(i);
                    temp.add(CharacterGenerator.cardFactory(returnedCard));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return temp;
    }
    private int getLatestLevel(){
        ArrayList<Integer> temp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GAMEDATABASE",
                    "root", "m@96@s97");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                String user = resultSet.getString(1);
                int level = resultSet.getInt(4);
                if (user.equals(usernameTextField.getText()))
                    temp.add(level);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return temp.get(temp.size() - 1);
    }
    private int getLatestXp(){
        ArrayList<Integer> temp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GAMEDATABASE",
                    "root", "m@96@s97");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where username = '"+usernameTextField.getText()+"'");
            while (resultSet.next()){
                String user = resultSet.getString(1);
                int xp = resultSet.getInt(5);
                if (user.equals(usernameTextField.getText()))
                    temp.add(xp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return temp.get(temp.size() - 1);
    }
    private boolean isLoggedIn(String username, String password){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GAMEDATABASE",
                    "root", "m@96@s97");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                String user = resultSet.getString(1);
                String pass = resultSet.getString(2);
                if (username.equals(user) && password.equals(pass))
                    return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
