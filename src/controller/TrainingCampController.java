package controller;

import cards.Card;
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
import sample.User;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TrainingCampController {
    private User currentUser;
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
        getUserCards();
    }
    private void getUserCards(){
        cards = currentUser.getDeck();
        setOnActionCards();
    }
    private void setOnActionCards(){
        for (int i = 0; i < insideGameCards.size(); i++) {
            insideGameCards.get(i).setImage(new Image(cards.get(i).getPath()));
        }
    }

}
