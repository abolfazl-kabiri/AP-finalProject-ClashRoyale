package controller;

import cards.*;
import javafx.animation.*;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Bot;
import sample.DataBase;
import sample.Player;
import sample.User;
import towers.Tower;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TrainingCampController {
    private Bot bot;
    private User currentUser;
    private Player currentPlayer;
    private ArrayList<ImageView> insideGameCards;
    private ArrayList<Button> cardsButtons;
    private HashMap<Button, Card> fourPlayableCardsAndTheirButtons;
    private int wholeTime = 180;
    private GraphicsContext gc;
    private boolean emoteWindowKey;
    private MediaPlayer mediaPlayer;
    private Timer timer;
    private int counter;
    @FXML private ImageView kingEmote;
    @FXML private Pane emotesWindow;
    @FXML private Canvas grassBackGround;
    @FXML private Label countDownLabel;
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
    @FXML private ProgressBar enemyKingHpBar;
    @FXML private ProgressBar enemyLeftPrincessHpBar;
    @FXML private ProgressBar enemyRightPrincessHpBar;
    @FXML private Button card1;
    @FXML private Button card2;
    @FXML private Button card3;
    @FXML private Button card4;
    @FXML private Button emoteWindowButton;
    @FXML private AnchorPane pane;
    @FXML private Pane onTopPane;
    @FXML private Label playerCounterLabel;
    @FXML private Label enemyCounterLabel;
    public static List playerTowers;
    public static List playerInGameCards;
    public static List enemyTowers;
    public static List enemyInGameCards;
    public static ArrayList<Timeline> animations;

    public void initialize(){
        this.counter = 3;
        fourPlayableCardsAndTheirButtons = new HashMap<>();
        animations = new ArrayList<>();
        playerTowers = Collections.synchronizedList(new ArrayList<Tower>());
        playerInGameCards = Collections.synchronizedList(new ArrayList<Card>());
        enemyTowers = Collections.synchronizedList(new ArrayList<Tower>());
        enemyInGameCards = Collections.synchronizedList(new ArrayList<Card>());

        this.kingEmote.setOpacity(0);
        this.emotesWindow.setOpacity(0);
        this.emoteWindowKey = false;

        elixirBar.setStyle("-fx-accent: #6a0991");
        elixirBar.setProgress(.4);

        leftPrincessHpBar.setStyle("-fx-accent: #3576e5");
        rightPrincessHpBar.setStyle("-fx-accent: #3576e5");
        kingHpBar.setStyle("-fx-accent: #3576e5");
        enemyLeftPrincessHpBar.setStyle("-fx-accent: #d10f0f");
        enemyRightPrincessHpBar.setStyle("-fx-accent: #d10f0f");
        enemyKingHpBar.setStyle("-fx-accent: #d10f0f");

        insideGameCards = new ArrayList<>();
        insideGameCards.add(onActionCard1);
        insideGameCards.add(onActionCard2);
        insideGameCards.add(onActionCard3);
        insideGameCards.add(onActionCard4);
        insideGameCards.add(nextCard);

        cardsButtons = new ArrayList<>();
        cardsButtons.add(card1);
        cardsButtons.add(card2);
        cardsButtons.add(card3);
        cardsButtons.add(card4);

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
                    playerCounterLabel.setText(String.valueOf(currentPlayer.getNumberOfStars()));
                    enemyCounterLabel.setText(String.valueOf(bot.getNumberOfStars()));
                    if (wholeTime >= 60){
                        if (wholeTime % 2 == 1){
                            currentPlayer.updateElixir();
                            elixirBar.setProgress(currentPlayer.getElixir());
                        }
                    }
                    else{
                        currentPlayer.updateElixir();
                        elixirBar.setProgress(currentPlayer.getElixir());
                    }
                    updateTowerBars();
                    updateStars();
                    wholeTime--;
                    if (gameEnds()){
                        timeMonitor.setText("00:00");
                        gameConclusion();
                        stopAnimations();
                        timer.cancel();
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(task, 3000, 1000);
        Timer countDownTimer = new Timer();
        TimerTask countDownTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    countDownLabel.setText(String.valueOf(counter));
                    counter--;
                });
            }
        };
        countDownTimer.scheduleAtFixedRate(countDownTask, 0, 1000);
        Timer timer1 = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < cardsButtons.size(); i++) {
                    cardsButtons.get(i).setDisable(false);
                }
                emoteWindowButton.setDisable(false);
                onTopPane.setDisable(true);
                onTopPane.setVisible(false);
                countDownLabel.setDisable(true);
                countDownLabel.setVisible(false);
            }
        };
        timer1.schedule(timerTask, 3000);

    }
    public void stopAnimations(){
        synchronized (animations){
            for (Timeline t: animations)
                t.stop();
        }
    }
    public void updateStars(){
        int botStars = 0;
        if (currentPlayer.getRightTower().getHp() <= 0)
            botStars += currentPlayer.getRightTower().getNumberOfStars();
        if (currentPlayer.getLeftTower().getHp() <= 0)
            botStars += currentPlayer.getLeftTower().getNumberOfStars();
        if (currentPlayer.getKingTower().getHp() <= 0)
            botStars += currentPlayer.getKingTower().getNumberOfStars();
        bot.setNumberOfStars(botStars);
        if (bot.getNumberOfStars() > 0)
            currentPlayer.getKingTower().setActive(true);

        int playerStars = 0;
        if (bot.getRightTower().getHp() <= 0)
            playerStars += bot.getRightTower().getNumberOfStars();
        if (bot.getLeftTower().getHp() <= 0)
            playerStars += bot.getLeftTower().getNumberOfStars();
        if (bot.getKingTower().getHp() <= 0)
            playerStars += bot.getKingTower().getNumberOfStars();
        currentPlayer.setNumberOfStars(playerStars);
        if (currentPlayer.getNumberOfStars() > 0)
            bot.getKingTower().setActive(true);
    }
    public void gameConclusion(){
        Player winner = null;
        Player loser = null;
        if (currentPlayer.getNumberOfStars() > bot.getNumberOfStars()){
            winner = currentPlayer;
            loser = bot;
        } else if (bot.getNumberOfStars() > currentPlayer.getNumberOfStars()){
            winner = bot;
            loser = currentPlayer;
        } else {
            int playerTowersHp = 0;
            int botTowersHp = 0;
            playerTowersHp += currentPlayer.getKingTower().getHp();
            playerTowersHp += currentPlayer.getLeftTower().getHp();
            playerTowersHp += currentPlayer.getRightTower().getHp();

            botTowersHp += bot.getKingTower().getHp();
            botTowersHp += bot.getLeftTower().getHp();
            botTowersHp += bot.getRightTower().getHp();

            if (playerTowersHp >= botTowersHp){
                winner = currentPlayer;
                loser = bot;
            } else {
                winner = bot;
                loser = currentPlayer;
            }
        }
        if (winner == currentPlayer)
            currentUser.setXp(currentUser.getXp() + 200);
        else if (loser == currentPlayer)
            currentUser.setXp(currentUser.getXp() + 70);
        currentUser.setLevel(DataBase.getUserLevel(currentUser.getXp()));
        String history = "you " + " " + currentPlayer.getNumberOfStars() + " - " + bot.getNumberOfStars() + " " + bot.getName() + " " + new Date();
        currentUser.addToHistory(history);
        currentUser.saveUser();
    }
    public boolean gameEnds(){
        boolean end = false;
        if (currentPlayer.getNumberOfStars() == 3 || bot.getNumberOfStars() == 3)
            end = true;
        else if (wholeTime == 0)
            end = true;
        return end;
    }
    @FXML public void openEmoteWindow(ActionEvent event){
        emotesWindow.toFront();
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
        kingEmote.toFront();
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
        Media media = new Media(getClass().getResource(emoteSoundEffect).toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.8);
        mediaPlayer.play();
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
        setOnActionCards();
        getEnemyTowers();
        getPlayerTowers();

    }
    private void getEnemyTowers(){
        enemyTowers.add(bot.getKingTower());
        enemyTowers.add(bot.getLeftTower());
        enemyTowers.add(bot.getRightTower());

    }
    private void getPlayerTowers(){
        playerTowers.add(currentPlayer.getKingTower());
        playerTowers.add(currentPlayer.getLeftTower());
        playerTowers.add(currentPlayer.getRightTower());
        setTowerImages();
        emotesWindow.toFront();
        kingEmote.toFront();
        onTopPane.toFront();
    }
    private void setOnActionCards(){
        for (int i = 0; i < insideGameCards.size() - 1; i++) {
            insideGameCards.get(i).setImage(new Image(currentPlayer.getPlayableCards().get(i).getPath()));
            fourPlayableCardsAndTheirButtons.put(cardsButtons.get(i), currentPlayer.getPlayableCards().get(i));
        }
        insideGameCards.get(insideGameCards.size()-1).setImage(new Image(currentPlayer.getNextCard().getPath()));

    }
    private void setTowerImages(){
        synchronized (playerTowers){
            Iterator<Tower> it = playerTowers.iterator();
            while (it.hasNext()){
                Tower tower = it.next();
                tower.createPicture(pane);
                tower.playerAttack();
            }
        }

        synchronized (enemyTowers){
            Iterator<Tower> it = enemyTowers.iterator();
            while (it.hasNext()){
                Tower tower = it.next();
                tower.createPicture(pane);
                tower.botAttack();
            }
        }
    }
    @FXML public void pickCard1(MouseEvent event){
        Card card = fourPlayableCardsAndTheirButtons.get(card1);
        card1.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if ((card.getCost() * 0.1) <= currentPlayer.getElixir() &&
                        isReleasedOnBattleGround(event.getSceneX(), event.getSceneY(), card)) {
                    pick(card1, event, onActionCard1);
                }
            }
        });
    }
    @FXML public void pickCard2(MouseEvent event){
        Card card = fourPlayableCardsAndTheirButtons.get(card2);
        card2.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if ((card.getCost() * 0.1) <= currentPlayer.getElixir() &&
                        isReleasedOnBattleGround(event.getSceneX(), event.getSceneY(), card)) {
                    pick(card2, event, onActionCard2);
                }
            }
        });
    }
    @FXML public void pickCard3(MouseEvent event){
        Card card = fourPlayableCardsAndTheirButtons.get(card3);
        card3.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if ((card.getCost() * 0.1) <= currentPlayer.getElixir() &&
                        isReleasedOnBattleGround(event.getSceneX(), event.getSceneY(), card)) {
                    pick(card3, event, onActionCard3);
                }
            }
        });
    }
    @FXML public void pickCard4(MouseEvent event){
        Card card = fourPlayableCardsAndTheirButtons.get(card4);
        card4.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if ((card.getCost() * 0.1) <= currentPlayer.getElixir() &&
                        isReleasedOnBattleGround(event.getSceneX(), event.getSceneY(), card)) {
                    pick(card4, event, onActionCard4);
                }
            }
        });
    }
    private void pick(Button intendedCard, MouseEvent event, ImageView intendedOnActionCard){
        currentPlayer.setElixir(currentPlayer.getElixir() - ((fourPlayableCardsAndTheirButtons.get(intendedCard).getCost()) * 0.1));
        elixirBar.setProgress(currentPlayer.getElixir());
        Card selectedCard = fourPlayableCardsAndTheirButtons.get(intendedCard);
        fourPlayableCardsAndTheirButtons.replace(intendedCard, currentPlayer.getNextCard());
        intendedOnActionCard.setImage(new Image(currentPlayer.getNextCard().getPath()));
        Card tempCard = currentPlayer.updatePlayableCards(selectedCard);
        if (selectedCard instanceof Soldier){
            int count = ((Soldier) selectedCard).getCount();
            if ( count > 1){
                int[] locs = {20, 45, 70, 95};
                int i=0;
                while (count > 0){
                    selectedCard = generateCard(selectedCard);
                    nextCard.setImage(new Image(tempCard.getPath()));
                    selectedCard.setPathInBattle(DataBase.getPathInBattle(selectedCard, false));
                    selectedCard.createPicture(pane, event.getSceneX() - 20, event.getSceneY() - locs[i]);
                    playerInGameCards.add(selectedCard);
                    selectedCard.startFunctioning();
                    count--;
                    i++;
                }
            } else {
                selectedCard = generateCard(selectedCard);
                nextCard.setImage(new Image(tempCard.getPath()));
                selectedCard.setPathInBattle(DataBase.getPathInBattle(selectedCard, false));
                selectedCard.createPicture(pane, event.getSceneX() - 20, event.getSceneY() - 20);
                playerInGameCards.add(selectedCard);
                selectedCard.startFunctioning();
            }
        } else {
            selectedCard = generateCard(selectedCard);
            nextCard.setImage(new Image(tempCard.getPath()));
            selectedCard.setPathInBattle(DataBase.getPathInBattle(selectedCard, false));
            selectedCard.createPicture(pane, event.getSceneX() - 20, event.getSceneY() - 20);
            playerInGameCards.add(selectedCard);
            selectedCard.startFunctioning();

        }

        bot.playCard(pane);
    }
    private Card generateCard(Card card){
        if (card instanceof Archer)
            return new Archer(card.getDamage(), card.getHp());
        else if (card instanceof Arrows)
            return new Arrows(card.getDamage());
        else if (card instanceof BabyDragon)
            return new BabyDragon(card.getDamage(), card.getHp());
        else if (card instanceof Barbarian)
            return new Barbarian(card.getDamage(), card.getHp());
        else if (card instanceof Cannon)
            return new Cannon(card.getDamage(), card.getHp());
        else if (card instanceof FireBall)
            return new FireBall(card.getDamage());
        else if (card instanceof Giant)
            return new Giant(card.getDamage(), card.getHp());
        else if (card instanceof InfernoTower)
            return new InfernoTower(card.getDamage(), card.getHp());
        else if (card instanceof MiniPekka)
            return new MiniPekka(card.getDamage(), card.getHp());
        else if (card instanceof Rage)
            return new Rage(DataBase.getRageDuration(currentUser.getLevel()));
        else if (card instanceof Valkyrie)
            return new Valkyrie(card.getDamage(), card.getHp());
        else if (card instanceof Wizard)
            return new Wizard(card.getDamage(), card.getHp());
        else
            return null;
    }
    public void updateTowerBars(){
        kingHpBar.setProgress(Double.valueOf(currentPlayer.getKingTower().getHp()) / DataBase.getHP(currentPlayer.getKingTower(), currentUser.getLevel()));
        if (currentPlayer.getKingTower().getHp() == 0){
            currentPlayer.getKingTower().getImageView().setImage(new Image("./photos/inside game models/destroyed tower.png", 60.0, 60.0, false, false));
            kingHpBar.setVisible(false);
            playerTowers.remove(currentPlayer.getKingTower());
        }

        rightPrincessHpBar.setProgress(Double.valueOf(currentPlayer.getRightTower().getHp()) / DataBase.getHP(currentPlayer.getRightTower(), currentUser.getLevel()));
        if (currentPlayer.getRightTower().getHp() == 0){
            currentPlayer.getRightTower().getImageView().setImage(new Image("./photos/inside game models/destroyed tower.png", 60.0, 60.0, false, false));
            rightPrincessHpBar.setVisible(false);
            playerTowers.remove(currentPlayer.getRightTower());
        }

        leftPrincessHpBar.setProgress(Double.valueOf(currentPlayer.getLeftTower().getHp()) / DataBase.getHP(currentPlayer.getLeftTower(), currentUser.getLevel()));
        if (currentPlayer.getLeftTower().getHp() == 0){
            currentPlayer.getLeftTower().getImageView().setImage(new Image("./photos/inside game models/destroyed tower.png", 60.0, 60.0, false, false));
            leftPrincessHpBar.setVisible(false);
            playerTowers.remove(currentPlayer.getLeftTower());
        }


        enemyKingHpBar.setProgress(Double.valueOf(bot.getKingTower().getHp()) / DataBase.getHP(bot.getKingTower(), currentUser.getLevel()));
        if (bot.getKingTower().getHp() == 0){
            enemyKingHpBar.setVisible(false);
            bot.getKingTower().getImageView().setImage(new Image("./photos/inside game models/destroyed tower.png", 60.0, 60.0, false, false));
            enemyTowers.remove(bot.getKingTower());
        }

        enemyRightPrincessHpBar.setProgress(Double.valueOf(bot.getRightTower().getHp()) / DataBase.getHP(bot.getRightTower(), currentUser.getLevel()));
        if (bot.getRightTower().getHp() == 0){
            enemyRightPrincessHpBar.setVisible(false);
            bot.getRightTower().getImageView().setImage(new Image("./photos/inside game models/destroyed tower.png", 60.0, 60.0, false, false));
            enemyTowers.remove(bot.getRightTower());
        }

        enemyLeftPrincessHpBar.setProgress(Double.valueOf(bot.getLeftTower().getHp()) / DataBase.getHP(bot.getLeftTower(), currentUser.getLevel()));
        if (bot.getLeftTower().getHp() == 0){
            enemyLeftPrincessHpBar.setVisible(false);
            bot.getRightTower().getImageView().setImage(new Image("./photos/inside game models/destroyed tower.png", 60.0, 60.0, false, false));
            enemyTowers.remove(bot.getLeftTower());
        }

    }
    private boolean isReleasedOnBattleGround(double x, double y, Card card){
        boolean valid = false;
        if(enemyTowers.size() == 3){
            valid = (x > 164 && x < 435) && (y > 260 && y < 489);
        } else if (enemyTowers.size() == 2){
            if (bot.getLeftTower().getHp() <= 0){
                valid = (((x > 164 && x < 435) && (y > 260 && y < 489)) || ((x > 164 && x < 300 ) && (y > 180 && y < 489)));
            } else if (bot.getRightTower().getHp() <= 0){
                valid = (((x > 164 && x < 435) && (y > 260 && y < 489)) || ((x > 300 && x < 435) && (y > 180 && y < 489)));
            }
        } else if (enemyTowers.size() == 1){
            valid = (x > 164 && x < 435) && ((y > 180 && y < 210) || (y > 260 && y < 489));
        }
        if (card instanceof Spell)
            valid = true;
        return valid;
    }

}
