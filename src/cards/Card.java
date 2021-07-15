package cards;

import sample.DataBase;
import sample.GameElement;


public abstract class Card extends GameElement {
    protected int cost;
    protected String path;
    public Card(int cost, String path){
        super(0.0, 0.0, 43, 43);
        this.cost = cost;
        this.path = path;
        this.setPathInBattle(DataBase.getPathInBattle(this));
    }

    public void move(){

    }

    public void attack(){

    }

    public String getPath() {
        return path;
    }
}
