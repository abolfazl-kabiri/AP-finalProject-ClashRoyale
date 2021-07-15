package sample;

import cards.Card;
import towers.KingTower;
import towers.PrincessTower;

import java.util.ArrayList;

public class Bot extends Player{
    public Bot(ArrayList<Card> userCards, int level) {
        super(userCards, level);
        this.kingTower = new KingTower(0,0, 275.0, 21.0);
        this.leftTower = new PrincessTower(0,0, 183.0, 81.0);
        this.rightTower = new PrincessTower(0,0, 377.0, 81.0);
        kingTower.setPathInBattle(DataBase.getEnemyPathInBattle(kingTower));
        leftTower.setPathInBattle(DataBase.getEnemyPathInBattle(leftTower));
        rightTower.setPathInBattle(DataBase.getEnemyPathInBattle(rightTower));
    }


}
