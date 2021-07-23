package cards;

import sample.Speed;
import sample.Target;

/**
 * The type Giant.
 */
public class Giant extends Soldier{
    /**
     * Instantiates a new Giant.
     *
     * @param damage the damage
     * @param hp     the hp
     */
    public Giant(int damage, int hp){
        super(5, 1.5,
                Speed.SLOW, Target.BUILDINGS,
                "melee", false,
                1,
                ".\\photos\\cardsImage\\giant_00000.png", damage, hp);
    }
}
