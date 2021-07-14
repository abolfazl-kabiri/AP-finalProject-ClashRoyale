package towers;

import java.io.Serializable;

public abstract class Tower implements Serializable {

    protected int hp;
    protected int damage;
    protected double hitSpeed;
    protected double range;
    protected boolean isActive;
    protected String path;

    public Tower(int hp, int damage, double hitSpeed, double range, boolean isActive, String path) {
        this.hp = hp;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.range = range;
        this.isActive = isActive;
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}
