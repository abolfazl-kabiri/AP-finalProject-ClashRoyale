package sample;

import cards.*;
import controller.TrainingCampController;
import javafx.scene.layout.Pane;
import towers.Tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * The type Smart bot.
 */
public class SmartBot extends Bot{
    /**
     * Instantiates a new Smart bot.
     *
     * @param userCards the user cards
     * @param level     the level
     */
    public SmartBot(ArrayList<Card> userCards, int level) {
        super(userCards, level);
    }

    @Override
    public void playCard(Pane pane){
        this.pane = pane;
        Card selectedCard = giveCard();
        handleMove(selectedCard);
    }
    @Override
    public void handleMove(Card card){
        Card playerCard = findNearCard();
        if (playerCard.getY() <= 225){
            while (card instanceof Rage)
                card = giveCard();
            if (card instanceof Soldier){
                if (playerCard.getX() >= 289)
                    locateSoldier(card, 377.0);
                else
                    locateSoldier(card, 184.0);
            } else if (card instanceof Spell){
                locateDefensiveSpell(card, playerCard);
            } else if (card instanceof Building){
                if (!botHasBuilding()){
                    if (playerCard.getX() >= 289)
                        locateBuilding(card, 320);
                    else
                        locateBuilding(card, 220);
                } else
                    playCard(pane);

            }
        } else {
            while (card instanceof Building)
                card = giveCard();
            if (card instanceof Soldier){
                Tower tower = findWeakestTower();
                if (tower.getX() >= 289)
                    locateSoldier(card, 377.0);
                else
                    locateSoldier(card, 184.0);
            } else if (card instanceof Rage) {
                locateRage(card);
            } else if (card instanceof Spell) {
                locateAggressiveSpell(card);
            }
        }
    }

    /**
     * The Random.
     */
    Random random = new Random();

    /**
     * Give card card.
     *
     * @return the card
     */
    public Card giveCard(){
        Card selectedCard = null;
        while (selectedCard == null || selectedCard == lastCard)
            selectedCard = selectedCards.get(random.nextInt(selectedCards.size()));
        lastCard = selectedCard;
        return selectedCard;
    }

    /**
     * Locate soldier.
     *
     * @param card the card
     * @param x    the x
     */
    public void locateSoldier(Card card, double x){
        card = getCardInstance(card);
        int i = 0;
        int count = ((Soldier) card).getCount();
        while (count > 0){
            card = getCardInstance(card);
            ((Soldier) card).setBot(true);
            card.setPathInBattle(DataBase.getPathInBattle(card, true));
            card.createPicture(pane, x, yLocs[i]);
            card.startFunctioning(pane);
            TrainingCampController.enemyInGameCards.add(card);
            count--;
            i++;
        }
    }

    /**
     * Locate rage.
     *
     * @param card the card
     */
    public void locateRage(Card card){
        card = getCardInstance(card);
        Card furthest = findFurthestCard();
        TrainingCampController.enemyInGameCards.add(card);
        card.createPicture(pane, furthest.getX(), furthest.getY());
        card.startFunctioning(pane);
    }

    /**
     * Locate aggressive spell.
     *
     * @param card the card
     */
    public void locateAggressiveSpell(Card card){
        card = getCardInstance(card);
        Tower weakestTower = findWeakestTower();
        TrainingCampController.enemyInGameCards.add(card);
        card.createPicture(pane, weakestTower.getX(), weakestTower.getY());
        card.startFunctioning(pane);
    }

    /**
     * Locate defensive spell.
     *
     * @param card       the card
     * @param playerCard the player card
     */
    public void locateDefensiveSpell(Card card, Card playerCard){
        card = getCardInstance(card);
        TrainingCampController.enemyInGameCards.add(card);
        card.createPicture(pane, playerCard.getX(), playerCard.getY());
        card.startFunctioning(pane);
    }

    /**
     * Locate building.
     *
     * @param card the card
     * @param x    the x
     */
    public void locateBuilding(Card card, double x){
        card = getCardInstance(card);
        TrainingCampController.enemyInGameCards.add(card);
        card.createPicture(pane, x, 150.0);
        card.startFunctioning(pane);
    }

    /**
     * Find near card card.
     *
     * @return the card
     */
    public Card findNearCard(){
        Card card = (Card)TrainingCampController.playerInGameCards.get(0);
        synchronized (TrainingCampController.playerInGameCards){
            Iterator<Card> iterator = TrainingCampController.playerInGameCards.iterator();
            while (iterator.hasNext()){
                Card temp = iterator.next();
                if (temp.getY() < card.getY())
                    card = temp;
            }
        }
        return card;
    }

    /**
     * Find furthest card card.
     *
     * @return the card
     */
    public Card findFurthestCard(){
        Card further = (Card)TrainingCampController.enemyInGameCards.get(0);
        synchronized (TrainingCampController.enemyInGameCards){
            Iterator<Card> it = TrainingCampController.enemyInGameCards.iterator();
            while (it.hasNext()){
                Card temp = it.next();
                if (temp.getY() > further.getY())
                    further = temp;
            }
        }
        return further;
    }

    /**
     * Find weakest tower tower.
     *
     * @return the tower
     */
    public Tower findWeakestTower(){
        Tower tower = (Tower) TrainingCampController.playerTowers.get(0);
        synchronized (TrainingCampController.playerTowers){
            Iterator<Tower> playerTowers = TrainingCampController.playerTowers.iterator();
            while (playerTowers.hasNext()){
                Tower temp = playerTowers.next();
                if (temp.getHp() < tower.getHp())
                    tower = temp;
            }
        }
        return tower;
    }

    /**
     * Bot has building boolean.
     *
     * @return the boolean
     */
    public boolean botHasBuilding(){
        synchronized (TrainingCampController.enemyInGameCards){
            Iterator<Card> iterator = TrainingCampController.enemyInGameCards.iterator();
            while (iterator.hasNext()){
                Card card = iterator.next();
                if (card instanceof Building)
                    return true;
            }
        }
        return false;
    }

    @Override
    public String getName(){
        return "smart bot";
    }
}
