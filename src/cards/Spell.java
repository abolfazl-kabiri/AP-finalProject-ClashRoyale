package cards;

public abstract class Spell extends Card{
    protected double radius;
    public Spell(double radius, int cost,
                  int level, String path){
        super(cost, path, level);
        this.radius = radius;
    }
}
