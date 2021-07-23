package cards;

import sample.Speed;
import sample.Target;

/**
 * The type Mini pekka.
 */
public class MiniPekka extends Soldier{
    /**
     * Instantiates a new Mini pekka.
     *
     * @param damage the damage
     * @param hp     the hp
     */
    public MiniPekka(int damage, int hp){
        super(4, 1.8,
                Speed.FAST, Target.GROUND,
                "melee", false,
                1,
                ".\\photos\\cardsImage\\mini pekka_00000.png", damage, hp);
    }
}
