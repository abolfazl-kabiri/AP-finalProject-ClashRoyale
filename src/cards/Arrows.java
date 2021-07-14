package cards;

public class Arrows extends Spell{
    private int damage;
    public Arrows(int damage){
        super(4, 3,
                ".\\photos\\cardsImage\\arrows_00000.png");
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
