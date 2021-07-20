package cards;

import controller.TrainingCampController;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class Rage extends Spell{
    public Rage(double duration){
        super(5, 3,
                ".\\photos\\cardsImage\\rage_00000.png", 0, 1);
        this.duration = duration;
    }

    @Override
    public void startFunctioning(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                duration -= 0.5;
                if (duration < 0.5){
                    if (TrainingCampController.playerInGameCards.contains(this))
                        TrainingCampController.playerInGameCards.remove(this);
                    else if (TrainingCampController.enemyInGameCards.contains(this))
                        TrainingCampController.enemyInGameCards.remove(this);
                    imageView.setVisible(false);
                    imageView.setDisable(true);
                    damage = 0;
                    hp = 0;
                    cancelDamage();
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0 , 500);
        rage();
    }
    public void rage(){
        if (TrainingCampController.playerInGameCards.contains(this))
            handlePlayerRage();
        else if (TrainingCampController.enemyInGameCards.contains(this))
            handleBotRage();
    }
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
    public void cancelDamage(){
        if (TrainingCampController.playerInGameCards.contains(this))
            cancelPlayerRage();
        else if (TrainingCampController.enemyInGameCards.contains(this))
            cancelBotRage();
    }
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
