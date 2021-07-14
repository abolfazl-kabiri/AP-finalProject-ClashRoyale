package cards;

public abstract class Spell extends Card{
    protected double radius;
    public Spell(double radius, int cost,
                   String path){
        super(cost, path);
        this.radius = radius;
    }
}
