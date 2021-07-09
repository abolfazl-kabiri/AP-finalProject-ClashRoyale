package cards;

public class Card {
    protected long cost;
    protected String path;
    protected int level;
    public Card(long cost, String path, int level){
        this.cost = cost;
        this.path = path;
        this.level = level;
    }
}
