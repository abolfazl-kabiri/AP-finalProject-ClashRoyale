package cards;


import controller.TrainingCampController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import sample.DataBase;
import sample.GameElement;
import sample.Target;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Building extends Card{
    protected double hitSpeed;
    protected Target target;
    protected double range;
    protected int lifeTime;
    protected Timer timer;
    protected Timeline buildingTimeline;
    public Building(double hitSpeed, Target target,
                    double range, int lifeTime,
                    int cost, String path,
                    int damage, int hp){
        super(cost, path, damage, hp);
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
        this.lifeTime  = lifeTime;
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
        if (hp <= 0){
            timer.cancel();
            buildingTimeline.stop();
            setDamage(0);
            imageView.setDisable(true);
            imageView.setVisible(false);
            if (TrainingCampController.enemyInGameCards.contains(this))
                TrainingCampController.enemyInGameCards.remove(this);
            else if (TrainingCampController.playerInGameCards.contains(this))
                TrainingCampController.playerInGameCards.remove(this);
        }
    }
    private boolean isInferno(){
        return this instanceof InfernoTower;
    }
    private void updateInfernoDamage(){
        setDamage(damage + DataBase.getInfernoDPS(3));
    }
    @Override
    public void startFunctioning(){
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    lifeTime--;
                    if (isInferno())
                        updateInfernoDamage();
                    if (lifeTime == 0){
                        setDamage(0);
                        imageView.setVisible(false);
                        imageView.setDisable(true);
                        buildingTimeline.stop();
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
                if (checkX(temp) && checkY(temp)){
                    if (!(temp instanceof Spell))
                        hit(temp);
                }
            }
        }
    }
    private void handleBotAttack(){
        synchronized (TrainingCampController.playerInGameCards){
            Iterator<Card> enemyIterator = TrainingCampController.playerInGameCards.iterator();
            while (enemyIterator.hasNext()){
                Card temp = enemyIterator.next();
                if (checkX(temp) && checkY(temp)){
                    if (!(temp instanceof Spell))
                        hit(temp);
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
        card.setHp(card.getHp() - getDamage());
        if (card.getHp() <= 0) {
            card.setHp(0);
            card.setDamage(0);
            card.getImageView().setVisible(false);
            card.getImageView().setDisable(true);
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
}
