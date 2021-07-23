package sample;

import cards.*;
import towers.KingTower;
import towers.PrincessTower;
import towers.Tower;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Player.
 */
public class Player implements Serializable {
    /**
     * The Number of stars.
     */
    transient protected int numberOfStars;
    /**
     * The Player level.
     */
    protected int playerLevel;
    /**
     * The Selected cards.
     */
    protected ArrayList<Card> selectedCards;
    /**
     * The Playable cards.
     */
    protected ArrayList<Card> playableCards;
    /**
     * The Towers.
     */
    protected ArrayList<Tower> towers;
    /**
     * The Next card.
     */
    protected Card nextCard;
    /**
     * The King tower.
     */
    protected KingTower kingTower = new KingTower(0,0, 275.0, 426.0);
    /**
     * The Right tower.
     */
    protected PrincessTower rightTower = new PrincessTower(0,0, 377.0 , 366.0);
    /**
     * The Left tower.
     */
    protected PrincessTower leftTower = new PrincessTower(0,0, 183.0 , 366.0);
    /**
     * The Elixir.
     */
    protected double elixir;

    /**
     * Instantiates a new Player.
     *
     * @param userCards the user cards
     * @param level     the level
     */
    public Player(ArrayList<Card> userCards, int level) {
        numberOfStars = 0;
        this.playerLevel = level;
        this.selectedCards = userCards;
        towers = new ArrayList<>();
        towers.add(kingTower);
        towers.add(rightTower);
        towers.add(leftTower);
        playableCards = new ArrayList<>();
        fillPlayableCards();
        nextCard = randomNextCard();
        elixir = .4;
        fillTowerAttributes();
        fillCardsAttributes();
    }

    /**
     * The Random.
     */
    Random random = new Random();

    /**
     * Fill cards attributes.
     */
    protected void fillCardsAttributes() {
        for(Card card: selectedCards){
            if(card instanceof Soldier){
                card.setDamage(DataBase.getDamage(card, playerLevel));
                card.setHp(DataBase.getHP(card, playerLevel) );
            } else if (card instanceof Building){
                card.setDamage(DataBase.getDamage(card, playerLevel));
                card.setHp(DataBase.getHP(card, playerLevel) );
            } else if (card instanceof FireBall)
                card.setDamage(DataBase.getDamage(card, playerLevel));
            else if (card instanceof Arrows)
                card.setDamage(DataBase.getDamage(card, playerLevel));
        }
    }

    /**
     * Fill tower attributes.
     */
    protected void fillTowerAttributes() {
        for(Tower tower: towers){
            tower.setDamage(DataBase.getDamage(tower, playerLevel));
            tower.setHp(DataBase.getHP(tower, playerLevel));
        }
    }

    /**
     * Random next card card.
     *
     * @return the card
     */
    public Card randomNextCard() {
        Card card = null;
        while (playableCards.contains(card) || card == null)
            card = selectedCards.get(random.nextInt(8));
        return card;
    }

    /**
     * Fill playable cards.
     */
    protected void fillPlayableCards() {
        while (playableCards.size() < 4){
            Card card = selectedCards.get(random.nextInt(8));
            if(!(playableCards.contains(card)))
                playableCards.add(card);
        }
    }

    /**
     * Sets elixir.
     *
     * @param elixir the elixir
     */
    public void setElixir(double elixir) {
        this.elixir = elixir;
    }

    /**
     * Gets elixir.
     *
     * @return the elixir
     */
    public double getElixir() {
        return elixir;
    }

    /**
     * Update elixir.
     */
    public void updateElixir() {
        this.elixir += .1;
        if(this.elixir >= 1)
            this.elixir = 1.0;
    }

    /**
     * Gets playable cards.
     *
     * @return the playable cards
     */
    public ArrayList<Card> getPlayableCards() {
        return playableCards;
    }

    /**
     * Gets next card.
     *
     * @return the next card
     */
    public Card getNextCard() {
        return nextCard;
    }

    /**
     * Gets king tower.
     *
     * @return the king tower
     */
    public KingTower getKingTower() {
        return kingTower;
    }

    /**
     * Gets right tower.
     *
     * @return the right tower
     */
    public PrincessTower getRightTower() {
        return rightTower;
    }

    /**
     * Gets left tower.
     *
     * @return the left tower
     */
    public PrincessTower getLeftTower() {
        return leftTower;
    }

    /**
     * Gets player level.
     *
     * @return the player level
     */
    public int getPlayerLevel() {
        return playerLevel;
    }

    /**
     * Update playable cards card.
     *
     * @param card the card
     * @return the card
     */
    public Card updatePlayableCards(Card card){
        playableCards.remove(card);
        playableCards.add(nextCard);
        nextCard = randomNextCard();
        return nextCard;
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
     * Sets number of stars.
     *
     * @param numberOfStars the number of stars
     */
    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }
}
