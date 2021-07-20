package towers;

import cards.Card;
import cards.Soldier;
import cards.Spell;
import controller.TrainingCampController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sample.DataBase;
import sample.GameElement;

import java.util.Iterator;


public abstract class Tower extends GameElement {

    protected int hp;
    protected int damage;
    protected double hitSpeed;
    protected double range;
    protected boolean isActive;
    protected String path;
    transient protected Timeline hittingAnimation;
    protected int numberOfStars;
    public Tower(int hp, int damage, double hitSpeed, double range, boolean isActive, String path, double x, double y, int numberOfStars) {
        super(x, y, 40, 54);
        this.hp = hp;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.range = range;
        this.isActive = isActive;
        this.path = path;
        this.numberOfStars = numberOfStars;
        this.setPathInBattle(DataBase.getPathInBattle(this));

    }

    public int getNumberOfStars() {
        return numberOfStars;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public void botAttack(){
        hittingAnimation = new Timeline(new KeyFrame(Duration.seconds(this.hitSpeed), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
        }));
        hittingAnimation.setCycleCount(Timeline.INDEFINITE);
        hittingAnimation.play();
        TrainingCampController.animations.add(hittingAnimation);

    }
    public void playerAttack(){
         hittingAnimation = new Timeline(new KeyFrame(Duration.seconds(this.hitSpeed), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
        }));
         hittingAnimation.setCycleCount(Timeline.INDEFINITE);
         hittingAnimation.play();
         TrainingCampController.animations.add(hittingAnimation);


    }
    private void hit(Card card){
        card.setHp(card.getHp() - getDamage());
        if (card.getHp() <= 0) {
            card.setHp(0);
            card.setDamage(0);
            card.getImageView().setVisible(false);
            card.getImageView().setDisable(true);
            card = null;
        }
    }
    private boolean checkX(GameElement gameElement){
        return this.getX() + (15 * range) > gameElement.getX() &&
                this.getX() - (15 * range) < gameElement.getX();
    }
    private boolean checkY(GameElement gameElement){
        return this.getY() + (15 * range) > gameElement.getY() &&
                this.getY() - (15 * range) < gameElement.getY();
    }
}
