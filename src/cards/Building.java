package cards;


import sample.Target;

public abstract class Building extends Card{
    protected double hitSpeed;
    protected Target target;
    protected double range;
    protected int lifeTime;
    protected int damage;
    protected int hp;
    public Building(double hitSpeed, Target target,
                    double range, int lifeTime,
                    int cost, String path,
                    int damage, int hp){
        super(cost, path);
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
        this.lifeTime  = lifeTime;
        this.hp = hp;
        this.damage = damage;
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
