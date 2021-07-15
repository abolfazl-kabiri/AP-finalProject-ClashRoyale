package controller;

import cards.Card;
import cards.Soldier;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import sample.Bot;
import sample.Player;
import sample.User;
import towers.Tower;

import java.net.Socket;
import java.text.DecimalFormat;
import java.util.*;

public class TrainingCampController {

    private Bot bot;
    private User currentUser;
    private Player currentPlayer;
    private ArrayList<Card> cards;
    private ArrayList<ImageView> insideGameCards;
    private int wholeTime = 180;
    private GraphicsContext gc;
    private boolean emoteWindowKey;
//    private MediaPlayer mediaPlayer;
    private Timer timer;
    @FXML private ImageView kingEmote;
    @FXML private Pane emotesWindow;
    @FXML private Canvas grassBackGround;
    @FXML private Label timeMonitor;
    @FXML private ImageView onActionCard1;
    @FXML private ImageView onActionCard2;
    @FXML private ImageView onActionCard3;
    @FXML private ImageView onActionCard4;
    @FXML private ImageView nextCard;
    @FXML private ProgressBar elixirBar;
    @FXML private ProgressBar leftPrincessHpBar;
    @FXML private ProgressBar rightPrincessHpBar;
    @FXML private ProgressBar kingHpBar;
    @FXML private Button card1;
    @FXML private AnchorPane pane;

    public static List playerTowers;
    public static List playerInGameCards;
    public static List enemyTowers;
    public static List enemyInGameCards;
    public static ArrayList<Timeline> animations;


