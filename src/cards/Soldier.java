package cards;

import controller.TrainingCampController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import sample.DataBase;
import sample.GameElement;
import sample.Speed;
import sample.Target;
import towers.Tower;

import java.util.Date;
import java.util.Iterator;

/**
 * The type Soldier.
 */
public abstract class Soldier extends Card{

    /**
     * The Last attack.
     */
    transient protected Date lastAttack;
    /**
     * The Damaging image.
     */
    transient protected Image damagingImage;
    /**
     * The Hit speed.
     */
    protected double hitSpeed;
    /**
     * The Speed.
     */
    protected Speed speed;
    /**
     * The Target.
     */
    protected Target target;
    /**
     * The Range.
     */
    protected String range;
    /**
     * The Is area splash.
     */
    protected boolean isAreaSplash;
    /**
     * The Is bot.
     */
    protected boolean isBot;
    /**
     * The Count.
     */
    protected int count;
    /**
     * The Dx.
     */
    protected int dx;
    /**
     * The Dy.
     */
    protected int dy;
    /**
     * The Sp.
     */
    protected double sp;
    /**
     * The Area splash done.
     */
    protected boolean areaSplashDone;

    /**
     * Instantiates a new Soldier.
     *
     * @param cost         the cost
     * @param hitSpeed     the hit speed
     * @param speed        the speed
     * @param target       the target
     * @param range        the range
     * @param isAreaSplash the is area splash
     * @param count        the count
     * @param path         the path
     * @param damage       the damage
     * @param hp           the hp
     */
    public Soldier(int cost, double hitSpeed,
                   Speed speed, Target target,
                   String range, boolean isAreaSplash,
                   int count, String path,
                   int damage, int hp) {
        super(cost, path, damage, hp);
        this.hitSpeed = hitSpeed;
        this.speed = speed;
        this.target = target;
        this.range = range;
        this.isAreaSplash = isAreaSplash;
        this.count = count;
        this.isBot = false;
        this.dx = 1;
        this.dy = -1;
        areaSplashDone = false;
        damagingImage = new Image(DataBase.getDamagePath(this, isBot), 43.0, 43.0, false, false);
        lastAttack = new Date();
        sp = speed.getSpeedValue();
    }

