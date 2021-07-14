package controller;

import cards.Card;
import cards.Soldier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.Player;
import sample.User;
import towers.KingTower;
import towers.PrincessTower;
import towers.Tower;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TrainingCampController {
    private User currentUser;
    private Player currentPlayer;
    private ArrayList<Card> cards;
    private ArrayList<ImageView> insideGameCards;
    private int wholeTime = 180;
    private GraphicsContext gc;
    @FXML private Canvas grassBackGround;
    @FXML private Label timeMonitor;
    @FXML private ImageView onActionCard1;
    @FXML private ImageView onActionCard2;
    @FXML private ImageView onActionCard3;
    @FXML private ImageView onActionCard4;
    @FXML private ImageView nextCard;

    @FXML private ImageView playerKingImage;
    @FXML private ImageView enemyKingImage;
    @FXML private ImageView playerLeftTowerImage;
    @FXML private ImageView playerRightTowerImage;
    @FXML private ImageView enemyLeftTowerImage;
    @FXML private ImageView enemyRightTowerImage;

    public void initialize(){
        insideGameCards = new ArrayList<>();
        insideGameCards.add(onActionCard1);
        insideGameCards.add(onActionCard2);
        insideGameCards.add(onActionCard3);
        insideGameCards.add(onActionCard4);
        insideGameCards.add(nextCard);


        gc = grassBackGround.getGraphicsContext2D();
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 32; j++) {
                if ((i + j) % 2 == 0)
                    gc.setFill(Color.web("#16b404"));
                else
                    gc.setFill(Color.web("#159604"));
                gc.fillRect(i * 15,j * 15,15,15);
            }
        }
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    int minutes = wholeTime / 60;
                    int seconds = wholeTime % 60;
                    String toStringMinutes = "0" + minutes;
                    String toStringSeconds = seconds < 10 ? "0" + seconds : "" + seconds;
                    String time = toStringMinutes + ":" + toStringSeconds;
                    timeMonitor.setText(time);
                    wholeTime--;
                });

            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
    public void setCurrentUser(User user) {
        this.currentUser = user;
        this.currentPlayer = currentUser.getPlayer();
        getPlayerCards();
    }
    private void getPlayerCards(){
        cards = currentPlayer.getPlayableCards();
        setOnActionCards();
        setTowerImages();
    }
    private void setOnActionCards(){
        for (int i = 0; i < insideGameCards.size() - 1; i++)
            insideGameCards.get(i).setImage(new Image(cards.get(i).getPath()));
        insideGameCards.get(insideGameCards.size() - 1).setImage(new Image(currentPlayer.getNextCard().getPath()));
    }
    private void setTowerImages(){
        playerKingImage.setImage(new Image(currentPlayer.getKingTower().getPath()));
        playerLeftTowerImage.setImage(new Image(currentPlayer.getLeftTower().getPath()));
        playerRightTowerImage.setImage(new Image(currentPlayer.getRightTower().getPath()));

        enemyKingImage.setImage(new Image(".\\photos\\inside game models\\red king building_00000.png"));
        enemyLeftTowerImage.setImage(new Image(".\\photos\\inside game models\\red queen building_00000.png"));
        enemyRightTowerImage.setImage(new Image(".\\photos\\inside game models\\red queen building_00000.png"));
    }

}
