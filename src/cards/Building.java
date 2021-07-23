package cards;


import controller.TrainingCampController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.DataBase;
import sample.GameElement;
import sample.Target;

import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Building.
 */
public abstract class Building extends Card{
    /**
     * The Last attack.
     */
    transient protected Date lastAttack;
    /**
     * The Hit speed.
     */
    protected double hitSpeed;
    /**
     * The Target.
     */
    protected Target target;
    /**
     * The Range.
     */
    protected double range;
    /**
     * The Life time.
     */
    protected int lifeTime;
    /**
     * The Timer.
     */
    protected Timer timer;
    /**
     * The Building timeline.
     */
    protected Timeline buildingTimeline;

    /**
     * Instantiates a new Building.
     *
     * @param hitSpeed the hit speed
     * @param target   the target
     * @param range    the range
     * @param lifeTime the life time
     * @param cost     the cost
     * @param path     the path
     * @param damage   the damage
     * @param hp       the hp
     */
    public Building(double hitSpeed, Target target,
                    double range, int lifeTime,
                    int cost, String path,
                    int damage, int hp){
        super(cost, path, damage, hp);
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
        this.lifeTime  = lifeTime;
        lastAttack = new Date();
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
        if (hp <= 0)
            removeBuilding();
    }
    private boolean isInferno(){
        return this instanceof InfernoTower;
    }
    private void updateInfernoDamage(){
        setDamage(damage + DataBase.getInfernoDPS(3));
    }
    @Override
    public void startFunctioning(Pane pane){
        locatedOnPane = pane;
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    lifeTime--;
                    if (isInferno())
                        updateInfernoDamage();
                    if (lifeTime == 0){
                        removeBuilding();
                        removeFromList();
                        timer.cancel();
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        buildingTimeline = new Timeline(new KeyFrame(Duration.seconds(hitSpeed), event -> handleAttack() ));
        buildingTimeline.setCycleCount(Timeline.INDEFINITE);
        buildingTimeline.play();
        TrainingCampController.animations.add(buildingTimeline);
    }
    private void handleAttack(){
        if (TrainingCampController.playerInGameCards.contains(this))
            handlePlayerAttack();
        else if (TrainingCampController.enemyInGameCards.contains(this))
            handleBotAttack();
    }
    private void handlePlayerAttack(){
        synchronized (TrainingCampController.enemyInGameCards){
            Iterator<Card> enemyIterator = TrainingCampController.enemyInGameCards.iterator();
            while (enemyIterator.hasNext()){
                Card temp = enemyIterator.next();
                if (checkX(temp) && checkY(temp) && DataBase.isTargetValid(temp, this)){
                    if (!(temp instanceof Spell)){
                        hit(temp);
                        if(temp.getHp() <= 0){
                            enemyIterator.remove();
                            temp = null;
                        }
                    }

                }
            }
        }
    }
    private void handleBotAttack(){
        synchronized (TrainingCampController.playerInGameCards){
            Iterator<Card> playerIterator = TrainingCampController.playerInGameCards.iterator();
            while (playerIterator.hasNext()){
                Card temp = playerIterator.next();
                if (checkX(temp) && checkY(temp) && DataBase.isTargetValid(temp, this)){
                    if (!(temp instanceof Spell)){
                        hit(temp);
                        if(temp.getHp() <= 0){
                            playerIterator.remove();
                            temp = null;
                        }
                    }
                }
            }
        }
    }
    private boolean checkX(GameElement gm){
        return this.getX() + (15 * range) > gm.getX() &&
                this.getX() - (15 * range) < gm.getX();
    }
    private boolean checkY(GameElement gm){
        return this.getY() + (15 * range) > gm.getY() &&
                this.getY() - (15 * range) < gm.getY();
    }
    private void hit(Card card){
        Date currentDate = new Date();
        if (currentDate.getTime() - lastAttack.getTime() >= hitSpeed * 1000){
            card.setHp(card.getHp() - getDamage());
            if (card.getHp() <= 0) {
                card.removeCard();
                card = null;
            }
        }

    }
    @Override
    public void rageIt(){
        this.damage *= 1.4;
        this.hitSpeed /= 1.4;
        this.isRaged = true;
    }
    @Override
    public void unRageIt(){
        this.damage /= 1.4;
        this.hitSpeed *= 1.4;
        this.isRaged = false;
    }

    /**
     * Remove from list.
     */
    public void removeFromList(){
        if (TrainingCampController.enemyInGameCards.contains(this))
            TrainingCampController.enemyInGameCards.remove(this);
        else if (TrainingCampController.playerInGameCards.contains(this))
            TrainingCampController.playerInGameCards.remove(this);
    }

    /**
     * Remove building.
     */
    public void removeBuilding(){
        setDamage(0);
        if (buildingTimeline != null){
            TrainingCampController.animations.remove(buildingTimeline);
            buildingTimeline.stop();
            buildingTimeline.getKeyFrames().clear();
            buildingTimeline = null;
        }

        if (locatedOnPane.getChildren().contains(this.imageView))
            locatedOnPane.getChildren().remove(this.imageView);

        setX(0);
        setY(0);
    }

    /**
     * Gets target.
     *
     * @return the target
     */
    public Target getTarget() {
        return target;
    }
}
