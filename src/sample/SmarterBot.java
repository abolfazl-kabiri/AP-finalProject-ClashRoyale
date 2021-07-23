package sample;

import cards.*;
import controller.TrainingCampController;
import javafx.scene.layout.Pane;
import towers.Tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * The type Smarter bot.
 */
public class SmarterBot extends SmartBot{
    /**
     * The Y offensive locs.
     */
    protected final double [] yOffensiveLocs = {240 , 260, 280, 300};

    /**
     * Instantiates a new Smarter bot.
     *
     * @param userCards the user cards
     * @param level     the level
     */
    public SmarterBot(ArrayList<Card> userCards, int level) {
        super(userCards, level);
    }

    @Override
    public void playCard(Pane pane){
        this.pane = pane;
        if (TrainingCampController.playerInGameCards.size() > 0){
            Card playerCard = findNearCard();
            if (playerCard.getY() < 220)
                playDefensive(playerCard);
            else
                playOffensive();
        } else
            playOffensive();

    }

    /**
     * The Random.
     */
    Random random = new Random();

    /**
     * Play offensive.
     */
    public void playOffensive(){
        int choice = 0;
        Card selectedCard = null;
        Tower weakestTower = findWeakestTower();
        if (weakestTower.getHp() <= DataBase.getDamage(new Arrows(0), playerLevel))
            selectedCard = getMaxArrow();
        else if (weakestTower.getHp() <= DataBase.getDamage(new FireBall(0), playerLevel) && !(lastCard instanceof FireBall))
            selectedCard = getMaxFireball();
        else if (TrainingCampController.enemyInGameCards.size() > 0
                && findFurthestCard().getY() > 300 && !botHasRage()
                && !(lastCard instanceof Rage))
            selectedCard = getMaxRage();
        else if (playerHasBuilding() && !botHasGiant())
            selectedCard = getMaxGiant();
        else if (!playerHasWizOrArcher() && !(lastCard instanceof MiniPekka) && !(lastCard instanceof BabyDragon)){
            choice = random.nextInt(2);
            switch (choice){
                case 0:
                    selectedCard = getMaxBabyDragon();
                    break;
                case 1:
                    selectedCard = getMaxMinniPekka();
                    break;
            }
        } else {
            if (playerHasValkyrie() && !(lastCard instanceof Wizard) && !(lastCard instanceof Archer)){
                choice = random.nextInt(2);
                switch (choice) {
                    case 0:
                        selectedCard = getMaxWizard();
                        break;
                    case 1:
                        selectedCard = getMaxArcher();
                        break;
                }
            } else {
                choice = random.nextInt(3);
                switch (choice) {
                    case 0:
                        selectedCard = getMaxWizard();
                        break;
                    case 1:
                        selectedCard = getMaxArcher();
                        break;
                    case 2:
                        selectedCard = getMaxBarbarian();
                }
            }
        }
        lastCard = selectedCard;
        handleOffensive(selectedCard, weakestTower);

    }

    /**
     * Handle offensive.
     *
     * @param selectedCard the selected card
     * @param weakestTower the weakest tower
     */
    public void handleOffensive(Card selectedCard, Tower weakestTower){
        if (selectedCard instanceof FireBall || selectedCard instanceof Arrows)
            locateAggressiveSpell(selectedCard);
        else if (selectedCard instanceof Rage)
            locateRage(selectedCard);
        else if (selectedCard instanceof Giant)
            locateGiant(selectedCard);
        else {
            if (TrainingCampController.playerTowers.size() == 3){
                if (weakestTower.getX() >= 289)
                    locateSoldier(selectedCard, 377.0);
                else
                    locateSoldier(selectedCard, 184.0);
            } else if (TrainingCampController.playerTowers.size() == 2){
                locateOffensiveSoldier(selectedCard, getXOfDestroyedTower());
            } else {
                locateOffensiveSoldier(selectedCard, 275.0);
            }
        }

    }

    /**
     * Play defensive.
     *
     * @param playerCard the player card
     */
    public void playDefensive(Card playerCard){
        int choice = 0;
        Card selectedCard = null;
        if (playerCard instanceof BabyDragon){
            choice = random.nextInt(3);
            switch (choice) {
                case 0:
                    selectedCard = getMaxWizard();
                    break;
                case 1:
                    selectedCard = getMaxFireball();
                    break;
                case 2:
                    selectedCard = getMaxArcher();
                    break;
            }
        } else if (playerCard instanceof Barbarian || playerCard instanceof MiniPekka || playerCard instanceof Valkyrie) {
            choice = random.nextInt(3);
            switch (choice) {
                case 0:
                    selectedCard = getMaxWizard();
                    break;
                case 1:
                    selectedCard = getMaxFireball();
                    break;
                case 2:
                    selectedCard = getMaxValkyrie();
            }
        } else if (playerCard instanceof Giant){
            choice = random.nextInt(2);
            switch (choice) {
                case 0:
                    selectedCard = getMaxCannon();
                    break;
                case 1:
                    selectedCard = getMaxInferno();
                    break;
            }
        } else if (playerCard instanceof Wizard || playerCard instanceof Archer){
            choice = random.nextInt(3);
            switch (choice) {
                case 0:
                    selectedCard = getMaxWizard();
                    break;
                case 1:
                    selectedCard = getMaxFireball();
                    break;
                case 2:
                    selectedCard = getMaxArrow();
                    break;
            }
        } else
            playOffensive();
        handleDefensive(selectedCard, playerCard);
    }

