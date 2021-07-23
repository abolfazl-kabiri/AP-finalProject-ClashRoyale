package cards;

import controller.TrainingCampController;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import sample.GameElement;
import towers.Tower;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Spell.
 */
public abstract class Spell extends Card{
    /**
     * The Duration.
     */
    protected double duration = 2;
    /**
     * The Radius.
     */
    protected double radius;

    /**
     * Instantiates a new Spell.
     *
     * @param radius the radius
     * @param cost   the cost
     * @param path   the path
     * @param damage the damage
     * @param hp     the hp
     */
    public Spell(double radius, int cost,
                 String path, int damage, int hp){
        super(cost, path, damage, hp);
        this.radius = radius;
    }

    @Override
    public void startFunctioning(Pane pane){
        locatedOnPane = pane;
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    duration--;
                    if (duration == 0) {
                        removeSpell();
                        timer.cancel();
                    }
                } );
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        hit();
    }

    /**
     * Hit.
     */
    public void hit(){
        if (TrainingCampController.playerInGameCards.contains(this))
            handlePlayerHit();
        else if (TrainingCampController.enemyInGameCards.contains(this))
            handleBotHit();
    }
    private void handlePlayerHit(){
        synchronized (TrainingCampController.enemyInGameCards){
            Iterator<Card> it = TrainingCampController.enemyInGameCards.iterator();
            while (it.hasNext()){
                Card card = it.next();
                if (checkX(card) && checkY(card)){
                    card.setHp(card.getHp() - (this.damage));
                    checkHp(card);
                    if (card.getHp() <= 0)
                        it.remove();
                    card = null;
                }
            }
        }

        synchronized (TrainingCampController.enemyTowers){
            Iterator<Tower> it = TrainingCampController.enemyTowers.iterator();
            while (it.hasNext()){
                Tower tower = it.next();
                if (checkX(tower) && checkY(tower)){
                    tower.setHp(tower.getHp() - (this.damage));
                    checkHp(tower);
                    if (tower.getHp() <= 0)
                        it.remove();
                }
            }
        }
    }
    private void handleBotHit(){
        synchronized (TrainingCampController.playerInGameCards){
            Iterator<Card> it = TrainingCampController.playerInGameCards.iterator();
            while (it.hasNext()){
                Card card = it.next();
                if (checkX(card) && checkY(card)){
                    card.setHp(card.getHp() - (this.damage));
                    checkHp(card);
                    if (card.getHp() <= 0){
                        it.remove();
                        card = null;
                    }

                }
            }
        }

        synchronized (TrainingCampController.playerTowers){
            Iterator<Tower> it = TrainingCampController.playerTowers.iterator();
            while (it.hasNext()){
                Tower tower = it.next();
                if (checkX(tower) && checkY(tower)){
                    tower.setHp(tower.getHp() - (this.damage));
                    checkHp(tower);
                    if (tower.getHp() <= 0)
                        it.remove();
                }
            }
        }
    }

    /**
     * Check x boolean.
     *
     * @param gameElement the game element
     * @return the boolean
     */
    public boolean checkX(GameElement gameElement){
        return this.getX() + (15 * radius) > gameElement.getX() &&
                this.getX() - (15 * radius) < gameElement.getX();
    }

    /**
     * Check y boolean.
     *
     * @param gameElement the game element
     * @return the boolean
     */
    public boolean checkY(GameElement gameElement){
        return this.getY() + (15 * radius) > gameElement.getY() &&
                this.getY() - (15 * radius) < gameElement.getY();
    }

    /**
     * Check hp.
     *
     * @param card the card
     */
    public void checkHp(Card card){
        if (card.getHp() <= 0){
            card.removeCard();
        }
    }

    /**
     * Remove spell.
     */
    public void removeSpell(){
        if (TrainingCampController.playerInGameCards.contains(this))
            TrainingCampController.playerInGameCards.remove(this);
        else if (TrainingCampController.enemyInGameCards.contains(this))
            TrainingCampController.enemyInGameCards.remove(this);
        setDamage(0);
        if (locatedOnPane.getChildren().contains(this.imageView))
            locatedOnPane.getChildren().remove(this.imageView);

        setX(0);
        setY(0);
    }

    /**
     * Check hp.
     *
     * @param tower the tower
     */
    public void checkHp(Tower tower){
        if (tower.getHp() <= 0){
            tower.setHp(0);
            tower.setDamage(0);
            tower.removeTower();
        }
    }

}
