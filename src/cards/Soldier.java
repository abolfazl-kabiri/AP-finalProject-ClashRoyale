package cards;

public class Soldier extends Card{
    protected double hitSpeed;
    protected String speed;
    protected String target;
    protected String range;
    protected boolean isAreaSplash;
    protected int count;
    public Soldier(long cost, double hitSpeed,
                   String speed, String target,
                   String range, boolean isAreaSplash,
                   int count, int level, String path) {
        super(cost, path, level);
        this.hitSpeed = hitSpeed;
        this.speed = speed;
        this.target = target;
        this.range = range;
        this.isAreaSplash = isAreaSplash;
        this.count = count;
    }
}
