package cards;

import sample.Speed;
import sample.Target;

/**
 * The type Barbarian.
 */
public class Barbarian extends Soldier{
    /**
     * Instantiates a new Barbarian.
     *
     * @param damage the damage
     * @param hp     the hp
     */
    public Barbarian(int damage, int hp){
        super(5, 1.5,
                Speed.MEDIUM, Target.GROUND, "melee",
                false, 4,
                ".\\photos\\cardsImage\\barbarian_00000.png", damage, hp);
    }
}
