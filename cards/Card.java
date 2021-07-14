package cards;

import java.io.Serializable;

public abstract class Card implements Serializable {
    protected long cost;
    protected String path;
    public Card(long cost, String path){
        this.cost = cost;
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
