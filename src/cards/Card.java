package cards;

import controller.TrainingCampController;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import sample.DataBase;
import sample.GameElement;


/**
 * The type Card.
 */
public abstract class Card extends GameElement {
    /**
     * The Sound effect player.
     */
    protected MediaPlayer soundEffectPlayer;
    /**
     * The Located on pane.
     */
    transient protected Pane locatedOnPane;
    /**
     * The Is raged.
     */
    protected boolean isRaged;
    /**
     * The Damage.
     */
    protected int damage;
    /**
     * The Hp.
     */
    protected int hp;
    /**
     * The Cost.
     */
    protected int cost;
    /**
     * The Path.
     */
    protected String path;
    /**
     * The Moving animation.
     */
    transient protected Timeline movingAnimation;

    /**
     * Instantiates a new Card.
     *
     * @param cost   the cost
     * @param path   the path
     * @param damage the damage
     * @param hp     the hp
     */
    public Card(int cost, String path, int damage, int hp){
        super(0.0, 0.0, 43, 43);
        this.cost = cost;
        this.path = path;
        this.damage = damage;
        this.hp = hp;
        this.setPathInBattle(DataBase.getPathInBattle(this, true));
        isRaged = false;
    }

    /**
     * Start functioning.
     *
     * @param pane the pane
     */
    public void startFunctioning(Pane pane){

    }

    /**
     * Move.
     */
    public void move(){

    }

    /**
     * Attack.
     */
    public void attack(){

    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public int getCost() {
        return cost;
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
     * Rage it.
     */
    public void rageIt(){
        damage /= 1.4;
        this.isRaged = true;
    }

    /**
     * Un rage it.
     */
    public void unRageIt(){
        damage *= 1.4;
        this.isRaged = false;
    }

    /**
     * Remove card.
     */
    public void removeCard(){
        if (this instanceof Soldier){
            if (((Soldier) this).isAreaSplash && !(((Soldier) this).areaSplashDone))
                ((Soldier) this).damageAreaSplash();
        }
        this.setHp(0);
        this.setDamage(0);
        if (this.movingAnimation != null){
            TrainingCampController.animations.remove(this.movingAnimation);
            this.movingAnimation.stop();
            this.movingAnimation.getKeyFrames().clear();
            this.movingAnimation = null;
        }
        locatedOnPane.getChildren().remove(this.imageView);

        if (this instanceof Building)
            ((Building) this).removeBuilding();

        setX(0);
        setY(0);
    }

    /**
     * Is raged boolean.
     *
     * @return the boolean
     */
    public boolean isRaged() {
        return isRaged;
    }
}
