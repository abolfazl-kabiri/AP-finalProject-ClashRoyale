package cards;

import controller.TrainingCampController;
import sample.GameElement;
import towers.Tower;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Spell extends Card{
    protected double duration = 2;
    protected double radius;
    public Spell(double radius, int cost,
                 String path, int damage, int hp){
        super(cost, path, damage, hp);
        this.radius = radius;
    }

    @Override
    public void startFunctioning(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                duration--;
                if (duration == 0){
                    if (TrainingCampController.playerInGameCards.contains(this))
                        TrainingCampController.playerInGameCards.remove(this);
                    else if (TrainingCampController.enemyInGameCards.contains(this))
                        TrainingCampController.enemyInGameCards.remove(this);
                    imageView.setVisible(false);
                    imageView.setDisable(true);
                    damage = 0;
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        hit();
    }
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
                    card.setHp(card.getHp() - (this.damage / 2));
                    checkHp(card);
                }
            }
        }

        synchronized (TrainingCampController.enemyTowers){
            Iterator<Tower> it = TrainingCampController.enemyTowers.iterator();
            while (it.hasNext()){
                Tower tower = it.next();
                if (checkX(tower) && checkY(tower)){
                    tower.setHp(tower.getHp() - (this.damage / 2));
                    checkHp(tower);
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
                    card.setHp(card.getHp() - (this.damage / 2));
                    checkHp(card);
                }
            }
        }

        synchronized (TrainingCampController.playerTowers){
            Iterator<Tower> it = TrainingCampController.playerTowers.iterator();
            while (it.hasNext()){
                Tower tower = it.next();
                if (checkX(tower) && checkY(tower)){
                    tower.setHp(tower.getHp() - (this.damage / 2));
                    checkHp(tower);
                }
            }
        }
    }
    public boolean checkX(GameElement gameElement){
        return this.getX() + (15 * radius) > gameElement.getX() &&
                this.getX() - (15 * radius) < gameElement.getX();
    }
    public boolean checkY(GameElement gameElement){
        return this.getY() + (15 * radius) > gameElement.getY() &&
                this.getY() - (15 * radius) < gameElement.getY();
    }
    public void checkHp(Card card){
        if (card.getHp() <= 0){
            card.setHp(0);
            card.setDamage(0);
            card.getImageView().setVisible(false);
            card.getImageView().setDisable(true);
            if (TrainingCampController.enemyInGameCards.contains(card))
                TrainingCampController.enemyInGameCards.remove(card);
            else if (TrainingCampController.playerInGameCards.contains(card))
                TrainingCampController.playerInGameCards.remove(card);
            card = null;
        }
    }
    public void checkHp(Tower tower){
        if (tower.getHp() <= 0){
            tower.setHp(0);
            tower.setDamage(0);
            tower.getImageView().setVisible(false);
            tower.getImageView().setDisable(true);
            if (TrainingCampController.enemyTowers.contains(tower))
                TrainingCampController.enemyTowers.remove(tower);
            else if (TrainingCampController.playerTowers.contains(tower))
                TrainingCampController.playerTowers.remove(tower);
        }
    }

}
