package cards;

import sample.Target;

/**
 * The type Inferno tower.
 */
public class InfernoTower extends Building{
    /**
     * Instantiates a new Inferno tower.
     *
     * @param damage the damage
     * @param hp     the hp
     */
    public InfernoTower(int damage, int hp) {
        super(0.4, Target.AIRandGROUND, 6, 40, 5,
                ".\\photos\\cardsImage\\inferno_00000.png", damage, hp);
    }
}
