package cards;

import controller.TrainingCampController;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Rage.
 */
public class Rage extends Spell{
    /**
     * Instantiates a new Rage.
     *
     * @param duration the duration
     */
    public Rage(double duration){
        super(5, 3,
                ".\\photos\\cardsImage\\rage_00000.png", 0, 1);
        this.duration = duration;
    }

    @Override
    public void startFunctioning(Pane pane){
        locatedOnPane = pane;
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    duration -= 0.5;
                    if (duration < 0.5){
                        removeSpell();
                        cancelDamage();
                        timer.cancel();
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(timerTask, 0 , 500);
        rage();
    }

    /**
     * Rage.
     */
    public void rage(){
        if (TrainingCampController.playerInGameCards.contains(this))
            handlePlayerRage();
        else if (TrainingCampController.enemyInGameCards.contains(this))
            handleBotRage();
    }

    /**
     * Handle player rage.
     */
    public void handlePlayerRage(){
        synchronized (TrainingCampController.playerInGameCards){
            Iterator<Card> it = TrainingCampController.playerInGameCards.iterator();
            while (it.hasNext()){
                Card card = it.next();
                if (checkX(card) && checkY(card)){
//                    System.out.println("before rage: " + card.getDamage());
                   card.rageIt();
//                    System.out.println("after rage: " + card.getDamage());
                }
            }
        }
    }

    /**
     * Handle bot rage.
     */
    public void handleBotRage(){
        synchronized (TrainingCampController.enemyInGameCards){
            Iterator<Card> it = TrainingCampController.enemyInGameCards.iterator();
            while (it.hasNext()){
                Card card = it.next();
                if (checkX(card) && checkY(card)){
                   card.rageIt();
                }
            }
        }
    }

    /**
     * Cancel damage.
     */
    public void cancelDamage(){
        if (TrainingCampController.playerInGameCards.contains(this))
            cancelPlayerRage();
        else if (TrainingCampController.enemyInGameCards.contains(this))
            cancelBotRage();
    }

    /**
     * Cancel bot rage.
     */
    public void cancelBotRage(){
        synchronized (TrainingCampController.enemyInGameCards){
            Iterator<Card> it = TrainingCampController.enemyInGameCards.iterator();
            while (it.hasNext()){
                Card card = it.next();
                if (card.isRaged){
                    card.unRageIt();
                }
            }
        }
    }

    /**
     * Cancel player rage.
     */
    public void cancelPlayerRage(){
        synchronized (TrainingCampController.playerInGameCards){
            Iterator<Card> it = TrainingCampController.playerInGameCards.iterator();
            while (it.hasNext()){
                Card card = it.next();
                if (card.isRaged){
                    card.unRageIt();
//                    System.out.println("after canceling: " + card.getDamage());
                }
            }
        }
    }

}
