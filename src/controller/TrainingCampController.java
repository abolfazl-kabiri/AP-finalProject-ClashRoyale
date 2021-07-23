package controller;

import cards.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Bot;
import sample.DataBase;
import sample.Player;
import sample.User;
import towers.Tower;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Training camp controller.
 */
public class TrainingCampController {
    private Bot bot;
    private User currentUser;
    private Player currentPlayer;
    private ArrayList<ImageView> insideGameCards;
    private ArrayList<Button> cardsButtons;
    private ArrayList<ImageView> playerCrowns;
    private ArrayList<ImageView> enemyCrowns;
    private HashMap<Button, Card> fourPlayableCardsAndTheirButtons;
    private int wholeTime = 180;
    private GraphicsContext gc;
    private boolean emoteWindowKey;
    private MediaPlayer mediaPlayer;
    private Timer timer;
    private int counter;
    private Stage stage;
    private Parent root;
    private MediaPlayer player;
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
    @FXML private ImageView playerCrown1;
    @FXML private ImageView playerCrown2;
    @FXML private ImageView playerCrown3;
    @FXML private ImageView enemyCrown1;
    @FXML private ImageView enemyCrown2;
    @FXML private ImageView enemyCrown3;
    @FXML private Label gameResultLabel;
    @FXML private HBox enemyCrownsPillow;
    @FXML private HBox playerCrownsPillow;
    @FXML private Button returnToMenuButton;
    /**
     * The constant playerTowers.
     */
    public static List playerTowers;
    /**
     * The constant playerInGameCards.
     */
    public static List playerInGameCards;
    /**
     * The constant enemyTowers.
     */
    public static List enemyTowers;
    /**
     * The constant enemyInGameCards.
     */
    public static List enemyInGameCards;
    /**
     * The Animations.
     */
    public static ArrayList<Timeline> animations;

