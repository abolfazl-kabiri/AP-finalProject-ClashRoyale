package cards;

public class Building extends Card{
    protected double hitSpeed;
    protected String target;
    protected double range;
    protected int lifeTime;
    public Building(double hitSpeed, String target,
                    double range, int lifeTime,
                    int cost, int level,
                    String path){
        super(cost, path, level);
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
        this.lifeTime  = lifeTime;
    }
}
