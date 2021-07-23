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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sample.DataBase;
import sample.GameElement;

import java.util.Date;
import java.util.Iterator;


/**
 * The type Tower.
 */
public abstract class Tower extends GameElement {

    /**
     * The Last attack.
     */
    transient protected Date lastAttack;
    /**
     * The Hp.
     */
    protected int hp;
    /**
     * The Damage.
     */
    protected int damage;
    /**
     * The Hit speed.
     */
    protected double hitSpeed;
    /**
     * The Range.
     */
    protected double range;
    /**
     * The Is active.
     */
    protected boolean isActive;
    /**
     * The Path.
     */
    protected String path;
    /**
     * The Hitting animation.
     */
    transient protected Timeline hittingAnimation;
    /**
     * The Number of stars.
     */
    protected int numberOfStars;
    /**
     * The Destroyed tower.
     */
    transient protected Image destroyedTower;

    /**
     * Instantiates a new Tower.
     *
     * @param hp            the hp
     * @param damage        the damage
     * @param hitSpeed      the hit speed
     * @param range         the range
     * @param isActive      the is active
     * @param path          the path
     * @param x             the x
     * @param y             the y
     * @param numberOfStars the number of stars
     */
    public Tower(int hp, int damage, double hitSpeed, double range,
                 boolean isActive, String path,
                 double x, double y, int numberOfStars) {
        super(x, y, 40, 54);
        this.hp = hp;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.range = range;
        this.isActive = isActive;
        this.path = path;
        this.numberOfStars = numberOfStars;
        this.setPathInBattle(DataBase.getPathInBattle(this));
        destroyedTower = new Image("./photos/inside game models/destroyed tower.png", 60.0, 60.0, false, false);
        lastAttack = new Date();
    }

    /**
     * Gets number of stars.
     *
     * @return the number of stars
     */
    public int getNumberOfStars() {
        return numberOfStars;
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets hp.
     *
     * @param hp the hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Bot attack.
     */
    public void botAttack(){
        hittingAnimation = new Timeline(new KeyFrame(Duration.seconds(this.hitSpeed), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                synchronized (TrainingCampController.playerInGameCards){
                    Iterator<Card> enemyIterator = TrainingCampController.playerInGameCards.iterator();
                    while (enemyIterator.hasNext()){
                        Card temp = enemyIterator.next();
                        if (checkX(temp) && checkY(temp)){
                            if (!(temp instanceof Spell)){
                                hit(temp);
                                if (temp.getHp() <= 0)
                                    enemyIterator.remove();
                            }
                        }
                    }
                }
            }
        }));
        hittingAnimation.setCycleCount(Timeline.INDEFINITE);
        hittingAnimation.play();
        TrainingCampController.animations.add(hittingAnimation);

    }

    /**
     * Player attack.
     */
    public void playerAttack(){
         hittingAnimation = new Timeline(new KeyFrame(Duration.seconds(this.hitSpeed), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                synchronized (TrainingCampController.enemyInGameCards){
                    Iterator<Card> enemyIterator = TrainingCampController.enemyInGameCards.iterator();
                    while (enemyIterator.hasNext()){
                        Card temp = enemyIterator.next();
                        if (checkX(temp) && checkY(temp)){
                            if (!(temp instanceof Spell)){
                                hit(temp);
                                if (temp.getHp() <= 0)
                                    enemyIterator.remove();
                            }
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
        Date currentDate = new Date();
        if (currentDate.getTime() - lastAttack.getTime() >= hitSpeed * 1000 && isActive){
            lastAttack = currentDate;
            card.setHp(card.getHp() - getDamage());
            if (card.getHp() <= 0) {
                card.removeCard();
            }
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

    /**
     * Remove tower.
     */
    public void removeTower(){
        this.setHp(0);
        this.setDamage(0);
        imageView.setImage(destroyedTower);

    }
}
