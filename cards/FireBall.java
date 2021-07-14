package cards;

public class FireBall extends Spell{
    private int damage;
    public FireBall(int damage){
        super(2.5, 4,
                ".\\photos\\cardsImage\\fireball_00000.png");
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
