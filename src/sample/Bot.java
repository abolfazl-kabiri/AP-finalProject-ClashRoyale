package sample;

import cards.*;
import controller.TrainingCampController;
import javafx.scene.layout.Pane;
import towers.KingTower;
import towers.PrincessTower;
import towers.Tower;

import java.util.ArrayList;
import java.util.Random;

public class Bot extends Player{

    private final double [] xLocs = {184.0 , 377.0};
    private final double [] yLocs = {125.0 , 150.0, 175.0, 200.0};
    private Card lastCard;

    public Bot(ArrayList<Card> userCards, int level) {
        super(userCards, level);
        this.selectedCards = null;
        this.kingTower = new KingTower(DataBase.getHP(kingTower, level),DataBase.getDamage(kingTower, level), 275.0, 21.0);
        this.leftTower = new PrincessTower(DataBase.getHP(leftTower, level),DataBase.getDamage(leftTower, level), 183.0, 81.0);
        this.rightTower = new PrincessTower(DataBase.getHP(rightTower, level),DataBase.getDamage(leftTower, level), 377.0, 81.0);
        generateCards();
        fillCardsAttributes();
        fillTowerAttributes();
        kingTower.setPathInBattle(DataBase.getEnemyPathInBattle(kingTower));
        leftTower.setPathInBattle(DataBase.getEnemyPathInBattle(leftTower));
        rightTower.setPathInBattle(DataBase.getEnemyPathInBattle(rightTower));
        this.elixir = 1.0;
    }


    Random random = new Random();
    public void playCard(Pane pane){
        Card selectedCard = null;
        while (selectedCard == null || selectedCard instanceof Rage || selectedCard == lastCard)
            selectedCard = selectedCards.get(random.nextInt(selectedCards.size()));
        lastCard = selectedCard;
        handleMove(selectedCard, pane);
    }
    private Card getCardInstance(Card card){
        if (card instanceof Archer)
            return new Archer(card.getDamage(), card.getHp());
        else if (card instanceof Arrows)
            return new Arrows(card.getDamage());
        else if (card instanceof BabyDragon)
            return new BabyDragon(card.getDamage(), card.getHp());
        else if (card instanceof Barbarian)
            return new Barbarian(card.getDamage(), card.getHp());
        else if (card instanceof Cannon)
            return new Cannon(card.getDamage(), card.getHp());
        else if (card instanceof FireBall)
            return new FireBall(card.getDamage());
        else if (card instanceof Giant)
            return new Giant(card.getDamage(), card.getHp());
        else if (card instanceof InfernoTower)
            return new InfernoTower(card.getDamage(), card.getHp());
        else if (card instanceof MiniPekka)
            return new MiniPekka(card.getDamage(), card.getHp());
        else if (card instanceof Rage)
            return new Rage(DataBase.getRageDuration(this.playerLevel));
        else if (card instanceof Valkyrie)
            return new Valkyrie(card.getDamage(), card.getHp());
        else if (card instanceof Wizard)
            return new Wizard(card.getDamage(), card.getHp());
        else
            return null;
    }
    private void generateCards(){
        ArrayList<Card> copy = new ArrayList<>();
        Arrows arrows = new Arrows(0);
        Archer archer = new Archer(0,0);
        BabyDragon babyDragon = new BabyDragon(0,0);
        Barbarian barbarian = new Barbarian(0,0);
        Cannon cannon = new Cannon(0,0);
        FireBall fireBall = new FireBall(0);
        Giant giant = new Giant(0,0);
        InfernoTower infernoTower = new InfernoTower(0,0);
        MiniPekka miniPekka = new MiniPekka(0,0);
        Rage rage = new Rage(0);
        Valkyrie valkyrie = new Valkyrie(0,0);
        Wizard wizard = new Wizard(0,0);
        copy.add(arrows);
        copy.add(archer);
        copy.add(babyDragon);
        copy.add(barbarian);
        copy.add(cannon);
        copy.add(fireBall);
        copy.add(giant);
        copy.add(infernoTower);
        copy.add(miniPekka);
        copy.add(rage);
        copy.add(valkyrie);
        copy.add(wizard);
        this.selectedCards = copy;
    }
    private void handleMove(Card card, Pane pane){
        card = getCardInstance(card);
        if (card instanceof Soldier){
            int i = 0;
            int count = ((Soldier) card).getCount();
            while (count > 0){
                card = getCardInstance(card);
                ((Soldier) card).setBot(true);
                card.setPathInBattle(DataBase.getPathInBattle(card, true));
                int way = random.nextInt(2);
                card.createPicture(pane, xLocs[way], yLocs[i]);
                card.startFunctioning();
                TrainingCampController.enemyInGameCards.add(card);
                count--;
                i++;
            }
        }
        card.setPathInBattle(DataBase.getPathInBattle(card, true));
        if(card instanceof Spell){
            Tower lastTower = (Tower)TrainingCampController.playerTowers.get(TrainingCampController.playerTowers.size() - 1);
            card.createPicture(pane, lastTower.getX(), lastTower.getY());
        } else if (card instanceof Building){
            if (card instanceof Cannon)
                card.createPicture(pane, 240, 150);
            if (card instanceof InfernoTower)
                card.createPicture(pane, 350, 150);
        }


        TrainingCampController.enemyInGameCards.add(card);
        card.startFunctioning();
    }
    public String getName(){
        return "simple bot";
    }

}
