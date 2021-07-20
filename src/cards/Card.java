package cards;

import controller.TrainingCampController;
import javafx.animation.Timeline;
import sample.DataBase;
import sample.GameElement;


public abstract class Card extends GameElement {
    protected boolean isRaged;
    protected int damage;
    protected int hp;
    protected int cost;
    protected String path;
    transient protected Timeline movingAnimation;
    transient protected Timeline hittingAnimation;
    public Card(int cost, String path, int damage, int hp){
        super(0.0, 0.0, 43, 43);
        this.cost = cost;
        this.path = path;
        this.damage = damage;
        this.hp = hp;
        this.setPathInBattle(DataBase.getPathInBattle(this, true));
        isRaged = false;
    }

    public void startFunctioning(){

    }
    public void move(){

    }
    public void attack(){

    }
    public String getPath() {
        return path;
    }
    public int getCost() {
        return cost;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void rageIt(){
        damage /= 1.4;
        this.isRaged = true;
    }

    public void unRageIt(){
        damage *= 1.4;
        this.isRaged = false;
    }

    public boolean isRaged() {
        return isRaged;
    }
}