    /**
     * Initialize.
     */
    public void initialize(){
        addPlayerCrownsToList();
        addEnemyCrownsToList();
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
                    playerCounterLabel.setText(String.valueOf(currentPlayer.getNumberOfStars()));
                    enemyCounterLabel.setText(String.valueOf(bot.getNumberOfStars()));
                    wholeTime--;
                    if (gameEnds()){
                        timeMonitor.setText("00:00");
                        Player winner = gameConclusion();
                        stopAnimations();
                        removeFinalCards();
//                        pane.getChildren().clear();
                        showResults(winner);
                        //..................................................................................................
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
                player =
                        new MediaPlayer(new Media(getClass().getResource("/sound effects and musics/arena music.mp3").toString()));
                player.setVolume(0.312345);
                player.play();
            }
        };
        timer1.schedule(timerTask, 3000);
    }

    /**
     * Remove final cards.
     */
    public void removeFinalCards(){
        synchronized (playerInGameCards){
            Iterator<Card> playerCards = playerInGameCards.iterator();
            while (playerCards.hasNext()){
                Card card = playerCards.next();
                pane.getChildren().remove(card.getImageView());
            }
        }
        playerInGameCards.clear();


        synchronized (enemyInGameCards){
            Iterator<Card> enemyCards = enemyInGameCards.iterator();
            while (enemyCards.hasNext()){
                Card card = enemyCards.next();
                pane.getChildren().remove(card.getImageView());
            }
        }
        enemyInGameCards.clear();
        enemyTowers.clear();
        synchronized (enemyTowers){
            Iterator<Tower> towerIterator = enemyTowers.iterator();
            while (towerIterator.hasNext()){
                Tower tower = towerIterator.next();
                pane.getChildren().remove(tower.getImageView());
                tower.getImageView().setImage(null);
            }
        }

    }

    /**
     * Stop animations.
     */
    public void stopAnimations(){
        synchronized (animations){
            for (Timeline t: animations){
                t.stop();
                t.getKeyFrames().clear();
                t = null;
            }

        }
    }

    /**
     * Update stars.
     */
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

    /**
     * Game conclusion player.
     *
     * @return the player
     */
    public Player gameConclusion(){
        Player winner = null;
        Player loser = null;
        if (currentPlayer.getNumberOfStars() > bot.getNumberOfStars()){
            if (currentPlayer.getNumberOfStars() > 3)
                currentPlayer.setNumberOfStars(3);
            winner = currentPlayer;
            loser = bot;
        } else if (bot.getNumberOfStars() > currentPlayer.getNumberOfStars()){
            if (bot.getNumberOfStars() > 3)
                bot.setNumberOfStars(3);
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
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GAMEDATABASE",
                    "root", "m@96@s97");
            Statement statement = connection.createStatement();
            String insert = "insert into users(username, password, battleHistory, level, xp) VALUES " +
                    "('"+currentUser.getUsername()+"', '"+currentUser.getPassword()+"', " +
                    "'"+history+"', '"+currentUser.getLevel()+"', '"+currentUser.getXp()+"')";
            boolean result = statement.execute(insert);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        currentUser.addToHistory(history);
        currentUser.saveUser();
        return winner;
    }

    /**
     * Game ends boolean.
     *
     * @return the boolean
     */
    public boolean gameEnds(){
        boolean end = false;
        if (currentPlayer.getNumberOfStars() >= 3 || bot.getNumberOfStars() >= 3)
            end = true;
        else if (wholeTime == 0)
            end = true;
        return end;
    }

    /**
     * Open emote window.
     *
     * @param event the event
     */
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

    /**
     * Angry emote.
     *
     * @param event the event
     */
    @FXML public void angryEmote(ActionEvent event){
        String photoPath = ".\\photos\\inside game models\\135-1354443_clash-royale-king-png.png";
        String soundPath = "/sound effects and musics/angry_sound_effect.mp3";
        showEmote(photoPath, soundPath);
    }

    /**
     * Happy emote.
     *
     * @param event the event
     */
    @FXML public void happyEmote(ActionEvent event){
        String photoPath = ".\\photos\\inside game models\\happy_emote_00000.png";
        String soundPath = "/sound effects and musics/happy_sound_effect.mp3";
        showEmote(photoPath, soundPath);
    }

    /**
     * Sad emote.
     *
     * @param event the event
     */
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

    /**
     * Sets current user.
     *
     * @param user the user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        this.currentPlayer = currentUser.getPlayer();
        setOnActionCards();

        getPlayerTowers();

    }

    /**
     * Set bot.
     *
     * @param bot the bot
     */
    public void setBot(Bot bot){
        this.bot = bot;
        getEnemyTowers();
    }
    private void getEnemyTowers(){
        enemyTowers.add(bot.getKingTower());
        enemyTowers.add(bot.getLeftTower());
        enemyTowers.add(bot.getRightTower());
        setBotTowerImages();
    }
    private void getPlayerTowers(){
        playerTowers.add(currentPlayer.getKingTower());
        playerTowers.add(currentPlayer.getLeftTower());
        playerTowers.add(currentPlayer.getRightTower());
        setPlayerTowerImages();
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
    private void setPlayerTowerImages(){
        synchronized (playerTowers){
            Iterator<Tower> it = playerTowers.iterator();
            while (it.hasNext()){
                Tower tower = it.next();
                tower.createPicture(pane);
                tower.playerAttack();
            }
        }
    }
    private void setBotTowerImages(){
        synchronized (enemyTowers){
            Iterator<Tower> it = enemyTowers.iterator();
            while (it.hasNext()){
                Tower tower = it.next();
                tower.createPicture(pane);
                tower.botAttack();
            }
        }
        onTopPane.toFront();
        enemyCrownsPillow.toFront();
        for (ImageView i : enemyCrowns) {
            i.toFront();
        }
    }

    /**
     * Pick card 1.
     *
     * @param event the event
     */
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

    /**
     * Pick card 2.
     *
     * @param event the event
     */
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

    /**
     * Pick card 3.
     *
     * @param event the event
     */
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

    /**
     * Pick card 4.
     *
     * @param event the event
     */
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
                int[] locs = {-20, 0, 20, 40};
                int i=0;
                while (count > 0){
                    selectedCard = generateCard(selectedCard);
                    nextCard.setImage(new Image(tempCard.getPath()));
                    selectedCard.setPathInBattle(DataBase.getPathInBattle(selectedCard, false));
                    selectedCard.createPicture(pane, event.getSceneX() - 20, event.getSceneY() - locs[i]);
                    playerInGameCards.add(selectedCard);
                    selectedCard.startFunctioning(pane);
                    count--;
                    i++;
                }
            } else {
                selectedCard = generateCard(selectedCard);
                nextCard.setImage(new Image(tempCard.getPath()));
                selectedCard.setPathInBattle(DataBase.getPathInBattle(selectedCard, false));
                selectedCard.createPicture(pane, event.getSceneX() - 20, event.getSceneY() - 20);
                playerInGameCards.add(selectedCard);
                selectedCard.startFunctioning(pane);
            }
        } else {
            selectedCard = generateCard(selectedCard);
            nextCard.setImage(new Image(tempCard.getPath()));
            selectedCard.setPathInBattle(DataBase.getPathInBattle(selectedCard, false));
            selectedCard.createPicture(pane, event.getSceneX() - 20, event.getSceneY() - 20);
            playerInGameCards.add(selectedCard);
            selectedCard.startFunctioning(pane);

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

    /**
     * Update tower bars.
     */
    public void updateTowerBars(){
        kingHpBar.setProgress(Double.valueOf(currentPlayer.getKingTower().getHp()) / DataBase.getHP(currentPlayer.getKingTower(), currentUser.getLevel()));
        if (currentPlayer.getKingTower().getHp() == 0)
            pane.getChildren().remove(kingHpBar);
        if (currentPlayer.getKingTower().getHp() < DataBase.getHP(currentPlayer.getKingTower(), currentUser.getLevel()) &&
                !(currentPlayer.getKingTower().isActive()))
            currentPlayer.getKingTower().setActive(true);

        rightPrincessHpBar.setProgress(Double.valueOf(currentPlayer.getRightTower().getHp()) / DataBase.getHP(currentPlayer.getRightTower(), currentUser.getLevel()));
        if (currentPlayer.getRightTower().getHp() == 0)
            pane.getChildren().remove(rightPrincessHpBar);

        leftPrincessHpBar.setProgress(Double.valueOf(currentPlayer.getLeftTower().getHp()) / DataBase.getHP(currentPlayer.getLeftTower(), currentUser.getLevel()));
        if (currentPlayer.getLeftTower().getHp() == 0)
            pane.getChildren().remove(leftPrincessHpBar);


        if (bot.getKingTower().getHp() < DataBase.getHP(bot.getKingTower(), bot.getPlayerLevel()) &&
                !(bot.getKingTower().isActive()))
            bot.getKingTower().setActive(true);
        enemyKingHpBar.setProgress(Double.valueOf(bot.getKingTower().getHp()) / DataBase.getHP(bot.getKingTower(), currentUser.getLevel()));
        if (bot.getKingTower().getHp() == 0)
            pane.getChildren().remove(enemyKingHpBar);

        enemyRightPrincessHpBar.setProgress(Double.valueOf(bot.getRightTower().getHp()) / DataBase.getHP(bot.getRightTower(), currentUser.getLevel()));
        if (bot.getRightTower().getHp() == 0)
            pane.getChildren().remove(enemyRightPrincessHpBar);

        enemyLeftPrincessHpBar.setProgress(Double.valueOf(bot.getLeftTower().getHp()) / DataBase.getHP(bot.getLeftTower(), currentUser.getLevel()));
        if (bot.getLeftTower().getHp() == 0)
            pane.getChildren().remove(enemyLeftPrincessHpBar);

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
            valid = (x > 164 && x < 435) && (y > 10 && y < 489);
        return valid;
    }
    private void addPlayerCrownsToList(){
        playerCrowns = new ArrayList<>();
        playerCrowns.add(playerCrown1);
        playerCrowns.add(playerCrown3);
        playerCrowns.add(playerCrown2);
    }
    private void addEnemyCrownsToList(){
        enemyCrowns = new ArrayList<>();
        enemyCrowns.add(enemyCrown1);
        enemyCrowns.add(enemyCrown3);
        enemyCrowns.add(enemyCrown2);
    }

    /**
     * Back to menu.
     *
     * @param event the event
     */
    @FXML public void backToMenu(ActionEvent event){
        currentPlayer.setNumberOfStars(0);
        bot.setNumberOfStars(0);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainMenu.fxml"));
            stage = (Stage) returnToMenuButton.getScene().getWindow();
            root = loader.load();
            MainMenuController menuController = loader.getController();
            menuController.setCurrentUser(currentUser);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("main menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showResults(Player winner){
        onTopPane.setOpacity(0);
        onTopPane.setVisible(true);
        onTopPane.setDisable(false);
        playerCrownsPillow.setDisable(false);
        playerCrownsPillow.setVisible(true);
        enemyCrownsPillow.setDisable(false);
        enemyCrownsPillow.setVisible(true);
        gameResultLabel.setDisable(false);
        gameResultLabel.setVisible(true);
        FadeTransition fadeResultWindow = new FadeTransition(Duration.seconds(0.4), onTopPane);
        fadeResultWindow.setFromValue(0);
        fadeResultWindow.setToValue(1);
        fadeResultWindow.play();
        if (currentPlayer == winner) {
            gameResultLabel.setText("you won");
            player =
                    new MediaPlayer(new Media(getClass().getResource("/sound effects and musics/scroll_win_02.mp3").toString()));
        }
        else if (bot == winner) {
            gameResultLabel.setText("you lost");
            player =
                    new MediaPlayer(new Media(getClass().getResource("/sound effects and musics/scroll_lose_01.mp3").toString()));
        }
        player.play();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                FadeTransition fadeRedPillows = new FadeTransition(Duration.seconds(0.4), enemyCrownsPillow);
                FadeTransition fadeBluePillows = new FadeTransition(Duration.seconds(0.4), playerCrownsPillow);
                FadeTransition fadeResultLabel = new FadeTransition(Duration.seconds(0.4), gameResultLabel);
                fadeBluePillows.setFromValue(0);
                fadeBluePillows.setToValue(1);
                fadeRedPillows.setFromValue(0);
                fadeRedPillows.setToValue(1);
                fadeResultLabel.setFromValue(0);
                fadeResultLabel.setToValue(1);
                SequentialTransition sequentialTransition =
                        new SequentialTransition(fadeRedPillows, fadeBluePillows, fadeResultLabel);
                sequentialTransition.play();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1000);
        Timer timer1 = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < currentPlayer.getNumberOfStars(); i++) {
                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.4 + (.1 * i)), playerCrowns.get(i));
                    playerCrowns.get(i).setVisible(true);
                    playerCrowns.get(i).setDisable(false);
                    fadeTransition.setFromValue(0);
                    fadeTransition.setToValue(1);
                    fadeTransition.play();
                }
                for (int i = 0; i < bot.getNumberOfStars(); i++) {
                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.4 + (.1 * i)), enemyCrowns.get(i));
                    enemyCrowns.get(i).setVisible(true);
                    enemyCrowns.get(i).setDisable(false);
                    fadeTransition.setFromValue(0);
                    fadeTransition.setToValue(1);
                    fadeTransition.play();
                }
                returnToMenuButton.setDisable(false);
                returnToMenuButton.setVisible(true);
                FadeTransition fadeMenuButton = new FadeTransition(Duration.seconds(0.4), returnToMenuButton);
                fadeMenuButton.setFromValue(0);
                fadeMenuButton.setToValue(1);
                fadeMenuButton.play();
            }
        };
        timer1.schedule(task1, 2200);
    }


}
