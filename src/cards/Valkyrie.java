package cards;

import sample.Speed;
import sample.Target;

/**
 * The type Valkyrie.
 */
public class Valkyrie extends Soldier{
    /**
     * Instantiates a new Valkyrie.
     *
     * @param damage the damage
     * @param hp     the hp
     */
    public Valkyrie(int damage, int hp){
        super(4, 1.5,
                Speed.FAST, Target.GROUND,
                "melee", true,
                1,
                ".\\photos\\cardsImage\\valkyrie_00000.png", damage, hp);
    }
}
