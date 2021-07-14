package cards;

import java.io.Serializable;

public abstract class Card implements Serializable {
    protected int cost;
    protected String path;
    protected int level;
    public Card(int cost, String path){
        this.cost = cost;
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