    /**
     * Sets bot.
     *
     * @param bot the bot
     */
    public void setBot(boolean bot) {
        isBot = bot;
        this.dy = 1;
        damagingImage = new Image(DataBase.getDamagePath(this, isBot), 43.0, 43.0, false, false);
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public void startFunctioning(Pane pane){
        locatedOnPane = pane;
        movingAnimation = new Timeline(new KeyFrame(Duration.seconds(sp), e-> move()));
        movingAnimation.setCycleCount(Timeline.INDEFINITE);
        movingAnimation.play();
        TrainingCampController.animations.add(movingAnimation);
    }
    @Override
    public void move() {
        if (isBot)
            handleBotMove();
        else
            handlePlayerMove();
        Date currentDate = new Date();
        if (currentDate.getTime() - lastAttack.getTime() > hitSpeed * 1000){
            lastAttack = currentDate;
            attack();
        }
    }
    private void handlePlayerMove(){
        /*soundEffectPlayer = new MediaPlayer(new Media(getClass().getResource(DataBase.getWalkSoundEffect(this)).toString()));
        soundEffectPlayer.setVolume(0.5);
        soundEffectPlayer.play();*/
        this.dx = 1;
//        System.out.println("entered " + this + " " + dx+ " " + dy );
        if (!playerHasReachedBridge() || y > 240){
            if (x < 289){
                if (x < 183)
                    setX(getX() + dx);
                else if (x > 185)
                    setX(getX() - dx);
            } else {
                if (x < 376)
                    setX(getX() + dx);
                else if (x > 378)
                    setX(getX() - dx);
            } if (y > 240.0)
                setY(getY() + dy);
        } else if (y < 220 && !towerExistsOnPlayerWay()){

            if (x < 273.0)
                setX(getX() + dx);
            else if (x > 277.0)
                setX(getX() - dx);
            if (y > 30.0)
                setY(getY() + dy);


        } else
            setY(getY() + dy);

    }
    private void handleBotMove(){
        /*soundEffectPlayer = new MediaPlayer(new Media(getClass().getResource(DataBase.getWalkSoundEffect(this)).toString()));
        soundEffectPlayer.setVolume(0.5);
        soundEffectPlayer.play();*/
        this.dx = 1;
        if (!botHasReachedBridge() || y < 220){
            if (x < 289){
                if (x <= 183)
                    setX(getX() + dx);
                else if (x >= 185)
                    setX(getX() - dx);
            } else {
                if (x <= 376)
                    setX(getX() + dx);
                else if (x >= 378)
                    setX(getX() - dx);
            } if (y <= 220)
                setY(getY() + dy);
        } else if (y > 240 && !towerExistsOnBotWay()){
//            System.out.println("in");
            if (x < 273.0){
                setX(getX() + dx);
            }
            else if (x > 277.0){
                setX(getX() - dx);
            }
            if (y < 415.0)
                setY(getY() + dy);
        } else
            setY(getY() + dy);
    }

    @Override
    public void attack() {
        if (isBot)
            handleBotAttack();
        else
            handlePlayerAttack();
    }
    private void handlePlayerAttack(){
        /*soundEffectPlayer = new MediaPlayer(new Media(getClass().getResource(DataBase.getHitSoundEffect(this)).toString()));
        soundEffectPlayer.setVolume(0.5);
        soundEffectPlayer.play();*/
        synchronized (TrainingCampController.enemyInGameCards){
            Iterator <Card> enemyIterator = TrainingCampController.enemyInGameCards.iterator();
            while (enemyIterator.hasNext()){
                Card temp = enemyIterator.next();
                if (checkXRange(temp) && checkYRange(temp) && DataBase.isTargetValid(temp, this)){
                    this.dx = 0;
                    this.dy = 0;
                    hit(temp);
                    if (temp.getHp() <= 0){
                        enemyIterator.remove();
                        temp = null;
                    }
                }
            }
        }
        synchronized (TrainingCampController.enemyTowers){
            Iterator<Tower> towerIterator = TrainingCampController.enemyTowers.iterator();
            while (towerIterator.hasNext()){
                Tower tower = towerIterator.next();
                if (checkXRange(tower) && checkYRange(tower)){
                    this.dx = 0;
                    this.dy = 0;
                    hit(tower);
                    if (tower.getHp() <=0 ){
                        towerIterator.remove();
                    }
                }
            }
        }
    }
    private void handleBotAttack(){
        /*soundEffectPlayer = new MediaPlayer(new Media(getClass().getResource(DataBase.getHitSoundEffect(this)).toString()));
        soundEffectPlayer.play();*/
        synchronized (TrainingCampController.playerInGameCards){
            Iterator <Card> playerIterator = TrainingCampController.playerInGameCards.iterator();
            while (playerIterator.hasNext()){
                Card temp = playerIterator.next();
                if (checkXRange(temp) && checkYRange(temp) && DataBase.isTargetValid(temp, this)){
                    this.dx = 0;
                    this.dy = 0;
                    hit(temp);
                    if (temp.getHp() <= 0){
                        playerIterator.remove();
                        temp = null;
                    }
                }
            }
        }
        synchronized (TrainingCampController.playerTowers){
            Iterator<Tower> towerIterator = TrainingCampController.playerTowers.iterator();
            while (towerIterator.hasNext()){
                Tower tower = towerIterator.next();
                if (checkXRange(tower) && checkYRange(tower)){
                    this.dx = 0;
                    this.dy = 0;
                    hit(tower);
                    if (tower.getHp() <=0 ){
                        towerIterator.remove();
                    }
                }
            }
        }

    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    private void hit(Card card){
        if (card instanceof Building || card instanceof Soldier){
            /*soundEffectPlayer = new MediaPlayer(new Media(getClass().getResource(DataBase.getHitSoundEffect(this)).toString()));
            soundEffectPlayer.setVolume(0.5);
            soundEffectPlayer.play();*/
            this.getImageView().setImage(damagingImage);
            card.setHp(card.getHp() - this.damage);
//            System.out.println(this + " hits " + card);
            checkHp(card);
        }
    }
    private void hit(Tower tower){
        /*soundEffectPlayer = new MediaPlayer(new Media(getClass().getResource(DataBase.getHitSoundEffect(this)).toString()));
        soundEffectPlayer.setVolume(0.5);
        soundEffectPlayer.play();*/
        this.getImageView().setImage(damagingImage);
        tower.setHp(tower.getHp() - this.damage);
        checkHp(tower);
    }

    private void checkHp(Tower tower){
        if (tower.getHp() <= 0) {
            this.getImageView().setImage(this.image);
            if (!isBot)
                dy = -1;
            else
                dy = 1;
            dx = 1;
            tower.removeTower();
        }
    }
    private void checkHp(Card card){
        if (card.getHp() <= 0){
            this.getImageView().setImage(this.image);
            if (!isBot)
                dy = -1;
            else
                dy = 1;
            dx = 1;
//            System.out.println(dx + " " + dy);
//            movingAnimation.play();
            card.removeCard();
        }
    }

    private boolean checkXRange(GameElement gm){
        if (range.equals("melee")){
            return this.getX() - (2 * 15) < gm.getX() &&
                    this.getX() + (2 * 15) > gm.getX();
        } else {
            return this.getX() + (15 * Integer.parseInt(range)) > gm.getX() &&
                    this.getX() - (15 * Integer.parseInt(range)) < gm.getX();
        }
    }
    private boolean checkYRange(GameElement gm){
        if (range.equals("melee")){
            if (!isBot)
                return this.getY() - (2 * 15) < gm.getY();
            return this.getY() + (2 * 15) > gm.getY();
        } else {
            return this.getY() + (15 * Integer.parseInt(range)) > gm.getY() &&
                    this.getY() - (15 * Integer.parseInt(range)) < gm.getY();
        }
    }

    private boolean checkSplash(GameElement gm){
        return this.getX() - 15 < gm.getX() &&
                this.getX() + 15 > gm.getX() &&
                this.getY() + 15 > gm.getY() &&
                this.getY() - 15 < gm.getY();
    }

    /**
     * Damage area splash.
     */
    public void damageAreaSplash(){
        if (!areaSplashDone){
            if (isBot)
                handleBotSplash();
            else
                handlePlayerSplash();
        }
        areaSplashDone = true;

    }

    /**
     * Handle bot splash.
     */
    public void handleBotSplash(){
        synchronized (TrainingCampController.playerInGameCards){
            Iterator<Card> it = TrainingCampController.playerInGameCards.iterator();
            while (it.hasNext()){
                Card card = it.next();
                if(checkSplash(card)){
                    card.setHp(0);
                    card.removeCard();
                    it.remove();
                    card = null;
                }
            }
        }
    }

    /**
     * Handle player splash.
     */
    public void handlePlayerSplash(){
        synchronized (TrainingCampController.enemyInGameCards){
            Iterator<Card> it = TrainingCampController.enemyInGameCards.iterator();
            while (it.hasNext()){
                Card card = it.next();
                if(checkSplash(card)){
                    card.setHp(0);
                    card.removeCard();
                    it.remove();
                    card = null;
                }
            }
        }
    }


    private boolean towerExistsOnBotWay(){
        synchronized (TrainingCampController.playerTowers){
            Iterator<Tower> iterator = TrainingCampController.playerTowers.iterator();
            while (iterator.hasNext()){
                Tower tower = iterator.next();
                if (tower.getX() + 10 > x && tower.getX() - 10 < x && tower.getHp() > 0)
                    return true;
            }
        }
        return false;
    }
    private boolean towerExistsOnPlayerWay(){
        synchronized (TrainingCampController.enemyTowers){
            Iterator<Tower> iterator = TrainingCampController.enemyTowers.iterator();
            while (iterator.hasNext()){
                Tower tower = iterator.next();
                if (tower.getX() + 10 > x && tower.getX() - 10 < x && tower.getHp() > 0)
                    return true;
            }
        }
        return false;
    }
    private boolean playerHasReachedBridge(){
        if (y < 220)
            return true;
        return (x > 183 && x < 185) || (x > 376 && x < 378);
    }
    private boolean botHasReachedBridge(){
        if (y > 240)
            return true;
        return (x > 183 && x < 185) || (x > 376 && x < 378);
    }

    /**
     * Get target target.
     *
     * @return the target
     */
    public Target getTarget(){
        return this.target;
    }


    @Override
    public void rageIt(){
        this.damage *= 1.4;
        this.hitSpeed /= 1.4;
        this.sp /= 1.4;
        this.isRaged = true;
    }
    @Override
    public void unRageIt(){
        this.damage /= 1.4;
        this.hitSpeed *= 1.4;
        this.sp *= 1.4;
        this.isRaged = false;
    }
}
