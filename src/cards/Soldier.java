package cards;

import sample.Speed;
import sample.Target;

public abstract class Soldier extends Card{
    protected double hitSpeed;
    protected Speed speed;
    protected Target target;
    protected String range;
    protected boolean isAreaSplash;
    protected int count;
    protected int damage;
    protected int hp;
    public Soldier(int cost, double hitSpeed,
                   Speed speed, Target target,
                   String range, boolean isAreaSplash,
                   int count, String path,
                   int damage, int hp) {
        super(cost, path);
        this.hitSpeed = hitSpeed;
        this.speed = speed;
        this.target = target;
        this.range = range;
        this.isAreaSplash = isAreaSplash;
        this.count = count;
        this.damage = damage;
        this.hp = hp;
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
}