    public void initialize(){
        animations = new ArrayList<>();
        playerTowers = Collections.synchronizedList(new ArrayList<Tower>());
        playerInGameCards = Collections.synchronizedList(new ArrayList<Card>());
        enemyTowers = Collections.synchronizedList(new ArrayList<Tower>());
        enemyInGameCards = Collections.synchronizedList(new ArrayList<Card>());

        this.kingEmote.setOpacity(0);
        this.emotesWindow.setOpacity(0);
        this.emoteWindowKey = false;

        elixirBar.setStyle("-fx-accent: #6a0991");
        leftPrincessHpBar.setStyle("-fx-accent: #3576e5");
        rightPrincessHpBar.setStyle("-fx-accent: #3576e5");
        kingHpBar.setStyle("-fx-accent: #3576e5");
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


    @FXML public void openEmoteWindow(ActionEvent event){
        FadeTransition ft = new FadeTransition(Duration.seconds(.3), emotesWindow);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(.3), emotesWindow);
        if (!emoteWindowKey) {
            ft.setFromValue(0);
            ft.setToValue(1);
            tt.setByX(-5);
            tt.setByY(-5);
            ParallelTransition pt = new ParallelTransition(ft, tt);
            pt.play();
            emoteWindowKey = true;
            return;
        }
        ft.setFromValue(1);
        ft.setToValue(0);
        tt.setByX(5);
        tt.setByY(5);
        ParallelTransition pt = new ParallelTransition(ft, tt);
        pt.play();
        emoteWindowKey = false;
    }
    @FXML public void angryEmote(ActionEvent event){
        String photoPath = ".\\photos\\inside game models\\135-1354443_clash-royale-king-png.png";
        String soundPath = "/sound effects and musics/angry_sound_effect.mp3";
        showEmote(photoPath, soundPath);
    }
    @FXML public void happyEmote(ActionEvent event){
        String photoPath = ".\\photos\\inside game models\\happy_emote_00000.png";
        String soundPath = "/sound effects and musics/happy_sound_effect.mp3";
        showEmote(photoPath, soundPath);
    }
    @FXML public void sadEmote(ActionEvent event){
        String photoPath = ".\\photos\\inside game models\\sad_emote_00000.png";
        String soundPath = "/sound effects and musics/sad_sound_effect.mp3";
        showEmote(photoPath, soundPath);
    }
    private void showEmote(String emotePhotoPath, String emoteSoundEffect){
        kingEmote.setImage(new Image(emotePhotoPath));
        FadeTransition ft = new FadeTransition(Duration.seconds(.3), kingEmote);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(.3), emotesWindow);
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(.3), emotesWindow);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        translateTransition.setByX(5);
        translateTransition.setByY(5);
        ParallelTransition pt = new ParallelTransition(fadeTransition, translateTransition);
        pt.play();
        emoteWindowKey = false;
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
//        Media media = new Media(getClass().getResource(emoteSoundEffect).toString());
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setVolume(0.8);
//        mediaPlayer.play();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.play();
            }
        };
        timer = new Timer();
        timer.schedule(task, 1100);
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        this.currentPlayer = currentUser.getPlayer();
        bot = new Bot(currentUser.getDeck(), currentUser.getLevel());
        getPlayerCards();
        getEnemyTowers();
        getPlayerTowers();

    }

    private void getEnemyTowers(){
        enemyTowers.add(bot.getKingTower());
        enemyTowers.add(bot.getLeftTower());
        enemyTowers.add(bot.getRightTower());

    }
    private void getPlayerCards(){
        cards = currentPlayer.getPlayableCards();
        setOnActionCards();
    }
    private void getPlayerTowers(){
        playerTowers.add(currentPlayer.getKingTower());
        playerTowers.add(currentPlayer.getLeftTower());
        playerTowers.add(currentPlayer.getRightTower());
        setTowerImages();
    }
    private void setOnActionCards(){
        for (int i = 0; i < insideGameCards.size() - 1; i++) {
            insideGameCards.get(i).setImage(new Image(currentPlayer.getPlayableCards().get(i).getPath()));
        }
        insideGameCards.get(insideGameCards.size()-1).setImage(new Image(currentPlayer.getNextCard().getPath()));
    }
    private void setTowerImages(){
        synchronized (playerTowers){
            Iterator<Tower> it = playerTowers.iterator();
            while (it.hasNext()){
                Tower tower = it.next();
                tower.createPicture(pane);
            }
        }

        synchronized (enemyTowers){
            Iterator<Tower> it = enemyTowers.iterator();
            while (it.hasNext()){
                Tower tower = it.next();
                tower.createPicture(pane);
            }
        }
    }
    public void pickCard1(MouseEvent event){
        card1.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (isElixirValueDefined()) {
                    ImageView temp = new ImageView(new Image(".\\photos\\icon.jpg"));
                    temp.setFitWidth(43);
                    temp.setFitHeight(43);
                    pane.getChildren().add(temp);
                    temp.setLayoutX(event.getSceneX() - 25);
                    temp.setLayoutY(event.getSceneY() - 25);
                    temp.toFront();
                    elixirBar.setProgress(elixirBar.getProgress() - 0.1);
                    handleMove(temp, event.getSceneX(), event.getSceneY());
                }
            }
        });
    }

    private void handleMove(ImageView temp, double X, double Y){

        TranslateTransition tt = new TranslateTransition();
        tt.setNode(temp);
        tt.setToX((getNearBridge(X) - X) + 20);
        tt.setToY((-1 * Math.abs(232.0 - Y)) + 20);
        tt.setDuration(Duration.seconds(1.0));
        tt.play();

    }

    private Double getNearBridge(double x){
        double distanceToRight = Math.abs(x - 383.0);
        double distanceToLeft = Math.abs(x - 189.0);
        if(distanceToLeft <= distanceToRight)
            return 189.0;
        else
            return 383.0;
    }

    public boolean isElixirValueDefined(){
        String temp = new DecimalFormat().format(elixirBar.getProgress());
        return temp.equals("0.1") || temp.equals("0.2") ||
                temp.equals("0.3") || temp.equals("0.4") ||
                temp.equals("0.5") || temp.equals("0.6") ||
                temp.equals("0.7") || temp.equals("0.8") ||
                temp.equals("0.9") || temp.equals("1");
    }

    private boolean isReleasedOnBattleGround(int x, int y){
        return x > 164 && x < 435 && y > 8.8 && y < 489;
    }

}
