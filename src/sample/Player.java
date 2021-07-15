package sample;

import cards.*;
import towers.KingTower;
import towers.PrincessTower;
import towers.Tower;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Player implements Serializable {
    protected int playerLevel;
    protected ArrayList<Card> selectedCards;
    protected ArrayList<Card> playableCards;
    protected ArrayList<Tower> towers;
    protected Card nextCard;
    protected KingTower kingTower = new KingTower(0,0, 275.0 , 426.0);
    protected PrincessTower rightTower = new PrincessTower(0,0, 377.0 , 366.0);
    protected PrincessTower leftTower = new PrincessTower(0,0, 183.0  , 366.0);
    protected int elixir;

    public Player(ArrayList<Card> userCards, int level) {
        this.playerLevel = level;
        this.selectedCards = userCards;
        towers = new ArrayList<>();
        towers.add(kingTower);
        towers.add(rightTower);
        towers.add(leftTower);
        playableCards = new ArrayList<>();
        fillPlayableCards();
        nextCard = randomNextCard();
        elixir = 4;
        fillTowerAttributes();
        fillCardsAttributes();
    }

    Random random = new Random();

    protected void fillCardsAttributes() {
        for(Card card: selectedCards){
            if(card instanceof Soldier){
                ((Soldier) card).setDamage(DataBase.getDamage(card, playerLevel));
                ((Soldier) card).setHp(DataBase.getHP(card, playerLevel) );
            } else if (card instanceof Building){
                ((Building) card).setDamage(DataBase.getDamage(card, playerLevel));
                ((Building) card).setHp(DataBase.getHP(card, playerLevel) );
            } else if (card instanceof FireBall)
                ((FireBall) card).setDamage(DataBase.getDamage(card, playerLevel));
            else if (card instanceof Arrows)
                ((Arrows) card).setDamage(DataBase.getDamage(card, playerLevel));
        }
    }
    protected void fillTowerAttributes() {
        for(Tower tower: towers){
            tower.setDamage(DataBase.getDamage(tower, playerLevel));
            tower.setHp(DataBase.getHP(tower, playerLevel));
        }
    }
    public Card randomNextCard() {
        Card card = null;
        while (playableCards.contains(card) || card == null)
            card = selectedCards.get(random.nextInt(8));
        return card;
    }
    protected void fillPlayableCards() {
        while (playableCards.size() < 4){
            Card card = selectedCards.get(random.nextInt(8));
            if(!(playableCards.contains(card)))
                playableCards.add(card);
        }
    }
    public int getElixir() {
        return elixir;
    }
    public void setElixir(int elixir) {
        this.elixir = elixir;
    }

    public ArrayList<Card> getSelectedCards() {
        return selectedCards;
    }
    public ArrayList<Card> getPlayableCards() {
        return playableCards;
    }
    public Card getNextCard() {
        return nextCard;
    }
    public KingTower getKingTower() {
        return kingTower;
    }
    public PrincessTower getRightTower() {
        return rightTower;
    }
    public PrincessTower getLeftTower() {
        return leftTower;
    }


}
