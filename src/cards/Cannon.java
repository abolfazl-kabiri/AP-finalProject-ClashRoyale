package cards;

import sample.Target;

/**
 * The type Cannon.
 */
public class Cannon extends Building{
    /**
     * Instantiates a new Cannon.
     *
     * @param damage the damage
     * @param hp     the hp
     */
    public Cannon(int damage, int hp) {
        super(0.8, Target.GROUND,
                5.5, 30,
                3,
                ".\\photos\\cardsImage\\canon_00000.png", damage, hp);
    }
}