    /**
     * Handle defensive.
     *
     * @param selectedCard the selected card
     * @param playerCard   the player card
     */
    public void handleDefensive(Card selectedCard, Card playerCard){
        if (selectedCard instanceof Soldier){
            if (playerCard.getX() >= 289)
                locateSoldier(selectedCard, 377.0);
            else
                locateSoldier(selectedCard, 184.0);
        } else if (selectedCard instanceof Spell)
            locateDefensiveSpell(selectedCard, playerCard);
        else if (selectedCard instanceof Building){
            if (!botHasBuilding()){
                if (playerCard.getX() >= 289)
                    locateBuilding(selectedCard, 320);
                else
                    locateBuilding(selectedCard, 220);
            } else
                playDefensive(playerCard);
        }
    }

    /**
     * Locate giant.
     *
     * @param selectedCard the selected card
     */
    public void locateGiant(Card selectedCard){
        Card playerBuilding = givePlayerBuilding();
        if (playerBuilding.getX() >= 289)
            locateSoldier(selectedCard, 377.0);
        else
            locateSoldier(selectedCard, 184.0);
    }

    /**
     * Locate offensive soldier.
     *
     * @param selectedCard the selected card
     * @param x            the x
     */
    public void locateOffensiveSoldier(Card selectedCard, double x){
        selectedCard = getCardInstance(selectedCard);
        int i = 0;
        int count = ((Soldier) selectedCard).getCount();
        while (count > 0){
            selectedCard = getCardInstance(selectedCard);
            ((Soldier) selectedCard).setBot(true);
            selectedCard.setPathInBattle(DataBase.getPathInBattle(selectedCard, true));
            selectedCard.createPicture(pane, x, yOffensiveLocs[i]);
            selectedCard.startFunctioning(pane);
            TrainingCampController.enemyInGameCards.add(selectedCard);
            count--;
            i++;
        }
    }

    /**
     * Get x of destroyed tower double.
     *
     * @return the double
     */
    public double getXOfDestroyedTower(){
        synchronized (TrainingCampController.playerTowers){
            Iterator<Tower> playerTowers = TrainingCampController.playerTowers.iterator();
            while (playerTowers.hasNext()){
                Tower tower = playerTowers.next();
                if (tower.getX() > 375 && tower.getX() < 379)
                    return 183.0;
                else if (tower.getX() > 181.0 && tower.getX() < 185.0)
                    return 377.0;
            }
        }
        return 0;
    }

    /**
     * Give player building card.
     *
     * @return the card
     */
    public Card givePlayerBuilding(){
        synchronized (TrainingCampController.playerInGameCards){
            Iterator<Card> iterator = TrainingCampController.playerInGameCards.iterator();
            while (iterator.hasNext()){
                Card card = iterator.next();
                if (card instanceof Building)
                    return card;
            }
        }
        return null;
    }

    /**
     * Bot has rage boolean.
     *
     * @return the boolean
     */
    public boolean botHasRage(){
        synchronized (TrainingCampController.enemyInGameCards){
            Iterator<Card> iterator = TrainingCampController.enemyInGameCards.iterator();
            while (iterator.hasNext()){
                Card card = iterator.next();
                if (card instanceof Rage)
                    return true;
            }
        }
        return false;
    }

    /**
     * Player has building boolean.
     *
     * @return the boolean
     */
    public boolean playerHasBuilding(){
        synchronized (TrainingCampController.playerInGameCards){
            Iterator<Card> iterator = TrainingCampController.playerInGameCards.iterator();
            while (iterator.hasNext()){
                Card card = iterator.next();
                if (card instanceof Building)
                    return true;
            }
        }
        return false;
    }

    /**
     * Bot has giant boolean.
     *
     * @return the boolean
     */
    public boolean botHasGiant(){
        synchronized (TrainingCampController.enemyInGameCards){
            Iterator<Card> iterator = TrainingCampController.enemyInGameCards.iterator();
            while (iterator.hasNext()){
                Card card = iterator.next();
                if (card instanceof Giant)
                    return true;
            }
        }
        return false;
    }

    /**
     * Player has wiz or archer boolean.
     *
     * @return the boolean
     */
    public boolean playerHasWizOrArcher(){
        synchronized (TrainingCampController.playerInGameCards){
            Iterator<Card> iterator = TrainingCampController.playerInGameCards.iterator();
            while (iterator.hasNext()){
                Card card = iterator.next();
                if (card instanceof Wizard || card instanceof Archer)
                    return true;
            }
        }
        return false;
    }

    /**
     * Player has valkyrie boolean.
     *
     * @return the boolean
     */
    public boolean playerHasValkyrie(){
        synchronized (TrainingCampController.playerInGameCards){
            Iterator<Card> iterator = TrainingCampController.playerInGameCards.iterator();
            while (iterator.hasNext()){
                Card card = iterator.next();
                if (card instanceof Valkyrie)
                    return true;
            }
        }
        return false;
    }

    private Card getMaxArrow() {
        return new Arrows(210);
    }
    private Card getMaxArcher(){
        return new Archer(58, 182);
    }
    private Card getMaxBabyDragon(){
        return new BabyDragon(146, 1168);
    }
    private Card getMaxBarbarian(){
        return new Barbarian(109, 480);
    }
    private Card getMaxGiant(){
        return new Giant(183, 2920);
    }
    private Card getMaxWizard(){
        return new Wizard(189, 496);
    }
    private Card getMaxValkyrie(){
        return new Valkyrie(175, 1284);
    }
    private Card getMaxMinniPekka(){
        return new MiniPekka(474, 876);
    }
    private Card getMaxRage(){
        return new Rage(8);
    }
    private Card getMaxFireball(){
        return new FireBall(474);
    }
    private Card getMaxCannon(){
        return new Cannon(87, 544);
    }
    private Card getMaxInferno(){
        return new InfernoTower(29, 1168);
    }

    @Override
    public String getName(){
        return "smarter bot";
    }
}
